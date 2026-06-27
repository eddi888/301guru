package guru.threezeroone;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

@Configuration
@Profile("local")
@EnableDynamoDBRepositories(basePackages = "guru.threezeroone")
public class LocalDynamoDBConfig {
	private static final Object SERVER_MONITOR = new Object();
	private static Object sharedServer;

	@Value("${amazon.dynamodb.local.port:8000}")
	private int localPort;

	@Value("${amazon.dynamodb.region:eu-central-1}")
	private String region;

	@Value("${amazon.aws.accesskey:dummy}")
	private String accessKey;

	@Value("${amazon.aws.secretkey:dummy}")
	private String secretKey;

	@Value("${amazon.dynamodb.sqlite.lib.path:${user.home}/.m2/repository/com/almworks/sqlite4java/libsqlite4java-linux-amd64/1.0.392}")
	private String sqliteLibraryPath;

	@Bean
	public LocalDynamoDBServer localDynamoDBServer() {
		startServerIfRequired();
		return new LocalDynamoDBServer();
	}

	private void startServerIfRequired() {
		synchronized (SERVER_MONITOR) {
			if (sharedServer != null) {
				return;
			}

			System.setProperty("sqlite4java.library.path", sqliteLibraryPath);

		String[] localArgs = { "-inMemory", "-sharedDb", "-port", String.valueOf(localPort) };
		try {
			Class<?> serverRunnerClass = Class.forName("com.amazonaws.services.dynamodbv2.local.main.ServerRunner");
			Object server = serverRunnerClass.getMethod("createServerFromCommandLineArgs", String[].class)
					.invoke(null, (Object) localArgs);
			server.getClass().getMethod("start").invoke(server);
			sharedServer = server;
			Runtime.getRuntime().addShutdownHook(new Thread(this::stopSharedServer));
		} catch (Exception ex) {
			throw new IllegalStateException(
					"Embedded DynamoDB konnte nicht gestartet werden. Maven-Profil 'local-embedded-dynamodb' aktivieren.",
					ex);
		}
		}
	}

	private void stopSharedServer() {
		synchronized (SERVER_MONITOR) {
			if (sharedServer == null) {
				return;
			}
			try {
				sharedServer.getClass().getMethod("stop").invoke(sharedServer);
			} catch (Exception ex) {
				throw new IllegalStateException("Embedded DynamoDB konnte nicht gestoppt werden.", ex);
			}
		}
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB(LocalDynamoDBServer localDynamoDBServer) {
		return AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:" + localPort, region))
				.build();
	}

	@Bean
	public CommandLineRunner ensureLocalTables(AmazonDynamoDB amazonDynamoDB) {
		return args -> {
			ensureTable(amazonDynamoDB, "url", "shortToken");
			ensureTable(amazonDynamoDB, "webuser", "id");
		};
	}

	private void ensureTable(AmazonDynamoDB amazonDynamoDB, String tableName, String hashKeyName) {
		for (int attempt = 0; attempt < 20; attempt++) {
			try {
				amazonDynamoDB.describeTable(tableName);
				return;
			} catch (ResourceNotFoundException ex) {
				CreateTableRequest request = new CreateTableRequest()
						.withTableName(tableName)
						.withKeySchema(new KeySchemaElement().withAttributeName(hashKeyName).withKeyType(KeyType.HASH))
						.withAttributeDefinitions(new AttributeDefinition().withAttributeName(hashKeyName)
								.withAttributeType(ScalarAttributeType.S))
						.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
				amazonDynamoDB.createTable(request);
				sleepShort();
			} catch (AmazonDynamoDBException ex) {
				if (!"InternalFailure".equals(ex.getErrorCode())) {
					throw ex;
				}
				sleepShort();
			}
		}
		throw new IllegalStateException("Embedded DynamoDB Tabelle konnte nicht erstellt/geladen werden: " + tableName);
	}

	public static class LocalDynamoDBServer {
	}

	private void sleepShort() {
		try {
			Thread.sleep(200L);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new IllegalStateException("Wartezeit auf Embedded DynamoDB wurde unterbrochen.", ex);
		}
	}
}
