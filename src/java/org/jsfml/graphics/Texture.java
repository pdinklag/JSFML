package org.jsfml.graphics;

import org.jsfml.internal.*;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Window;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Represents a 2D texture stored on the graphics card for rendering.
 */
public class Texture extends SFMLNativeObject implements ConstTexture {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Gets the maximum texture size supported by the current hardware.
     *
     * @return the maximum texture size supported by the current hardware.
     */
    public static native int getMaximumSize();

    /**
     * Enumeation of texture coordinate types that can be used for rendering.
     */
    public static enum CoordinateType {
        /**
         * Normalized OpenGL coordinates, ranging from 0 to 1.
         */
        NORMALIZED,

        /**
         * Pixel coordinates, ranging from 0 to the respective dimension (width or height).
         */
        PIXELS
    }

    /**
     * Constructs a new texture.
     */
    public Texture() {
        super();
    }

    @SuppressWarnings("deprecation")
    Texture(long wrap) {
        super(wrap);
    }

    /**
     * Constructs a new texture by copying another texture.
     *
     * @param other The texture to copy.
     */
    @SuppressWarnings("deprecation")
    public Texture(ConstTexture other) {
        super(((Texture) other).nativeCopy());
        UnsafeOperations.manageSFMLObject(this, true);
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

    private native long nativeCopy();

    private native boolean nativeCreate(int width, int height);

    /**
     * Generates an empty texture with the specified dimensions.
     *
     * @param width  the texture's width.
     * @param height the texture's height.
     * @throws TextureCreationException if the texture could not be created.
     */
    public void create(int width, int height) throws TextureCreationException {
        if (!nativeCreate(width, height))
            throw new TextureCreationException("Failed to create texture.");
    }

    private native boolean nativeLoadFromMemory(byte[] memory, IntRect area);

    /**
     * Fully loads all available bytes from an {@link InputStream}
     * and attempts to load the texture portion from it.
     *
     * @param in   the input stream to read from.
     * @param area the area of the image to load into the texture.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromStream(InputStream in, @NotNull IntRect area) throws IOException {
        if (!nativeLoadFromMemory(StreamUtil.readStream(in), Objects.requireNonNull(area)))
            throw new IOException("Failed to load texture from stream.");
    }

    /**
     * Fully loads all available bytes from an {@link InputStream}
     * and attempts to load the texture from it.
     *
     * @param in the input stream to read from.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromStream(InputStream in) throws IOException {
        loadFromStream(in, IntRect.EMPTY);
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param file the file to load the texture from.
     * @param area the area of the image to load into the texture.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromFile(File file, @NotNull IntRect area) throws IOException {
        if (!nativeLoadFromMemory(StreamUtil.readFile(file), Objects.requireNonNull(area)))
            throw new IOException("Failed to load texture from file: " + file);
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param file the file to load the texture from.
     * @throws IOException in case an I/O error occurs.
     */
    public final void loadFromFile(File file) throws IOException {
        loadFromFile(file, IntRect.EMPTY);
    }

    private native boolean nativeLoadFromImage(Image image, IntRect area);

    /**
     * Attempts to load the texture from a source image portion.
     *
     * @param image the source image.
     * @param area  the area of the image to load into the texture.
     * @throws TextureCreationException if the texture could not be loaded from the image.
     */
    public void loadFromImage(@NotNull Image image, @NotNull IntRect area)
            throws TextureCreationException {
        if (!nativeLoadFromImage(Objects.requireNonNull(image), Objects.requireNonNull(area)))
            throw new TextureCreationException("Failed to load texture from image.");
    }

    /**
     * Attempts to load the texture from a source image.
     *
     * @param image the source image.
     * @throws TextureCreationException if the texture could not be loaded from the image.
     */
    public final void loadFromImage(Image image) throws TextureCreationException {
        loadFromImage(image, IntRect.EMPTY);
    }

    /**
     * Gets the dimensions of the texture.
     *
     * @return the dimensions of the texture.
     */
    public native Vector2i getSize();

    private native long nativeCopyToImage();

    @Override
    public Image copyToImage() {
        Image image = new Image(nativeCopyToImage());
        UnsafeOperations.manageSFMLObject(image, true);

        return image;
    }

    private native void nativeUpdate(Image image, int x, int y);

    /**
     * Updates a part of the texture from an image.
     *
     * @param image the image to update from.
     * @param x     the X offset inside the texture.
     * @param y     the Y offset inside the texture.
     */
    public void update(@NotNull Image image, int x, int y) {
        nativeUpdate(Objects.requireNonNull(image), x, y);
    }

    private native void nativeUpdate(Window window, int x, int y);

    /**
     * Updates a part of the texture from the contents of a window.
     *
     * @param window the window to update from.
     * @param x      the X offset inside the texture.
     * @param y      the Y offset inside the texture.
     */
    public void update(@NotNull Window window, int x, int y) {
        nativeUpdate(Objects.requireNonNull(window), x, y);
    }

    /**
     * Updates the texture from the contents of a window.
     *
     * @param window the window to update from.
     */
    public final void update(Window window) {
        update(window, 0, 0);
    }

    private native void nativeBind(CoordinateType type);

    @Override
    public void bind(@NotNull CoordinateType coordinateType) {
        nativeBind(Objects.requireNonNull(coordinateType));
    }

    @Override
    public final void bind() {
        bind(CoordinateType.NORMALIZED);
    }

    /**
     * Enables or disables the smooth filter.
     * <p/>
     * The smooth filter is disabled by default.
     *
     * @param smooth {@code true} to enable, {@code false} to disable.
     */
    public native void setSmooth(boolean smooth);

    @Override
    public native boolean isSmooth();

    /**
     * Enables or disables texture repeating.
     * <p/>
     * Texture repeating is disabled by default.
     *
     * @param repeated {@code true} to enable, {@code false} to disable.
     */
    public native void setRepeated(boolean repeated);

    @Override
    public native boolean isRepeated();
}
