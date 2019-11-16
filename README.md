
# Prepair
AWS Keys

# Build
```console
mvn clean package

docker build -t guru.301/url-shorter .
```


# Run
```console
docker rm -f 301guru || true && docker run -d -p 8080:8080 --name 301guru guru.301/url-shorter
```


# Problems?
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
docker start -a 301guru
```
