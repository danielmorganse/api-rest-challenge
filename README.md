# API REST - Cálculo con Porcentaje Dinámico

Este proyecto es una API REST desarrollada en Spring Boot utilizando Java 21. 
Su objetivo es proporcionar una funcionalidad para calcular la suma de dos números
y aplicar un porcentaje dinámico obtenido de un servicio externo, además de 
implementar caché, reintentos ante fallos, historial de llamadas, control de 
tasas de consumo (Rate Limiting) y manejo adecuado de errores.

## Instrucciones de Ejecución

1. Clonar el repositorio:
```
git clone https://github.com/danielmorganse/api-rest-challenge.git
cd rest-api-challenge
```

2. Ejecutar con Docker:
```
docker compose up --build
```

3. Acceder a la API:
   - Swagger UI: http://localhost:8082/swagger-ui/index.html
   - Endpoint calculo: POST /calculate
   - Endpoint historial: GET /history

4. Probar los Endpoints con Postman:
   - Importar la colección de Postman incluida en el repositorio ([postman_collection.json](postman_collection.json)).

5. O por CLI con curl:
   - Endpoint calculo: POST /calculate
       ```
       curl -X 'POST' \ 
       'http://localhost:8082/calculate' \
       -H 'accept: application/json' \
       -H 'Content-Type: application/json' \
       -d '{
       "num1": 5,
       "num2": 5
       }'
       ```

   - Endpoint historial: GET /history
       ```
       curl -X 'GET' \
       'http://localhost:8082/history?page=0&size=10' \
       -H 'accept: application/json'
       ```

---

# Configuraciones application.properties

A continuación se describe las configuraciones clave utilizadas en la API REST desarrollada con Spring Boot ([application.properties](rest-api-challenge/src/main/resources/application.properties)).

## Configuración General

```properties
spring.application.name=rest-api-challenge
server.port=${SERVER_PORT:8080}
logging.level.root=INFO
server.servlet.context-path=/
```

- `spring.application.name`: Nombre de la aplicación.
- `server.port`: Puerto en el que la aplicación se ejecuta. Se puede sobreescribir con la variable de entorno `SERVER_PORT`.
- `logging.level.root`: Nivel de log predeterminado (`INFO`).
- `server.servlet.context-path`: Ruta base del contexto de la aplicación.

## Configuración de Serialización JSON

```properties
spring.jackson.date-format=io.swagger.configuration.RFC3339DateFormat
spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS=false
```

- `spring.jackson.date-format`: Define el formato de fecha según RFC3339.
- `spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS`: Define que las fechas no se serialicen como timestamps.

## Configuración de Compresión de Respuesta

```properties
server.compression.enabled=true
server.compression.min-response-size=2048
server.compression.mime-types=text/html,text/xml,text/plain,text/css,application/json
```

- `server.compression.enabled`: Habilita la compresión de respuesta.
- `server.compression.min-response-size`: Tamaño mínimo de respuesta para aplicar compresión (2KB).
- `server.compression.mime-types`: Tipos de contenido que se comprimen.

## Ruta Base de los Controladores REST

```properties
api.base-path=/api/v1
```

- `api.base-path`: Define la ruta base para los endpoints REST.

## Configuración de Clientes REST

```properties
rest.clients.externalPercentageClient.url=${REST_CLIENT_EXTERNALPERCENTAGE_URL}
feign.client.config.externalPercentageClient.readTimeout=2000
feign.client.config.externalPercentageClient.connectTimeout=2000
feign.client.config.externalPercentageClient.retries=3
feign.client.config.externalPercentageClient.loggerLevel=full
```

- Configura un cliente REST externo con Feign, incluyendo la URL, tiempos de espera y nivel de logging.

## Configuración de Cache con Redis

```properties
spring.redis.host=${SPRING_REDIS_HOST}
spring.redis.port=${SPRING_REDIS_PORT}
spring.redis.password=${SPRING_REDIS_PASSWORD}
spring.cache.redis.time-to-live=${SPRING_REDIS_TTL}
```

- Define los parámetros de conexión a Redis y el TTL para la caché.

## Configuración de Rate Limit

```properties
ratelimit.capacity=3
ratelimit.minutes=1
ratelimit.enabled=true
ratelimit.allowed-patterns=${api.base-path}/calculate/**
```

- `ratelimit.capacity`: Número de solicitudes permitidas.
- `ratelimit.minutes`: Período de tiempo en minutos.
- `ratelimit.enabled`: Activa o desactiva el rate limit.
- `ratelimit.allowed-patterns`: Define qué rutas están sujetas al rate limit.

## Configuración de Logs de Llamadas a la API

```properties
apicalls.log.enabled=true
apicalls.log.allowed-endpoints=${api.base-path}/calculate
```

- Habilita la opción de registrar ciertas llamadas a la API en la base de datos.

## Configuración de la Base de Datos

```properties
spring.datasource.hikari.connectionTimeout=20000
spring.datasource.hikari.maximumPoolSize=5
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```

- Configura la conexión a la base de datos, incluyendo credenciales y parámetros de conexión.

### Configuración en Ambiente de Desarrollo

```properties
spring.jpa.hibernate.ddl-auto=create-drop
```

- `spring.jpa.hibernate.ddl-auto`: Define que el esquema se crea y elimina automáticamente en entornos de desarrollo.

