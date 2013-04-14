package org.jsfml.graphics;

import org.jsfml.internal.*;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Context;
import org.jsfml.window.Window;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Represents a 2D texture stored on the graphics card for rendering.
 */
public class Texture extends SFMLNativeObject implements ConstTexture {
    private static final int maximumSize;

    static {
        SFMLNative.loadNativeLibraries();
        maximumSize = nativeGetMaximumSize();
    }

    private static native int nativeGetMaximumSize();

    /**
     * Gets the maximum texture size supported by the current hardware.
     *
     * @return the maximum texture size supported by the current hardware.
     */
    public static int getMaximumSize() {
        return maximumSize;
    }

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

    private static native void nativeBind(Texture texture, int coordinateType);

    /**
     * Activates a texture for rendering with the specified coordinate type.
     * <p/>
     * This is required only if you wish to use JSFML textures in custom OpenGL code.
     *
     * @param texture        the texture to bind, or {@code null} to indicate that
     *                       no texture is to be used..
     * @param coordinateType the coordinate type to use.
     */
    public static void bind(ConstTexture texture, CoordinateType coordinateType) {
        nativeBind((Texture) texture, coordinateType.ordinal());
    }

    /**
     * Activates a texture for rendering, using the
     * {@link Texture.CoordinateType#NORMALIZED} coordinate type.
     */
    public static void bind(ConstTexture texture) {
        bind(texture, CoordinateType.NORMALIZED);
    }

    //cache
    private Vector2i size = Vector2i.ZERO;
    private boolean smooth = false;
    private boolean repeated = false;

    /**
     * Constructs a new texture.
     */
    public Texture() {
        super();
    }

    @SuppressWarnings("deprecation")
    Texture(long wrap) {
        super(wrap);
        updateSize();
        smooth = nativeIsSmooth();
        repeated = nativeIsRepeated();
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
        updateSize();
        smooth = nativeIsSmooth();
        repeated = nativeIsRepeated();
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
        if (!nativeCreate(width, height)) {
            throw new TextureCreationException("Failed to create texture.");
        }

        updateSize();
    }

    private native boolean nativeLoadFromStream(
            SFMLInputStream.NativeStreamRef stm, Buffer area);

    /**
     * Fully loads all available bytes from an {@link InputStream}
     * and attempts to load the texture portion from it.
     *
     * @param in   the input stream to read from.
     * @param area the area of the image to load into the texture.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromStream(InputStream in, IntRect area) throws IOException {
        Context.getContext();

        final SFMLInputStream.NativeStreamRef streamRef =
                new SFMLInputStream.NativeStreamRef();

        streamRef.initialize(new SFMLInputStream(Objects.requireNonNull(in)));

        SFMLErrorCapture.start();
        final boolean success = nativeLoadFromStream(
                streamRef, IntercomHelper.encodeIntRect(area));

        final String msg = SFMLErrorCapture.finish();

        if (!success) {
            throw new IOException(msg);
        }

        updateSize();
    }

    /**
     * Fully loads all available bytes from an {@link InputStream}
     * and attempts to load the texture from it.
     *
     * @param in the input stream to read from.
     * @throws IOException in case an I/O error occurs.
     */
    public final void loadFromStream(InputStream in) throws IOException {
        loadFromStream(in, IntRect.EMPTY);
    }

    private native boolean nativeLoadFromFile(String path, Buffer area);

    /**
     * Attempts to load the texture from a file.
     *
     * @param path the path to the file to load the texture from.
     * @param area the area of the image to load into the texture.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromFile(Path path, IntRect area) throws IOException {
        Context.getContext();

        SFMLErrorCapture.start();

        final boolean success = nativeLoadFromFile(
                path.toAbsolutePath().toString(),
                IntercomHelper.encodeIntRect(area));

        final String msg = SFMLErrorCapture.finish();

        if (!success) {
            throw new IOException(msg);
        }

        updateSize();
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param path the path to the file to load the texture from.
     * @throws IOException in case an I/O error occurs.
     */
    public final void loadFromFile(Path path) throws IOException {
        loadFromFile(path, IntRect.EMPTY);
    }

    private native boolean nativeLoadFromImage(Image image, Buffer area);

    /**
     * Attempts to load the texture from a source image portion.
     *
     * @param image the source image.
     * @param area  the area of the image to load into the texture.
     * @throws TextureCreationException if the texture could not be loaded from the image.
     */
    public void loadFromImage(Image image, IntRect area)
            throws TextureCreationException {

        image.commit();

        Context.getContext();

        if (!nativeLoadFromImage(image, IntercomHelper.encodeIntRect(area))) {
            throw new TextureCreationException("Failed to load texture from image.");
        }

        updateSize();
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

    private native long nativeGetSize();

    private void updateSize() {
        size = IntercomHelper.decodeVector2i(nativeGetSize());
    }

    /**
     * Gets the dimensions of the texture.
     *
     * @return the dimensions of the texture.
     */
    public Vector2i getSize() {
        return size;
    }

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
    public void update(Image image, int x, int y) {
        image.commit();
        nativeUpdate(image, x, y);
    }

    private native void nativeUpdate(Window window, int x, int y);

    /**
     * Updates a part of the texture from the contents of a window.
     *
     * @param window the window to update from.
     * @param x      the X offset inside the texture.
     * @param y      the Y offset inside the texture.
     */
    public void update(Window window, int x, int y) {
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

    private native void nativeSetSmooth(boolean smooth);

    /**
     * Enables or disables the smooth filter.
     * <p/>
     * The smooth filter is disabled by default.
     *
     * @param smooth {@code true} to enable, {@code false} to disable.
     */
    public void setSmooth(boolean smooth) {
        nativeSetSmooth(smooth);
        this.smooth = smooth;
    }

    private native boolean nativeIsSmooth();

    @Override
    public boolean isSmooth() {
        return smooth;
    }

    private native void nativeSetRepeated(boolean repeated);

    /**
     * Enables or disables texture repeating.
     * <p/>
     * Texture repeating is disabled by default.
     *
     * @param repeated {@code true} to enable, {@code false} to disable.
     */
    public void setRepeated(boolean repeated) {
        nativeSetRepeated(repeated);
        this.repeated = repeated;
    }

    private native boolean nativeIsRepeated();

    @Override
    public boolean isRepeated() {
        return repeated;
    }
}
