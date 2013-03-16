package org.jsfml.window.event;

/**
 * Base class for window events.
 * <p/>
 * Window events must be polled from a window on a regular basis to keep
 * the window responsive.
 * <p/>
 * Events are of a certain type, some of which provide additional data. For
 * various special types, subclasses of this class exist. This class provides
 * convenience methods to cast {@code Event} objects to one of these special
 * subclasses (e.g. {@link #asKeyEvent()}, which will cast the event to a {@code KeyEvent},
 * if possible).
 *
 * @see org.jsfml.window.Window#pollEvents()
 * @see Type
 */
public class Event {
    /**
     * Enumeration of available window event types.
     */
    public static enum Type {
        /**
         * Fired when the user clicked on the window's close button.
         */
        CLOSED,

        /**
         * Fired when the window was resized.
         */
        RESIZED,

        /**
         * Fired when the window lost focus.
         */
        LOST_FOCUS,

        /**
         * Fired when the window gained focus.
         */
        GAINED_FOCUS,

        /**
         * Fired when a text character was entered using the keyboard while the
         * window had focus.
         * <p/>
         * Events of this type can be cast to {@link TextEvent} using the
         * {@link #asTextEvent()} method.
         */
        TEXT_ENTERED,

        /**
         * Fired when a keyboard key was pressed while the window had focus.
         * <p/>
         * Events of this type can be cast to {@link KeyEvent} using the
         * {@link #asKeyEvent()} method.
         */
        KEY_PRESSED,

        /**
         * Fired when a keyboard key was released while the window had focus.
         * <p/>
         * Events of this type can be cast to {@link KeyEvent} using the
         * {@link #asKeyEvent()} method.
         */
        KEY_RELEASED,

        /**
         * Fired when the mouse wheel was moved while the window had focus.
         * <p/>
         * Events of this type can be cast to {@link MouseWheelEvent} using the
         * {@link #asMouseWheelEvent()} method.
         */
        MOUSE_WHEEL_MOVED,

        /**
         * Fired when a mouse button was pressed while the window had focus.
         * <p/>
         * Events of this type can be cast to {@link MouseButtonEvent} using the
         * {@link #asMouseButtonEvent()} method.
         */
        MOUSE_BUTTON_PRESSED,

        /**
         * Fired when a mouse button was released while the window had focus.
         * <p/>
         * Events of this type can be cast to {@link MouseButtonEvent} using the
         * {@link #asMouseButtonEvent()} method.
         */
        MOUSE_BUTTON_RELEASED,

        /**
         * Fired when the mouse cursor was moved within the window's boundaries.
         * <p/>
         * Events of this type can be cast to {@link MouseEvent} using the
         * {@link #asMouseEvent()} method.
         */
        MOUSE_MOVED,

        /**
         * Fired when the mouse cursor entered the window's boundaries.
         * <p/>
         * Events of this type can be cast to {@link MouseEvent} using the
         * {@link #asMouseEvent()} method.
         */
        MOUSE_ENTERED,

        /**
         * Fired when the mouse cursor left the window's boundaries.
         * <p/>
         * Events of this type can be cast to {@link MouseEvent} using the
         * {@link #asMouseEvent()} method.
         */
        MOUSE_LEFT,

        /**
         * Fired when a joystick or gamepad button was pressed while the window
         * had focus.
         * <p/>
         * Events of this type can be cast to {@link JoystickButtonEvent} using the
         * {@link #asJoystickButtonEvent()} method.
         */
        JOYSTICK_BUTTON_PRESSED,

        /**
         * Fired when a joystick or gamepad button was released while the window
         * had focus.
         * <p/>
         * Events of this type can be cast to {@link JoystickButtonEvent} using the
         * {@link #asJoystickButtonEvent()} method.
         */
        JOYSTICK_BUTTON_RELEASED,

        /**
         * Fired when a joystick or gamepad axis was moved while the window
         * had focus.
         * <p/>
         * Events of this type can be cast to {@link JoystickMoveEvent} using the
         * {@link #asJoystickMoveEvent()} method.
         */
        JOYSTICK_MOVED,

