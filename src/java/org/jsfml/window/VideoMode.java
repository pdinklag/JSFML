package org.jsfml.window;

import org.jsfml.Intercom;
import org.jsfml.JSFMLException;
import org.jsfml.SFMLNative;

import java.io.Serializable;

/**
 * Data structure for video modes (width, height, pixel depth).
 */
@Intercom
public class VideoMode implements Serializable {
    private static final long serialVersionUID = 8608938390916786270L;

    static {
        try {
            SFMLNative.loadNativeLibraries();
        } catch (JSFMLException ex) {
            throw new UnsatisfiedLinkError(ex.getMessage());
        }
    }

    /**
     * The width of the video mode in pixels.
     */
    @Intercom
    public final int width;

    /**
     * The height of the video mode in pixels.
     */
    @Intercom
    public final int height;

    /**
     * The pixel depth of the video mode, in bits per pixel.
     */
    @Intercom
    public final int bpp;

    /**
     * Retrieves the current desktop's video mode.
     *
     * @return The current desktop's video mode.
     */
    public static native VideoMode getDesktopMode();

    /**
     * Retrieves a list of supported fullscreen video modes.
     *
     * @return A list of supported fullscreen video modes.
     */
    public static native VideoMode[] getFullscreenModes();

    /**
     * Creates a new video mode.
     *
     * @param width  The width in pixels.
     * @param height The height in pixels.
     * @param bpp    The pixel depth in bits per pixel.
     */
    @Intercom
    public VideoMode(int width, int height, int bpp) {
        this.width = width;
        this.height = height;
        this.bpp = bpp;
    }

    /**
     * Creates a new video mode with 32 bits per pixel.
     *
     * @param width  The width in pixels.
     * @param height The height in pixels.
     */
    public VideoMode(int width, int height) {
        this.width = width;
        this.height = height;
        this.bpp = 32;
    }

    /**
     * Checks whether this display mode is a valid (supported) fullscreen mode.
     *
     * @return <tt>true</tt> if this video mode is a valid fullscreen mode, <tt>false</tt> otherwise.
     */
    public boolean isValid() {
        VideoMode[] validModes = getFullscreenModes();
        for (VideoMode mode : validModes) {
            if (mode.equals(this))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoMode)) return false;

        VideoMode videoMode = (VideoMode) o;

        if (bpp != videoMode.bpp) return false;
        if (height != videoMode.height) return false;
        if (width != videoMode.width) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + bpp;
        return result;
    }

    @Override
    public String toString() {
        return "VideoMode{" +
                "width=" + width +
                ", height=" + height +
                ", bpp=" + bpp +
                '}';
    }
}
