package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Represents mouse wheel movement events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#MOUSE_WHEEL_MOVED}.
 */
@Intercom
public final class MouseWheelEvent extends MouseEvent {
	/**
	 * The amount of ticks that the mouse wheel was moved.
	 */
	public final int delta;

	@Intercom
	MouseWheelEvent(int type, int x, int y, int delta) {
		super(type, x, y);
		this.delta = delta;
	}

	@Override
	public MouseWheelEvent asMouseWheelEvent() {
		return this;
	}
}
