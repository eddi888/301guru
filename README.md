# 301guru

URL-Shortener auf Basis von Spring Boot. Lange URLs werden auf einen kurzen Token
gemappt; ein Aufruf des Tokens leitet per HTTP-301 auf die Originaladresse weiter.
Klicks und Verkürzungen werden je URL gezählt und auf einer Statistikseite angezeigt.

## Tech-Stack

- Java 8, Spring Boot 1.5.2
- Spring MVC + Thymeleaf (Web-UI), Spring Security
- DynamoDB als Datenspeicher (lokal: Embedded DynamoDB, Profil `local`)
- Build mit Maven, Auslieferung als Docker-Image

## Endpunkte

| Methode | Pfad                 | Beschreibung                                       |
|---------|----------------------|----------------------------------------------------|
| GET     | `/`                  | Startseite mit Eingabeformular                     |
| POST    | `/`                  | URL verkürzen (Parameter `fullUrl`)                |
| GET     | `/{shortToken}`      | Weiterleitung (HTTP 301) auf die Original-URL      |
| GET     | `/stats`             | Liste aller verkürzten URLs mit Zählern            |
| GET     | `/stats/{shortToken}`| Statistik einer einzelnen URL                      |

Login (Spring Security, In-Memory): Benutzer `user`, Passwort `password`.
Geschützt sind aktuell nur die Pfade `/statistics` und `/webuser`.

## Voraussetzungen

- JDK 8
- Maven (kein Wrapper im Repo, System-`mvn` nutzen)
- Docker (optional, für Container-Betrieb)

## Build

```console
mvn -DskipTests clean package
docker build -t guru.301/url-shorter .
```

## Lokal starten (ohne Docker)

```console
mvn spring-boot:run
```

App-Aufruf: http://localhost:7070

Profil `local` (Default) startet mit Embedded DynamoDB. Konfiguration siehe
`src/main/resources/application-local.properties` (Port `7070`, DynamoDB-Port `8000`,
Region `eu-central-1`).

## Mit Docker starten

```console
docker rm -f 301guru || true
docker run -d -p 7070:7070 --name 301guru guru.301/url-shorter
```

Der Container läuft mit Java 8, Spring-Profil `local` und Embedded DynamoDB
(Port `8000` im Container). App-Aufruf: http://localhost:7070

> Hinweis: Bei Änderung von Version/Artifact in `pom.xml` das `ARG JAR_FILE` im
> `Dockerfile` anpassen (aktuell `target/301guru-0.0.1-SNAPSHOT.jar`).

## Tests

```console
# Schneller, lokal sicherer Unit-Test
mvn -Dtest=UrlServiceUnitTest test

# Vollständige Tests (inkl. Integrationstests gegen DynamoDB)
mvn test
```

## Engineering-Leitplanken

- Verbindliche Arbeitsregeln stehen in `.specify/memory/constitution.md`.
- Vor größeren Änderungen an Routen, DynamoDB oder Docker-Artifact den
  Constitution Check in `.specify/templates/plan-template.md` verwenden.

## Troubleshooting

Umgebung prüfen:

```console
java -version
mvn --version
docker --version
```

Container-Status und Logs:

```console
docker ps -a
docker start -a 301guru
docker logs 301guru
```
