package org.jsfml.window;

import org.jsfml.internal.Intercom;
import org.jsfml.internal.IntercomHelper;
import org.jsfml.internal.SFMLNative;

import java.io.Serializable;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a video mode (width, height and bits per pixel).
 * <p/>
 * It defines the width, height and pixel depth of a window and must be
 * supported by the monitor hardware in order to be used in fullscreen.
 *
 * @see Window#create(VideoMode, String, int)
 */
public final class VideoMode implements Serializable {
    private static final long serialVersionUID = 8608938390916786270L;

    //cache
    private static VideoMode desktopMode = null;
    private static Set<VideoMode> fullscreenModes = null;

    static {
        SFMLNative.loadNativeLibraries();
    }

    private static native void nativeGetDesktopMode(Buffer buffer);

    /**
     * Retrieves the desktop's current video mode / screen resolution.
     *
     * @return the desktop's current video mode.
     */
    public static VideoMode getDesktopMode() {
        if (desktopMode == null) {
            final IntBuffer buffer = IntercomHelper.getBuffer().asIntBuffer();
            nativeGetDesktopMode(buffer);
            desktopMode = new VideoMode(buffer.get(0), buffer.get(1), buffer.get(2));
        }

        return desktopMode;
    }

    private static native int nativeGetFullscreenModeCount();

    private static native void nativeGetFullscreenModes(Buffer buffer);

    private static Set<VideoMode> getFullscreenModeSet() {
        if (fullscreenModes == null) {
            final int num = nativeGetFullscreenModeCount();
            final IntBuffer buffer = ByteBuffer.allocateDirect(12 * num).order(
                    ByteOrder.nativeOrder()).asIntBuffer();

            nativeGetFullscreenModes(buffer);

            fullscreenModes = new HashSet<>(num);
            for (int i = 0; i < num; i++) {
                fullscreenModes.add(new VideoMode(
                        buffer.get(3 * i),
                        buffer.get(3 * i + 1),
                        buffer.get(3 * i + 2)));
            }
        }

        return fullscreenModes;
    }

    /**
     * Retrieves the list of supported fullscreen video modes.
     *
     * @return the list of supported fullscreen video modes.
     */
    public static VideoMode[] getFullscreenModes() {
        final Set<VideoMode> modes = getFullscreenModeSet();
        return modes.toArray(new VideoMode[modes.size()]);
    }

    /**
     * The width of the video mode, in pixels.
     */
    public final int width;

    /**
     * The height of the video mode, in pixels.
     */
    public final int height;

    /**
     * The pixel depth, in bits per pixel, of the video mode.
     */
    public final int bitsPerPixel;

    /**
     * Constructs a new video mode.
     *
     * @param width        the width, in pixels.
     * @param height       the height, in pixels.
     * @param bitsPerPixel the pixel depth, in bits per pixel.
     */
    @Intercom
    public VideoMode(int width, int height, int bitsPerPixel) {
        this.width = width;
        this.height = height;
        this.bitsPerPixel = bitsPerPixel;
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
        return getFullscreenModeSet().contains(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VideoMode) {
            VideoMode v = (VideoMode) o;
            return (v.width == width &&
                    v.height == height &&
                    v.bitsPerPixel == bitsPerPixel);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + bitsPerPixel;
        return result;
    }

    @Override
    public String toString() {
        return "VideoMode{" +
                "width=" + width +
                ", height=" + height +
                ", bitsPerPixel=" + bitsPerPixel +
                '}';
    }
}
