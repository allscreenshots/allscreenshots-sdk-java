package com.allscreenshots.sdk.exception;

/**
 * Exception thrown when authentication fails.
 *
 * <p>This exception is thrown when the API key is missing, invalid,
 * or does not have the required permissions.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * try {
 *     byte[] screenshot = client.screenshots().capture(request);
 * } catch (AuthenticationException e) {
 *     System.err.println("Authentication failed: " + e.getMessage());
 *     // Check your API key configuration
 * }
 * }</pre>
 */
public class AuthenticationException extends AllscreenshotsException {

    /**
     * Creates a new authentication exception.
     *
     * @param message the authentication error message
     */
    public AuthenticationException(String message) {
        super(message, "AUTHENTICATION_ERROR", 401);
    }

    /**
     * Creates a new authentication exception with an error code.
     *
     * @param message the authentication error message
     * @param errorCode the specific authentication error code
     */
    public AuthenticationException(String message, String errorCode) {
        super(message, errorCode, 401);
    }
}
