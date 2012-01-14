package org.jsfml;

/**
 * Exception class for JSFML related exceptions.
 */
public class JSFMLException extends Exception {
    private static final long serialVersionUID = 8867869229054766964L;

    /**
     * Creates a new JSFML exception.
     *
     * @param message The exception's message.
     */
    public JSFMLException(String message) {
        super(message);
    }

    /**
     * Creates a new JSFML exception.
     *
     * @param message The exception's message.
     * @param cause   The exception's cause, or <tt>null</tt> if no cause is available.
     */
    public JSFMLException(String message, Throwable cause) {
        super(message, cause);
    }
}
