# Implementation Phases

## Phase 1: Foundation and UI
- Create Android project (Kotlin DSL, Version Catalogs).
- Establish MVVM architecture and Hilt.
- Build design system (pure black/charcoal).
- Set up Navigation Compose and placeholder screens.

## Phase 2: Secure configuration and key management
- Implement Settings screen.
- Set up Android Keystore for secure key storage.
- Add Biometric authentication layer.

## Phase 3: SSH connection layer
- Integrate SSH library.
- Handle connection lifecycle and background stability.
- Host key verification.

## Phase 4: Terminal emulation and tmux integration
- Build full ANSI terminal view.
- Handle hardware/software keyboard input and shortcuts.
- Auto-attach to tmux session.

## Phase 5: Server diagnostics
- Fetch metrics (CPU, RAM, Uptime, Ollama status) via SSH.
- Display metrics on Server dashboard.

## Phase 6: Reliability, reconnect behavior, and production hardening
- Harden reconnect logic for signal drops.
- Final polish and optimization.

## Future Long-Term Phases (Not Approved for Implementation)
- **Phase 7**: Structured remote agent protocol
- **Phase 8**: Mobile task review and approvals
- **Phase 9**: Shared Android and macOS session model
- **Phase 10**: Relay Studio macOS foundation
- **Phase 11**: Native editor and workspace system
- **Phase 12**: AI chat and inline assistance
- **Phase 13**: Agent execution engine
- **Phase 14**: Git, diffs, approvals, and task orchestration
- **Phase 15**: Extensions, model providers, and production hardening
