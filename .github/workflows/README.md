# CI/CD Workflows Documentation

This directory contains enterprise-level CI/CD workflows for the Spring Boot Production Application.

## Overview

The CI/CD pipeline is designed to ensure code quality, security, and reliable deployments across multiple environments.

## Workflows

### 1. Continuous Integration (`ci.yml`)

**Triggers:**
- Push to `main`, `develop`, `feature/**`, `hotfix/**` branches
- Pull requests to `main` and `develop`
- Manual workflow dispatch

**Jobs:**
- **Code Quality Analysis**: Validates code style and runs static analysis
- **Build**: Compiles the application
- **Unit Tests**: Runs unit tests with coverage reporting
- **Integration Tests**: Runs integration tests against PostgreSQL
- **Security Scan**: Performs OWASP dependency check, Snyk, and Trivy scans
- **Docker Build**: Builds and pushes Docker images
- **SonarQube Analysis**: Code quality analysis (if configured)
- **CI Status Check**: Final validation of all checks

**Key Features:**
- Parallel job execution for faster feedback
- Artifact caching for improved performance
- Comprehensive test coverage reporting
- Multi-platform Docker builds (AMD64, ARM64)
- Security vulnerability scanning

### 2. Continuous Deployment (`cd.yml`)

**Triggers:**
- Push to `main` (production) or `develop` (staging)
- Manual workflow dispatch with environment selection
- Release events

**Jobs:**
- **Determine Environment**: Automatically determines deployment target
- **Deploy to Staging**: Deploys to staging environment
- **Deploy to Production**: Blue-green deployment to production
- **Rollback**: Automatic rollback on failure

**Key Features:**
- Environment-based deployments
- Health checks after deployment
- Smoke tests validation
- Blue-green deployment strategy
- Automatic rollback capability
- Slack notifications

### 3. Security Scanning (`security-scan.yml`)

**Triggers:**
- Daily schedule (2 AM UTC)
- Push to `main` or `develop`
- Pull requests
- Manual dispatch

**Jobs:**
- **Dependency Scan**: OWASP Dependency Check
- **Snyk Scan**: Snyk vulnerability scanning
- **Container Scan**: Trivy container image scanning
- **CodeQL Analysis**: GitHub's CodeQL security analysis
- **Secret Scanning**: Gitleaks and TruffleHog
- **SonarQube SAST**: Static Application Security Testing
- **Security Summary**: Aggregated security report

**Key Features:**
- Multiple security scanning tools
- SARIF upload to GitHub Security
- Daily automated scans
- Comprehensive vulnerability reporting

### 4. Release Management (`release.yml`)

**Triggers:**
- Tag push (e.g., `v1.0.0`)
- Manual workflow dispatch

**Jobs:**
- **Validate Release**: Version validation and pre-release checks
- **Build Release Artifacts**: Creates JAR files with checksums
- **Build Docker Image**: Multi-platform Docker image with version tags
- **Create GitHub Release**: Automated release notes and artifact upload
- **Deploy to Production**: Production deployment
- **Post-Release Tasks**: Documentation updates and notifications

**Key Features:**
- Semantic versioning validation
- Automated release notes generation
- Multi-version Docker tagging
- Release artifact management
- Production deployment automation

### 5. Dependency Updates (`dependency-update.yml`)

**Triggers:**
- Weekly schedule (Monday 3 AM UTC)
- Manual dispatch

**Jobs:**
- **Check Updates**: Identifies available dependency updates
- **Create Update PR**: Automatically creates PRs for updates
- **Security Alerts**: Monitors for security vulnerabilities

**Key Features:**
- Automated dependency update detection
- Selective update types (security, minor, major)
- Automatic PR creation
- Security vulnerability alerts

### 6. Pull Request Checks (`pr-checks.yml`)

**Triggers:**
- Pull request events (opened, synchronized, reopened)

**Jobs:**
- **Code Quality**: Style and formatting checks
- **Build Verification**: Compilation validation
- **Tests**: Unit test execution
- **Security**: Vulnerability scanning
- **PR Size Check**: Validates PR size
- **PR Status**: Final status check

