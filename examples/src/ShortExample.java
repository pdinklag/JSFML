import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.io.File;
import java.io.IOException;

/**
 * This is the short simple example from the front page of the SFML documentation "translated" to JSFML.
 */
public class ShortExample {
    public static void main(String[] args) throws IOException {
        //Create the main window
        RenderWindow window = new RenderWindow(new VideoMode(800, 600), "JSFML window");

        //Load a sprite to display
        Texture texture = new Texture();
        texture.loadFromFile(new File("cute_image.jpg"));

        Sprite sprite = new Sprite(texture);

        //Create a graphical text to display
        Font font = new Font();
        font.loadFromFile(new File("arial.ttf"));

        Text text = new Text("Hello JSFML", font, 50);

        //Load a music to play
        Music music = new Music();
        music.openFromFile(new File("nice_music.ogg"));

        //Play the music
        music.play();

        //Start the game loop
        while (window.isOpen()) {
            //Process events
            Event event = window.pollEvent();
            while (event != null) {
                //Close window : exit
                if (event.getType() == Event.CLOSED)
                    window.close();
            }
        }

        //Clear screen
        window.clear();

        //Draw the sprite
        window.draw(sprite);

        //Draw the string
        window.draw(text);

        //Update the window
        window.display();
    }
}
