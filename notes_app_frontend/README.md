# Personal Notes - Android App (Declarative Gradle)

Modern light-themed Android app with:
- User authentication (local demo)
- Offline storage using Room
- Full CRUD for notes
- Search by title/content/tags
- Categories management
- Single-activity with Bottom Navigation (Notes, Categories, Settings)
- Floating Action Button to create a note

Build:
- ./gradlew :notes_app_frontend:app:assembleDebug

Run:
- Install on a device/emulator, login with any email/password, start organizing notes.

Notes:
- This project uses Gradle 9 Declarative DSL; only implementation(...) lines were added to app/build.gradle.dcl dependencies per constraints.
