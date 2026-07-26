# Relay Architecture

## Design Principles
- **Compose-First**: All UI is built with Jetpack Compose.
- **MVVM**: Model-View-ViewModel architecture ensures separation of concerns. Note: Where screens remain static in Phase 1, they are implemented honestly as stateless Compose placeholders rather than inventing unnecessary ViewModels.
- **Unidirectional Data Flow**: State flows down, events flow up.
- **Clean Boundaries**: Feature packages encapsulate their own UI, ViewModel, and domain logic.

## Directory Structure
The app module is structured by feature to allow future extraction into multi-module Gradle setups:
- `core`: Shared components (design system, navigation, utility functions, preview demo models).
- `feature`: Independent feature packages (home, terminal, server, settings).
- `data`: Data layer (models, repositories, local persistence).

## Shared Platform Direction
Relay Mobile and the future Relay Studio will share concepts and domain models. The Android domain model must remain clean and avoid tightly coupling core concepts to Android UI classes, allowing for future shared models for workspaces, sessions, agent tasks, and approvals without forcing premature cross-platform code sharing in Phase 1.

## Dependency Injection
Hilt is used for dependency injection throughout the app to inject ViewModels and Repositories.
