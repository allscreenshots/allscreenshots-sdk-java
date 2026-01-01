package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Response containing screenshot job information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("url")
    private String url;

    @JsonProperty("resultUrl")
    private String resultUrl;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("startedAt")
    private OffsetDateTime startedAt;

    @JsonProperty("completedAt")
    private OffsetDateTime completedAt;

    @JsonProperty("expiresAt")
    private OffsetDateTime expiresAt;

    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    public JobResponse() {
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public JobStatus getStatusEnum() {
        return status != null ? JobStatus.fromValue(status) : null;
    }

    public String getUrl() {
        return url;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getStartedAt() {
        return startedAt;
    }

    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public boolean isCompleted() {
        return "COMPLETED".equalsIgnoreCase(status);
    }

    public boolean isFailed() {
        return "FAILED".equalsIgnoreCase(status);
    }

    public boolean isCancelled() {
        return "CANCELLED".equalsIgnoreCase(status);
    }

    public boolean isProcessing() {
        return "PROCESSING".equalsIgnoreCase(status);
    }

    public boolean isQueued() {
        return "QUEUED".equalsIgnoreCase(status);
    }

    public boolean isTerminal() {
        return isCompleted() || isFailed() || isCancelled();
    }
}
