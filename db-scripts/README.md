

Ejecutar
```
docker run \
--name challenge-postgres \
-e POSTGRES_USER=usr_apirestchallenge \
-e POSTGRES_PASSWORD=mysecretpassworddb \
-e POSTGRES_DB=db_tenpochallenge \
-p 5432:5432 \
-d postgres
```