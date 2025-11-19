# Spring Boot Production-Ready Application

A production-ready Spring Boot application demonstrating industry best practices including proper architecture, error handling, logging, testing, and Docker support.

## Features

- ✅ RESTful API with CRUD operations
- ✅ JPA/Hibernate with PostgreSQL
- ✅ Input validation
- ✅ Global exception handling
- ✅ Structured logging
- ✅ Health checks (Spring Boot Actuator)
- ✅ DTO pattern with MapStruct
- ✅ Service layer architecture
- ✅ Unit tests
- ✅ Docker & Docker Compose support
- ✅ Environment-based configuration
- ✅ Database connection pooling (HikariCP)
- ✅ JPA Auditing (created/updated timestamps)

## Tech Stack

- **Java 8** (compatible with Java 8-17)
- **Spring Boot 2.7.18** (LTS version)
- **PostgreSQL** (production) / **H2** (development)
- **Maven**
- **Lombok**
- **MapStruct**
- **Docker**

> **Note:** This project is configured for Java 8 compatibility. To use Spring Boot 3.x features, upgrade to Java 17+ and update the pom.xml accordingly.

## Project Structure

```
src/
├── main/
│   ├── java/com/example/springbootapp/
│   │   ├── controller/      # REST controllers
│   │   ├── service/         # Business logic
│   │   ├── repository/      # Data access layer
│   │   ├── entity/          # JPA entities
│   │   ├── dto/             # Data transfer objects
│   │   ├── mapper/          # MapStruct mappers
│   │   └── exception/       # Exception handling
│   └── resources/
│       ├── application.yml  # Main configuration
│       └── application-dev.yml  # Dev profile
└── test/                    # Unit tests
```

## Prerequisites

- Java 8 or higher (tested with Java 8, compatible up to Java 17)
- Maven 3.6+
- PostgreSQL 12+ (for production)
- Docker & Docker Compose (optional)

## Quick Start

### Option 1: Using Docker Compose (Recommended)

1. Clone the repository
2. Run the application with Docker Compose:
```bash
docker-compose up -d
```

This will start both PostgreSQL and the Spring Boot application.

### Option 2: Local Development

1. **Start PostgreSQL** (if not using Docker):
```bash
# Using Docker
docker run -d --name postgres \
  -e POSTGRES_DB=production_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:15-alpine
```

2. **Run the application**:
```bash
# Using Maven
mvn spring-boot:run

# Or build and run
mvn clean package
java -jar target/springboot-production-app-1.0.0.jar
```

3. **For development with H2 database**:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## API Endpoints

### Users API

- `POST /api/v1/users` - Create a new user
- `GET /api/v1/users` - Get all users
- `GET /api/v1/users/{id}` - Get user by ID
- `PUT /api/v1/users/{id}` - Update user
- `DELETE /api/v1/users/{id}` - Delete user
- `PATCH /api/v1/users/{id}/deactivate` - Deactivate user

### Health Check

- `GET /api/v1/health` - Application health check
- `GET /actuator/health` - Spring Boot Actuator health endpoint

## Example API Usage

### Create User
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "bio": "Software Developer"
  }'
```

### Get All Users
```bash
curl http://localhost:8080/api/v1/users
```

### Get User by ID
```bash
curl http://localhost:8080/api/v1/users/1
```

### Update User
```bash
curl -X PUT http://localhost:8080/api/v1/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Updated",
    "email": "john.updated@example.com",
    "bio": "Senior Software Developer"
  }'
```

## Configuration

### Environment Variables

- `DATABASE_URL` - Database connection URL (default: `jdbc:postgresql://localhost:5432/production_db`)
- `DATABASE_USERNAME` - Database username (default: `postgres`)
- `DATABASE_PASSWORD` - Database password (default: `postgres`)
- `PORT` - Application port (default: `8080`)
- `DDL_AUTO` - Hibernate DDL mode (default: `validate`)
- `LOG_LEVEL` - Logging level (default: `INFO`)

### Profiles

- **default**: Production profile with PostgreSQL
- **dev**: Development profile with H2 in-memory database

## Testing

Run tests with:
```bash
mvn test
```

## Building for Production

```bash
# Build JAR
mvn clean package

# Build Docker image
docker build -t springboot-production-app .

# Run Docker container
docker run -p 8080:8080 \
  -e DATABASE_URL=jdbc:postgresql://host.docker.internal:5432/production_db \
  -e DATABASE_USERNAME=postgres \
  -e DATABASE_PASSWORD=postgres \
  springboot-production-app
```

## Monitoring

The application includes Spring Boot Actuator with the following endpoints:

- `/actuator/health` - Health status
- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics
- `/actuator/prometheus` - Prometheus metrics

## Logging

Logs are written to:
- Console (stdout)
- File: `logs/application.log` (with rotation)

## Best Practices Implemented

1. **Layered Architecture**: Controller → Service → Repository
2. **DTO Pattern**: Separation of API contracts from entities
3. **Exception Handling**: Global exception handler with proper HTTP status codes
4. **Validation**: Input validation using Bean Validation
5. **Logging**: Structured logging with SLF4J
6. **Database**: Connection pooling, transactions, and proper indexing
7. **Security**: Prepared for Spring Security integration
8. **Testing**: Unit tests for service layer
9. **Documentation**: Comprehensive README
10. **Docker**: Multi-stage builds for optimized images

## Next Steps for Production

Consider adding:

- [ ] Spring Security for authentication/authorization
- [ ] API rate limiting
- [ ] Caching (Redis)
- [ ] Message queue (RabbitMQ/Kafka)
- [ ] API documentation (Swagger/OpenAPI)
- [ ] CI/CD pipeline
- [ ] Monitoring and alerting (Prometheus, Grafana)
- [ ] Distributed tracing (Zipkin, Jaeger)
- [ ] Database migrations (Flyway/Liquibase)

## License

This project is provided as-is for educational and production use.

