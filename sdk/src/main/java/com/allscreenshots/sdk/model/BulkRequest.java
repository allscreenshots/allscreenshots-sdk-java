package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Request to create a bulk screenshot job.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * BulkRequest request = BulkRequest.builder()
 *     .addUrl("https://example.com")
 *     .addUrl("https://github.com")
 *     .defaults(BulkDefaults.builder()
 *         .device("Desktop HD")
 *         .fullPage(true)
 *         .build())
 *     .build();
 * }</pre>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulkRequest {

    @JsonProperty("urls")
    private List<BulkUrlRequest> urls;

    @JsonProperty("defaults")
    private BulkDefaults defaults;

    @JsonProperty("webhookUrl")
    private String webhookUrl;

    @JsonProperty("webhookSecret")
    private String webhookSecret;

    public BulkRequest() {
    }

    private BulkRequest(Builder builder) {
        this.urls = builder.urls;
        this.defaults = builder.defaults;
        this.webhookUrl = builder.webhookUrl;
        this.webhookSecret = builder.webhookSecret;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<BulkUrlRequest> getUrls() {
        return urls;
    }

    public BulkDefaults getDefaults() {
        return defaults;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public String getWebhookSecret() {
        return webhookSecret;
    }

    public static class Builder {
        private List<BulkUrlRequest> urls = new ArrayList<>();
        private BulkDefaults defaults;
        private String webhookUrl;
        private String webhookSecret;

        public Builder urls(List<BulkUrlRequest> urls) {
            this.urls = urls;
            return this;
        }

        public Builder addUrl(BulkUrlRequest url) {
            this.urls.add(url);
            return this;
        }

        public Builder addUrl(String url) {
            this.urls.add(BulkUrlRequest.builder().url(url).build());
            return this;
        }

        public Builder defaults(BulkDefaults defaults) {
            this.defaults = defaults;
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

        public BulkRequest build() {
            if (urls == null || urls.isEmpty()) {
                throw new IllegalArgumentException("At least one URL is required");
            }
            if (urls.size() > 100) {
                throw new IllegalArgumentException("Maximum 100 URLs allowed");
            }
            return new BulkRequest(this);
        }
    }
}
