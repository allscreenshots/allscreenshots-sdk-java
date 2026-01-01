package com.allscreenshots.sdk.client;

import com.allscreenshots.sdk.model.*;

import java.util.List;

/**
 * API for bulk screenshot operations.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * BulkResponse response = client.bulk().create(
 *     BulkRequest.builder()
 *         .addUrl("https://example.com")
 *         .addUrl("https://github.com")
 *         .defaults(BulkDefaults.builder()
 *             .device("Desktop HD")
 *             .build())
 *         .build()
 * );
 * }</pre>
 */
public class BulkApi {

    private final HttpClient httpClient;

    BulkApi(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Creates a bulk screenshot job.
     *
     * @param request the bulk request
     * @return the created bulk job
     */
    public BulkResponse create(BulkRequest request) {
        return httpClient.post("/v1/screenshots/bulk", request, BulkResponse.class);
    }

    /**
     * Lists all bulk jobs.
     *
     * @return list of bulk job summaries
     */
    public List<BulkJobSummary> list() {
        return httpClient.get("/v1/screenshots/bulk",
            httpClient.getObjectMapper().getTypeFactory().constructCollectionType(List.class, BulkJobSummary.class));
    }

    /**
     * Gets detailed status of a bulk job.
     *
     * @param bulkId the bulk job ID
     * @return the bulk job status
     */
    public BulkStatusResponse getStatus(String bulkId) {
        return httpClient.get("/v1/screenshots/bulk/" + bulkId, BulkStatusResponse.class);
    }

    /**
     * Cancels a bulk job.
     *
     * @param bulkId the bulk job ID
     * @return the updated bulk job summary
     */
    public BulkJobSummary cancel(String bulkId) {
        return httpClient.post("/v1/screenshots/bulk/" + bulkId + "/cancel", null, BulkJobSummary.class);
    }
}
