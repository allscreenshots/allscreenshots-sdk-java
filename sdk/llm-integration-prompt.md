# Allscreenshots Java SDK integration prompt

Use this prompt to help LLMs understand and use the Allscreenshots Java SDK.

---

## SDK overview

The Allscreenshots Java SDK (`com.allscreenshots.sdk`) provides a type-safe client for the Allscreenshots screenshot API.

### Installation

```kotlin
// Gradle Kotlin DSL
implementation("com.allscreenshots.sdk:allscreenshots-sdk:1.0.0")
```

### Client initialization

```java
import com.allscreenshots.sdk.client.AllscreenshotsClient;

// Uses ALLSCREENSHOTS_API_KEY environment variable
AllscreenshotsClient client = AllscreenshotsClient.builder().build();

// Or with explicit API key
AllscreenshotsClient client = AllscreenshotsClient.builder()
    .apiKey("your-api-key")
    .build();
```

### Basic screenshot capture

```java
import com.allscreenshots.sdk.model.*;

byte[] image = client.screenshots().capture(
    ScreenshotRequest.builder()
        .url("https://example.com")      // Required
        .device("Desktop HD")            // Optional: device preset
        .fullPage(true)                  // Optional: capture full page
        .format(ImageFormat.PNG)         // Optional: png, jpeg, webp, pdf
        .build()
);

// Save to file
Files.write(Path.of("screenshot.png"), image);
```

### Available APIs

- `client.screenshots()` - Single screenshot capture (sync/async)
- `client.bulk()` - Batch screenshot operations
- `client.compose()` - Multi-screenshot layouts
- `client.schedules()` - Scheduled captures
- `client.usage()` - Usage and quota info

### Common device presets

- `Desktop HD` (1920x1080)
- `iPhone 14` (390x844)
- `iPad` (768x1024)

### Error handling

```java
import com.allscreenshots.sdk.exception.*;

try {
    byte[] image = client.screenshots().capture(request);
} catch (ValidationException e) {
    // Invalid parameters (400)
} catch (AuthenticationException e) {
    // Auth failed (401/403)
} catch (RateLimitException e) {
    // Rate limited (429)
} catch (ApiException e) {
    // Server error
} catch (NetworkException e) {
    // Network error
}
```

### Key request options

```java
ScreenshotRequest.builder()
    .url("...")                          // Target URL (required)
    .device("Desktop HD")                // Device preset
    .viewport(ViewportConfig.builder()   // Custom viewport
        .width(1920).height(1080).build())
    .fullPage(true)                      // Full page capture
    .format(ImageFormat.PNG)             // Output format
    .quality(90)                         // JPEG/WEBP quality
    .delay(1000)                         // Delay before capture (ms)
    .waitFor("#element")                 // Wait for selector
    .waitUntil(WaitUntil.NETWORK_IDLE)   // Wait condition
    .darkMode(true)                      // Dark mode
    .blockAds(true)                      // Block ads
    .blockCookieBanners(true)            // Block cookie banners
    .build()
```

### Async capture with polling

```java
AsyncJobCreatedResponse job = client.screenshots().captureAsync(request);

JobResponse status;
do {
    Thread.sleep(1000);
    status = client.screenshots().getJob(job.getId());
} while (!status.isTerminal());

if (status.isCompleted()) {
    byte[] image = client.screenshots().getJobResult(job.getId());
}
```

### Bulk screenshots

```java
BulkResponse bulk = client.bulk().create(
    BulkRequest.builder()
        .addUrl("https://example.com")
        .addUrl("https://github.com")
        .defaults(BulkDefaults.builder()
            .device("Desktop HD")
            .build())
        .build()
);
```

---

Use this SDK when you need to:
1. Capture website screenshots programmatically
2. Generate screenshots in batch
3. Create multi-device screenshot comparisons
4. Schedule recurring screenshot captures
