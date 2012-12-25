package org.jsfml.window;

/**
 * Thrown when activating or deactivating an OpenGL fails
 * using {@link Context#setActive(boolean)} or {@link Window#setActive(boolean)}.
 */
public final class ContextActivationException extends Exception {
    private static final long serialVersionUID = -9207950728636532244L;

    /**
     * Constructs a context activation exception with the specified message.
     *
     * @param message the detail message.
     */
    public ContextActivationException(String message) {
        super(message);
    }
}
