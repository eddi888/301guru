FROM eclipse-temurin:8-jre-jammy
VOLUME /tmp
EXPOSE 7070

ARG JAR_FILE=target/301guru-0.0.1-SNAPSHOT.jar

RUN apt-get update \
    && apt-get install -y --no-install-recommends wget ca-certificates \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /opt/sqlite4java \
    && wget -O /opt/sqlite4java/libsqlite4java-linux-amd64-1.0.392.so \
    "https://repo1.maven.org/maven2/com/almworks/sqlite4java/libsqlite4java-linux-amd64/1.0.392/libsqlite4java-linux-amd64-1.0.392.so" \
    && cp /opt/sqlite4java/libsqlite4java-linux-amd64-1.0.392.so /opt/sqlite4java/libsqlite4java-linux-amd64.so \
    && cp /opt/sqlite4java/libsqlite4java-linux-amd64-1.0.392.so /opt/sqlite4java/libsqlite4java.so

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=local","-Dserver.port=7070","-Damazon.dynamodb.sqlite.lib.path=/opt/sqlite4java","-jar","/app.jar"]
