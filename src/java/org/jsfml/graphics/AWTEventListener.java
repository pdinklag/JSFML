package org.jsfml.graphics;

import org.jsfml.window.event.*;
import org.jsfml.window.event.TextEvent;

import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Translates AWT events to JSFML events and queues them.
 */
final class AWTEventListener implements
        KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private final LinkedList<Event> queue = new LinkedList<Event>();

    /**
     * Polls the queued translated events and clears the queue.
     *
     * @return The queues translated events.
     */
    public List<Event> pollEvents() {
        final List<Event> r = new LinkedList<Event>(queue);
        queue.clear();
        return r;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        queue.add(new TextEvent(Event.Type.TEXT_ENTERED.ordinal(), (long) e.getKeyChar()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int modifiers = e.getModifiers();
        final boolean alt = ((modifiers & KeyEvent.ALT_MASK) != 0);
        final boolean shift = ((modifiers & KeyEvent.SHIFT_MASK) != 0);
        final boolean control = ((modifiers & KeyEvent.CTRL_MASK) != 0);
        final boolean system = ((modifiers & KeyEvent.META_MASK) != 0);

        queue.add(new org.jsfml.window.event.KeyEvent(
                Event.Type.KEY_PRESSED.ordinal(),
                AWTCodeTranslator.translateKeyCode(e.getKeyCode(), e.getKeyLocation()).ordinal() - 1,
                alt, shift, control, system));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        final int modifiers = e.getModifiers();
        final boolean alt = ((modifiers & KeyEvent.ALT_MASK) != 0);
        final boolean shift = ((modifiers & KeyEvent.SHIFT_MASK) != 0);
        final boolean control = ((modifiers & KeyEvent.CTRL_MASK) != 0);
        final boolean system = ((modifiers & KeyEvent.META_MASK) != 0);

        queue.add(new org.jsfml.window.event.KeyEvent(
                Event.Type.KEY_RELEASED.ordinal(),
                AWTCodeTranslator.translateKeyCode(e.getKeyCode(), e.getKeyLocation()).ordinal() - 1,
                alt, shift, control, system));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //not specified in SFML
    }

    @Override
    public void mousePressed(MouseEvent e) {
        queue.add(new MouseButtonEvent(Event.Type.MOUSE_BUTTON_PRESSED.ordinal(),
                e.getXOnScreen(), e.getYOnScreen(),
                AWTCodeTranslator.translateMouseButton(e.getButton()).ordinal()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        queue.add(new MouseButtonEvent(Event.Type.MOUSE_BUTTON_RELEASED.ordinal(),
                e.getXOnScreen(), e.getYOnScreen(),
                AWTCodeTranslator.translateMouseButton(e.getButton()).ordinal()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //correctly passed to SFML by X11
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //correctly passed to SFML by X11
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        queue.add(new org.jsfml.window.event.MouseWheelEvent(
                Event.Type.MOUSE_WHEEL_MOVED.ordinal(),
                e.getXOnScreen(), e.getYOnScreen(),
                -e.getWheelRotation()));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        queue.add(new org.jsfml.window.event.MouseEvent(
                Event.Type.MOUSE_MOVED.ordinal(),
                e.getXOnScreen(), e.getYOnScreen()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //correctly passed to SFML by X11
    }
}
