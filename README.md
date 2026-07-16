# Relay

Relay is a premium native Android application that provides secure remote access to an OpenCode AI development server.

## Overview
Relay provides a VS Code Remote-like experience for Android, focusing on a fast, beautiful, stable, and highly reliable connection. It connects directly into a tmux session, preserving state across network drops or device locks.

In the long term, Relay will evolve into a cross-device AI development platform, including Relay Studio (a native macOS AI IDE) and Relay Mobile. See [VISION.md](docs/VISION.md) for more details.

## Current State
Phase 1: Foundation and UI placeholders have been established.

## Development
- Minimum SDK: 26
- Target SDK: 34
- UI Framework: Jetpack Compose
- Architecture: MVVM + Coroutines/StateFlow + Hilt
