# NoteMaster

A modern Android note-taking application built with **Clean Architecture** and **Jetpack Compose**.

## 📝 Description

NoteMaster is an Android application demonstrating modern development practices. The project follows **Clean Architecture** principles with a multi-module structure, organized into three layers: **Presentation** (Jetpack Compose UI with ViewModels), **Domain** (business logic and use cases), and **Data** (repository implementations). Built with **Jetpack Compose** and **Material 3** for a modern, declarative UI. Uses **Hilt** for dependency injection and **Navigation Compose** for type-safe navigation.

## 🛠️ Tech Stack

- **UI**: Jetpack Compose with Material 3
- **Architecture**: Clean Architecture (Multi-module)
- **Dependency Injection**: Hilt
- **Navigation**: Navigation Compose
- **Language**: Kotlin
- **Build System**: Gradle (Kotlin DSL)

## 🎯 Project Structure

```
NoteMaster/
├── app/          # Application module
├── presentation/ # UI layer (Compose, ViewModels)
├── domain/       # Business logic layer
└── data/         # Data layer (Repositories, Data Sources)
```

