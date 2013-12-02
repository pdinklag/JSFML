package org.jsfml.window.event;

import org.jsfml.window.Mouse;

/**
 * Represents mouse button events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#MOUSE_BUTTON_PRESSED} or
 * {@link Event.Type#MOUSE_BUTTON_RELEASED}.
 */
public final class MouseButtonEvent extends MouseEvent {
    /**
     * The mouse button that was pressed or released.
     */
    public final Mouse.Button button;

    /**
     * Constructs a new mouse button event.
     *
     * @param type   the type of the event.
     *               This must be a valid ordinal in the {@link Event.Type} enumeration.
     * @param x      the X coordinate of the mouse cursor relative to the window.
     * @param y      the Y coordinate of the mouse cursor relative to the window.
     * @param button the button that was pressed.
     *               This must be a valid ordinal in the {@link org.jsfml.window.Mouse.Button}
     *               enumeration.
     */
    public MouseButtonEvent(int type, int x, int y, int button) {
        super(type, x, y);
        this.button = Mouse.Button.values()[button];
    }

    @Override
    public MouseButtonEvent asMouseButtonEvent() {
        return this;
    }
}
