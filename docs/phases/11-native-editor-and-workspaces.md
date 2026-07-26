Phase 11 — Native Editor, Workspaces & Project System

Status: Planned (Long-Term)
Priority: Critical
Estimated Duration: 8–12 development sessions

⸻

Objective

Build Relay Studio’s first production editing experience.

This phase transforms Relay Studio from an application shell into a true integrated development environment by introducing a native code editor, workspace management, project indexing, file navigation, search, split views, and editing infrastructure.

This is not where AI features are introduced. The editor must be excellent on its own before AI is layered on top.

⸻

Product Philosophy

Relay Studio should never feel like a browser pretending to be an IDE.

The editor should prioritize:

* Native performance
* Extremely low latency
* Efficient memory usage
* Excellent keyboard interaction
* Large project support
* Local-first architecture
* Clean extensibility

The editor should be something developers enjoy using even with AI disabled.

⸻

User Experience Goals

Users should be able to:

* Open a local workspace.
* Open a remote workspace.
* Browse files.
* Edit files.
* Create files.
* Rename files.
* Delete files.
* Search across the project.
* Open multiple tabs.
* Split the editor.
* Restore previous sessions.
* Work offline where appropriate.

Relay Studio should now feel like a capable modern IDE.

⸻

Technical Scope

Implement:

* Native code editor
* File tree
* Workspace manager
* Project indexing
* Search
* Tabs
* Split editor
* Breadcrumbs
* Minimap (optional)
* Syntax highlighting
* Diagnostics foundation
* Undo/redo
* Autosave
* Session restoration
* File watching

Do not implement:

* AI chat
* Inline AI
* Git UI
* Extensions

⸻

Architecture

Introduce editor-specific modules.

Sources/
Editor/
    EditorView
    EditorState
    EditorBuffer
    CursorManager
    SelectionManager
    UndoManager
    SyntaxHighlighter
    DiagnosticsOverlay
    SearchEngine
    FileWatcher
Workspace/
    WorkspaceManager
    WorkspaceIndexer
    ProjectModel
Search/
    ProjectSearch
    SymbolSearch
Tabs/
    TabManager
SplitView/

The editor should remain independent from AI systems.

⸻

Editor Engine

Create:

EditorBuffer

Responsibilities:

* Text storage
* Editing operations
* Cursor movement
* Selection
* Undo
* Redo
* Incremental updates
* File loading
* File saving

The editor engine should not depend on SwiftUI.

SwiftUI becomes a rendering layer only.

⸻

Supported Languages

Initial syntax support:

* Swift
* Kotlin
* Java
* TypeScript
* JavaScript
* JSON
* YAML
* Markdown
* HTML
* CSS
* Shell
* Python
* Rust
* Go
* C#
* SQL

Design the syntax engine so additional languages can be added later.

⸻

File Tree

Support:

* Large repositories
* Expand/collapse
* Drag and drop
* Context menus
* Create
* Rename
* Delete
* Duplicate
* Reveal in Finder
* Reveal in terminal

Lazy-load directories for large projects.

⸻

Workspace Manager

Support:

* Multiple workspaces
* Recent workspaces
* Favorite workspaces
* Remote workspaces
* Session restoration
* Workspace settings

Workspaces become first-class objects throughout Relay Studio.

⸻

Tabs

Support:

* Unlimited tabs
* Dirty indicators
* Pinning
* Tab history
* Reordering
* Close others
* Close saved
* Restore recently closed

Future support may include tab groups.

⸻

Split Editor

Support:

* Vertical split
* Horizontal split
* Independent navigation
* Shared workspace state

Prepare for multiple monitors in later phases.

⸻

Search

Implement:

Project Search

* File names
* File contents
* Regular expressions
* Case sensitivity
* Whole word
* Replace

⸻

Symbol Search

Support:

* Classes
* Functions
* Variables
* Types
* Files

The indexing system should support future language intelligence.

⸻

File Watching

Automatically detect:

* External file changes
* Git branch switches
* Repository updates
* Deleted files
* Renamed files

Provide intelligent reload behavior.

⸻

Diagnostics Foundation

Prepare for:

* Errors
* Warnings
* Notes
* Build messages

Only local diagnostics are required in this phase.

⸻

Session Restoration

Remember:

* Open workspaces
* Open tabs
* Cursor positions
* Scroll positions
* Split layouts

Restore automatically on launch.

⸻

Keyboard Support

Implement comprehensive shortcuts.

Examples:

* Command-P
* Command-T
* Command-W
* Command-Shift-P
* Command-F
* Command-G
* Command-S
* Command-Option-Left/Right
* Command-1 through Command-9

All shortcuts should be configurable later.

⸻

Performance Goals

Target:

* Instant typing
* Large file support
* Fast search
* Incremental indexing
* Minimal memory overhead
* Smooth scrolling
* Efficient syntax highlighting

Avoid blocking the main thread.

⸻

Accessibility

Support:

* VoiceOver
* Keyboard-only workflows
* High contrast
* Dynamic accessibility settings
* Accessible menus
* Proper focus management

⸻

Documentation

Create:

* docs/EDITOR.md
* docs/WORKSPACES.md
* docs/SEARCH.md
* docs/INDEXING.md

Document:

* Editor architecture
* Rendering pipeline
* Workspace model
* Search engine
* Indexing strategy
* Session restoration

⸻

Explicit Non-Goals

Do not implement:

* AI chat
* Inline AI
* Git UI
* Extensions
* Relay Agent improvements
* Cloud synchronization

The editor must stand on its own before AI enhancements begin.

⸻

Acceptance Criteria

Phase 11 is complete when:

* Files can be opened and edited.
* Workspaces function correctly.
* Project search works.
* Tabs work.
* Split editors work.
* Session restoration works.
* Syntax highlighting functions.
* File watching detects changes.
* Documentation reflects the architecture.

⸻

Verification

Verify:

* Large repositories
* Large files
* Rapid typing
* Search performance
* Split editor behavior
* Session restoration
* External file modifications
* Keyboard shortcuts
* Accessibility

Run project formatting, static analysis, unit tests, and inspect repository cleanliness:

git status

⸻

Completion Report

Provide:

* Build status
* Editor architecture summary
* Workspace implementation
* Search performance
* File watcher summary
* Keyboard shortcut support
* Accessibility observations
* Files created
* Files modified
* Deferred work
* Recommended Phase 12 scope

⸻

Future Enhancements

Potential additions after Phase 11:

* Code folding
* Minimap
* Multiple cursors
* Column selection
* Inline blame
* Live collaboration
* Semantic highlighting
* LSP integration
* Structural editing
* Notebook-style documents

These enhancements should extend the editor without compromising its performance or simplicity.

⸻

Design Notes

2026-07-15

Phase 11 establishes Relay Studio’s identity as a serious native IDE. The editor is deliberately developed before AI capabilities so that every future AI feature builds upon a fast, stable, and well-designed editing experience rather than compensating for a weak foundation.

The editor engine should remain independent from SwiftUI and AI systems, making it reusable, testable, and capable of supporting future rendering technologies if needed.
