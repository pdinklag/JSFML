package org.jsfml.graphics;

import org.jsfml.JSFMLException;
import org.jsfml.SFMLNativeObject;
import org.jsfml.system.Vector2f;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

import java.awt.image.BufferedImage;

/**
 * Window that can serve as a target for 2D drawing.
 */
public class RenderWindow extends SFMLNativeObject implements RenderTarget, Window {
    private View view, defaultView;
    private long iconPtr;

    /**
     * Constructs a new window without creating it.
     *
     * @see RenderWindow#create(org.jsfml.window.VideoMode, String, int, org.jsfml.window.ContextSettings)
     */
    public RenderWindow() {
        super();

        defaultView = new View(nativeGetDefaultView());
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

    private native void nativeClear(Color color);

    @Override
    public void clear(Color color) {
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
    public void setView(View view) {
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
    public IntRect getViewport(View view) {
        if (view == null)
            throw new IllegalArgumentException("view must not be null.");

        return nativeGetViewport(view);
    }

    @Override
    public native Vector2f convertCoords(float x, float y);

    private native Vector2f nativeConvertCoords(float x, float y, View view);

    @Override
    public Vector2f convertCoords(float x, float y, View view) {
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
    public void draw(Drawable drawable, RenderStates renderStates) {
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
    public void draw(Vertex[] vertices, PrimitiveType type, RenderStates states) {
        if (vertices == null)
            throw new IllegalArgumentException("vertices must not be null.");

        if (type == null)
            throw new IllegalArgumentException("type must not be null.");

        if (states == null)
            throw new IllegalArgumentException("states must not be null.");

        nativeDraw(vertices, type, states);
    }

    private native void nativeCreate(VideoMode mode, String title, int style, ContextSettings settings);

    @Override
    public void create(VideoMode mode, String title, int style, ContextSettings settings) {
        if (mode == null)
            throw new IllegalArgumentException("mode must not be null.");

        if (title == null)
            throw new IllegalArgumentException("title must not be null.");

        if (settings == null)
            throw new IllegalArgumentException("settings must not be null.");

        nativeCreate(mode, title, style, settings);
    }

    @Override
    public void create(VideoMode mode, String title, int style) {
        create(mode, title, style, new ContextSettings());
    }

    @Override
    public void create(VideoMode mode, String title) {
        create(mode, title, DEFAULT, new ContextSettings());
    }

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

    private native void nativeSetTitle(String title);

    @Override
    public void setTitle(String title) {
        if (title == null)
            throw new IllegalArgumentException("title must not be null.");

        nativeSetTitle(title);
    }

    @Override
    public native void show(boolean show);

    @Override
    public native void enableKeyRepeat(boolean enable);

    private native long nativeSetIcon(int width, int height, int bpp, int[] pixels);

    private native void nativeDeleteIcon(long ptr);

    @Override
    public void setIcon(BufferedImage icon) throws JSFMLException {
        deleteIcon();

        int[] pixels = icon.getData().getPixels(0, 0, icon.getWidth(), icon.getHeight(), (int[]) null);

        //FIXME UGLY way to "determine" bytes per pixel
        int bytesPerPixel;
        if (pixels.length % 3 == 0)
            bytesPerPixel = 3;
        else if (pixels.length % 4 == 0)
            bytesPerPixel = 4;
        else
            throw new JSFMLException("Unsupported image type - please make sure your image is 32-bit RGBA or 24-bit RGB.");

        iconPtr = nativeSetIcon(
                icon.getWidth(),
                icon.getHeight(),
                bytesPerPixel,
                pixels);
    }

    @Override
    public native void setActive(boolean active);

    @Override
    public native void display();

    @Override
    public native void setFramerateLimit(int flimit);

    @Override
    public native long getFrameTime();

    @Override
    public native void setJoystickTreshold(float treshold);

    @Override
    public native void pushGLStates();

    @Override
    public native void popGLStates();

    @Override
    public native void resetGLStates();

    private void deleteIcon() {
        if (iconPtr != 0) {
            nativeDeleteIcon(iconPtr);
            iconPtr = 0;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        deleteIcon();
        super.finalize();
    }
}
