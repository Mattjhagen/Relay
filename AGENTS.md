# AGENTS.md — Shaggoth / Relay / Archon Handoff

**Purpose:** resume work after a context reset. Everything here was *verified by command*, not assumed.
**Last verified:** 2026-07-27 ~02:05 UTC

---

## 1. Where things actually run

| Layer | Host | Detail |
|---|---|---|
| **Shaggoth inference** | **r510-1** (`matt@100.103.3.35`) | 16 cores, 39 GB RAM, Ubuntu 24.04, Python 3.12.3. **This is the AI compute.** |
| Shaggoth overflow (planned) | MacBook Air `100.67.199.109` | Also runs a Shaggoth instance on :8420 |
| Rust backend + React IDE | Fly.io `archon-ide-pacmac` (ord) | Orchestration only — **no model inference** |
| Anthropic / OpenAI / Gemini | Their clouds | Only when selected as provider |
| Ollama | r510-1 | `ollama.service` active |

**Fly reaches Shaggoth over Tailscale.** Fly is a proxy, not the compute.

### Live URLs
- `https://ide.relayapp.pro` — **the real, working Archon IDE** (Fly)
- `https://archon-ide-pacmac.fly.dev` — same app, direct
- `https://r510-1.tail3f2448.ts.net` — Shaggoth HTTPS (**tailnet-only**, real LE cert)
- `https://mattys-macbook-air.tail3f2448.ts.net` — MacBook Shaggoth (tailnet-only)
- `https://docs.relayapp.pro/ide` — ⚠️ **stale prototype, not the real IDE** (see §5)

---

## 2. Access

```bash
ssh matt@100.103.3.35        # r510-1, key auth works, NO passwordless sudo
```

- `~/.ssh/config` has `Host r510` → `192.168.0.169` (LAN, currently unreachable — use the Tailscale IP)
- **sudo on r510 requires a password.** Do not ask the user for it. Work around it (see §4).
- `loginctl show-user matt` → **`Linger=yes`** — user systemd units run 24/7 without login.
- `gh` CLI auth is **broken** (invalid keyring token). Git over SSH works.

---

## 3. Shaggoth state (verified)

### Two installs, same repo, different branches
| Path | Branch | Has curiosity? |
|---|---|---|
| `~/Shaggoth-a1` | `main` @ `e033ae8` | ❌ No |
| `~/shaggotha1` | `claude/ai-model-guardrails-platform-o6b50g` @ `1cf466f` | ✅ **Yes** |

**The running process is the manual one from `~/shaggotha1`** (has curiosity).
`shaggoth.service` (`/etc/systemd/system/`) points at `~/Shaggoth-a1` and is **crash-looping**
with `OSError: [Errno 98] Address already in use` — port 8420 held by the manual process.

> ⚠️ **Reboot hazard:** as-is, a reboot boots the OLD curiosity-less code. Fixed by §4 consolidation.

### Data location
`DATA_DIR = ROOT/data` where `ROOT = $SHAGGOTH_ROOT` or the install dir.
Live data → **`/home/matt/shaggotha1/data`**. `SHAGGOTH_ROOT` env var can override.

### Curiosity engine — already built
`shaggoth/curiosity/`: `engine.py`, `scheduler.py`, `search.py`, `topics.py`,
`wikipedia.py`, `freshness.py`. Also `scraper/`, `learner/`, `knowledge/`, `memory/`.

`serve()` auto-starts the scheduler (`scheduler.start()`), and every `/chat` message is fed to
`scheduler.record_message()` → **curiosity clues come from conversation already.**

### Verified status snapshot
```
/curiosity/scheduler  → {"enabled": true, "interval_minutes": 60,
                         "buffered_messages": 4, "thread_alive": true}
/scrape/stats         → 8 pages, 161,004 words, 34 seeds (7 done/27 pending), 0 errors
/curiosity/status     → total_episodes: 0, knowledge_entries: 1
```

**Why 0 episodes:** `ScheduleConfig.min_message_count = 5`, only 4 buffered, 60-min interval.
Not broken — never triggered. Tune in `shaggoth/curiosity/scheduler.py`.

