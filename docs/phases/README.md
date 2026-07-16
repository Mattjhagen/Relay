Create the project roadmap as separate phase documents so they can be added to the Git repository one at a time.

Do not implement any new code from these phases yet.

Create the following files under:

docs/phases/

Use this naming convention:

01-foundation-and-ui.md
02-secure-configuration-and-key-management.md
03-ssh-connection-layer.md
04-terminal-emulation-and-tmux.md
05-server-diagnostics.md
06-reliability-and-production-hardening.md
07-structured-remote-agent-protocol.md
08-mobile-task-review-and-approvals.md
09-shared-session-model.md
10-relay-studio-macos-foundation.md
11-native-editor-and-workspaces.md
12-ai-chat-and-inline-assistance.md
13-agent-execution-engine.md
14-git-diffs-approvals-and-orchestration.md
15-extensions-model-providers-and-hardening.md

For now, write Phase 1 only:

Phase 1: Foundation and UI

The document must include:

* Phase objective
* User-facing outcome
* Technical scope
* Architecture decisions
* Directory structure
* Design system requirements
* Navigation requirements
* Screen requirements
* Accessibility requirements
* Documentation deliverables
* Explicit non-goals
* Acceptance criteria
* Verification commands
* Completion report format

Use the following approved Phase 1 scope:

Objective

Create the native Android foundation for Relay using Kotlin and Jetpack Compose.

The result should be a polished, buildable application shell with Home, Terminal, Server, and Settings destinations.

Technology

* Kotlin
* Jetpack Compose
* Material 3
* MVVM
* Coroutines
* StateFlow
* Hilt
* Gradle Kotlin DSL
* Version Catalogs
* Single app module
* Feature-based package structure

Do not configure Room, SSH libraries, terminal libraries, or networking libraries in this phase unless they are already required by generated project constraints.

Application Identity

Application ID:

com.pacmacmobile.relay

Display name:

Relay

Minimum SDK:

Prefer API 26 unless the installed toolchain requires a justified alternative.

Target the newest stable Android SDK installed locally.

Package Structure

com.pacmacmobile.relay
├── RelayApplication.kt
├── MainActivity.kt
├── core
│   ├── design
│   │   ├── color
│   │   ├── component
│   │   ├── shape
│   │   ├── theme
│   │   └── typography
│   ├── navigation
│   └── util
├── feature
│   ├── home
│   ├── terminal
│   ├── server
│   └── settings
└── data
    ├── model
    └── repository

The data layer may contain interfaces and demo models only. It must not contain real persistence or networking implementations.

Design Direction

Relay should use:

* True black primary background
* Layered charcoal surfaces
* White primary typography
* Muted gray secondary typography
* Subtle borders
* Restrained gradients
* Large consistent corner radii
* Generous spacing
* Smooth understated motion
* One restrained status accent where needed
* Android system sans-serif font
* Android system monospace font for terminal-like text

Avoid:

* Material default purple
* Hacker green
* Heavy shadows
* Excessive glow
* Loud gradients
* Stock-template appearance

Screens

Home

Include demo-only UI for:

* Relay branding
* Server card
* Server name
* Connection state
* Last connection time
* Ping
* Prominent Connect button
* Small recent-session section

The Connect button must not simulate a real SSH connection.

Terminal

Include:

* Terminal header
* Demo terminal surface
* Monospace sample content
* Shortcut row with ESC, CTRL, TAB, arrows, slash, dash, and pipe
* Clear indication that the terminal engine is not implemented yet

Server

Include demo dashboard cards for:

* CPU
* Memory
* Disk
* Uptime
* Temperature
* Ollama
* OpenCode
* tmux sessions

All values must be clearly demo data.

Settings

Include non-functional preview sections for:

* Connection
* Security
* Appearance
* Reconnect behavior

Do not collect or store real credentials, keys, hosts, or usernames.

Navigation

Destinations:

* Home
* Terminal
* Server
* Settings

Home is the initial destination.

Use a centralized route abstraction.

Use bottom navigation on phones.

Structure navigation so a tablet navigation rail can be introduced later without rewriting the destination screens.

Reusable Components

Create:

* RelayCard
* RelayButton
* RelayTopAppBar
* Status indicator
* Metric card
* Section header
* Shortcut key button

Include Compose previews for major components.

Accessibility

Include:

* Content descriptions
* Semantic roles
* Minimum reasonable touch targets
* Readable contrast
* Text scaling support
* No color-only status communication

Documentation

Create or update:

* README.md
* ARCHITECTURE.md
* docs/PHASES.md
* docs/SECURITY.md
* docs/VISION.md
* docs/phases/01-foundation-and-ui.md

Non-Goals

Do not implement:

* SSH
* Password authentication
* SSH key import
* Android Keystore
* Biometrics
* Room
* Terminal emulation
* tmux integration
* Server diagnostics commands
* Background services
* Push notifications
* macOS code
* Relay Agent
* AI features
* Git operations

Acceptance Criteria

Phase 1 is complete only when:

1. The project opens successfully in Android Studio.
2. The application ID is correct.
3. The app builds successfully.
4. All four destinations are navigable.
5. The UI is responsive across common phone widths.
6. The design does not resemble stock Material defaults.
7. Placeholder data is clearly marked as demo data.
8. No secrets or personal server details exist in source control.
9. Compose previews compile.
10. Documentation accurately reflects the implemented architecture.
11. Lint passes.
12. Unit tests pass.

Verification

Run:

./gradlew assembleDebug
./gradlew lintDebug
./gradlew testDebugUnitTest

Also run:

git status

Report:

* Build result
* Lint result
* Unit-test result
* Application ID
* Minimum SDK
* Target SDK
* Kotlin version
* Compose BOM version
* AGP version
* Gradle version
* Dependency summary
* Directory tree
* APK path
* Untracked files
* Ignored generated files
* Deviations from the phase document

Do not create the remaining phase files yet.

At the end, report that docs/phases/01-foundation-and-ui.md is ready to commit.
