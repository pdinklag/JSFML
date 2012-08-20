package org.jsfml.window;

import org.jsfml.JSFMLError;
import org.jsfml.NotNull;
import org.jsfml.SFMLNativeObject;
import org.jsfml.graphics.Image;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;

import java.util.LinkedList;

/**
 * Basic window that serves as an OpenGL target.
 */
public class Window extends SFMLNativeObject {
    /**
     * Unstyled window.
     */
    public static final int NONE = 0;

    /**
     * The window will have a title bar.
     */
    public static final int TITLEBAR = 0x01;

    /**
     * The window is resizable.
     */
    public static final int RESIZE = 0x02;

    /**
     * The window has a close button.
     */
    public static final int CLOSE = 0x04;

    /**
     * The window emulates a fullscreen mode.
     */
    public static final int FULLSCREEN = 0x08;

    /**
     * The default style, a resizable and closeable window with a title bar.
     */
    public static final int DEFAULT = TITLEBAR | RESIZE | CLOSE;

    /**
     * The current window icon image.
     * <p/>
     * A reference to it must be maintained in order to assure that the image will not be
     * garbage-collected.
     */
    private Image icon = null;

    /**
     * Constructs a new window without creating it.
     *
     * @see Window#create(org.jsfml.window.VideoMode, String, int, org.jsfml.window.ContextSettings)
     */
    public Window() {
        super();
    }

    /**
     * Creates a new window.
     *
     * @param mode     The video mode to use for OpenGL rendering.
     * @param title    The window title.
     * @param style    The window style.
     * @param settings The settings for the OpenGL context.
     */
    public Window(@NotNull VideoMode mode, @NotNull String title, int style, @NotNull ContextSettings settings) {
        this();
        create(mode, title, style, settings);
    }

    /**
     * Creates a new window with default context settings.
     *
     * @param mode  The video mode to use for OpenGL rendering.
     * @param title The window title.
     * @param style The window style.
     */
    public Window(@NotNull VideoMode mode, @NotNull String title, int style) {
        this();
        create(mode, title, style, new ContextSettings());
    }

