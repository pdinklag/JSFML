package org.jsfml.graphics;

import org.jsfml.JSFMLError;
import org.jsfml.NotNull;
import org.jsfml.SFMLNative;
import org.jsfml.UnsafeOperations;
import org.jsfml.window.ContextSettings;

import java.awt.*;

/**
 * An AWT canvas that can be used as a JSFML render target.
 */
public class RenderCanvas extends Canvas {
    private static final long serialVersionUID = 5458266655879697610L;

    private final ContextSettings settings;
    private RenderWindow renderWindow = null;
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
                renderWindow = new RenderWindow(ptr);
                UnsafeOperations.manageSFMLObject(renderWindow, true);

                if (System.getProperty("os.name").contains("Linux")) {
                    /**
                     * On Linux, there is a problem that prevents key and mouse button events to
                     * be passed to SFML. The cause of that is not yet known.
                     *
                     * As a workaround, we install an AWT event listener that will translate
                     * AWT events to SFML events.
                     */
                    AWTEventListener awtListener = new AWTEventListener();
                    addKeyListener(awtListener);
                    addMouseListener(awtListener);
                    addMouseWheelListener(awtListener);
                    renderWindow.setAwtListener(awtListener);
                }
            }
        }
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
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);

        if (renderWindow != null) {
            //TODO pass resize event?
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (renderWindow != null) {
            renderWindow.close();
        }

        super.finalize();
    }
}
