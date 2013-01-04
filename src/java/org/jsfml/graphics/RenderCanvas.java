package org.jsfml.graphics;

import org.jsfml.internal.JSFMLError;
import org.jsfml.internal.NotNull;
import org.jsfml.internal.SFMLNative;
import org.jsfml.internal.UnsafeOperations;
import org.jsfml.window.ContextSettings;

import java.awt.*;

/**
 * An AWT canvas that contains a {@link RenderWindow}.
 * <p/>
 * This allows for a {@code RenderWindow} to be integrated into a Swing UI. Once validated,
 * it can be retrieved using the {@link #getRenderWindow()} method.
 */
public class RenderCanvas extends Canvas {
    private static final long serialVersionUID = 5458266655879697610L;

    private final ContextSettings settings;
    private AWTRenderWindow renderWindow = null;
    private boolean initialized = false;

    /**
     * Constructs a new render canvas with default context settings.
     */
    public RenderCanvas() {
        this(new ContextSettings());
    }

    /**
     * Constructs a new render canvas with the specified context settings.
     *
     * @param settings the OpenGL context settings.
     */
    public RenderCanvas(@NotNull ContextSettings settings) {
        SFMLNative.ensureDisplay();
        SFMLNative.loadNativeLibraries();

        if (settings == null)
            throw new NullPointerException("settings must not be null");

        this.settings = settings;
    }

    private native long nativeCreateRenderWindow(ContextSettings settings);

    @SuppressWarnings("deprecation")
    @Override
    public void validate() {
        super.validate();

        if (isValid() && !initialized) {
            initialized = true;

            long ptr = nativeCreateRenderWindow(settings);
            if (ptr == 0) {
                throw new JSFMLError("Failed to initialize RenderCanvas");
            } else {
                renderWindow = new AWTRenderWindow(ptr);
                UnsafeOperations.manageSFMLObject(renderWindow, true);

                addComponentListener(renderWindow);
                addFocusListener(renderWindow);
                addKeyListener(renderWindow);
                addMouseListener(renderWindow);
                addMouseMotionListener(renderWindow);
                addMouseWheelListener(renderWindow);

                //vvoid occasional flicker; painting is done by calling "display"
                setIgnoreRepaint(true);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        //nothing to paint
    }

    /**
     * Retrieves the {@link RenderWindow} encapsuled by this canvas that can be used for JSFML drawing.
     * <p/>
     * The render window is not available before the canvas has been validated.
     *
     * @return the render window, or {@code null} if the canvas was not validated yet.
     */
    public RenderWindow getRenderWindow() {
        return renderWindow;
    }

    @Override
    protected void finalize() throws Throwable {
        if (renderWindow != null) {
            removeComponentListener(renderWindow);
            removeFocusListener(renderWindow);
            removeKeyListener(renderWindow);
            removeMouseListener(renderWindow);
            removeMouseMotionListener(renderWindow);
            removeMouseWheelListener(renderWindow);
            renderWindow.close();
            renderWindow = null;
        }

        super.finalize();
    }
}