### Working API endpoints
`/health` `/chat` `/chat/stream` `/history` `/facts` `/guardrails`
`/learn/start` `/learn/status` `/scrape/url` `/scrape/stats`
`/curiosity/research|status|history|ingest|ingest-wiki|freshness|refresh-stale|scheduler`
`/wiki?q=topic`

`POST /chat` body `{"message": "...", "session_id": "..."}` → response field **`reply`**
(the Rust backend depends on this exact shape).

---

## 4. IN FLIGHT — finish these first

### 4a. Knowledge seeding (running in background)
```bash
ssh matt@100.103.3.35 'tail -3 ~/seed_knowledge.log; grep -c OK ~/seed_knowledge.log'
```
Script: `~/seed_knowledge.py` — 104 foundational Wikipedia topics (science, math, computing,
history, geography, society, health, engineering). ~7k words each, ~700k total.
Last seen **54/104, 0 failures**, KB 2.4 MB.

### 4b. Directory consolidation (DO ONLY AFTER 4a COMPLETES)
Seeding writes to `~/shaggotha1/data` — moving mid-write loses it.

```bash
# 1. confirm seeding done ("DONE:" line present)
ssh matt@100.103.3.35 'tail -1 ~/seed_knowledge.log'

# 2. consolidate  (no sudo needed)
mkdir -p ~/stash
mv ~/Shaggoth-a1 ~/stash/Shaggoth-a1.main-2026-07-27
mv ~/shaggotha1 ~/Shaggoth-a1
ln -s ~/Shaggoth-a1 ~/shaggotha1        # old path keeps working

# 3. hand the port to systemd (Restart=always grabs it within ~5s)
pkill -f "python3 -m shaggoth serve"
sleep 8
systemctl status shaggoth --no-pager | head -5
curl -s https://r510-1.tail3f2448.ts.net/health
```
**Why this matters:** `shaggoth.service` already points at `~/Shaggoth-a1`, so this makes the
service run the *correct* code and fixes reboot safety **without sudo**.

---

## 5. Pending work

| # | Task | Blocker |
|---|---|---|
| 1 | Consolidate dirs, systemd reboot-safety | Waiting on 4a |
| 2 | `docs.relayapp.pro/ide` + site chat bubble | See below |
| 3 | Shaggoth dashboard tabs | — |
| 4 | 24/7 continuous learning tuning | — |
| 5 | CPU-overflow failover to 2nd node | — |
| 6 | PWA on relayapp.pro + notifications | **Cloudflare tunnel creds** |
| 7 | News/social/Reddit scraping | — |
| 8 | Health monitoring → `r510-command-center` repo | — |

### §5.2 detail — docs site
Source = **`archon-ios` repo, `Docs` branch** (worktree already at `/tmp/docs-wt`).
- `docs/ide.html` + `docs/js/ide.js` — static prototype, hardcodes
  `"AI responses require a backend connection. This is a frontend prototype."` → should redirect to
  `https://ide.relayapp.pro`. There's a `_redirects` file (Cloudflare Pages) — cleanest lever.
- `docs/js/chat-widget.js` — canned FAQ bot. Hardcoded stale models (GPT-4o, Claude 3.5,
  Gemini 1.5, Ollama). Makes **zero** network calls.
  ⚠️ All Fly `/api/*` routes require Supabase auth, so the widget needs a **public unauthenticated
  endpoint**. Recommendation: route it to Shaggoth only (self-hosted = no API credit exposure),
  rate-limited, with graceful fallback to canned answers when Shaggoth is offline.

### §5.6 detail — public domain
**Tailscale Serve is tailnet-only.** For `shaggoth.relayapp.pro` you need a **named Cloudflare
tunnel**. The `cloudflared` on r510 is an *ephemeral quick-tunnel* (`--url http://localhost:3847`)
→ random `trycloudflare.com`, unrelated. Needs `cloudflared tunnel login` (interactive) or a
dashboard token. Cloudflare MCP available here has only D1/KV/R2/Workers — **no DNS or tunnel tools.**
PWA assets already exist: `shaggoth/static/{manifest.json,sw.js,pwa-192.png,pwa-512.png,favicon.svg}`
plus `generate-pwa-icons.py` (regenerate icons from favicon per user request).

