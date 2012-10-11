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
     * <tt>true</tt> if an ALT key was pressed, <tt>false</tt> otherwise.
     */
    public final boolean alt;

    /**
     * <tt>true</tt> if a SHIFT key was pressed, <tt>false</tt> otherwise.
     */
    public final boolean shift;

    /**
     * <tt>true</tt> if a CTRL key was pressed, <tt>false</tt> otherwise.
     */
    public final boolean control;

    /**
     * <tt>true</tt> if a system key was pressed, <tt>false</tt> otherwise.
     */
    public final boolean system;

    @Intercom
    public KeyEvent(int type, int keyCode, boolean alt, boolean shift, boolean control, boolean system) {
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
