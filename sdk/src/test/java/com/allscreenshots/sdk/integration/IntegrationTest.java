package com.allscreenshots.sdk.integration;

import com.allscreenshots.sdk.client.AllscreenshotsClient;
import com.allscreenshots.sdk.exception.AllscreenshotsException;
import com.allscreenshots.sdk.exception.ValidationException;
import com.allscreenshots.sdk.model.ScreenshotRequest;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IntegrationTest {

    private static AllscreenshotsClient client;
    private static final List<TestResult> testResults = new ArrayList<>();
    private static final String SDK_VERSION = "1.0.0";
    private static final String SDK_NAME = "allscreenshots-sdk-java";

    @BeforeAll
    static void setUp() {
        String apiKey = System.getenv("ALLSCREENSHOTS_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            fail("ALLSCREENSHOTS_API_KEY environment variable is not set");
        }

        client = AllscreenshotsClient.builder()
                .apiKey(apiKey)
                .readTimeout(Duration.ofMinutes(2))
                .build();
    }

    @AfterAll
    static void generateReport() throws IOException {
        String report = generateHtmlReport();
        Path reportPath = Path.of("build", "reports", "integration-tests", "test-report.html");
        Files.createDirectories(reportPath.getParent());
        Files.writeString(reportPath, report);
        System.out.println("Integration test report generated at: " + reportPath.toAbsolutePath());
    }

    @Test
    @Order(1)
    @DisplayName("IT-001: Basic Desktop Screenshot")
    void it001_basicDesktopScreenshot() {
        TestResult result = runTest("IT-001", "Basic Desktop Screenshot",
                "https://github.com", "Desktop HD", false);
        testResults.add(result);

        if (!result.passed) {
            fail(result.errorMessage);
        }
        assertTrue(result.imageData != null && result.imageData.length > 0);
    }

    @Test
    @Order(2)
    @DisplayName("IT-002: Basic Mobile Screenshot")
    void it002_basicMobileScreenshot() {
        TestResult result = runTest("IT-002", "Basic Mobile Screenshot",
                "https://github.com", "iPhone 14", false);
        testResults.add(result);

        if (!result.passed) {
            fail(result.errorMessage);
        }
        assertTrue(result.imageData != null && result.imageData.length > 0);
    }

    @Test
    @Order(3)
    @DisplayName("IT-003: Basic Tablet Screenshot")
    void it003_basicTabletScreenshot() {
        TestResult result = runTest("IT-003", "Basic Tablet Screenshot",
                "https://github.com", "iPad", false);
        testResults.add(result);

        if (!result.passed) {
            fail(result.errorMessage);
        }
        assertTrue(result.imageData != null && result.imageData.length > 0);
    }

    @Test
    @Order(4)
    @DisplayName("IT-004: Full Page Desktop")
    void it004_fullPageDesktop() {
        TestResult result = runTest("IT-004", "Full Page Desktop",
                "https://github.com", "Desktop HD", true);
        testResults.add(result);

        if (!result.passed) {
            fail(result.errorMessage);
        }
        assertTrue(result.imageData != null && result.imageData.length > 0);
    }

    @Test
    @Order(5)
    @DisplayName("IT-005: Full Page Mobile")
    void it005_fullPageMobile() {
        TestResult result = runTest("IT-005", "Full Page Mobile",
                "https://github.com", "iPhone 14", true);
        testResults.add(result);

        if (!result.passed) {
            fail(result.errorMessage);
        }
        assertTrue(result.imageData != null && result.imageData.length > 0);
    }

    @Test
    @Order(6)
    @DisplayName("IT-006: Complex Page")
    void it006_complexPage() {
        TestResult result = runTest("IT-006", "Complex Page",
                "https://github.com/anthropics/claude-code", "Desktop HD", false);
        testResults.add(result);

        if (!result.passed) {
            fail(result.errorMessage);
        }
        assertTrue(result.imageData != null && result.imageData.length > 0);
    }

    @Test
    @Order(7)
    @DisplayName("IT-007: Invalid URL")
    void it007_invalidUrl() {
        long startTime = System.currentTimeMillis();
        TestResult result = new TestResult();
        result.testId = "IT-007";
        result.testName = "Invalid URL";
        result.url = "not-a-valid-url";
        result.device = "Desktop HD";
        result.fullPage = false;

        try {
            client.screenshots().capture(
                    ScreenshotRequest.builder()
                            .url("not-a-valid-url")
                            .device("Desktop HD")
                            .build()
            );
            result.passed = false;
            result.errorMessage = "Expected validation error but request succeeded";
        } catch (ValidationException e) {
            result.passed = true;
            result.errorMessage = "Validation error as expected: " + e.getMessage();
        } catch (AllscreenshotsException e) {
            // Any error is acceptable for invalid URL
            result.passed = true;
            result.errorMessage = "Error as expected: " + e.getMessage();
        }

        result.executionTimeMs = System.currentTimeMillis() - startTime;
        testResults.add(result);

        assertTrue(result.passed, result.errorMessage);
    }

    @Test
    @Order(8)
    @DisplayName("IT-008: Unreachable URL")
    void it008_unreachableUrl() {
        long startTime = System.currentTimeMillis();
        TestResult result = new TestResult();
        result.testId = "IT-008";
        result.testName = "Unreachable URL";
        result.url = "https://this-domain-does-not-exist-12345.com";
        result.device = "Desktop HD";
        result.fullPage = false;

        try {
            client.screenshots().capture(
                    ScreenshotRequest.builder()
                            .url("https://this-domain-does-not-exist-12345.com")
                            .device("Desktop HD")
                            .build()
            );
            result.passed = false;
            result.errorMessage = "Expected error but request succeeded";
        } catch (AllscreenshotsException e) {
            result.passed = true;
            result.errorMessage = "Error handled gracefully: " + e.getMessage();
        }

        result.executionTimeMs = System.currentTimeMillis() - startTime;
        testResults.add(result);

        assertTrue(result.passed, result.errorMessage);
    }

    private TestResult runTest(String testId, String testName, String url, String device, boolean fullPage) {
        long startTime = System.currentTimeMillis();
        TestResult result = new TestResult();
        result.testId = testId;
        result.testName = testName;
        result.url = url;
        result.device = device;
        result.fullPage = fullPage;

        try {
            byte[] image = client.screenshots().capture(
                    ScreenshotRequest.builder()
                            .url(url)
                            .device(device)
                            .fullPage(fullPage)
                            .build()
            );
            result.passed = true;
            result.imageData = image;
        } catch (AllscreenshotsException e) {
            result.passed = false;
            result.errorMessage = e.getMessage();
        }

        result.executionTimeMs = System.currentTimeMillis() - startTime;
        return result;
    }

    private static String generateHtmlReport() {
        int passed = (int) testResults.stream().filter(r -> r.passed).count();
        int failed = testResults.size() - passed;
        long totalTime = testResults.stream().mapToLong(r -> r.executionTimeMs).sum();
        String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());

        StringBuilder html = new StringBuilder();
        html.append("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Integration Test Report - %s</title>
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; background: #f5f5f5; color: #333; line-height: 1.6; }
                        .container { max-width: 1200px; margin: 0 auto; padding: 20px; }
                        header { background: #1a1a1a; color: white; padding: 30px 20px; margin-bottom: 30px; }
                        header h1 { font-size: 24px; font-weight: 500; margin-bottom: 5px; }
                        header p { color: #888; font-size: 14px; }
                        .summary { display: grid; grid-template-columns: repeat(auto-fit, minmax(150px, 1fr)); gap: 20px; margin-bottom: 30px; }
                        .summary-card { background: white; border-radius: 8px; padding: 20px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
                        .summary-card h3 { font-size: 12px; text-transform: uppercase; color: #888; margin-bottom: 5px; }
                        .summary-card .value { font-size: 32px; font-weight: 600; }
                        .summary-card .value.passed { color: #22c55e; }
                        .summary-card .value.failed { color: #ef4444; }
                        .test-results { background: white; border-radius: 8px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
                        .test-item { border-bottom: 1px solid #eee; }
                        .test-item:last-child { border-bottom: none; }
                        .test-header { display: flex; align-items: center; padding: 15px 20px; cursor: pointer; }
                        .test-header:hover { background: #fafafa; }
                        .badge { display: inline-block; padding: 4px 12px; border-radius: 20px; font-size: 12px; font-weight: 600; margin-right: 15px; }
                        .badge.passed { background: #dcfce7; color: #166534; }
                        .badge.failed { background: #fee2e2; color: #991b1b; }
                        .test-id { font-weight: 600; margin-right: 10px; color: #666; }
                        .test-name { flex: 1; }
                        .test-time { color: #888; font-size: 14px; }
                        .test-details { padding: 0 20px 20px 20px; display: none; }
                        .test-item.expanded .test-details { display: block; }
                        .test-params { display: grid; grid-template-columns: repeat(3, 1fr); gap: 10px; margin-bottom: 15px; background: #f9f9f9; padding: 15px; border-radius: 6px; }
                        .test-params div { font-size: 14px; }
                        .test-params strong { display: block; font-size: 11px; text-transform: uppercase; color: #888; margin-bottom: 3px; }
                        .error-message { background: #fee2e2; color: #991b1b; padding: 15px; border-radius: 6px; font-size: 14px; }
                        .screenshot { max-width: 100%%; border: 1px solid #ddd; border-radius: 6px; margin-top: 15px; }
                        footer { text-align: center; padding: 30px; color: #888; font-size: 14px; }
                    </style>
                </head>
                <body>
                    <header>
                        <div class="container">
                            <h1>%s v%s</h1>
                            <p>Integration Test Report - %s</p>
                        </div>
                    </header>
                    <div class="container">
                        <div class="summary">
                            <div class="summary-card">
                                <h3>Total tests</h3>
                                <div class="value">%d</div>
                            </div>
                            <div class="summary-card">
                                <h3>Passed</h3>
                                <div class="value passed">%d</div>
                            </div>
                            <div class="summary-card">
                                <h3>Failed</h3>
                                <div class="value failed">%d</div>
                            </div>
                            <div class="summary-card">
                                <h3>Duration</h3>
                                <div class="value">%.1fs</div>
                            </div>
                        </div>
                        <div class="test-results">
                """.formatted(SDK_NAME, SDK_NAME, SDK_VERSION, timestamp,
                testResults.size(), passed, failed, totalTime / 1000.0));

        for (TestResult result : testResults) {
            String badge = result.passed ? "passed" : "failed";
            String badgeText = result.passed ? "PASS" : "FAIL";

            html.append("""
                            <div class="test-item" onclick="this.classList.toggle('expanded')">
                                <div class="test-header">
                                    <span class="badge %s">%s</span>
                                    <span class="test-id">%s</span>
                                    <span class="test-name">%s</span>
                                    <span class="test-time">%dms</span>
                                </div>
                                <div class="test-details">
                                    <div class="test-params">
                                        <div><strong>URL</strong>%s</div>
                                        <div><strong>Device</strong>%s</div>
                                        <div><strong>Full page</strong>%s</div>
                                    </div>
                    """.formatted(badge, badgeText, result.testId, result.testName,
                    result.executionTimeMs, result.url, result.device, result.fullPage));

            if (!result.passed && result.errorMessage != null) {
                html.append("""
                                    <div class="error-message">%s</div>
                        """.formatted(escapeHtml(result.errorMessage)));
            }

            if (result.passed && result.imageData != null) {
                String base64Image = Base64.getEncoder().encodeToString(result.imageData);
                html.append("""
                                    <img class="screenshot" src="data:image/png;base64,%s" alt="Screenshot">
                        """.formatted(base64Image));
            }

            html.append("""
                                </div>
                            </div>
                    """);
        }

        html.append("""
                        </div>
                    </div>
                    <footer>
                        <p>OS: %s %s | Java: %s</p>
                    </footer>
                </body>
                </html>
                """.formatted(System.getProperty("os.name"), System.getProperty("os.version"),
                System.getProperty("java.version")));

        return html.toString();
    }

    private static String escapeHtml(String text) {
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

    private static class TestResult {
        String testId;
        String testName;
        String url;
        String device;
        boolean fullPage;
        boolean passed;
        String errorMessage;
        byte[] imageData;
        long executionTimeMs;
    }
}
