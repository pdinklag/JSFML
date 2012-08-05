package org.jsfml.window;

import org.jsfml.JSFMLException;
import org.jsfml.SFMLNative;

/**
 * Provides access to the the real-time state of the keyboard.
 */
public class Keyboard {
    static {
        try {
            SFMLNative.loadNativeLibraries();
        } catch (JSFMLException ex) {
            throw new UnsatisfiedLinkError(ex.getMessage());
        }
    }

    /**
     * Key codes.
     */
    public static enum Key {
        UNKNOWN,
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H,
        I,
        J,
        K,
        L,
        M,
        N,
        O,
        P,
        Q,
        R,
        S,
        T,
        U,
        V,
        W,
        X,
        Y,
        Z,
        NUM0,
        NUM1,
        NUM2,
        NUM3,
        NUM4,
        NUM5,
        NUM6,
        NUM7,
        NUM8,
        NUM9,
        ESCAPE,
        LCONTROL,
        LSHIFT,
        LALT,
        LSYSTEM,
        RCONTROL,
        RSHIFT,
        RALT,
        RSYSTEM,
        MENU,
        LBRACKET,
        RBRACKET,
        SEMICOLON,
        COMMA,
        PERIOD,
        QUOTE,
        SLASH,
        BACKSLASH,
        TILDE,
        EQUAL,
        DASH,
        SPACE,
        RETURN,
        BACKSPACE,
        TAB,
        PAGEUP,
        PAGEDOWN,
        END,
        HOME,
        INSERT,
        DELETE,
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        LEFT,
        RIGHT,
        UP,
        DOWN,
        NUMPAD0,
        NUMPAD1,
        NUMPAD2,
        NUMPAD3,
        NUMPAD4,
        NUMPAD5,
        NUMPAD6,
        NUMPAD7,
        NUMPAD8,
        NUMPAD9,
        F1,
        F2,
        F3,
        F4,
        F5,
        F6,
        F7,
        F8,
        F9,
        F10,
        F11,
        F12,
        F13,
        F14,
        F15,
        PAUSE
    }

    /**
     * Checks if a certain key is currently pressed on the keyboard.
     *
     * @param key The key in question.
     * @return <tt>true</tt> if the key is currently being pressed, <tt>false</tt> otherwise.
     */
    public static native boolean isKeyPressed(Key key);
}
