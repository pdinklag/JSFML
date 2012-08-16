package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;

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

    private native void nativeClear(Color color);

    @Override
    public void clear(@NotNull Color color) {
        if (color == null)
            throw new NullPointerException("color must not be null.");

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
            throw new NullPointerException("view must not be null.");

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
            throw new NullPointerException("view must not be null.");

        return nativeGetViewport(view);
    }

    private native Vector2f nativeConvertCoords(Vector2i point, View view);

    @Override
    public Vector2f convertCoords(@NotNull Vector2i point) {
        if (point == null)
            throw new NullPointerException("point must not be null.");

        return nativeConvertCoords(point, null); //null is handled in C code
    }

    @Override
    public Vector2f convertCoords(@NotNull Vector2i point, @NotNull View view) {
        if (point == null)
            throw new NullPointerException("point must not be null.");

        if (view == null)
            throw new NullPointerException("view must not be null.");

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
            throw new NullPointerException("vertices must not be null.");

        if (type == null)
            throw new NullPointerException("type must not be null.");

        if (states == null)
            throw new NullPointerException("states must not be null.");

        nativeDraw(vertices, type, states);
    }

    @Override
    public native void pushGLStates();

    @Override
    public native void popGLStates();

    @Override
    public native void resetGLStates();
}
