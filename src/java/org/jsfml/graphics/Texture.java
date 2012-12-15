package org.jsfml.graphics;

import org.jsfml.*;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Window;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Immutable 2D texture stored on the graphics card for rendering.
 */
public class Texture extends SFMLNativeObject implements ConstTexture {
	static {
		SFMLNative.loadNativeLibraries();
	}

    /**
     * Gets the maximum texture size supported by the current hardware.
     *
     * @return The maximum texture size supported by the current hardware.
     */
    public static native int getMaximumSize();

    /**
     * Types of texture coordinates that can be used for rendering.
     */
    public static enum CoordinateType {
        /**
         * Normalized OpenGL coordinates ranging from 0 to 1.
         */
        NORMALIZED,

        /**
         * Pixel coordinates ranging from 0 to the respective dimension (width or height).
         */
        PIXELS
    }

    /**
     * Creates a texture.
     */
    public Texture() {
        super();
    }

    @SuppressWarnings("deprecation")
    Texture(long wrap) {
        super(wrap);
    }

    /**
     * Creates a texture from another texture.
     *
     * @param other The texture to copy.
     */
    @SuppressWarnings("deprecation")
    public Texture(ConstTexture other) {
        super(((Texture) other).nativeCopy());
        UnsafeOperations.manageSFMLObject(this, true);
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected void nativeSetExPtr() {
    }

    @Override
    protected native void nativeDelete();

    private native long nativeCopy();

    /**
     * Generates an empty texture with the given dimensions.
     *
     * @param width  The texture's width.
     * @param height The texture's height.
     * @return <tt>true</tt> if the texture was successfully created, <tt>false</tt> otherwise.
     */
    public native boolean create(int width, int height);

    private native boolean nativeLoadFromMemory(byte[] memory, IntRect area);

    /**
     * Fully loads all available bytes from an {@link InputStream} and attempts to load the texture from it.
     *
     * @param in   The input stream to read from.
     * @param area The area of the image to load into the texture.
     * @throws IOException In case an I/O error occurs.
     */
    public void loadFromStream(InputStream in, @NotNull IntRect area) throws IOException {
        if (area == null)
            throw new NullPointerException("area must not be null.");

        if (!nativeLoadFromMemory(StreamUtil.readStream(in), area))
            throw new IOException("Failed to load texture from stream.");
    }

    /**
     * Fully loads all available bytes from an {@link InputStream} and attempts to load the texture from it.
     *
     * @param in The input stream to read from.
     * @throws IOException In case an I/O error occurs.
     */
    public void loadFromStream(InputStream in) throws IOException {
        loadFromStream(in, new IntRect());
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param file The file to load the texture from.
     * @param area The area of the image to load into the texture.
     * @throws IOException In case an I/O error occurs.
     */
    public void loadFromFile(File file, @NotNull IntRect area) throws IOException {
        if (area == null)
            throw new NullPointerException("area must not be null.");

        if (!nativeLoadFromMemory(StreamUtil.readFile(file), area))
            throw new IOException("Failed to load texture from file: " + file);
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param file The file to load the texture from.
     * @throws IOException In case an I/O error occurs.
     */
    public final void loadFromFile(File file) throws IOException {
        loadFromFile(file, new IntRect());
    }

    private native boolean nativeLoadFromImage(Image image, IntRect area);

    /**
     * Attempts to load the texture from a source image.
     *
     * @param image The source image.
     * @param area  The area of the image to load into the texture.
     * @return <tt>true</tt> if the texture was successfully loaded, <tt>false</tt> otherwise.
     */
    public boolean loadFromImage(@NotNull Image image, @NotNull IntRect area) {
        if (image == null)
            throw new NullPointerException("image must not be null.");

        if (area == null)
            throw new NullPointerException("area must not be null.");

        return nativeLoadFromImage(image, area);
    }

    /**
     * Attempts to load the texture from a source image.
     *
     * @param image The source image.
     * @return <tt>true</tt> if the texture was successfully loaded, <tt>false</tt> otherwise.
     */
    public final boolean loadFromImage(Image image) {
        return loadFromImage(image, new IntRect());
    }

    /**
     * Gets the dimensions of the texture.
     *
     * @return The dimensions of the texture.
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
     * @param image The image to update from.
     * @param x     The X offset inside the texture.
     * @param y     The Y offset inside the texture.
     */
    public void update(@NotNull Image image, int x, int y) {
        if (image == null)
            throw new NullPointerException("image must not be null.");

        nativeUpdate(image, x, y);
    }

    private native void nativeUpdate(Window window, int x, int y);

    /**
     * Updates a part of the texture from the contents of a window.
     *
     * @param window The window to update from.
     * @param x      The X offset inside the texture.
     * @param y      The Y offset inside the texture.
     */
    public void update(@NotNull Window window, int x, int y) {
        if (window == null)
            throw new NullPointerException("window must not be null.");

        nativeUpdate(window, x, y);
    }

    /**
     * Updates the texture from the contents of a window.
     *
     * @param window The window to update from.
     */
    public final void update(Window window) {
        update(window, 0, 0);
    }

    private native void nativeBind(CoordinateType type);

    @Override
    public void bind(@NotNull CoordinateType coordinateType) {
        if (coordinateType == null)
            throw new NullPointerException("coordinateType must not be null.");

        nativeBind(coordinateType);
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
     * @param smooth <tt>true</tt> to enable, <tt>false</tt> to disable.
     */
    public native void setSmooth(boolean smooth);

    @Override
    public native boolean isSmooth();

    /**
     * Enables or disables texture repeating.
     * <p/>
     * Texture repeating is disabled by default.
     *
     * @param repeated <tt>true</tt> to enable, <tt>false</tt> to disable.
     */
    public native void setRepeated(boolean repeated);

    @Override
    public native boolean isRepeated();
}
