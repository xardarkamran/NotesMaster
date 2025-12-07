# NoteMaster

A modern Android note-taking application built with **Clean Architecture**, **MVI (Model-View-Intent)** pattern, and **Jetpack Compose**.

## 📝 Description

NoteMaster is an Android application demonstrating modern development practices. The project follows **Clean Architecture** principles with a multi-module structure and implements **MVI architecture** for predictable state management. Built entirely with **Jetpack Compose** and **Material 3**.

## 📱 Screenshots
<div align="center">
  <img src="https://github.com/user-attachments/assets/ca4d225c-eff3-4f4e-a95c-857347dc9673" alt="All Notes" width="200"/>
  <img src="https://github.com/user-attachments/assets/3e94e554-c987-41ea-b3d7-06df6a77738f" alt="Filter Notes" width="200"/>
  <img src="https://github.com/user-attachments/assets/2f50aa79-57d3-487e-9c3a-a05411152b44" alt="Add Notes" width="200"/>
  <img src="https://github.com/user-attachments/assets/f13c9f04-9924-40ef-878f-6d7fca6f36c3" alt="Edit note" width="200"/>
</div>

## ✨ Features

- Create, edit, and delete notes
- Colorful note cards with custom color selection
- Sort notes by Latest, Oldest, Title, or Color
- Staggered grid layout
- Build variants (Dev & Production)

## 🏗️ Architecture

### Clean Architecture
- **Presentation Layer**: Feature-based UI (Intent, State, ViewModel, View)
- **Domain Layer**: Business logic, Use Cases, Models
- **Data Layer**: Repository implementations, Room Database

### MVI Pattern
- **Intent**: Sealed classes for user actions
- **State**: Immutable UI state
- **ViewModel**: Processes intents, manages state
- **View**: Observes state, sends intents

**Data Flow**: `View → Intent → ViewModel → State → View`

## 🛠️ Tech Stack

- **UI**: Jetpack Compose with Material 3
- **Architecture**: Clean Architecture + MVI
- **Dependency Injection**: Hilt
- **Navigation**: Navigation Compose
- **Database**: Room
- **Language**: Kotlin
- **Async**: Kotlin Coroutines & Flow

## 📁 Project Structure

```
NoteMaster/
├── app/                    # Application module
├── presentation/           # Presentation Layer
│   └── ui/
│       ├── all_notes/     # Notes List Feature
│       ├── add_note/      # Add/Edit Note Feature
│       └── shared/        # Shared UI resources
├── domain/                # Domain Layer (Use Cases, Models)
└── data/                  # Data Layer (Room, Repository)
```

## 🚀 Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Build and run

### Build Variants

- **Dev**: `com.navigation.live.NoteMaster.dev` (App name: "NoteMaster Dev")
- **Production**: `com.navigation.live.NoteMaster` (App name: "NoteMaster")

Switch variants: **Build → Select Build Variant**
