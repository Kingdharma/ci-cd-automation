# CI/CD Setup Guide

This guide will help you set up the enterprise-level CI/CD workflows for your Spring Boot application.

## Quick Start

### 1. Push Workflows to Repository

The workflows are already in `.github/workflows/`. Simply commit and push:

```bash
git add .github/
git commit -m "feat: add enterprise CI/CD workflows"
git push origin main
```

### 2. Configure Required Secrets

Go to your GitHub repository → Settings → Secrets and variables → Actions → New repository secret

#### Essential Secrets (Required for full functionality)

| Secret Name | Description | Where to Get It |
|------------|-------------|-----------------|
| `GITHUB_TOKEN` | Auto-provided by GitHub | Automatically available |
| `SONAR_TOKEN` | SonarQube/SonarCloud token | [SonarCloud](https://sonarcloud.io) or your SonarQube instance |
| `SNYK_TOKEN` | Snyk API token | [Snyk.io](https://snyk.io) |

#### Optional Secrets (For enhanced features)

| Secret Name | Description | When Needed |
|------------|-------------|-------------|
| `CODECOV_TOKEN` | Codecov token | For code coverage tracking |
| `AWS_ACCESS_KEY_ID` | AWS access key | For AWS deployments |
| `AWS_SECRET_ACCESS_KEY` | AWS secret key | For AWS deployments |
| `AWS_REGION` | AWS region | For AWS deployments |
| `DOCKER_REGISTRY` | Container registry URL | If not using GitHub Container Registry |
| `STAGING_DATABASE_URL` | Staging DB connection | For staging deployments |
| `PRODUCTION_DATABASE_URL` | Production DB connection | For production deployments |
| `SLACK_WEBHOOK_URL` | Slack webhook | For deployment notifications |

### 3. Configure Environments

Go to Settings → Environments → New environment

#### Create Staging Environment
- Name: `staging`
- Deployment branches: `develop`
- Protection rules (optional): Add required reviewers

#### Create Production Environment
- Name: `production`
- Deployment branches: `main`
- Protection rules (recommended): 
  - Required reviewers (add your team)
  - Wait timer (optional, e.g., 5 minutes)

### 4. Enable Dependabot

Dependabot is already configured via `.github/dependabot.yml`. It will:
- Check for updates weekly (Mondays at 3 AM UTC)
- Create PRs for dependency updates
- Group related updates together

## Workflow Overview

### Automatic Triggers

1. **CI Pipeline** (`ci.yml`)
   - Runs on every push to main/develop/feature branches
   - Runs on every pull request
   - Includes: build, test, security scan, Docker build

2. **CD Pipeline** (`cd.yml`)
   - Runs on push to `main` (production) or `develop` (staging)
   - Can be manually triggered with environment selection

3. **Security Scanning** (`security-scan.yml`)
   - Runs daily at 2 AM UTC
   - Runs on push to main/develop
   - Runs on pull requests

4. **Dependency Updates** (`dependency-update.yml`)
   - Runs weekly on Mondays at 3 AM UTC
   - Can be manually triggered

5. **Nightly Build** (`nightly-build.yml`)
   - Runs daily at 1 AM UTC
   - Full test suite and performance tests

6. **PR Checks** (`pr-checks.yml`)
   - Runs on every pull request
   - Fast feedback for code quality

7. **Release** (`release.yml`)
   - Runs when you push a tag (e.g., `v1.0.0`)
   - Can be manually triggered

## Testing the Setup

### Test CI Pipeline

1. Create a test branch:
```bash
git checkout -b test/ci-pipeline
```

2. Make a small change (e.g., update README)

3. Push and create a PR:
```bash
git add .
git commit -m "test: verify CI pipeline"
git push origin test/ci-pipeline
```

4. Go to GitHub → Actions tab to see the workflow running

### Test Manual Deployment

1. Go to Actions → Continuous Deployment
2. Click "Run workflow"
3. Select environment (staging or production)
4. Click "Run workflow"

### Test Release

1. Create and push a tag:
```bash
git tag v1.0.0
git push origin v1.0.0
```

2. This will trigger the release workflow automatically

## Customization

### Modify Deployment Targets

Edit `.github/workflows/cd.yml` and update the deployment steps:

```yaml
- name: Deploy to staging
  run: |
    # Add your deployment commands here
    # Examples:
    # kubectl apply -f k8s/staging/
    # helm upgrade --install app ./helm-chart
    # aws ecs update-service --cluster staging --service app
```

### Adjust Test Configuration

Edit `.github/workflows/ci.yml` to modify test execution:

```yaml
- name: Run unit tests
  run: mvn test -B
  env:
    TEST_PROFILE: integration
```

### Configure Notifications

Update Slack webhook in workflows or add other notification methods:

```yaml
- name: Notify deployment status
  uses: 8398a7/action-slack@v3
  with:
    webhook_url: ${{ secrets.SLACK_WEBHOOK_URL }}
```

## Monitoring

### View Workflow Runs

1. Go to GitHub → Actions tab
2. Click on a workflow to see its runs
3. Click on a run to see detailed logs

### View Artifacts

1. Go to a workflow run
2. Scroll to "Artifacts" section
3. Download test reports, build artifacts, etc.

### View Security Alerts

1. Go to Security → Code scanning alerts
2. Review vulnerabilities found by CodeQL, Trivy, etc.

## Troubleshooting

### Workflow Not Running

- Check if workflows are in `.github/workflows/` directory
- Verify YAML syntax is correct
- Check branch protection rules

### Build Failures

- Review workflow logs
- Check Maven dependencies
- Verify Java version compatibility
- Review test failures

### Deployment Failures

- Verify environment secrets are set
- Check deployment target connectivity
- Review health check endpoints
- Check environment protection rules

### Security Scan Issues

- Verify security tool tokens are set
- Check scan configuration
- Review vulnerability reports

## Best Practices

1. **Keep Workflows Updated**
   - Regularly update action versions
   - Review and optimize workflow performance

2. **Monitor Build Times**
   - Use caching effectively
   - Parallelize jobs where possible
   - Remove unnecessary steps

3. **Security**
   - Review security scan results regularly
   - Keep dependencies updated
   - Rotate secrets periodically

4. **Documentation**
   - Document custom deployment steps
   - Keep workflow documentation updated
   - Document environment-specific configurations

## Support

For issues or questions:
1. Check workflow logs in GitHub Actions
2. Review workflow documentation in `.github/workflows/README.md`
3. Check individual workflow files for inline comments

## Next Steps

1. ✅ Configure secrets
2. ✅ Set up environments
3. ✅ Test CI pipeline
4. ✅ Customize deployment scripts
5. ✅ Set up monitoring and alerts
6. ✅ Train team on workflow usage

---

**Note**: These workflows are production-ready but may require customization based on your specific deployment infrastructure (Kubernetes, AWS, Azure, etc.).

