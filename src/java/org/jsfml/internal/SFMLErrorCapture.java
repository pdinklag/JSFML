package org.jsfml.internal;

import java.util.concurrent.Semaphore;

/**
 * Provides methods to capture output to {@code sf::err()}.
 */
public class SFMLErrorCapture {
    private static boolean capturing = false;
    private final static Semaphore semaphore = new Semaphore(1);

    private static native void nativeStart();

    /**
     * Starts capturing all output to {@code sf::err()}.
     * <p/>
     * This method acquires a semaphore and will block if a capture is currently going,
     * therefore the capturing is thread-safe.
     * <p/>
     * A capture must be concluded by calling the {@link #finish()} method.
     */
    public static void start() {
        try {
            semaphore.acquire();
            capturing = true;

            nativeStart();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private static native String nativeFinish();

    /**
     * Finishes capturing all output to {@code sf::err()} and returns what was captured.
     *
     * @return the output that was captured, or {@code null} if capturing was never started using
     *         {@link #start()}.
     */
    public static String finish() {
        final String str;
        if (capturing) {
            str = nativeFinish().trim();

            capturing = false;
            semaphore.release();
        } else {
            str = null;
        }

        return str;
    }

    private SFMLErrorCapture() {
        //cannot instantiate
    }
}
