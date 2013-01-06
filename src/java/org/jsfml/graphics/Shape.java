package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

import java.util.Objects;

/**
 * Abstract base class for (optionally) textured shapes with (optional) outlines.
 */
public abstract class Shape extends SFMLNativeTransformable implements Drawable {
    private ConstTexture texture = null;

    /**
     * Default constructor.
     */
    public Shape() {
        super();
    }

    private native void nativeSetTexture(Texture texture, boolean resetRect);

    /**
     * Sets the texture of the shape.
     * <p/>
     * The texture may be {@code null} if no texture is to be used.
     *
     * @param texture   the texture of the shape, or {@code null} to indicate that no texture
     *                  is to be used.
     * @param resetRect {@code true} to reset the texture rect, {@code false} otherwise.
     */
    public void setTexture(ConstTexture texture, boolean resetRect) {
        this.texture = texture;
        nativeSetTexture((Texture) texture, resetRect);
    }

    /**
     * Sets the texture of the shape without affecting the texture rectangle.
     * <p/>
     * The texture may be {@code null} if no texture is to be used.
     *
     * @param texture the texture of the shape, or {@code null} to indicate that no texture
     *                is to be used.
     */
    public final void setTexture(ConstTexture texture) {
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
    public void setTextureRect(IntRect rect) {
        nativeSetTextureRect(Objects.requireNonNull(rect));
    }

    private native void nativeSetFillColor(Color color);

    /**
     * Sets the fill color of the shape.
     *
     * @param color the new fill color of the shape, or {@link Color#TRANSPARENT} to indicate
     *              that the shape should not be filled.
     */
    public void setFillColor(Color color) {
        nativeSetFillColor(Objects.requireNonNull(color));
    }

    private native void nativeSetOutlineColor(Color color);

    /**
     * Sets the outline color of the shape.
     *
     * @param color the new outline color of the shape.
     */
    public void setOutlineColor(Color color) {
        nativeSetOutlineColor(Objects.requireNonNull(color));
    }

    /**
     * Sets the thickness of the shape's outline.
     *
     * @param thickness the thickness of the shape's outline, or 0 to indicate that no
     *                  outline should be drawn.
     */
    public native void setOutlineThickness(float thickness);

    /**
     * Gets the shape's current texture.
     *
     * @return the shape's current texture.
     */
    public ConstTexture getTexture() {
        return texture;
    }

    /**
     * Gets the shape's current texture portion.
     *
     * @return the shape's current texture portion.
     */
    public native IntRect getTextureRect();

    /**
     * Gets the shape's current fill color.
     *
     * @return the shape's current fill color.
     */
    public native Color getFillColor();

    /**
     * Gets the shape's current outline color.
     *
     * @return the shape's current outline color.
     */
    public native Color getOutlineColor();

    /**
     * Gets the shape's current outline thickness.
     *
     * @return the shape's current outline thickness.
     */
    public native float getOutlineThickness();

    /**
     * Gets the amount of points that defines this shape.
     *
     * @return the amount of points that defines this shape.
     */
    public native int getPointCount();

    private native Vector2f nativeGetPoint(int i);

    /**
     * Gets a point of the shape.
     *
     * @param i the index of the point to retrieve.
     * @return the point at the given index.
     */
    public Vector2f getPoint(int i) {
        if (i < 0 || i >= getPointCount())
            throw new IndexOutOfBoundsException(Integer.toString(i));

        return nativeGetPoint(i);
    }

    /**
     * Gets all the points of the shape.
     *
     * @return an array containing the points of the shape.
     */
    public Vector2f[] getPoints() {
        int n = getPointCount();
        Vector2f[] points = new Vector2f[n];

        for (int i = 0; i < n; i++)
            points[i] = nativeGetPoint(i);

        return points;
    }

    /**
     * Gets the shape's local bounding rectangle,
     * <i>not</i> taking the shape's transformation into account.
     *
     * @return the shape's local bounding rectangle.
     * @see org.jsfml.graphics.Shape#getGlobalBounds()
     */
    public native FloatRect getLocalBounds();

    /**
     * Gets the shape's global bounding rectangle in the scene,
     * taking the shape's transformation into account.
     *
     * @return the shape's global bounding rectangle.
     * @see org.jsfml.graphics.Shape#getLocalBounds()
     */
    public native FloatRect getGlobalBounds();

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        DrawableNativeImpl.draw(this,
                Objects.requireNonNull(target),
                Objects.requireNonNull(states));
    }
}
