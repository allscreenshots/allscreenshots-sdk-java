package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Basic information about a job in a bulk request.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkJobInfo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("status")
    private String status;

    public BulkJobInfo() {
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }
}
