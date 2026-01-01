package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Default options applied to all URLs in a bulk screenshot request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulkDefaults {

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

    @JsonProperty("blockAds")
    private Boolean blockAds;

    @JsonProperty("blockCookieBanners")
    private Boolean blockCookieBanners;

    @JsonProperty("blockLevel")
    private BlockLevel blockLevel;

    public BulkDefaults() {
    }

    private BulkDefaults(Builder builder) {
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
        this.blockAds = builder.blockAds;
        this.blockCookieBanners = builder.blockCookieBanners;
        this.blockLevel = builder.blockLevel;
    }

    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public ViewportConfig getViewport() { return viewport; }
    public String getDevice() { return device; }
    public ImageFormat getFormat() { return format; }
    public Boolean getFullPage() { return fullPage; }
    public Integer getQuality() { return quality; }
    public Integer getDelay() { return delay; }
    public String getWaitFor() { return waitFor; }
    public WaitUntil getWaitUntil() { return waitUntil; }
    public Integer getTimeout() { return timeout; }
    public Boolean getDarkMode() { return darkMode; }
    public String getCustomCss() { return customCss; }
    public Boolean getBlockAds() { return blockAds; }
    public Boolean getBlockCookieBanners() { return blockCookieBanners; }
    public BlockLevel getBlockLevel() { return blockLevel; }

    public static class Builder {
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
        private Boolean blockAds;
        private Boolean blockCookieBanners;
        private BlockLevel blockLevel;

        public Builder viewport(ViewportConfig viewport) { this.viewport = viewport; return this; }
        public Builder device(String device) { this.device = device; return this; }
        public Builder format(ImageFormat format) { this.format = format; return this; }
        public Builder fullPage(boolean fullPage) { this.fullPage = fullPage; return this; }
        public Builder quality(int quality) { this.quality = quality; return this; }
        public Builder delay(int delay) { this.delay = delay; return this; }
        public Builder waitFor(String waitFor) { this.waitFor = waitFor; return this; }
        public Builder waitUntil(WaitUntil waitUntil) { this.waitUntil = waitUntil; return this; }
        public Builder timeout(int timeout) { this.timeout = timeout; return this; }
        public Builder darkMode(boolean darkMode) { this.darkMode = darkMode; return this; }
        public Builder customCss(String customCss) { this.customCss = customCss; return this; }
        public Builder blockAds(boolean blockAds) { this.blockAds = blockAds; return this; }
        public Builder blockCookieBanners(boolean blockCookieBanners) { this.blockCookieBanners = blockCookieBanners; return this; }
        public Builder blockLevel(BlockLevel blockLevel) { this.blockLevel = blockLevel; return this; }

        public BulkDefaults build() {
            return new BulkDefaults(this);
        }
    }
}
