package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.SFMLNativeObject;
import org.jsfml.StreamUtil;
import org.jsfml.system.Vector2i;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Provides methods for loading, manipulating and saving images.
 */
public class Image extends SFMLNativeObject {
    /**
     * Constructs a new empty image.
     */
    public Image() {
        super();
    }

    @SuppressWarnings("deprecation")
    Image(long wrap) {
        super(wrap);
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected void nativeSetExPtr() {
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native void nativeCreate(int width, int height, Color color);

    /**
     * Generates a new image and fills it with a color.
     *
     * @param width  the image's width.
     * @param height the image's height.
     * @param color  the fill color of the image.
     */
    public void create(int width, int height, @NotNull Color color) {
        nativeCreate(width, height, Objects.requireNonNull(color));
    }

    /**
     * Generates a new image and fills it with black.
     *
     * @param width  the image's width.
     * @param height the image's height.
     */
    public final void create(int width, int height) {
        create(width, height, Color.BLACK);
    }

    private native boolean nativeLoadFromMemory(byte[] memory);

    /**
     * Fully loads all available bytes from an {@link java.io.InputStream}
     * and attempts to load the image from it.
     *
     * @param in the input stream to read from.
     * @throws java.io.IOException in case an I/O error occurs.
     */
    public void loadFromStream(InputStream in) throws IOException {
        if (!nativeLoadFromMemory(StreamUtil.readStream(in)))
            throw new IOException("Failed to load image from stream.");
    }

    /**
     * Attempts to load an image from a file.
     *
     * @param file the file to load the texture from.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromFile(File file) throws IOException {
        if (!nativeLoadFromMemory(StreamUtil.readFile(file)))
            throw new IOException("Failed to load image from file: " + file);
    }

    private native boolean nativeSaveToFile(String fileName);

    /**
     * Attempts to save the image to a file.
     *
     * @param file the file to write.
     * @throws IOException in case an I/O error occurs.
     */
    public void saveToFile(@NotNull File file) throws IOException {
        if (!nativeSaveToFile(file.getAbsolutePath()))
            throw new IOException("Failed to save image to file: " + file);
    }

    /**
     * Gets the size of the image.
     *
     * @return the size of the image.
     */
    public native Vector2i getSize();

    private native void nativeCreateMaskFromColor(Color color, int alpha);

    /**
     * Creates a transparency mask from the given color.
     *
     * @param color the color to be made transparent.
     * @param alpha the alpha value to assign to pixels matching the color key.
     */
    public void createMaskFromColor(@NotNull Color color, int alpha) {
        nativeCreateMaskFromColor(Objects.requireNonNull(color), alpha);
    }

    /**
     * Creates a transparency mask from the given color, making matching pixels fully transparent.
     *
     * @param color the color to be made transparent.
     */
    public final void createMaskFromColor(Color color) {
        createMaskFromColor(color, 0);
    }

    private native void nativeCopy(Image source, int destX, int destY, IntRect sourceRect, boolean applyAlpha);

    /**
     * Copies a portion of another image onto this image.
     *
     * @param source     the source image.
     * @param destX      the destination X coordinate.
     * @param destY      the destination X coordinate.
     * @param sourceRect the source rectangle. An empty rectangle means the full image.
     * @param applyAlpha {@code true} to copy alpha values as well, {@code false} to leave the destination alpha.
     */
    public void copy(@NotNull Image source, int destX, int destY, @NotNull IntRect sourceRect, boolean applyAlpha) {
        nativeCopy(
                Objects.requireNonNull(source),
                destX, destY,
                Objects.requireNonNull(sourceRect),
                applyAlpha);
    }

    /**
     * Copies a portion of another image onto this image.
     *
     * @param source     the source image.
     * @param destX      the destination X coordinate.
     * @param destY      the destination X coordinate.
     * @param sourceRect the source rectangle. An empty rectangle means the full image.
     */
    public final void copy(Image source, int destX, int destY, IntRect sourceRect) {
        copy(source, destX, destY, sourceRect, false);
    }

    /**
     * Copies another image onto this image.
     *
     * @param source the source image.
     * @param destX  the destination X coordinate.
     * @param destY  the destination X coordinate.
     */
    public final void copy(Image source, int destX, int destY) {
        copy(source, destX, destY, IntRect.EMPTY, false);
    }

    private native void nativeSetPixel(int x, int y, Color color);

    /**
     * Sets the color of a certain pixel.
     *
     * @param x     the pixel's X coordinate.
     * @param y     the pixel's Y coordinate.
     * @param color the color to apply to the pixel.
     */
    public void setPixel(int x, int y, @NotNull Color color) {
        nativeSetPixel(x, y, Objects.requireNonNull(color));
    }

    /**
     * Gets the color of a certain pixel.
     *
     * @param x the pixel's X coordinate.
     * @param y the pixel's Y coordinate.
     * @return the pixel's color.
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
