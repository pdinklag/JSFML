package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.system.Vector2i;

/**
 * Base class for mouse related events and event class for event type {@link Event#MOUSE_MOVED}.
 */
@Intercom
public class MouseEvent extends Event {
    @Intercom
    private int x, y;

    @Intercom
    public MouseEvent(int type, int x, int y) {
        super(type);
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X coordinate of the mouse cursor on the screen.
     *
     * @return The X coordinate of the mouse cursor on the screen.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate of the mouse cursor on the screen.
     *
     * @return The Y coordinate of the mouse cursor on the screen.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the position of the mouse cursor on the screen.
     *
     * @return The position of the mouse cursor on the screen.
     */
    public Vector2i getPosition() {
        return new Vector2i(x, y);
    }
}
