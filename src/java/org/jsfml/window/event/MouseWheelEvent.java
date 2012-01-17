package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Event class for event type {@link Event.Type#MOUSE_WHEEL_MOVED}.
 */
@Intercom
public class MouseWheelEvent extends MouseEvent {
    private int delta;

    @Intercom
    public MouseWheelEvent(int type, int x, int y, int delta) {
        super(type, x, y);
        this.delta = delta;
    }

    /**
     * Retrieves the amount of ticks the mouse wheel has been moved.
     *
     * @return The amount of ticks the mouse wheel has been moved.
     */
    public int getDelta() {
        return delta;
    }
}
