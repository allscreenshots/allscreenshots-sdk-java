package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Detailed quota information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotaDetailResponse {

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("used")
    private Integer used;

    @JsonProperty("remaining")
    private Integer remaining;

    @JsonProperty("percentUsed")
    private Integer percentUsed;

    public QuotaDetailResponse() {
    }

    public Integer getLimit() { return limit; }
    public Integer getUsed() { return used; }
    public Integer getRemaining() { return remaining; }
    public Integer getPercentUsed() { return percentUsed; }
}
