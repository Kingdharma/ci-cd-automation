# Render Deployment Guide

This application is configured to deploy exclusively on Render.

## Setup Instructions

### 1. Connect Your Repository to Render

1. Go to [Render Dashboard](https://dashboard.render.com)
2. Click "New +" and select "Web Service"
3. Connect your GitHub repository
4. Render will automatically detect the `render.yaml` configuration file

### 2. Automatic Deployment

Render will automatically:
- Deploy on every push to the `main` branch
- Build the application using Maven
- Start the application with the configured start command
- Connect to the PostgreSQL database defined in `render.yaml`

### 3. Environment Variables

The following environment variables are configured automatically:
- `DATABASE_URL` - PostgreSQL connection string (from database service)
- `DATABASE_USERNAME` - Database username (from database service)
- `DATABASE_PASSWORD` - Database password (from database service)
- `PORT` - Server port (default: 8080)
- `SPRING_PROFILES_ACTIVE` - Set to `production`
- `JAVA_VERSION` - Set to 8

### 4. Health Checks

The application includes a health check endpoint at `/api/v1/health` which Render uses to verify the service is running.

### 5. Database

A PostgreSQL database is automatically provisioned as defined in `render.yaml`. The database connection is automatically configured via environment variables.

## Manual Deployment

If you need to manually trigger a deployment:
1. Go to your service in Render Dashboard
2. Click "Manual Deploy"
3. Select the branch or commit to deploy

## Configuration

The deployment configuration is defined in `render.yaml`:
- **Runtime**: Java 8
- **Build Command**: `mvn clean package -DskipTests`
- **Start Command**: `java -jar target/springboot-production-app-*.jar`
- **Health Check**: `/api/v1/health`
- **Auto Deploy**: Enabled for `main` branch

## Notes

- All other deployment configurations (AWS, Docker registries, etc.) have been removed
- GitHub Actions workflows will still run CI/CD checks but will not deploy
- Render handles all deployments automatically from GitHub

