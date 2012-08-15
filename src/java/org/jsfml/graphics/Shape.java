package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.system.Vector2f;

/**
 * Base class for textured shapes with outlines.
 */
public abstract class Shape extends Transformable implements Drawable {
    private Texture texture = null;

    /**
     * Default constructor.
     */
    public Shape() {
        super();
    }

    abstract void nativeSetTexture(Texture texture, boolean resetRect);

    /**
     * Sets the texture of the shape.
     * <p/>
     * The texture may be <tt>null</tt> if no texture is to be used.
     *
     * @param texture   The texture of the shape.
     * @param resetRect <tt>true</tt> to reset the texture rect, <tt>false</tt> otherwise.
     */
    public void setTexture(Texture texture, boolean resetRect) {
        this.texture = texture;
        nativeSetTexture(texture, resetRect);
    }

    /**
     * Sets the texture of the shape.
     * <p/>
     * The texture may be <tt>null</tt> if no texture is to be used.
     *
     * @param texture The texture of the shape.
     */
    public void setTexture(Texture texture) {
        setTexture(texture, false);
    }

    abstract void nativeSetTextureRect(IntRect rect);

    /**
     * Sets the texture portion to use for drawing.
     *
     * @param rect The texture portion to use for drawing. An empty rectangle means the whole texture.
     */
    public void setTextureRect(@NotNull IntRect rect) {
        if (rect == null)
            throw new NullPointerException("rect must not be null.");

        nativeSetTextureRect(rect);
    }

    abstract void nativeSetFillColor(Color color);

    /**
     * Sets the fill color of the shape.
     *
     * @param color The fill color of the shape.
     */
    public void setFillColor(@NotNull Color color) {
        if (color == null)
            throw new NullPointerException("color must not be null.");

        nativeSetFillColor(color);
    }

    abstract void nativeSetOutlineColor(Color color);

    /**
     * Sets the outline color of the shape.
     *
     * @param color The outline color of the shape.
     */
    public void setOutlineColor(@NotNull Color color) {
        if (color == null)
            throw new NullPointerException("color must not be null.");

        nativeSetOutlineColor(color);
    }

    /**
     * Sets the thickness of the shape's outline.
     *
     * @param thickness The thickness of the shape's outline.
     */
    public abstract void setOutlineThickness(float thickness);

    /**
     * Gets the shape's current texture.
     *
     * @return The shape's current texture.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Gets the shape's current texture portion.
     *
     * @return The shape's current texture portion.
     */
    public abstract IntRect getTextureRect();

    /**
     * Gets the shape's current fill color.
     *
     * @return The shape's current fill color.
     */
    public abstract Color getFillColor();

    /**
     * Gets the shape's current outline color.
     *
     * @return The shape's current outline color.
     */
    public abstract Color getOutlineColor();

    /**
     * Gets the shape's current outline thickness.
     *
     * @return The shape's current outline thickness.
     */
    public abstract float getOutlineThickness();

    /**
     * Gets the amount of points that defines this shape.
     *
     * @return The amount of points that defines this shape.
     */
    public abstract int getPointCount();

    abstract Vector2f nativeGetPoint(int i);

    /**
     * Gets a point of the shape.
     *
     * @param i The index of the point to retrieve.
     * @return The point at the given index.
     */
    public Vector2f getPoint(int i) {
        if (i < 0 || i >= getPointCount())
            throw new IndexOutOfBoundsException(Integer.toString(i));

        return nativeGetPoint(i);
    }

    /**
     * Gets all the points of the shape.
     *
     * @return An array containing the points of the shape.
     */
    public Vector2f[] getPoints() {
        int n = getPointCount();
        Vector2f[] points = new Vector2f[n];

        for (int i = 0; i < n; i++)
            points[i] = nativeGetPoint(i);

        return points;
    }

    /**
     * Gets the shape's local bounding rectangle, <i>not</i> taking the shape's transformation into account.
     *
     * @return The shape's local bounding rectangle.
     * @see org.jsfml.graphics.Shape#getGlobalBounds()
     */
    public abstract FloatRect getLocalBounds();

    /**
     * Gets the shape's global bounding rectangle, taking the shape's transformation into account.
     *
     * @return The shape's global bounding rectangle.
     * @see org.jsfml.graphics.Shape#getLocalBounds()
     */
    public abstract FloatRect getGlobalBounds();
}
