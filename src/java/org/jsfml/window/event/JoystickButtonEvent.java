package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Event class for event types {@link Event.Type#JOYSTICK_BUTTON_PRESSED} and
 * {@link Event.Type#JOYSTICK_BUTTON_RELEASED}.
 */
@Intercom
public class JoystickButtonEvent extends JoystickEvent {
    private int button;

    @Intercom
    public JoystickButtonEvent(int type, int joystickId, int button) {
        super(type, joystickId);
        this.button = button;
    }

    /**
     * Retrieves the button that was pressed or released.
     *
     * @return The index of the button that was pressed or released.
     */
    public int getButton() {
        return button;
    }

    @Override
    public JoystickButtonEvent asJoystickButtonEvent() {
        return this;
    }
}
