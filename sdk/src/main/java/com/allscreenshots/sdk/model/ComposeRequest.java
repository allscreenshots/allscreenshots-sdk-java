package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Request to compose multiple screenshots into a single image.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * ComposeRequest request = ComposeRequest.builder()
 *     .addCapture(CaptureItem.builder()
 *         .url("https://example.com")
 *         .device("Desktop HD")
 *         .build())
 *     .addCapture(CaptureItem.builder()
 *         .url("https://github.com")
 *         .device("iPhone 14")
 *         .build())
 *     .output(ComposeOutputConfig.builder()
 *         .layout(LayoutType.GRID)
 *         .columns(2)
 *         .build())
 *     .build();
 * }</pre>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComposeRequest {

    @JsonProperty("captures")
    private List<CaptureItem> captures;

    @JsonProperty("url")
    private String url;

    @JsonProperty("variants")
    private List<VariantConfig> variants;

    @JsonProperty("defaults")
    private CaptureDefaults defaults;

    @JsonProperty("output")
    private ComposeOutputConfig output;

    @JsonProperty("async")
    private Boolean async;

    @JsonProperty("webhookUrl")
    private String webhookUrl;

    @JsonProperty("webhookSecret")
    private String webhookSecret;

    @JsonProperty("capturesMode")
    private Boolean capturesMode;

    @JsonProperty("variantsMode")
    private Boolean variantsMode;

    public ComposeRequest() {
    }

    private ComposeRequest(Builder builder) {
        this.captures = builder.captures;
        this.url = builder.url;
        this.variants = builder.variants;
        this.defaults = builder.defaults;
        this.output = builder.output;
        this.async = builder.async;
        this.webhookUrl = builder.webhookUrl;
        this.webhookSecret = builder.webhookSecret;
        this.capturesMode = builder.capturesMode;
        this.variantsMode = builder.variantsMode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<CaptureItem> getCaptures() { return captures; }
    public String getUrl() { return url; }
    public List<VariantConfig> getVariants() { return variants; }
    public CaptureDefaults getDefaults() { return defaults; }
    public ComposeOutputConfig getOutput() { return output; }
    public Boolean getAsync() { return async; }
    public String getWebhookUrl() { return webhookUrl; }
    public String getWebhookSecret() { return webhookSecret; }
    public Boolean getCapturesMode() { return capturesMode; }
    public Boolean getVariantsMode() { return variantsMode; }

    public static class Builder {
        private List<CaptureItem> captures;
        private String url;
        private List<VariantConfig> variants;
        private CaptureDefaults defaults;
        private ComposeOutputConfig output;
        private Boolean async;
        private String webhookUrl;
        private String webhookSecret;
        private Boolean capturesMode;
        private Boolean variantsMode;

        public Builder captures(List<CaptureItem> captures) {
            this.captures = captures;
            return this;
        }

        public Builder addCapture(CaptureItem capture) {
            if (this.captures == null) {
                this.captures = new ArrayList<>();
            }
            this.captures.add(capture);
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder variants(List<VariantConfig> variants) {
            this.variants = variants;
            return this;
        }

        public Builder addVariant(VariantConfig variant) {
            if (this.variants == null) {
                this.variants = new ArrayList<>();
            }
            this.variants.add(variant);
            return this;
        }

        public Builder defaults(CaptureDefaults defaults) {
            this.defaults = defaults;
            return this;
        }

        public Builder output(ComposeOutputConfig output) {
            this.output = output;
            return this;
        }

        public Builder async(boolean async) {
            this.async = async;
            return this;
        }

        public Builder webhookUrl(String webhookUrl) {
            this.webhookUrl = webhookUrl;
            return this;
        }

        public Builder webhookSecret(String webhookSecret) {
            this.webhookSecret = webhookSecret;
            return this;
        }

        public Builder capturesMode(boolean capturesMode) {
            this.capturesMode = capturesMode;
            return this;
        }

        public Builder variantsMode(boolean variantsMode) {
            this.variantsMode = variantsMode;
            return this;
        }

        public ComposeRequest build() {
            return new ComposeRequest(this);
        }
    }
}
