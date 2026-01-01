package com.allscreenshots.sdk.client;

import com.allscreenshots.sdk.model.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * API for screenshot operations.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Capture a screenshot synchronously
 * byte[] image = client.screenshots().capture(
 *     ScreenshotRequest.builder()
 *         .url("https://example.com")
 *         .device("Desktop HD")
 *         .build()
 * );
 *
 * // Capture asynchronously
 * AsyncJobCreatedResponse job = client.screenshots().captureAsync(
 *     ScreenshotRequest.builder()
 *         .url("https://example.com")
 *         .build()
 * );
 * }</pre>
 */
public class ScreenshotsApi {

    private final HttpClient httpClient;

    ScreenshotsApi(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Captures a screenshot synchronously and returns the image bytes.
     *
     * @param request the screenshot request
     * @return the screenshot image as bytes
     */
    public byte[] capture(ScreenshotRequest request) {
        return httpClient.postForBinary("/v1/screenshots", request);
    }

    /**
     * Captures a screenshot asynchronously.
     *
     * @param request the screenshot request
     * @return the created job information
     */
    public AsyncJobCreatedResponse captureAsync(ScreenshotRequest request) {
        return httpClient.post("/v1/screenshots/async", request, AsyncJobCreatedResponse.class);
    }

    /**
     * Lists all screenshot jobs.
     *
     * @return list of jobs
     */
    public List<JobResponse> listJobs() {
        return httpClient.get("/v1/screenshots/jobs",
            httpClient.getObjectMapper().getTypeFactory().constructCollectionType(List.class, JobResponse.class));
    }

    /**
     * Gets a specific job's status.
     *
     * @param jobId the job ID
     * @return the job status
     */
    public JobResponse getJob(String jobId) {
        return httpClient.get("/v1/screenshots/jobs/" + jobId, JobResponse.class);
    }

    /**
     * Gets the result image of a completed job.
     *
     * @param jobId the job ID
     * @return the screenshot image as bytes
     */
    public byte[] getJobResult(String jobId) {
        return httpClient.getBinary("/v1/screenshots/jobs/" + jobId + "/result");
    }

    /**
     * Cancels a pending or processing job.
     *
     * @param jobId the job ID
     * @return the updated job status
     */
    public JobResponse cancelJob(String jobId) {
        return httpClient.post("/v1/screenshots/jobs/" + jobId + "/cancel", null, JobResponse.class);
    }
}
