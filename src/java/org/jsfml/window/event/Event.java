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

    /**
     * Fired when the user clicked a {@link org.jsfml.window.Window}'s close button. Creates a {@link Event}.
     */
    public final static int CLOSED = 0;

    /**
     * Fired when a {@link org.jsfml.window.Window} was resized. Creates a {@link Event}.
     */
    public final static int RESIZED = 1;

    /**
     * Fired when a {@link org.jsfml.window.Window} lost focus. Creates a {@link Event}.
     */
    public final static int LOST_FOCUS = 2;

    /**
     * Fired when a {@link org.jsfml.window.Window} gained focus. Creates a {@link Event}.
     */
    public final static int GAINED_FOCUS = 3;

    /**
     * Fired when a text character was entered using the keyboard. Creates a {@link TextEvent}.
     */
    public final static int TEXT_ENTERED = 4;

    /**
     * Fired when a keyboard key was pressed. Creates a {@link KeyEvent}.
     */
    public final static int KEY_PRESSED = 5;

    /**
     * Fired when a keyboard key was released. Creates a {@link KeyEvent}.
     */
    public final static int KEY_RELEASED = 6;

    /**
     * Fired when the mouse wheel was moved. Creates a {@link MouseWheelEvent}.
     */
    public final static int MOUSE_WHEEL_MOVED = 7;


    /**
     * Fired when the mouse button was pressed. Creates a {@link MouseButtonEvent}.
     */
    public final static int MOUSE_BUTTON_PRESSED = 8;

    /**
     * Fired when the mouse button was released. Creates a {@link MouseButtonEvent}.
     */
    public final static int MOUSE_BUTTON_RELEASED = 9;

    /**
     * Fired when the mouse cursor was moved. Creates a {@link MouseEvent}.
     */
    public final static int MOUSE_MOVED = 10;

    /**
     * Fired when the mouse cursor entered the {@link org.jsfml.window.Window}. Creates a {@link MouseEvent}.
     */
    public final static int MOUSE_ENTERED = 11;

    /**
     * Fired when the mouse cursor left the {@link org.jsfml.window.Window}. Creates a {@link MouseEvent}.
     */
    public final static int MOUSE_LEFT = 12;

    /**
     * Fired when a joystick button was pressed. Creates a {@link JoystickButtonEvent}.
     */
    public final static int JOYSTICK_BUTTON_PRESSED = 13;

    /**
     * Fired when a joystick button was released. Creates a {@link JoystickButtonEvent}.
     */
    public final static int JOYSTICK_BUTTON_RELEASED = 14;

    /**
     * Fired when a joystick axis was moved. Creates a {@link JoystickMoveEvent}.
     */
    public final static int JOYSTICK_MOVED = 15;

    /**
     * Fired when a joystick was connected. Creates a {@link JoystickConnectEvent}.
     */
    public final static int JOYSTICK_CONNECETED = 16;

    /**
     * Fired when a joystick was connected. Creates a {@link JoystickConnectEvent}.
     */
    public final static int JOYSTICK_DISCONNECTED = 17;

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
