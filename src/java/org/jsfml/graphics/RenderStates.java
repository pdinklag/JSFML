package org.jsfml.graphics;

import org.jsfml.Intercom;
import org.jsfml.NotNull;

/**
 * Defines drawing properties when drawing to a {@link RenderTarget}.
 */
@Intercom
public final class RenderStates {
    @Intercom
    public final BlendMode blendMode;

    @Intercom
    public final Transform transform;

    @Intercom
    public final ConstTexture texture;

    @Intercom
    public final Shader shader;

    /**
     * Creates a new set of render states with default settings.
     */
    public RenderStates() {
        this(BlendMode.ALPHA, Transform.IDENTITY, null, null);
    }

    /**
     * Creates a new set of render states.
     *
     * @param blendMode The blending mode used for drawing.
     */
    public RenderStates(@NotNull BlendMode blendMode) {
        this(blendMode, Transform.IDENTITY, null, null);
    }

    /**
     * Creates a new set of render states.
     *
     * @param transform The transformation matrix used for drawing.
     */
    public RenderStates(@NotNull Transform transform) {
        this(BlendMode.ALPHA, transform, null, null);
    }

    /**
     * Creates a new set of render states.
     *
     * @param texture The texture used for drawing.
     */
    public RenderStates(ConstTexture texture) {
        this(BlendMode.ALPHA, Transform.IDENTITY, texture, null);
    }

    /**
     * Creates a new set of render states.
     *
     * @param shader The shader applied to whatever is drawn using these states.
     */
    public RenderStates(Shader shader) {
        this(BlendMode.ALPHA, Transform.IDENTITY, null, shader);
    }

    /**
     * Creates a new set of render states.
     *
     * @param blendMode The blending mode used for drawing.
     * @param transform The transformation matrix used for drawing.
     * @param texture   The texture used for drawing.
     * @param shader    The shader applied to whatever is drawn using these states.
     */
    public RenderStates(BlendMode blendMode, Transform transform, ConstTexture texture, Shader shader) {
        this.blendMode = blendMode;
        this.transform = transform;
        this.texture = texture;
        this.shader = shader;
    }
}
