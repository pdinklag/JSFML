package org.jsfml.graphics;

import org.jsfml.Intercom;
import org.jsfml.NotNull;

/**
 * Defines drawing properties when drawing to a {@link RenderTarget}.
 */
@Intercom
public class RenderStates {
    @Intercom
    private BlendMode blendMode = BlendMode.ALPHA;

    @Intercom
    private Transform transform = new Transform();

    @Intercom
    private Texture texture = null;

    @Intercom
    private Shader shader = null;

    /**
     * Creates a new set of render states with default settings.
     */
    public RenderStates() {
    }

    /**
     * Creates a new set of render states.
     *
     * @param blendMode The blending mode used for drawing.
     */
    public RenderStates(@NotNull BlendMode blendMode) {
        setBlendMode(blendMode);
    }

    /**
     * Creates a new set of render states.
     *
     * @param transform The transformation matrix used for drawing.
     */
    public RenderStates(@NotNull Transform transform) {
        setTransform(transform);
    }

    /**
     * Creates a new set of render states.
     *
     * @param texture The texture used for drawing.
     */
    public RenderStates(Texture texture) {
        setTexture(texture);
    }

    /**
     * Creates a new set of render states.
     *
     * @param shader The shader applied to whatever is drawn using these states.
     */
    public RenderStates(Shader shader) {
        setShader(shader);
    }

    /**
     * Creates a new set of render states.
     *
     * @param blendMode The blending mode used for drawing.
     * @param transform The transformation matrix used for drawing.
     * @param texture   The texture used for drawing.
     * @param shader    The shader applied to whatever is drawn using these states.
     */
    public RenderStates(BlendMode blendMode, Transform transform, Texture texture, Shader shader) {
        setBlendMode(blendMode);
        setTransform(transform);
        setTexture(texture);
        setShader(shader);
    }

    /**
     * Gets the blending mode used for drawing.
     *
     * @return The blending mode used for drawing.
     */
    public BlendMode getBlendMode() {
        return blendMode;
    }

    /**
     * Sets the blending mode used for drawing using these states.
     *
     * @param blendMode The blending mode used for drawing.
     */
    public void setBlendMode(@NotNull BlendMode blendMode) {
        if (blendMode == null)
            throw new NullPointerException("blendMode must not be null.");

        this.blendMode = blendMode;
    }

    /**
     * Gets the transformation matrix used for drawing.
     *
     * @return The transformation matrix used for drawing.
     */
    public Transform getTransform() {
        return transform;
    }

    /**
     * Sets the transformation matrix used for drawing using these states.
     *
     * @param transform The transformation matrix used for drawing.
     */
    public void setTransform(@NotNull Transform transform) {
        if (transform == null)
            throw new NullPointerException("transform must not be null.");

        this.transform = transform;
    }

    /**
     * Gets the texture used for drawing using these states.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Sets the texture used for drawing using these states.
     *
     * @param texture The texture used for drawing. This may be <tt>null</tt> to indicate
     *                that no texture shall be used.
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * Gets the shader used for drawing.
     *
     * @return The shader used for drawing.
     */
    public Shader getShader() {
        return shader;
    }

    /**
     * Sets the shader applied to whatever is drawn using these states.
     *
     * @param shader The shader used for drawing.This may be <tt>null</tt> to indicate
     *               that no shader shall be used.
     */
    public void setShader(Shader shader) {
        this.shader = shader;
    }
}
