package org.jsfml.window.event;

import org.jsfml.window.Joystick;

/**
 * Represents joystick or gamepad axis movement events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#JOYSTICK_MOVED}.
 */
public final class JoystickMoveEvent extends JoystickEvent {
    /**
     * The joystick or gamepad axis that was moved.
     */
    public final Joystick.Axis joyAxis;

    /**
     * The position that the axis was moved to, ranging between -100 and 100.
     */
    public final float position;

    /**
     * Constructs a new joystick axis event.
     *
     * @param type       the type of the event.
     *                   This must be a valid ordinal in the {@link Event.Type} enumeration.
     * @param joystickId the joystick ID.
     * @param joyAxis    the joystick axis that was moved.
     *                   This must be a valid ordinal in the {@link org.jsfml.window.Joystick.Axis}
     *                   enumeration.
     * @param position   the position that the axis was moved to.
     */
    public JoystickMoveEvent(int type, int joystickId, int joyAxis, float position) {
        super(type, joystickId);
        this.joyAxis = Joystick.Axis.values()[joyAxis];
        this.position = position;
    }

    @Override
    public JoystickMoveEvent asJoystickMoveEvent() {
        return this;
    }
}
