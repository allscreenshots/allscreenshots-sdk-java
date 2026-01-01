package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * Response when an asynchronous screenshot job is created.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsyncJobCreatedResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusUrl")
    private String statusUrl;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    public AsyncJobCreatedResponse() {
    }

    /**
     * Returns the job ID.
     *
     * @return the job ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the initial job status.
     *
     * @return the status (typically "QUEUED")
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the URL to check job status.
     *
     * @return the status URL
     */
    public String getStatusUrl() {
        return statusUrl;
    }

    /**
     * Returns when the job was created.
     *
     * @return the creation timestamp
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
