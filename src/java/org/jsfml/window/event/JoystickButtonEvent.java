package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Event class for event types {@link Event.Type#JOYSTICK_BUTTON_PRESSED} and
 * {@link Event.Type#JOYSTICK_BUTTON_RELEASED}.
 */
@Intercom
public final class JoystickButtonEvent extends JoystickEvent {
    /**
     * The index of the button that was pressed or released.
     */
    public final int button;

    @Intercom
    public JoystickButtonEvent(int type, int joystickId, int button) {
        super(type, joystickId);
        this.button = button;
    }

    @Override
    public JoystickButtonEvent asJoystickButtonEvent() {
        return this;
    }
}
