package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Configuration for borders in composed screenshots.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorderConfig {

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("color")
    private String color;

    @JsonProperty("radius")
    private Integer radius;

    public BorderConfig() {
    }

    private BorderConfig(Builder builder) {
        this.width = builder.width;
        this.color = builder.color;
        this.radius = builder.radius;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getWidth() { return width; }
    public String getColor() { return color; }
    public Integer getRadius() { return radius; }

    public static class Builder {
        private Integer width;
        private String color;
        private Integer radius;

        public Builder width(int width) { this.width = width; return this; }
        public Builder color(String color) { this.color = color; return this; }
        public Builder radius(int radius) { this.radius = radius; return this; }

        public BorderConfig build() {
            return new BorderConfig(this);
        }
    }
}
