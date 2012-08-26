package org.jsfml.window;

/**
 * Exception type that is thrown when activating or deactivating a {@link Context} fails
 * using {@link Context#setActive(boolean)} or {@link Window#setActive(boolean)}.
 */
public final class ContextActivationException extends Exception {
    private static final long serialVersionUID = -9207950728636532244L;

    public ContextActivationException(String message) {
        super(message);
    }
}
