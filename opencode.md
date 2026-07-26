# OpenCode Memory — Relay Project

## Project Identity
- **Relay** is the unified AI development platform at `relayapp.pro`
- **Archon IDE** is the web IDE product (browser-based, multi-model AI coding)
- **Archon Mobile** is the iOS app (Swift/SwiftUI, App Store)
- **Relay Android** is the Android app (Kotlin/Compose, early stage)

## Domain Structure
| URL | Content | Source |
|---|---|---|
| `relayapp.pro` | Gateway homepage | Relay repo (GitHub Pages) |
| `docs.relayapp.pro` | Full documentation site | archon-docs Cloudflare Pages project |
| `app.relayapp.pro` | Archon IDE (React/Rust full app) | archon-ide repo (Fly.io) |
| `ide.relayapp.pro` | Web IDE (lightweight browser IDE) | docs.relayapp.pro/ide.html |
| `schmidt-construction.com` | Schmidt Construction | Separate repo, DO NOT TOUCH |
| `mattjhagen.xyz` | Portfolio | Separate repo |

## Repos
| Repo | Purpose | Status |
|---|---|---|
| `Mattjhagen/Relay` | Main platform repo (Android app + gateway) | Active, deploy to GitHub Pages |
| `Mattjhagen/archon-ios` | iOS app + docs site | Active, deploy docs to Cloudflare Pages |
| `Mattjhagen/archon-ide` | Full-stack IDE (Rust+React+iOS) | Active, deployed on Fly.io |
| `Mattjhagen/SchmidtConstruction` | Client project | DO NOT MODIFY |
| `Mattjhagen/SchmidtAdmin` | Admin panel | Separate |
| `Mattjhagen/SchmidtWalls` | Walls site | Separate |
| `Mattjhagen/r510-command-center` | IoT project | Separate |
| `Mattjhagen/mattjhagen-portfolio` | Personal portfolio | Separate |

## Deployment
- **Cloudflare Pages** project `archon-docs` serves `docs.relayapp.pro`
- Deploy command: `CLOUDFLARE_API_TOKEN=$CF_TOKEN npx wrangler pages deploy docs --project-name=archon-docs --commit-dirty=true` (token stored in env var `CF_TOKEN`)
- **GitHub Pages** serves `relayapp.pro` from Relay repo root
- **Fly.io** serves `app.relayapp.pro` from archon-ide repo

## Design System
- Accent: `#7c5cfc` (purple)
- Backgrounds: `#06060a` → `#0c0c14` → `#12121e`
- Fonts: Inter (UI) + JetBrains Mono (code)
- Dark theme throughout, glass/blur effects

## Key Decisions
- All internal links use absolute `https://relayapp.pro/` URLs (not relative)
- No `archon-docs.pages.dev` or `docs.relayapp.pro` references in user-facing code
- SchmidtConstruction is off-limits — never modify
- Chat widget (`chat-widget.js`) is shared across docs pages via CDN link
- PWA support added to both relayapp.pro and docs site

## User Preferences
- Wants everything consolidated into one repo (Relay)
- Wants all links to use relayapp.pro subdomains, not Cloudflare preview URLs
- Prefers clean, organized directory structure
- Values speed — push frequently
- Does not want emoji in code/comments unless explicitly asked
