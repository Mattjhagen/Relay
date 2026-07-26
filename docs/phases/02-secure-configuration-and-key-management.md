Phase 02 — Secure Configuration & Key Management

Status: Planned
Priority: Critical
Estimated Duration: 2–4 development sessions

⸻

Objective

Build Relay’s security foundation.

By the end of this phase, Relay should securely manage server profiles, SSH authentication, biometric protection, and encrypted local configuration without establishing a network connection.

This phase intentionally focuses on trust before connectivity.

⸻

User Experience Goals

Users should be able to:

* Add one or more servers
* Edit server profiles
* Import SSH private keys
* Generate new SSH keys (optional if supported)
* Protect Relay with biometrics
* Securely store all sensitive information
* View trusted host fingerprints
* Choose a default server

No SSH connection should be made during this phase.

⸻

Technical Scope

Implement:

* Android Keystore integration
* Biometric authentication
* Encrypted local storage
* Server profile management
* SSH key management
* Host fingerprint trust model
* Settings persistence
* Secure configuration repository

Do not implement SSH networking.

⸻

Architecture Goals

Introduce the first production data layer.

data/
    model/
    repository/
    security/
    storage/
security/
    BiometricManager
    KeyStoreManager
    EncryptionManager
feature/
    servers/
    security/

Business logic must remain independent of Compose.

⸻

Server Profile Model

Create a strongly typed model.

Example:

ServerProfile

Fields should include:

* UUID
* Display name
* Hostname or IP
* Port
* Username
* Authentication type
* Default profile flag
* Trusted host fingerprint
* Preferred tmux session
* Preferred startup command
* Last successful connection
* Favorite flag
* Notes
* Tags

The model should support future synchronization without requiring breaking changes.

⸻

Authentication Types

Support the architecture for:

* SSH private key
* Hardware-backed key (future)
* Agent forwarding (future)

Password authentication is intentionally not supported.

⸻

SSH Key Management

Support importing:

* OpenSSH private keys
* Ed25519
* RSA
* ECDSA (where supported)

Future support:

* FIDO2 resident keys
* YubiKey
* Android hardware-backed credentials

Do not implement those future types yet.

⸻

Android Keystore

Use Android Keystore to protect:

* Encryption keys
* Authentication secrets
* Imported credentials

Private keys should never be stored in plaintext.

⸻

Biometrics

Support:

* Fingerprint
* Face authentication (where supported)
* Device credential fallback (optional)

Users should be able to require biometric approval before:

* Unlocking Relay
* Viewing private keys
* Editing server profiles
* Beginning an SSH session (future)

⸻

Encrypted Storage

Persist:

* Server profiles
* User preferences
* Trusted host fingerprints
* Appearance settings
* Reconnect preferences

Do not store plaintext credentials.

⸻

Security Principles

Relay is privacy-first.

Requirements:

* Offline operation
* No analytics
* No telemetry
* No cloud synchronization
* No account creation
* No credential upload
* No third-party authentication

Every secret remains on the device.

⸻

UI Requirements

Create a complete server management experience.

Screens:

Servers

* Server list
* Favorite server
* Search
* Add button

⸻

Add Server

Fields:

* Display name
* Host
* Port
* Username
* tmux session
* Startup command

Validate all input.

⸻

SSH Keys

Display:

* Imported keys
* Key type
* Fingerprint
* Creation date
* Default key

Allow:

* Import
* Rename
* Delete

Never display private key contents.

⸻

Security

Settings:

* Require biometrics
* Lock timeout
* Auto-lock
* Clipboard timeout
* Screenshot protection (future)

⸻

Navigation

Add:

* Server Management
* SSH Keys
* Security

Settings should navigate to these screens.

⸻

Data Validation

Validate:

* Hostnames
* IPv4
* IPv6
* Ports
* Usernames
* Duplicate profiles
* Duplicate fingerprints

Reject invalid data before persistence.

⸻

Error Handling

Create a centralized error hierarchy.

Examples:

InvalidHostException
InvalidKeyException
DuplicateServerException
BiometricUnavailableException
EncryptionFailureException

UI should present friendly messages while preserving detailed logs internally.

⸻

Accessibility

Support:

* Screen readers
* Dynamic text
* Keyboard navigation
* Accessible dialogs
* Secure input fields

⸻

Documentation

Update:

* README.md
* ARCHITECTURE.md
* SECURITY.md

Create:

docs/KEY_MANAGEMENT.md

Document:

* Encryption flow
* Android Keystore usage
* Threat model
* Trust assumptions

⸻

Threat Model

Relay assumes:

* Device may be lost
* Device may be stolen
* Network may be hostile
* SSH server may change host keys
* User should always approve new fingerprints

Relay should never silently trust unknown servers.

⸻

Explicit Non-Goals

Do not implement:

* SSH connections
* Terminal emulator
* tmux
* Server monitoring
* File transfer
* Relay Agent
* Git
* Notifications
* Background sync

⸻

Acceptance Criteria

Phase 02 is complete when:

* Multiple server profiles can be managed.
* Sensitive data is encrypted at rest.
* Android Keystore protects encryption material.
* Biometrics successfully gate protected actions.
* SSH keys can be imported and securely stored.
* Trusted host fingerprints are persisted.
* No plaintext secrets appear in storage.
* Configuration survives app restarts.
* Documentation reflects the implemented security model.

⸻

Verification

Run:

./gradlew assembleDebug
./gradlew lintDebug
./gradlew testDebugUnitTest
./gradlew connectedDebugAndroidTest
git status

Additionally verify:

* Keystore initialization
* Biometric enrollment handling
* Encrypted storage persistence
* Import/export edge cases
* Rotation after device reboot

⸻

Completion Report

Provide:

* Build status
* Lint status
* Test status
* Encryption implementation summary
* Keystore implementation summary
* Supported SSH key formats
* Data model overview
* Security decisions made
* Remaining risks
* Deferred work
* Files created
* Files modified
* Recommended Phase 03 scope

⸻

Future Enhancements

Potential additions after Phase 02:

* YubiKey support
* FIDO2 resident credentials
* Passkey-backed SSH authentication
* Secure backup/export of Relay configuration
* Multiple identities per server
* Team profile import/export
* Hardware security module integration

These items are intentionally deferred until later phases.

⸻

Design Notes

2026-07-15

Relay adopts a security-first architecture. Before the application is allowed to communicate with any server, it must demonstrate that sensitive configuration can be managed safely and predictably.

This phase establishes the trust model that every later networking feature will build upon. Future phases must reuse these security abstractions rather than bypassing them.
