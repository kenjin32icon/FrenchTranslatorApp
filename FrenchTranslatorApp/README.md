# French Translator App

An offline, lightweight Android application for real-time French to English translation with automated response generation.

## Features

- Real-time audio capture of French conversations
- Offline French to English translation
- Automated response generation
- Lightweight and efficient processing
- User-friendly interface

## Technical Stack

- Language: Kotlin
- Audio Processing: Android AudioRecord API
- Translation: TensorFlow Lite
- Architecture: MVVM (Model-View-ViewModel)

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/frenchtranslator/
│   │   │   ├── data/          # Data handling and models
│   │   │   ├── ui/            # Activities and Fragments
│   │   │   ├── viewmodel/     # ViewModels
│   │   │   └── utils/         # Utility classes
│   │   ├── res/               # Resources
│   │   └── assets/            # ML models and assets
└── build.gradle
```

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio
3. Sync project with Gradle files
4. Build and run the application

## Requirements

- Android Studio Arctic Fox or newer
- Minimum SDK: API 24 (Android 7.0)
- Gradle 7.0+
- Device with microphone support

## License

MIT License
