package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Response containing schedule information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("schedule")
    private String schedule;

    @JsonProperty("scheduleDescription")
    private String scheduleDescription;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("status")
    private String status;

    @JsonProperty("options")
    private Map<String, Object> options;

    @JsonProperty("webhookUrl")
    private String webhookUrl;

    @JsonProperty("retentionDays")
    private Integer retentionDays;

    @JsonProperty("startsAt")
    private OffsetDateTime startsAt;

    @JsonProperty("endsAt")
    private OffsetDateTime endsAt;

    @JsonProperty("lastExecutedAt")
    private OffsetDateTime lastExecutedAt;

    @JsonProperty("nextExecutionAt")
    private OffsetDateTime nextExecutionAt;

    @JsonProperty("executionCount")
    private Integer executionCount;

    @JsonProperty("successCount")
    private Integer successCount;

    @JsonProperty("failureCount")
    private Integer failureCount;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("updatedAt")
    private OffsetDateTime updatedAt;

    public ScheduleResponse() {
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getUrl() { return url; }
    public String getSchedule() { return schedule; }
    public String getScheduleDescription() { return scheduleDescription; }
    public String getTimezone() { return timezone; }
    public String getStatus() { return status; }
    public Map<String, Object> getOptions() { return options; }
    public String getWebhookUrl() { return webhookUrl; }
    public Integer getRetentionDays() { return retentionDays; }
    public OffsetDateTime getStartsAt() { return startsAt; }
    public OffsetDateTime getEndsAt() { return endsAt; }
    public OffsetDateTime getLastExecutedAt() { return lastExecutedAt; }
    public OffsetDateTime getNextExecutionAt() { return nextExecutionAt; }
    public Integer getExecutionCount() { return executionCount; }
    public Integer getSuccessCount() { return successCount; }
    public Integer getFailureCount() { return failureCount; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
}
