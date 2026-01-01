# Allscreenshots Java SDK

Official Java SDK for the [Allscreenshots](https://allscreenshots.com) API - capture screenshots programmatically.

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("com.allscreenshots.sdk:allscreenshots-sdk:1.0.0")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'com.allscreenshots.sdk:allscreenshots-sdk:1.0.0'
}
```

### Maven

```xml
<dependency>
    <groupId>com.allscreenshots.sdk</groupId>
    <artifactId>allscreenshots-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick start

```java
import com.allscreenshots.sdk.client.AllscreenshotsClient;
import com.allscreenshots.sdk.model.ScreenshotRequest;

// Create client (reads API key from ALLSCREENSHOTS_API_KEY environment variable)
AllscreenshotsClient client = AllscreenshotsClient.builder().build();

// Or provide API key explicitly
AllscreenshotsClient client = AllscreenshotsClient.builder()
    .apiKey("your-api-key")
    .build();

// Capture a screenshot
byte[] image = client.screenshots().capture(
    ScreenshotRequest.builder()
        .url("https://example.com")
        .device("Desktop HD")
        .fullPage(true)
        .build()
);

// Save to file
Files.write(Path.of("screenshot.png"), image);
```

## Configuration

```java
AllscreenshotsClient client = AllscreenshotsClient.builder()
    .apiKey("your-api-key")                      // API key (or set ALLSCREENSHOTS_API_KEY env var)
    .baseUrl("https://api.allscreenshots.com")   // Custom API URL (optional)
    .connectTimeout(Duration.ofSeconds(30))       // Connection timeout
    .readTimeout(Duration.ofMinutes(2))           // Read timeout for long operations
    .writeTimeout(Duration.ofSeconds(30))         // Write timeout
    .retryConfig(RetryConfig.builder()
        .maxRetries(3)                            // Number of retries
        .initialDelayMs(1000)                     // Initial retry delay
        .maxDelayMs(30000)                        // Maximum retry delay
        .multiplier(2.0)                          // Exponential backoff multiplier
        .build())
    .enableLogging(false)                         // Enable HTTP logging (debug)
    .build();
```

## API reference

### Screenshots

#### Capture screenshot (synchronous)

```java
byte[] image = client.screenshots().capture(
    ScreenshotRequest.builder()
        .url("https://example.com")              // Required: URL to capture
        .device("Desktop HD")                    // Device preset
        .fullPage(true)                          // Capture entire page
        .format(ImageFormat.PNG)                 // Output format
        .quality(90)                             // JPEG/WEBP quality (1-100)
        .delay(1000)                             // Wait before capture (ms)
        .waitFor("#content")                     // Wait for CSS selector
        .waitUntil(WaitUntil.NETWORK_IDLE)       // Wait condition
        .timeout(30000)                          // Timeout (ms)
        .darkMode(true)                          // Enable dark mode
        .customCss("body { background: white; }")// Inject CSS
        .addHideSelector(".ad")                  // Hide elements
        .selector("#main")                       // Capture specific element
        .blockAds(true)                          // Block ads
        .blockCookieBanners(true)                // Block cookie banners
        .blockLevel(BlockLevel.PRO)              // Blocking level
        .build()
);
```

#### Capture screenshot (asynchronous)

```java
// Start async capture
AsyncJobCreatedResponse job = client.screenshots().captureAsync(
    ScreenshotRequest.builder()
        .url("https://example.com")
        .build()
);

System.out.println("Job ID: " + job.getId());

// Poll for completion
JobResponse status;
do {
    Thread.sleep(1000);
    status = client.screenshots().getJob(job.getId());
} while (!status.isTerminal());

if (status.isCompleted()) {
    byte[] image = client.screenshots().getJobResult(job.getId());
    Files.write(Path.of("screenshot.png"), image);
}
```

#### List and manage jobs

```java
// List all jobs
List<JobResponse> jobs = client.screenshots().listJobs();

// Get job status
JobResponse job = client.screenshots().getJob("job-id");

// Cancel a job
JobResponse cancelled = client.screenshots().cancelJob("job-id");
```

### Bulk screenshots

```java
// Create bulk job
BulkResponse bulk = client.bulk().create(
    BulkRequest.builder()
        .addUrl("https://example.com")
        .addUrl("https://github.com")
        .addUrl(BulkUrlRequest.builder()
            .url("https://google.com")
            .options(BulkUrlOptions.builder()
                .device("iPhone 14")
                .fullPage(true)
                .build())
            .build())
        .defaults(BulkDefaults.builder()
            .device("Desktop HD")
            .format(ImageFormat.PNG)
            .build())
        .build()
);

// Get bulk job status
BulkStatusResponse status = client.bulk().getStatus(bulk.getId());

// List all bulk jobs
List<BulkJobSummary> jobs = client.bulk().list();

// Cancel bulk job
client.bulk().cancel(bulk.getId());
```

