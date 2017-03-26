package guru.threezeroone.url;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "url")
public class Url {
	
	private String shortToken;
	private String fullUrl;
	private long clickCount;
	private long shortenCount;
	
	public Url() {
	}
	
	public Url(String shortToken, String fullUrl) {
		this.shortToken=shortToken;
		this.fullUrl=fullUrl;
	}
	
	@DynamoDBHashKey
	public String getShortToken() {
		return shortToken;
	}
	public void setShortToken(String shortToken) {
		this.shortToken = shortToken;
	}
	
	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}
	
	public long getClickCount() {
		return clickCount;
	}
	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}

	public long getShortenCount() {
		return shortenCount;
	}

	public void setShortenCount(long shortenCount) {
		this.shortenCount = shortenCount;
	}
	
	
}
