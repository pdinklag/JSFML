package org.jsfml.graphics;

import org.jsfml.internal.JSFMLError;
import org.jsfml.internal.NotNull;
import org.jsfml.internal.SFMLNative;
import org.jsfml.internal.UnsafeOperations;
import org.jsfml.window.ContextSettings;

import java.awt.*;

/**
 * An AWT canvas that can be used as a JSFML render target.
 */
public class RenderCanvas extends Canvas {
    private static final long serialVersionUID = 5458266655879697610L;

    private final ContextSettings settings;
    private AWTRenderWindow renderWindow = null;
    private boolean initialized = false;

    /**
     * Creates a new render canvas with default context settings.
     */
    public RenderCanvas() {
        this(new ContextSettings());
    }

    /**
     * Creates a new render canvas.
     *
     * @param settings The settings for the OpenGL context.
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
     * Retrieves the render window encapsuled by this canvas that can be used for JSFML drawing.
     * <p/>
     * Note that this may be <tt>null</tt> if the canvas was not yet initialized.
     *
     * @return The render target, or <tt>null</tt> if it was not yet initialized.
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
