# API REST - Cálculo con Porcentaje Dinámico

### Tabla de contenido
- **[Descripción](#descripción)**
- **[Instrucciones de despliegue local](#instrucciones-de-despliegue-local)**
- **[Configuraciones application.properties de API REST](#configuraciones-applicationproperties-de-api-rest)**
   - [Configuración General](#configuración-general)
   - [Configuración de Serialización JSON](#configuración-de-serialización-json)
   - [Configuración de Compresión de Respuesta](#configuración-de-compresión-de-respuesta)
   - [Ruta Base de los Controladores REST](#ruta-base-de-los-controladores-rest)
   - [Configuración de Clientes REST](#configuración-de-clientes-rest)
   - [Configuración de Cache con Redis](#configuración-de-cache-con-redis)
   - [Configuración de Rate Limit](#configuración-de-rate-limit)
   - [Configuración de Logs de Llamadas a la API](#configuración-de-logs-de-llamadas-a-la-api)
   - [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
   - [Configuración en Ambiente de Desarrollo](#configuración-en-ambiente-de-desarrollo)
- **[Bitacora desiciones técnicas](#bitacora-desiciones-técnicas)**

# Descripción
Este proyecto es una API REST desarrollada en Spring Boot utilizando Java 21. 
Su objetivo es proporcionar una funcionalidad para calcular la suma de dos números
y aplicar un porcentaje dinámico obtenido de un servicio externo, además de 
implementar caché, reintentos ante fallos, historial de llamadas, control de 
tasas de consumo (Rate Limiting) y manejo adecuado de errores.

# Instrucciones de despliegue local

1. Clonar el repositorio:
```sh
git clone https://github.com/danielmorganse/api-rest-challenge.git
cd rest-api-challenge
```

2. Ejecutar con Docker:
- Para utilizar la imagen desde docker hub, comente la linea **build** y descomente la linea de **image** del archivo [docker-compose.yml](rest-api-challenge/docker-compose.yml)
```yaml
    image: devdams/rest-api-challenge:latest
    # build: ./rest-api-challenge
```

- Ejecutar:
```sh
docker compose up --build
```
Nota: La instrucción levantará el API REST, un servicio mock que retorna un porcentaje, un caché Redis y una BD PostgreSQL.

3. Acceder a la API:
   - Swagger UI: http://localhost:8082/swagger-ui/index.html
   - Endpoint calculo: POST /calculate
   - Endpoint historial: GET /history

4. Probar los Endpoints mediante cualquiera de las alternativas:
   - **Swagger UI**: http://localhost:8082/swagger-ui/index.html, en la sección "Try it out" de cada operación.
   - **Postman**: Importar la colección de Postman incluida en el repositorio ([postman_collection.json](rest-api-challenge/postman_collection.json)).
   - **CLI**: Mediante la ejecución de instruciones CLI con curl.
      - Endpoint calculo: POST /calculate
         ```sh
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
         ```sh
         curl -X 'GET' \
         'http://localhost:8082/history?page=0&size=10' \
         -H 'accept: application/json'
         ```

---

# Configuraciones application.properties de API REST

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

---

# Bitacora desiciones técnicas

1. **Mock Service con OutOfCoffee/Imposter**: Para evitar dependencias de servicios externos en el desarrollo y pruebas, se ha utilizado OutOfCoffee/Imposter como servicio mock de porcentaje. Esto permite simular respuestas predefinidas sin necesidad de desplegar entornos adicionales, agilizando el desarrollo y las pruebas automatizadas.

2. **OpenFeign para Cliente REST y Reintentos**: Se ha elegido OpenFeign para consumir servicios REST debido a su integración nativa con Spring Boot y su capacidad para definir clientes declarativos de manera sencilla. Además, se ha configurado lógica de reintentos para manejar fallos en la comunicación con el servicio mock, mejorando la resiliencia del sistema y evitando errores por fallas temporales en la red.

3. **Caché Externo con Redis**: Se ha implementado una capa de caché fuera de la API utilizando Redis para mejorar la escalabilidad y garantizar un comportamiento consistente entre las instancias del API REST.

4. **Uso de Spring Data para la Integración de Caché con Redis**: Se ha utilizado Spring Data Redis para gestionar la integración de la API con Redis de manera eficiente. Esto permite aprovechar las capacidades de Spring para almacenar y recuperar datos en caché con mínima configuración, mejorando la mantenibilidad y reduciendo la necesidad de código adicional para la gestión de caché.

5. **Rate Limit dentro de la Aplicación**: Se ha decidido implementar el control de tasa (Rate Limit) dentro de la API en lugar de utilizar un middleware de integración porque la API no tiene fines productivos. Dado que su propósito no requiere un control de acceso externo, la implementación dentro de la aplicación simplifica la configuración y evita una capa adicional de gestión sin aportar un beneficio significativo. En caso de que el desarrollo tuviera fines productivos, se recomendaría delegar esta responsabilidad a un middleware de integración como API Gateway de IBM o WSO2 para una mejor gestión y escalabilidad.

6. **Implementación de Rate Limit con Bucket4J**: Se ha optado por Bucket4J para la implementación del Rate Limit dentro de la aplicación debido a su flexibilidad y capacidad para definir estrategias de limitación basadas en tokens. Esto permite controlar la cantidad de solicitudes por unidad de tiempo, protegiendo la API de abusos y asegurando una distribución equitativa de los recursos sin depender de un middleware externo. No obstante, esta decisión se ha tomado exclusivamente porque el desarrollo no tiene fines productivos. Para un entorno productivo, se recomienda utilizar un middleware de integración como API Gateway de IBM o WSO2 para delegar esta responsabilidad de manera más eficiente.

7. **Uso de JPA y Pool de Conexión HikariCP**: Para la conexión a la BD PostgreSQL se ha optado por utilizar JPA junto con HikariCP como pool de conexiones debido a su equilibrio entre tiempo de codificación, rendimiento y capacidad de recuperación. HikariCP es conocido por su eficiencia en la gestión de conexiones, reduciendo la sobrecarga y mejorando el tiempo de respuesta de la API. Además, en caso de fallos en la base de datos, HikariCP proporciona mecanismos de recuperación automática, minimizando el impacto en la disponibilidad del servicio.