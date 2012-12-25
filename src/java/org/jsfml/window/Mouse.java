package org.jsfml.window;

import org.jsfml.NotNull;
import org.jsfml.SFMLNative;
import org.jsfml.system.Vector2i;

/**
 * Provides access to the the real-time state of the mouse.
 * <p/>
 * The methods in this class provide direct access to the mouse state, that means
 * that they work independently of a window's focus. In order to react to window
 * based events, use the  {@link org.jsfml.window.Window#pollEvents()} method instead.
 */
public final class Mouse {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Enumeration of supported mouse buttons.
     */
    public static enum Button {
        /**
         * The left mouse button.
         */
        LEFT,

        /**
         * The right mouse button.
         */
        RIGHT,

        /**
         * The middle mouse button, or mouse wheel on many mouses, if available.
         */
        MIDDLE,

        /**
         * The first extra mouse button, if available.
         */
        XBUTTON1,

        /**
         * The second extra mouse button, if available.
         */
        XBUTTON2,
    }

    private static native boolean nativeIsButtonPressed(Button button);

    /**
     * Checks if a mouse button is currently pressed.
     *
     * @param button the mouse button in question.
     * @return {@code true} if the button is currently being pressed,
     *         {@code false} otherwise.
     */
    public static boolean isButtonPressed(@NotNull Button button) {
        if (button == null)
            throw new NullPointerException("button must not be null");

        return nativeIsButtonPressed(button);
    }

    /**
     * Retrieves the absolute position of the mouse cursor on the screen.
     *
     * @return the absolute position of the mouse cursor on the screen.
     */
    public static native Vector2i getPosition();

    private static native Vector2i nativeGetPosition(Window relativeTo);

    /**
     * Retrieves the position of the mouse cursor relative to a window.
     *
     * @param relativeTo the window in question.
     * @return the position of the mouse cursor relative to the window's top left corner.
     */
    public static Vector2i getPosition(@NotNull Window relativeTo) {
        if (relativeTo == null)
            throw new NullPointerException("relativeTo must not be null");

        return nativeGetPosition(relativeTo);
    }

    private static native void nativeSetPosition(Vector2i position);

    /**
     * Sets the absolute position of the mouse cursor on the screen.
     *
     * @param position the new absolute position of the mouse cursor on the screen.
     */
    public static void setPosition(@NotNull Vector2i position) {
        if (position == null)
            throw new NullPointerException("position must not be null");

        nativeSetPosition(position);
    }

    private static native void nativeSetPosition(Vector2i position, Window relativeTo);

    /**
     * Sets the position of the mouse cursor relative to a window.
     *
     * @param position   the new position of the mouse cursor relative
     *                   to the window's top left corner.
     * @param relativeTo the window in question.
     */
    public static void setPosition(@NotNull Vector2i position, @NotNull Window relativeTo) {
        if (position == null)
            throw new NullPointerException("position must not be null");

        if (relativeTo == null)
            throw new NullPointerException("relativeTo must not be null");

        nativeSetPosition(position, relativeTo);
    }

    //cannot instantiate
    private Mouse() {
    }
}
