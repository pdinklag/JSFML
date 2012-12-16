package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.system.Vector2i;

/**
 * Represents generic mouse events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#MOUSE_ENTERED},
 * {@link Event.Type#MOUSE_MOVED},
 * {@link Event.Type#MOUSE_LEFT},
 * {@link Event.Type#MOUSE_WHEEL_MOVED},
 * {@link Event.Type#MOUSE_BUTTON_PRESSED} or
 * {@link Event.Type#MOUSE_BUTTON_RELEASED}.
 */
@Intercom
public class MouseEvent extends Event {
	/**
	 * The position of the mouse pointer in pixels, relative to the window's top left corner.
	 */
	public final Vector2i position;

	@Intercom
	MouseEvent(int type, int x, int y) {
		super(type);
		this.position = new Vector2i(x, y);
	}

	@Override
	public MouseEvent asMouseEvent() {
		return this;
	}
}
