package org.jsfml.graphics;

import org.jsfml.internal.Const;

/**
 * Interface for read-only fonts.
 * <p/>
 * It provides methods to can gain information from a font, but none to modify it
 * in any way.
 * <p/>
 * Note that this interface is expected to be implemented by a {@link Font}.
 * It is not recommended to be implemented outside of the JSFML API.
 *
 * @see org.jsfml.internal.Const
 */
public interface ConstFont extends Const {
    /**
     * Gets a glyph information structure from the font.
     *
     * @param unicode       the unicode (UTF-32) of the character to retrieve the glyph for.
     * @param characterSize the character size in question.
     * @param bold          {@code true} if the bold glyph version should be returned,
     *                      {@code false} for the regular version.
     * @return the {@link Glyph} representing the given unicode character.
     */
    public Glyph getGlyph(int unicode, int characterSize, boolean bold);

    /**
     * Gets the kerning offset between two glyphs.
     *
     * @param first         the unicode (UTF-32) of the first character.
     * @param second        the unicode (UTF-32) of the second character.
     * @param characterSize the character size in question.
     * @return the kerning offset between the two glyphs.
     */
    public int getKerning(int first, int second, int characterSize);

    /**
     * Gets the line spacing of the font.
     *
     * @param characterSize the character size in question.
     * @return the line spacing of the font.
     */
    public int getLineSpacing(int characterSize);

    /**
     * Retrieves the texture containing the font's glyphs.
     * <p/>
     * The texture returned is immutable.
     *
     * @param characterSize the character size in question.
     * @return the texture containing the font's glyphs of the character given size.
     */
    public ConstTexture getTexture(int characterSize);
}
