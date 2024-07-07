# GitHub User Service

Spring boot application to download information about GitHub users.


## Features

- Download information about GitHub users
- Handling exceptions with friendly messages
- Automatic API documentation
- Docker containerization
  

## Użyte technologie

- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Spring Cloud OpenFeign**
- **JUnit 5**
- **Mockito**
- **Swagger/OpenAPI**
- **Docker**


## Features

- Fetching information about GitHub users
- Exception handling with user-friendly messages
- Automatic API documentation
- Containerization of the application using Docker


## Requirements

- Java 21
- Gradle
- Docker 


## Instructions to run

1. Clone repository:

    ```sh
    git clone https://github.com/yourusername/github-user-service.git
    cd github-user-service
    ```

2. Build project:

    ```sh
    ./gradlew build
    ```

3. Run application:

    ```sh
    ./gradlew bootRun
    ```

Application will be available at: `http://localhost:8080`.


## Docker

To run application on docker you have to run command 
from main directory

```sh
docker compose up
```

Application will be available at: `http://localhost:8080`.


## Tests

To run unit and integration tests execute the command below:

```sh
./gradlew test
```


## Swagger API documentation

To run swagger documentation, run application and go to: 

```
http://localhost:8080/swagger-ui/index.html
```