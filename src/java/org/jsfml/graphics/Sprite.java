package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.system.Vector2f;

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
        super();
        setTexture(texture);
    }

    /**
     * Creates a new sprite from the given texture portion.
     *
     * @param texture The texture.
     * @param rect    The area of the texture to use.
     */
    public Sprite(Texture texture, IntRect rect) {
        super();
        setTexture(texture);
        setTextureRect(rect);
    }

    @Override
    protected native long nativeCreate();

    private native void nativeSetTexture(Texture texture);

    /**
     * Sets the texture of this sprite.
     *
     * @param texture   The new texture.
     * @param resetRect <tt>true</tt> to reset the texture rectangle, <tt>false</tt> otherwise.
     */
    public void setTexture(@NotNull Texture texture, boolean resetRect) {
        if (texture == null)
            throw new IllegalArgumentException("texture must not be null.");

        nativeSetTexture(texture);
        this.texture = texture;
    }

    /**
     * Sets the texture of this sprite.
     *
     * @param texture The new texture.
     */
    public void setTexture(Texture texture) {
        setTexture(texture, false);
    }

    private native void nativeSetTextureRect(IntRect rect);

    /**
     * Sets the portion of the texture that will be used for drawing.
     *
     * @param rect The texture portion.
     */
    public void setTextureRect(@NotNull IntRect rect) {
        if (rect == null)
            throw new IllegalArgumentException("rect must not be null.");

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
            throw new IllegalArgumentException("color must not be null.");

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
    public native void setPosition(float x, float y);

    @Override
    public native void setRotation(float angle);

    @Override
    public native void setScale(float x, float y);

    @Override
    public native void setOrigin(float x, float y);

    @Override
    public native Vector2f getPosition();

    @Override
    public native float getRotation();

    @Override
    public native Vector2f getScale();

    @Override
    public native Vector2f getOrigin();

    @Override
    public native void move(float x, float y);

    @Override
    public native void rotate(float angle);

    @Override
    public native void scale(float x, float y);

    @Override
    public native Transform getTransform();

    @Override
    public native Transform getInverseTransform();
}
