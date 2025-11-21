# Quick Push to GitHub - Step by Step

## Current Status
✅ Repository switched to HTTPS
✅ Ready to push

## Steps to Push (Choose One Method)

### Method 1: Personal Access Token (Easiest - Recommended)

**Step 1: Create Token**
1. Open: https://github.com/settings/tokens
2. Click: **"Generate new token"** → **"Generate new token (classic)"**
3. Name: `ci-cd-automation` (or any name)
4. Expiration: Choose your preference (90 days, 1 year, etc.)
5. Select scope: Check **`repo`** (this gives full repository access)
6. Click: **"Generate token"** (scroll down if needed)
7. **IMPORTANT:** Copy the token immediately! It looks like: `ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`

**Step 2: Push**
```bash
cd /Users/muthumani/springboot-production-app
git push -u origin main
```

When prompted:
- **Username:** `Kingdharma` (or your GitHub username)
- **Password:** Paste your personal access token (NOT your GitHub password)

---

### Method 2: Use Token in URL (One-time setup)

If you have your token ready, you can embed it:

```bash
cd /Users/muthumani/springboot-production-app

# Replace YOUR_TOKEN with your actual token
git remote set-url origin https://YOUR_TOKEN@github.com/Kingdharma/ci-cd-automation.git

# Now push (no authentication prompt)
git push -u origin main
```

**Note:** The token will be stored in `.git/config`. For security, you might want to use credential helper instead.

---

### Method 3: Set Up SSH Keys (For Future Use)

If you prefer SSH for future pushes:

**Step 1: Generate SSH Key**
```bash
ssh-keygen -t ed25519 -C "dharmaraja2001@gmail.com"
# Press Enter to accept default location
# Press Enter twice for no passphrase (or set one)
```

**Step 2: Copy Public Key**
```bash
cat ~/.ssh/id_ed25519.pub
# Copy the entire output
```

**Step 3: Add to GitHub**
1. Go to: https://github.com/settings/keys
2. Click: **"New SSH key"**
3. Title: `Mac Mini` (or any name)
4. Key: Paste the copied key
5. Click: **"Add SSH key"**

**Step 4: Switch to SSH and Push**
```bash
cd /Users/muthumani/springboot-production-app
git remote set-url origin git@github.com:Kingdharma/ci-cd-automation.git
git push -u origin main
```

---

## After Successful Push

Verify your code is on GitHub:
https://github.com/Kingdharma/ci-cd-automation

## Troubleshooting

**"Repository not found" error:**
- Make sure the repository exists at: https://github.com/Kingdharma/ci-cd-automation
- Check you have access to the repository

**"Authentication failed" error:**
- Make sure you're using the token, not your password
- Verify the token has `repo` scope
- Check if the token has expired

**"Permission denied" error:**
- For HTTPS: Use personal access token
- For SSH: Make sure SSH key is added to GitHub

