package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.SFMLNativeObject;
import org.jsfml.system.Vector2f;

/**
 * Target for off-screen 2D rendering into a texture.
 */
public class RenderTexture extends SFMLNativeObject implements RenderTarget {
    private ImmutableView defaultView;
    private View view;
    private ImmutableTexture texture;

    /**
     * Creates a new render texture.
     */
    public RenderTexture() {
        super();

        defaultView = new ImmutableView(nativeGetDefaultView());
        view = defaultView;
        texture = new ImmutableTexture(nativeGetTexture());
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    /**
     * Sets up the render texture.
     *
     * @param width       The texture's width.
     * @param height      The texture's height.
     * @param depthBuffer <tt>true</tt> to generate a depth buffer, <tt>false</tt> otherwise.
     * @return <tt>true</tt> if the render texture was set up successfully, <tt>false</tt> otherwise.
     */
    public native boolean create(int width, int height, boolean depthBuffer);

    /**
     * Enables or disables textures smoothing.
     *
     * @param smooth <tt>true</tt> to enable, <tt>false</tt> to disable.
     */
    public native void setSmooth(boolean smooth);

    /**
     * Checks whether texture smoothing is enabled.
     *
     * @return <tt>true</tt> if enabled, <tt>false</tt> if not.
     */
    public native boolean isSmooth();

    /**
     * Activates or deactivates the render texture for rendering.
     *
     * @param active <tt>true</tt> to activate, <tt>false</tt> to deactivate.
     */
    public native void setActive(boolean active);

    /**
     * Updates the contents of the target texture.
     */
    public native void display();

    private native long nativeGetTexture();

    /**
     * Gets the target texture.
     *
     * @return The target texture.
     */
    public Texture getTexture() {
        return texture;
    }

    @Override
    public native int getWidth();

    @Override
    public native int getHeight();

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
    public native void pushGLStates();

    @Override
    public native void popGLStates();

    @Override
    public native void resetGLStates();
}
