package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Event class for event type {@link Event.Type#RESIZED}.
 */
@Intercom
public class SizeEvent extends Event {
    int width, height;

    @Intercom
    public SizeEvent(int type, int width, int height) {
        super(type);
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the new width of the window.
     *
     * @return The new width of the window.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the new height of the window.
     *
     * @return The new height of the window.
     */
    public int getHeight() {
        return height;
    }
}
