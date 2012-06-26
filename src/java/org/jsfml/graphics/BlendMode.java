package org.jsfml.graphics;

/**
 * Enumeration of supported blending modes for drawing.
 */
public enum BlendMode {
    /**
     * <tt>Pixel = Source * Source.a + Dest * (1 - Source.a)</tt>
     */
    ALPHA,

    /**
     * <tt>Pixel = Source + Dest</tt>
     */
    ADD,

    /**
     * <tt>Pixel = Source * Dest</tt>
     */
    MULTIPLY,

    /**
     * <tt>Pixel = Source</tt>
     */
    NONE
}
