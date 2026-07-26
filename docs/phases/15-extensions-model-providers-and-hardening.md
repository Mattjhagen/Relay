Phase 15 — Platform Ecosystem, Extensions, Model Providers & Production Hardening

Status: Planned (Long-Term)
Priority: Critical
Estimated Duration: 12–24 development sessions

⸻

Objective

Transform Relay from a powerful application into a mature development platform.

This phase introduces the public extension architecture, provider SDK, automation framework, enterprise-grade hardening, release engineering, long-term compatibility guarantees, and ecosystem tooling required for Relay to become a sustainable platform.

Phase 15 represents Relay 1.0.

⸻

Vision

Relay should no longer be viewed as:

* an Android app
* a macOS IDE
* an SSH client
* an AI coding assistant

Relay should become a complete software engineering platform capable of adapting to future AI models, development tools, deployment systems, and operating systems without requiring major architectural rewrites.

⸻

Product Philosophy

Relay should be:

* Modular
* Extensible
* Provider-independent
* Offline-capable
* Privacy-first
* Open to community contributions
* Stable
* Secure
* Observable
* Long-lived

Everything introduced in this phase should reinforce those principles.

⸻

User Experience Goals

Users should be able to:

* Install extensions.
* Add new AI providers.
* Configure automation rules.
* Customize workflows.
* Import/export settings.
* Back up workspaces.
* Restore environments.
* Manage updates.
* Review security permissions.
* Monitor platform health.

Developers should be able to build on Relay rather than fork it.

⸻

Technical Scope

Implement:

* Extension SDK
* Public APIs
* Provider SDK
* Extension marketplace foundation (local-first)
* Automation engine
* Event bus
* Permission system
* Backup/restore
* Configuration export
* Update manager
* Diagnostics
* Telemetry abstraction (disabled by default)
* Performance benchmarking
* Compatibility testing
* Release engineering

⸻

Architecture

Introduce platform modules.

Sources/
Platform/
    ExtensionManager
    ProviderManager
    AutomationEngine
    EventBus
    Permissions
    BackupManager
    UpdateManager
    CompatibilityLayer
    Diagnostics
    Benchmarking
SDK/
    ExtensionSDK
    ProviderSDK
    AutomationSDK
Marketplace/

All platform APIs should be documented and versioned.

⸻

Extension System

Extensions should be capable of contributing:

* Commands
* Panels
* Views
* Tools
* AI capabilities
* Context providers
* Diagnostics
* Menus
* Themes
* Keyboard shortcuts

Extensions should never require unrestricted system access.

⸻

Extension Lifecycle

States:

Discovered
↓
Installed
↓
Enabled
↓
Running
↓
Disabled
↓
Updating
↓
Removed

Extensions should be isolated from one another whenever practical.

⸻

Provider SDK

Third-party providers should be able to integrate:

* Chat models
* Reasoning models
* Embedding models
* Image models
* Audio models
* Tool execution
* Streaming
* Authentication

Relay should never assume any specific provider.

⸻

Automation Engine

Allow users to define workflows.

Examples:

* On commit
* On branch creation
* On task completion
* On deployment
* On build failure
* On pull request

Automations should compose existing Relay capabilities rather than introducing hidden logic.

⸻

Event Bus

Introduce a versioned event system.

Example events:

* WorkspaceOpened
* RepositoryChanged
* TaskCompleted
* AgentStarted
* DeploymentFinished
* ReviewApproved
* ProviderChanged

Extensions should subscribe through the Event Bus rather than internal APIs.

⸻

Permission Model

Every extension should declare:

* Required capabilities
* Filesystem access
* Network access
* AI provider access
* Terminal access
* Git access
* Relay Agent access

Users should explicitly approve elevated permissions.

⸻

Backup & Restore

Support exporting:

* Settings
* Workspaces
* Shortcuts
* Themes
* Providers
* Automations

Secrets should remain encrypted and excluded by default unless explicitly requested.

