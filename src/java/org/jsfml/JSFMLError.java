package org.jsfml;

/**
 * Error class for severe JSFML faults.
 * <p/>
 * An error of this type is raised either if JSFML tried to load its native libraries on an unsupported
 * platform, or if a platform-specific requirement is violated.
 */
public class JSFMLError extends Error {
    private static final long serialVersionUID = -8281004117329430845L;

    /**
     * Creates a new JSFML error.
     *
     * @param message The exception's message.
     */
    public JSFMLError(String message) {
        super(message);
    }

    /**
     * Creates a new JSFML error.
     *
     * @param message The exception's message.
     * @param cause   The exception's cause, or <tt>null</tt> if no cause is available.
     */
    public JSFMLError(String message, Throwable cause) {
        super(message, cause);
    }
}
