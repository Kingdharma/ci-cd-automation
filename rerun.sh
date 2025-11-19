#!/bin/bash

# Script to rerun the Spring Boot application

echo "=========================================="
echo "Spring Boot Application - Rerun Script"
echo "=========================================="

# Check if port 8080 is in use
if lsof -ti:8080 > /dev/null 2>&1; then
    echo "⚠️  Port 8080 is already in use. Stopping existing process..."
    kill $(lsof -ti:8080)
    sleep 2
    echo "✅ Stopped existing process"
fi

# Navigate to project directory
cd "$(dirname "$0")"

echo ""
echo "Choose run mode:"
echo "1) Dev mode (H2 database - no setup needed)"
echo "2) Production mode (PostgreSQL)"
echo "3) Run JAR file directly"
read -p "Enter choice [1-3] (default: 1): " choice

choice=${choice:-1}

case $choice in
    1)
        echo ""
        echo "🚀 Starting in DEV mode with H2 database..."
        mvn spring-boot:run -Dspring-boot.run.profiles=dev
        ;;
    2)
        echo ""
        echo "🚀 Starting in PRODUCTION mode with PostgreSQL..."
        echo "⚠️  Make sure PostgreSQL is running!"
        mvn spring-boot:run
        ;;
    3)
        echo ""
        echo "🚀 Running JAR file..."
        if [ ! -f "target/springboot-production-app-1.0.0.jar" ]; then
            echo "📦 Building JAR file first..."
            mvn clean package -DskipTests
        fi
        java -jar target/springboot-production-app-1.0.0.jar --spring.profiles.active=dev
        ;;
    *)
        echo "Invalid choice. Starting in DEV mode..."
        mvn spring-boot:run -Dspring-boot.run.profiles=dev
        ;;
esac

