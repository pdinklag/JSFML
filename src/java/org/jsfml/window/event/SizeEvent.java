package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.system.Vector2i;

/**
 * Event class for event type {@link Event.Type#RESIZED}.
 */
@Intercom
public class SizeEvent extends Event {
    private final Vector2i size;

    @Intercom
    protected SizeEvent(int type, int width, int height) {
        super(type);
        this.size = new Vector2i(width, height);
    }

    /**
     * Returns the new size of the window.
     *
     * @return The new size of the window.
     */
    public Vector2i getSize() {
        return size;
    }

    @Override
    public SizeEvent asSizeEvent() {
        return this;
    }
}
