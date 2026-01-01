package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response containing quota status.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotaStatusResponse {

    @JsonProperty("tier")
    private String tier;

    @JsonProperty("screenshots")
    private QuotaDetailResponse screenshots;

    @JsonProperty("bandwidth")
    private BandwidthQuotaResponse bandwidth;

    @JsonProperty("periodEnds")
    private String periodEnds;

    public QuotaStatusResponse() {
    }

    public String getTier() { return tier; }
    public QuotaDetailResponse getScreenshots() { return screenshots; }
    public BandwidthQuotaResponse getBandwidth() { return bandwidth; }
    public String getPeriodEnds() { return periodEnds; }
}
