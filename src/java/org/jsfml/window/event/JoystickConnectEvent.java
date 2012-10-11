package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Event class for event types {@link Event.Type#JOYSTICK_CONNECETED} and
 * {@link Event.Type#JOYSTICK_DISCONNECTED}.
 */
@Intercom
public final class JoystickConnectEvent extends JoystickEvent {
    @Intercom
    public JoystickConnectEvent(int type, int joystickId) {
        super(type, joystickId);
    }

    @Override
    public JoystickConnectEvent asJoystickConnectEvent() {
        return this;
    }
}
