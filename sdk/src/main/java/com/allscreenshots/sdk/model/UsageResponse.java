package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response containing usage statistics.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageResponse {

    @JsonProperty("tier")
    private String tier;

    @JsonProperty("currentPeriod")
    private PeriodUsageResponse currentPeriod;

    @JsonProperty("quota")
    private QuotaResponse quota;

    @JsonProperty("history")
    private List<PeriodUsageResponse> history;

    @JsonProperty("totals")
    private TotalsResponse totals;

    public UsageResponse() {
    }

    public String getTier() { return tier; }
    public PeriodUsageResponse getCurrentPeriod() { return currentPeriod; }
    public QuotaResponse getQuota() { return quota; }
    public List<PeriodUsageResponse> getHistory() { return history; }
    public TotalsResponse getTotals() { return totals; }
}
