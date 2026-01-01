package com.allscreenshots.sdk.exception;

/**
 * Base exception for all Allscreenshots SDK errors.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * try {
 *     byte[] screenshot = client.screenshots().capture(request);
 * } catch (AllscreenshotsException e) {
 *     System.err.println("Screenshot failed: " + e.getMessage());
 * }
 * }</pre>
 */
public class AllscreenshotsException extends RuntimeException {

    private final String errorCode;
    private final Integer statusCode;

    /**
     * Creates a new Allscreenshots exception with a message.
     *
     * @param message the error message
     */
    public AllscreenshotsException(String message) {
        super(message);
        this.errorCode = null;
        this.statusCode = null;
    }

    /**
     * Creates a new Allscreenshots exception with a message and cause.
     *
     * @param message the error message
     * @param cause the underlying cause
     */
    public AllscreenshotsException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
        this.statusCode = null;
    }

    /**
     * Creates a new Allscreenshots exception with full details.
     *
     * @param message the error message
     * @param errorCode the API error code
     * @param statusCode the HTTP status code
     */
    public AllscreenshotsException(String message, String errorCode, Integer statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    /**
     * Creates a new Allscreenshots exception with full details and cause.
     *
     * @param message the error message
     * @param errorCode the API error code
     * @param statusCode the HTTP status code
     * @param cause the underlying cause
     */
    public AllscreenshotsException(String message, String errorCode, Integer statusCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    /**
     * Returns the API error code, if available.
     *
     * @return the error code, or null if not available
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Returns the HTTP status code, if available.
     *
     * @return the status code, or null if not available
     */
    public Integer getStatusCode() {
        return statusCode;
    }
}
