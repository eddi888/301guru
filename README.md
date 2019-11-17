
# Prepair
AWS Keys


# Build
```console
mvn clean package

docker build -t guru.301/url-shorter .
```


# Run on Docker
```console
docker rm -f 301guru || true && docker run -d -p 8080:8080 --name 301guru guru.301/url-shorter
```


# Deploy on Kubernetes
kubectl apply -f deploy/deployment.yml
kubectl apply -f deploy/service.yml
kubectl apply -f deploy/ingress.yml


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

```console
kubectl get namespaces
```

```console
kubectl get deployment
```

```console
kubectl get pods -o wide
```
```console
kubectl logs
```

```console
kubectl exec tree01guru-<????POD-ID????> wget localhost:8080
kubectl exec tree01guru-<????POD-ID????> cat index.html
kubectl exec tree01guru-<????POD-ID????> rm index.html
```

```console
kubectl apply --force -f deployment.yml 
```

```console
kubectl delete pods tree01guru-<????POD-ID????>
```

```console
kubectl get service
```

```console
kubectl describe service tree01guru
```

```console
kubectl get deployments
```

```console
kubectl delete deployment tree01guru
```

```console
kubectl describe ingress tree01guru
```

```console
kubectl cluster-info
```

```console
kubectl get all --all-namespaces
```
