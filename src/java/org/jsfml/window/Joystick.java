package org.jsfml.window;

import org.jsfml.SFMLNative;

/**
 * Provides access to the the real-time states of joysticks or gamepads.
 */
public class Joystick {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Enumeration of supported joystick axes.
     */
    public static enum Axis {
        X,
        Y,
        Z,
        R,
        U,
        V,
        POV_X,
        POV_Y
    }

    /**
     * Checks if a joystick is connected.
     *
     * @param joystick The index of the joystick to check.
     * @return <tt>true</tt> if the joystick is connected, <tt>false</tt> otherwise.
     */
    public static native boolean isConnected(int joystick);

    /**
     * Retrieves the button count of a joystick.
     *
     * @param joystick The index of the joystick in question.
     * @return The amount of buttons of the joystick.
     */
    public static native int getButtonCount(int joystick);

    /**
     * Checks whether a joystick has a certain axis controller.
     *
     * @param joystick The index of the joystick in question.
     * @param axis     The axis in question.
     * @return <tt>true</tt> if the joystick supports the given axis, <tt>false</tt> otherwise.
     */
    public static native boolean hasAxis(int joystick, Axis axis);

    /**
     * Checks whether a certain button on the joystick is currently pressed.
     *
     * @param joystick The index of the joystick in question.
     * @param button   The index of the button on the joystick to check.
     * @return <tt>true</tt> if the button on the joystick is currently pressed, <tt>false</tt> otherwise.
     */
    public static native boolean isButtonPressed(int joystick, int button);

    /**
     * Retrieves the current position of a certain axis of a joystick.
     *
     * @param joystick The index of the joystick in question.
     * @param axis     The axis in question.
     * @return A floating point number ranging between -100 and 100, denoting the current position of the axis.
     */
    public static native float getAxisPosition(int joystick, Axis axis);

    /**
     * Forces an updates of the states of all joysticks.
     * <p/>
     * This method is only required if the joystick state needs to be known before a window has been
     * created. Once there is a window, the states will be automatically updated in regular periods.
     */
    public static native void update();
}
