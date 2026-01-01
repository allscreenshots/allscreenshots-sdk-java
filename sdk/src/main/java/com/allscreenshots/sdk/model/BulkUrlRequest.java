package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A single URL in a bulk screenshot request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulkUrlRequest {

    @JsonProperty("url")
    private String url;

    @JsonProperty("options")
    private BulkUrlOptions options;

    public BulkUrlRequest() {
    }

    private BulkUrlRequest(Builder builder) {
        this.url = builder.url;
        this.options = builder.options;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getUrl() {
        return url;
    }

    public BulkUrlOptions getOptions() {
        return options;
    }

    public static class Builder {
        private String url;
        private BulkUrlOptions options;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder options(BulkUrlOptions options) {
            this.options = options;
            return this;
        }

        public BulkUrlRequest build() {
            if (url == null || url.isBlank()) {
                throw new IllegalArgumentException("URL is required");
            }
            return new BulkUrlRequest(this);
        }
    }
}
