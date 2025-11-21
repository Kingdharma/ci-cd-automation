# How to Push to GitHub

## Current Status
✅ Git repository initialized (local only)
✅ Git configured for this project only (dharmaraja2001@gmail.com)
✅ All files committed
✅ Remote repository added: https://github.com/Kingdharma/ci-cd-automation.git

## Authentication Required

To push to GitHub, you need to authenticate. Choose one method:

### Method 1: Personal Access Token (Recommended)

1. **Create a Personal Access Token:**
   - Go to: https://github.com/settings/tokens
   - Click "Generate new token" → "Generate new token (classic)"
   - Name it: "ci-cd-automation" or any name you prefer
   - Select scope: **repo** (full control of private repositories)
   - Click "Generate token"
   - **Copy the token immediately** (you won't see it again!)

2. **Push using the token:**
   ```bash
   cd /Users/muthumani/springboot-production-app
   git push -u origin main
   ```
   - Username: `Kingdharma` (or your GitHub username)
   - Password: Paste your personal access token

### Method 2: Update Remote URL with Token

```bash
cd /Users/muthumani/springboot-production-app
git remote set-url origin https://YOUR_TOKEN@github.com/Kingdharma/ci-cd-automation.git
git push -u origin main
```

Replace `YOUR_TOKEN` with your personal access token.

### Method 3: Use SSH (if you have SSH keys configured)

```bash
cd /Users/muthumani/springboot-production-app
git remote set-url origin git@github.com:Kingdharma/ci-cd-automation.git
git push -u origin main
```

### Method 4: GitHub CLI (if installed)

```bash
gh auth login
git push -u origin main
```

## Verify Push

After pushing, check your repository:
https://github.com/Kingdharma/ci-cd-automation

## Future Pushes

After the first push, you can simply use:
```bash
git add .
git commit -m "Your commit message"
git push
```

## Note

- Git is configured **only for this project** (not globally)
- Email: dharmaraja2001@gmail.com
- Name: Kingdharma
- These settings won't affect your other projects

