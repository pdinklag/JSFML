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
 * This allows for a {@code RenderWindow} to be integrated into a Swing UI.
 * <p/>
 * In each of the constructors, a {@link Runner} is expected. Once the
 * internal render window has been initialized and is ready for use,
 * the runner's {@code run} method will be invoked and passed the render window.
 */
public final class RenderCanvas extends Canvas {
    private static final long serialVersionUID = 5458266655879697610L;

    /**
     * Runs the rendering loop for the render canvas.
     */
    public static interface Runner {
        /**
         * Called in a new thread once the render canvas' {@code RenderWindow}
         * has been initialized and is ready for use.
         * <p/>
         * This method is supposed to enter a <i>main loop</i> that does the rendering.
         * <p/>
         * It should regularly check the window's {@link org.jsfml.graphics.RenderWindow#isOpen()}
         * state and return once that returns {@code false}.
         *
         * @param window the render window.
         */
        public void run(RenderWindow window);
    }

    private final ContextSettings settings;
    private final Runner runner;

    private AWTRenderWindow renderWindow = null;
    private boolean running = false;

    /**
     * Constructs a new render canvas with default context settings.
     *
     * @param runner the main loop runner.
     * @see Runner
     */
    public RenderCanvas(Runner runner) {
        this(runner, new ContextSettings());
    }

    /**
     * Constructs a new render canvas with the specified context settings.
     *
     * @param runner   the main loop runner.
     * @param settings the OpenGL context settings.
     * @see Runner
     */
    public RenderCanvas(Runner runner, @NotNull ContextSettings settings) {
        SFMLNative.ensureDisplay();
        SFMLNative.loadNativeLibraries();

        if (settings == null)
            throw new NullPointerException("settings must not be null");

        this.runner = runner;
        this.settings = settings;
    }

    /**
     * Closes the internal render window if available.
     * <p/>
     * This method simply delegates to the render window's
     * {@link org.jsfml.graphics.RenderWindow#close()} method and thus
     * allows to close it from outside the {@link Runner}.
     */
    public void closeWindow() {
        if (renderWindow != null) {
            renderWindow.close();
        }
    }

    private native long nativeCreateRenderWindow(ContextSettings settings);

    @Override
    public void paint(Graphics g) {
        /**
         * There is nothing to paint here, but the fact that this method is being
         * called means that the canvas is now valid and showing, ie this is the
         * time to initialize the RenderWindow if that didn't happen yet.
         */
        if (!running) {
            running = true;

            new Thread(new Runnable() {
                @Override
                public void run() {
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

                        //avoid occasional flicker; painting is done by calling "display"
                        setIgnoreRepaint(true);

                        //Start runner
                        runner.run(renderWindow);
                    }
                }
            }, "JSFML RenderCanvas Runner").start();
        }
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
