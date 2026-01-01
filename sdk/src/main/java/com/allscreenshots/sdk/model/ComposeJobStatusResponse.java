package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * Status response for an asynchronous compose job.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeJobStatusResponse {

    @JsonProperty("jobId")
    private String jobId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("progress")
    private Integer progress;

    @JsonProperty("totalCaptures")
    private Integer totalCaptures;

    @JsonProperty("completedCaptures")
    private Integer completedCaptures;

    @JsonProperty("result")
    private ComposeResponse result;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("completedAt")
    private OffsetDateTime completedAt;

    public ComposeJobStatusResponse() {
    }

    public String getJobId() { return jobId; }
    public String getStatus() { return status; }
    public Integer getProgress() { return progress; }
    public Integer getTotalCaptures() { return totalCaptures; }
    public Integer getCompletedCaptures() { return completedCaptures; }
    public ComposeResponse getResult() { return result; }
    public String getErrorCode() { return errorCode; }
    public String getErrorMessage() { return errorMessage; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public OffsetDateTime getCompletedAt() { return completedAt; }

    public boolean isCompleted() {
        return "COMPLETED".equalsIgnoreCase(status);
    }

    public boolean isFailed() {
        return "FAILED".equalsIgnoreCase(status);
    }

    public boolean isTerminal() {
        return isCompleted() || isFailed() || "CANCELLED".equalsIgnoreCase(status);
    }
}
