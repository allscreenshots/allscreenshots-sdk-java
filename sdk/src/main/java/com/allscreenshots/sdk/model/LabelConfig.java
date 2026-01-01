package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Configuration for labels in composed screenshots.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabelConfig {

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("position")
    private String position;

    @JsonProperty("fontSize")
    private Integer fontSize;

    @JsonProperty("fontColor")
    private String fontColor;

    @JsonProperty("backgroundColor")
    private String backgroundColor;

    @JsonProperty("padding")
    private Integer padding;

    public LabelConfig() {
    }

    private LabelConfig(Builder builder) {
        this.enabled = builder.enabled;
        this.position = builder.position;
        this.fontSize = builder.fontSize;
        this.fontColor = builder.fontColor;
        this.backgroundColor = builder.backgroundColor;
        this.padding = builder.padding;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Boolean getEnabled() { return enabled; }
    public String getPosition() { return position; }
    public Integer getFontSize() { return fontSize; }
    public String getFontColor() { return fontColor; }
    public String getBackgroundColor() { return backgroundColor; }
    public Integer getPadding() { return padding; }

    public static class Builder {
        private Boolean enabled;
        private String position;
        private Integer fontSize;
        private String fontColor;
        private String backgroundColor;
        private Integer padding;

        public Builder enabled(boolean enabled) { this.enabled = enabled; return this; }
        public Builder position(String position) { this.position = position; return this; }
        public Builder fontSize(int fontSize) { this.fontSize = fontSize; return this; }
        public Builder fontColor(String fontColor) { this.fontColor = fontColor; return this; }
        public Builder backgroundColor(String backgroundColor) { this.backgroundColor = backgroundColor; return this; }
        public Builder padding(int padding) { this.padding = padding; return this; }

        public LabelConfig build() {
            return new LabelConfig(this);
        }
    }
}
