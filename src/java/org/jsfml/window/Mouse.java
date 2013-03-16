package org.jsfml.window;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.internal.SFMLNative;
import org.jsfml.system.Vector2i;

import java.util.Objects;

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

    private static native boolean nativeIsButtonPressed(int button);

    /**
     * Checks if a mouse button is currently pressed.
     *
     * @param button the mouse button in question.
     * @return {@code true} if the button is currently being pressed,
     *         {@code false} otherwise.
     */
    public static boolean isButtonPressed(Button button) {
        return nativeIsButtonPressed(button.ordinal());
    }

    private static native long nativeGetPosition();

    /**
     * Retrieves the absolute position of the mouse cursor on the screen.
     *
     * @return the absolute position of the mouse cursor on the screen.
     */
    public static Vector2i getPoisition() {
        return IntercomHelper.decodeVector2i(nativeGetPosition());
    }

    private static native long nativeGetPosition(Window relativeTo);

    /**
     * Retrieves the position of the mouse cursor relative to a window.
     *
     * @param relativeTo the window in question.
     * @return the position of the mouse cursor relative to the window's top left corner.
     */
    public static Vector2i getPosition(Window relativeTo) {
        return IntercomHelper.decodeVector2i(
                nativeGetPosition(Objects.requireNonNull(relativeTo)));
    }

    private static native void nativeSetPosition(long position);

    /**
     * Sets the absolute position of the mouse cursor on the screen.
     *
     * @param position the new absolute position of the mouse cursor on the screen.
     */
    public static void setPosition(Vector2i position) {
        nativeSetPosition(IntercomHelper.encodeVector2i(position));
    }

    private static native void nativeSetPosition(long position, Window relativeTo);

    /**
     * Sets the position of the mouse cursor relative to a window.
     *
     * @param position   the new position of the mouse cursor relative
     *                   to the window's top left corner.
     * @param relativeTo the window in question.
     */
    public static void setPosition(Vector2i position, Window relativeTo) {
        nativeSetPosition(
                IntercomHelper.encodeVector2i(position),
                Objects.requireNonNull(relativeTo));
    }

    //cannot instantiate
    private Mouse() {
    }
}
