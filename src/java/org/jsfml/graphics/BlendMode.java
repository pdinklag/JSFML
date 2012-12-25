package org.jsfml.graphics;

/**
 * Enumeration of the supported blending modes for drawing.
 */
public enum BlendMode {
    /**
     * Blends source and destrination colors by using a common alpha blending formula.
     * <p/>
     * {@code Pixel = Source * Source.a + Dest * (1 - Source.a)}
     */
    ALPHA,

    /**
     * Blends source and destination colors by performing a component-wise addition.
     * <p/>
     * {@code Pixel = Source + Dest}
     */
    ADD,

    /**
     * Blends source and destination colors by performing a component-wise multiplication.
     * <p/>
     * {@code Pixel = Source * Dest}
     */
    MULTIPLY,

    /**
     * Does not blend and overrides the destination color with the source color.
     * <p/>
     * {@code Pixel = Source}
     */
    NONE
}
