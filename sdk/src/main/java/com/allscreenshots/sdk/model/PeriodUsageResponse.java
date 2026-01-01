package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Usage information for a specific period.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeriodUsageResponse {

    @JsonProperty("periodStart")
    private String periodStart;

    @JsonProperty("periodEnd")
    private String periodEnd;

    @JsonProperty("screenshotsCount")
    private Integer screenshotsCount;

    @JsonProperty("bandwidthBytes")
    private Long bandwidthBytes;

    @JsonProperty("bandwidthFormatted")
    private String bandwidthFormatted;

    public PeriodUsageResponse() {
    }

    public String getPeriodStart() { return periodStart; }
    public String getPeriodEnd() { return periodEnd; }
    public Integer getScreenshotsCount() { return screenshotsCount; }
    public Long getBandwidthBytes() { return bandwidthBytes; }
    public String getBandwidthFormatted() { return bandwidthFormatted; }
}
