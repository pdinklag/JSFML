package org.jsfml.window.event;

import org.jsfml.Intercom;
import org.jsfml.JSFMLException;
import org.jsfml.SFMLNative;

/**
 * Base class for events.
 *
 * @see org.jsfml.window.Window#pollEvent()
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
     * Attempts to cast this event to the given type.
     *
     * @param clazz The event class to cast this event to.
     * @param <T>   The destination type.
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    @SuppressWarnings("unchecked")
    public <T extends Event> T as(Class<T> clazz) {
        try {
            return (T) this;
        } catch (ClassCastException ex) {
            return null;
        }
    }

    /**
     * Attempts to cast this event to a {@link JoystickButtonEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public JoystickButtonEvent asJoystickButtonEvent() {
        return as(JoystickButtonEvent.class);
    }

    /**
     * Attempts to cast this event to a {@link JoystickConnectEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public JoystickConnectEvent asJoystickConnectEvent() {
        return as(JoystickConnectEvent.class);
    }

    /**
     * Attempts to cast this event to a {@link JoystickEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public JoystickEvent asJoystickEvent() {
        return as(JoystickConnectEvent.class);
    }

    /**
     * Attempts to cast this event to a {@link JoystickMoveEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public JoystickMoveEvent asJoystickMoveEvent() {
        return as(JoystickMoveEvent.class);
    }

    /**
     * Attempts to cast this event to a {@link KeyEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public KeyEvent asKeyEvent() {
        return as(KeyEvent.class);
    }

    /**
     * Attempts to cast this event to a {@link MouseButtonEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public MouseButtonEvent asMouseButtonEvent() {
        return as(MouseButtonEvent.class);
    }

    /**
     * Attempts to cast this event to a {@link MouseEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public MouseEvent asMouseEvent() {
        return as(MouseEvent.class);
    }

    /**
     * Attempts to cast this event to a {@link MouseWheelEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public MouseWheelEvent asMouseWheelEvent() {
        return as(MouseWheelEvent.class);
    }

    /**
     * Attempts to cast this event to a {@link SizeEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public SizeEvent asSizeEvent() {
        return as(SizeEvent.class);
    }

    /**
     * Attempts to cast this event to a {@link TextEvent}.
     *
     * @return The casted event, or <tt>null</tt> if casting is not possible.
     */
    public TextEvent asTextEvent() {
        return as(TextEvent.class);
    }
}
