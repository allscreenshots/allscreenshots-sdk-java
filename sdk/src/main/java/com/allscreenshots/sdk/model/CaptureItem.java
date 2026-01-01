package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A single capture item in a compose request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaptureItem {

    @JsonProperty("url")
    private String url;

    @JsonProperty("id")
    private String id;

    @JsonProperty("label")
    private String label;

    @JsonProperty("viewport")
    private ViewportConfig viewport;

    @JsonProperty("device")
    private String device;

    @JsonProperty("fullPage")
    private Boolean fullPage;

    @JsonProperty("darkMode")
    private Boolean darkMode;

    @JsonProperty("delay")
    private Integer delay;

    public CaptureItem() {
    }

    private CaptureItem(Builder builder) {
        this.url = builder.url;
        this.id = builder.id;
        this.label = builder.label;
        this.viewport = builder.viewport;
        this.device = builder.device;
        this.fullPage = builder.fullPage;
        this.darkMode = builder.darkMode;
        this.delay = builder.delay;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getUrl() { return url; }
    public String getId() { return id; }
    public String getLabel() { return label; }
    public ViewportConfig getViewport() { return viewport; }
    public String getDevice() { return device; }
    public Boolean getFullPage() { return fullPage; }
    public Boolean getDarkMode() { return darkMode; }
    public Integer getDelay() { return delay; }

    public static class Builder {
        private String url;
        private String id;
        private String label;
        private ViewportConfig viewport;
        private String device;
        private Boolean fullPage;
        private Boolean darkMode;
        private Integer delay;

        public Builder url(String url) { this.url = url; return this; }
        public Builder id(String id) { this.id = id; return this; }
        public Builder label(String label) { this.label = label; return this; }
        public Builder viewport(ViewportConfig viewport) { this.viewport = viewport; return this; }
        public Builder device(String device) { this.device = device; return this; }
        public Builder fullPage(boolean fullPage) { this.fullPage = fullPage; return this; }
        public Builder darkMode(boolean darkMode) { this.darkMode = darkMode; return this; }
        public Builder delay(int delay) { this.delay = delay; return this; }

        public CaptureItem build() {
            if (url == null || url.isBlank()) {
                throw new IllegalArgumentException("URL is required");
            }
            return new CaptureItem(this);
        }
    }
}
