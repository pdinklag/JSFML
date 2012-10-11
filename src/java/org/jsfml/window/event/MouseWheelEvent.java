package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Event class for event type {@link Event.Type#MOUSE_WHEEL_MOVED}.
 */
@Intercom
public final class MouseWheelEvent extends MouseEvent {
    /**
     * The amount of ticks the mouse wheel has been moved.
     */
    public final int delta;

    @Intercom
    public MouseWheelEvent(int type, int x, int y, int delta) {
        super(type, x, y);
        this.delta = delta;
    }

    @Override
    public MouseWheelEvent asMouseWheelEvent() {
        return this;
    }
}
