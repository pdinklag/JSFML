package org.jsfml.window;

/**
 * Contains constants for window styles.
 * <p/>
 * These constants represent flags to be ORed together in order to create a window style.
 *
 * @see Window#create(VideoMode, String, int, ContextSettings)
 */
public final class WindowStyle {

    /**
     * Unstyled window.
     */
    public static final int NONE = 0;

    /**
     * The window will have a title bar.
     */
    public static final int TITLEBAR = 0x01;

    /**
     * The window is resizable.
     */
    public static final int RESIZE = 0x02;

    /**
     * The window has a close button.
     */
    public static final int CLOSE = 0x04;

    /**
     * The window emulates a fullscreen mode.
     */
    public static final int FULLSCREEN = 0x08;

    /**
     * The default style, a resizable and closeable window with a title bar.
     */
    public static final int DEFAULT = TITLEBAR | RESIZE | CLOSE;

    private WindowStyle() {
    }
}
