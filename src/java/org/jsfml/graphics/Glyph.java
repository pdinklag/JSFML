package org.jsfml.graphics;

import org.jsfml.internal.Intercom;

/**
 * Describes a glyph in a {@link Font}.
 *
 * @see Font
 */
@Intercom
public final class Glyph {
    /**
     * The offset to move horizontally to the next character.
     */
    @Intercom
    public final int advance;

    /**
     * The bounding rectangle of the glyph, in coordinates relative to the baseline.
     */
    @Intercom
    public final IntRect bounds;

    /**
     * The texture coordinates of the glyph on the font's texture.
     */
    @Intercom
    public final IntRect textureRect;

    @Intercom
    private Glyph(int advance, IntRect bounds, IntRect textureRect) {
        this.advance = advance;
        this.bounds = bounds;
        this.textureRect = textureRect;
    }
}
