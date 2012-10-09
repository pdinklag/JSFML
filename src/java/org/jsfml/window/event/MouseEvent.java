package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.system.Vector2i;

/**
 * Base class for mouse related events and event class for event type {@link Event.Type#MOUSE_MOVED}.
 */
@Intercom
public class MouseEvent extends Event {
    /**
     * The position of the mouse pointer on the screen when the event occured.
     */
    public final Vector2i position;

    @Intercom
    protected MouseEvent(int type, int x, int y) {
        super(type);
        this.position = new Vector2i(x, y);
    }

    @Override
    public MouseEvent asMouseEvent() {
        return this;
    }
}
