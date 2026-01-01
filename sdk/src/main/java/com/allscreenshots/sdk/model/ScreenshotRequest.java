package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Request parameters for capturing a screenshot.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * ScreenshotRequest request = ScreenshotRequest.builder()
 *     .url("https://example.com")
 *     .device("Desktop HD")
 *     .fullPage(true)
 *     .format(ImageFormat.PNG)
 *     .build();
 * }</pre>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenshotRequest {

    @JsonProperty("url")
    private String url;

    @JsonProperty("viewport")
    private ViewportConfig viewport;

    @JsonProperty("device")
    private String device;

    @JsonProperty("format")
    private ImageFormat format;

    @JsonProperty("fullPage")
    private Boolean fullPage;

    @JsonProperty("quality")
    private Integer quality;

    @JsonProperty("delay")
    private Integer delay;

    @JsonProperty("waitFor")
    private String waitFor;

    @JsonProperty("waitUntil")
    private WaitUntil waitUntil;

    @JsonProperty("timeout")
    private Integer timeout;

    @JsonProperty("darkMode")
    private Boolean darkMode;

    @JsonProperty("customCss")
    private String customCss;

    @JsonProperty("hideSelectors")
    private List<String> hideSelectors;

    @JsonProperty("selector")
    private String selector;

    @JsonProperty("blockAds")
    private Boolean blockAds;

    @JsonProperty("blockCookieBanners")
    private Boolean blockCookieBanners;

    @JsonProperty("blockLevel")
    private BlockLevel blockLevel;

    @JsonProperty("webhookUrl")
    private String webhookUrl;

    @JsonProperty("webhookSecret")
    private String webhookSecret;

    @JsonProperty("responseType")
    private ResponseType responseType;

    public ScreenshotRequest() {
    }

    private ScreenshotRequest(Builder builder) {
        this.url = builder.url;
        this.viewport = builder.viewport;
        this.device = builder.device;
        this.format = builder.format;
        this.fullPage = builder.fullPage;
        this.quality = builder.quality;
        this.delay = builder.delay;
        this.waitFor = builder.waitFor;
        this.waitUntil = builder.waitUntil;
        this.timeout = builder.timeout;
        this.darkMode = builder.darkMode;
        this.customCss = builder.customCss;
        this.hideSelectors = builder.hideSelectors;
        this.selector = builder.selector;
        this.blockAds = builder.blockAds;
        this.blockCookieBanners = builder.blockCookieBanners;
        this.blockLevel = builder.blockLevel;
        this.webhookUrl = builder.webhookUrl;
        this.webhookSecret = builder.webhookSecret;
        this.responseType = builder.responseType;
    }

    public static Builder builder() {
        return new Builder();
    }

    // Getters

    public String getUrl() {
        return url;
    }

    public ViewportConfig getViewport() {
        return viewport;
    }

    public String getDevice() {
        return device;
    }

    public ImageFormat getFormat() {
        return format;
    }

    public Boolean getFullPage() {
        return fullPage;
    }

    public Integer getQuality() {
        return quality;
    }

    public Integer getDelay() {
        return delay;
    }

    public String getWaitFor() {
        return waitFor;
    }

    public WaitUntil getWaitUntil() {
        return waitUntil;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Boolean getDarkMode() {
        return darkMode;
    }

    public String getCustomCss() {
        return customCss;
    }

    public List<String> getHideSelectors() {
        return hideSelectors;
    }

    public String getSelector() {
        return selector;
    }

    public Boolean getBlockAds() {
        return blockAds;
    }

    public Boolean getBlockCookieBanners() {
        return blockCookieBanners;
    }

    public BlockLevel getBlockLevel() {
        return blockLevel;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public String getWebhookSecret() {
        return webhookSecret;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * Builder for {@link ScreenshotRequest}.
     */
    public static class Builder {
        private String url;
        private ViewportConfig viewport;
        private String device;
        private ImageFormat format;
        private Boolean fullPage;
        private Integer quality;
        private Integer delay;
        private String waitFor;
        private WaitUntil waitUntil;
        private Integer timeout;
        private Boolean darkMode;
        private String customCss;
        private List<String> hideSelectors;
        private String selector;
        private Boolean blockAds;
        private Boolean blockCookieBanners;
        private BlockLevel blockLevel;
        private String webhookUrl;
        private String webhookSecret;
        private ResponseType responseType;

        /**
         * Sets the URL to capture (required).
         *
         * @param url the target URL (must start with http:// or https://)
         * @return this builder
         */
        public Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         * Sets custom viewport dimensions.
         *
         * @param viewport the viewport configuration
         * @return this builder
         */
        public Builder viewport(ViewportConfig viewport) {
            this.viewport = viewport;
            return this;
        }

        /**
         * Sets the device preset (e.g., "Desktop HD", "iPhone 14", "iPad").
         *
         * @param device the device preset name
         * @return this builder
         */
        public Builder device(String device) {
            this.device = device;
            return this;
        }

        /**
         * Sets the output image format.
         *
         * @param format the image format
         * @return this builder
         */
        public Builder format(ImageFormat format) {
            this.format = format;
            return this;
        }

        /**
         * Enables full-page screenshot capture.
         *
         * @param fullPage true to capture the entire page
         * @return this builder
         */
        public Builder fullPage(boolean fullPage) {
            this.fullPage = fullPage;
            return this;
        }

        /**
         * Sets the image quality (for JPEG/WEBP formats).
         *
         * @param quality the quality (1-100)
         * @return this builder
         */
        public Builder quality(int quality) {
            this.quality = quality;
            return this;
        }

        /**
         * Sets the delay before capture in milliseconds.
         *
         * @param delay the delay (0-30000)
         * @return this builder
         */
        public Builder delay(int delay) {
            this.delay = delay;
            return this;
        }

        /**
         * Sets a CSS selector to wait for before capturing.
         *
         * @param waitFor the CSS selector
         * @return this builder
         */
        public Builder waitFor(String waitFor) {
            this.waitFor = waitFor;
            return this;
        }

        /**
         * Sets when to consider navigation complete.
         *
         * @param waitUntil the wait condition
         * @return this builder
         */
        public Builder waitUntil(WaitUntil waitUntil) {
            this.waitUntil = waitUntil;
            return this;
        }

        /**
         * Sets the timeout in milliseconds.
         *
         * @param timeout the timeout (1000-60000)
         * @return this builder
         */
        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        /**
         * Enables dark mode for the page.
         *
         * @param darkMode true to enable dark mode
         * @return this builder
         */
        public Builder darkMode(boolean darkMode) {
            this.darkMode = darkMode;
            return this;
        }

        /**
         * Sets custom CSS to inject into the page.
         *
         * @param customCss the CSS code (max 10000 chars)
         * @return this builder
         */
        public Builder customCss(String customCss) {
            this.customCss = customCss;
            return this;
        }

        /**
         * Sets CSS selectors for elements to hide.
         *
         * @param hideSelectors the selectors (max 50)
         * @return this builder
         */
        public Builder hideSelectors(List<String> hideSelectors) {
            this.hideSelectors = hideSelectors;
            return this;
        }

        /**
         * Adds a CSS selector for an element to hide.
         *
         * @param selector the CSS selector
         * @return this builder
         */
        public Builder addHideSelector(String selector) {
            if (this.hideSelectors == null) {
                this.hideSelectors = new ArrayList<>();
            }
            this.hideSelectors.add(selector);
            return this;
        }

        /**
         * Sets a CSS selector to capture a specific element.
         *
         * @param selector the CSS selector (max 500 chars)
         * @return this builder
         */
        public Builder selector(String selector) {
            this.selector = selector;
            return this;
        }

        /**
         * Enables ad blocking.
         *
         * @param blockAds true to block ads
         * @return this builder
         */
        public Builder blockAds(boolean blockAds) {
            this.blockAds = blockAds;
            return this;
        }

        /**
         * Enables cookie banner blocking.
         *
         * @param blockCookieBanners true to block cookie banners
         * @return this builder
         */
        public Builder blockCookieBanners(boolean blockCookieBanners) {
            this.blockCookieBanners = blockCookieBanners;
            return this;
        }

        /**
         * Sets the blocking level for ads and trackers.
         *
         * @param blockLevel the blocking level
         * @return this builder
         */
        public Builder blockLevel(BlockLevel blockLevel) {
            this.blockLevel = blockLevel;
            return this;
        }

        /**
         * Sets a webhook URL for async notifications.
         *
         * @param webhookUrl the webhook URL
         * @return this builder
         */
        public Builder webhookUrl(String webhookUrl) {
            this.webhookUrl = webhookUrl;
            return this;
        }

        /**
         * Sets a secret for webhook signature verification.
         *
         * @param webhookSecret the webhook secret (max 255 chars)
         * @return this builder
         */
        public Builder webhookSecret(String webhookSecret) {
            this.webhookSecret = webhookSecret;
            return this;
        }

        /**
         * Sets the response type (BINARY or JSON).
         *
         * @param responseType the response type
         * @return this builder
         */
        public Builder responseType(ResponseType responseType) {
            this.responseType = responseType;
            return this;
        }

        /**
         * Builds the screenshot request.
         *
         * @return the built {@link ScreenshotRequest}
         * @throws IllegalArgumentException if url is null or empty
         */
        public ScreenshotRequest build() {
            if (url == null || url.isBlank()) {
                throw new IllegalArgumentException("URL is required");
            }
            return new ScreenshotRequest(this);
        }
    }
}
