package org.jsfml.window;

import org.jsfml.graphics.Image;
import org.jsfml.internal.*;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;

import java.util.Iterator;
import java.util.Objects;

/**
 * A basic window that provides an OpenGL context.
 * <p/>
 * This class implements the {@code WindowStyle} interface for quick access
 * to the constants provided by it.
 */
public class Window extends SFMLNativeObject implements WindowStyle {
    /**
     * The current window icon image.
     * <p/>
     * A reference to it must be maintained in order to assure that the image will not be
     * garbage-collected.
     */
    private Image icon = null;

    /**
     * Constructs a new window without actually creating it (making it visible).
     *
     * @see Window#create(org.jsfml.window.VideoMode, String, int, org.jsfml.window.ContextSettings)
     */
    public Window() {
        super();
        SFMLNative.ensureDisplay();
    }

    /**
     * Constructs a new window within the specified parent window.
     *
     * @param ptr the window handle of the parent window.
     * @deprecated Use of this method may cause undefined behaviour and is not supported.
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    protected Window(long ptr) {
        super(ptr);
    }

    /**
     * Constructs a new window and creates it with the specified settings.
     *
     * @param mode     the window's video mode.
     * @param title    the window title.
     * @param style    the window style.
     * @param settings the settings for the OpenGL context.
     * @see #create(VideoMode, String, int, ContextSettings)
     */
    public Window(VideoMode mode, String title, int style, ContextSettings settings) {
        this();
        create(mode, title, style, settings);
    }

    /**
     * Constructs a new window and creates it with the specified settings and default
     * context settings..
     *
     * @param mode  the window's video mode.
     * @param title the window title.
     * @param style the window style.
     */
    public Window(VideoMode mode, String title, int style) {
        this();
        create(mode, title, style, new ContextSettings());
    }

