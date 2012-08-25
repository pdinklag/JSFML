package org.jsfml.window;

import org.jsfml.SFMLNativeObject;

/**
 * Class holding a valid OpenGL drawing context.
 * <p/>
 * For every OpenGL call, a valid context is required. Using this class, by creating
 * an instance, you can obtain a valid context.
 */
public class Context extends SFMLNativeObject {
    /**
     * Creates and activates a valid OpenGL context.
     */
    public Context() {
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    private native boolean nativeSetActive(boolean active);

    /**
     * Explictly activates or deactivates the OpenGL context.
     *
     * @param active <tt>true</tt> to activate, <tt>false</tt> to deactivate.
     * @throws ActivationException If activating or deactivating the context failed.
     */
    public void setActive(boolean active) throws ActivationException {
        if (!nativeSetActive(active)) {
            throw new ActivationException("Failed to " +
                    (active ? "activate" : "deactivate") +
                    " the context.");
        }
    }

    /**
     * Exception type that is thrown when activating or deactivating a {@link Context}
     * using {@link Context#setActive(boolean)} fails.
     */
    public final class ActivationException extends Exception {
        private static final long serialVersionUID = -9207950728636532244L;

        public ActivationException(String message) {
            super(message);
        }
    }
}
