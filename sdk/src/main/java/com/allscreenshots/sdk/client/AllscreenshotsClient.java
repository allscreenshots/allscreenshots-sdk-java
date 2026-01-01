package com.allscreenshots.sdk.client;

import com.allscreenshots.sdk.util.RetryConfig;

import java.time.Duration;

/**
 * Main client for interacting with the Allscreenshots API.
 *
 * <p>Create a client using the builder:</p>
 * <pre>{@code
 * AllscreenshotsClient client = AllscreenshotsClient.builder()
 *     .apiKey("your-api-key")
 *     .build();
 *
 * // Or use the API key from environment variable ALLSCREENSHOTS_API_KEY
 * AllscreenshotsClient client = AllscreenshotsClient.builder().build();
 * }</pre>
 *
 * <p>Capture a screenshot:</p>
 * <pre>{@code
 * byte[] image = client.screenshots().capture(
 *     ScreenshotRequest.builder()
 *         .url("https://example.com")
 *         .device("Desktop HD")
 *         .fullPage(true)
 *         .build()
 * );
 * }</pre>
 *
 * @see ScreenshotsApi
 * @see BulkApi
 * @see ComposeApi
 * @see SchedulesApi
 * @see UsageApi
 */
public class AllscreenshotsClient {

    private static final String DEFAULT_BASE_URL = "https://api.allscreenshots.com";
    private static final String API_KEY_ENV_VAR = "ALLSCREENSHOTS_API_KEY";

    private final ScreenshotsApi screenshotsApi;
    private final BulkApi bulkApi;
    private final ComposeApi composeApi;
    private final SchedulesApi schedulesApi;
    private final UsageApi usageApi;

    private AllscreenshotsClient(Builder builder) {
        String apiKey = builder.apiKey;
        if (apiKey == null || apiKey.isBlank()) {
            apiKey = System.getenv(API_KEY_ENV_VAR);
        }

        HttpClient httpClient = new HttpClient(
                builder.baseUrl,
                apiKey,
                builder.connectTimeout,
                builder.readTimeout,
                builder.writeTimeout,
                builder.retryConfig,
                builder.enableLogging
        );

        this.screenshotsApi = new ScreenshotsApi(httpClient);
        this.bulkApi = new BulkApi(httpClient);
        this.composeApi = new ComposeApi(httpClient);
        this.schedulesApi = new SchedulesApi(httpClient);
        this.usageApi = new UsageApi(httpClient);
    }

    /**
     * Creates a new builder for the client.
     *
     * @return a new builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns the screenshots API for capturing single screenshots.
     *
     * @return the screenshots API
     */
    public ScreenshotsApi screenshots() {
        return screenshotsApi;
    }

    /**
     * Returns the bulk API for batch screenshot operations.
     *
     * @return the bulk API
     */
    public BulkApi bulk() {
        return bulkApi;
    }

    /**
     * Returns the compose API for creating combined screenshots.
     *
     * @return the compose API
     */
    public ComposeApi compose() {
        return composeApi;
    }

    /**
     * Returns the schedules API for scheduled screenshots.
     *
     * @return the schedules API
     */
    public SchedulesApi schedules() {
        return schedulesApi;
    }

    /**
     * Returns the usage API for quota and usage information.
     *
     * @return the usage API
     */
    public UsageApi usage() {
        return usageApi;
    }

    /**
     * Builder for creating {@link AllscreenshotsClient} instances.
     */
    public static class Builder {
        private String apiKey;
        private String baseUrl = DEFAULT_BASE_URL;
        private Duration connectTimeout = Duration.ofSeconds(30);
        private Duration readTimeout = Duration.ofSeconds(120);
        private Duration writeTimeout = Duration.ofSeconds(30);
        private RetryConfig retryConfig = RetryConfig.defaultConfig();
        private boolean enableLogging = false;

        /**
         * Sets the API key for authentication.
         *
         * <p>If not set, the client will read from the {@code ALLSCREENSHOTS_API_KEY}
         * environment variable.</p>
         *
         * @param apiKey the API key
         * @return this builder
         */
        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        /**
         * Sets the base URL for the API.
         *
         * <p>Defaults to {@code https://api.allscreenshots.com}</p>
         *
         * @param baseUrl the base URL
         * @return this builder
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Sets the connection timeout.
         *
         * <p>Defaults to 30 seconds.</p>
         *
         * @param connectTimeout the timeout
         * @return this builder
         */
        public Builder connectTimeout(Duration connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        /**
         * Sets the read timeout for responses.
         *
         * <p>Defaults to 120 seconds to accommodate long-running screenshot operations.</p>
         *
         * @param readTimeout the timeout
         * @return this builder
         */
        public Builder readTimeout(Duration readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * Sets the write timeout for requests.
         *
         * <p>Defaults to 30 seconds.</p>
         *
         * @param writeTimeout the timeout
         * @return this builder
         */
        public Builder writeTimeout(Duration writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        /**
         * Sets the retry configuration for transient failures.
         *
         * <p>Defaults to 3 retries with exponential backoff.</p>
         *
         * @param retryConfig the retry configuration
         * @return this builder
         */
        public Builder retryConfig(RetryConfig retryConfig) {
            this.retryConfig = retryConfig;
            return this;
        }

        /**
         * Enables HTTP request/response logging.
         *
         * <p>Warning: This may log sensitive information including API keys.</p>
         *
         * @param enableLogging true to enable logging
         * @return this builder
         */
        public Builder enableLogging(boolean enableLogging) {
            this.enableLogging = enableLogging;
            return this;
        }

        /**
         * Builds the client.
         *
         * @return a new {@link AllscreenshotsClient}
         */
        public AllscreenshotsClient build() {
            return new AllscreenshotsClient(this);
        }
    }
}
