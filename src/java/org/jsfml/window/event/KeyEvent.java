package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.window.Keyboard;

/**
 * Event class for event types {@link Event.Type#KEY_PRESSED} and {@link Event.Type#KEY_RELEASED}.
 */
@Intercom
public final class KeyEvent extends Event {
    /**
     * The code of the key that was pressed or released.
     */
    public final Keyboard.Key key;

    /**
     * {@code true} if an ALT key was pressed, {@code false} otherwise.
     */
    public final boolean alt;

    /**
     * {@code true} if a SHIFT key was pressed, {@code false} otherwise.
     */
    public final boolean shift;

    /**
     * {@code true} if a CTRL key was pressed, {@code false} otherwise.
     */
    public final boolean control;

    /**
     * {@code true} if a system key was pressed, {@code false} otherwise.
     */
    public final boolean system;

    @Intercom
    protected KeyEvent(int type, int keyCode, boolean alt, boolean shift, boolean control, boolean system) {
        super(type);
        this.key = Keyboard.Key.values()[keyCode + 1];
        this.alt = alt;
        this.shift = shift;
        this.control = control;
        this.system = system;
    }

    @Override
    public KeyEvent asKeyEvent() {
        return this;
    }
}
