package org.jsfml.graphics;

import org.jsfml.NotNull;

/**
 * Represents a drawable instance of a texture or texture portion.
 */
public class Sprite extends SFMLNativeTransformable implements Drawable {
    private ConstTexture texture = null;

    /**
     * Constructs a new sprite without a texture.
     */
    public Sprite() {
        super();
    }

    /**
     * Constructs a new sprite with the specified texture.
     *
     * @param texture the texture.
     */
    public Sprite(ConstTexture texture) {
        this();
        setTexture(texture);
    }

    /**
     * Constructs a new sprite with the specified texture and texture portion.
     *
     * @param texture the texture.
     * @param rect    the portion of the texture to use.
     */
    public Sprite(ConstTexture texture, IntRect rect) {
        this(texture);
        setTextureRect(rect);
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeSetExPtr();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native void nativeSetTexture(Texture texture, boolean resetRect);

    /**
     * Sets the texture of this sprite.
     *
     * @param texture   the new texture.
     * @param resetRect {@code true} to reset the texture rectangle, {@code false} otherwise.
     */
    public void setTexture(@NotNull ConstTexture texture, boolean resetRect) {
        if (texture == null)
            throw new NullPointerException("texture must not be null.");

        nativeSetTexture((Texture) texture, resetRect);
        this.texture = texture;
    }

    /**
     * Sets the texture of this sprite without affecting the texture rectangle.
     *
     * @param texture the new texture.
     */
    public final void setTexture(@NotNull ConstTexture texture) {
        setTexture(texture, false);
    }

    private native void nativeSetTextureRect(IntRect rect);

    /**
     * Sets the portion of the texture that will be used for drawing.
     * <p/>
     * An empty rectangle can be passed to indicate that the whole texture shall be used.
     * <p/>
     * The width and / or height of the rectangle may be negative to indicate that the
     * respective axis should be flipped. For example, a width of {@code -32} will
     * result in a sprite that is 32 pixels wide and flipped horizontally.
     *
     * @param rect the texture portion.
     */
    public void setTextureRect(@NotNull IntRect rect) {
        if (rect == null)
            throw new NullPointerException("rect must not be null.");

        nativeSetTextureRect(rect);
    }

    private native void nativeSetColor(Color color);

    /**
     * Sets the color mask of the sprite.
     *
     * @param color the new color.
     */
    public void setColor(@NotNull Color color) {
        if (color == null)
            throw new NullPointerException("color must not be null.");

        nativeSetColor(color);
    }

    /**
     * Gets the sprite's current texture.
     *
     * @return the sprite's current texture.
     */
    public ConstTexture getTexture() {
        return texture;
    }

    /**
     * Gets the sprite's current texture rectangle.
     *
     * @return the sprite's current texture rectangle.
     */
    public native IntRect getTextureRect();

    /**
     * Gets the sprite's current color mask.
     *
     * @return the sprite's current color mask.
     */
    public native Color getColor();

    /**
     * Gets the sprite's local bounding rectangle,
     * <i>not</i> taking the sprite's transformation into account.
     *
     * @return the sprite's local bounding rectangle.
     * @see org.jsfml.graphics.Sprite#getGlobalBounds()
     */
    public native FloatRect getLocalBounds();

    /**
     * Gets the sprite's global bounding rectangle in the scene,
     * taking the sprite's transformation into account.
     *
     * @return the sprite's global bounding rectangle.
     * @see org.jsfml.graphics.Sprite#getLocalBounds()
     */
    public native FloatRect getGlobalBounds();

    @Override
    public void draw(@NotNull RenderTarget target, @NotNull RenderStates states) {
        if (target == null)
            throw new NullPointerException("target must not be null");

        if (states == null)
            throw new NullPointerException("states must not be null");

        DrawableNativeImpl.draw(this, target, states);
    }
}
