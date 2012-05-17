package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.SFMLNativeObject;
import org.jsfml.StreamUtil;
import org.jsfml.system.Vector2i;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class for loading, manipulating and saving images.
 */
public class Image extends SFMLNativeObject {
    /**
     * Creates a new image.
     */
    public Image() {
        super();
    }

    @SuppressWarnings("deprecation")
    Image(long wrap) {
        super(wrap);
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    private native void nativeCreate(int width, int height, Color color);

    /**
     * Generates a new image and fills it with a color.
     *
     * @param width  The image's width.
     * @param height The image's height.
     * @param color  The fill color of the image.
     */
    public void create(int width, int height, @NotNull Color color) {
        if (color == null)
            throw new IllegalArgumentException("color must not be null.");

        nativeCreate(width, height, color);
    }

    /**
     * Generates a new image and fills it with black.
     *
     * @param width  The image's width.
     * @param height The image's height.
     */
    public void create(int width, int height) {
        create(width, height, new Color(0, 0, 0));
    }

    private native boolean nativeLoadFromMemory(byte[] memory);

    /**
     * Fully loads all available bytes from an {@link java.io.InputStream} and attempts to load the image from it.
     *
     * @param in The input stream to read from.
     * @throws java.io.IOException In case an I/O error occurs.
     */
    public void loadFromStream(InputStream in) throws IOException {
        if (!nativeLoadFromMemory(StreamUtil.readStream(in)))
            throw new IOException("Failed to load image from stream.");
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param file The file to load the texture from.
     * @throws IOException In case an I/O error occurs.
     */
    public void loadFromFile(File file) throws IOException {
        if (!nativeLoadFromMemory(StreamUtil.readFile(file)))
            throw new IOException("Failed to load image from file: " + file);
    }

    private native boolean nativeSaveToFile(String fileName);

    /**
     * Attempts to save the image to a file.
     *
     * @param file The file to write.
     */
    public void saveToFile(@NotNull File file) throws IOException {
        if (file == null)
            throw new IllegalArgumentException("file must not be null");

        if (!nativeSaveToFile(file.getAbsolutePath()))
            throw new IOException("Failed to save image to file: " + file);
    }

    /**
     * Gets the size of the image.
     *
     * @return The size of the image.
     */
    public native Vector2i getSize();

    private native void nativeCreateMaskFromColor(Color color, int alpha);

    /**
     * Creates a transparency mask from the given color.
     *
     * @param color The color to be made transparent.
     * @param alpha The alpha value to assign to transparent pixels.
     */
    public void createMaskFromColor(@NotNull Color color, int alpha) {
        if (color == null)
            throw new IllegalArgumentException("color must not be null.");

        nativeCreateMaskFromColor(color, alpha);
    }


    /**
     * Creates a transparency mask from the given color.
     *
     * @param color The color to be made transparent.
     */
    public void createMaskFromColor(Color color) {
        createMaskFromColor(color, 0);
    }

    private native void nativeCopy(Image source, int destX, int destY, IntRect sourceRect, boolean applyAlpha);

    /**
     * Copies a portion of another image onto this image.
     *
     * @param source     The source image.
     * @param destX      The destination X coordinate.
     * @param destY      The destination X coordinate.
     * @param sourceRect The source rectangle. An empty rectangle means the full image.
     * @param applyAlpha <tt>true</tt> to copy alpha values as well, <tt>false</tt> to leave the destination alpha.
     */
    public void copy(@NotNull Image source, int destX, int destY, @NotNull IntRect sourceRect, boolean applyAlpha) {
        if (source == null)
            throw new IllegalArgumentException("source must not be null.");

        if (sourceRect == null)
            throw new IllegalArgumentException("sourceRect must not be null.");

        nativeCopy(source, destX, destY, sourceRect, applyAlpha);
    }

    /**
     * Copies a portion of another image onto this image.
     *
     * @param source     The source image.
     * @param destX      The destination X coordinate.
     * @param destY      The destination X coordinate.
     * @param sourceRect The source rectangle. An empty rectangle means the full image.
     */
    public void copy(Image source, int destX, int destY, IntRect sourceRect) {
        copy(source, destX, destY, sourceRect, false);
    }

    /**
     * Copies a portion of another image onto this image.
     *
     * @param source The source image.
     * @param destX  The destination X coordinate.
     * @param destY  The destination X coordinate.
     */
    public void copy(Image source, int destX, int destY) {
        copy(source, destX, destY, new IntRect(), false);
    }

    private native void nativeSetPixel(int x, int y, Color color);

    /**
     * Sets the color of a certain pixel.
     *
     * @param x     The pixel's X coordinate.
     * @param y     The pixel's Y coordinate.
     * @param color The color to apply to the pixel.
     */
    public void setPixel(int x, int y, @NotNull Color color) {
        if (color == null)
            throw new IllegalArgumentException("color must not be null.");

        nativeSetPixel(x, y, color);
    }

    /**
     * Gets the color of a certain pixel.
     *
     * @param x The pixel's X coordinate.
     * @param y The pixel's Y coordinate.
     * @return The pixel's color.
     */
    public native Color getPixel(int x, int y);

    /**
     * Flips the image horizontally.
     */
    public native void flipHorizontally();

    /**
     * Flips the image vertically.
     */
    public native void flipVertically();
}
