# Mock API Dynamic Percentage


Para levantar localmente ejecute:
```
docker run --rm -ti -p 8081:8080 \
    -v $PWD/mock-dynamic-percentage/config:/opt/imposter/config \
    -d outofcoffee/imposter
```

Para probarlo con curl, ejecute:
```
curl -X 'GET' \
  'http://localhost:8081/external-percentage' \
  -H 'accept: application/json'
```