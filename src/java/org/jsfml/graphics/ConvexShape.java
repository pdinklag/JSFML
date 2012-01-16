package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

/**
 * Specialized shape representing a convex polygon.
 */
public class ConvexShape extends Shape {
    /**
     * Creates a new empty polygon.
     */
    public ConvexShape() {
        super();
    }

    /**
     * Creates a new polygon.
     *
     * @param points The amount of points of the polygon.
     */
    public ConvexShape(int points) {
        super();
        setPointCount(points);
    }

    @Override
    protected native long nativeCreate();

    private native void nativeSetPoint(int i, Vector2f v);

    /**
     * Sets a point of the polygon.
     *
     * @param i The index of the point to set.
     * @param v The point to set at the given index.
     */
    public void setPoint(int i, Vector2f v) {
        if (v == null)
            throw new IllegalArgumentException("point must not be null.");

        if (i < 0 || i >= getPointCount())
            throw new IndexOutOfBoundsException(Integer.toString(i));

        nativeSetPoint(i, v);
    }

    /**
     * Sets the amount of points of the polygon.
     *
     * @param pointCount The amount of points of the polygon.
     */
    public native void setPointCount(int pointCount);

    private native void nativeSetTexture(Texture texture, boolean resetRect);

    @Override
    public void setTexture(Texture texture, boolean resetRect) {
        nativeSetTexture(texture, resetRect);
        super.setTexture(texture, resetRect);
    }

    @Override
    native void nativeSetTextureRect(IntRect rect);

    @Override
    native void nativeSetFillColor(Color color);

    @Override
    native void nativeSetOutlineColor(Color color);

    @Override
    public native void setOutlineThickness(float thickness);

    @Override
    public native IntRect getTextureRect();

    @Override
    public native Color getFillColor();

    @Override
    public native Color getOutlineColor();

    @Override
    public native float getOutlineThickness();

    @Override
    public native int getPointCount();

    @Override
    native Vector2f nativeGetPoint(int i);

    @Override
    public native FloatRect getLocalBounds();

    @Override
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
