# DivKit workshop: Mobile Development School 2025

Materials for a hands-on [DivKit](https://github.com/divkit/divkit) workshop I ran for students of Yandex's Mobile Development School in 2025. Students build a small server-driven UI app step by step: a Kotlin backend describes the screen with DivKit's Kotlin DSL, and native Android and iOS apps render it.

## Stages

Each stage is a branch, and each branch builds on the previous one, so you can start from any point or compare two stages with `git diff`.

| Branch | What it adds |
|---|---|
| [`initial_state`](../../tree/initial_state) | Starting point: an empty Spring Boot endpoint `GET /demo`, an Android app that loads a layout from the server, and a minimal iOS app. |
| [`after_part_1`](../../tree/after_part_1) | Backend: a feed screen built with the Kotlin DSL (toolbar, title, button) from a view model, personalized with a `username` parameter and localized via `Accept-Language`. |
| [`after_part_2`](../../tree/after_part_2) | Clients: custom actions. Android (`DivActionHandler`) and iOS (`DivUrlHandler`) handle `sample-action://update` and reload the layout from the server. |
| [`final_apps`](../../tree/final_apps) | Backend: a list of cards built from a reusable template, a search field that filters the cards on the client with DivKit expressions (no server round trip), and a DivKit timer that fires the update action every 3 seconds. |

## Running

```bash
cd backend && ./gradlew bootRun     # serves http://localhost:8080/demo
```

Then open `android/` in Android Studio and run it on an emulator (the app reaches the host at `10.0.2.2:8080`), or open `ios/LightDivkitPlayground.xcodeproj` in Xcode and run it on a simulator.
