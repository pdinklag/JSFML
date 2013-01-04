package org.jsfml.examples.scene;

import org.jsfml.graphics.RenderCanvas;
import org.jsfml.graphics.RenderWindow;

import java.applet.Applet;
import java.awt.*;

/**
 * Java Applet for the example scene.
 */
public class JavaApplet extends Applet implements RenderCanvas.Runner {
    private static final long serialVersionUID = 3408247691675571495L;

    private final RenderCanvas canvas = new RenderCanvas(this);

    public JavaApplet() {
        setLayout(new GridLayout());
        add(canvas);
    }

    @Override
    public void init() {
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        canvas.closeWindow();
    }

    @Override
    public void destroy() {
    }

    @Override
    public void run(RenderWindow window) {
        new ExampleApp(window).play(new ExampleScene());
    }
}
