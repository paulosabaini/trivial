# KMP/CMP Migration Checklist

This document provides a step-by-step guide to manually migrate the Trivial app to Kotlin Multiplatform (KMP) and Compose Multiplatform (CMP).

---

## Phase 1: Environment & Versioning

- [x] **Update `libs.versions.toml`**
    - [x] Add `jetbrains-compose` plugin version (e.g., `1.7.3`).
    - [x] Add `kotlin` multiplatform version (matching your current version `2.3.21`).
    - [x] Update `ktor` to use multiplatform-friendly engines (replace `ktor-client-android` with `ktor-client-cio` or `ktor-client-darwin`/`ktor-client-oktop` in source sets).
    - [x] Add `room-sqlite-bundled` for KMP Room support.
    - [ ] Add `skiko` version if needed for custom drawing.

- [ ] **Configure Build Logic**
    - [ ] Create `KmpLibraryConventionPlugin.kt` in `build-logic`.
    - [ ] Define `commonMain`, `androidMain`, and `iosMain` source sets in the plugin.
    - [ ] Create `KmpLibraryComposeConventionPlugin.kt` for UI modules.

---

## Phase 2: Core Modules Migration

### 1. `:core:common`
- [ ] Convert to KMP module (apply KMP plugin).
- [ ] Move `TriviaCategory`, `TriviaDifficulty`, etc., from `src/main/java` to `src/commonMain/kotlin`.
- [ ] Update imports across the project.

### 2. `:core:network`
- [ ] Convert to KMP module.
- [ ] Replace `Android` engine in `HttpClient` with a multiplatform setup:
    ```kotlin
    // commonMain
    fun provideHttpClient(engine: HttpClientEngine): HttpClient { ... }
    ```
- [ ] Implement `actual` engine providers in `androidMain` (OkHttp/Android) and `iosMain` (Darwin).
- [ ] Replace `slf4j-android` with a KMP-friendly logger like `Napier` or `Kermit`.

### 3. `:core:database`
- [ ] Convert to KMP module.
- [ ] Update `AppDatabase` to use KMP Room annotations.
- [ ] Implement `expect`/`actual` for `RoomDatabase.Builder`:
    - `androidMain`: Uses `Context`.
    - `iosMain`: Uses `NSHomeDirectory()`.
- [ ] Use `SQLiteDriver` (bundled) in the builder.

### 4. `:core:ui`
- [ ] Convert to KMP module (using Compose Multiplatform).
- [ ] Move all components (`TrivialButton`, `TrivialTopAppBar`, etc.) to `commonMain`.
- [ ] Move images/drawables from `res/drawable` to `composeResources/drawable`.
- [ ] Replace `androidx.compose.ui.res.painterResource` with `org.jetbrains.compose.resources.painterResource`.

---

## Phase 3: Feature Modules Migration

- [ ] **`:feature:home`**
    - [ ] Convert to KMP.
    - [ ] Move `HomeScreen.kt` and ViewModels to `commonMain`.
    - [ ] Update Koin annotations for KMP compatibility.
- [ ] **`:feature:quiz`**
    - [ ] Convert to KMP.
    - [ ] Move Quiz logic, domain, and UI to `commonMain`.
    - [ ] Migrate `strings.xml` to `composeResources/values/strings.xml`.
    - [ ] Replace `stringResource(R.string...)` with `Res.string...`.

---

## Phase 4: Application Entry Point

- [ ] **Create `:composeApp` module**
    - [ ] This will host the shared `App()` composable.
    - [ ] Move `NavHostContainer.kt` here.
    - [ ] Move the main `App` entry point (Koin initialization) here for shared logic.

- [ ] **Refactor `:app` (Android)**
    - [ ] Keep `MainActivity`.
    - [ ] Call the shared `App()` from `setContent`.
    - [ ] Keep Android-specific initializations (e.g., Firebase, if any).

---

## Phase 5: iOS Integration

- [ ] **Configure `iosMain` in `:composeApp`**
    - [ ] Create `MainViewController.kt`:
    ```kotlin
    fun MainViewController() = ComposeUIViewController { App() }
    ```
- [ ] **Create Xcode Project**
    - [ ] Add a basic SwiftUI app.
    - [ ] Integrate the shared KMP framework using CocoaPods or Swift Package Manager (KMP native distribution).
    - [ ] Call `MainViewController` from Swift.

---

## Phase 6: Verification

- [ ] **Run Unit Tests**: `./gradlew allTests`
- [ ] **Build Android**: `./gradlew :app:assembleDebug`
- [ ] **Build iOS**: Run from Xcode or `./gradlew :composeApp:iosDeploy`
- [ ] **UI Check**: Verify that `composeResources` load correctly on both platforms.
