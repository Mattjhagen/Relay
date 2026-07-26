Phase 12 — AI Chat, Inline Assistance & Multi-Agent Collaboration

Status: Planned (Long-Term)
Priority: Critical
Estimated Duration: 10–16 development sessions

⸻

Objective

Introduce Relay Studio’s AI layer.

This phase transforms Relay Studio from a traditional IDE into an AI-native development environment by adding conversational AI, inline code assistance, autonomous coding agents, semantic project understanding, and provider-independent model orchestration.

The AI should feel like a knowledgeable engineering teammate—not autocomplete.

⸻

Product Philosophy

AI should never take control away from the developer.

Relay’s AI must be:

* Explainable
* Transparent
* Interruptible
* Provider-independent
* Privacy-first
* Context-aware
* Workspace-aware
* Session-aware

Every action should be inspectable.

Every destructive action should require approval.

⸻

User Experience Goals

Users should be able to:

* Chat with AI.
* Reference files.
* Reference folders.
* Reference repositories.
* Reference terminals.
* Reference diagnostics.
* Ask architectural questions.
* Generate code.
* Refactor code.
* Explain code.
* Review pull requests.
* Generate documentation.
* Create tests.
* Ask the AI to complete multi-step work.
* Interrupt AI work at any time.

The AI should understand the entire active workspace.

⸻

Technical Scope

Implement:

* AI chat
* Inline code assistance
* Workspace context engine
* Semantic indexing
* Prompt composer
* Conversation history
* Tool invocation
* Multi-provider support
* Agent orchestration foundation
* Context selection
* Streaming responses
* AI command palette integration

Do not implement:

* Plugin marketplace
* Enterprise collaboration
* Cloud synchronization

⸻

Architecture

Introduce an AI subsystem.

Sources/
AI/
    AIManager
    ConversationManager
    ContextEngine
    PromptComposer
    ProviderRouter
    ModelRegistry
    StreamingEngine
    ToolDispatcher
    AgentCoordinator
    SemanticIndex
Chat/
Inline/
Models/
Providers/
Prompts/

The AI subsystem must remain completely provider-independent.

⸻

AI Provider Layer

Support:

* OpenAI
* Anthropic
* Google Gemini
* Ollama
* OpenRouter
* Local models
* Future providers

No provider-specific logic should leak into the editor.

Every provider should implement a common protocol.

⸻

Conversation Model

Create:

Conversation
ConversationMessage
ConversationContext
ConversationSummary

Each conversation should include:

* Workspace
* Repository
* Files
* Referenced symbols
* Active tasks
* Active terminal
* AI provider
* Model
* Token usage
* Cost estimate (where available)

⸻

Workspace Context Engine

The AI should understand:

* Open files
* Selected code
* Entire repository
* Diagnostics
* Search results
* Git status
* Terminal output
* Active tasks
* Documentation
* Project structure

Context selection should be explicit whenever possible.

⸻

Inline Assistance

Support:

* Explain selection
* Refactor selection
* Improve naming
* Generate documentation
* Generate tests
* Fix diagnostics
* Complete functions
* Generate implementations
* Expand TODOs

Suggestions should appear as previews before acceptance.

⸻

AI Sidebar

Display:

* Conversations
* Active agents
* Running tasks
* Model status
* Context inspector
* Token usage
* Estimated cost

Allow multiple simultaneous conversations.

⸻

Prompt Composer

Automatically build prompts from:

* Selected files
* Workspace metadata
* User request
* Relevant symbols
* Conversation history

The prompt system should minimize unnecessary token usage.

⸻

Semantic Index

Introduce semantic search over:

* Files
* Symbols
* Documentation
* Conversations
* Tasks
* Notes

Support natural-language retrieval rather than simple keyword matching.

⸻

Tool Invocation

Allow AI to request tools such as:

* Read file
* Search workspace
* List directory
* Read diagnostics
* Read Git status
* Execute approved commands
* Query Relay Agent
* Read logs

Every tool invocation should be visible to the user.

⸻

Multi-Agent Foundation

Introduce specialized agents.

Examples:

* Architect
* Refactor Agent
* Test Agent
* Documentation Agent
* Security Agent
* Debug Agent
* Performance Agent

A coordinating agent may delegate work to specialized agents.

⸻

AI Memory

Support:

* Conversation summaries
* Workspace memory
* Project preferences
* User preferences

Memory should remain local by default.

Cloud synchronization should remain optional.

⸻

Model Management

Users should be able to:

* Select provider
* Select model
* Configure temperature
* Configure context limits
* Configure reasoning mode
* Configure streaming

Per-workspace defaults should be supported.

⸻

Streaming

Responses should stream incrementally.

The UI should remain responsive while generation occurs.

Users should be able to interrupt generation instantly.

⸻

Security

Requirements:

* Local-first by default.
* Explicit approval before sharing workspace context externally.
* Provider-specific privacy notices.
* Secret redaction.
* Context inspection before sending.
* Tool-call transparency.

Users should always know what information is being shared.

⸻

Accessibility

Support:

* Screen readers
* Keyboard-first workflows
* Dynamic type
* Accessible streaming responses
* Accessible code previews

⸻

Performance Goals

Target:

* Fast prompt construction
* Efficient semantic search
* Low idle resource usage
* Responsive streaming
* Background indexing without UI blocking

⸻

Documentation

Create:

* docs/AI.md
* docs/CONTEXT_ENGINE.md
* docs/PROVIDERS.md
* docs/SEMANTIC_INDEX.md
* docs/CONVERSATIONS.md

Document:

* AI architecture
* Provider abstraction
* Context assembly
* Tool invocation
* Privacy model
* Streaming architecture

⸻

Explicit Non-Goals

Do not implement:

* Plugin marketplace
* Enterprise collaboration
* Marketplace-hosted prompts
* Cloud-only AI workflows
* Remote code execution without approval

⸻

Acceptance Criteria

Phase 12 is complete when:

* AI conversations function.
* Multiple providers are supported.
* Workspace context can be inspected.
* Inline assistance works.
* Tool invocations are transparent.
* Semantic search functions.
* Streaming responses work.
* Documentation reflects the AI architecture.

⸻

Verification

Verify:

* Multi-provider switching
* Large workspace context
* Streaming interruption
* Tool approval flow
* Context inspection
* Semantic search quality
* Offline local model support
* Accessibility
* Keyboard navigation

Run project tests, static analysis, formatting tools, and inspect repository status:

git status

⸻

Completion Report

Provide:

* Build status
* AI architecture summary
* Supported providers
* Context engine summary
* Semantic indexing summary
* Tool invocation summary
* Performance observations
* Files created
* Files modified
* Deferred work
* Recommended Phase 13 scope

⸻

Future Enhancements

Potential additions after Phase 12:

* Voice conversations
* Screen understanding
* Diagram generation
* Image reasoning
* Personalized coding styles
* Long-term project memory
* Multi-modal context
* AI-assisted onboarding
* Team knowledge bases

These enhancements should build on the provider-independent architecture introduced here.

⸻

Design Notes

2026-07-15

Phase 12 is where Relay Studio becomes an AI-native IDE rather than simply an editor with an assistant bolted on. AI should integrate deeply with workspaces, repositories, diagnostics, and developer intent while remaining transparent and controllable.

The architecture must assume that models, providers, and capabilities will evolve rapidly. By isolating provider-specific behavior behind common interfaces and making context assembly explicit, Relay can remain flexible, privacy-conscious, and resilient to future changes in the AI ecosystem.
