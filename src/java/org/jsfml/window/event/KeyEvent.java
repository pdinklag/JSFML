package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.window.Keyboard;

/**
 * Event class for event types {@link Event.Type#KEY_PRESSED} and {@link Event.Type#KEY_RELEASED}.
 */
@Intercom
public class KeyEvent extends Event {
    private int keyCode;
    private boolean alt, shift, control, system;

    @Intercom
    public KeyEvent(int type, int keyCode, boolean alt, boolean shift, boolean control, boolean system) {
        super(type);
        this.keyCode = keyCode;
        this.alt = alt;
        this.shift = shift;
        this.control = control;
        this.system = system;
    }

    /**
     * Gets the code of the key that was pressed or released.
     *
     * @return The code of the key that was pressed or released.
     */
    public Keyboard.Key getKeyCode() {
        return Keyboard.Key.values()[keyCode];
    }

    /**
     * Checks if the ALT key (left or right) was pressed when the key was pressed or released.
     *
     * @return <tt>true</tt> if the ALT key was pressed, <tt>false</tt> otherwise.
     */
    public boolean isAltPressed() {
        return alt;
    }

    /**
     * Checks if the SHIFT key (left or right) was pressed when the key was pressed or released.
     *
     * @return <tt>true</tt> if the SHIFT key was pressed, <tt>false</tt> otherwise.
     */
    public boolean isShiftPressed() {
        return shift;
    }

    /**
     * Checks if the CTRL key (left or right) was pressed when the key was pressed or released.
     *
     * @return <tt>true</tt> if the CTRL key was pressed, <tt>false</tt> otherwise.
     */
    public boolean isControlPressed() {
        return control;
    }

    /**
     * Checks if the system key (left or right) was pressed when the key was pressed or released.
     *
     * @return <tt>true</tt> if the system key was pressed, <tt>false</tt> otherwise.
     */
    public boolean isSystemPressed() {
        return system;
    }
}
