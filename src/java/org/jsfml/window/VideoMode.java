package org.jsfml.window;

import org.jsfml.internal.Intercom;
import org.jsfml.internal.SFMLNative;

import java.io.Serializable;

/**
 * Represents a video mode (width, height and bits per pixel).
 * <p/>
 * It defines the width, height and pixel depth of a window and must be
 * supported by the monitor hardware in order to be used in fullscreen.
 *
 * @see Window#create(VideoMode, String, int)
 */
@Intercom
public final class VideoMode implements Serializable {
    private static final long serialVersionUID = 8608938390916786270L;

    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * The width of the video mode, in pixels.
     */
    @Intercom
    public final int width;

    /**
     * The height of the video mode, in pixels.
     */
    @Intercom
    public final int height;

    /**
     * The pixel depth, in bits per pixel, of the video mode.
     */
    @Intercom
    public final int bpp;

    /**
     * Retrieves the desktop's current video mode / screen resolution.
     *
     * @return the desktop's current video mode.
     */
    public static native VideoMode getDesktopMode();

    /**
     * Retrieves the list of supported fullscreen video modes.
     *
     * @return the list of supported fullscreen video modes.
     */
    public static native VideoMode[] getFullscreenModes();

    /**
     * Constructs a new video mode.
     *
     * @param width  the width, in pixels.
     * @param height the height, in pixels.
     * @param bpp    the pixel depth, in bits per pixel.
     */
    @Intercom
    public VideoMode(int width, int height, int bpp) {
        this.width = width;
        this.height = height;
        this.bpp = bpp;
    }

    /**
     * Constructs a new video mode with a pixel depth of 32 bits per pixel.
     *
     * @param width  the width, in pixels.
     * @param height the height, in pixels.
     */
    public VideoMode(int width, int height) {
        this(width, height, 32);
    }

    /**
     * Checks whether this display mode is a valid fullscreen mode, ie whether
     * the current monitor supports it for fullscreen.
     *
     * @return {@code true} if this video mode is a valid fullscreen mode,
     *         {@code false} otherwise.
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
        if (o instanceof VideoMode) {
            VideoMode v = (VideoMode) o;
            return (v.width == width &&
                    v.height == height &&
                    v.bpp == bpp);
        } else {
            return false;
        }
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
