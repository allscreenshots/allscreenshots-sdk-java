package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Total usage information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalsResponse {

    @JsonProperty("screenshotsCount")
    private Long screenshotsCount;

    @JsonProperty("bandwidthBytes")
    private Long bandwidthBytes;

    @JsonProperty("bandwidthFormatted")
    private String bandwidthFormatted;

    public TotalsResponse() {
    }

    public Long getScreenshotsCount() { return screenshotsCount; }
    public Long getBandwidthBytes() { return bandwidthBytes; }
    public String getBandwidthFormatted() { return bandwidthFormatted; }
}
