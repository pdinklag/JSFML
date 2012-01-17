package org.jsfml.graphics;

import org.jsfml.SFMLNativeObject;
import org.jsfml.StreamUtil;
import org.jsfml.UnsafeOperations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Class for loading character fonts.
 */
public class Font extends SFMLNativeObject {
    private static ImmutableFont defaultFont = null;

    private static native long nativeGetDefaultFont();

    /**
     * Gets the in-built default font.
     *
     * @return The in-built default font.
     */
    public static Font getDefaultFont() {
        if (defaultFont == null)
            defaultFont = new ImmutableFont(nativeGetDefaultFont());

        return defaultFont;
    }

    private final HashMap<Integer, ImmutableTexture> textureMap = new HashMap<Integer, ImmutableTexture>();

    /**
     * Creates a font.
     */
    public Font() {
        super();
    }

    @SuppressWarnings("deprecation")
    Font(long wrap) {
        super(wrap);
    }

    /**
     * Creates a font from another font.
     *
     * @param other The font to copy.
     */
    @SuppressWarnings("deprecation")
    public Font(Font other) {
        super(other.nativeCopy());
        UnsafeOperations.manageSFMLObject(this, true);
    }

    @Override
    protected native long nativeCreate();

    private native long nativeCopy();

    private native boolean nativeLoadFromMemory(byte[] memory);

    /**
     * Fully loads all available bytes from an {@link java.io.InputStream} and attempts to load the texture from it.
     *
     * @param in The input stream to read from.
     * @throws java.io.IOException In case an I/O error occurs.
     */
    public void loadFromStream(InputStream in) throws IOException {
        if (!nativeLoadFromMemory(StreamUtil.readStream(in)))
            throw new IOException("Failed to load font from input stream.");
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param file The file to load the texture from.
     * @throws IOException In case an I/O error occurs.
     */
    public void loadFromFile(File file) throws IOException {
        if (!nativeLoadFromMemory(StreamUtil.readFile(file)))
            throw new IOException("Failed to load font from file: " + file);
    }

    /**
     * Gets a glyph information structure from the font.
     *
     * @param unicode       The unicode (UTF-32) of the character to retrieve the glyph for.
     * @param characterSize The character size in question.
     * @param bold          <tt>true</tt> if the bold glyph version should be returned,
     *                      <tt>false</tt> for the regular version.
     * @return The {@link Glyph} representing the given unicode character.
     */
    public native Glyph getGlyph(int unicode, int characterSize, boolean bold);

    /**
     * Gets the kerning offset between two glyphs.
     *
     * @param first         The unicode (UTF-32) of the first character.
     * @param second        The unicode (UTF-32) of the second character.
     * @param characterSize The character size in question.
     * @return The kerning offset between two glyphs.
     */
    public native int getKerning(int first, int second, int characterSize);

    /**
     * Gets the line spacing of the font.
     *
     * @param characterSize The character size in question.
     * @return The line spacing of the font.
     */
    public native int getLineSpacing(int characterSize);

    private native long nativeGetTexture(int characterSize);

    /**
     * Retrieves the texture containing the font's glyphs.
     *
     * @param characterSize The character size in question.
     * @return The texture containing the font's glyphs of the character given size.
     */
    public Texture getTexture(int characterSize) {
        ImmutableTexture texture;
        if (textureMap.containsKey(characterSize)) {
            texture = textureMap.get(characterSize);
        } else {
            long p = nativeGetTexture(characterSize);
            if (p != 0) {
                texture = new ImmutableTexture(p);
                textureMap.put(characterSize, texture);
            } else {
                texture = null;
            }
        }

        return texture;
    }
}
