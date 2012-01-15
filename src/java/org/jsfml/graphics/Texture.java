package org.jsfml.graphics;

import org.jsfml.SFMLNativeObject;
import org.jsfml.UnsafeOperations;
import org.jsfml.window.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Immutable 2D texture stored on the graphics card for rendering.
 */
public class Texture extends SFMLNativeObject {
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
    private Texture(long wrap) {
        super(wrap);
    }

    @SuppressWarnings("deprecation")
    public Texture(Texture other) {
        super(0);
        UnsafeOperations.setSFMLObjectPointer(this, other.nativeCopy());
        UnsafeOperations.manageSFMLObject(this, true);
    }

    @Override
    protected native long nativeCreate();

    private native long nativeCopy();

    /**
     * Generates an empty texture with the given dimensions.
     *
     * @param width  The texture's width.
     * @param height The texture's height.
     * @return <tt>true</tt> if the texture was successfully created, <tt>false</tt> otherwise.
     */
    public native boolean create(int width, int height);

    private native boolean loadFromMemory(byte[] memory, IntRect area);

    /**
     * Fully loads all available bytes from an {@link InputStream} and attempts to load the texture from it.
     *
     * @param in   The input stream to read from.
     * @param area The area of the image to load into the texture.
     * @return <tt>true</tt> if the texture was successfully loaded, <tt>false</tt> otherwise.
     * @throws IOException In case an I/O error occurs.
     */
    public boolean loadFromStream(InputStream in, IntRect area) throws IOException {
        if (area == null)
            throw new IllegalArgumentException("area must not be null.");

        int n = in.available();
        byte[] b = new byte[n];

        n = in.read(b);
        if (n != b.length)
            throw new IOException("Read error, expected " + b.length + ", got " + n + ".");

        return loadFromMemory(b, area);
    }

    /**
     * Fully loads all available bytes from an {@link InputStream} and attempts to load the texture from it.
     *
     * @param in The input stream to read from.
     * @return <tt>true</tt> if the texture was successfully loaded, <tt>false</tt> otherwise.
     * @throws IOException In case an I/O error occurs.
     */
    public boolean loadFromStream(InputStream in) throws IOException {
        return loadFromStream(in, new IntRect());
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param file The file to load the texture from.
     * @param area The area of the image to load into the texture.
     * @return <tt>true</tt> if the texture was successfully loaded, <tt>false</tt> otherwise.
     * @throws IOException In case an I/O error occurs.
     */
    public boolean loadFromFile(File file, IntRect area) throws IOException {
        if (area == null)
            throw new IllegalArgumentException("area must not be null.");

        InputStream in = null;

        boolean result = false;
        IOException ioException = null;

        try {
            in = new FileInputStream(file);
            result = loadFromStream(in, area);
        } catch (IOException ex) {
            ioException = ex;
        } finally {
            if (in != null)
                in.close();
        }

        if (ioException != null)
            throw ioException;

        return result;
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param file The file to load the texture from.
     * @return <tt>true</tt> if the texture was successfully loaded, <tt>false</tt> otherwise.
     * @throws IOException In case an I/O error occurs.
     */
    public boolean loadFromFile(File file) throws IOException {
        return loadFromFile(file, new IntRect());
    }

    //TODO public native boolean loadFromImage(Image image, IntRect area);

    /**
     * Gets the width of the texture.
     *
     * @return The width of the texture.
     */
    public native int getWidth();

    /**
     * Gets the height of the texture.
     *
     * @return The width of the texture.
     */
    public native int getHeight();

    //TODO public native Image copyToImage();

    //TODO public native void update(Image image, int x, int y);

    private native void nativeUpdate(Window window, int x, int y);

    /**
     * Updates a part of the texture from the contents of a window.
     *
     * @param window The window to update from.
     * @param x      The X offset inside the texture.
     * @param y      The Y offset inside the texture.
     */
    public void update(Window window, int x, int y) {
        if (window == null)
            throw new IllegalArgumentException("window must not be null.");

        nativeUpdate(window, x, y);
    }

    /**
     * Updates the texture from the contents of a window.
     *
     * @param window The window to update from.
     */
    public void update(Window window) {
        update(window, 0, 0);
    }

    private native void nativeBind(CoordinateType type);

    /**
     * Activates the texture for rendering.
     *
     * @param coordinateType The coordinate type to use.
     */
    public void bind(CoordinateType coordinateType) {
        if (coordinateType == null)
            throw new IllegalArgumentException("coordinateType must not be null.");

        nativeBind(coordinateType);
    }

    /**
     * Activates the texture for rendering.
     */
    public void bind() {
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

    /**
     * Checks whether the smooth filter is enabled.
     *
     * @return <tt>true</tt> if enabled, <tt>false</tt> if disabled.
     */
    public native boolean isSmooth();

    /**
     * Enables or disables texture repeating.
     * <p/>
     * Texture repeating is disabled by default.
     *
     * @param repeated <tt>true</tt> to enable, <tt>false</tt> to disable.
     */
    public native void setRepeated(boolean repeated);

    /**
     * Checks whether texture repeating is enabled.
     *
     * @return <tt>true</tt> if enabled, <tt>false</tt> if disabled.
     */
    public native boolean isRepeated();
}
