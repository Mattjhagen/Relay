Phase 03 — SSH Connection Layer

Status: Planned
Priority: Critical
Estimated Duration: 3–5 development sessions

⸻

Objective

Implement Relay’s production-grade SSH engine.

By the end of this phase, Relay should be capable of establishing secure SSH connections to remote servers, authenticating with imported private keys, verifying host identities, managing connection state, and exposing a clean API for higher-level features.

This phase intentionally does not implement terminal emulation, tmux integration, or server dashboards.

Its sole purpose is to create a robust, reusable SSH foundation.

⸻

User Experience Goals

Users should be able to:

* Connect to a configured server.
* Disconnect manually.
* Reconnect after temporary network interruptions.
* View meaningful connection states.
* Trust new host fingerprints.
* Reject unknown or changed host keys.
* Select which server profile to connect to.
* See connection latency and session information.

No terminal interaction should occur yet.

⸻

Technical Scope

Implement:

* SSH client abstraction
* SSH session lifecycle
* Key-based authentication
* Host key verification
* Connection manager
* Session state management
* Keep-alive handling
* Automatic reconnection
* Connection timeout handling
* Structured logging
* Connection metrics

Do not implement:

* Shell interaction
* Terminal rendering
* tmux
* File transfer
* Git
* AI integration

⸻

Architecture

Introduce a dedicated SSH layer.

core/
    ssh/
        RelaySshClient
        RelaySession
        SessionManager
        HostKeyVerifier
        KeepAliveManager
        ConnectionMonitor
        SshLogger
        SshExceptions
data/
    repository/
        ConnectionRepository
feature/
    connection/

No UI should directly communicate with SSH APIs.

All interactions must pass through ViewModels and repositories.

⸻

Library Selection

Choose a mature SSH implementation that supports:

* OpenSSH compatibility
* Ed25519
* RSA
* ECDSA
* Modern key exchange algorithms
* Session reuse
* Channel abstraction
* Active maintenance

Document why the library was chosen and what alternatives were evaluated.

Hide all third-party APIs behind Relay interfaces so the implementation can be replaced later if necessary.

⸻

Connection Lifecycle

Define the complete lifecycle.

Idle
↓
Resolving
↓
Connecting
↓
Authenticating
↓
Host Verification
↓
Connected
↓
Disconnected
↓
Retrying
↓
Failed

Represent connection state as a sealed hierarchy rather than booleans.

⸻

Session Model

Create:

RelaySession

Include:

* Session ID
* Connected server
* Connected time
* Authentication method
* SSH version
* Remote banner
* Connection duration
* Ping
* State
* Last activity

The model should support future terminal attachment.

⸻

Host Verification

Implement strict host verification.

Requirements:

* Never automatically trust unknown hosts.
* Display fingerprints before acceptance.
* Persist accepted fingerprints.
* Reject changed fingerprints unless explicitly approved.
* Allow removal of trusted fingerprints.

Future phases should build upon this model rather than replacing it.

⸻

Authentication

Support:

* Imported OpenSSH private keys
* Encrypted keys
* Passphrase prompts (if applicable)

Do not support passwords.

Future authentication methods:

* YubiKey
* SSH Agent
* Hardware-backed credentials

⸻

Connection Monitoring

Create a monitoring layer exposing:

* RTT / latency
* Packet loss estimation
* Last successful keep-alive
* Connection quality
* Idle duration
* Reconnect attempts

Expose this as observable state.

⸻

Automatic Reconnection

Requirements:

* Detect network interruptions.
* Retry using exponential backoff.
* Preserve session metadata.
* Notify the UI.
* Stop retrying after configurable limits.

Do not reconnect indefinitely.

⸻

Logging

Implement structured logging.

Levels:

* Debug
* Info
* Warning
* Error

Sensitive information must never appear in logs.

Never log:

* Private keys
* Passphrases
* Authentication material
* Session secrets

⸻

Error Model

Create typed exceptions.

Examples:

ConnectionTimeoutException
AuthenticationFailedException
HostVerificationFailedException
NetworkUnavailableException
ServerUnreachableException
UnsupportedKeyException
ConnectionLostException

The UI should present user-friendly messages while preserving structured diagnostics internally.

⸻

Home Screen Enhancements

The Home screen may now display real connection state.

Include:

* Connected
* Connecting
* Reconnecting
* Failed
* Last successful connection
* Current latency

Do not display fake demo values once live connection data exists.

⸻

Security

Requirements:

* Continue using Android Keystore.
* Never expose decrypted key material outside the SSH layer.
* Validate host identity before authentication.
* Clear sensitive objects from memory when practical.
* Minimize object lifetime for secrets.

⸻

Performance Goals

Target:

* Initial connection <2 seconds on LAN.
* Stable keep-alive.
* Low memory overhead.
* No UI thread blocking.
* Efficient coroutine usage.

⸻

Accessibility

Provide accessible connection status announcements.

Connection state changes should be announced through accessibility services where appropriate.

⸻

Documentation

Update:

* README.md
* ARCHITECTURE.md
* SECURITY.md
* KEY_MANAGEMENT.md

Create:

docs/SSH.md

Document:

* Session lifecycle
* Connection architecture
* Library choice
* Host verification flow
* Authentication flow
* Reconnection strategy

⸻

Explicit Non-Goals

Do not implement:

* Terminal emulator
* tmux
* Shell execution UI
* Server metrics
* File browser
* SCP
* SFTP
* Git
* AI chat
* Relay Agent

⸻

Acceptance Criteria

Phase 03 is complete when:

* Relay connects successfully to a configured server.
* Host verification is enforced.
* SSH authentication uses imported keys.
* Session lifecycle is observable.
* Connection loss is detected.
* Automatic reconnection functions correctly.
* Latency is measured.
* Sensitive information never appears in logs.
* UI remains responsive during connection events.
* Documentation reflects the implemented architecture.

⸻

Verification

Run:

./gradlew assembleDebug
./gradlew lintDebug
./gradlew testDebugUnitTest
./gradlew connectedDebugAndroidTest

Additionally perform manual verification:

* Connect to a local server.
* Connect through Tailscale.
* Reject an unknown host.
* Accept a new host.
* Detect a changed fingerprint.
* Disconnect network during an active session.
* Verify exponential reconnect behavior.
* Validate key import compatibility.
* Confirm no secrets appear in logs.

Run:

git status

⸻

Completion Report

Provide:

* Build status
* Lint status
* Unit test status
* Instrumentation test status
* SSH library selected
* Supported key algorithms
* Session architecture summary
* Connection lifecycle diagram
* Security decisions
* Performance observations
* Files created
* Files modified
* Deferred work
* Recommended Phase 04 scope

⸻

Future Enhancements

Potential additions after Phase 03:

* Connection pooling
* Multiple concurrent SSH sessions
* Jump host (ProxyJump) support
* SOCKS proxy support
* SSH agent forwarding
* SSH multiplexing
* Certificate-based authentication
* Session recording
* Compression negotiation
* IPv6 optimization

These enhancements should not delay completion of Phase 03.

⸻

Design Notes

2026-07-15

The SSH layer is designed as a reusable infrastructure component rather than a feature tied to terminal rendering. Every future capability—including terminal emulation, server diagnostics, Git operations, and the future Relay Agent—should build upon this single connection abstraction.

By separating connection management from terminal interaction, Relay remains easier to test, easier to secure, and flexible enough to support future desktop and multi-session workflows without rewriting the networking stack.
