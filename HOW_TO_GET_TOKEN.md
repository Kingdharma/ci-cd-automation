# How to Get GitHub Personal Access Token

## Step-by-Step Guide

### Step 1: Go to GitHub Settings
1. Open your web browser
2. Go to: **https://github.com**
3. Sign in with your account (if not already signed in)
4. Click on your **profile picture** (top right corner)
5. Click **"Settings"** from the dropdown menu

### Step 2: Navigate to Developer Settings
1. In the left sidebar, scroll down
2. Click **"Developer settings"** (at the bottom of the list)

### Step 3: Go to Personal Access Tokens
1. In the left sidebar, click **"Personal access tokens"**
2. Click **"Tokens (classic)"** (or just click it if it's already selected)

### Step 4: Generate New Token
1. Click the green button: **"Generate new token"**
2. Select **"Generate new token (classic)"** from the dropdown

### Step 5: Configure the Token
1. **Note:** Give it a descriptive name, for example:
   - `ci-cd-automation`
   - `Spring Boot Project`
   - `My Mac Mini`

2. **Expiration:** Choose how long the token should be valid:
   - `No expiration` (for convenience, but less secure)
   - `90 days` (recommended)
   - `30 days` (more secure)

3. **Select scopes:** Check the boxes for permissions you need:
   - ✅ **`repo`** - Full control of private repositories
     - This is the minimum you need to push code
     - It includes: repo:status, repo_deployment, public_repo, repo:invite, security_events

4. **Optional scopes** (you don't need these for basic push):
   - `workflow` - Update GitHub Action workflows
   - `write:packages` - Upload packages
   - `delete:packages` - Delete packages
   - etc.

### Step 6: Generate and Copy Token
1. Scroll down to the bottom
2. Click the green button: **"Generate token"**
3. **IMPORTANT:** GitHub will show you the token **ONCE ONLY**
4. The token will look like: `ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`
5. **Copy it immediately!** You won't be able to see it again
6. Save it somewhere safe (password manager, notes app, etc.)

### Step 7: Use the Token
Now you can use this token to push your code:

```bash
cd /Users/muthumani/springboot-production-app
git push -u origin main
```

When prompted:
- **Username:** `Kingdharma` (or your GitHub username)
- **Password:** Paste the token you just copied (NOT your GitHub password)

---

## Quick Link
**Direct link to create token:**
https://github.com/settings/tokens/new

---

## Visual Guide (What You'll See)

```
GitHub Settings Page:
┌─────────────────────────────────────┐
│ Profile Picture → Settings          │
├─────────────────────────────────────┤
│ Account settings                    │
│   Profile                           │
│   Account                           │
│   ...                               │
│   Developer settings  ← Click here │
│     Personal access tokens          │
│       Tokens (classic)              │
│         Generate new token          │
└─────────────────────────────────────┘
```

---

## Security Tips

1. **Don't share your token** - Treat it like a password
2. **Use descriptive names** - So you know which token is for what
3. **Set expiration dates** - Rotate tokens regularly
4. **Revoke unused tokens** - Delete tokens you're not using
5. **Use fine-grained tokens** - For better security (newer feature)

---

## Troubleshooting

**"Token not working"**
- Make sure you copied the entire token (starts with `ghp_`)
- Check if the token has expired
- Verify the `repo` scope is selected

**"Can't find Developer settings"**
- Make sure you're logged into GitHub
- Scroll down in the left sidebar
- It's at the very bottom of the settings menu

**"Token disappeared"**
- GitHub only shows tokens once
- You'll need to generate a new one
- Old tokens can't be viewed again

---

## Alternative: Use GitHub CLI

If you have GitHub CLI installed:
```bash
gh auth login
```

This will guide you through authentication interactively.

