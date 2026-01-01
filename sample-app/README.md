# Allscreenshots Demo Application

A simple web application demonstrating the Allscreenshots Java SDK.

## Prerequisites

- Java 17 or higher
- `ALLSCREENSHOTS_API_KEY` environment variable set

## Running the application

1. Set your API key:

```bash
export ALLSCREENSHOTS_API_KEY=your-api-key
```

2. Run the application:

```bash
./gradlew bootRun
```

3. Open your browser to [http://localhost:8080](http://localhost:8080)

## Usage

1. Enter a URL in the input field
2. Select a device preset from the dropdown
3. Optionally check "Full page" for full-page screenshots
4. Click "Take Screenshot"
5. The screenshot will appear in the result area

## Features

- URL input with validation
- Device selector (Desktop HD, iPhone 14, iPad)
- Full page toggle
- Loading state indicator
- Error handling with user-friendly messages

## Project structure

```
sample-app/
├── src/main/java/com/allscreenshots/demo/
│   ├── DemoApplication.java          # Spring Boot main class
│   ├── config/
│   │   └── AllscreenshotsConfig.java # SDK client configuration
│   └── controller/
│       └── ScreenshotController.java # Web controller
├── src/main/resources/
│   ├── templates/
│   │   └── index.html                # Thymeleaf template
│   └── application.properties        # App configuration
├── build.gradle.kts
└── README.md
```

## License

Apache License 2.0
