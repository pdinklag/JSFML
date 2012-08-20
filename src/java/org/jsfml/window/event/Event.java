package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.SFMLNative;

/**
 * Base class for events.
 *
 * @see org.jsfml.window.Window#pollEvent()
 */
@Intercom
public class Event {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Enumerations of event types.
     */
    public static enum Type {
        /**
         * Fired when the user clicked a {@link org.jsfml.window.Window}'s close button. Creates a {@link Event}.
         */
        CLOSED,

        /**
         * Fired when a {@link org.jsfml.window.Window} was resized. Creates a {@link Event}.
         */
        RESIZED,

        /**
         * Fired when a {@link org.jsfml.window.Window} lost focus. Creates a {@link Event}.
         */
        LOST_FOCUS,

        /**
         * Fired when a {@link org.jsfml.window.Window} gained focus. Creates a {@link Event}.
         */
        GAINED_FOCUS,

        /**
         * Fired when a text character was entered using the keyboard. Creates a {@link TextEvent}.
         */
        TEXT_ENTERED,

        /**
         * Fired when a keyboard key was pressed. Creates a {@link KeyEvent}.
         */
        KEY_PRESSED,

        /**
         * Fired when a keyboard key was released. Creates a {@link KeyEvent}.
         */
        KEY_RELEASED,

        /**
         * Fired when the mouse wheel was moved. Creates a {@link MouseWheelEvent}.
         */
        MOUSE_WHEEL_MOVED,


        /**
         * Fired when the mouse button was pressed. Creates a {@link MouseButtonEvent}.
         */
        MOUSE_BUTTON_PRESSED,

        /**
         * Fired when the mouse button was released. Creates a {@link MouseButtonEvent}.
         */
        MOUSE_BUTTON_RELEASED,

        /**
         * Fired when the mouse cursor was moved. Creates a {@link MouseEvent}.
         */
        MOUSE_MOVED,

        /**
         * Fired when the mouse cursor entered the {@link org.jsfml.window.Window}. Creates a {@link MouseEvent}.
         */
        MOUSE_ENTERED,

        /**
         * Fired when the mouse cursor left the {@link org.jsfml.window.Window}. Creates a {@link MouseEvent}.
         */
        MOUSE_LEFT,

        /**
         * Fired when a joystick button was pressed. Creates a {@link JoystickButtonEvent}.
         */
        JOYSTICK_BUTTON_PRESSED,

        /**
         * Fired when a joystick button was released. Creates a {@link JoystickButtonEvent}.
         */
        JOYSTICK_BUTTON_RELEASED,

        /**
         * Fired when a joystick axis was moved. Creates a {@link JoystickMoveEvent}.
         */
        JOYSTICK_MOVED,

        /**
         * Fired when a joystick was connected. Creates a {@link JoystickConnectEvent}.
         */
        JOYSTICK_CONNECETED,

        /**
         * Fired when a joystick was connected. Creates a {@link JoystickConnectEvent}.
         */
        JOYSTICK_DISCONNECTED
    }

    private Type type;

    /**
     * Creates a new event.
     *
     * @param type The event type.
     */
    @Intercom
    public Event(int type) {
        this.type = Type.values()[type];
    }

    /**
     * Retrieves the type of the event.
     *
     * @return The event type, represented by one of the constants defined in this class.
     */
    public Type getType() {
        return type;
    }

    /**
     * Attempts to cast this event to a {@link JoystickButtonEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public JoystickButtonEvent asJoystickButtonEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link JoystickConnectEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public JoystickConnectEvent asJoystickConnectEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link JoystickEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public JoystickEvent asJoystickEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link JoystickMoveEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public JoystickMoveEvent asJoystickMoveEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link KeyEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public KeyEvent asKeyEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link MouseButtonEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public MouseButtonEvent asMouseButtonEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link MouseEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public MouseEvent asMouseEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link MouseWheelEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public MouseWheelEvent asMouseWheelEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link SizeEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public SizeEvent asSizeEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link TextEvent}.
     *
     * @return The cast event, or <tt>null</tt> if casting was not possible.
     */
    public TextEvent asTextEvent() {
        return null;
    }
}
