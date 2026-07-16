# Relay Vision

Relay will eventually become a cross-device AI development platform consisting of Relay Mobile, Relay Studio, and the Relay Agent.

## Relay Mobile
A native Android companion app for:
- Connecting securely to remote development servers
- Accessing persistent OpenCode sessions
- Monitoring running AI coding tasks
- Reviewing diffs and logs
- Approving or rejecting actions
- Managing repositories and servers
- Receiving completion and failure notifications
- Performing lightweight edits while away from a desktop

## Relay Studio
A fully native macOS AI IDE designed to compete with tools such as Cursor and Antigravity. It will feature:
- Native macOS interface
- Multi-repository workspaces
- Advanced code editor with file tree, tabs, and split panes
- Integrated terminal and git source control
- Diff, merge tools, and project-wide semantic search
- AI chat and inline code generation
- Agent mode, planning, and execution workflows
- Tool-call approval system and background task management
- Local and remote model support (OpenAI, Anthropic, Gemini, Ollama)
- Remote development over SSH and shared sessions between Mac and Android
- Cloud-independent operation with privacy-first local storage
- Plugin and extension architecture

## Relay Agent
A lightweight server-side agent running on development machines to provide:
- Secure session discovery and OpenCode process management
- Repository metadata, file browsing, git status, and diffs
- Task progress events and structured logs
- Push notification events and approval requests
- File synchronization and shared session state between Mobile and Studio

## Shared Session Architecture
Relay Mobile and Relay Studio will eventually share concepts and protocols for seamless development experiences. They will share domain models around:
- Server profiles and workspaces
- Repositories and sessions
- Agent tasks, messages, and tool calls
- Approvals, file changes, and git diffs
- Notifications and model providers

## Local-first and Privacy-first Principles
The future macOS product and mobile companion will feel genuinely native and deeply integrated with their respective platforms. Technologies and architectural decisions will support:
- Native performance and local-first operation
- Strong privacy with cloud-independent operation
- Provider independence
- Shared state between desktop and mobile without compromising data security
- Human approval before dangerous actions

## Staged Roadmap

The following phases are long-term and are not yet approved for implementation:

- **Phase 7**: Structured remote agent protocol
- **Phase 8**: Mobile task review and approvals
- **Phase 9**: Shared Android and macOS session model
- **Phase 10**: Relay Studio macOS foundation
- **Phase 11**: Native editor and workspace system
- **Phase 12**: AI chat and inline assistance
- **Phase 13**: Agent execution engine
- **Phase 14**: Git, diffs, approvals, and task orchestration
- **Phase 15**: Extensions, model providers, and production hardening
