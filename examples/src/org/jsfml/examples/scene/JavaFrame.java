package org.jsfml.examples.scene;

import org.jsfml.graphics.RenderCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Java Swing application for the example scene.
 */
public class JavaFrame {
    public static void main(String[] args) {
        //Set the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Failed to set system look and feel!");
            ex.printStackTrace();
        }

        //Create the Java frame
        JFrame frame = new JFrame("JFrame");
        frame.setSize(640, 480);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Try and apply an icon to the frame
        try {
            BufferedImage image = ImageIO.read(Standalone.class.getResourceAsStream(
                    "/resources/jsfml-icon.png"));

            frame.setIconImage(image);
        } catch (IOException ex) {
            System.err.println("Failed to load icon resource!");
            ex.printStackTrace();
        }

        //Create the RenderCanvas
        RenderCanvas canvas = new RenderCanvas();
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(new JLabel("I'm a JLabel!"), BorderLayout.SOUTH);
        frame.setVisible(true);

        //Launch the example application
        new ExampleApp(canvas.getRenderWindow()).play(new ExampleScene());
    }
}
