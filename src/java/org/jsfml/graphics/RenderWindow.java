package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.SFMLNativeObject;
import org.jsfml.system.Vector2f;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

/**
 * Window that can serve as a target for 2D drawing.
 */
public class RenderWindow extends Window implements RenderTarget  {
    private ImmutableView defaultView;
    private View view;

    /**
     * Constructs a new window without creating it.
     *
     * @see RenderWindow#create(org.jsfml.window.VideoMode, String, int, org.jsfml.window.ContextSettings)
     */
    public RenderWindow() {
        super();

        defaultView = new ImmutableView(nativeGetDefaultView());
        view = defaultView;
    }

    /**
     * Creates a new render window.
     *
     * @param mode     The video mode to use for rendering.
     * @param title    The window title.
     * @param style    The window style.
     * @param settings The settings for the OpenGL context.
     */
    public RenderWindow(VideoMode mode, String title, int style, ContextSettings settings) {
        this();
        create(mode, title, style, settings);
    }

    /**
     * Creates a new render window with default context settings.
     *
     * @param mode  The video mode to use for rendering.
     * @param title The window title.
     * @param style The window style.
     */
    public RenderWindow(VideoMode mode, String title, int style) {
        this();
        create(mode, title, style, new ContextSettings());
    }

    /**
     * Creates a new render window with default style and context settings.
     *
     * @param mode  The video mode to use for rendering.
     * @param title The window title.
     */
    public RenderWindow(VideoMode mode, String title) {
        this();
        create(mode, title, DEFAULT, new ContextSettings());
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    private native void nativeClear(Color color);

    @Override
    public void clear(@NotNull Color color) {
        if (color == null)
            throw new IllegalArgumentException("color must not be null.");

        nativeClear(color);
    }

    /**
     * Clears the target with black.
     */
    public void clear() {
        nativeClear(Color.BLACK);
    }

    private native void nativeSetView(View view);

    @Override
    public void setView(@NotNull View view) {
        if (view == null)
            throw new IllegalArgumentException("view must not be null.");

        this.view = view;
        nativeSetView(view);
    }

    @Override
    public View getView() {
        return view;
    }

    private native long nativeGetDefaultView();

    @Override
    public View getDefaultView() {
        return defaultView;
    }

    private native IntRect nativeGetViewport(View view);

    @Override
    public IntRect getViewport(@NotNull View view) {
        if (view == null)
            throw new IllegalArgumentException("view must not be null.");

        return nativeGetViewport(view);
    }

    @Override
    public native Vector2f convertCoords(float x, float y);

    private native Vector2f nativeConvertCoords(float x, float y, View view);

    @Override
    public Vector2f convertCoords(float x, float y, @NotNull View view) {
        if (view == null)
            throw new IllegalArgumentException("view must not be null.");

        return nativeConvertCoords(x, y, view);
    }

    @Override
    public void draw(Drawable drawable) {
        draw(drawable, new RenderStates());
    }

    private native void nativeDraw(Drawable drawable, RenderStates states);

    @Override
    public void draw(@NotNull Drawable drawable, @NotNull RenderStates renderStates) {
        if (drawable == null)
            throw new IllegalArgumentException("drawable must not be null.");

        if (!(drawable instanceof SFMLNativeObject))
            throw new IllegalArgumentException("drawable must be a native SFML object.");

        if (renderStates == null)
            throw new IllegalArgumentException("renderStates must not be null.");

        nativeDraw(drawable, renderStates);
    }

    @Override
    public void draw(Vertex[] vertices, PrimitiveType type) {
        draw(vertices, type, new RenderStates());
    }

    private native void nativeDraw(Vertex[] vertices, PrimitiveType type, RenderStates states);

    @Override
    public void draw(@NotNull Vertex[] vertices, @NotNull PrimitiveType type, @NotNull RenderStates states) {
        if (vertices == null)
            throw new IllegalArgumentException("vertices must not be null.");

        if (type == null)
            throw new IllegalArgumentException("type must not be null.");

        if (states == null)
            throw new IllegalArgumentException("states must not be null.");

        nativeDraw(vertices, type, states);
    }

    @Override
    protected native void nativeCreate(VideoMode mode, String title, int style, ContextSettings settings);

    @Override
    public native void close();

    @Override
    public native boolean isOpen();

    @Override
    public native int getWidth();

    @Override
    public native int getHeight();

    @Override
    public native ContextSettings getSettings();

    @Override
    public native Event pollEvent();

    @Override
    public native Event waitEvent();

    @Override
    public native void enableVerticalSync(boolean enable);

    @Override
    public native void showMouseCursor(boolean show);

    @Override
    public native void setPosition(int x, int y);

    @Override
    public native void setSize(int width, int height);

    @Override
    protected native void nativeSetTitle(String title);

    @Override
    public native void show(boolean show);

    @Override
    public native void enableKeyRepeat(boolean enable);

    @Override
    protected native void nativeSetIcon(Image image);

    @Override
    public native void setActive(boolean active);

    @Override
    public native void display();

    @Override
    public native void setFramerateLimit(int flimit);

    @Override
    public native void setJoystickTreshold(float treshold);

    @Override
    public native void pushGLStates();

    @Override
    public native void popGLStates();

    @Override
    public native void resetGLStates();
}
