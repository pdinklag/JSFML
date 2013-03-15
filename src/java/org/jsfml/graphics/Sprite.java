package org.jsfml.graphics;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.system.Vector2f;

import java.nio.Buffer;
import java.util.Objects;

/**
 * Represents a drawable instance of a texture or texture portion.
 */
public class Sprite extends SFMLNativeTransformable implements Drawable {
    //cache
    private Color color = Color.WHITE;
    private IntRect textureRect = IntRect.EMPTY;
    private ConstTexture texture = null;

    private boolean boundsNeedUpdate = true;
    private FloatRect localBounds = null;
    private FloatRect globalBounds = null;

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
    public void setTexture(ConstTexture texture, boolean resetRect) {
        nativeSetTexture((Texture) Objects.requireNonNull(texture), resetRect);
        this.texture = texture;

        if(resetRect) {
            textureRect = IntRect.EMPTY;
        }

        boundsNeedUpdate = true;
    }

    /**
     * Sets the texture of this sprite without affecting the texture rectangle.
     *
     * @param texture the new texture.
     */
    public final void setTexture(ConstTexture texture) {
        setTexture(texture, false);
    }

    private native void nativeSetTextureRect(Buffer rect);

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
    public void setTextureRect(IntRect rect) {
        this.textureRect = rect;
        nativeSetTextureRect(IntercomHelper.encodeIntRect(rect));
        boundsNeedUpdate = true;
    }

    private native void nativeSetColor(int color);

    /**
     * Sets the color mask of the sprite.
     *
     * @param color the new color.
     */
    public void setColor(Color color) {
        this.color = color;
        nativeSetColor(IntercomHelper.encodeColor(color));
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
    public IntRect getTextureRect() {
        return textureRect;
    }

    /**
     * Gets the sprite's current color mask.
     *
     * @return the sprite's current color mask.
     */
    public Color getColor() {
        return color;
    }

    private native void nativeGetLocalBounds(Buffer buf);

    private native void nativeGetGlobalBounds(Buffer buf);

    private void updateBounds() {
        if(boundsNeedUpdate) {
            nativeGetLocalBounds(IntercomHelper.getBuffer());
            localBounds = IntercomHelper.decodeFloatRect();

            nativeGetGlobalBounds(IntercomHelper.getBuffer());
            globalBounds = IntercomHelper.decodeFloatRect();

            boundsNeedUpdate = false;
        }
    }

    /**
     * Gets the sprite's local bounding rectangle,
     * <i>not</i> taking the sprite's transformation into account.
     *
     * @return the sprite's local bounding rectangle.
     * @see org.jsfml.graphics.Sprite#getGlobalBounds()
     */
    public FloatRect getLocalBounds() {
        if(boundsNeedUpdate) {
            updateBounds();
        }

        return localBounds;
    }

    /**
     * Gets the sprite's global bounding rectangle in the scene,
     * taking the sprite's transformation into account.
     *
     * @return the sprite's global bounding rectangle.
     * @see org.jsfml.graphics.Sprite#getLocalBounds()
     */
    public FloatRect getGlobalBounds() {
        if(boundsNeedUpdate) {
            updateBounds();
        }

        return globalBounds;
    }

    @Override
    public void setPosition(Vector2f v) {
        super.setPosition(v);
        boundsNeedUpdate = true;
    }

    @Override
    public void setRotation(float angle) {
        super.setRotation(angle);
        boundsNeedUpdate = true;
    }

    @Override
    public void setScale(Vector2f v) {
        super.setScale(v);
        boundsNeedUpdate = true;
    }

    @Override
    public void setOrigin(Vector2f v) {
        super.setOrigin(v);
        boundsNeedUpdate = true;
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        SFMLNativeDrawer.draw(this,
                Objects.requireNonNull(target),
                Objects.requireNonNull(states));
    }
}
