# Agent.md — Archon IDE / Relay / Shaggoth

AI agents reading this file: use it as a living record of decisions made, work completed,
and what's next. Update this file whenever you complete a meaningful task.

---

## Project Overview

| Repo | Purpose | Deployed At |
|------|---------|-------------|
| `Mattjhagen/Relay` | Main monorepo — website + IDE | GitHub Pages (relayapp.pro) |
| `archon-ide/` subdirectory | Rust backend + React IDE frontend | Fly.io |
| `Mattjhagen/archon-ios` | docs site + chat widget | Cloudflare Pages (docs.relayapp.pro) |
| `Mattjhagen/Shaggoth-a1` | Self-hosted Python AI (no external APIs) | localhost:8420 |
| `Mattjhagen/Relay` (Android) | Android remote dev client | Play Store (future) |

### Domain Map
- `relayapp.pro` → `index.html` (GitHub Pages) — marketing/gateway page
- `docs.relayapp.pro` → archon-ios repo (Cloudflare Pages) — docs + chat widget
- `docs.relayapp.pro/ide` → archon-ide React SPA (currently served from Cloudflare Pages)
- `app.relayapp.pro` → archon-ide Fly.io deployment (Rust backend + React SPA)

---

## AI Provider Architecture

The IDE supports two top-level modes selectable in the provider dropdown:

### Auto Mode
Smart routing across all configured providers. Priority order:
1. **Anthropic** (claude-haiku-4-5 for short, claude-sonnet-5 for long context)
2. **OpenAI** (gpt-5.6-terra for short, gpt-5.6-sol for long context)
3. **Google Gemini** (gemini-3.6-flash)
4. **Ollama** (local, llama3.2)
5. **Shaggoth** (local Python AI, always free)

Switching triggers: credit exhaustion, quota errors. Future: bandwidth, CPU load signals.

### Shaggoth Mode
Routes all requests to the self-hosted Shaggoth Python AI.
- Default URL: `http://localhost:8420`
- Override: `SHAGGOTH_BASE_URL` env var
- API: `POST /api/chat` `{"message":"...", "session_id":"archon-ide"}`
- Health check: `GET /health`
- Start: `python3 -m shaggoth serve --port 8420`

---

## Completed Work

### 2026-07-26 — Initial AI Provider Integration
**Agent**: Claude Sonnet 4.6

**What was done:**
- Added `shaggoth` and `auto` providers to backend `ai.rs`:
  - `list_providers()`: probes Shaggoth health, inserts Auto at top of list
  - `chat_shaggoth()`: HTTP adapter to Shaggoth REST API
  - `chat_auto()`: smart provider selection with credit-limit fallback
- Added `call_shaggoth()` and `call_auto()` to `agent/model_adapter.rs`
- Updated `types/index.ts`: added `'auto' | 'shaggoth'` to `ProviderId`
- Updated `persistence.ts`: changed default provider from `openai` to `auto`

**Files changed:**
- `archon-ide/backend/src/ai.rs`
- `archon-ide/backend/src/agent/model_adapter.rs`
- `archon-ide/frontend/src/types/index.ts`
- `archon-ide/frontend/src/lib/persistence.ts`

### 2026-08-02 — Chat bubble wired to Shaggoth
**Agent**: Claude Code

**What was done:**
- Replaced the CDN chat widget on `relayapp.pro` with one that lives in this
  repo and calls Shaggoth instead of returning canned strings.
- `assets/chat-widget.js` — talks to `https://ai.relayapp.pro`:
  `GET /health` (status dot, polled while open), `GET /greeting` (opener
  composed from what Shaggoth currently knows), `POST /chat/stream` for
  token-by-token replies with an automatic fall back to `POST /chat` when the
  stream never starts.
- Conversation and session id persist in `localStorage` (capped at 40
  messages), so the transcript survives page navigation. "Clear" wipes both,
  which also starts a fresh server-side session.
- Offline degradation: when Shaggoth is unreachable the widget answers from a
  short set of pointer answers and flips the status to "Offline — limited
  answers". Those answers name no models, prices, or versions — the previous
  widget's hardcoded model list is exactly what went stale.
- `research: false` on every request. Shaggoth feeds `/chat` messages to the
  curiosity scheduler, and this bubble sits on a public page; opt in with
  `RELAY_CHAT_CONFIG.research = true` if visitors should steer what it reads.
- Overridable via `window.RELAY_CHAT_CONFIG` (apiBase, apiKey, mode, stream,
  suggestions, title). `window.RelayChat.{open,close,toggle,ask}` lets page
  buttons drive the bubble.

