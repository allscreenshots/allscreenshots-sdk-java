package com.allscreenshots.sdk.exception;

/**
 * Exception thrown when the API returns an error response.
 *
 * <p>This exception is thrown for server-side errors (5xx status codes)
 * and other API-level errors that are not validation or authentication related.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * try {
 *     byte[] screenshot = client.screenshots().capture(request);
 * } catch (ApiException e) {
 *     System.err.println("API error: " + e.getStatusCode() + " - " + e.getMessage());
 * }
 * }</pre>
 */
public class ApiException extends AllscreenshotsException {

    private final String responseBody;

    /**
     * Creates a new API exception.
     *
     * @param message the error message
     * @param statusCode the HTTP status code
     */
    public ApiException(String message, int statusCode) {
        super(message, "API_ERROR", statusCode);
        this.responseBody = null;
    }

    /**
     * Creates a new API exception with error code.
     *
     * @param message the error message
     * @param errorCode the API error code
     * @param statusCode the HTTP status code
     */
    public ApiException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
        this.responseBody = null;
    }

    /**
     * Creates a new API exception with response body.
     *
     * @param message the error message
     * @param errorCode the API error code
     * @param statusCode the HTTP status code
     * @param responseBody the raw response body
     */
    public ApiException(String message, String errorCode, int statusCode, String responseBody) {
        super(message, errorCode, statusCode);
        this.responseBody = responseBody;
    }

    /**
     * Returns the raw response body from the API.
     *
     * @return the response body, or null if not available
     */
    public String getResponseBody() {
        return responseBody;
    }
}
