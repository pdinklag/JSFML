package org.jsfml.internal;

/**
 * Error class for severe JSFML faults.
 * <p/>
 * An error of this type is raised either if JSFML tried to load its native libraries on an unsupported
 * platform, or if a platform-specific requirement is violated.
 */
public class JSFMLError extends Error {
    private static final long serialVersionUID = -8281004117329430845L;

    /**
     * Constructs a JSFML error with the specified message.
     *
     * @param message the exception's message text.
     */
    public JSFMLError(String message) {
        super(message);
    }

    /**
     * Constructs a JSFML error with the specified message and cause.
     *
     * @param message the exception's message text.
     * @param cause   the exception's cause, or {@code null} if no cause is known or available.
     */
    public JSFMLError(String message, Throwable cause) {
        super(message, cause);
    }
}
