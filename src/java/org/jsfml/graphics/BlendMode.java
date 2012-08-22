package org.jsfml.graphics;

/**
 * Enumeration of supported blending modes for drawing.
 */
public enum BlendMode {
    /**
     * Blends source and destrination colors by using a common alpha blending formula.
     * <p/>
     * <tt>Pixel = Source * Source.a + Dest * (1 - Source.a)</tt>
     */
    ALPHA,

    /**
     * Blends source and destination colors by performing a component-wise addition.
     * <p/>
     * <tt>Pixel = Source + Dest</tt>
     */
    ADD,

    /**
     * Blends source and destination colors by performing a component-wise multiplication.
     * <p/>
     * <tt>Pixel = Source * Dest</tt>
     */
    MULTIPLY,

    /**
     * Does not blend and overrides the destination color with the source color.
     * <p/>
     * <tt>Pixel = Source</tt>
     */
    NONE
}
