package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response containing schedule execution history.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleHistoryResponse {

    @JsonProperty("scheduleId")
    private String scheduleId;

    @JsonProperty("totalExecutions")
    private Long totalExecutions;

    @JsonProperty("executions")
    private List<ScheduleExecutionResponse> executions;

    public ScheduleHistoryResponse() {
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public Long getTotalExecutions() {
        return totalExecutions;
    }

    public List<ScheduleExecutionResponse> getExecutions() {
        return executions;
    }
}
