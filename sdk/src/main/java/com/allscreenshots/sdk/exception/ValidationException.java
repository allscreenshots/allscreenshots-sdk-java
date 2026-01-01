package com.allscreenshots.sdk.exception;

/**
 * Exception thrown when request validation fails.
 *
 * <p>This exception is thrown when the request parameters do not meet
 * the API requirements, such as invalid URLs or out-of-range values.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * try {
 *     byte[] screenshot = client.screenshots().capture(request);
 * } catch (ValidationException e) {
 *     System.err.println("Invalid request: " + e.getMessage());
 * }
 * }</pre>
 */
public class ValidationException extends AllscreenshotsException {

    /**
     * Creates a new validation exception.
     *
     * @param message the validation error message
     */
    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR", 400);
    }

    /**
     * Creates a new validation exception with an error code.
     *
     * @param message the validation error message
     * @param errorCode the specific validation error code
     */
    public ValidationException(String message, String errorCode) {
        super(message, errorCode, 400);
    }
}
