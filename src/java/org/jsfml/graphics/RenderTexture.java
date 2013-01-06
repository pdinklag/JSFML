package org.jsfml.graphics;

import org.jsfml.internal.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.util.Objects;

/**
 * Provides a render target for off-screen 2D rendering into a texture.
 */
public class RenderTexture extends SFMLNativeObject implements RenderTarget {
    private final ConstView defaultView;
    private ConstView view;
    private final ConstTexture texture;

    /**
     * Constructs a new render texture.
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
     * @param width       the texture's width.
     * @param height      the texture's height.
     * @param depthBuffer {@code true} to generate a depth buffer, {@code false} otherwise.
     *                    Use this only in case you wish to do 3D rendering to this texture.
     * @throws TextureCreationException if the render texture could not be created.
     */
    public void create(int width, int height, boolean depthBuffer)
            throws TextureCreationException {

        SFMLErrorCapture.start();
        final boolean success = nativeCreate(width, height, depthBuffer);
        final String msg = SFMLErrorCapture.finish();

        if (!success)
            throw new TextureCreationException(msg);
    }

    /**
     * Sets up the render texture without a depth buffer.
     *
     * @param width  the texture's width.
     * @param height the texture's height.
     * @throws TextureCreationException if the render texture could not be created.
     */
    public final void create(int width, int height) throws TextureCreationException {
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
     * @return the target texture.
     */
    public ConstTexture getTexture() {
        return texture;
    }

    @Override
    public native Vector2i getSize();

    private native void nativeClear(Color color);

    @Override
    public void clear(Color color) {
        nativeClear(Objects.requireNonNull(color));
    }

    /**
     * Clears the target with black.
     */
    public void clear() {
        nativeClear(Color.BLACK);
    }

    private native void nativeSetView(View view);

    @Override
    public void setView(ConstView view) {
        this.view = Objects.requireNonNull(view);
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
    public IntRect getViewport(View view) {
        return nativeGetViewport(Objects.requireNonNull(view));
    }

    private native Vector2f nativeMapPixelToCoords(Vector2i point, View view);

    @Override
    public final Vector2f mapPixelToCoords(Vector2i point) {
        return mapPixelToCoords(point, null); //null is handled in C code
    }

    @Override
    public Vector2f mapPixelToCoords(Vector2i point, View view) {
        return nativeMapPixelToCoords(Objects.requireNonNull(point), view);
    }

    private native Vector2i nativeMapCoordsToPixel(Vector2f point, View view);

    @Override
    public final Vector2i mapCoordsToPixel(Vector2f point) {
        return mapCoordsToPixel(point, null); //null is handled in C code
    }

    @Override
    public Vector2i mapCoordsToPixel(Vector2f point, View view) {
        return nativeMapCoordsToPixel(Objects.requireNonNull(point), view);
    }

    @Override
    public final void draw(Drawable drawable) {
        draw(drawable, RenderStates.DEFAULT);
    }

    @Override
    public void draw(Drawable drawable, RenderStates renderStates) {
        drawable.draw(this, renderStates);
    }

    @Override
    public final void draw(Vertex[] vertices, PrimitiveType type) {
        draw(vertices, type, RenderStates.DEFAULT);
    }

    private native void nativeDraw(Vertex[] vertices, PrimitiveType type, RenderStates states);

    @Override
    public void draw(Vertex[] vertices, PrimitiveType type, RenderStates states) {
        //TODO deep null check of the vertices array
        nativeDraw(
                Objects.requireNonNull(vertices),
                Objects.requireNonNull(type),
                Objects.requireNonNull(states));
    }

    @Override
    public native void pushGLStates();

    @Override
    public native void popGLStates();

    @Override
    public native void resetGLStates();
}
