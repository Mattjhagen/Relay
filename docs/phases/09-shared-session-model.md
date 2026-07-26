Phase 09 — Shared Session Model & Cross-Device Synchronization

Status: Planned (Long-Term)
Priority: High
Estimated Duration: 5–8 development sessions

⸻

Objective

Establish Relay’s shared session architecture.

This phase introduces a unified session model that allows Relay Mobile, the Relay Agent, and the future Relay Studio desktop application to view and interact with the same long-running development session.

The goal is not cloud synchronization. The goal is shared awareness of a live workspace.

Users should be able to start working on one device and continue from another without losing context.

⸻

Vision

Relay should treat development sessions as first-class objects.

Instead of reconnecting to a terminal, devices reconnect to an intelligent workspace that contains:

* Active repositories
* Running tasks
* AI conversations
* Terminal sessions
* File changes
* Git state
* Diagnostics
* Notifications

Every Relay client should understand the same session.

⸻

User Experience Goals

Users should be able to:

* Open Relay Mobile while Relay Studio is running.
* See active workspaces.
* View running AI tasks.
* Read current terminal output.
* Continue reviewing work.
* Switch between devices.
* Rejoin the same development session.
* Observe updates in real time.

No manual synchronization should be required.

⸻

Technical Scope

Implement:

* Shared session model
* Session discovery
* Session registry
* Session synchronization
* Workspace identity
* Device presence
* Session locking
* State reconciliation
* Conflict handling
* Session lifecycle management

The Relay Agent becomes the authoritative source of truth for active sessions.

⸻

Architecture

Introduce a session subsystem.

core/
    session/
        SessionManager
        SessionRegistry
        SessionSyncEngine
        SessionLifecycleManager
        PresenceManager
        ConflictResolver
        WorkspaceRegistry
data/
    session/
feature/
    sessions/

The session layer must remain platform-independent so it can be shared conceptually across Android and macOS.

⸻

Session Model

Introduce:

RelaySession

Fields:

* Session ID
* Workspace ID
* Repository ID
* Server ID
* Connected devices
* Active users
* Active tasks
* Active terminals
* Active AI conversations
* Current Git branch
* Dirty state
* Last activity
* Created time
* Updated time
* Session version

Session IDs should remain stable until explicitly closed.

⸻

Workspace Model

Introduce:

Workspace

Fields:

* Workspace ID
* Display name
* Root path
* Active repositories
* Active terminals
* Active tasks
* Git status
* Diagnostics snapshot
* AI provider
* Preferred models
* Workspace settings

The Workspace becomes the central object users interact with.

⸻

Device Presence

Track:

* Mobile
* macOS
* Future desktop clients

Display:

* Connected
* Idle
* Offline
* Last seen
* Active workspace

Users should know where a session is currently active.

⸻

Session Discovery

Relay Mobile should automatically discover:

* Existing sessions
* Running workspaces
* Open terminals
* Active repositories
* Active AI tasks

Discovery should occur through the Relay Agent.

⸻

Synchronization Model

Synchronize:

* Session metadata
* Task state
* Diagnostics
* Notifications
* Repository state
* Terminal attachment metadata

Do not synchronize raw terminal scrollback or entire repositories.

Those remain on the server.

⸻

Conflict Resolution

Support:

* Multiple mobile devices
* Mobile + desktop
* Temporary disconnects
* Session resume
* Simultaneous updates

Conflicts should resolve deterministically.

The Relay Agent remains authoritative.

⸻

Session Lifecycle

States:

Created
↓
Initializing
↓
Active
↓
Idle
↓
Suspended
↓
Resuming
↓
Closing
↓
Closed

Represent lifecycle using typed state models.

⸻

Presence Indicators

Show:

* Device type
* Current user
* Active workspace
* Current task
* Last interaction
* Connection quality

Presence should update in near real time.

⸻

Session Locking

Allow optional exclusive locks for:

* Destructive Git operations
* Deployment
* Workspace migration
* Repository maintenance

Normal viewing should remain collaborative.

⸻

Offline Behavior

If Relay Mobile loses connectivity:

* Preserve cached session metadata.
* Display stale state clearly.
* Resume automatically upon reconnection.
* Merge updates using session versioning.

⸻

Security

Requirements:

* Session ownership validation.
* Device authentication.
* Replay protection.
* Session expiration.
* Revocation support.
* Secure synchronization over the existing authenticated Relay Agent channel.

No direct peer-to-peer communication between devices.

⸻

Performance Goals

Target:

* Session discovery under one second.
* Synchronization latency under 250 ms on LAN.
* Efficient incremental updates.
* Minimal network overhead.

Avoid repeatedly transmitting unchanged state.

⸻

Documentation

Create:

docs/SESSIONS.md
docs/WORKSPACES.md
docs/PRESENCE.md

Document:

* Session lifecycle
* Synchronization architecture
* Conflict resolution
* Presence model
* Workspace model

⸻

Explicit Non-Goals

Do not implement:

* Relay Studio editor
* Plugin system
* Cloud synchronization
* Multi-user collaboration
* Real-time shared editing
* Source control UI

These belong to future phases.

⸻

Acceptance Criteria

Phase 09 is complete when:

* Sessions are discoverable.
* Devices can rejoin existing sessions.
* Presence updates correctly.
* Workspace metadata synchronizes.
* Task state synchronizes.
* Conflict resolution functions.
* Offline recovery behaves predictably.
* Documentation reflects the architecture.

⸻

Verification

Run:

./gradlew assembleDebug
./gradlew lintDebug
./gradlew testDebugUnitTest

Manual verification:

* Connect mobile after desktop session begins.
* Disconnect and reconnect devices.
* Resume suspended sessions.
* Simulate conflicting updates.
* Verify presence indicators.
* Verify workspace synchronization.
* Confirm session version handling.

Run:

git status

⸻

Completion Report

Provide:

* Build status
* Session architecture summary
* Synchronization summary
* Presence implementation
* Conflict resolution strategy
* Security decisions
* Performance observations
* Files created
* Files modified
* Deferred work
* Recommended Phase 10 scope

⸻

Future Enhancements

Potential additions after Phase 09:

* Multi-user collaborative sessions
* Shared annotations
* Workspace snapshots
* Session recording and replay
* Cross-account workspace transfer
* Team presence
* Shared review sessions

These enhancements should build on the session architecture introduced here.

⸻

Design Notes

2026-07-15

Phase 09 introduces Relay’s most important architectural abstraction: the development session. Rather than thinking in terms of terminals or SSH connections, every Relay client should think in terms of shared workspaces with persistent state.

This abstraction allows Android, Relay Studio, and future Relay clients to participate in the same development workflow while remaining loosely coupled. By making the Relay Agent the authoritative source of session state, Relay avoids fragile client-to-client synchronization and preserves its local-first, privacy-focused design.
