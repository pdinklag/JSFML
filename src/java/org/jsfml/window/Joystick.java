package org.jsfml.window;

import org.jsfml.SFMLNative;

/**
 * Provides access to the the real-time states of joysticks or gamepads.
 */
public final class Joystick {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * The maximum amount of supported joysticks.
     */
    public static final int JOYSTICK_COUNT = 8;

    /**
     * The maximum amount of supported buttons.
     */
    public static final int BUTTON_COUNT = 32;

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

    private static native boolean nativeIsConnected(int joystick);

    /**
     * Checks if a joystick is connected.
     *
     * @param joystick The index of the joystick to check.
     * @return {@code true} if the joystick is connected, {@code false} otherwise.
     */
    public static boolean isConnected(int joystick) {
        if (joystick < 0 || joystick >= JOYSTICK_COUNT)
            throw new IllegalArgumentException("joystick must be between 0 and " + JOYSTICK_COUNT);

        return nativeIsConnected(joystick);
    }

    private static native int nativeGetButtonCount(int joystick);

    /**
     * Retrieves the button count of a joystick.
     *
     * @param joystick The index of the joystick in question.
     * @return The amount of buttons of the joystick.
     */
    public static int getButtonCount(int joystick) {
        if (joystick < 0 || joystick >= JOYSTICK_COUNT)
            throw new IllegalArgumentException("joystick must be between 0 and " + JOYSTICK_COUNT);

        return nativeGetButtonCount(joystick);
    }

    private static native boolean nativeHasAxis(int joystick, int axis);

    /**
     * Checks whether a joystick has a certain axis controller.
     *
     * @param joystick The index of the joystick in question.
     * @param axis     The axis in question.
     * @return {@code true} if the joystick supports the given axis, {@code false} otherwise.
     */
    public static boolean hasAxis(int joystick, Axis axis) {
        if (joystick < 0 || joystick >= JOYSTICK_COUNT)
            throw new IllegalArgumentException("joystick must be between 0 and " + JOYSTICK_COUNT);

        return nativeHasAxis(joystick, axis.ordinal());
    }

    private static native boolean nativeIsButtonPressed(int joystick, int button);

    /**
     * Checks whether a certain button on the joystick is currently pressed.
     *
     * @param joystick The index of the joystick in question.
     * @param button   The index of the button on the joystick to check.
     * @return {@code true} if the button on the joystick is currently pressed, {@code false} otherwise.
     */
    public static boolean isButtonPressed(int joystick, int button) {
        if (joystick < 0 || joystick >= JOYSTICK_COUNT)
            throw new IllegalArgumentException("joystick must be between 0 and " + JOYSTICK_COUNT);

        if (button < 0 || button >= BUTTON_COUNT)
            throw new IllegalArgumentException("button must be between 0 and " + BUTTON_COUNT);

        return nativeIsButtonPressed(joystick, button);
    }

    private static native float nativeGetAxisPosition(int joystick, int axis);

    /**
     * Retrieves the current position of a certain axis of a joystick.
     *
     * @param joystick The index of the joystick in question.
     * @param axis     The axis in question.
     * @return A floating point number ranging between -100 and 100, denoting the current position of the axis.
     */
    public static float getAxisPosition(int joystick, Axis axis) {
        if (joystick < 0 || joystick >= JOYSTICK_COUNT)
            throw new IllegalArgumentException("joystick must be between 0 and " + JOYSTICK_COUNT);

        return nativeGetAxisPosition(joystick, axis.ordinal());
    }

    /**
     * Forces an updates of the states of all joysticks.
     * <p/>
     * This method is only required if the joystick state needs to be known before a window has been
     * created. Once there is a window, the states will be automatically updated in regular periods.
     */
    public static native void update();

    //cannot instantiate
    private Joystick() {
    }
}
