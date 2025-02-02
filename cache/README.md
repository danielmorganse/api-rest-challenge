

Ejecutar:
```
docker run \
-p 6379:6379 \
--name challenge-redis \
-d redis redis-server --appendonly yes  --requirepass "mysecretpasswordcache" 
```