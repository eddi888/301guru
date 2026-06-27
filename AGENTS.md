# AGENTS

## Struktur und Einstiegspunkte
- Single-Module Maven Spring Boot App (`pom.xml`), Java 8, Spring Boot `1.5.2.RELEASE`.
- App-Einstiegspunkt: `src/main/java/guru/threezeroone/Application.java`.
- Der reale Feature-Pfad ist `guru.threezeroone.url` (`UrlController` -> `UrlService` -> `UrlRepository` -> DynamoDB `Url`).
- `guru.threezeroone.webuser` ist überwiegend Scaffold/TODO; kein vollständiges User-Management-Verhalten annehmen.

## Wichtige Kommandos
- Es ist kein Maven-Wrapper committed; nutze System-Maven (`mvn`).
- Schneller, lokal sicherer Check: `mvn -Dtest=UrlServiceUnitTest test`.
- Vollständige Tests: `mvn test` (enthält Integrationstests mit echter DynamoDB-Config/AWS-Credentials).
- App starten: `mvn spring-boot:run`.
- Docker-Image bauen: `docker build -t guru.301/url-shorter .`.
- Container lokal starten: `docker rm -f 301guru || true && docker run -d -p 7070:7070 --name 301guru guru.301/url-shorter` (App-Port 7070, Embedded DynamoDB auf Port 8000 im Container).

## DynamoDB/Test-Fallen
- `DynamoDBConfig` hardcoded die Region `EU_CENTRAL_1` und liest Keys aus `amazon.aws.accesskey` / `amazon.aws.secretkey`.
- Integrationstests laden `classpath:test.properties`, das in `src/main/resources/test.properties` liegt (nichtstandardmäßiger Ort).
- DynamoDB-Tabellennamen sind per Annotation fix: `Url` -> `url`, `Webuser` -> `webuser`.
- `UrlRepository`-Query-Methoden nutzen `@EnableScan`; Listen/Lookups sind Table-Scans, keine Index-Abfragen.

## Auth, Routen, Laufzeit
- Security ist In-Memory: `user` / `password` (`WebSecurityConfig`).
- Geschützte Pfade sind nur `"/statistics"` und `"/webuser"`; URL-Statistikseiten liegen unter `"/stats"` und `"/stats/{shortToken}"`.
- Das `Dockerfile` kopiert `target/301guru-0.0.1-SNAPSHOT.jar`; `ARG JAR_FILE` bei Änderungen an Artifact/Version in `pom.xml` anpassen.
