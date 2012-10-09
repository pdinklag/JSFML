package org.jsfml.examples.scene;

import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;

import java.io.IOException;

/**
 * Standalone application for the example scene.
 */
public class Standalone {
    public static void main(String[] args) {
        //Set OpenGL 3.0 to be the desired version
        ContextSettings settings = new ContextSettings(3, 0);

        //Create a render window
        RenderWindow window = new RenderWindow(
                new VideoMode(640, 480),
                "JSFML Standalone Example",
                Window.DEFAULT,
                settings);

        //Try and apply an icon to the window
        try {
            Image icon = new Image();
            icon.loadFromStream(Standalone.class.getResourceAsStream("/resources/jsfml-icon.png"));
            window.setIcon(icon);
        } catch (IOException ex) {
            System.err.println("Failed to load icon resource!");
            ex.printStackTrace();
        }

        //Launch the example application
        new ExampleApp(window).play(new ExampleScene());
    }
}
