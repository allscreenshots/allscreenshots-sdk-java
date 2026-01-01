package com.allscreenshots.sdk.exception;

/**
 * Exception thrown when API rate limit is exceeded.
 *
 * <p>This exception is thrown when too many requests are made
 * in a given time period. The client will automatically retry
 * with exponential backoff when this occurs.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * try {
 *     byte[] screenshot = client.screenshots().capture(request);
 * } catch (RateLimitException e) {
 *     System.err.println("Rate limit exceeded, retry after: " + e.getRetryAfter());
 * }
 * }</pre>
 */
public class RateLimitException extends AllscreenshotsException {

    private final Long retryAfter;

    /**
     * Creates a new rate limit exception.
     *
     * @param message the error message
     */
    public RateLimitException(String message) {
        super(message, "RATE_LIMIT_EXCEEDED", 429);
        this.retryAfter = null;
    }

    /**
     * Creates a new rate limit exception with retry information.
     *
     * @param message the error message
     * @param retryAfter seconds until the rate limit resets
     */
    public RateLimitException(String message, Long retryAfter) {
        super(message, "RATE_LIMIT_EXCEEDED", 429);
        this.retryAfter = retryAfter;
    }

    /**
     * Returns the number of seconds until the rate limit resets.
     *
     * @return seconds until retry is allowed, or null if not specified
     */
    public Long getRetryAfter() {
        return retryAfter;
    }
}
