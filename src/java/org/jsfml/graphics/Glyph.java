package org.jsfml.graphics;

import org.jsfml.Intercom;

/**
 * Class describing a glyph of a {@link Font}.
 *
 * @see Font
 */
@Intercom
public class Glyph {
    @Intercom
    private int advance;

    @Intercom
    private IntRect bounds;

    @Intercom
    private IntRect textureRect;

    @Intercom
    private Glyph(int advance, IntRect bounds, IntRect textureRect) {
        this.advance = advance;
        this.bounds = bounds;
        this.textureRect = textureRect;
    }

    /**
     * Gets the offset to move horizontally to the next character.
     *
     * @return The offset to move horizontally to the next character.
     */
    public int getAdvance() {
        return advance;
    }

    /**
     * Gets the bounding rectangle of the glyph.
     *
     * @return The bounding rectangle of the glyph, in coordinates relative to the baseline.
     */
    public IntRect getBounds() {
        return bounds;
    }

    /**
     * Gets the texture coordinates of the glyph on the font's texture.
     *
     * @return The texture coordinates of the glyph on the font's texture.
     */
    public IntRect getTextureRect() {
        return textureRect;
    }
}
