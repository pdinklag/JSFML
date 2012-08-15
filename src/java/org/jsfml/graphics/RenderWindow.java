package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

/**
 * Window that can serve as a target for 2D drawing.
 */
public class RenderWindow extends Window implements RenderTarget {
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
    public RenderWindow(@NotNull VideoMode mode, @NotNull String title, int style, @NotNull ContextSettings settings) {
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
    public RenderWindow(@NotNull VideoMode mode, @NotNull String title, int style) {
        this();
        create(mode, title, style, new ContextSettings());
    }

    /**
     * Creates a new render window with default style and context settings.
     *
     * @param mode  The video mode to use for rendering.
     * @param title The window title.
     */
    public RenderWindow(@NotNull VideoMode mode, @NotNull String title) {
        this();
        create(mode, title, DEFAULT, new ContextSettings());
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    private native long nativeGetRenderTargetPtr();

    @Override
    protected long[] getExtraPointers() {
        return new long[]{nativeGetRenderTargetPtr()};
    }

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

    private native Vector2f nativeConvertCoords(Vector2i point, View view);

    @Override
    public Vector2f convertCoords(@NotNull Vector2i point) {
        if (point == null)
            throw new IllegalArgumentException("point must not be null.");

        return nativeConvertCoords(point, null); //null is handled in C code
    }

    @Override
    public Vector2f convertCoords(@NotNull Vector2i point, @NotNull View view) {
        if (point == null)
            throw new IllegalArgumentException("point must not be null.");

        if (view == null)
            throw new IllegalArgumentException("view must not be null.");

        return nativeConvertCoords(point, view);
    }

    @Override
    public void draw(Drawable drawable) {
        draw(drawable, new RenderStates());
    }

    @Override
    public void draw(@NotNull Drawable drawable, @NotNull RenderStates renderStates) {
        drawable.draw(this, renderStates);
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
    public native Vector2i getPosition();

    @Override
    protected native void nativeSetPosition(Vector2i position);

    @Override
    public native Vector2i getSize();

    @Override
    protected native void nativeSetSize(Vector2i size);

    @Override
    public native ContextSettings getSettings();

    @Override
    public native Event pollEvent();

    @Override
    public native Event waitEvent();

    @Override
    public native void setVerticalSyncEnabled(boolean enable);

    @Override
    public native void setMouseCursorVisible(boolean show);

    @Override
    protected native void nativeSetTitle(String title);

    @Override
    public native void setVisible(boolean show);

    @Override
    public native void setKeyRepeatEnabled(boolean enable);

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
