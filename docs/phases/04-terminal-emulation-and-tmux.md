Phase 04 — Terminal Emulation & tmux Integration

Status: Planned
Priority: Critical
Estimated Duration: 4–6 development sessions

⸻

Objective

Transform Relay from an SSH client into a true remote development companion.

This phase introduces a full terminal emulator, seamless tmux integration, persistent OpenCode sessions, and the interaction model that defines Relay’s core experience.

By the end of this phase, opening Relay should reconnect the user directly into the same long-running OpenCode session regardless of app restarts, device sleep, or temporary network interruptions.

⸻

User Experience Goals

Users should be able to:

* Connect to a server and immediately resume their existing OpenCode session.
* Interact with OpenCode naturally from Android.
* Disconnect and reconnect without losing terminal state.
* Rotate the device without interrupting the session.
* Use both hardware and software keyboards.
* Scroll through terminal history.
* Copy and paste text.
* Send control keys and escape sequences.
* Reattach to an existing tmux session automatically.

Relay should feel comparable to using a desktop terminal, not a simplified mobile console.

⸻

Technical Scope

Implement:

* ANSI terminal emulation
* VT100 / VT220 compatibility where appropriate
* Terminal rendering
* Scrollback buffer
* Keyboard translation
* Clipboard integration
* Session attachment
* tmux integration
* Automatic OpenCode launch
* Resize handling
* Terminal state restoration
* Cursor rendering
* Selection model
* Terminal preferences

Do not implement:

* File browser
* SCP/SFTP
* Git UI
* AI workflow management
* Relay Agent

⸻

Architecture

Introduce a dedicated terminal subsystem.

core/
    terminal/
        TerminalEngine
        TerminalRenderer
        TerminalBuffer
        TerminalSession
        TerminalInputProcessor
        TerminalOutputProcessor
        TerminalPreferences
        SelectionManager
        ClipboardManager
        KeyboardMapper
        ResizeManager
feature/
    terminal/
        TerminalScreen
        TerminalViewModel
        TerminalUiState

The terminal engine must remain independent of Compose rendering.

Compose should display state, not perform terminal parsing.

⸻

Terminal Standards

Support modern terminal behavior including:

* ANSI escape sequences
* UTF-8
* 24-bit color
* Cursor movement
* Alternate screen buffer
* Line wrapping
* Window resizing
* Terminal title updates
* Bell events (configurable)
* Mouse reporting (future-ready)

Document any unsupported sequences.

⸻

tmux Integration

Relay should automatically execute:

tmux new-session -A -s opencode "$HOME/.opencode/bin/opencode"

Requirements:

* Automatically create the session if missing.
* Reattach if already running.
* Preserve terminal history.
* Handle server reboots gracefully.
* Detect unexpected session termination.
* Allow custom session names in server profiles.
* Allow custom startup commands.

No manual tmux interaction should be required for normal use.

⸻

OpenCode Session Lifecycle

States:

Disconnected
↓
Connecting
↓
SSH Connected
↓
Attaching tmux
↓
Launching OpenCode (if needed)
↓
Interactive
↓
Detached
↓
Reconnecting

Represent this lifecycle separately from SSH connection state.

⸻

Keyboard Support

Support:

* Hardware keyboards
* Bluetooth keyboards
* Foldable keyboards
* Software keyboard

Shortcut toolbar:

ESC
CTRL
ALT
TAB
↑
↓
←
→
/
-
|
~
:

Allow future customization.

⸻

Text Selection

Implement:

* Tap to position cursor (where appropriate)
* Long-press selection
* Copy
* Select all
* Paste
* Share selected text

Selection should not interfere with normal terminal interaction.

⸻

Scrollback

Requirements:

* Configurable history size
* Smooth scrolling
* Fast rendering
* Preserve history across reconnections where possible
* Optional “jump to bottom” control

⸻

Window Resizing

Correctly propagate terminal size changes when:

* Device rotates
* Keyboard opens
* Split-screen mode changes
* Foldable device posture changes

The remote shell must receive updated terminal dimensions.

⸻

Terminal Preferences

Allow configuration for:

* Font size
* Cursor style
* Cursor blink
* Scrollback size
* Bell behavior
* Theme
* Line spacing
* Shortcut toolbar visibility

Persist preferences securely.

⸻

Performance Goals

Target:

* 60 FPS scrolling
* Minimal input latency
* Efficient rendering for large outputs
* No dropped characters
* No UI freezes during rapid output

Avoid unnecessary recomposition.

⸻

Accessibility

Support:

* Screen readers where practical
* Adjustable font sizes
* High contrast
* External keyboards
* Landscape orientation
* Large touch targets for shortcut buttons

⸻

Error Handling

Gracefully handle:

* Broken SSH channels
* Lost tmux sessions
* OpenCode crashes
* Invalid startup commands
* Resize failures
* Clipboard errors

Provide actionable recovery guidance.

⸻

Documentation

Update:

* README.md
* ARCHITECTURE.md
* SSH.md

Create:

docs/TERMINAL.md

Document:

* Terminal architecture
* ANSI support
* Rendering pipeline
* tmux integration
* Session lifecycle
* Keyboard mapping
* Performance considerations

⸻

Explicit Non-Goals

Do not implement:

* File browser
* Git UI
* SCP
* SFTP
* Relay Agent
* AI chat
* Notifications
* Server metrics dashboard improvements beyond what’s already available

⸻

Acceptance Criteria

Phase 04 is complete when:

* Relay automatically attaches to the correct tmux session.
* OpenCode launches automatically when required.
* Existing sessions resume seamlessly.
* ANSI rendering behaves correctly.
* Terminal resizing works.
* Scrollback functions reliably.
* Clipboard operations work.
* Keyboard shortcuts function correctly.
* Orientation changes do not interrupt sessions.
* Terminal rendering remains smooth under sustained output.

⸻

Verification

Run:

./gradlew assembleDebug
./gradlew lintDebug
./gradlew testDebugUnitTest
./gradlew connectedDebugAndroidTest

Manual verification:

* Connect over local network.
* Connect through Tailscale.
* Launch OpenCode automatically.
* Reconnect after device sleep.
* Rotate device repeatedly.
* Test hardware keyboard shortcuts.
* Test software keyboard.
* Verify copy/paste.
* Generate high-volume terminal output.
* Confirm scrollback performance.
* Restart the server and reconnect.
* Kill the tmux session and verify recreation.

Run:

git status

⸻

Completion Report

Provide:

* Build status
* Lint status
* Unit test status
* Instrumentation test status
* Terminal engine architecture
* ANSI feature support summary
* tmux integration summary
* Keyboard support summary
* Performance observations
* Files created
* Files modified
* Deferred work
* Recommended Phase 05 scope

⸻

Future Enhancements

Potential additions after Phase 04:

* Split terminals
* Multiple concurrent terminal tabs
* Terminal search
* Saved commands
* Command history browser
* Inline image rendering
* OSC 52 clipboard support
* Hyperlink detection
* Custom keyboard layouts
* Terminal themes
* Session recording
* Macro support

These enhancements should not delay completion of Phase 04.

⸻

Design Notes

2026-07-15

Phase 04 defines the experience users will associate with Relay. The terminal is not merely an SSH shell—it is a persistent workspace centered around OpenCode and tmux.

Every later capability, including AI task management, server administration, and eventually Relay Studio on macOS, should reuse this session model. The terminal engine should therefore remain modular, renderer-independent, and reusable across future platforms wherever practical.