    /**
     * Constructs a new window and creates it with the specified settings and default
     * context settings and window style.
     *
     * @param mode  the window's video mode.
     * @param title the window title.
     */
    public Window(VideoMode mode, String title) {
        this();
        create(mode, title, WindowStyle.DEFAULT, new ContextSettings());
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeSetExPtr();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native void nativeCreate(VideoMode mode, String title, int style, ContextSettings settings);

    /**
     * Checks whether the current native thread is eligibile for creating a window.
     * <p/>
     * This will always be the case on Windows or Linux, but on Mac OS X, it will check whether
     * the JVM was started in the main thread using the {@code -XstartOnFirstThread} command
     * line parameter.
     *
     * @return {@code true} if the current native thread may create a window,
     *         {@code false} otherwise.
     */
    public static native boolean isLegalWindowThread();

    /**
     * Creates and opens a window or re-creates it if it was already opened.
     *
     * @param mode     the video mode that determines the window's size.
     *                 This must be a valid video mode in case {@link WindowStyle#FULLSCREEN} is set.
     * @param title    the window title.
     * @param style    the style of the window.
     *                 This should be a combination (using {@code OR} of the style flags
     *                 {@link WindowStyle#TITLEBAR}, {@link WindowStyle#RESIZE}, {@link WindowStyle#CLOSE} and
     *                 {@link WindowStyle#FULLSCREEN}, or {@link WindowStyle#NONE} for no style.
     *                 {@link WindowStyle#DEFAULT} provides a default combination of style flags.
     * @param settings the context settings for the created OpenGL context.
     * @see org.jsfml.window.VideoMode#isValid()
     * @see WindowStyle
     * @see ContextSettings
     */
    public void create(VideoMode mode, String title, int style, ContextSettings settings) {
        if (!isLegalWindowThread()) {
            throw new JSFMLError("This thread is not allowed to create a window on this system. " +
                    "If you are running on Mac OS X, you MUST run your" +
                    "application with the -XstartOnFirstThread command line argument!");
        }

        if ((style & FULLSCREEN) != 0 && !mode.isValid())
            throw new IllegalArgumentException("Invalid video mode for a fullscreen window.");

        nativeCreate(
                Objects.requireNonNull(mode),
                Objects.requireNonNull(title),
                style,
                Objects.requireNonNull(settings));
    }

    /**
     * Creates and opens a window or re-creates it if it was already opened.
     * The default context settings will be used for the OpenGL context.
     *
     * @param mode  the video mode that determines the window's size.
     *              This must be a valid video mode in case {@link WindowStyle#FULLSCREEN} is set.
     * @param title the window title.
     * @param style the style of the window.
     *              This should be a combination (using {@code OR} of the style flags
     *              {@link WindowStyle#TITLEBAR}, {@link WindowStyle#RESIZE}, {@link WindowStyle#CLOSE} and
     *              {@link WindowStyle#FULLSCREEN}, or {@link WindowStyle#NONE} for no style.
     *              {@link WindowStyle#DEFAULT} provides a default combination of style flags.
     * @see org.jsfml.window.VideoMode#isValid()
     * @see WindowStyle
     */
    public final void create(VideoMode mode, String title, int style) {
        create(mode, title, style, new ContextSettings());
    }

    /**
     * Creates and opens a window or re-creates it if it was already opened.
     * The {@link WindowStyle#DEFAULT} window style will be applied and default
     * context settings will be used for the OpenGL context.
     *
     * @param mode  the video mode that determines the window's size.
     * @param title the window title.
     */
    public final void create(VideoMode mode, String title) {
        create(mode, title, DEFAULT, new ContextSettings());
    }

    /**
     * Closes the window and destroys all attached resources,
     * including the OpenGL context provided by it.
     */
    public native void close();

    /**
     * Checks if the window has been created and is opened.
     * <p/>
     * Note that the <i>open</i> state is not directly affected by the user clicking
     * the window's <i>close</i> button, if available. In order to accomplish that,
     * listen to an event of type {@link org.jsfml.window.event.Event.Type#CLOSED} and use
     * the {@link #close()} method.
     *
     * @return {@code true} if the window has been created and is currently open.
     */
    public native boolean isOpen();

    /**
     * Gets the absolute position of the window's top left corner on the screen.
     *
     * @return the absolute position of the window's top left corner on the screen.
     */
    public native Vector2i getPosition();

    protected native void nativeSetPosition(Vector2i v);

    /**
     * Sets the absolute position of the window's top left corner on the screen.
     *
     * @param position the new absolute position of the window's top left corner on the screen.
     */
    public void setPosition(Vector2i position) {
        nativeSetPosition(Objects.requireNonNull(position));
    }

    /**
     * Gets the size of the window.
     *
     * @return the size of the window.
     */
    public native Vector2i getSize();

    protected native void nativeSetSize(Vector2i v);

    /**
     * Sets the size of the window.
     *
     * @param size the new size of the window.
     */
    public void setSize(Vector2i size) {
        nativeSetPosition(Objects.requireNonNull(size));
    }

    /**
     * Retrieves the context settings for the window's OpenGL context.
     *
     * @return the context settings for the window's OpenGL context.
     */
    public native ContextSettings getSettings();

    /**
     * Pops the event on top of the event stack, if any, and returns it.
     * <p/>
     * This method needs to be called regularly in order to process pending events. If this is
     * not done, the window will be unresponsive.
     *
     * @return the event currently on top of the event stack, or {@code null} if there is none.
     * @see #waitEvent()
     */
    public native Event pollEvent();

    /**
     * Returns an {@link Iterable} that consecutively calls {@link #pollEvent()} and
     * can be used to process all pending events.
     *
     * @return an {@code Iterable} over all pending events.
     * @see #pollEvent()
     */
    public Iterable<Event> pollEvents() {
        return new Iterable<Event>() {
            @Override
            public Iterator<Event> iterator() {
                return new Iterator<Event>() {
                    private Event nextEvent = pollEvent();

                    @Override
                    public boolean hasNext() {
                        return (nextEvent != null);
                    }

                    @Override
                    public Event next() {
                        Event currentEvent = nextEvent;
                        nextEvent = pollEvent();
                        return currentEvent;
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    /**
     * Pops the event on top of the event stack and returns it, or, if there is none,
     * waits until an event occurs and then returns it.
     * <p/>
     * This method will block the program flow until an event is returned.
     *
     * @return the event currently on top of the event stack, or the next event that will occur.
     * @see #pollEvent()
     */
    public native Event waitEvent();

    /**
     * Enables or disables vertical synchronization (VSync).
     * <p/>
     * Activating vertical synchronization will limit the number of frames displayed
     * to the refresh rate of the monitor. This can avoid some visual artifacts,
     * and limit the framerate to a good value (but not constant across different computers).
     * <p/>
     * This should not be used in combination with {@link #setFramerateLimit(int)},
     * as these two will conflict with one another.
     * <p/>
     * By default, vertical synchronization is disabled.
     *
     * @param enable {@code true} to enable vertical synchronization, {@code false} to disable.
     */
    public native void setVerticalSyncEnabled(boolean enable);

    /**
     * Determines whether the mouse cursor, if moved over the window, is visible or not.
     *
     * @param show {@code true} to make the cursor visible, {@code false} to hide it.
     */
    public native void setMouseCursorVisible(boolean show);

    private native void nativeSetTitle(String title);

    /**
     * Sets the window's title.
     *
     * @param title the window's new title.
     */
    public void setTitle(String title) {
        nativeSetTitle(Objects.requireNonNull(title));
    }

    /**
     * Shows or hides the window.
     *
     * @param show {@code true} to show the window, {@code false} to hide it.
     */
    public native void setVisible(boolean show);

    /**
     * Determines whether automatic key repeat is enabled.
     * <p/>
     * If enabled, multiple key press events will be fired when a key stays pressed
     * (much like in a text field).
     * <p/>
     * Key repeat is enabled by default.
     *
     * @param enable {@code true} to enable, {@code false} to disabled.
     */
    public native void setKeyRepeatEnabled(boolean enable);

    protected native void nativeSetIcon(Image image);

    /**
     * Sets the icon of the window.
     *
     * @param icon the icon image.
     */
    public void setIcon(Image icon) {
        this.icon = Objects.requireNonNull(icon); //keep a local reference
        nativeSetIcon(icon);
    }

    private native boolean nativeSetActive(boolean active);

    /**
     * Activates or deactivates the window as the current OpenGL rendering target.
     * <p/>
     * If a window gets activated, all other windows operating in the same thread will
     * automatically be deactivated.
     *
     * @param active {@code true} to activate, {@code false} to deactivate.
     * @throws ContextActivationException in case window activation fails.
     */
    public void setActive(boolean active) throws ContextActivationException {
        if (!nativeSetActive(active)) {
            throw new ContextActivationException("Failed to " +
                    (active ? "activate" : "deactivate") +
                    " the window's context.");
        }
    }

    /**
     * Activates the window as the current OpenGL rendering target.
     * <p/>
     * If a window gets activated, all other windows operating in the same thread will
     * automatically be deactivated.
     *
     * @throws ContextActivationException in case window activation fails.
     */
    public final void setActive() throws ContextActivationException {
        setActive(true);
    }

    /**
     * Flushes the OpenGL pixel buffer to the screen.
     * <p/>
     * This should be called every frame after everything has been drawn in order to
     * make the changes visible in the window.
     */
    public native void display();

    /**
     * Sets the maximum frame rate in frames per second.
     * <p/>
     * If a limit is set, the {@link #display()} method will block for a short delay time
     * after flushing the buffer in order to possibly maintain a constant frame rate.
     * <p/>
     * This should not be used in combination with
     * {@link #setVerticalSyncEnabled(boolean)} } as these two will conflict with one another.
     * <p/>
     * By default, there is no frame rate limit.
     *
     * @param flimit the maximum frame rate in frames per second, or {@code 0} to disable
     *               the frame rate limit.
     */
    public native void setFramerateLimit(int flimit);

    /**
     * Sets the joystick treshold.
     * <p/>
     * Joystick axis movements with a magnitude smaller than this treshold will not
     * fire a joystick event.
     * <p/>
     * The default joystick treshold is 0.1.
     *
     * @param treshold the joystick treshold, ranging between 0 and 100.
     * @see org.jsfml.window.event.JoystickMoveEvent
     */
    public native void setJoystickTreshold(float treshold);
}