⸻

Update System

Support:

* Stable channel
* Beta channel
* Nightly channel
* Manual updates
* Rollback
* Update verification

Users should retain control over update behavior.

⸻

Diagnostics

Provide:

* Health dashboard
* Performance metrics
* Extension diagnostics
* Provider diagnostics
* Crash summaries
* Compatibility warnings

No telemetry should be transmitted automatically.

⸻

Compatibility

Introduce formal compatibility guarantees.

Version:

* SDK
* Protocol
* Extensions
* Providers
* Relay Agent
* Relay Studio
* Relay Mobile

Breaking changes should require explicit version increments.

⸻

Enterprise Readiness

Prepare for:

* Organization policies
* Device management
* Audit exports
* Offline deployment
* Custom provider integrations

Enterprise features should remain optional.

⸻

Performance Goals

Target:

* Fast extension loading
* Stable provider switching
* Minimal startup impact
* Efficient event dispatch
* Low idle resource usage

Extensions should not compromise the core application.

⸻

Accessibility

Require all first-party extensions and SDK examples to support:

* VoiceOver
* TalkBack
* Keyboard-first navigation
* Dynamic text
* High contrast
* Reduced motion

Accessibility becomes part of the extension quality standard.

⸻

Documentation

Create:

* docs/SDK.md
* docs/EXTENSIONS.md
* docs/PROVIDERS.md
* docs/AUTOMATION.md
* docs/PERMISSIONS.md
* docs/COMPATIBILITY.md
* docs/BACKUP.md
* docs/RELEASE_PROCESS.md

Document:

* SDK architecture
* Extension lifecycle
* Event system
* Permission model
* Versioning strategy
* Release engineering
* Platform governance

⸻

Explicit Non-Goals

Do not implement:

* Mandatory cloud services
* Mandatory telemetry
* Vendor lock-in
* Closed extension ecosystem
* Proprietary provider APIs

Relay should remain open, provider-independent, and usable without internet connectivity whenever practical.

⸻

Acceptance Criteria

Phase 15 is complete when:

* Extensions can be installed and managed.
* Providers can be added through the SDK.
* Automation workflows function.
* Event Bus is stable.
* Permission model is enforced.
* Backup and restore operate correctly.
* Update system functions.
* Compatibility guarantees are documented.
* SDK documentation is complete.
* Relay is ready for a 1.0 release.

⸻

Verification

Verify:

* Extension installation
* Extension removal
* Provider registration
* Automation execution
* Permission prompts
* Backup/restore
* Version compatibility
* SDK examples
* Update workflow
* Release build integrity

Run project formatting, static analysis, full automated test suites, benchmark suites, and inspect repository cleanliness:

git status

⸻

Completion Report

Provide:

* Build status
* Platform architecture summary
* Extension SDK summary
* Provider SDK summary
* Automation engine summary
* Compatibility guarantees
* Security review
* Performance benchmarks
* Files created
* Files modified
* Deferred work
* Relay 1.0 readiness assessment

⸻

Future Enhancements

Potential post-1.0 initiatives:

* Linux desktop client
* Windows desktop client
* iPadOS companion
* Web dashboard
* Collaborative editing
* Team workspaces
* Enterprise administration console
* Distributed AI clusters
* Federated Relay Agents
* Self-hosted synchronization
* Community extension marketplace

These initiatives should build upon the stable platform introduced in Phase 15 rather than requiring architectural changes.

⸻

Design Notes

2026-07-15

Phase 15 completes Relay’s transformation from an application into a long-lived platform. By formalizing extension points, provider integrations, automation, permissions, versioning, and release engineering, Relay becomes resilient to changes in AI models, operating systems, and developer workflows.

Relay 1.0 should embody four enduring principles:

1. Local-first whenever practical.
2. Provider-independent by design.
3. Human-controlled for all impactful actions.
4. Extensible without sacrificing stability or security.

Every future feature should build upon these principles rather than bypassing them.
