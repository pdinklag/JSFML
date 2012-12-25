package org.jsfml.graphics;

import org.jsfml.JSFMLError;
import org.jsfml.NotNull;
import org.jsfml.SFMLNative;
import org.jsfml.SFMLNativeObject;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Target for off-screen 2D rendering into a texture.
 */
public class RenderTexture extends SFMLNativeObject implements RenderTarget {
    private final ConstView defaultView;
    private ConstView view;
    private final ConstTexture texture;

    /**
     * Creates a new render texture.
     */
    public RenderTexture() {
        super();
        SFMLNative.ensureDisplay();

        defaultView = new View(nativeGetDefaultView());
        view = defaultView;
        texture = new Texture(nativeGetTexture());
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

    private native boolean nativeCreate(int width, int height, boolean depthBuffer);

    /**
     * Sets up the render texture.
     *
     * @param width       The texture's width.
     * @param height      The texture's height.
     * @param depthBuffer {@code true} to generate a depth buffer, {@code false} otherwise.
     *                    Use this only in case you wish to do 3D rendering to this texture.
     */
    public void create(int width, int height, boolean depthBuffer) {
        if (!nativeCreate(width, height, depthBuffer))
            throw new JSFMLError("Failed to create render texture.");
    }

    /**
     * Sets up the render texture without a depth buffer..
     *
     * @param width  The texture's width.
     * @param height The texture's height.
     */
    public final void create(int width, int height) {
        create(width, height, false);
    }

    /**
     * Enables or disables textures smoothing.
     *
     * @param smooth {@code true} to enable, {@code false} to disable.
     */
    public native void setSmooth(boolean smooth);

    /**
     * Checks whether texture smoothing is enabled.
     *
     * @return {@code true} if enabled, {@code false} if not.
     */
    public native boolean isSmooth();

    /**
     * Activates or deactivates the render texture for rendering.
     *
     * @param active {@code true} to activate, {@code false} to deactivate.
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
    public ConstTexture getTexture() {
        return texture;
    }

    @Override
    public native Vector2i getSize();

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
    public void setView(@NotNull ConstView view) {
        if (view == null)
            throw new NullPointerException("view must not be null.");

        this.view = view;
        nativeSetView((View) view);
    }

    @Override
    public ConstView getView() {
        return view;
    }

    private native long nativeGetDefaultView();

    @Override
    public ConstView getDefaultView() {
        return defaultView;
    }

    private native IntRect nativeGetViewport(View view);

    @Override
    public IntRect getViewport(@NotNull View view) {
        if (view == null)
            throw new NullPointerException("view must not be null.");

        return nativeGetViewport(view);
    }

    private native Vector2f nativeMapPixelToCoords(Vector2i point, View view);

    @Override
    public final Vector2f mapPixelToCoords(@NotNull Vector2i point) {
        return mapPixelToCoords(point, null); //null is handled in C code
    }

    @Override
    public Vector2f mapPixelToCoords(@NotNull Vector2i point, View view) {
        if (point == null)
            throw new NullPointerException("point must not be null.");

        return nativeMapPixelToCoords(point, view);
    }

    private native Vector2i nativeMapCoordsToPixel(Vector2f point, View view);

    @Override
    public final Vector2i mapCoordsToPixel(@NotNull Vector2f point) {
        return mapCoordsToPixel(point, null); //null is handled in C code
    }

    @Override
    public Vector2i mapCoordsToPixel(@NotNull Vector2f point, View view) {
        if (point == null)
            throw new NullPointerException("point must not be null.");

        return nativeMapCoordsToPixel(point, view);
    }

    @Override
    public final void draw(Drawable drawable) {
        draw(drawable, RenderStates.DEFAULT);
    }

    @Override
    public void draw(@NotNull Drawable drawable, @NotNull RenderStates renderStates) {
        drawable.draw(this, renderStates);
    }

    @Override
    public final void draw(Vertex[] vertices, PrimitiveType type) {
        draw(vertices, type, RenderStates.DEFAULT);
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
