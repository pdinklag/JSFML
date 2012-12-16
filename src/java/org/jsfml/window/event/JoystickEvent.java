package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Represents generic joystick or gamepad events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#JOYSTICK_CONNECETED},
 * {@link Event.Type#JOYSTICK_DISCONNECTED},
 * {@link Event.Type#JOYSTICK_BUTTON_PRESSED},
 * {@link Event.Type#JOYSTICK_BUTTON_RELEASED} or
 * {@link Event.Type#JOYSTICK_MOVED}.
 */
@Intercom
public class JoystickEvent extends Event {
	/**
	 * The index of the joystick that caused this event.
	 * <p/>
	 * The value is guaranteed to range between 0 (inclusive) and
	 * {@link org.jsfml.window.Joystick#JOYSTICK_COUNT} (exclusive).
	 */
	public final int joystickId;

	@Intercom
	JoystickEvent(int type, int joystickId) {
		super(type);
		this.joystickId = joystickId;
	}

	@Override
	public JoystickEvent asJoystickEvent() {
		return this;
	}
}
