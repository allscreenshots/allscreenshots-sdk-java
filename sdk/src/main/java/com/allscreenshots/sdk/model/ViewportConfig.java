package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Configuration for the viewport (browser window) dimensions.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * ViewportConfig viewport = ViewportConfig.builder()
 *     .width(1920)
 *     .height(1080)
 *     .deviceScaleFactor(2)
 *     .build();
 * }</pre>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewportConfig {

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("deviceScaleFactor")
    private Integer deviceScaleFactor;

    public ViewportConfig() {
    }

    private ViewportConfig(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
        this.deviceScaleFactor = builder.deviceScaleFactor;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns the viewport width in pixels.
     *
     * @return the width (100-4096)
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Returns the viewport height in pixels.
     *
     * @return the height (100-4096)
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Returns the device scale factor (pixel ratio).
     *
     * @return the scale factor (1-3)
     */
    public Integer getDeviceScaleFactor() {
        return deviceScaleFactor;
    }

    /**
     * Builder for {@link ViewportConfig}.
     */
    public static class Builder {
        private Integer width;
        private Integer height;
        private Integer deviceScaleFactor;

        /**
         * Sets the viewport width in pixels.
         *
         * @param width the width (100-4096)
         * @return this builder
         */
        public Builder width(int width) {
            this.width = width;
            return this;
        }

        /**
         * Sets the viewport height in pixels.
         *
         * @param height the height (100-4096)
         * @return this builder
         */
        public Builder height(int height) {
            this.height = height;
            return this;
        }

        /**
         * Sets the device scale factor (pixel ratio).
         *
         * @param deviceScaleFactor the scale factor (1-3)
         * @return this builder
         */
        public Builder deviceScaleFactor(int deviceScaleFactor) {
            this.deviceScaleFactor = deviceScaleFactor;
            return this;
        }

        /**
         * Builds the viewport configuration.
         *
         * @return the built {@link ViewportConfig}
         */
        public ViewportConfig build() {
            return new ViewportConfig(this);
        }
    }
}