### Compose (multi-screenshot layouts)

```java
// Create composed image from multiple URLs
ComposeResponse compose = client.compose().create(
    ComposeRequest.builder()
        .addCapture(CaptureItem.builder()
            .url("https://example.com")
            .device("Desktop HD")
            .label("Desktop")
            .build())
        .addCapture(CaptureItem.builder()
            .url("https://example.com")
            .device("iPhone 14")
            .label("Mobile")
            .build())
        .output(ComposeOutputConfig.builder()
            .layout(LayoutType.GRID)
            .columns(2)
            .spacing(20)
            .padding(20)
            .background("#ffffff")
            .build())
        .build()
);

System.out.println("Composed image URL: " + compose.getUrl());
```

### Scheduled screenshots

```java
// Create schedule
ScheduleResponse schedule = client.schedules().create(
    CreateScheduleRequest.builder()
        .name("Daily homepage capture")
        .url("https://example.com")
        .schedule("0 9 * * *")                   // Cron expression: 9 AM daily
        .timezone("America/New_York")
        .options(ScheduleScreenshotOptions.builder()
            .device("Desktop HD")
            .fullPage(true)
            .build())
        .retentionDays(30)
        .build()
);

// List schedules
ScheduleListResponse schedules = client.schedules().list();

// Update schedule
client.schedules().update(schedule.getId(),
    UpdateScheduleRequest.builder()
        .schedule("0 */6 * * *")                 // Every 6 hours
        .build()
);

// Pause/resume schedule
client.schedules().pause(schedule.getId());
client.schedules().resume(schedule.getId());

// Trigger manually
client.schedules().trigger(schedule.getId());

// Get history
ScheduleHistoryResponse history = client.schedules().getHistory(schedule.getId());

// Delete schedule
client.schedules().delete(schedule.getId());
```

### Usage and quota

```java
// Get usage statistics
UsageResponse usage = client.usage().getUsage();
System.out.println("Tier: " + usage.getTier());
System.out.println("Screenshots this period: " + usage.getCurrentPeriod().getScreenshotsCount());

// Get quota status
QuotaStatusResponse quota = client.usage().getQuota();
System.out.println("Remaining: " + quota.getScreenshots().getRemaining());
System.out.println("Percent used: " + quota.getScreenshots().getPercentUsed() + "%");
```

## Device presets

Common device presets:
- `Desktop HD` - 1920x1080
- `Desktop Large` - 2560x1440
- `Laptop` - 1366x768
- `iPhone 14` - 390x844
- `iPhone 14 Pro Max` - 430x932
- `iPad` - 768x1024
- `iPad Pro` - 1024x1366

## Error handling

```java
import com.allscreenshots.sdk.exception.*;

try {
    byte[] image = client.screenshots().capture(request);
} catch (ValidationException e) {
    // Invalid request parameters (400)
    System.err.println("Validation error: " + e.getMessage());
    System.err.println("Error code: " + e.getErrorCode());
} catch (AuthenticationException e) {
    // Invalid or missing API key (401/403)
    System.err.println("Auth error: " + e.getMessage());
} catch (RateLimitException e) {
    // Rate limit exceeded (429)
    System.err.println("Rate limited. Retry after: " + e.getRetryAfter() + " seconds");
} catch (ApiException e) {
    // Other API errors (5xx)
    System.err.println("API error: " + e.getMessage());
    System.err.println("Status code: " + e.getStatusCode());
} catch (NetworkException e) {
    // Network/connection errors
    System.err.println("Network error: " + e.getMessage());
} catch (AllscreenshotsException e) {
    // Base exception for all SDK errors
    System.err.println("Error: " + e.getMessage());
}
```

## Retry behavior

The SDK automatically retries transient failures:
- Network errors
- Rate limit errors (429)
- Server errors (5xx)

Default configuration: 3 retries with exponential backoff (1s, 2s, 4s).

Disable retries:
```java
AllscreenshotsClient client = AllscreenshotsClient.builder()
    .retryConfig(RetryConfig.noRetries())
    .build();
```

## Requirements

- Java 17 or higher

## License

Apache License 2.0 - see [LICENSE](LICENSE) for details.
