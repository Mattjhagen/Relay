Phase 08 — Mobile Task Review & Approvals

Status: Planned (Long-Term)
Priority: High
Estimated Duration: 4–7 development sessions

⸻

Objective

Transform Relay Mobile from a remote terminal into an intelligent command center for AI-assisted software development.

This phase introduces structured task management, live progress monitoring, human approval workflows, notifications, and review tools that allow users to supervise AI coding agents from anywhere without remaining attached to a terminal.

Relay Mobile should become the “mission control” application for Relay Studio and remote AI agents.

⸻

Vision

Users should be able to leave a complex AI task running on a server and confidently walk away.

Relay should notify them when:

* A task completes.
* The AI needs approval.
* A merge conflict occurs.
* A dangerous action is requested.
* Tests fail.
* Human input is required.

The user should be able to review the request and approve or reject it directly from their phone.

⸻

User Experience Goals

Users should be able to:

* View active AI tasks.
* Review completed tasks.
* Monitor progress.
* Read structured reasoning.
* Review proposed changes.
* Approve or reject dangerous actions.
* Pause or cancel tasks.
* Resume paused work.
* Receive actionable notifications.
* Continue using the terminal when desired.

Terminal interaction should become optional for many workflows.

⸻

Technical Scope

Implement:

* Task dashboard
* Structured task model
* Approval workflow
* Notification center
* Event timeline
* Progress reporting
* Task history
* Action requests
* Human-in-the-loop controls
* Agent communication through the Relay Agent protocol

Terminal emulation should remain available but no longer be the primary interface for AI work.

⸻

Architecture

Introduce a task subsystem.

feature/
    tasks/
        TaskDashboard
        TaskDetails
        TaskHistory
        ApprovalScreen
        TimelineScreen
core/
    tasks/
        TaskManager
        ApprovalManager
        TimelineManager
        NotificationManager
data/
    tasks/

Business logic must remain independent from the UI.

⸻

Task Model

Introduce:

RelayTask

Fields should include:

* Task ID
* Title
* Description
* Repository
* Workspace
* Status
* Progress
* Current step
* Started time
* Updated time
* Completed time
* Agent
* Priority
* Estimated completion
* User who initiated task
* Required approvals
* Result summary

The model should be reusable by Relay Studio.

⸻

Task States

Supported states:

Queued
↓
Preparing
↓
Running
↓
Waiting For Approval
↓
Paused
↓
Completed
↓
Failed
↓
Cancelled

Represent task state using sealed classes or equivalent typed models.

⸻

Timeline

Each task should expose a chronological timeline.

Examples:

* Repository opened
* Branch created
* Dependencies installed
* Tests started
* Build succeeded
* Commit prepared
* Approval requested
* Merge completed

Each event should include:

* Timestamp
* Type
* Description
* Severity

⸻

Approval Workflow

Support approval requests for:

* Shell commands
* File deletion
* Git push
* Branch deletion
* Dependency installation
* Database migrations
* Deployment
* Secret access
* Force operations

Each approval should display:

* Requested action
* Reason
* Risk level
* Affected files
* Agent reasoning
* Estimated impact

The user may:

* Approve
* Reject
* Cancel task
* Ask for more information (future)

⸻

Risk Levels

Define:

Low
Medium
High
Critical

Critical actions should always require explicit approval.

⸻

Notifications

Support notifications for:

* Task completed
* Task failed
* Approval requested
* Tests completed
* Build failed
* Deployment completed
* Agent disconnected

Notifications should deep-link directly into the relevant task.

⸻

Task Dashboard

Display:

* Active tasks
* Queued tasks
* Waiting approvals
* Recently completed
* Failed tasks

Support filtering by:

* Repository
* Status
* Priority
* Agent
* Server

⸻

Task Details

Display:

* Progress
* Timeline
* Logs
* Summary
* Repository
* Branch
* Duration
* Files changed
* AI reasoning summary
* Approval history

Large log output should be collapsible.

⸻

Progress Reporting

Support:

* Percent complete
* Current operation
* Estimated remaining time
* Step count
* Live updates

Progress should be streamed from the Relay Agent.

⸻

Review Experience

Users should be able to review:

* File summaries
* Commit summaries
* Test results
* Build results
* Lint results

Actual code review will be expanded in later phases.

⸻

Security

Approvals should:

* Require an authenticated Relay session.
* Respect biometric requirements.
* Expire after configurable timeouts.
* Record approval decisions locally.

Never allow approval from stale task states.

⸻

Accessibility

Support:

* Screen readers
* Dynamic type
* Accessible timelines
* Notification accessibility
* Keyboard navigation where applicable

⸻

Documentation

Create:

docs/TASKS.md
docs/APPROVALS.md
docs/NOTIFICATIONS.md

Document:

* Task lifecycle
* Approval model
* Notification architecture
* Security decisions
* Timeline model

⸻

Explicit Non-Goals

Do not implement:

* Relay Studio
* Plugin system
* Full Git UI
* Visual merge tools
* Code editor
* AI prompt editing
* Workspace editing

These belong to later phases.

⸻

Acceptance Criteria

Phase 08 is complete when:

* Active tasks are displayed.
* Task progress updates in real time.
* Timeline events stream correctly.
* Approval requests function.
* Notifications deep-link correctly.
* Task history is persisted.
* Security requirements are enforced.
* Documentation reflects the implemented workflow.

⸻

Verification

Run:

./gradlew assembleDebug
./gradlew lintDebug
./gradlew testDebugUnitTest

Manual verification:

* Create multiple concurrent tasks.
* Receive approval request.
* Approve task.
* Reject task.
* Cancel task.
* Verify notification navigation.
* Verify reconnect during active tasks.
* Verify timeline ordering.

Run:

git status

⸻

Completion Report

Provide:

* Build status
* Task architecture summary
* Approval workflow summary
* Notification summary
* Timeline implementation
* Security decisions
* Files created
* Files modified
* Deferred work
* Recommended Phase 09 scope

⸻

Future Enhancements

Potential additions after Phase 08:

* Wear OS approvals
* Voice approvals
* Rich notification actions
* Scheduled approvals
* Multi-user approvals
* Team review queues
* AI-generated executive summaries
* Cross-device task handoff

These enhancements should build upon the task model established in this phase.

⸻

Design Notes

2026-07-15

Phase 08 fundamentally changes Relay Mobile’s role. Instead of acting primarily as a remote terminal, the application becomes a mobile operations center for supervising autonomous AI development work.

The terminal remains an important escape hatch, but the preferred interaction model becomes structured tasks, clear progress reporting, human approval at critical decision points, and concise summaries rather than raw terminal output. This same task model will later be shared with Relay Studio, ensuring a consistent experience across mobile and desktop.
