package org.jsfml.graphics;

/**
 * Describes a glyph in a {@link Font}.
 *
 * @see Font
 */
public final class Glyph {
    /**
     * The offset to move horizontally to the next character.
     */
    public final int advance;

    /**
     * The bounding rectangle of the glyph, in coordinates relative to the baseline.
     */
    public final IntRect bounds;

    /**
     * The texture coordinates of the glyph on the font's texture.
     */
    public final IntRect textureRect;

    /**
     * Constructs a glyph with the specified parameters.
     * <p/>
     * Note that this constructor is reserved for internal use and should
     * never be required to be used directly. Glyphs should be obtained
     * using the {@link Font#getGlyph(int, int, boolean)} method.
     *
     * @param advance     the offset to move horizontally to the next character.
     * @param bounds      the boundaries of the glyph.
     * @param textureRect the texture rectangle used by the glyph.
     * @see Font#getGlyph(int, int, boolean)
     */
    public Glyph(int advance, IntRect bounds, IntRect textureRect) {
        this.advance = advance;
        this.bounds = bounds;
        this.textureRect = textureRect;
    }
}
