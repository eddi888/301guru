
# Clone
```console
git clone <REPO_URL>
cd 301guru
```

# Build
```console
mvn -DskipTests clean package
docker build -t guru.301/url-shorter .
```


# Run on Docker
```console
docker rm -f 301guru || true
docker run -d -p 7070:7070 --name 301guru guru.301/url-shorter
```

Container startet mit Java 8, Spring-Profil `local` und Embedded DynamoDB (Port `8000` im Container).

App aufrufen:

```console
http://localhost:7070
```


# Problems / troubleshooting?
Test your enviroment, e.g.:

```console
java -version
```

```console
mvn --version
```

```console
docker --version
```

```console
docker ps -a
```

```console
docker run hello-world
```

```console
docker image
```

```console
docker start -a 301guru
```

```console
docker save
```

