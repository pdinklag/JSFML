package org.jsfml.graphics;

import org.jsfml.Intercom;

/**
 * Defines the states used for drawing to a {@link RenderTarget}
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
     * @param blendMode The blending mode.
     */
    public RenderStates(BlendMode blendMode) {
        setBlendMode(blendMode);
    }

    /**
     * Creates a new set of render states.
     * @param transform The transform.
     */
    public RenderStates(Transform transform) {
        setTransform(transform);
    }

    /**
     * Creates a new set of render states.
     * @param texture The texture.
     */
    public RenderStates(Texture texture) {
        setTexture(texture);
    }

    /**
     * Creates a new set of render states.
     * @param shader The shader.
     */
    public RenderStates(Shader shader) {
        setShader(shader);
    }

    /**
     * Creates a new set of render states.
     * @param blendMode The blending mode.
     * @param transform The transform.
     * @param texture The texture.
     * @param shader The shader.
     */
    public RenderStates(BlendMode blendMode, Transform transform, Texture texture, Shader shader) {
        setBlendMode(blendMode);
        setTransform(transform);
        setTexture(texture);
        setShader(shader);
    }

    public BlendMode getBlendMode() {
        return blendMode;
    }

    public void setBlendMode(BlendMode blendMode) {
        if(blendMode == null)
            throw new IllegalArgumentException("blendMode must not be null.");

        this.blendMode = blendMode;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        if(transform == null)
            throw new IllegalArgumentException("transform must not be null.");

        this.transform = transform;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }
}
