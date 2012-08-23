package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.window.Mouse;

/**
 * Event class for event types {@link Event.Type#MOUSE_BUTTON_PRESSED} and
 * {@link Event.Type#MOUSE_BUTTON_RELEASED}.
 */
@Intercom
public class MouseButtonEvent extends MouseEvent {
    private final int button;

    @Intercom
    protected MouseButtonEvent(int type, int x, int y, int button) {
        super(type, x, y);
        this.button = button;
    }

    /**
     * Retrieves the mouse button that was pressed or released.
     *
     * @return The mouse button that was pressed or released.
     */
    public Mouse.Button getButton() {
        return Mouse.Button.values()[button];
    }

    @Override
    public MouseButtonEvent asMouseButtonEvent() {
        return this;
    }
}
