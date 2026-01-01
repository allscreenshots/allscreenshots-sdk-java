package com.allscreenshots.sdk.unit;

import com.allscreenshots.sdk.client.AllscreenshotsClient;
import com.allscreenshots.sdk.exception.*;
import com.allscreenshots.sdk.model.*;
import com.allscreenshots.sdk.util.RetryConfig;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class AllscreenshotsClientTest {

    private MockWebServer mockServer;
    private AllscreenshotsClient client;

    @BeforeEach
    void setUp() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();

        client = AllscreenshotsClient.builder()
                .apiKey("test-api-key")
                .baseUrl(mockServer.url("/").toString().replaceAll("/$", ""))
                .retryConfig(RetryConfig.noRetries())
                .connectTimeout(Duration.ofSeconds(5))
                .readTimeout(Duration.ofSeconds(5))
                .build();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockServer.shutdown();
    }

    @Test
    void captureScreenshotSuccess() throws Exception {
        byte[] imageBytes = new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47};
        okio.Buffer buffer = new okio.Buffer();
        buffer.write(imageBytes);
        mockServer.enqueue(new MockResponse()
                .setBody(buffer)
                .setHeader("Content-Type", "image/png"));

        byte[] result = client.screenshots().capture(
                ScreenshotRequest.builder()
                        .url("https://example.com")
                        .device("Desktop HD")
                        .build()
        );

        assertEquals(4, result.length);

        RecordedRequest request = mockServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/screenshots", request.getPath());
        assertEquals("test-api-key", request.getHeader("X-API-Key"));
        assertTrue(request.getBody().readUtf8().contains("\"url\":\"https://example.com\""));
    }

    @Test
    void captureAsyncScreenshotSuccess() throws Exception {
        mockServer.enqueue(new MockResponse()
                .setBody("""
                        {
                            "id": "job-123",
                            "status": "QUEUED",
                            "statusUrl": "/v1/screenshots/jobs/job-123",
                            "createdAt": "2024-01-15T10:30:00Z"
                        }
                        """)
                .setHeader("Content-Type", "application/json"));

        AsyncJobCreatedResponse response = client.screenshots().captureAsync(
                ScreenshotRequest.builder()
                        .url("https://example.com")
                        .build()
        );

        assertEquals("job-123", response.getId());
        assertEquals("QUEUED", response.getStatus());

        RecordedRequest request = mockServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("/v1/screenshots/async", request.getPath());
    }

    @Test
    void getJobStatus() throws Exception {
        mockServer.enqueue(new MockResponse()
                .setBody("""
                        {
                            "id": "job-123",
                            "status": "COMPLETED",
                            "url": "https://example.com",
                            "resultUrl": "https://storage.example.com/result.png"
                        }
                        """)
                .setHeader("Content-Type", "application/json"));

        JobResponse response = client.screenshots().getJob("job-123");

        assertEquals("job-123", response.getId());
        assertEquals("COMPLETED", response.getStatus());
        assertTrue(response.isCompleted());

        RecordedRequest request = mockServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/screenshots/jobs/job-123", request.getPath());
    }

    @Test
    void handles401AuthenticationError() {
        mockServer.enqueue(new MockResponse()
                .setResponseCode(401)
                .setBody("""
                        {
                            "message": "Invalid API key",
                            "code": "INVALID_API_KEY"
                        }
                        """)
                .setHeader("Content-Type", "application/json"));

        AuthenticationException exception = assertThrows(AuthenticationException.class, () ->
                client.screenshots().capture(
                        ScreenshotRequest.builder()
                                .url("https://example.com")
                                .build()
                )
        );

        assertEquals("Invalid API key", exception.getMessage());
        assertEquals("INVALID_API_KEY", exception.getErrorCode());
        assertEquals(401, exception.getStatusCode());
    }

    @Test
    void handles400ValidationError() {
        mockServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody("""
                        {
                            "message": "Invalid URL format",
                            "code": "INVALID_URL"
                        }
                        """)
                .setHeader("Content-Type", "application/json"));

        ValidationException exception = assertThrows(ValidationException.class, () ->
                client.screenshots().capture(
                        ScreenshotRequest.builder()
                                .url("not-a-url")
                                .build()
                )
        );

        assertEquals("Invalid URL format", exception.getMessage());
        assertEquals("INVALID_URL", exception.getErrorCode());
        assertEquals(400, exception.getStatusCode());
    }

    @Test
    void handles429RateLimit() {
        mockServer.enqueue(new MockResponse()
                .setResponseCode(429)
                .setBody("""
                        {
                            "message": "Rate limit exceeded"
                        }
                        """)
                .setHeader("Content-Type", "application/json")
                .setHeader("Retry-After", "60"));

        RateLimitException exception = assertThrows(RateLimitException.class, () ->
                client.screenshots().capture(
                        ScreenshotRequest.builder()
                                .url("https://example.com")
                                .build()
                )
        );

        assertEquals("Rate limit exceeded", exception.getMessage());
        assertEquals(60L, exception.getRetryAfter());
        assertEquals(429, exception.getStatusCode());
    }

    @Test
    void handles500ServerError() {
        mockServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("""
                        {
                            "message": "Internal server error"
                        }
                        """)
                .setHeader("Content-Type", "application/json"));

        ApiException exception = assertThrows(ApiException.class, () ->
                client.screenshots().capture(
                        ScreenshotRequest.builder()
                                .url("https://example.com")
                                .build()
                )
        );

        assertEquals("Internal server error", exception.getMessage());
        assertEquals(500, exception.getStatusCode());
    }

    @Test
    void usageApiWorks() throws Exception {
        mockServer.enqueue(new MockResponse()
                .setBody("""
                        {
                            "tier": "PRO",
                            "screenshots": {
                                "limit": 10000,
                                "used": 500,
                                "remaining": 9500,
                                "percentUsed": 5
                            }
                        }
                        """)
                .setHeader("Content-Type", "application/json"));

        QuotaStatusResponse response = client.usage().getQuota();

        assertEquals("PRO", response.getTier());
        assertEquals(10000, response.getScreenshots().getLimit());
        assertEquals(500, response.getScreenshots().getUsed());
        assertEquals(9500, response.getScreenshots().getRemaining());
    }
}
