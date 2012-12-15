package org.jsfml.window;

import org.jsfml.NotNull;
import org.jsfml.SFMLNative;
import org.jsfml.system.Vector2i;

/**
 * Provides access to the the real-time state of the mouse.
 */
public final class Mouse {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Enumeration of supported mouse buttons.
     */
    public static enum Button {
        LEFT,
        RIGHT,
        MIDDLE,
        XBUTTON1,
        XBUTTON2,
    }

	private static native boolean nativeIsButtonPressed(Button button);

    /**
     * Checks if a mouse button is currently pressed.
     *
     * @param button The button in question.
     * @return <tt>true</tt> if the button is currently being pressed, <tt>false</tt> otherwise.
     */
    public static boolean isButtonPressed(@NotNull Button button) {
		if (button == null)
			throw new NullPointerException("button must not be null");

		return nativeIsButtonPressed(button);
	}

    /**
     * Retrieves the position of the mouse cursor on the screen.
     *
     * @return The absolute position of the mouse cursor on the screen.
     */
    public static native Vector2i getPosition();

    private static native Vector2i nativeGetPosition(Window relativeTo);

    /**
     * Retrieves the position of the mouse cursor relative to a window.
     *
     * @param relativeTo The window to relate to.
     * @return The position of the mouse cursor relative to the window's top left corner.
     */
    public static Vector2i getPosition(@NotNull Window relativeTo) {
        if (relativeTo == null)
            throw new NullPointerException("relativeTo must not be null");

        return nativeGetPosition(relativeTo);
    }

    private static native void nativeSetPosition(Vector2i position);

    /**
     * Sets the position of the mouse cursor on the screen.
     *
     * @param position The absolute position of the mouse cursor on the screen.
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
     * @param position   The position of the mouse cursor relative to the window's top left corner.
     * @param relativeTo The window to relate to.
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
