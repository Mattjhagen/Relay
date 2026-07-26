Phase 05 — Server Diagnostics & Management

Status: Planned
Priority: High
Estimated Duration: 3–5 development sessions

⸻

Objective

Turn Relay into a real server operations dashboard.

By the end of this phase, users should be able to monitor the health of their development servers, inspect running services, review system resources, and perform safe administrative actions without leaving the app.

All diagnostics should be collected securely over the existing SSH connection established in previous phases.

This phase intentionally avoids introducing a separate server agent. Everything should continue to operate through SSH.

⸻

User Experience Goals

Users should be able to:

* View live server health.
* Monitor CPU, memory, disk, and network usage.
* Check system uptime.
* Inspect running services.
* Monitor OpenCode.
* Monitor Ollama.
* Monitor tmux sessions.
* Review Docker containers (if installed).
* Restart selected services.
* View recent system logs.
* Refresh diagnostics manually or automatically.

Relay should begin feeling like a lightweight Network Operations Center (NOC) for AI development servers.

⸻

Technical Scope

Implement:

* Diagnostics collection
* Command execution framework
* Metrics parser
* Live dashboard
* Refresh scheduling
* Service status monitoring
* Safe administrative actions
* Log viewing
* Command abstraction
* Health indicators

Do not implement:

* Relay Agent
* Push notifications
* Git operations
* File browser
* AI workflow orchestration

⸻

Architecture

Introduce a diagnostics subsystem.

core/
    diagnostics/
        DiagnosticsEngine
        DiagnosticsRepository
        CommandExecutor
        MetricsCollector
        MetricsParser
        HealthEvaluator
        ServiceManager
        LogReader
        RefreshScheduler
data/
    diagnostics/
feature/
    server/

All remote commands must execute through the existing SSH layer.

The diagnostics layer must not establish its own network connections.

⸻

Dashboard

The Server screen should become a real monitoring dashboard.

Sections:

* Server Overview
* Resources
* Services
* AI Runtime
* Storage
* Networking
* Recent Activity

Information should update smoothly without disrupting the UI.

⸻

System Metrics

Collect:

CPU

* Utilization
* Core count
* Load average
* Frequency (where available)

⸻

Memory

* Total
* Used
* Available
* Cached
* Swap

⸻

Disk

For each mounted filesystem:

* Capacity
* Used
* Free
* Usage percentage

Highlight low-space conditions.

⸻

Temperature

Collect:

* CPU temperature
* System temperature
* Fan status (when available)

Gracefully handle systems that do not expose sensors.

⸻

Network

Display:

* IP addresses
* Interface status
* Network throughput
* Active connections
* Latency

Future support may include bandwidth graphs.

⸻

AI Runtime Monitoring

Monitor:

OpenCode

Display:

* Running
* Not running
* Attached session
* Active workspace
* Runtime duration

⸻

Ollama

Display:

* Running
* Installed version
* Loaded models
* Active inference jobs
* GPU usage (when available)
* CPU fallback status

⸻

tmux

Display:

* Active sessions
* Session names
* Creation times
* Attached clients

Allow users to:

* Attach
* Kill
* Create sessions

Prompt for confirmation before destructive actions.

⸻

Docker

If Docker is installed:

Display:

* Running containers
* Stopped containers
* Images
* Container health
* Restart count

Future versions may expose container controls.

⸻

Service Monitoring

Monitor configurable services.

Examples:

* OpenCode
* Ollama
* Docker
* SSH
* Tailscale
* Custom services

Each service should expose:

* Running
* Failed
* Restarting
* Disabled

⸻

Administrative Actions

Allow:

* Restart service
* Stop service
* Start service
* Restart OpenCode
* Restart Ollama
* Restart tmux session

Future actions:

* Reboot server
* Shutdown server

Destructive actions must require explicit confirmation.

⸻

Command Framework

Every remote operation should use typed commands rather than inline shell strings.

Example abstraction:

GetCpuUsageCommand
GetMemoryCommand
GetDiskCommand
RestartServiceCommand
GetTmuxSessionsCommand

This improves testing and future Relay Agent compatibility.

⸻

Refresh Behavior

Support:

* Manual refresh
* Configurable automatic refresh
* Pull-to-refresh
* Suspend refresh while disconnected

Avoid unnecessary SSH traffic.

⸻

Charts

Introduce lightweight charts for:

* CPU history
* Memory history
* Disk history

Maintain rolling history locally during the session.

No long-term historical storage yet.

⸻

Error Handling

Handle:

* Missing commands
* Unsupported Linux distributions
* Missing sensors
* Permission failures
* Command timeouts
* Partial data

A missing metric should never crash the dashboard.

⸻

Security

Administrative actions must:

* Require an active authenticated session.
* Never bypass SSH authentication.
* Respect biometric requirements established earlier.
* Log administrative events locally without exposing sensitive data.

⸻

Performance Goals

Dashboard refresh should:

* Complete in under one second on a LAN where practical.
* Avoid excessive SSH command execution.
* Batch compatible commands when appropriate.
* Minimize battery consumption.

⸻

Accessibility

Support:

* Screen readers
* High contrast
* Accessible charts
* Large touch targets
* Dynamic text

Charts should always include textual summaries.

⸻

Documentation

Update:

* README.md
* ARCHITECTURE.md
* SSH.md

Create:

docs/DIAGNOSTICS.md

Document:

* Diagnostics architecture
* Command framework
* Metrics collection
* Dashboard update strategy
* Administrative safeguards

⸻

Explicit Non-Goals

Do not implement:

* Relay Agent
* Push notifications
* Git
* File browser
* AI planning
* Background daemon
* Long-term metrics storage
* Cloud synchronization

⸻

Acceptance Criteria

Phase 05 is complete when:

* Live server metrics display correctly.
* OpenCode status is visible.
* Ollama status is visible.
* tmux sessions are listed.
* Services can be monitored.
* Safe administrative actions function.
* Dashboard updates smoothly.
* Missing metrics fail gracefully.
* Documentation reflects the diagnostics architecture.

⸻

Verification

Run:

./gradlew assembleDebug
./gradlew lintDebug
./gradlew testDebugUnitTest
./gradlew connectedDebugAndroidTest

Manual verification:

* Test on Ubuntu server.
* Disconnect and reconnect.
* Restart monitored services.
* Simulate missing system utilities.
* Verify low disk warnings.
* Verify charts update correctly.
* Confirm destructive actions require confirmation.
* Validate dashboard responsiveness under load.

Run:

git status

⸻

Completion Report

Provide:

* Build status
* Lint status
* Unit test status
* Instrumentation test status
* Metrics collected
* Commands implemented
* Services monitored
* Dashboard architecture summary
* Performance observations
* Files created
* Files modified
* Deferred work
* Recommended Phase 06 scope

⸻

Future Enhancements

Potential additions after Phase 05:

* Historical metrics database
* GPU monitoring
* SMART disk health
* RAID monitoring
* UPS integration
* Prometheus export
* Grafana-compatible endpoints
* Custom dashboards
* Alert thresholds
* Per-server widgets
* Home screen widgets
* Wear OS monitoring

These enhancements should be implemented in later phases and should not delay completion of Phase 05.

⸻

Design Notes

2026-07-15

Phase 05 expands Relay from a terminal client into an operational control center for AI infrastructure. Diagnostics are intentionally collected through the existing SSH layer rather than introducing a custom server-side agent. This preserves Relay’s privacy-first philosophy while creating a reusable command framework that can later be backed by the optional Relay Agent without requiring changes to the user interface or domain models.
