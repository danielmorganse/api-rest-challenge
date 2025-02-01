# API REST - Cálculo con Porcentaje Dinámico

Este proyecto es una API REST desarrollada en Spring Boot utilizando Java 21. 
Su objetivo es proporcionar una funcionalidad para calcular la suma de dos números
y aplicar un porcentaje dinámico obtenido de un servicio externo, además de 
implementar caché, reintentos ante fallos, historial de llamadas, control de 
tasas de consumo (Rate Limiting) y manejo adecuado de errores.

## Instrucciones de Ejecución

1. Clonar el repositorio:
```
git clone <URL_DEL_REPOSITORIO>
cd rest-api-challenge
```

2. Ejecutar con Docker:
```
docker-compose up --build
```

3. Acceder a la API:
   - Swagger UI: http://localhost:8081/swagger-ui/index.html
   - Endpoint calculo: POST /calculate
   - Endpoint historial: GET /history

4. Probar los Endpoints con Postman:
   - Importar la colección de Postman incluida en el repositorio.

5. O por CLI con curl:
   - Endpoint calculo: POST /calculate
      ```
      curl
      ```

   - Endpoint historial: GET /history
      ```
      curl
      ```