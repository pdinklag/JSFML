package org.jsfml.graphics;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.internal.UnsafeOperations;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;

import java.util.Objects;

/**
 * Provides a window that can serve as a target for 2D drawing.
 */
public class RenderWindow extends Window implements RenderTarget {
    private ConstView defaultView = null;
    private ConstView view = null;

    /**
     * Constructs a new render window without actually creating (opening) it.
     *
     * @see RenderWindow#create(org.jsfml.window.VideoMode, String, int, org.jsfml.window.ContextSettings)
     */
    public RenderWindow() {
        super();
    }

    @Deprecated
    @SuppressWarnings("deprecation")
    RenderWindow(long ptr) {
        super(ptr);

        defaultView = new View(nativeGetDefaultView());
        view = defaultView;
    }

    /**
     * Constructs a new render window and creates it with the specified parameters.
     *
     * @param mode     the video mode to use for rendering.
     * @param title    the window title.
     * @param style    the window style.
     * @param settings the settings for the OpenGL context.
     * @see #create(org.jsfml.window.VideoMode, String, int, org.jsfml.window.ContextSettings)
     */
    public RenderWindow(VideoMode mode, String title, int style, ContextSettings settings) {
        this();
        create(mode, title, style, settings);
    }

    /**
     * Constructs a new render window and creates it with default context settings.
     *
     * @param mode  the video mode to use for rendering.
     * @param title the window title.
     * @param style the window style.
     * @see #create(org.jsfml.window.VideoMode, String, int)
     */
    public RenderWindow(VideoMode mode, String title, int style) {
        this();
        create(mode, title, style, new ContextSettings());
    }

    /**
     * Constructs a new render window and creates it with default style and context settings.
     *
     * @param mode  The video mode to use for rendering.
     * @param title The window title.
     */
    public RenderWindow(VideoMode mode, String title) {
        this();
        create(mode, title, DEFAULT, new ContextSettings());
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

    @Override
    public void create(VideoMode mode, String title, int style, ContextSettings settings) {
        super.create(mode, title, style, settings);

        defaultView = new View(nativeGetDefaultView());

        if (view == null) {
            view = defaultView;
        }
    }

    private native long nativeCapture();

    /**
     * Copies the current contents of the window to an image.
     * <p/>
     * This is a slow operation and should be used to take screenshots, not to re-use
     * resulting image as a texture. For that, use {@link Texture#update(org.jsfml.window.Window)}
     * or a {@link RenderTexture} instead.
     *
     * @return the image with the current contents of the window.
     */
    public Image capture() {
        final Image image = new Image(nativeCapture());
        UnsafeOperations.manageSFMLObject(image, true);
        return image;
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
