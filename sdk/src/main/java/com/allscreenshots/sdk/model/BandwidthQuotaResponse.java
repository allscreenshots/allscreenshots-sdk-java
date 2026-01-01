package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bandwidth quota information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BandwidthQuotaResponse {

    @JsonProperty("limitBytes")
    private Long limitBytes;

    @JsonProperty("limitFormatted")
    private String limitFormatted;

    @JsonProperty("usedBytes")
    private Long usedBytes;

    @JsonProperty("usedFormatted")
    private String usedFormatted;

    @JsonProperty("remainingBytes")
    private Long remainingBytes;

    @JsonProperty("remainingFormatted")
    private String remainingFormatted;

    @JsonProperty("percentUsed")
    private Integer percentUsed;

    public BandwidthQuotaResponse() {
    }

    public Long getLimitBytes() { return limitBytes; }
    public String getLimitFormatted() { return limitFormatted; }
    public Long getUsedBytes() { return usedBytes; }
    public String getUsedFormatted() { return usedFormatted; }
    public Long getRemainingBytes() { return remainingBytes; }
    public String getRemainingFormatted() { return remainingFormatted; }
    public Integer getPercentUsed() { return percentUsed; }
}
