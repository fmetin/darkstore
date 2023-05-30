# Darkstore

Darkstore is simple warehouse management system. Darkstore has two microservices which are depot and transfer. Each service has a separate database. It uses H2-DB as database technology.


## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com/)

## Running the application locally


- Step 1:

```shell
mvn clean install
```
- Step 2:

```shell
docker-compose up
```

- Note: If you want to run it locally without Docker, you must first install redis and rabbitmq. Also, since both services are set to port 8080 by default, you will need to change the port of a service. But if you proceed with docker, it will be enough to run the two commands above.

## Depot Service
This microservice undertakes warehouse operations, product operations, stock operations. This service includes rabbitmq, redis integration. It produces messages for Rabbitmq. It uses redis for distributed lock operations.
- Service URL: [Depot Service](http://localhost:8081) (http://localhost:8081)
- H2-Console URL: [H2-Console](http://localhost:8081/h2-console) (http://localhost:8081/h2-console)
- Swagger UI URL: [Swagger UI](http://localhost:8081/swagger-ui.html) (http://localhost:8081/swagger-ui.html)
### H2 Console Login Infos:
- JDBC URL:	jdbc:h2:mem:depotdb
- username: fmetin

## Transfer Service
This microservice undertakes transfer operations. This service includes rabbitmq, redis integration. It consumes messages from Rabbitmq. It uses redis for distributed lock operations. Also this service includes Depot Service integration to update stocks.
- Service URL: [Transfer Service](http://localhost:8082) (http://localhost:8082)
- H2-Console URL: [H2-Console](http://localhost:8082/h2-console) (http://localhost:8082/h2-console)
- Swagger UI URL: [Swagger UI](http://localhost:8082/swagger-ui.html) (http://localhost:8082/swagger-ui.html)
### H2 Console Login Infos:
- JDBC URL:	jdbc:h2:mem:transferdb
- username: fmetin

### RabbitMq Management Console:
- Url: http://localhost:15672
- Username: guest
- Password: guest

#### NOTE: There are postman collections in docs package. There are some example requests to consume depot and transfer service.

## Tech Stack
- Java 17
- Hibernate
- Spring Boot
- H2 database
- Lombok
- Redis
- RabbitMQ
- Swagger UI
- Mapstruct
- Docker

