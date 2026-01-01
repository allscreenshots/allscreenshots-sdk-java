package com.allscreenshots.sdk.client;

import com.allscreenshots.sdk.model.*;

/**
 * API for scheduled screenshot operations.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * ScheduleResponse schedule = client.schedules().create(
 *     CreateScheduleRequest.builder()
 *         .name("Daily homepage capture")
 *         .url("https://example.com")
 *         .schedule("0 9 * * *")
 *         .timezone("America/New_York")
 *         .build()
 * );
 * }</pre>
 */
public class SchedulesApi {

    private final HttpClient httpClient;

    SchedulesApi(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Creates a new scheduled screenshot.
     *
     * @param request the schedule request
     * @return the created schedule
     */
    public ScheduleResponse create(CreateScheduleRequest request) {
        return httpClient.post("/v1/schedules", request, ScheduleResponse.class);
    }

    /**
     * Lists all schedules.
     *
     * @return list of schedules
     */
    public ScheduleListResponse list() {
        return httpClient.get("/v1/schedules", ScheduleListResponse.class);
    }

    /**
     * Gets a specific schedule.
     *
     * @param scheduleId the schedule ID
     * @return the schedule
     */
    public ScheduleResponse get(String scheduleId) {
        return httpClient.get("/v1/schedules/" + scheduleId, ScheduleResponse.class);
    }

    /**
     * Updates an existing schedule.
     *
     * @param scheduleId the schedule ID
     * @param request the update request
     * @return the updated schedule
     */
    public ScheduleResponse update(String scheduleId, UpdateScheduleRequest request) {
        return httpClient.put("/v1/schedules/" + scheduleId, request, ScheduleResponse.class);
    }

    /**
     * Deletes a schedule.
     *
     * @param scheduleId the schedule ID
     */
    public void delete(String scheduleId) {
        httpClient.delete("/v1/schedules/" + scheduleId);
    }

    /**
     * Pauses a schedule.
     *
     * @param scheduleId the schedule ID
     * @return the updated schedule
     */
    public ScheduleResponse pause(String scheduleId) {
        return httpClient.post("/v1/schedules/" + scheduleId + "/pause", null, ScheduleResponse.class);
    }

    /**
     * Resumes a paused schedule.
     *
     * @param scheduleId the schedule ID
     * @return the updated schedule
     */
    public ScheduleResponse resume(String scheduleId) {
        return httpClient.post("/v1/schedules/" + scheduleId + "/resume", null, ScheduleResponse.class);
    }

    /**
     * Manually triggers a schedule execution.
     *
     * @param scheduleId the schedule ID
     * @return the updated schedule
     */
    public ScheduleResponse trigger(String scheduleId) {
        return httpClient.post("/v1/schedules/" + scheduleId + "/trigger", null, ScheduleResponse.class);
    }

    /**
     * Gets the execution history for a schedule.
     *
     * @param scheduleId the schedule ID
     * @return the execution history
     */
    public ScheduleHistoryResponse getHistory(String scheduleId) {
        return httpClient.get("/v1/schedules/" + scheduleId + "/history", ScheduleHistoryResponse.class);
    }

    /**
     * Gets the execution history for a schedule with a limit.
     *
     * @param scheduleId the schedule ID
     * @param limit maximum number of executions to return
     * @return the execution history
     */
    public ScheduleHistoryResponse getHistory(String scheduleId, int limit) {
        return httpClient.get("/v1/schedules/" + scheduleId + "/history?limit=" + limit, ScheduleHistoryResponse.class);
    }
}
