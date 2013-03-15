package org.jsfml.graphics;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.internal.SFMLErrorCapture;
import org.jsfml.internal.SFMLNative;
import org.jsfml.internal.SFMLNativeObject;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.util.Objects;

/**
 * Provides a render target for off-screen 2D rendering into a texture.
 */
public class RenderTexture extends SFMLNativeObject implements RenderTarget {
    private Vector2i size = Vector2i.ZERO;

    private ConstView defaultView;
    private ConstView view;
    private final Texture texture;

    /**
     * Constructs a new render texture.
     */
    public RenderTexture() {
        super();
        SFMLNative.ensureDisplay();

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

    private native boolean nativeCreateTexture(int width, int height, boolean depthBuffer);

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

        size = Vector2i.ZERO;

        SFMLErrorCapture.start();
        final boolean success = nativeCreateTexture(width, height, depthBuffer);
        final String msg = SFMLErrorCapture.finish();

        if (!success)
            throw new TextureCreationException(msg);

        defaultView = new View(nativeGetDefaultView());

        if(view == null) {
            view = defaultView;
        }

        size = new Vector2i(width, height);
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
    public void setSmooth(boolean smooth) {
        texture.setSmooth(smooth);
    }

    /**
     * Checks whether texture smoothing is enabled.
     *
     * @return {@code true} if enabled, {@code false} if not.
     */
    public boolean isSmooth() {
        return texture.isSmooth();
    }

    /**
     * Enables or disables texture repeating for the underlying texture.
     * <p/>
     * Texture repeating is disabled by default.
     *
     * @param repeated {@code true} to enable, {@code false} to disable.
     */
    public void setRepeated(boolean repeated) {
        texture.setRepeated(repeated);
    }

    /**
     * Checks whether texture repeating is enabled for the underlying texture.
     *
     * @return {@code true} if enabled, {@code false} if disabled.
     */
    public boolean isRepeated() {
        return texture.isRepeated();
    }

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
    public Vector2i getSize() {
        return size;
    }

    private native void nativeClear(int color);

    @Override
    public void clear(Color color) {
        nativeClear(IntercomHelper.encodeColor(color));
    }

    /**
     * Clears the target with black.
     */
    public void clear() {
        nativeClear(0xFF000000);
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

    @Override
    public IntRect getViewport(ConstView view) {
        final FloatRect viewport = view.getViewport();
        final Vector2i size = getSize();

        return new IntRect(
                (int) (0.5f + viewport.left * size.x),
                (int) (0.5f + viewport.top * size.y),
                (int) (viewport.width * size.x),
                (int) (viewport.height * size.y));
    }

    private native long nativeMapPixelToCoords(long point, ConstView view);

    @Override
    public final Vector2f mapPixelToCoords(Vector2i point) {
        return mapPixelToCoords(point, view);
    }

    @Override
    public Vector2f mapPixelToCoords(Vector2i point, ConstView view) {
        return IntercomHelper.decodeVector2f(
                nativeMapPixelToCoords(IntercomHelper.encodeVector2i(point), view));
    }

    private native long nativeMapCoordsToPixel(long point, ConstView view);

    @Override
    public final Vector2i mapCoordsToPixel(Vector2f point) {
        return mapCoordsToPixel(point, view);
    }

    @Override
    public Vector2i mapCoordsToPixel(Vector2f point, ConstView view) {
        return IntercomHelper.decodeVector2i(
                nativeMapCoordsToPixel(IntercomHelper.encodeVector2f(point), view));
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

    @Override
    public void draw(Vertex[] vertices, PrimitiveType type, RenderStates states) {
        SFMLNativeDrawer.drawVertices(vertices, type, this, states);
    }

    @Override
    public native void pushGLStates();

    @Override
    public native void popGLStates();

    @Override
    public native void resetGLStates();
}
