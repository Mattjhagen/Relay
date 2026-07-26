Phase 10 — Relay Studio macOS Foundation

Status: Planned (Long-Term)
Priority: Critical
Estimated Duration: 6–10 development sessions

⸻

Objective

Begin development of Relay Studio, the native macOS desktop application.

Relay Studio is not intended to be another Electron editor or a web application wrapped in a desktop shell. It is a fully native development environment designed around AI-assisted software engineering, local performance, privacy, and remote development.

This phase establishes the architectural foundation, application shell, design system, navigation, workspace model, and infrastructure that future desktop features will build upon.

No production code editor should exist yet.

⸻

Product Vision

Relay Studio should eventually compete with:

* Cursor
* Antigravity
* Windsurf
* VS Code (AI workflows)

However, Relay Studio should feel unmistakably native to macOS.

Every design decision should favor:

* Native performance
* Low memory usage
* Fast startup
* Deep macOS integration
* Local-first architecture
* Provider independence

⸻

User Experience Goals

Users should be able to:

* Launch Relay Studio.
* Open the application shell.
* Browse available workspaces.
* View recent projects.
* Connect to Relay Agents.
* Switch between workspaces.
* Customize appearance.
* Explore the overall interface.

No production editing features should be implemented yet.

⸻

Technical Scope

Implement:

* Native macOS application
* SwiftUI application shell
* Navigation
* Design system
* Workspace manager
* Session integration
* Window management
* Preferences
* Command palette foundation
* Sidebar
* Status bar
* Inspector foundation
* Settings architecture

Do not implement:

* Code editor
* Git
* AI chat
* Agent execution
* Extensions

⸻

Technology Stack

Use:

* Swift
* SwiftUI
* Observation framework (or latest stable state management)
* Swift Concurrency
* Swift Package Manager
* App Sandbox where appropriate

Avoid:

* Electron
* React Native
* Flutter
* Qt
* WebView-based interfaces

Relay Studio should remain 100% native.

⸻

Architecture

Create a feature-oriented architecture.

RelayStudio/
Sources/
    App/
    DesignSystem/
    Navigation/
    Workspaces/
    Sessions/
    Sidebar/
    Inspector/
    Preferences/
    Models/
    Services/
    Platform/
    Utilities/

Keep UI and business logic separated.

⸻

Design System

Create a desktop-specific design language.

Characteristics:

* True dark mode
* Layered surfaces
* Native typography
* Native animations
* Large work areas
* Minimal chrome
* Dense information where appropriate
* Excellent keyboard support

Do not simply port the Android UI.

⸻

Window Layout

Foundation layout:

┌─────────────────────────────────────────────────────────┐
│ Toolbar                                                  │
├───────────────┬──────────────────────────┬──────────────┤
│ Sidebar       │ Main Workspace           │ Inspector    │
│               │                          │              │
│               │                          │              │
└───────────────┴──────────────────────────┴──────────────┘
│ Status Bar                                              │
└─────────────────────────────────────────────────────────┘

This layout should remain flexible.

⸻

Navigation

Prepare navigation for:

* Dashboard
* Workspaces
* Repositories
* Sessions
* AI
* Tasks
* Terminal
* Git
* Settings

Only Dashboard and Workspaces require placeholder implementations.

⸻

Workspace Manager

Implement the foundation for:

WorkspaceManager

Responsibilities:

* Recent workspaces
* Active workspace
* Workspace metadata
* Session attachment
* Window restoration

No repository indexing yet.

⸻

Session Integration

Connect to the shared session model introduced in Phase 09.

Display:

* Active Relay Agent
* Connected workspaces
* Active tasks
* Connection status

No editing yet.

⸻

Preferences

Implement architecture for:

* Appearance
* Keyboard shortcuts
* AI providers
* Relay Agent
* Updates
* Privacy

Most settings should remain placeholders.

⸻

Command Palette Foundation

Implement:

* Global shortcut
* Search architecture
* Command registration
* Placeholder actions

Actual command execution arrives in later phases.

⸻

Sidebar

Prepare sections for:

* Recent Workspaces
* Favorites
* Servers
* Tasks
* Sessions

Only navigation should function.

⸻

Status Bar

Display:

* Connected server
* Active workspace
* Agent status
* Current branch (placeholder)
* Notifications

⸻

Accessibility

Support:

* VoiceOver
* Dynamic type where appropriate
* Keyboard-first navigation
* Focus management
* High contrast
* Reduced motion

Accessibility should be designed from the beginning.

⸻

Performance Goals

Target:

* Startup under 2 seconds
* Low idle CPU usage
* Low memory footprint
* Smooth navigation
* Instant window restoration

Avoid unnecessary abstraction layers.

⸻

Documentation

Create:

docs/STUDIO.md
docs/MACOS_ARCHITECTURE.md
docs/WINDOW_LAYOUT.md

Document:

* Desktop architecture
* Navigation
* Workspace concepts
* Design language
* Future roadmap

⸻

Explicit Non-Goals

Do not implement:

* Text editor
* Git integration
* AI chat
* Inline completions
* Diff viewer
* Plugin system
* Terminal emulator
* Agent execution
* Code intelligence

Those belong to later phases.

⸻

Acceptance Criteria

Phase 10 is complete when:

* Relay Studio launches successfully.
* Window layout exists.
* Navigation functions.
* Workspace foundation is implemented.
* Session awareness exists.
* Command palette foundation exists.
* Preferences architecture exists.
* Documentation reflects the architecture.

⸻

Verification

Build the application.

Verify:

* Launch
* Window restoration
* Navigation
* Sidebar
* Status bar
* Appearance switching
* Accessibility
* Keyboard shortcuts
* Session display

Run source formatting and static analysis tools appropriate to the chosen toolchain.

Inspect project cleanliness:

git status

⸻

Completion Report

Provide:

* Build status
* Architecture summary
* Window architecture
* Workspace model
* Navigation summary
* Performance observations
* Files created
* Files modified
* Deferred work
* Recommended Phase 11 scope

⸻

Future Enhancements

Potential additions after Phase 10:

* Multi-window workspaces
* Window layouts
* Saved perspectives
* External display support
* Native macOS menu customization
* Dock integration
* Spotlight integration
* Handoff support
* Apple Intelligence integrations where appropriate

These enhancements should build upon the application shell created in this phase.

⸻

Design Notes

2026-07-15

Phase 10 marks the birth of Relay Studio. The emphasis is on creating an exceptionally strong native desktop foundation rather than immediately chasing feature parity with existing editors.

The application should embrace macOS conventions while introducing Relay’s own identity centered around workspaces, sessions, and AI-assisted development. By delaying implementation of the editor itself, the architecture remains flexible enough to support future innovations without being constrained by early design decisions.
