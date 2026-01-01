package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Configuration for shadows in composed screenshots.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShadowConfig {

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("blur")
    private Integer blur;

    @JsonProperty("offsetX")
    private Integer offsetX;

    @JsonProperty("offsetY")
    private Integer offsetY;

    @JsonProperty("color")
    private String color;

    public ShadowConfig() {
    }

    private ShadowConfig(Builder builder) {
        this.enabled = builder.enabled;
        this.blur = builder.blur;
        this.offsetX = builder.offsetX;
        this.offsetY = builder.offsetY;
        this.color = builder.color;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Boolean getEnabled() { return enabled; }
    public Integer getBlur() { return blur; }
    public Integer getOffsetX() { return offsetX; }
    public Integer getOffsetY() { return offsetY; }
    public String getColor() { return color; }

    public static class Builder {
        private Boolean enabled;
        private Integer blur;
        private Integer offsetX;
        private Integer offsetY;
        private String color;

        public Builder enabled(boolean enabled) { this.enabled = enabled; return this; }
        public Builder blur(int blur) { this.blur = blur; return this; }
        public Builder offsetX(int offsetX) { this.offsetX = offsetX; return this; }
        public Builder offsetY(int offsetY) { this.offsetY = offsetY; return this; }
        public Builder color(String color) { this.color = color; return this; }

        public ShadowConfig build() {
            return new ShadowConfig(this);
        }
    }
}
