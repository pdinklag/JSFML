package org.jsfml.examples.tutorials;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

/**
 * This is a very basic tutorial, showing how to create a
 * RenderWindow that is filled with a color and can be
 * closed using the close button.
 */
public class BasicTutorial {
    /**
     * The main method that starts the application.
     *
     * @param args The arguments passed to the application. We will not need these here.
     */
    public static void main(String[] args) {
        /**
         * This creates the window with a size of 640x480 pixels and
         * a title saying "Hello JSFML!"
         */
        RenderWindow window = new RenderWindow(new VideoMode(640, 480), "Hello JSFML!");

        /**
         * Limit the window to 60 frames per second. This will make sure that
         * the application does not use all of the CPU, but will still run smoothly.
         */
        window.setFramerateLimit(60);

        /**
         * Defines the background color of the window.
         */
        Color backgroundColor = Color.RED;

        /**
         * As long as the window is opened, it will be updated with a certain color.
         */
        while (window.isOpen()) {
            /**
             * Process the events received from the window.
             */
            for (Event event = window.pollEvent(); event != null; event = window.pollEvent()) {
                if (event.getType() == Event.Type.CLOSED) {
                    /**
                     *  The close button was pressed - close the window!
                     */
                    window.close();
                }
            }

            /**
             * Clear the window with the background color.
             */
            window.clear(backgroundColor);

            /**
             * Display what was drawn - in this example, this is only the background color.
             */
            window.display();
        }
    }
}
