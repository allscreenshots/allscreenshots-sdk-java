package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Default options for captures in a compose request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaptureDefaults {

    @JsonProperty("viewport")
    private ViewportConfig viewport;

    @JsonProperty("device")
    private String device;

    @JsonProperty("format")
    private String format;

    @JsonProperty("fullPage")
    private Boolean fullPage;

    @JsonProperty("quality")
    private Integer quality;

    @JsonProperty("delay")
    private Integer delay;

    @JsonProperty("waitFor")
    private String waitFor;

    @JsonProperty("waitUntil")
    private String waitUntil;

    @JsonProperty("timeout")
    private Integer timeout;

    @JsonProperty("darkMode")
    private Boolean darkMode;

    @JsonProperty("customCss")
    private String customCss;

    @JsonProperty("hideSelectors")
    private List<String> hideSelectors;

    @JsonProperty("blockAds")
    private Boolean blockAds;

    @JsonProperty("blockCookieBanners")
    private Boolean blockCookieBanners;

    @JsonProperty("blockLevel")
    private String blockLevel;

    public CaptureDefaults() {
    }

    private CaptureDefaults(Builder builder) {
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
        this.blockAds = builder.blockAds;
        this.blockCookieBanners = builder.blockCookieBanners;
        this.blockLevel = builder.blockLevel;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ViewportConfig getViewport() { return viewport; }
    public String getDevice() { return device; }
    public String getFormat() { return format; }
    public Boolean getFullPage() { return fullPage; }
    public Integer getQuality() { return quality; }
    public Integer getDelay() { return delay; }
    public String getWaitFor() { return waitFor; }
    public String getWaitUntil() { return waitUntil; }
    public Integer getTimeout() { return timeout; }
    public Boolean getDarkMode() { return darkMode; }
    public String getCustomCss() { return customCss; }
    public List<String> getHideSelectors() { return hideSelectors; }
    public Boolean getBlockAds() { return blockAds; }
    public Boolean getBlockCookieBanners() { return blockCookieBanners; }
    public String getBlockLevel() { return blockLevel; }

    public static class Builder {
        private ViewportConfig viewport;
        private String device;
        private String format;
        private Boolean fullPage;
        private Integer quality;
        private Integer delay;
        private String waitFor;
        private String waitUntil;
        private Integer timeout;
        private Boolean darkMode;
        private String customCss;
        private List<String> hideSelectors;
        private Boolean blockAds;
        private Boolean blockCookieBanners;
        private String blockLevel;

        public Builder viewport(ViewportConfig viewport) { this.viewport = viewport; return this; }
        public Builder device(String device) { this.device = device; return this; }
        public Builder format(String format) { this.format = format; return this; }
        public Builder fullPage(boolean fullPage) { this.fullPage = fullPage; return this; }
        public Builder quality(int quality) { this.quality = quality; return this; }
        public Builder delay(int delay) { this.delay = delay; return this; }
        public Builder waitFor(String waitFor) { this.waitFor = waitFor; return this; }
        public Builder waitUntil(String waitUntil) { this.waitUntil = waitUntil; return this; }
        public Builder timeout(int timeout) { this.timeout = timeout; return this; }
        public Builder darkMode(boolean darkMode) { this.darkMode = darkMode; return this; }
        public Builder customCss(String customCss) { this.customCss = customCss; return this; }
        public Builder hideSelectors(List<String> hideSelectors) { this.hideSelectors = hideSelectors; return this; }
        public Builder blockAds(boolean blockAds) { this.blockAds = blockAds; return this; }
        public Builder blockCookieBanners(boolean blockCookieBanners) { this.blockCookieBanners = blockCookieBanners; return this; }
        public Builder blockLevel(String blockLevel) { this.blockLevel = blockLevel; return this; }

        public CaptureDefaults build() {
            return new CaptureDefaults(this);
        }
    }
}
