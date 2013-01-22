package org.jsfml.system;

/**
 * Provides functionality for time measurement.
 */
public class Clock {
    private long t0;

    /**
     * Constructs a clock and starts it.
     */
    public Clock() {
        t0 = System.nanoTime();
    }

    /**
     * Gets the elapsed time since the clock was created or last restarted.
     *
     * @return the elapsed time since the clock was created or last restarted.
     */
    public Time getElapsedTime() {
        long dt = System.nanoTime() - t0;
        return Time.getMicroseconds(dt / 1000L);
    }

    /**
     * Yields the elapsed time and restarts the clock.
     *
     * @return the elapsed time since the clock was created or last restarted.
     */
    public final Time restart() {
        Time dt = getElapsedTime();
        t0 = System.nanoTime();
        return dt;
    }
}
