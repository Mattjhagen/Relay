Phase 01 — Foundation & UI

Status: Approved
Priority: Critical
Estimated Duration: 1–2 development sessions

⸻

Objective

Establish the production-quality foundation for Relay.

This phase creates the Android project, architecture, design system, navigation, reusable UI components, documentation, and placeholder screens. It intentionally contains no real networking, SSH, terminal, or persistence logic.

When complete, Relay should already feel like a premium application despite being powered entirely by demo data.

⸻

User Experience Goals

By the end of this phase the user should be able to:

* Launch Relay
* Navigate between all major destinations
* Experience the visual identity of the application
* Understand the purpose of each section
* See what Relay will eventually become without any fake functionality

The application should feel polished enough that it resembles a finished product rather than a prototype.

⸻

Technical Scope

Implement only:

* Kotlin project setup
* Jetpack Compose
* Material 3
* MVVM architecture
* StateFlow
* Coroutines
* Hilt
* Version Catalogs
* Edge-to-edge rendering
* Feature-based package organization
* Design system
* Navigation
* Placeholder UI
* Documentation

⸻

Application Identity

Application Name

Relay

Package Name

com.pacmacmobile.relay

⸻

Minimum Requirements

Minimum SDK

26

Target SDK

Latest stable SDK installed locally.

⸻

Architecture

Use a single Gradle app module.

Future modularization should remain possible without restructuring the package hierarchy.

com.pacmacmobile.relay
│
├── RelayApplication.kt
├── MainActivity.kt
│
├── core
│   ├── design
│   │   ├── color
│   │   ├── component
│   │   ├── shape
│   │   ├── theme
│   │   └── typography
│   │
│   ├── navigation
│   ├── util
│   └── preview
│
├── feature
│   ├── home
│   ├── terminal
│   ├── server
│   └── settings
│
├── data
│   ├── model
│   └── repository
│
└── di

No Android UI code should appear inside the data layer.

⸻

Design Language

Relay should establish a recognizable identity.

Colors

Background

True Black

Surfaces

Charcoal

Typography

White
Soft Gray

Status colors should be subtle.

Avoid loud colors.

⸻

Typography

Use Android system typography.

Use monospace only where terminal content will eventually appear.

No bundled font files during Phase 01.

⸻

Shapes

Large rounded corners

Consistent spacing

Soft borders

Minimal elevation

⸻

Motion

Animations should be:

* subtle
* responsive
* fast
* intentional

Avoid flashy transitions.

⸻

Navigation

Destinations

* Home
* Terminal
* Server
* Settings

Home is the start destination.

Phone layout:

Bottom Navigation

Future tablet layout:

Navigation Rail

Routes must be centralized.

No hardcoded route strings throughout the application.

⸻

Home Screen

Create a premium dashboard containing demo information.

Include:

* Relay logo/title
* Server connection card
* Demo online/offline status
* Demo ping
* Last connected time
* Primary Connect button
* Recent sessions section

The Connect button must not perform any action other than demonstrating navigation and interaction states.

⸻

Terminal Screen

Create a realistic placeholder.

Include:

* Toolbar
* Terminal surface
* Demo monospace output
* Shortcut bar

Shortcut row

ESC
CTRL
TAB
↑
↓
←
→
/
-
|

Clearly indicate:

Terminal engine coming in Phase 04

⸻

Server Screen

Create a dashboard composed of reusable metric cards.

Metrics

* CPU
* Memory
* Disk
* Temperature
* Uptime
* Ollama
* OpenCode
* tmux Sessions

All values must be marked as demo data.

⸻

Settings Screen

Sections

Connection

Security

Appearance

Behavior

Do not collect:

* passwords
* keys
* usernames
* server addresses

Only demonstrate the future layout.

⸻

Reusable Components

Create reusable Compose components.

Minimum components:

* RelayCard
* RelayButton
* RelayTopAppBar
* StatusIndicator
* MetricCard
* SectionHeader
* ShortcutKeyChip
* PlaceholderPanel

Every reusable component should include Compose previews.

⸻

Accessibility

Support:

* TalkBack
* Dynamic text sizing
* Large touch targets
* Semantic roles
* Content descriptions
* High contrast

Never rely solely on color.

⸻

Demo Data

Create dedicated preview/demo models.

Do not scatter fake values throughout screens.

Example

DemoServer
DemoMetrics
DemoSession

These should live under:

core.preview

⸻

Documentation

Create or update:

README.md
ARCHITECTURE.md
docs/VISION.md
docs/SECURITY.md
docs/PHASES.md
docs/phases/01-foundation-and-ui.md

⸻

Explicit Non-Goals

Do not implement:

SSH

Networking

Room

Android Keystore

Biometrics

Terminal emulator

tmux

Server polling

Background services

Push notifications

Relay Agent

Git

AI

File browser

Mac support

Anything from later phases

⸻

Code Quality

Requirements

* Kotlin style guide
* Compose best practices
* No duplicated UI
* Small composables
* Stable state
* Clear package names
* Documentation on public APIs where appropriate
* No TODO comments without associated issue references or phase notes

⸻

Acceptance Criteria

Phase 01 is complete when:

* Android Studio opens the project successfully
* Project builds successfully
* Four destinations exist
* Navigation functions correctly
* UI follows Relay branding
* Components are reusable
* Compose previews compile
* Demo models are centralized
* No networking exists
* No persistence exists
* No secrets exist
* Documentation matches implementation

⸻

Verification

Run

./gradlew assembleDebug
./gradlew lintDebug
./gradlew testDebugUnitTest
git status

⸻

Completion Report

At completion provide:

Build

PASS / FAIL

Lint

PASS / FAIL

Tests

PASS / FAIL

Application ID

Minimum SDK

Target SDK

Kotlin Version

Compose BOM Version

AGP Version

Gradle Version

Directory Tree

APK Location

Files Created

Files Modified

Deferred Work

Technical Debt

Known Issues

Recommended Next Phase

⸻

Design Notes

2026-07-15

Initial architectural foundation established.

The project intentionally prioritizes long-term maintainability over rapid feature implementation.

Networking, SSH, terminal emulation, persistence, and server management are deferred to dedicated future phases to keep the initial codebase clean and focused.

Future additions should update this section whenever a significant architectural decision is made so the reasoning behind the evolution of Relay is preserved.

2026-07-15 (Remediation)

Completed Phase 1 remediation to strictly enforce the authoritative scope. Reusable components (e.g. `RelayCard`, `ShortcutKeyChip`) were extracted and annotated with accessible semantics and broad `@Preview` coverage. MVVM was documented accurately as stateless placeholders for Phase 1. Demo data was isolated under `core.preview`. Adaptive navigation logic was introduced to intelligently swap between bottom navigation and a navigation rail. A dedicated `./gradlew` wrapper was established.
