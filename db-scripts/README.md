

Ejecutar
```
docker run \
--name challenge-postgres \
-e POSTGRES_PASSWORD=mysecretpassword \
-p 5432:5432 \
-d postgres
```