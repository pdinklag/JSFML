package org.jsfml.graphics;

import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Translates AWT key codes to JSFML key codes.
 */
final class AWTCodeTranslator {
    public static Mouse.Button translateMouseButton(int mouseButton) {
        switch (mouseButton) {
            case MouseEvent.BUTTON1:
                return Mouse.Button.LEFT;
            case MouseEvent.BUTTON2:
                return Mouse.Button.MIDDLE;
            case MouseEvent.BUTTON3:
                return Mouse.Button.RIGHT;
            case 4:
                return Mouse.Button.XBUTTON1; //TODO test
            case 5:
                return Mouse.Button.XBUTTON2; //TODO test
            default:
                return Mouse.Button.LEFT; //there is no unknown!
        }
    }

    public static Keyboard.Key translateKeyCode(int keyCode, int keyLoc) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                return Keyboard.Key.A;
            case KeyEvent.VK_B:
                return Keyboard.Key.B;
            case KeyEvent.VK_C:
                return Keyboard.Key.C;
            case KeyEvent.VK_D:
                return Keyboard.Key.D;
            case KeyEvent.VK_E:
                return Keyboard.Key.E;
            case KeyEvent.VK_F:
                return Keyboard.Key.F;
            case KeyEvent.VK_G:
                return Keyboard.Key.G;
            case KeyEvent.VK_H:
                return Keyboard.Key.H;
            case KeyEvent.VK_I:
                return Keyboard.Key.I;
            case KeyEvent.VK_J:
                return Keyboard.Key.J;
            case KeyEvent.VK_K:
                return Keyboard.Key.K;
            case KeyEvent.VK_L:
                return Keyboard.Key.L;
            case KeyEvent.VK_M:
                return Keyboard.Key.M;
            case KeyEvent.VK_N:
                return Keyboard.Key.N;
            case KeyEvent.VK_O:
                return Keyboard.Key.O;
            case KeyEvent.VK_P:
                return Keyboard.Key.P;
            case KeyEvent.VK_Q:
                return Keyboard.Key.Q;
            case KeyEvent.VK_R:
                return Keyboard.Key.R;
            case KeyEvent.VK_S:
                return Keyboard.Key.S;
            case KeyEvent.VK_T:
                return Keyboard.Key.T;
            case KeyEvent.VK_U:
                return Keyboard.Key.U;
            case KeyEvent.VK_V:
                return Keyboard.Key.V;
            case KeyEvent.VK_W:
                return Keyboard.Key.W;
            case KeyEvent.VK_X:
                return Keyboard.Key.X;
            case KeyEvent.VK_Y:
                return Keyboard.Key.Y;
            case KeyEvent.VK_Z:
                return Keyboard.Key.Z;
            case KeyEvent.VK_0:
                return Keyboard.Key.NUM0;
            case KeyEvent.VK_1:
                return Keyboard.Key.NUM1;
            case KeyEvent.VK_2:
                return Keyboard.Key.NUM2;
            case KeyEvent.VK_3:
                return Keyboard.Key.NUM3;
            case KeyEvent.VK_4:
                return Keyboard.Key.NUM4;
            case KeyEvent.VK_5:
                return Keyboard.Key.NUM5;
            case KeyEvent.VK_6:
                return Keyboard.Key.NUM6;
            case KeyEvent.VK_7:
                return Keyboard.Key.NUM7;
            case KeyEvent.VK_8:
                return Keyboard.Key.NUM8;
            case KeyEvent.VK_9:
                return Keyboard.Key.NUM9;
            case KeyEvent.VK_ESCAPE:
                return Keyboard.Key.ESCAPE;
            case KeyEvent.VK_CONTROL:
                return (keyLoc == KeyEvent.KEY_LOCATION_RIGHT) ?
                        Keyboard.Key.RCONTROL :
                        Keyboard.Key.LCONTROL;
            case KeyEvent.VK_SHIFT:
                return (keyLoc == KeyEvent.KEY_LOCATION_RIGHT) ?
                        Keyboard.Key.RSHIFT :
                        Keyboard.Key.LSHIFT;
            case KeyEvent.VK_ALT:
                return (keyLoc == KeyEvent.KEY_LOCATION_RIGHT) ?
                        Keyboard.Key.RALT :
                        Keyboard.Key.LALT;
            case KeyEvent.VK_WINDOWS:
                return (keyLoc == KeyEvent.KEY_LOCATION_RIGHT) ?
                        Keyboard.Key.RSYSTEM :
                        Keyboard.Key.LSYSTEM;
            case KeyEvent.VK_CONTEXT_MENU:
                return Keyboard.Key.MENU;
            case KeyEvent.VK_OPEN_BRACKET:
                return Keyboard.Key.LBRACKET;
            case KeyEvent.VK_CLOSE_BRACKET:
                return Keyboard.Key.RBRACKET;
            case KeyEvent.VK_SEMICOLON:
                return Keyboard.Key.SEMICOLON;
            case KeyEvent.VK_COMMA:
                return Keyboard.Key.COMMA;
            case KeyEvent.VK_PERIOD:
                return Keyboard.Key.PERIOD;
            case KeyEvent.VK_QUOTE:
                return Keyboard.Key.QUOTE;
            case KeyEvent.VK_SLASH:
                return Keyboard.Key.SLASH;
            case KeyEvent.VK_BACK_SLASH:
                return Keyboard.Key.BACKSLASH;
            case KeyEvent.VK_DEAD_TILDE:
                return Keyboard.Key.TILDE; //TODO test
            case KeyEvent.VK_EQUALS:
                return Keyboard.Key.EQUAL;
            case KeyEvent.VK_MINUS:
                return Keyboard.Key.DASH;
            case KeyEvent.VK_SPACE:
                return Keyboard.Key.SPACE;
            case KeyEvent.VK_ENTER:
                return Keyboard.Key.RETURN;
            case KeyEvent.VK_BACK_SPACE:
                return Keyboard.Key.BACKSPACE;
            case KeyEvent.VK_TAB:
                return Keyboard.Key.TAB;
            case KeyEvent.VK_PAGE_UP:
                return Keyboard.Key.PAGEUP;
            case KeyEvent.VK_PAGE_DOWN:
                return Keyboard.Key.PAGEDOWN;
            case KeyEvent.VK_END:
                return Keyboard.Key.END;
            case KeyEvent.VK_HOME:
                return Keyboard.Key.HOME;
            case KeyEvent.VK_INSERT:
                return Keyboard.Key.INSERT;
            case KeyEvent.VK_DELETE:
                return Keyboard.Key.DELETE;
            case KeyEvent.VK_ADD:
                return Keyboard.Key.ADD;
            case KeyEvent.VK_SUBTRACT:
                return Keyboard.Key.SUBTRACT;
            case KeyEvent.VK_MULTIPLY:
                return Keyboard.Key.MULTIPLY;
            case KeyEvent.VK_DIVIDE:
                return Keyboard.Key.DIVIDE;
            case KeyEvent.VK_LEFT:
                return Keyboard.Key.LEFT;
            case KeyEvent.VK_RIGHT:
                return Keyboard.Key.RIGHT;
            case KeyEvent.VK_UP:
                return Keyboard.Key.UP;
            case KeyEvent.VK_DOWN:
                return Keyboard.Key.DOWN;
            case KeyEvent.VK_NUMPAD0:
                return Keyboard.Key.NUMPAD0;
            case KeyEvent.VK_NUMPAD1:
                return Keyboard.Key.NUMPAD1;
            case KeyEvent.VK_NUMPAD2:
                return Keyboard.Key.NUMPAD2;
            case KeyEvent.VK_NUMPAD3:
                return Keyboard.Key.NUMPAD3;
            case KeyEvent.VK_NUMPAD4:
                return Keyboard.Key.NUMPAD4;
            case KeyEvent.VK_NUMPAD5:
                return Keyboard.Key.NUMPAD5;
            case KeyEvent.VK_NUMPAD6:
                return Keyboard.Key.NUMPAD6;
            case KeyEvent.VK_NUMPAD7:
                return Keyboard.Key.NUMPAD7;
            case KeyEvent.VK_NUMPAD8:
                return Keyboard.Key.NUMPAD8;
            case KeyEvent.VK_NUMPAD9:
                return Keyboard.Key.NUMPAD9;
            case KeyEvent.VK_F1:
                return Keyboard.Key.F1;
            case KeyEvent.VK_F2:
                return Keyboard.Key.F2;
            case KeyEvent.VK_F3:
                return Keyboard.Key.F3;
            case KeyEvent.VK_F4:
                return Keyboard.Key.F4;
            case KeyEvent.VK_F5:
                return Keyboard.Key.F5;
            case KeyEvent.VK_F6:
                return Keyboard.Key.F6;
            case KeyEvent.VK_F7:
                return Keyboard.Key.F7;
            case KeyEvent.VK_F8:
                return Keyboard.Key.F8;
            case KeyEvent.VK_F9:
                return Keyboard.Key.F9;
            case KeyEvent.VK_F10:
                return Keyboard.Key.F10;
            case KeyEvent.VK_F11:
                return Keyboard.Key.F11;
            case KeyEvent.VK_F12:
                return Keyboard.Key.F12;
            case KeyEvent.VK_F13:
                return Keyboard.Key.F13;
            case KeyEvent.VK_F14:
                return Keyboard.Key.F14;
            case KeyEvent.VK_F15:
                return Keyboard.Key.F15;
            case KeyEvent.VK_PAUSE:
                return Keyboard.Key.PAUSE;
            default:
                return Keyboard.Key.UNKNOWN;
        }
    }

    private AWTCodeTranslator() {
    }
}
