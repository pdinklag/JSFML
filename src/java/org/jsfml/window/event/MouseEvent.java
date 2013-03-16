package org.jsfml.window.event;

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
public class MouseEvent extends Event {
    /**
     * The position of the mouse pointer in pixels, relative to the window's top left corner.
     */
    public final Vector2i position;

    /**
     * /**
     * Constructs a new mouse button event.
     *
     * @param type the type of the event.
     *             This must be a valid ordinal in the {@link Event.Type} enumeration.
     * @param x    the X coordinate of the mouse cursor relative to the window.
     * @param y    the Y coordinate of the mouse cursor relative to the window.
     */
    public MouseEvent(int type, int x, int y) {
        super(type);
        this.position = new Vector2i(x, y);
    }

    @Override
    public MouseEvent asMouseEvent() {
        return this;
    }
}
