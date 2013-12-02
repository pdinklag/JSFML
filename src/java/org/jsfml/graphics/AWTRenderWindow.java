package org.jsfml.graphics;

import org.jsfml.window.event.*;

import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A render window that receives events from Java AWT rather than SFML.
 */
final class AWTRenderWindow extends RenderWindow implements
        ComponentListener, FocusListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private final static long WAIT_EVENT_DURATION = 10; //same as SFML

    private final ConcurrentLinkedQueue<Event> queue = new ConcurrentLinkedQueue<Event>();
    private boolean keyRepeatEnabled = true;
    private int currentKeyDown = -1;

    @SuppressWarnings("deprecation")
    AWTRenderWindow(long ptr) {
        super(ptr);
    }

    private void clearSFMLEventQueue() {
        //Queue SFML events so its buffer doesn't fill up, but ignore the output.
        for (Event e = super.pollEvent(); e != null; e = super.pollEvent()) {
            //discard
        }
    }

    @Override
    public Event pollEvent() {
        clearSFMLEventQueue();

        //Poll the first event from the queue
        return queue.poll();
    }

    @Override
    public Iterable<Event> pollEvents() {
        clearSFMLEventQueue();

        //Create a copy of the queue and clear it.
        final Iterable<Event> it = new LinkedList<Event>(queue);
        queue.clear();
        return it;
    }

    @Override
    public void setKeyRepeatEnabled(boolean enable) {
        keyRepeatEnabled = enable;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        queue.add(new org.jsfml.window.event.TextEvent(Event.Type.TEXT_ENTERED.ordinal(), e.getKeyChar()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keyRepeatEnabled && currentKeyDown == e.getKeyCode()) {
            return;
        }

        currentKeyDown = e.getKeyCode();

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
        if (currentKeyDown == e.getKeyCode()) {
            currentKeyDown = -1;
        }

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
        //not specified by SFML
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
        queue.add(new org.jsfml.window.event.MouseEvent(
                Event.Type.MOUSE_ENTERED.ordinal(),
                e.getXOnScreen(), e.getYOnScreen()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        queue.add(new org.jsfml.window.event.MouseEvent(
                Event.Type.MOUSE_LEFT.ordinal(),
                e.getXOnScreen(), e.getYOnScreen()));
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
        //as per SFML, this is the same as a simple mouse movement
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        queue.add(new org.jsfml.window.event.MouseEvent(
                Event.Type.MOUSE_MOVED.ordinal(),
                e.getXOnScreen(), e.getYOnScreen()));
    }

    @Override
    public void focusGained(FocusEvent e) {
        queue.add(new Event(Event.Type.GAINED_FOCUS.ordinal()));
    }

    @Override
    public void focusLost(FocusEvent e) {
        queue.add(new Event(Event.Type.LOST_FOCUS.ordinal()));
    }

    @Override
    public void componentResized(ComponentEvent e) {
        queue.add(new SizeEvent(Event.Type.RESIZED.ordinal(),
                e.getComponent().getWidth(), e.getComponent().getHeight()));
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        //not specified by SFML
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //not specified by SFML
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //not specified by SFML
    }
}
