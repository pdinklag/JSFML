package org.jsfml.window.event;

/**
 * Represents joystick or gamepad button events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#JOYSTICK_BUTTON_PRESSED} or
 * {@link Event.Type#JOYSTICK_BUTTON_RELEASED}.
 */
public final class JoystickButtonEvent extends JoystickEvent {
    /**
     * The index of the button that was pressed or released.
     * <p/>
     * The value is guaranteed to range between 0 (inclusive) and
     * {@link org.jsfml.window.Joystick#BUTTON_COUNT} (exclusive).
     */
    public final int button;

    /**
     * Constructs a new joystick button event.
     *
     * @param type       the type of the event.
     *                   This must be a valid ordinal in the {@link Event.Type} enumeration.
     * @param joystickId the joystick ID.
     * @param button     the index of the button that was pressed.
     */
    public JoystickButtonEvent(int type, int joystickId, int button) {
        super(type, joystickId);
        this.button = button;
    }

    @Override
    public JoystickButtonEvent asJoystickButtonEvent() {
        return this;
    }
}
