package org.jsfml.system;

import org.jsfml.SFMLNativeObject;

/**
 * Utility class for measuring time.
 */
public final class Clock extends SFMLNativeObject {
    /**
     * Creates and starts a new clock.
     */
    public Clock() {
        super();
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    /**
     * Gets the elapsed time since the clock was created or last restarted.
     *
     * @return The elapsed time since the clock was created or last restarted.
     */
    public native Time getElapsedTime();

    /**
     * Yields the elapsed time and restarts the clock at zero.
     *
     * @return The elapsed time since the clock was created or last restarted.
     */
    public native Time restart();
}
