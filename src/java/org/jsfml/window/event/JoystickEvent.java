package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Abstract base class for all joystick / gamepad events.
 */
@Intercom
public abstract class JoystickEvent extends Event {
    /**
     * The index of the joystick that caused this event.
     */
    public final int joystickId;

    @Intercom
    protected JoystickEvent(int type, int joystickId) {
        super(type);
        this.joystickId = joystickId;
    }

    public int getJoystickId() {
        return joystickId;
    }

    @Override
    public JoystickEvent asJoystickEvent() {
        return this;
    }
}