    /**
     * Creates a new window with default style and context settings.
     *
     * @param mode  The video mode to use for rendering.
     * @param title The window title.
     */
    public Window(@NotNull VideoMode mode, @NotNull String title) {
        this();
        create(mode, title, DEFAULT, new ContextSettings());
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    protected native void nativeCreate(VideoMode mode, String title, int style, ContextSettings settings);

    /**
     * Checks whether the current native thread is eligibile for spawning a window.
     * <p/>
     * This will always be the case on Windows or Linux, but on Mac OS X, it will check whether
     * the JVM was started in the main thread using the <tt>-XstartOnFirstThread</tt> command
     * line parameter.
     *
     * @return <tt>true</tt> if the current native thread may create a window, <tt>false</tt>
     *         otherwise.
     */
    public static native boolean isLegalWindowThread();

    /**
     * Creates a window or re-creates it if it was already opened.
     * <p/>
     * Note that this method may raise a {@link JSFMLError} on Mac OS X systems if the thread
     * this method was invoked in is not allowed to open a window. See {@link #isLegalWindowThread()}
     * for more information.
     *
     * @param mode     The video mode to use for the OpenGL context. This must be a valid video mode in case
     *                 {@link Window#FULLSCREEN} is set.
     * @param title    The window title.
     * @param style    The style of the window. This should be a ORed combination of style flags
     *                 {@link Window#TITLEBAR}, {@link Window#RESIZE}, {@link Window#CLOSE} and
     *                 {@link Window#FULLSCREEN}, or {@link Window#NONE} for no style. {@link Window#DEFAULT}
     *                 provides a default combination of style flags.
     * @param settings The context settings to be applied to the context.
     * @see org.jsfml.window.VideoMode#isValid()
     * @see ContextSettings
     */
    public void create(@NotNull VideoMode mode, @NotNull String title, int style, @NotNull ContextSettings settings) {
        if (mode == null)
            throw new NullPointerException("mode must not be null.");

        if (title == null)
            throw new NullPointerException("title must not be null.");

        if (settings == null)
            throw new NullPointerException("settings must not be null.");

        if (!isLegalWindowThread()) {
            throw new JSFMLError("This thread is not allowed to create a window on this system. " +
                    "If you are running on Mac OS X, you MUST run your" +
                    "application with the -XstartOnFirstThread command line argument!");
        }

        if ((style & FULLSCREEN) != 0 && !mode.isValid())
            throw new IllegalArgumentException("Invalid video mode for fullscreen window.");

        nativeCreate(mode, title, style, settings);
    }

    /**
     * Creates a window or re-creates it if it was already opened with default context settings.
     *
     * @param mode  The video mode to use for the OpenGL context. This must be a valid video mode in case
     *              {@link Window#FULLSCREEN} is set.
     * @param title The window title.
     * @param style The style of the window.
     * @see org.jsfml.window.VideoMode#isValid()
     */
    public final void create(@NotNull VideoMode mode, @NotNull String title, int style) {
        create(mode, title, style, new ContextSettings());
    }

    /**
     * Creates a window or re-creates it if it was already opened with default context settings and style
     * ({@link Window#DEFAULT}.
     *
     * @param mode  The video mode to use for the OpenGL context.
     * @param title The window title.
     */
    public final void create(@NotNull VideoMode mode, @NotNull String title) {
        create(mode, title, DEFAULT, new ContextSettings());
    }

    /**
     * Closes the window and destroys all attached resources.
     */
    public native void close();

    /**
     * Checks if the window has been created and is opened.
     * <p/>
     * The user can modify this property by clicking the window's close button.
     *
     * @return <tt>true</tt> if the window has been created and is currently opened.
     */
    public native boolean isOpen();

    /**
     * Gets the position of the window on the screen.
     *
     * @return The position of the window on the screen.
     */
    public native Vector2i getPosition();

    protected native void nativeSetPosition(Vector2i v);

    /**
     * Sets the position of the window on the screen.
     *
     * @param position The position on the screen.
     */
    public void setPosition(@NotNull Vector2i position) {
        if (position == null)
            throw new NullPointerException("position must not be null.");

        nativeSetPosition(position);
    }

    /**
     * Gets the size of the window.
     *
     * @return The size of the window.
     */
    public native Vector2i getSize();

    protected native void nativeSetSize(Vector2i v);

    /**
     * Sets the size of the window.
     *
     * @param size The new size of the window.
     */
    public void setSize(@NotNull Vector2i size) {
        if (size == null)
            throw new NullPointerException("size must not be null.");

        nativeSetPosition(size);
    }

    /**
     * Retrieves the context settings of the window's rendering context.
     *
     * @return The context settings of the window's rendering context.
     */
    public native ContextSettings getSettings();

    /**
     * Pops the event on top of the event stack, if any, and returns it.
     *
     * @return The event currently on top of the event stack, or <tt>null</tt> if there is none.
     * @see #waitEvent()
     */
    public native Event pollEvent();

    /**
     * Polls all events from the current event stack and returns an iterable list containing
     * these events..
     *
     * @return An iterable list of the received events.
     * @see #pollEvent()
     */
    public Iterable<Event> pollEvents() {
        final LinkedList<Event> events = new LinkedList<Event>();

        for (Event event = pollEvent(); event != null; event = pollEvent())
            events.add(event);

        return events;
    }

    /**
     * Pops the event on top of the event stack and returns it, or waits until an event occurs and then
     * returns it.
     * <p/>
     * Note that this method will block the program flow until an event is returned.
     *
     * @return The event currently on top of the event stack, or the next event that will occur.
     * @see #pollEvent()
     */
    public native Event waitEvent();

    /**
     * Enables or disables vertical synchronization (VSync).
     *
     * @param enable <tt>true</tt> to enable, <tt>false</tt> to disable.
     */
    public native void setVerticalSyncEnabled(boolean enable);

    /**
     * Determines whether the mouse cursor, if moved over the window, is visible or not.
     *
     * @param show <tt>true</tt> to make the cursor visible, <tt>false</tt> to hide it.
     */
    public native void setMouseCursorVisible(boolean show);

    protected native void nativeSetTitle(String title);

    /**
     * Sets the window's title.
     *
     * @param title The window's new title.
     */
    public void setTitle(@NotNull String title) {
        if (title == null)
            throw new NullPointerException("title must not be null.");

        nativeSetTitle(title);
    }

    /**
     * Shows or hides the window.
     *
     * @param show <tt>true</tt> to show the window, <tt>false</tt> to hide it.
     */
    public native void setVisible(boolean show);

    /**
     * Determines whether automatic key repeat is enabled.
     * <p/>
     * If enabled, new key events will be fired when a key stays pressed (much like in a text field). This is
     * enabled by default.
     *
     * @param enable <tt>true</tt> to enable, <tt>false</tt> to disabled.
     */
    public native void setKeyRepeatEnabled(boolean enable);

    protected native void nativeSetIcon(Image image);

    /**
     * Sets the icon of the window.
     *
     * @param icon The icon.
     */
    public void setIcon(@NotNull Image icon) {
        if (icon == null)
            throw new NullPointerException("icon must not be null.");

        this.icon = icon; //keep a local reference
        nativeSetIcon(icon);
    }

    /**
     * Activates or deactivates the window as the current OpenGL rendering target.
     * <p/>
     * Note that if a window gets activated, all other windows operating in the same thread will
     * automatically be deactivated.
     *
     * @param active <tt>true</tt> to activate, <tt>false</tt> to deactivate.
     */
    public native void setActive(boolean active);

    /**
     * Flushes the OpenGL pixel buffer to the screen.
     * <p/>
     * This should be called every frame to visibly update the scene.
     */
    public native void display();

    /**
     * Sets the maximum frame rate in frames per second.
     *
     * @param flimit The maximum frame rate in frames per second.
     */
    public native void setFramerateLimit(int flimit);

    /**
     * Sets the joystick treshold.
     * <p/>
     * Joystick axis movements with a magnitude smaller than this treshold will not fire a joystick event.
     * The default joystick treshold is 0.1.
     *
     * @param treshold The joystick treshold in a range between 0 and 100.
     * @see org.jsfml.window.event.JoystickMoveEvent
     */
    public native void setJoystickTreshold(float treshold);
}
