package org.jsfml.window;

import org.jsfml.internal.SFMLNative;

/**
 * Provides access to the the real-time state of the keyboard.
 * <p/>
 * The methods in this class provide direct access to the keyboard state, that means
 * that they work independently of a window's focus. In order to react to window
 * based events, use the  {@link org.jsfml.window.Window#pollEvents()} method instead.
 */
public final class Keyboard {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Enumeration of supported keys.
     * <p/>
     * The keys in this enumeration are named after the standard <i>QWERTY</i>
     * keyboard layout. Their locations and labels may vary on different keyboard
     * layouts, even though the scan codes, and therefore their representation
     * in this enumeration, are the same.
     * <p/>
     * There may be some non-standard keys on certain keyboards that are
     * mapped to unknown scan codes. Those special keys will be represented
     * by {@link Key#UNKNOWN}.
     */
    public static enum Key {
        /**
         * A keyboard specific key which maps to an unknown scan code.
         */
        UNKNOWN,
        /**
         * The {@code A} key.
         */
        A,

        /**
         * The {@code B} key.
         */
        B,

        /**
         * The {@code C} key.
         */
        C,

        /**
         * The {@code D} key.
         */
        D,

        /**
         * The {@code E} key.
         */
        E,

        /**
         * The {@code F} key.
         */
        F,

        /**
         * The {@code G} key.
         */
        G,

        /**
         * The {@code H} key.
         */
        H,

        /**
         * The {@code I} key.
         */
        I,

        /**
         * The {@code J} key.
         */
        J,

        /**
         * The {@code K} key.
         */
        K,

        /**
         * The {@code L} key.
         */
        L,

        /**
         * The {@code M} key.
         */
        M,

        /**
         * The {@code N} key.
         */
        N,

        /**
         * The {@code O} key.
         */
        O,

        /**
         * The {@code P} key.
         */
        P,

        /**
         * The {@code Q} key.
         */
        Q,

        /**
         * The {@code R} key.
         */
        R,

        /**
         * The {@code S} key.
         */
        S,

        /**
         * The {@code T} key.
         */
        T,

        /**
         * The {@code U} key.
         */
        U,

        /**
         * The {@code V} key.
         */
        V,

        /**
         * The {@code W} key.
         */
        W,

        /**
         * The {@code X} key.
         */
        X,

        /**
         * The {@code Y} key.
         */
        Y,

        /**
         * The {@code Z} key.
         */
        Z,

        /**
         * The {@code 0} key.
         * For the {@code 0} key on the numeric keypad, see {@link #NUMPAD0}.
         */
        NUM0,

        /**
         * The {@code 1} key.
         * For the {@code 1} key on the numeric keypad, see {@link #NUMPAD1}.
         */
        NUM1,

        /**
         * The {@code 2} key.
         * For the {@code 2} key on the numeric keypad, see {@link #NUMPAD2}.
         */
        NUM2,

        /**
         * The {@code 3} key.
         * For the {@code 3} key on the numeric keypad, see {@link #NUMPAD3}.
         */
        NUM3,

        /**
         * The {@code 4} key.
         * For the {@code 4} key on the numeric keypad, see {@link #NUMPAD4}.
         */
        NUM4,

        /**
         * The {@code 5} key.
         * For the {@code 5} key on the numeric keypad, see {@link #NUMPAD5}.
         */
        NUM5,

        /**
         * The {@code 6} key.
         * For the {@code 6} key on the numeric keypad, see {@link #NUMPAD6}.
         */
        NUM6,

        /**
         * The {@code 7} key.
         * For the {@code 7} key on the numeric keypad, see {@link #NUMPAD7}.
         */
        NUM7,

        /**
         * The {@code 8} key.
         * For the {@code 8} key on the numeric keypad, see {@link #NUMPAD8}.
         */
        NUM8,

        /**
         * The {@code 9} key.
         * For the {@code 9} key on the numeric keypad, see {@link #NUMPAD9}.
         */
        NUM9,

        /**
         * The escape key.
         */
        ESCAPE,

        /**
         * The left Ctrl key.
         */
        LCONTROL,

        /**
         * The left Shift key.
         */
        LSHIFT,

        /**
         * The left Alt key.
         */
        LALT,

        /**
         * The left system key.
         * This is commonly known as the Windows logo key or the Apple key.
         */
        LSYSTEM,

        /**
         * The right Ctrl key.
         */
        RCONTROL,

        /**
         * The right Shift key.
         */
        RSHIFT,

        /**
         * The right Alt key.
         */
        RALT,

        /**
         * The right system key.
         * This is commonly known as the Windows logo key or the Apple key.
         */
        RSYSTEM,

        /**
         * The menu key.
         */
        MENU,

        /**
         * The {@code [} key.
         */
        LBRACKET,

        /**
         * The {@code ]} key.
         */
        RBRACKET,

        /**
         * The {@code ;} key.
         */
        SEMICOLON,

        /**
         * The {@code ,} key.
         */
        COMMA,

        /**
         * {@code .} key.
         */
        PERIOD,

        /**
         * The {@code '} key.
         */
        QUOTE,

        /**
         * The {@code /} key.
         */
        SLASH,

        /**
         * The {@code \} key.
         */
        BACKSLASH,

        /**
         * The {@code ~} key.
         */
        TILDE,

        /**
         * The {@code =} key.
         */
        EQUAL,

        /**
         * The {@code -} key.
         */
        DASH,

        /**
         * The Space key.
         */
        SPACE,

        /**
         * The Return key or the Enter key on the numeric keypad.
         * Both keys send the same scancode and can therefore not be distinguished.
         */
        RETURN,

        /**
         * The Backspace key.
         */
        BACKSPACE,

        /**
         * The Tab key.
         */
        TAB,

        /**
         * The Page up key.
         */
        PAGEUP,

        /**
         * The Page down key.
         */
        PAGEDOWN,

        /**
         * The End key.
         */
        END,

        /**
         * The Home key.
         */
        HOME,

        /**
         * The Insert key.
         */
        INSERT,

        /**
         * The Delete key.
         */
        DELETE,

        /**
         * The {@code +} (addition) key on the numeric keypad.
         */
        ADD,

        /**
         * The {@code -} (subtraction) key on the numeric keypad.
         */
        SUBTRACT,

        /**
         * The {@code *} (multiplication) key on the numeric keypad.
         */
        MULTIPLY,

        /**
         * The {@code /} (division) key on the numeric keypad.
         */
        DIVIDE,

        /**
         * The left arrow key.
         */
        LEFT,

        /**
         * The right arrow key.
         */
        RIGHT,

        /**
         * The up arrow key.
         */
        UP,

        /**
         * The down arrow key.
         */
        DOWN,

        /**
         * The {@code 0} key on the numeric keypad.
         * For the regular {@code 0} key, see {@link #NUM0}.
         */
        NUMPAD0,

        /**
         * The {@code 1} key on the numeric keypad.
         * For the regular {@code 1} key, see {@link #NUM1}.
         */
        NUMPAD1,

        /**
         * The {@code 2} key on the numeric keypad.
         * For the regular {@code 2} key, see {@link #NUM2}.
         */
        NUMPAD2,

        /**
         * The {@code 3} key on the numeric keypad.
         * For the regular {@code 3} key, see {@link #NUM3}.
         */
        NUMPAD3,

        /**
         * The {@code 4} key on the numeric keypad.
         * For the regular {@code 4} key, see {@link #NUM4}.
         */
        NUMPAD4,

        /**
         * The {@code 5} key on the numeric keypad.
         * For the regular {@code 5} key, see {@link #NUM5}.
         */
        NUMPAD5,

        /**
         * The {@code 6} key on the numeric keypad.
         * For the regular {@code 6} key, see {@link #NUM6}.
         */
        NUMPAD6,

        /**
         * The {@code 7} key on the numeric keypad.
         * For the regular {@code 7} key, see {@link #NUM7}.
         */
        NUMPAD7,

        /**
         * The {@code 8} key on the numeric keypad.
         * For the regular {@code 8} key, see {@link #NUM8}.
         */
        NUMPAD8,

        /**
         * The {@code 9} key on the numeric keypad.
         * For the regular {@code 9} key, see {@link #NUM9}.
         */
        NUMPAD9,

        /**
         * The {@code F1} key.
         */
        F1,

        /**
         * The {@code F2} key.
         */
        F2,

        /**
         * The {@code F3} key.
         */
        F3,

        /**
         * The {@code F4} key.
         */
        F4,

        /**
         * The {@code F5} key.
         */
        F5,

        /**
         * The {@code F6} key.
         */
        F6,

        /**
         * The {@code F7} key.
         */
        F7,

        /**
         * The {@code F8} key.
         */
        F8,

        /**
         * The {@code F9} key.
         */
        F9,

        /**
         * The {@code F10} key.
         */
        F10,

        /**
         * The {@code F11} key.
         */
        F11,

        /**
         * The {@code F12} key.
         */
        F12,

        /**
         * The {@code F13} key, where available.
         */
        F13,

        /**
         * The {@code F14} key, where available.
         */
        F14,

        /**
         * The {@code F15} key, where available.
         */
        F15,

        /**
         * The Pause key.
         */
        PAUSE
    }

    private static native boolean nativeIsKeyPressed(int key);

    /**
     * Checks if a certain key is currently pressed on the keyboard.
     *
     * @param key the key in question.
     * @return {@code true} if the key is currently being pressed,
     *         {@code false} otherwise.
     */
    public static boolean isKeyPressed(Key key) {
        return nativeIsKeyPressed(key.ordinal() - 1);
    }

    //cannot instantiate
    private Keyboard() {
    }
}
