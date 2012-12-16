package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.window.Mouse;

/**
 * Represents mouse button events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#MOUSE_BUTTON_PRESSED} or
 * {@link Event.Type#MOUSE_BUTTON_RELEASED}.
 */
@Intercom
public final class MouseButtonEvent extends MouseEvent {
	/**
	 * The mouse button that was pressed or released.
	 */
	public final Mouse.Button button;

	@Intercom
	MouseButtonEvent(int type, int x, int y, int button) {
		super(type, x, y);
		this.button = Mouse.Button.values()[button];
	}

	@Override
	public MouseButtonEvent asMouseButtonEvent() {
		return this;
	}
}
