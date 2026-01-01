package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Response from a synchronous compose request.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeResponse {

    @JsonProperty("url")
    private String url;

    @JsonProperty("storageUrl")
    private String storageUrl;

    @JsonProperty("expiresAt")
    private OffsetDateTime expiresAt;

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("format")
    private String format;

    @JsonProperty("fileSize")
    private Long fileSize;

    @JsonProperty("renderTimeMs")
    private Long renderTimeMs;

    @JsonProperty("layout")
    private String layout;

    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    public ComposeResponse() {
    }

    public String getUrl() { return url; }
    public String getStorageUrl() { return storageUrl; }
    public OffsetDateTime getExpiresAt() { return expiresAt; }
    public Integer getWidth() { return width; }
    public Integer getHeight() { return height; }
    public String getFormat() { return format; }
    public Long getFileSize() { return fileSize; }
    public Long getRenderTimeMs() { return renderTimeMs; }
    public String getLayout() { return layout; }
    public Map<String, Object> getMetadata() { return metadata; }
}
