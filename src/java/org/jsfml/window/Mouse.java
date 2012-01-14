package org.jsfml.window;

import org.jsfml.JSFMLException;
import org.jsfml.SFMLNative;
import org.jsfml.system.Vector2i;

/**
 * Provides access to the the real-time state of the mouse.
 */
public class Mouse {
    static {
        try {
            SFMLNative.loadNativeLibraries();
        } catch (JSFMLException ex) {
            throw new UnsatisfiedLinkError(ex.getMessage());
        }
    }

    /**
     * Mouse Buttons.
     */
    public enum Button {
        LEFT,
        RIGHT,
        MIDDLE,
        XBUTTON1,
        XBUTTON2,
    }

    /**
     * Checks if a mouse button is currently pressed.
     *
     * @param button The button in question.
     * @return <tt>true</tt> if the button is currently being pressed, <tt>false</tt> otherwise.
     */
    public static native boolean isButtonPressed(Button button);

    /**
     * Retrieves the position of the mouse cursor on the screen.
     *
     * @return The absolute position of the mouse cursor on the screen.
     */
    public static native Vector2i getPosition();

    /**
     * Retrieves the position of the mouse cursor relative to a window.
     *
     * @param relativeTo The window to relate to.
     * @return The position of the mouse cursor relative to the window's top left corner.
     */
    public static native Vector2i getPosition(Window relativeTo);

    /**
     * Sets the position of the mouse cursor on the screen.
     *
     * @param position The absolute position of the mouse cursor on the screen.
     */
    public static native void setPosition(Vector2i position);

    /**
     * Sets the position of the mouse cursor relative to a window.
     *
     * @param position   The position of the mouse cursor relative to the window's top left corner.
     * @param relativeTo The window to relate to.
     */
    public static native void setPosition(Vector2i position, Window relativeTo);
}
