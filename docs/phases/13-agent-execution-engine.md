Phase 13 — Autonomous Agent Execution Engine

Status: Planned (Long-Term)
Priority: Critical
Estimated Duration: 12–20 development sessions

⸻

Objective

Build Relay’s autonomous execution engine.

This phase introduces long-running AI agents capable of planning, reasoning, executing tools, recovering from failures, coordinating with one another, and completing complex software engineering tasks with human oversight.

Relay transitions from an AI-assisted IDE into an AI software engineering platform.

The execution engine should be capable of running locally, remotely through the Relay Agent, or as a hybrid workflow.

⸻

Product Philosophy

Agents should behave like experienced software engineers.

Every agent must:

* Explain its reasoning.
* Work incrementally.
* Verify its own work.
* Recover from failure.
* Respect user approval policies.
* Stop immediately when requested.
* Never hide actions.
* Never execute destructive operations without authorization.

The human remains in control.

⸻

User Experience Goals

Users should be able to:

* Assign goals instead of individual prompts.
* Observe planning in real time.
* Watch agents execute tasks.
* Pause execution.
* Resume execution.
* Cancel execution.
* Approve dangerous operations.
* Review completed work.
* Inspect reasoning.
* Replay execution history.

Agents should feel predictable and trustworthy.

⸻

Technical Scope

Implement:

* Agent runtime
* Planner
* Executor
* Tool scheduler
* Approval engine
* Memory system
* Retry engine
* Checkpointing
* Task decomposition
* Parallel execution
* Agent messaging
* Agent coordination
* Long-running workflows

⸻

Architecture

Introduce an execution subsystem.

Sources/
Agents/
    AgentRuntime
    AgentManager
    Planner
    Executor
    Scheduler
    Memory
    ToolRouter
    ApprovalEngine
    CheckpointManager
    RecoveryEngine
    ConversationBridge
Planning/
Execution/
Tools/
Memory/

The execution engine should remain independent of any single AI provider.

⸻

Execution Pipeline

Every task follows:

Goal
↓
Planning
↓
Task Graph
↓
Execution
↓
Verification
↓
Approval (if required)
↓
Completion
↓
Summary

Each stage should be observable.

⸻

Task Graph

Represent execution as a directed graph.

Nodes may include:

* Read files
* Search code
* Run tests
* Modify files
* Execute terminal commands
* Ask user
* Query diagnostics
* Commit changes

The graph should support branching and retries.

⸻

Planner

Responsibilities:

* Understand goals.
* Break work into subtasks.
* Estimate complexity.
* Detect dependencies.
* Schedule execution.
* Request clarification when needed.

Planning should be separate from execution.

⸻

Executor

Responsibilities:

* Execute planned tasks.
* Observe outcomes.
* Report progress.
* Retry failures.
* Escalate problems.
* Request approvals.

Execution should never bypass the approval engine.

⸻

Tool System

Support tools such as:

* File operations
* Repository search
* Diagnostics
* Git
* Terminal
* Relay Agent
* Package managers
* Documentation lookup
* Model switching

Every tool should expose:

* Metadata
* Required permissions
* Risk level
* Input schema
* Output schema

⸻

Approval Engine

Require approval for:

* Git push
* Force push
* Branch deletion
* File deletion
* Dependency installation
* Database migrations
* Deployments
* Secret access
* Arbitrary shell commands

Approvals should integrate with Relay Mobile.

⸻

Agent Memory

Support:

* Working memory
* Conversation memory
* Workspace memory
* Project memory
* Execution history

Memory should be searchable.

Memory should remain local by default.

⸻

Checkpointing

Agents should periodically save:

* Plan
* Progress
* Tool state
* Context
* Current step

Interrupted tasks should resume without restarting from the beginning.

⸻

Recovery

Agents should recover from:

* Provider failures
* Network interruptions
* Tool failures
* Server restarts
* Relay Agent restarts
* Model timeouts

Recovery should preserve user confidence.

⸻

Parallel Execution

Support:

* Multiple independent tasks
* Background agents
* Coordinated agents
* Dependency-aware scheduling

Parallel execution must remain deterministic.

⸻

Multi-Agent Collaboration

Introduce specialized agents.

Examples:

* Architect
* Backend
* Frontend
* Security
* QA
* Documentation
* DevOps
* Research

The coordinator should assign work based on specialization.

⸻

Execution Timeline

Record:

* Plans
* Tool calls
* Decisions
* Approvals
* Failures
* Retries
* Results

Users should be able to replay execution.

⸻

Security

Requirements:

* Every tool call is attributable.
* Every approval is recorded.
* Every action is reversible where practical.
* Least privilege.
* Transparent reasoning.
* Configurable approval policies.

Agents must never become opaque.

⸻

Performance Goals

Target:

* Efficient scheduling
* Low orchestration overhead
* Fast checkpointing
* Minimal redundant planning
* Stable execution over many hours

⸻

Accessibility

Support:

* Screen readers
* Keyboard-first workflows
* Accessible timelines
* Accessible approval dialogs
* High contrast

⸻

Documentation

Create:

* docs/AGENTS.md
* docs/PLANNING.md
* docs/EXECUTION.md
* docs/TOOLS.md
* docs/CHECKPOINTS.md

Document:

* Execution engine
* Planner
* Scheduler
* Recovery model
* Memory architecture
* Approval model

⸻

Explicit Non-Goals

Do not implement:

* Marketplace agents
* Team collaboration
* Cloud-hosted orchestration
* Autonomous deployments without approval
* Plugin ecosystem

These belong to future phases.

⸻

Acceptance Criteria

Phase 13 is complete when:

* Agents can execute multi-step plans.
* Task graphs execute correctly.
* Checkpointing functions.
* Recovery functions.
* Parallel execution works.
* Approvals integrate with Relay Mobile.
* Execution timelines are preserved.
* Documentation reflects the architecture.

⸻

Verification

Verify:

* Multi-step execution
* Provider failure recovery
* Agent restart
* Server restart
* Approval interruption
* Parallel task scheduling
* Checkpoint restoration
* Long-running execution
* Timeline replay

Run project tests, static analysis, formatting tools, and inspect repository cleanliness:

git status

⸻

Completion Report

Provide:

* Build status
* Execution architecture summary
* Planner summary
* Scheduler summary
* Tool system summary
* Recovery observations
* Checkpoint implementation
* Files created
* Files modified
* Deferred work
* Recommended Phase 14 scope

⸻

Future Enhancements

Potential additions after Phase 13:

* Agent personalities
* Adaptive planning strategies
* Learning from prior executions
* Cost-aware planning
* Energy-efficient scheduling
* Distributed agent execution
* Swarm orchestration
* Visual execution graph editor
* Policy-driven enterprise agents

These capabilities should extend the execution engine without compromising transparency or user control.

⸻

Design Notes

2026-07-15

Phase 13 represents Relay’s transition from an AI-assisted development environment to a true autonomous engineering platform. The execution engine is intentionally designed around observable planning, explicit approvals, recoverable workflows, and provider independence.

The execution engine should orchestrate intelligence rather than embed it. By separating planning, execution, memory, approvals, and tool invocation into independent subsystems, Relay can evolve alongside future AI models while maintaining deterministic behavior, strong security boundaries, and a human-in-the-loop workflow.
