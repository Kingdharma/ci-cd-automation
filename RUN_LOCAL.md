# How to Run on Localhost

## Quick Start Options

### Option 1: Run with H2 Database (Easiest - No Database Setup Required)

This uses an in-memory H2 database, perfect for quick testing:

```bash
cd /Users/muthumani/springboot-production-app
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

The application will start on: **http://localhost:8080**

### Option 2: Run with PostgreSQL (Production-like)

**Step 1: Start PostgreSQL**

Using Docker:
```bash
docker run -d --name postgres \
  -e POSTGRES_DB=production_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:15-alpine
```

Or if you have PostgreSQL installed locally, make sure it's running and create the database:
```sql
CREATE DATABASE production_db;
```

**Step 2: Run the Application**

```bash
cd /Users/muthumani/springboot-production-app
mvn spring-boot:run
```

**Note:** For the first run with PostgreSQL, you may need to change `ddl-auto` to `update`:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.jpa.hibernate.ddl-auto=update"
```

### Option 3: Run the JAR File Directly

**Step 1: Build the JAR (if not already built)**
```bash
cd /Users/muthumani/springboot-production-app
mvn clean package
```

**Step 2: Run with H2 (dev profile)**
```bash
java -jar target/springboot-production-app-1.0.0.jar --spring.profiles.active=dev
```

**Step 3: Run with PostgreSQL**
```bash
java -jar target/springboot-production-app-1.0.0.jar
```

### Option 4: Using Docker Compose (Recommended for Production-like Setup)

```bash
cd /Users/muthumani/springboot-production-app
docker-compose up -d
```

This starts both PostgreSQL and the Spring Boot application.

## Verify the Application is Running

Once started, you can verify it's running by:

1. **Health Check:**
```bash
curl http://localhost:8080/api/v1/health
```

2. **Actuator Health:**
```bash
curl http://localhost:8080/actuator/health
```

3. **Open in Browser:**
- Health: http://localhost:8080/api/v1/health
- Actuator: http://localhost:8080/actuator/health

## Test the API

### Create a User
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

## Stop the Application

- If running with Maven: Press `Ctrl+C` in the terminal
- If running as JAR: Press `Ctrl+C` in the terminal
- If running with Docker Compose: `docker-compose down`

## Troubleshooting

### Port 8080 Already in Use

Change the port:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

Or set environment variable:
```bash
export PORT=8081
mvn spring-boot:run
```

### Database Connection Issues

Make sure PostgreSQL is running and accessible:
```bash
# Check if PostgreSQL is running
docker ps | grep postgres

# Or check local PostgreSQL
psql -U postgres -h localhost -c "SELECT version();"
```

### Application Won't Start

Check the logs:
```bash
# View application logs
tail -f logs/application.log

# Or check console output
```