        /**
         * Fired when a joystick or gamepad was connected.
         * <p/>
         * Events of this type can be cast to {@link JoystickEvent} using the
         * {@link #asJoystickEvent()} method.
         */
        JOYSTICK_CONNECETED,

        /**
         * Fired when a joystick or gamepad was disconnected.
         * <p/>
         * Events of this type can be cast to {@link JoystickEvent} using the
         * {@link #asJoystickEvent()} method.
         */
        JOYSTICK_DISCONNECTED
    }

    /**
     * The type of the window event.
     *
     * @see Type
     */
    public final Type type;

    /**
     * Constructs a new event.
     *
     * @param type the type of the event.
     *             This must be a valid ordinal in the {@link Event.Type} enumeration.
     */
    public Event(int type) {
        this.type = Type.values()[type];
    }

    /**
     * Attempts to cast this event to a {@link JoystickButtonEvent}.
     *
     * @return the event cast to a {@code JoystickButtonEvent} if its type is either
     *         {@link Type#JOYSTICK_BUTTON_PRESSED} or {@link Type#JOYSTICK_BUTTON_RELEASED},
     *         {@code null} otherwise.
     */
    public JoystickButtonEvent asJoystickButtonEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link JoystickEvent}.
     *
     * @return the event cast to a {@code JoystickEvent} if its type is one of
     *         {@link Type#JOYSTICK_CONNECETED}, {@link Type#JOYSTICK_DISCONNECTED},
     *         {@link Type#JOYSTICK_BUTTON_PRESSED}, {@link Type#JOYSTICK_BUTTON_RELEASED} or
     *         {@link Type#JOYSTICK_MOVED}, {@code null} otherwise.
     */
    public JoystickEvent asJoystickEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link JoystickMoveEvent}.
     *
     * @return the event cast to a {@code JoystickButtonEvent} if its type is
     *         {@link Type#JOYSTICK_MOVED}, {@code null} otherwise.
     */
    public JoystickMoveEvent asJoystickMoveEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link KeyEvent}.
     *
     * @return the event cast to a {@code KeyEvent} if its type is either
     *         {@link Type#KEY_PRESSED} or {@link Type#KEY_RELEASED},
     *         {@code null} otherwise.
     */
    public KeyEvent asKeyEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link MouseButtonEvent}.
     *
     * @return the event cast to a {@code MouseButtonEvent} if its type is either
     *         {@link Type#MOUSE_BUTTON_PRESSED} or {@link Type#MOUSE_BUTTON_RELEASED},
     *         {@code null} otherwise.
     */
    public MouseButtonEvent asMouseButtonEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link MouseEvent}.
     *
     * @return the event cast to a {@code MouseEvent} if its type is one of
     *         {@link Type#MOUSE_ENTERED}, {@link Type#MOUSE_MOVED},
     *         {@link Type#MOUSE_LEFT}, {@link Type#MOUSE_BUTTON_PRESSED},
     *         {@link Type#MOUSE_BUTTON_RELEASED} or {@link Type#MOUSE_WHEEL_MOVED},
     *         {@code null} otherwise.
     */
    public MouseEvent asMouseEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link MouseWheelEvent}.
     *
     * @return the event cast to a {@code MouseWheelEvent} if its type is
     *         {@link Type#MOUSE_WHEEL_MOVED}, {@code null} otherwise.
     */
    public MouseWheelEvent asMouseWheelEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link SizeEvent}.
     *
     * @return the event cast to a {@code SizeEvent} if its type is
     *         {@link Type#RESIZED}, {@code null} otherwise.
     */
    public SizeEvent asSizeEvent() {
        return null;
    }

    /**
     * Attempts to cast this event to a {@link TextEvent}.
     *
     * @return the event cast to a {@code TextEvent} if its type is
     *         {@link Type#TEXT_ENTERED}, {@code null} otherwise.
     */
    public TextEvent asTextEvent() {
        return null;
    }
}
