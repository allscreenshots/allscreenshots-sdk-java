package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Quota information in usage response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotaResponse {

    @JsonProperty("screenshots")
    private QuotaDetailResponse screenshots;

    @JsonProperty("bandwidth")
    private BandwidthQuotaResponse bandwidth;

    public QuotaResponse() {
    }

    public QuotaDetailResponse getScreenshots() { return screenshots; }
    public BandwidthQuotaResponse getBandwidth() { return bandwidth; }
}
