package org.jsfml.examples.scene;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.event.Event;

/**
 * Example JSFML Application.
 */
public class ExampleApp {
    //The max FPS to run at.
    private final static int FPS = 60;
    private final static float FRAME_TIME = 1.0f / (float) FPS;

    //Maximum value for time delta between frames (for fluid simulation in case of hangups).
    private final static float MAX_DT = 10.0f * FRAME_TIME;

    private final RenderWindow window;

    /**
     * Instantiates the application.
     *
     * @param window The window to use.
     */
    public ExampleApp(RenderWindow window) {
        //Set the window
        this.window = window;

        //Set a framerate limit
        window.setFramerateLimit(FPS);
    }

    /**
     * Plays a scene in the application and returns when done.
     *
     * @param scene The scene to play.
     */
    public void play(Scene scene) {
        //Attempt to initialize the scene
        try {
            scene.initialize(window);
        } catch (Exception ex) {
            //Scene initialization failed, exit
            ex.printStackTrace();
            return;
        }

        //Enter main loop
        while (!scene.isDone()) {
            //Delegate events to the scene
            for (Event event = window.pollEvent(); event != null; event = window.pollEvent())
                scene.handleEvent(event);

            //Calculate time delta in seconds
            float dt = Math.min((float) window.getFrameTime() / 1000.0f, MAX_DT);

            //Update the scene
            scene.update(dt);

            //Clear the window
            window.clear();

            //Render the scene
            scene.render(window);

            //Display the scene
            window.display();
        }
    }
}
