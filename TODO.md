# TODO — Relay Platform Improvements

## High Priority
- [ ] Set up `ide.relayapp.pro` custom domain in Cloudflare to serve the web IDE directly
- [ ] Consolidate archon-ios docs + archon-ide into the Relay repo (single repo strategy)
- [ ] Make File/Edit/View/Help dropdown menus fully functional (new project, undo, toggle panels, keyboard shortcuts)
- [ ] Platform selector: switch project templates when changing target (iOS → Swift files, Android → Kotlin, etc.)
- [ ] IDE: real code generation from AI (currently mock responses only)
- [ ] IDE: export as ZIP actually packages the correct platform structure

## Medium Priority
- [ ] Add `_headers` file to Relay repo for Cloudflare Pages security headers (CSP, HSTS, X-Frame-Options)
- [ ] Chat widget: connect to real AI backend instead of hardcoded responses
- [ ] Chat widget: persist conversation history across page navigations
- [ ] PWA: add install prompt banner on relayapp.pro
- [ ] PWA: offline fallback page for relayapp.pro
- [ ] Sitemap: add relayapp.pro/ide.html once ide.relayapp.pro is set up
- [ ] Fix relayapp.pro docs links — some use `/docs/` path which needs Cloudflare redirect rules

## Low Priority
- [ ] IDE: syntax highlighting in the code editor (currently plain textarea)
- [ ] IDE: terminal commands for each platform (xcodebuild, gradle, npm, etc.)
- [ ] IDE: git integration (init, commit, status, diff)
- [ ] Learning center: track progress across sessions (currently localStorage only)
- [ ] Add analytics (privacy-respecting, e.g. Plausible or Umami)
- [ ] Relay Android app: connect to real SSH backend (Phase 2+)
- [ ] Relay Studio (macOS): begin Phase 10 foundation

## Repo Consolidation Plan
- [ ] Move `archon-ios/docs/` → `Relay/web/docs/`
- [ ] Move `archon-ide/` → `Relay/web/ide-app/` (or keep as separate deployment)
- [ ] Configure Cloudflare Pages to serve from `web/` directory
- [ ] Update all internal links to use relayapp.pro subdomains
- [ ] Archive or redirect old repos to Relay
