package org.jsfml.examples.scene;

import org.jsfml.graphics.RenderCanvas;
import org.jsfml.graphics.RenderWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Java Swing application for the example scene.
 */
public class JavaFrame extends JFrame implements ActionListener, RenderCanvas.Runner {
    private static final long serialVersionUID = 5155515612001343084L;

    private final RenderCanvas canvas = new RenderCanvas(this);;
    private final JMenuBar menuBar = new JMenuBar();

    private final JMenu fileMenu = new JMenu("File");
    private final JMenuItem fileExit = new JMenuItem("Exit");

    private final JButton btClose = new JButton("Close");

    public static void main(String[] args) {
        //Set the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Failed to set system look and feel!");
            ex.printStackTrace();
        }

        //Create and show the frame
        JavaFrame frame = new JavaFrame();
        frame.setVisible(true);
    }

    public JavaFrame() {
        //Create the Java frame
        super("Example Scene in a Swing frame");
        setSize(640, 480);
        setLayout(new BorderLayout(3, 3));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Try and apply an icon to the frame
        try {
            BufferedImage image = ImageIO.read(Standalone.class.getResourceAsStream(
                    "/resources/jsfml-icon.png"));

            setIconImage(image);
        } catch (IOException ex) {
            System.err.println("Failed to load icon resource!");
            ex.printStackTrace();
        }

        //Build Menu
        fileExit.addActionListener(this);
        fileMenu.add(fileExit);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        //Add render area
        {
            final JPanel panel = new JPanel(new GridLayout());
            panel.setBorder(new TitledBorder("Render Area"));
            panel.add(canvas);

            add(panel, BorderLayout.CENTER);
        }

        //Add control area
        {
            btClose.addActionListener(this);

            final JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panel.setBorder(new TitledBorder("Control Area"));
            panel.add(btClose);

            add(panel, BorderLayout.SOUTH);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == fileExit || source == btClose) {
            //Close the render canvas
            canvas.closeWindow();

            //Close the frame
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void run(RenderWindow window) {
        new ExampleApp(window).play(new ExampleScene());
    }
}
