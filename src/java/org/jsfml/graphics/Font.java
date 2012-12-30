package org.jsfml.graphics;

import org.jsfml.NativeRef;
import org.jsfml.SFMLNativeObject;
import org.jsfml.StreamUtil;
import org.jsfml.UnsafeOperations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Holds a character font for use in text displays.
 */
public class Font extends SFMLNativeObject implements ConstFont {
    private final Map<Integer, Texture> textureMap = new TreeMap<>();

    /**
     * Memory reference and heap pointer that keeps alive the data input stream for freetype.
     */
    private final NativeRef<byte[]> memoryRef = new NativeRef<byte[]>() {
        @Override
        protected long nativeInitialize(byte[] ref) {
            return nativeLoadFromMemory(ref);
        }

        @Override
        protected void nativeRelease(byte[] ref, long ptr) {
            nativeReleaseMemory(ref, ptr);
        }
    };

    /**
     * Constructs a new font.
     */
    public Font() {
        super();
    }

    /**
     * Constructs a new font by copying another font.
     *
     * @param other the font to copy.
     */
    @SuppressWarnings("deprecation")
    public Font(ConstFont other) {
        super(((Font) other).nativeCopy());
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

    private native long nativeLoadFromMemory(byte[] memory);

    private native void nativeReleaseMemory(byte[] memory, long ptr);

    /**
     * Fully loads all available bytes from an {@link java.io.InputStream}
     * and attempts to load the texture from it.
     *
     * @param in the input stream to read from.
     * @throws java.io.IOException in case an I/O error occurs.
     */
    public void loadFromStream(InputStream in) throws IOException {
        memoryRef.initialize(StreamUtil.readStream(in));

        if (!memoryRef.hasNonZeroPointer()) {
            throw new IOException("Failed to load font from input stream.");
        }
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param file the file to load the texture from.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromFile(File file) throws IOException {
        memoryRef.initialize(StreamUtil.readFile(file));

        if (!memoryRef.hasNonZeroPointer()) {
            throw new IOException("Failed to load font from file: " + file);
        }
    }

    @Override
    public native Glyph getGlyph(int unicode, int characterSize, boolean bold);

    @Override
    public native int getKerning(int first, int second, int characterSize);

    @Override
    public native int getLineSpacing(int characterSize);

    private native long nativeGetTexture(int characterSize);

    @Override
    public ConstTexture getTexture(int characterSize) {
        Texture texture;
        if (textureMap.containsKey(characterSize)) {
            texture = textureMap.get(characterSize);
        } else {
            long p = nativeGetTexture(characterSize);
            if (p != 0) {
                texture = new Texture(p);
                textureMap.put(characterSize, texture);
            } else {
                texture = null;
            }
        }

        return texture;
    }
}