**Files changed:**
- `assets/chat-widget.js` (new)
- `assets/chat-widget.css` (new)
- `index.html` — loads the local widget instead of `docs.relayapp.pro`
- `sw.js` — precache the widget, cache bumped to `relay-v2`

**Verified:** Playwright against a mock of `shaggoth/server.py` — 30 checks
covering streaming, the non-streaming fallback, greeting, persistence across
navigation, clear, 429 handling, backend-down fallback, Escape/focus, and the
mobile viewport. **Not yet verified against the live `ai.relayapp.pro`** — the
sandbox this ran in has no outbound access to it.

---

## Current Status

### IDE Backend (Fly.io)
- Code exists and compiles (Rust/actix-web)
- **NOT YET WIRED**: The frontend at `docs.relayapp.pro/ide` shows
  "AI responses require a backend connection. This is a frontend prototype."
- The frontend makes API calls to `/api/*` (relative URL) — needs the Fly.io
  backend deployed and the SPA served from the same origin, OR a CORS proxy

### What's needed to make the IDE fully functional:
1. Deploy the Rust backend to Fly.io with the `archon-ide/` configs
2. Set environment variables in Fly.io (see `archon-ide/backend/.env.example` or Fly secrets):
   - `ANTHROPIC_API_KEY`
   - `OPENAI_API_KEY`
   - `GEMINI_API_KEY` (optional)
   - `SUPABASE_URL` + `SUPABASE_SERVICE_ROLE_KEY` (for auth + cloud memory)
   - `SHAGGOTH_BASE_URL` (if Shaggoth runs on a remote machine)
3. Build and deploy the React frontend (`npm run build` → static assets served by backend)
4. Point `docs.relayapp.pro/ide` to the deployed app OR serve the SPA from Fly.io directly

### Chat Widget
- **relayapp.pro**: served from this repo — `assets/chat-widget.{js,css}`,
  backed by Shaggoth at `https://ai.relayapp.pro`. No CDN dependency.
- **docs.relayapp.pro**: still loads the old canned widget from the
  `archon-ios` repo. Copying these two files there (and dropping the
  `#chat-bubble-mount` div in) is all that is needed to bring it across.
- No Auto selector: the Fly `/api/*` routes need Supabase auth and spend
  metered credits, so an unauthenticated public widget stays on Shaggoth only.

---

## Planned Work

### Phase 1: Backend Deployment
- [ ] `fly deploy` from `archon-ide/` directory
- [ ] Set Fly.io secrets for all API keys
- [ ] Verify `/api/ai/providers` responds correctly
- [ ] Verify `/api/ai/chat` works end-to-end

### Phase 2: Frontend Wiring
- [ ] Confirm SPA is served from same origin as backend (no CORS issues)
- [ ] Test Auto mode selects correct provider
- [ ] Test Shaggoth mode when server is running locally

### Phase 3: Chat Widget Update
- [x] Widget rebuilt in the Relay repo and pointed at Shaggoth
- [ ] Verify against the live `ai.relayapp.pro` from a browser on the internet
- [ ] Port the same two files to `archon-ios` so docs.relayapp.pro matches

### Phase 4: Shaggoth API Alignment
- [x] Confirm Shaggoth REST API shape — routes are unprefixed at the tunnel:
      `POST /chat` `{message, session_id, mode?, research?}` →
      `{reply, source, flag, ...}`. The `/api/` prefix only exists on the
      `ai.vibecodes.space` Worker, which rewrites it away.
- [x] Conversation history — `session_id` per browser, persisted; Shaggoth
      keeps the server-side transcript against it
- [x] Streaming — `POST /chat/stream` is SSE (`{token}` … `{done, reply}`)

### Phase 5: Plugin System
- [ ] Design plugin manifest format
- [ ] Implement plugin loader in the IDE sidebar
- [ ] First plugins: GitHub, Netlify, Supabase (already have integration stubs)

---

## Architecture Decisions

**Why `auto` is at the top of the provider list, not in a separate toggle:**
User wanted it as a first-class choice alongside Shaggoth. Making it a provider
keeps the UI consistent and allows per-session switching.

**Why Shaggoth falls last in auto-priority:**
It's a developing local AI. Cloud providers generally produce better coding answers
today. Shaggoth is included so the IDE works even with zero API keys.

**Why the IDE isn't wired yet:**
The frontend was built as a prototype served from Cloudflare Pages (archon-ios repo).
The Rust backend lives in the Relay repo but hasn't been deployed to Fly.io yet.
The fix is a `fly deploy` — no code changes needed to connect them.
