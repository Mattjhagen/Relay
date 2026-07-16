Phase 07 — Relay Agent & Structured Remote Protocol

Status: Planned (Long-Term)
Priority: High
Estimated Duration: 4–8 development sessions

⸻

Objective

Introduce the Relay Agent, a lightweight server-side companion that transforms Relay from a terminal-based remote client into a structured remote development platform.

Instead of parsing shell output for every feature, Relay will communicate with the Relay Agent using a secure, versioned protocol while still falling back to SSH whenever necessary.

SSH remains the transport and trust anchor. The Relay Agent becomes an intelligent service running behind that secure connection.

⸻

Vision

The Relay Agent is not a replacement for SSH.

It is a trusted companion process responsible for exposing structured APIs, events, metadata, and development workflows that are difficult or inefficient to obtain through shell commands alone.

Relay Mobile and the future Relay Studio should interact with the Agent through shared domain models rather than raw shell output.

⸻

User Experience Goals

Users should be able to:

* Automatically detect whether Relay Agent is installed.
* Install or update the Agent over SSH.
* View Agent health and version.
* Use structured APIs instead of fragile command parsing.
* Seamlessly fall back to SSH when the Agent is unavailable.
* Continue using Relay even if the Agent is stopped.

The Agent should enhance Relay, never become a hard dependency.

⸻

Technical Scope

Implement:

* Relay Agent server
* Agent lifecycle management
* Version negotiation
* Capability discovery
* Structured RPC protocol
* Event streaming
* Authentication handshake
* Health checks
* Automatic installation and updates
* Backward compatibility layer

Do not replace SSH.

⸻

High-Level Architecture

                Relay Mobile
                     │
              SSH Secure Tunnel
                     │
         ┌───────────┴───────────┐
         │                       │
         │  Relay Agent          │
         │                       │
         ├───────────────────────┤
         │ Repository Service    │
         │ Session Service       │
         │ OpenCode Service      │
         │ Ollama Service        │
         │ Diagnostics Service   │
         │ File Service          │
         │ Event Service         │
         │ Task Service          │
         └───────────┬───────────┘
                     │
             Linux Operating System

⸻

Agent Responsibilities

The Relay Agent should expose structured information for:

* Server metadata
* OpenCode sessions
* tmux sessions
* Running tasks
* Git repositories
* Diagnostics
* Logs
* Files
* Workspace metadata
* Notifications
* Capabilities

The Agent should never require root access.

⸻

Protocol Design

Design a versioned protocol.

Requirements:

* JSON serialization
* Version negotiation
* Capability negotiation
* Typed requests
* Typed responses
* Structured errors
* Event subscriptions
* Streaming support
* Forward compatibility

Avoid tightly coupling clients to implementation details.

⸻

Capability Discovery

Example capabilities:

terminal
git
diagnostics
notifications
repositories
tasks
files
ollama
opencode

Clients should adapt dynamically based on advertised capabilities.

⸻

Authentication

The Agent should trust only authenticated SSH sessions.

Do not expose:

* HTTP endpoints
* Public ports
* Anonymous access

The Agent should inherit SSH authentication.

⸻

Installation

Relay should be capable of:

* Detecting missing Agent
* Uploading binaries
* Verifying versions
* Performing upgrades
* Restarting Agent
* Rolling back failed updates

Installation should require explicit user approval.

⸻

Agent Lifecycle

States:

Not Installed
↓
Installing
↓
Stopped
↓
Starting
↓
Running
↓
Updating
↓
Restarting
↓
Failed

Expose lifecycle events to the UI.

⸻

Event Streaming

Support subscriptions for:

* Task progress
* Diagnostics updates
* OpenCode status
* Repository changes
* tmux events
* AI model events
* Notifications
* File changes

The protocol should support incremental updates rather than repeated polling.

⸻

Services

The Agent should be internally organized into services.

AgentCore
SessionService
RepositoryService
TaskService
DiagnosticsService
OpenCodeService
OllamaService
FileService
NotificationService
CapabilityService

Each service should expose a stable interface.

⸻

Domain Models

Introduce shared models for:

* AgentInfo
* Capability
* Task
* Session
* Workspace
* Repository
* Notification
* FileMetadata
* DiagnosticSnapshot

These models should later be reused by Relay Studio.

⸻

Compatibility

The Agent must support:

* Older mobile clients
* Newer mobile clients where practical
* Capability-based degradation
* Protocol version negotiation

Breaking changes require protocol version increments.

⸻

Security

Requirements:

* SSH transport only
* No plaintext secrets
* Signed releases
* Integrity verification
* Principle of least privilege
* Sandboxed execution where practical

The Agent should never expose arbitrary command execution without explicit authorization.

⸻

Performance Goals

Target:

* Agent startup <1 second
* Low idle memory usage
* Event latency <100 ms on LAN
* Efficient streaming
* Minimal CPU overhead

⸻

Documentation

Create:

docs/AGENT.md
docs/PROTOCOL.md
docs/CAPABILITIES.md

Document:

* Protocol versioning
* Capability negotiation
* Service architecture
* Installation process
* Upgrade strategy
* Security model

⸻

Explicit Non-Goals

Do not implement:

* Relay Studio
* Plugin system
* AI orchestration
* Git UI
* Background cloud services
* Public APIs
* Remote internet exposure

⸻

Acceptance Criteria

Phase 07 is complete when:

* Relay Agent can be installed through Relay.
* Capability discovery functions.
* Structured requests succeed.
* Event subscriptions work.
* SSH fallback continues functioning.
* Version negotiation is implemented.
* Documentation reflects the protocol architecture.

⸻

Verification

Run:

./gradlew assembleDebug
./gradlew lintDebug
./gradlew testDebugUnitTest

Manual verification:

* Fresh Agent installation
* Agent update
* Version mismatch handling
* Capability negotiation
* SSH fallback
* Event streaming
* Recovery after Agent crash
* Recovery after server reboot

Run:

git status

⸻

Completion Report

Provide:

* Build status
* Agent architecture summary
* Protocol version
* Supported capabilities
* Installation workflow
* Security decisions
* Performance observations
* Files created
* Files modified
* Deferred work
* Recommended Phase 08 scope

⸻

Future Enhancements

Potential additions after Phase 07:

* Binary delta updates
* Plugin-hosted Agent services
* Secure remote diagnostics bundles
* Distributed Agent clusters
* Agent health analytics
* Remote policy enforcement

These should remain optional and preserve Relay’s local-first architecture.

⸻

Design Notes

2026-07-15

Phase 07 marks Relay’s transition from a sophisticated SSH client into a structured remote development platform. The Relay Agent exists to provide reliable, versioned interfaces for development workflows while preserving SSH as the underlying transport and trust boundary.

The protocol should be designed with long-term stability in mind, as it will eventually serve both Relay Mobile and Relay Studio. Every message, capability, and domain model introduced here should be considered part of Relay’s public protocol and evolved with careful attention to backward compatibility.
