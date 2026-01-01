package com.allscreenshots.sdk.client;

import com.allscreenshots.sdk.model.*;

import java.util.List;

/**
 * API for composing multiple screenshots into a single image.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * ComposeResponse response = client.compose().create(
 *     ComposeRequest.builder()
 *         .addCapture(CaptureItem.builder()
 *             .url("https://example.com")
 *             .device("Desktop HD")
 *             .build())
 *         .addCapture(CaptureItem.builder()
 *             .url("https://github.com")
 *             .device("iPhone 14")
 *             .build())
 *         .output(ComposeOutputConfig.builder()
 *             .layout(LayoutType.GRID)
 *             .columns(2)
 *             .build())
 *         .build()
 * );
 * }</pre>
 */
public class ComposeApi {

    private final HttpClient httpClient;

    ComposeApi(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Creates a composed screenshot from multiple URLs.
     *
     * @param request the compose request
     * @return the compose response (sync) or job status (async)
     */
    public ComposeResponse create(ComposeRequest request) {
        return httpClient.post("/v1/screenshots/compose", request, ComposeResponse.class);
    }

    /**
     * Creates a composed screenshot asynchronously.
     *
     * @param request the compose request (async should be true)
     * @return the job status
     */
    public ComposeJobStatusResponse createAsync(ComposeRequest request) {
        ComposeRequest asyncRequest = ComposeRequest.builder()
                .captures(request.getCaptures())
                .url(request.getUrl())
                .variants(request.getVariants())
                .defaults(request.getDefaults())
                .output(request.getOutput())
                .async(true)
                .webhookUrl(request.getWebhookUrl())
                .webhookSecret(request.getWebhookSecret())
                .build();
        return httpClient.post("/v1/screenshots/compose", asyncRequest, ComposeJobStatusResponse.class);
    }

    /**
     * Previews a layout without actually capturing screenshots.
     *
     * @param layout the layout type
     * @param imageCount number of images
     * @param canvasWidth canvas width
     * @param canvasHeight canvas height
     * @param aspectRatios aspect ratios for images
     * @return the layout preview
     */
    public LayoutPreviewResponse preview(String layout, int imageCount, Integer canvasWidth,
                                          Integer canvasHeight, String aspectRatios) {
        StringBuilder path = new StringBuilder("/v1/screenshots/compose/preview?");
        path.append("layout=").append(layout);
        path.append("&image_count=").append(imageCount);
        if (canvasWidth != null) {
            path.append("&canvas_width=").append(canvasWidth);
        }
        if (canvasHeight != null) {
            path.append("&canvas_height=").append(canvasHeight);
        }
        if (aspectRatios != null) {
            path.append("&aspect_ratios=").append(aspectRatios);
        }
        return httpClient.get(path.toString(), LayoutPreviewResponse.class);
    }

    /**
     * Lists all compose jobs.
     *
     * @return list of compose job summaries
     */
    public List<ComposeJobSummaryResponse> listJobs() {
        return httpClient.get("/v1/screenshots/compose/jobs",
            httpClient.getObjectMapper().getTypeFactory().constructCollectionType(List.class, ComposeJobSummaryResponse.class));
    }

    /**
     * Gets a compose job's status.
     *
     * @param jobId the job ID
     * @return the job status
     */
    public ComposeJobStatusResponse getJob(String jobId) {
        return httpClient.get("/v1/screenshots/compose/jobs/" + jobId, ComposeJobStatusResponse.class);
    }
}
