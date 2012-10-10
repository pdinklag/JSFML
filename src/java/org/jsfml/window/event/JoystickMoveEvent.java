package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.window.Joystick;

/**
 * Event class for event type {@link Event.Type#JOYSTICK_MOVED}.
 */
@Intercom
public final class JoystickMoveEvent extends JoystickEvent {
    /**
     * The joystick axis that was moved.
     */
    public final Joystick.Axis joyAxis;

    /**
     * The current position of the axis that was moved, ranging between -100 and 100.
     */
    public final float position;

    @Intercom
    protected JoystickMoveEvent(int type, int joystickId, int joyAxis, float position) {
        super(type, joystickId);
        this.joyAxis = Joystick.Axis.values()[joyAxis];
        this.position = position;
    }

    @Override
    public JoystickMoveEvent asJoystickMoveEvent() {
        return this;
    }
}
