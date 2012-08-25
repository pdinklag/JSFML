package org.jsfml.graphics;

import org.jsfml.NotNull;

/**
 * Drawable representation, or instance, of a texture or a texture portion.
 */
public class Sprite extends Transformable implements Drawable {
    private Texture texture = null;

    /**
     * Creates a new sprite without a texture set.
     */
    public Sprite() {
        super();
    }

    /**
     * Creates a new sprite from the given texture.
     *
     * @param texture The texture.
     */
    public Sprite(Texture texture) {
        this();
        setTexture(texture);
    }

    /**
     * Creates a new sprite from the given texture portion.
     *
     * @param texture The texture.
     * @param rect    The area of the texture to use.
     */
    public Sprite(Texture texture, IntRect rect) {
        this(texture);
        setTextureRect(rect);
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    private native void nativeSetTexture(Texture texture, boolean resetRect);

    /**
     * Sets the texture of this sprite.
     *
     * @param texture   The new texture.
     * @param resetRect <tt>true</tt> to reset the texture rectangle, <tt>false</tt> otherwise.
     */
    public void setTexture(@NotNull Texture texture, boolean resetRect) {
        if (texture == null)
            throw new NullPointerException("texture must not be null.");

        nativeSetTexture(texture, resetRect);
        this.texture = texture;
    }

    /**
     * Sets the texture of this sprite.
     *
     * @param texture The new texture.
     */
    public final void setTexture(Texture texture) {
        setTexture(texture, false);
    }

    private native void nativeSetTextureRect(IntRect rect);

    /**
     * Sets the portion of the texture that will be used for drawing.
     * <p/>
     * The width and / or height of the rectangle may be negative to indicate that the
     * respective axis should be flipped. For example, a width of <tt>-32</tt> will
     * result in a sprite that is 32 pixels wide and flipped horizontally.
     *
     * @param rect The texture portion.
     */
    public void setTextureRect(@NotNull IntRect rect) {
        if (rect == null)
            throw new NullPointerException("rect must not be null.");

        nativeSetTextureRect(rect);
    }

    private native void nativeSetColor(Color color);

    /**
     * Sets the color of the sprite.
     *
     * @param color The new color.
     */
    public void setColor(@NotNull Color color) {
        if (color == null)
            throw new NullPointerException("color must not be null.");

        nativeSetColor(color);
    }

    /**
     * Gets the sprite's current texture.
     *
     * @return The sprite's current texture.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Gets the sprite's current texture rectangle.
     *
     * @return The sprite's current texture rectangle.
     */
    public native IntRect getTextureRect();

    /**
     * Gets the sprite's current color.
     *
     * @return The sprite's current color.
     */
    public native Color getColor();

    /**
     * Gets the sprite's local bounding rectangle, <i>not</i> taking the sprite's transformation into account.
     *
     * @return The sprite's local bounding rectangle.
     * @see org.jsfml.graphics.Sprite#getGlobalBounds()
     */
    public native FloatRect getLocalBounds();

    /**
     * Gets the sprite's global bounding rectangle, taking the sprite's transformation into account.
     *
     * @return The sprite's global bounding rectangle.
     * @see org.jsfml.graphics.Sprite#getLocalBounds()
     */
    public native FloatRect getGlobalBounds();

    @Override
    public void draw(@NotNull RenderTarget target, @NotNull RenderStates states) {
        if(target == null)
            throw new NullPointerException("target must not be null");

        if(states == null)
            throw new NullPointerException("states must not be null");

        DrawableNativeImpl.draw(this, target, states);
    }
}
