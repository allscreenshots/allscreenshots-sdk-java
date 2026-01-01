package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * Response for a single schedule execution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleExecutionResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("executedAt")
    private OffsetDateTime executedAt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("resultUrl")
    private String resultUrl;

    @JsonProperty("storageUrl")
    private String storageUrl;

    @JsonProperty("fileSize")
    private Long fileSize;

    @JsonProperty("renderTimeMs")
    private Long renderTimeMs;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("expiresAt")
    private OffsetDateTime expiresAt;

    public ScheduleExecutionResponse() {
    }

    public String getId() { return id; }
    public OffsetDateTime getExecutedAt() { return executedAt; }
    public String getStatus() { return status; }
    public String getResultUrl() { return resultUrl; }
    public String getStorageUrl() { return storageUrl; }
    public Long getFileSize() { return fileSize; }
    public Long getRenderTimeMs() { return renderTimeMs; }
    public String getErrorCode() { return errorCode; }
    public String getErrorMessage() { return errorMessage; }
    public OffsetDateTime getExpiresAt() { return expiresAt; }
}
