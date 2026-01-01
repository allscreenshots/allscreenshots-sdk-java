package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response containing a list of schedules.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleListResponse {

    @JsonProperty("schedules")
    private List<ScheduleResponse> schedules;

    @JsonProperty("total")
    private Integer total;

    public ScheduleListResponse() {
    }

    public List<ScheduleResponse> getSchedules() {
        return schedules;
    }

    public Integer getTotal() {
        return total;
    }
}
