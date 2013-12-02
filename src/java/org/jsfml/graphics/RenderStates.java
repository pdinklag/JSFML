package org.jsfml.graphics;

import java.util.Objects;

/**
 * Defines drawing properties when drawing objects to a {@link RenderTarget}.
 */
public final class RenderStates {
    /**
     * Pre-defined instance holding the default render states using
     * {@link BlendMode#ALPHA} blending, the identity transformation and no texture or shader.
     */
    public static final RenderStates DEFAULT =
            new RenderStates(BlendMode.ALPHA, Transform.IDENTITY, null, null);

    /**
     * The blending mode used for drawing.
     */
    public final BlendMode blendMode;

    /**
     * The transformation matrix used for drawing.
     */
    public final Transform transform;

    /**
     * The texture used for drawing.
     */
    public final ConstTexture texture;

    /**
     * The shader used for drawing.
     */
    public final ConstShader shader;

    /**
     * Constructs a new set of render states with default settings and
     * the specified blending mode.
     *
     * @param blendMode the blending mode used for drawing.
     */
    public RenderStates(BlendMode blendMode) {
        this(blendMode, Transform.IDENTITY, null, null);
    }

    /**
     * Constructs a new set of render states with default settings and
     * the specified transformation matrix.
     *
     * @param transform the transformation matrix used for drawing.
     */
    public RenderStates(Transform transform) {
        this(BlendMode.ALPHA, transform, null, null);
    }

    /**
     * Constructs a new set of render states with default settings and
     * the specified texture.
     *
     * @param texture the texture used for drawing,
     *                or {@code null} to indicate that no texture should be used.
     */
    public RenderStates(ConstTexture texture) {
        this(BlendMode.ALPHA, Transform.IDENTITY, texture, null);
    }

    /**
     * Constructs a new set of render states with default settings and
     * the specified shader.
     *
     * @param shader the shader applied to whatever is drawn using these states,
     *               or {@code null} to indicate that no shader should be used.
     */
    public RenderStates(ConstShader shader) {
        this(BlendMode.ALPHA, Transform.IDENTITY, null, shader);
    }

    /**
     * Constructs a new set of render states by copying other states,
     * but changing the blend mode.
     *
     * @param blendMode the blending mode used for drawing.
     */
    public RenderStates(RenderStates states, BlendMode blendMode) {
        this(blendMode, states.transform, states.texture, states.shader);
    }

    /**
     * Constructs a new set of render states by copying other states,
     * but changing the transformation.
     *
     * @param transform the transformation matrix used for drawing.
     */
    public RenderStates(RenderStates states, Transform transform) {
        this(states.blendMode, transform, states.texture, states.shader);
    }

    /**
     * Constructs a new set of render states by copying other states,
     * but changing the texture.
     *
     * @param texture the texture used for drawing, or {@code null} to indicate that
     *                no texture should be used.
     */
    public RenderStates(RenderStates states, ConstTexture texture) {
        this(states.blendMode, states.transform, texture, states.shader);
    }

    /**
     * Constructs a new set of render states by copying other states,
     * but changing the shader.
     *
     * @param shader the shader applied to whatever is drawn using these states,
     *               or {@code null} to indicate that no shader should be used.
     */
    public RenderStates(RenderStates states, ConstShader shader) {
        this(states.blendMode, states.transform, states.texture, shader);
    }

    /**
     * Constructs a new set of render states with the specified parameters.
     *
     * @param blendMode the blending mode used for drawing.
     * @param transform the transformation matrix used for drawing.
     * @param texture   the texture used for drawing,
     *                  or {@code null} to indicate that no texture should be used.
     * @param shader    the shader applied to whatever is drawn using these states,
     *                  or {@code null} to indicate that no shader should be used.
     */
    public RenderStates(BlendMode blendMode, Transform transform, ConstTexture texture, ConstShader shader) {
        this.blendMode = Objects.requireNonNull(blendMode);
        this.transform = Objects.requireNonNull(transform);
        this.texture = texture;
        this.shader = shader;
    }
}
