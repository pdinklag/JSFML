package org.jsfml.system;

/**
 * Utility class for measuring time.
 */
public final class Clock {
	private long t0;

	/**
	 * Creates and starts a new clock.
	 */
	public Clock() {
		t0 = System.nanoTime();
	}

	/**
	 * Gets the elapsed time since the clock was created or last restarted.
	 *
	 * @return The elapsed time since the clock was created or last restarted.
	 */
	public Time getElapsedTime() {
		long dt = System.nanoTime() - t0;
		return Time.getMicroseconds(dt / 1000L);
	}

	/**
	 * Yields the elapsed time and restarts the clock at zero.
	 *
	 * @return The elapsed time since the clock was created or last restarted.
	 */
	public Time restart() {
		Time dt = getElapsedTime();
		t0 = System.nanoTime();
		return dt;
	}
}
