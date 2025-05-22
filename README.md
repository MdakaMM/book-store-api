# Book Management System

A Spring Boot-based Book Management System that supports full CRUD operations with pagination, search, and ISBN-13 validation. It includes Dockerized deployment and Swagger API documentation.

## Features

- Add, edit, delete, and search books
- Pagination support
- ISBN-13 validation
- Swagger UI for API exploration
- Docker support for containerized deployment

---

## Technologies Used

- Java 17
- Spring Boot
- MySQL
- JPA/Hibernate
- Swagger (springdoc-openapi)
- Docker & Docker Compose
- Maven

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/MdakaMM/book-store-api.git
cd bookmanagement
```

---

## Run with Docker (Recommended)

### Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)

### Build and Start Containers

```bash
docker-compose up --build
```

### Access Application

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- MySQL DB: localhost:3307  
  (username: `root`, password: `Musa!1994`, database: `bookdb`)

---

## Run Locally (Without Docker)

### Prerequisites

- Java 17+
- Maven
- MySQL running locally (default port `3306`)

### Setup Local Database

```sql
CREATE DATABASE bookdb;
```

### Configure `application.properties`

Ensure your `application.properties` includes:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookdb
spring.datasource.username=YOUR USERNAME
spring.datasource.password=YOUR PASSWORD

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
```

### Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

---

### Optionally you can run the Jar file, if build is successfully

```
java -jar target/book-management-app.jar
```

## API Documentation

Once the application is running, access Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```
Or you can access the Postman Collection
```
https://documenter.getpostman.com/view/11684099/2sB2qahgd5
```
---

## Docker Tips

- Rebuild containers if code changes:

```bash
docker-compose down
docker-compose up --build
```

- Stop containers:

```bash
docker-compose down
```

## Author

Developed by Musa Mdaka
