package org.jsfml.graphics;

import java.io.Serializable;

/**
 * Defines a blending mode for drawing.
 */
public final class BlendMode implements Serializable {
    private static final long serialVersionUID = -384111167897124988L;

    /**
     * Blends source and destination according to destination alpha.
     */
    public static final BlendMode ALPHA = new BlendMode(
            Factor.SRC_ALPHA, Factor.ONE_MINUS_SRC_ALPHA, Equation.ADD,
            Factor.ONE, Factor.ONE_MINUS_SRC_ALPHA, Equation.ADD);

    /**
     * Adds source to destination.
     */
    public static final BlendMode ADD = new BlendMode(
            Factor.SRC_ALPHA, Factor.ONE, Equation.ADD,
            Factor.ONE, Factor.ONE, Equation.ADD);

    /**
     * Multiplies source and destination.
     */
    public static final BlendMode MULTIPLY = new BlendMode(Factor.DST_COLOR, Factor.ZERO);

    /**
     * Overwrites destination with source.
     */
    public static final BlendMode NONE = new BlendMode(Factor.ONE, Factor.ZERO);

    /**
     * Enumeration of blending factors.
     * <p/>
     * These factors are mapped directly to their OpenGL equivalents,
     * specified by {@code glBlendFunc}.
     */
    public enum Factor {
        /**
         * {@code (0, 0, 0, 0)}
         */
        ZERO,

        /**
         * {@code (1, 1, 1, 1)}
         */
        ONE,

        /**
         * {@code (src.r, src.g, src.b, src.a)}
         */
        SRC_COLOR,

        /**
         * {@code (1, 1, 1, 1) - (src.r, src.g, src.b, src.a)}
         */
        ONE_MINUS_SRC_COLOR,

        /**
         * {@code (dst.r, dst.g, dst.b, dst.a)}
         */
        DST_COLOR,

        /**
         * {@code (1, 1, 1, 1) - (dst.r, dst.g, dst.b, dst.a)}
         */
        ONE_MINUS_DST_COLOR,

        /**
         * {@code (src.a, src.a, src.a, src.a)}
         */
        SRC_ALPHA,

        /**
         * {@code (1, 1, 1, 1) - (src.a, src.a, src.a, src.a)}
         */
        ONE_MINUS_SRC_ALPHA,

        /**
         * {@code (dst.a, dst.a, dst.a, dst.a)}
         */
        DST_ALPHA,

        /**
         * {@code (1, 1, 1, 1) - (dst.a, dst.a, dst.a, dst.a)}
         */
        ONE_MINUS_DST_ALPHA
    }

    /**
     * Enumeration of blending equations.
     * <p/>
     * These factors are mapped directly to their OpenGL equivalents,
     * specified by {@code glBlendEquation}.
     */
    public enum Equation {
        /**
         * {@code Pixel = Src * SrcFactor + Dst * DstFactor}
         */
        ADD,

        /**
         * {@code Pixel = Src * SrcFactor - Dst * DstFactor}
         */
        SUBTRACT
    }

    /**
     * The source blending factor for the color channels.
     */
    public final Factor colorSrcFactor;

    /**
     * The destination blending factor for the color channels.
     */
    public final Factor colorDstFactor;

    /**
     * The blending equation for the color channels.
     */
    public final Equation colorEquation;

    /**
     * The source blending factor for the alpha channel.
     */
    public final Factor alphaSrcFactor;

    /**
     * The destination blending factor for the alpha channel.
     */
    public final Factor alphaDstFactor;

    /**
     * The blending equation for the alpha channel.
     */
    public final Equation alphaEquation;

    /**
     * Constructs a new blending mode that does alpha blending.
     */
    public BlendMode() {
        this(Factor.SRC_ALPHA,
                Factor.ONE_MINUS_SRC_ALPHA,
                Equation.ADD,
                Factor.ONE,
                Factor.ONE_MINUS_SRC_ALPHA,
                Equation.ADD);
    }

    /**
     * Constructs a new blending mode with the specified parameters for both the color and alpha channel,
     * using the {@link Equation#ADD} blending equation.
     *
     * @param srcFactor the source blending factor for the color and alpha channels.
     * @param dstFactor the destination blending factor for the color and alpha channels.
     */
    public BlendMode(Factor srcFactor, Factor dstFactor) {
        this(srcFactor, dstFactor, Equation.ADD, srcFactor, dstFactor, Equation.ADD);
    }

    /**
     * Constructs a new blending mode with the specified parameters for both the color and alpha channel.
     *
     * @param srcFactor     the source blending factor for the color and alpha channels.
     * @param dstFactor     the destination blending factor for the color and alpha channels.
     * @param blendEquation the blending equation for the color and alpha channels.
     */
    public BlendMode(Factor srcFactor, Factor dstFactor, Equation blendEquation) {
        this(srcFactor, dstFactor, blendEquation, srcFactor, dstFactor, blendEquation);
    }

    /**
     * Constructs a new blending mode with the specified parameters.
     *
     * @param colorSrcFactor the source blending factor for the color channels.
     * @param colorDstFactor the destination blending factor for the color channels.
     * @param colorEquation  the blending equation for the color channels
     * @param alphaSrcFactor the source blending factor for the alpha channel.
     * @param alphaDstFactor the destination blending factor for the alpha channel.
     * @param alphaEquation  the blending equation for the alpha channel.
     */
    public BlendMode(
            Factor colorSrcFactor, Factor colorDstFactor, Equation colorEquation,
            Factor alphaSrcFactor, Factor alphaDstFactor, Equation alphaEquation) {

        this.colorSrcFactor = colorSrcFactor;
        this.colorDstFactor = colorDstFactor;
        this.colorEquation = colorEquation;
        this.alphaSrcFactor = alphaSrcFactor;
        this.alphaDstFactor = alphaDstFactor;
        this.alphaEquation = alphaEquation;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BlendMode) {
            final BlendMode b = (BlendMode) o;
            return (colorSrcFactor == b.colorSrcFactor) &&
                    (colorDstFactor == b.colorDstFactor) &&
                    (colorEquation == b.colorEquation) &&
                    (alphaSrcFactor == b.alphaSrcFactor) &&
                    (alphaDstFactor == b.alphaDstFactor) &&
                    (alphaEquation == b.alphaEquation);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = colorSrcFactor.hashCode();
        result = 31 * result + colorDstFactor.hashCode();
        result = 31 * result + colorEquation.hashCode();
        result = 31 * result + alphaSrcFactor.hashCode();
        result = 31 * result + alphaDstFactor.hashCode();
        result = 31 * result + alphaEquation.hashCode();
        return result;
    }
}
