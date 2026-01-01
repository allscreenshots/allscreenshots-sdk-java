package com.allscreenshots.sdk.client;

import com.allscreenshots.sdk.model.*;

/**
 * API for usage and quota information.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * UsageResponse usage = client.usage().getUsage();
 * System.out.println("Screenshots this period: " + usage.getCurrentPeriod().getScreenshotsCount());
 *
 * QuotaStatusResponse quota = client.usage().getQuota();
 * System.out.println("Remaining: " + quota.getScreenshots().getRemaining());
 * }</pre>
 */
public class UsageApi {

    private final HttpClient httpClient;

    UsageApi(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Gets usage statistics.
     *
     * @return the usage response
     */
    public UsageResponse getUsage() {
        return httpClient.get("/v1/usage", UsageResponse.class);
    }

    /**
     * Gets current quota status.
     *
     * @return the quota status
     */
    public QuotaStatusResponse getQuota() {
        return httpClient.get("/v1/usage/quota", QuotaStatusResponse.class);
    }
}
