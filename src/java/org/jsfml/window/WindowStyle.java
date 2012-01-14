package org.jsfml.window;

/**
 * Contains constants for window styles.
 * <p/>
 * These constants represent flags to be ORed together in order to create a window style.
 *
 * @see Window#create(VideoMode, String, long, ContextSettings)
 */
public final class WindowStyle {

    /**
     * Unstyled window.
     */
    public static final long NONE = 0;

    /**
     * The window will have a title bar.
     */
    public static final long TITLEBAR = 1 << 0;

    /**
     * The window is resizable.
     */
    public static final long RESIZE = 1 << 1;

    /**
     * The window has a close button.
     */
    public static final long CLOSE = 1 << 2;

    /**
     * The window emulates a fullscreen mode.
     */
    public static final long FULLSCREEN = 1 << 3;

    /**
     * The default style, a resizable and closeable window with a title bar.
     */
    public static final long DEFAULT = TITLEBAR | RESIZE | CLOSE;

    private WindowStyle() {
    }
}
