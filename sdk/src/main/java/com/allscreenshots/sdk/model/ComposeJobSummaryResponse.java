package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * Summary of a compose job.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeJobSummaryResponse {

    @JsonProperty("jobId")
    private String jobId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("totalCaptures")
    private Integer totalCaptures;

    @JsonProperty("completedCaptures")
    private Integer completedCaptures;

    @JsonProperty("failedCaptures")
    private Integer failedCaptures;

    @JsonProperty("progress")
    private Integer progress;

    @JsonProperty("layoutType")
    private String layoutType;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("completedAt")
    private OffsetDateTime completedAt;

    public ComposeJobSummaryResponse() {
    }

    public String getJobId() { return jobId; }
    public String getStatus() { return status; }
    public Integer getTotalCaptures() { return totalCaptures; }
    public Integer getCompletedCaptures() { return completedCaptures; }
    public Integer getFailedCaptures() { return failedCaptures; }
    public Integer getProgress() { return progress; }
    public String getLayoutType() { return layoutType; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public OffsetDateTime getCompletedAt() { return completedAt; }
}
