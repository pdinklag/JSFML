package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.window.Mouse;

/**
 * Event class for event types {@link Event.Type#MOUSE_BUTTON_PRESSED} and
 * {@link Event.Type#MOUSE_BUTTON_RELEASED}.
 */
@Intercom
public final class MouseButtonEvent extends MouseEvent {
    /**
     * The mouse button that was pressed or released.
     */
    public final Mouse.Button button;

    @Intercom
    protected MouseButtonEvent(int type, int x, int y, int button) {
        super(type, x, y);
        this.button = Mouse.Button.values()[button];
    }

    @Override
    public MouseButtonEvent asMouseButtonEvent() {
        return this;
    }
}
