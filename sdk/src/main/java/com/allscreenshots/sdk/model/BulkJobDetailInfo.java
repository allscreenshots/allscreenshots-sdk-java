package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * Detailed information about a job in a bulk request.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkJobDetailInfo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("status")
    private String status;

    @JsonProperty("resultUrl")
    private String resultUrl;

    @JsonProperty("storageUrl")
    private String storageUrl;

    @JsonProperty("format")
    private String format;

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("fileSize")
    private Long fileSize;

    @JsonProperty("renderTimeMs")
    private Long renderTimeMs;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("completedAt")
    private OffsetDateTime completedAt;

    public BulkJobDetailInfo() {
    }

    public String getId() { return id; }
    public String getUrl() { return url; }
    public String getStatus() { return status; }
    public String getResultUrl() { return resultUrl; }
    public String getStorageUrl() { return storageUrl; }
    public String getFormat() { return format; }
    public Integer getWidth() { return width; }
    public Integer getHeight() { return height; }
    public Long getFileSize() { return fileSize; }
    public Long getRenderTimeMs() { return renderTimeMs; }
    public String getErrorCode() { return errorCode; }
    public String getErrorMessage() { return errorMessage; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public OffsetDateTime getCompletedAt() { return completedAt; }
}
