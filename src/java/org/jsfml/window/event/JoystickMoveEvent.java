package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.window.Joystick;

/**
 * Event class for event type {@link Event#JOYSTICK_MOVED}.
 */
@Intercom
public class JoystickMoveEvent extends JoystickEvent {
    @Intercom
    private int joyAxis;

    @Intercom
    private float position;

    @Intercom
    public JoystickMoveEvent(int type, int joystickId, int joyAxis, float position) {
        super(type, joystickId);
        this.joyAxis = joyAxis;
        this.position = position;
    }

    /**
     * Retrieves the joystick axis that was moved.
     *
     * @return The joystick axis that was moved.
     */
    public Joystick.Axis getJoyAxis() {
        return Joystick.Axis.values()[joyAxis];
    }

    /**
     * Retrieves the current position of the axis that was moved.
     *
     * @return The current position of the axis that was moved.
     */
    public float getPosition() {
        return position;
    }
}
