package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/**
 * Request to create a scheduled screenshot.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * CreateScheduleRequest request = CreateScheduleRequest.builder()
 *     .name("Daily homepage capture")
 *     .url("https://example.com")
 *     .schedule("0 9 * * *")
 *     .timezone("America/New_York")
 *     .options(ScheduleScreenshotOptions.builder()
 *         .device("Desktop HD")
 *         .fullPage(true)
 *         .build())
 *     .build();
 * }</pre>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateScheduleRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("schedule")
    private String schedule;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("options")
    private ScheduleScreenshotOptions options;

    @JsonProperty("webhookUrl")
    private String webhookUrl;

    @JsonProperty("webhookSecret")
    private String webhookSecret;

    @JsonProperty("retentionDays")
    private Integer retentionDays;

    @JsonProperty("startsAt")
    private OffsetDateTime startsAt;

    @JsonProperty("endsAt")
    private OffsetDateTime endsAt;

    public CreateScheduleRequest() {
    }

    private CreateScheduleRequest(Builder builder) {
        this.name = builder.name;
        this.url = builder.url;
        this.schedule = builder.schedule;
        this.timezone = builder.timezone;
        this.options = builder.options;
        this.webhookUrl = builder.webhookUrl;
        this.webhookSecret = builder.webhookSecret;
        this.retentionDays = builder.retentionDays;
        this.startsAt = builder.startsAt;
        this.endsAt = builder.endsAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() { return name; }
    public String getUrl() { return url; }
    public String getSchedule() { return schedule; }
    public String getTimezone() { return timezone; }
    public ScheduleScreenshotOptions getOptions() { return options; }
    public String getWebhookUrl() { return webhookUrl; }
    public String getWebhookSecret() { return webhookSecret; }
    public Integer getRetentionDays() { return retentionDays; }
    public OffsetDateTime getStartsAt() { return startsAt; }
    public OffsetDateTime getEndsAt() { return endsAt; }

    public static class Builder {
        private String name;
        private String url;
        private String schedule;
        private String timezone;
        private ScheduleScreenshotOptions options;
        private String webhookUrl;
        private String webhookSecret;
        private Integer retentionDays;
        private OffsetDateTime startsAt;
        private OffsetDateTime endsAt;

        public Builder name(String name) { this.name = name; return this; }
        public Builder url(String url) { this.url = url; return this; }
        public Builder schedule(String schedule) { this.schedule = schedule; return this; }
        public Builder timezone(String timezone) { this.timezone = timezone; return this; }
        public Builder options(ScheduleScreenshotOptions options) { this.options = options; return this; }
        public Builder webhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; return this; }
        public Builder webhookSecret(String webhookSecret) { this.webhookSecret = webhookSecret; return this; }
        public Builder retentionDays(int retentionDays) { this.retentionDays = retentionDays; return this; }
        public Builder startsAt(OffsetDateTime startsAt) { this.startsAt = startsAt; return this; }
        public Builder endsAt(OffsetDateTime endsAt) { this.endsAt = endsAt; return this; }

        public CreateScheduleRequest build() {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name is required");
            }
            if (url == null || url.isBlank()) {
                throw new IllegalArgumentException("URL is required");
            }
            if (schedule == null || schedule.isBlank()) {
                throw new IllegalArgumentException("Schedule is required");
            }
            return new CreateScheduleRequest(this);
        }
    }
}
