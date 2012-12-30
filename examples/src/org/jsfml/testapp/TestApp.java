package org.jsfml.testapp;

import org.jsfml.internal.SFMLNative;
import org.jsfml.examples.scene.Standalone;
import org.jsfml.graphics.Shader;
import org.jsfml.graphics.Texture;

import java.awt.*;


/**
 * This is a demo application used to test JSFML features on various systems and platforms.
 * <p/>
 * It will write various kinds of information to the standard output and errors streams,
 * which are supposed to be redirected to log files.
 * <p/>
 * After that, it will proceed executing the example scene.
 */
public class TestApp {
    /**
     * The main method that starts the application.
     *
     * @param args Command-line arguments; not used.
     */
    public static void main(String[] args) {
        System.out.println("JSFML TestApp started.");
        System.out.println();

        try {
            run();
        } catch (Throwable t) {
            System.err.println("The test application crashed!");
            t.printStackTrace();
        }

        System.out.println();
        System.out.println("JSFML TestApp terminated.");
    }

    private static void run() throws Throwable {
        //JVM facts
        {
            System.out.println("Detecting basic JVM facts ...");
            System.out.println("java.vendor: " + System.getProperty("java.vendor"));
            System.out.println("java.version: " + System.getProperty("java.version"));
            System.out.println("headless: " + GraphicsEnvironment.isHeadless());
            System.out.println();
        }

        //OS facts
        {
            System.out.println("Detecting basic operating system facts ...");
            final String osName = System.getProperty("os.name");

            System.out.println("os.name: " + osName);
            System.out.println("os.arch: " + System.getProperty("os.arch"));
            System.out.println("os.version: " + System.getProperty("os.version"));
            System.out.println();

            if (osName.contains(SFMLNative.OS_NAME_LINUX)) {
                System.out.println("Detecting Linux distribution ....");
                String linuxInfo = LinuxHelper.geLinuxDistributionInfo();
                if (linuxInfo != null)
                    System.out.println(linuxInfo);
                else
                    System.err.println("Could not detect this Linux distribution. :(");

                System.out.println();
            }
        }

        //Try to load native libs.
        SFMLNative.loadNativeLibraries();

        //If we made it here, things are looking good!
        System.out.println("SFMLNative.loadNativeLibraries() succeeded.");
        System.out.println();

        //Some SFML facts
        {
            System.out.println("Detecting basic SFML facts ...");
            System.out.println("Texture.getMaximumSize(): " + Texture.getMaximumSize());
            System.out.println("Shader.isAvailable(): " + Shader.isAvailable());
            System.out.println();
        }

        //Start demo scene
        System.out.println("Starting the demo scene ...");
        Standalone.main(new String[0]);
    }
}
