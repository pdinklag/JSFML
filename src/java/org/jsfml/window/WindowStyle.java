package org.jsfml.window;

/**
 * Provides window style constants.
 * <p/>
 * These constants can be combined using an arithmetic {@code OR} operation to define
 * the style settings for a {@code Window}. For instance, the {@code DEFAULT} style is defined
 * as {@code TITLEBAR | RESIZE | CLOSE}.
 *
 * @see Window#create(VideoMode, String, int)
 */
public interface WindowStyle {
    /**
     * Undecorated, non-resizable window.
     */
    public static final int NONE = 0;

    /**
     * Adds a title bar and a fixed border to the window.
     */
    public static final int TITLEBAR = 0x01;

    /**
     * Makes the window resizable.
     */
    public static final int RESIZE = 0x02;

    /**
     * Adds a close button to the window.
     */
    public static final int CLOSE = 0x04;

    /**
     * Makes the window a fullscreen window.
     * If this flag is set, the other flags will be ignored.
     */
    public static final int FULLSCREEN = 0x08;

    /**
     * The default style, a resizable and closeable window with a title bar.
     */
    public static final int DEFAULT = TITLEBAR | RESIZE | CLOSE;
}
