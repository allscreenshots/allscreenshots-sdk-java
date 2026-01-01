package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Detailed status of a bulk screenshot job.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkStatusResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("totalJobs")
    private Integer totalJobs;

    @JsonProperty("completedJobs")
    private Integer completedJobs;

    @JsonProperty("failedJobs")
    private Integer failedJobs;

    @JsonProperty("progress")
    private Integer progress;

    @JsonProperty("jobs")
    private List<BulkJobDetailInfo> jobs;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("completedAt")
    private OffsetDateTime completedAt;

    public BulkStatusResponse() {
    }

    public String getId() { return id; }
    public String getStatus() { return status; }
    public Integer getTotalJobs() { return totalJobs; }
    public Integer getCompletedJobs() { return completedJobs; }
    public Integer getFailedJobs() { return failedJobs; }
    public Integer getProgress() { return progress; }
    public List<BulkJobDetailInfo> getJobs() { return jobs; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public OffsetDateTime getCompletedAt() { return completedAt; }
}
