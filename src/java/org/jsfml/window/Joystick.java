package org.jsfml.window;

import org.jsfml.internal.SFMLNative;

/**
 * Provides access to the the real-time states of joysticks and gamepads.
 * <p/>
 * The methods in this class provide direct access to the joystick state, that means
 * that they work independently of a window's focus. In order to react to window
 * based events, use the  {@link org.jsfml.window.Window#pollEvents()} method instead.
 */
public final class Joystick {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * The maximum amount of supported joysticks and gamepads.
     * <p/>
     * Joystick identifiers are guaranteed to be smaller than this value.
     */
    public static final int JOYSTICK_COUNT = 8;

    /**
     * The maximum amount of supported joystick and gamepad buttons.
     * <p/>
     * Joystick button indices are guaranteed to be smaller than this value.
     */
    public static final int BUTTON_COUNT = 32;

    /**
     * Enumeration of supported joystick and gamepad axes.
     * <p/>
     * The axes in this enumeration are named after common standards. Their
     * representation on a joystick or gamepad may be specific to it. Generally,
     * the earlier an axis appears in this enumeration, the more likely it is
     * supported by a joystick or gamepad.
     */
    public static enum Axis {
        /**
         * The X axis.
         */
        X,

        /**
         * The Y axis.
         */
        Y,

        /**
         * The Z axis.
         */
        Z,

        /**
         * The R axis.
         */
        R,

        /**
         * The U axis.
         */
        U,

        /**
         * The V axis.
         */
        V,

        /**
         * The X axis on the point-of-view hat.
         */
        POV_X,

        /**
         * The Y axis on the point-of-view hat.
         */
        POV_Y
    }

    private static native boolean nativeIsConnected(int joystick);

    /**
     * Checks if a joystick or gamepad is currently connected.
     *
     * @param joystick the index of the joystick to check.
     *                 This value must range between 0 (inclusive) and
     *                 {@code #JOYSTICK_COUNT} (exclusive).
     * @return {@code true} if the joystick or gamepad is currently connected,
     *         {@code false} otherwise.
     */
    public static boolean isConnected(int joystick) {
        if (joystick < 0 || joystick >= JOYSTICK_COUNT)
            throw new IllegalArgumentException("joystick must be between 0 and " + JOYSTICK_COUNT);

        return nativeIsConnected(joystick);
    }

    private static native int nativeGetButtonCount(int joystick);

    /**
     * Retrieves the amount of buttons of a joystick or gamepad.
     *
     * @param joystick the index of the joystick in question.
     *                 This value must range between 0 (inclusive) and
     *                 {@code #JOYSTICK_COUNT} (exclusive).
     * @return the amount of buttons of the joystick.
     */
    public static int getButtonCount(int joystick) {
        if (joystick < 0 || joystick >= JOYSTICK_COUNT)
            throw new IllegalArgumentException("joystick must be between 0 and " + JOYSTICK_COUNT);

        return nativeGetButtonCount(joystick);
    }

    private static native boolean nativeHasAxis(int joystick, int axis);

    /**
     * Checks whether a joystick or gamepad supports the specified axis.
     *
     * @param joystick the index of the joystick in question.
     *                 This value must range between 0 (inclusive) and
     *                 {@code #JOYSTICK_COUNT} (exclusive).
     * @param axis     the axis to look for.
     * @return {@code true} if the joystick or gamepad supports the given axis,
     *         {@code false} otherwise.
     */
    public static boolean hasAxis(int joystick, Axis axis) {
        if (joystick < 0 || joystick >= JOYSTICK_COUNT)
            throw new IllegalArgumentException("joystick must be between 0 and " + JOYSTICK_COUNT);

        return nativeHasAxis(joystick, axis.ordinal());
    }

    private static native boolean nativeIsButtonPressed(int joystick, int button);

    /**
     * Checks whether the specified button is currently pressed on a joystick or gamepad.
     *
     * @param joystick the index of the joystick in question.
     *                 This value must range between 0 (inclusive) and
     *                 {@code #JOYSTICK_COUNT} (exclusive).
     * @param button   the index of the button to check.
     *                 This value must range between 0 (inclusive) and
     *                 {@code #BUTTON_COUNT} (exclusive).
     * @return {@code true} if the button is currently pressed on the joystick or gamepad,
     *         {@code false} otherwise.
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
     * Retrieves the current position of the specified axis of a joystick or gamepad.
     *
     * @param joystick the index of the joystick in question.
     *                 This value must range between 0 (inclusive) and
     *                 {@code #JOYSTICK_COUNT} (exclusive).
     * @param axis     the axis in question.
     * @return a floating point number ranging between -100 and 100,
     *         denoting the current position of the axis. If the joystick or gamepad is
     *         not connected or does not support the specified axis, 0 is returned.
     */
    public static float getAxisPosition(int joystick, Axis axis) {
        if (joystick < 0 || joystick >= JOYSTICK_COUNT)
            throw new IllegalArgumentException("joystick must be between 0 and " + JOYSTICK_COUNT);

        return nativeGetAxisPosition(joystick, axis.ordinal());
    }

    /**
     * Forces an update of the states of all joysticks and gamepads.
     * <p/>
     * This method is only required if the joystick state needs to be known before a window has been
     * created. Once there is a window, the states will be automatically updated in regular periods.
     */
    public static native void update();

    //cannot instantiate
    private Joystick() {
    }
}
