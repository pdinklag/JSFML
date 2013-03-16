package org.jsfml.window.event;

/**
 * Represents mouse wheel movement events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#MOUSE_WHEEL_MOVED}.
 */
public final class MouseWheelEvent extends MouseEvent {
    /**
     * The amount of ticks that the mouse wheel was moved.
     */
    public final int delta;

    /**
     * Constructs a new mouse wheel event.
     *
     * @param type  the type of the event.
     *              This must be a valid ordinal in the {@link Event.Type} enumeration.
     * @param x     the X coordinate of the mouse cursor relative to the window.
     * @param y     the Y coordinate of the mouse cursor relative to the window.
     * @param delta the amount of ticks that the mouse wheel was moved.
     */
    public MouseWheelEvent(int type, int x, int y, int delta) {
        super(type, x, y);
        this.delta = delta;
    }

    @Override
    public MouseWheelEvent asMouseWheelEvent() {
        return this;
    }
}