**Open question for user:** public endpoint gated with `SHAGGOTH_API_KEY`, or open?

---

## 6. Fly.io

App `archon-ide-pacmac`, region `ord`, v48+. `flyctl` authed as `matty@purepulse.one`.

Secrets set: `ANTHROPIC_API_KEY` `OPENAI_API_KEY` `OPENROUTER_API_KEY` `SUPABASE_*`
`OPENCODE_*` `TAILSCALE_AUTHKEY` `ALLOWED_ORIGINS` `SITES_BASE_DOMAIN` `SHAGGOTH_BASE_URL`

`SHAGGOTH_BASE_URL` currently `http://100.67.199.109:8420` (**MacBook**).
→ **TODO: repoint to r510** once consolidated: `http://100.103.3.35:8420`.

> ⚠️ `start.sh` runs tailscaled with **`--accept-dns=false`** → the container **cannot resolve
> MagicDNS names**. Use raw Tailscale IPs in `SHAGGOTH_BASE_URL`, *not* `*.ts.net` hostnames,
> unless you also add an `/etc/hosts` entry in `start.sh`.

Certs: `ide.relayapp.pro` ✅ Issued · `app.relayapp.pro` ✅ Issued (added this session) ·
`vibecodes.space` ✅

Provider code (both already support `auto` + `shaggoth`):
- `archon-ide/backend/src/ai.rs` → `list_providers()`, `chat()`, `chat_shaggoth()`, `chat_auto()`
- `archon-ide/backend/src/agent/model_adapter.rs` → `call_shaggoth()` (line ~354), `call_auto()` (~417)
- Auto priority: Anthropic → OpenAI → Gemini → Ollama → Shaggoth

Deploy: `cd archon-ide && flyctl deploy -a archon-ide-pacmac` (Rust build, several minutes).

---

## 7. Storage (r510) — not a constraint

```
/dev/mapper/ubuntu--vg-ubuntu--lv   98G   37G used   57G free   40%
sda 837.3G   sdb 2T          ← ~2.7 TB unallocated
```
Shaggoth data 2.4 MB and growing slowly. Expanding the LVM into free space **requires sudo**
(`lvextend` + `resize2fs`) — optional, hand the commands to the user.

---

## 8. Hard-won gotchas

1. **`docs.relayapp.pro/ide` is NOT the real IDE.** The real one is `ide.relayapp.pro`. Don't
   debug the prototype thinking it's the app.
2. **Never send secrets/passwords through the conversation.** Give the user commands to run.
3. Fly container can't resolve MagicDNS (`--accept-dns=false`) — use Tailscale IPs.
4. Tailscale IPs are **stable across reboots** (assigned at node registration). `100.103.3.35` = r510-1,
   `100.67.199.109` = MacBook Air.
5. `tailscale serve` works **without sudo** on r510; needs root on many other setups.
6. Tailscale **Serve** = tailnet-only. **Funnel** = public but only on `*.ts.net`.
7. Shaggoth `/chat` returns **`reply`** (not `response`) — the Rust adapter depends on it.
8. Don't `git checkout` in `~/shaggotha1` while the service runs from it.
9. Heredoc + f-string + `\"` = SyntaxError. Use single quotes inside f-strings.
10. `r510` (100.105.154.91) is **offline**; the live box is **`r510-1` (100.103.3.35)**.

---

## 9. User's stated goals (verbatim intent)

- Shaggoth on **r510-1**, overflow to another compute when CPU is high
- **Always learning, 24/7**, using **curiosity clues from conversation** for context
- Needs **general knowledge** — it's a homegrown AI
- Scrape **social media + news (Reddit)**
- Make **all dashboard tabs work**, especially Learn
- **PWA** using the **favicon** as icon, with **notifications**, tied to **relayapp.pro**
- Storage meter must read so they know if more storage is needed
- Health surface in `github.com/Mattjhagen/r510-command-center`
- Keep this AGENTS.md current for context-loss recovery
