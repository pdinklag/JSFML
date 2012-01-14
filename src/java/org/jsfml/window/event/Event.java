package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.JSFMLException;
import org.jsfml.SFMLNative;

/**
 * Base class for events.
 *
 * @see {@link org.jsfml.window.Window#pollEvent()}
 */
@Intercom
public class Event {
    static {
        try {
            SFMLNative.loadNativeLibraries();
        } catch (JSFMLException ex) {
            throw new UnsatisfiedLinkError(ex.getMessage());
        }
    }

    public final static int NONE = -1;
    public final static int CLOSED = 0;
    public final static int RESIZED = 1;
    public final static int LOST_FOCUS = 2;
    public final static int GAINED_FOCUS = 3;
    public final static int TEXT_ENTERED = 4;
    public final static int KEY_PRESSED = 5;
    public final static int KEY_RELEASED = 6;
    public final static int MOUSE_WHEEL_MOVED = 7;
    public final static int MOUSE_BUTTON_PRESSED = 8;
    public final static int MOUSE_BUTTON_RELEASED = 9;
    public final static int MOUSE_MOVED = 10;
    public final static int MOUSE_ENTERED = 11;
    public final static int MOUSE_LEFT = 12;
    public final static int JOYSTICK_BUTTON_PRESSED = 13;
    public final static int JOYSTICK_BUTTON_RELEASED = 14;
    public final static int JOYSTICK_MOVED = 15;
    public final static int JOYSTICK_CONNECETED = 15;
    public final static int JOYSTICK_DISCONNECTED = 15;

    @Intercom
    private int type;

    /**
     * Creates a new event.
     *
     * @param type The event type.
     */
    @Intercom
    public Event(int type) {
        this.type = type;
    }

    /**
     * Retrieves the type of the event.
     *
     * @return The event type, represented by one of the constants defined in this class.
     */
    public int getType() {
        return type;
    }
}