**Key Features:**
- Fast feedback for PRs
- Test result comments
- PR size validation
- Security scanning

### 7. Nightly Build (`nightly-build.yml`)

**Triggers:**
- Daily schedule (1 AM UTC)
- Manual dispatch

**Jobs:**
- **Nightly Build**: Full build and test suite
- **Performance Tests**: Performance validation
- **Code Metrics**: Code quality metrics collection
- **Nightly Report**: Aggregated report generation

**Key Features:**
- Comprehensive nightly validation
- Performance testing
- Code metrics tracking
- Historical artifact retention

## Required Secrets

Configure the following secrets in your GitHub repository settings:

### CI/CD Secrets
- `SONAR_TOKEN`: SonarQube/SonarCloud authentication token
- `SONAR_HOST_URL`: SonarQube server URL (optional, defaults to SonarCloud)
- `CODECOV_TOKEN`: Codecov authentication token (optional)

### Security Secrets
- `SNYK_TOKEN`: Snyk authentication token
- `GITHUB_TOKEN`: Automatically provided by GitHub Actions

### Deployment Secrets
- `AWS_ACCESS_KEY_ID`: AWS access key for deployments
- `AWS_SECRET_ACCESS_KEY`: AWS secret key
- `AWS_REGION`: AWS region (defaults to us-east-1)
- `DOCKER_REGISTRY`: Container registry URL (defaults to ghcr.io)
- `STAGING_DATABASE_URL`: Staging database connection string
- `PRODUCTION_DATABASE_URL`: Production database connection string
- `PRODUCTION_BACKUP_ENABLED`: Enable production backups (true/false)

### Notification Secrets
- `SLACK_WEBHOOK_URL`: Slack webhook for notifications

## Environment Configuration

Configure environments in GitHub repository settings:

1. **Staging Environment**
   - Protection rules (optional)
   - Required reviewers (optional)
   - Deployment branches: `develop`

2. **Production Environment**
   - Protection rules (recommended)
   - Required reviewers (recommended)
   - Deployment branches: `main`
   - Deployment wait timer (optional)

## Best Practices

1. **Branch Strategy**
   - `main`: Production-ready code
   - `develop`: Integration branch
   - `feature/**`: Feature development
   - `hotfix/**`: Critical fixes

2. **Pull Requests**
   - All PRs must pass CI checks
   - Require code review
   - Keep PRs small and focused

3. **Releases**
   - Use semantic versioning (e.g., v1.0.0)
   - Tag releases for traceability
   - Generate release notes automatically

4. **Security**
   - Review security scan results regularly
   - Address critical vulnerabilities immediately
   - Keep dependencies up to date

5. **Monitoring**
   - Monitor deployment health
   - Track build metrics
   - Review nightly reports

## Customization

### Adding Custom Steps

Each workflow can be customized to fit your specific needs:

1. **Deployment Scripts**: Update deployment steps in `cd.yml`
2. **Test Configuration**: Modify test execution in `ci.yml`
3. **Security Tools**: Add additional security scanners in `security-scan.yml`
4. **Notification Channels**: Configure additional notification methods

### Environment-Specific Configuration

- Modify environment variables in workflow files
- Add environment-specific secrets
- Configure deployment targets per environment

## Troubleshooting

### Common Issues

1. **Build Failures**
   - Check Maven dependency resolution
   - Verify Java version compatibility
   - Review test failures

2. **Deployment Failures**
   - Verify environment secrets
   - Check deployment target connectivity
   - Review health check endpoints

3. **Security Scan Failures**
   - Review vulnerability reports
   - Update vulnerable dependencies
   - Configure scan thresholds

### Getting Help

- Review workflow logs in GitHub Actions
- Check artifact uploads for detailed reports
- Consult individual workflow documentation

## Maintenance

- **Weekly**: Review dependency updates
- **Monthly**: Review security scan results
- **Quarterly**: Update workflow actions to latest versions
- **As Needed**: Adjust thresholds and configurations

## Version History

- **v1.0.0**: Initial enterprise-level CI/CD workflows
  - Comprehensive CI pipeline
  - Multi-environment CD
  - Security scanning
  - Release management
  - Dependency updates
  - PR checks
  - Nightly builds

