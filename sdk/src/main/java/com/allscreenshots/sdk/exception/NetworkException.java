package com.allscreenshots.sdk.exception;

/**
 * Exception thrown when a network error occurs.
 *
 * <p>This exception is thrown for connection failures, timeouts,
 * and other network-level issues. The client will automatically
 * retry transient network errors with exponential backoff.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * try {
 *     byte[] screenshot = client.screenshots().capture(request);
 * } catch (NetworkException e) {
 *     System.err.println("Network error: " + e.getMessage());
 *     // Check network connectivity
 * }
 * }</pre>
 */
public class NetworkException extends AllscreenshotsException {

    /**
     * Creates a new network exception.
     *
     * @param message the error message
     */
    public NetworkException(String message) {
        super(message, "NETWORK_ERROR", null);
    }

    /**
     * Creates a new network exception with a cause.
     *
     * @param message the error message
     * @param cause the underlying cause
     */
    public NetworkException(String message, Throwable cause) {
        super(message, "NETWORK_ERROR", null, cause);
    }
}
