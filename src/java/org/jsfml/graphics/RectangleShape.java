package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

/**
 * A specialized shape representing a rectangle.
 */
public class RectangleShape extends Shape {
    //cache
    private Vector2f size = Vector2f.ZERO;

    /**
     * Constructs a new rectangle shape with no dimensions.
     */
    public RectangleShape() {
        super();
    }

    /**
     * Constructs a new rectangle shape with the specified dimensions.
     *
     * @param size the rectangle's dimensions.
     */
    public RectangleShape(Vector2f size) {
        this();
        setSize(size);
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

    private native void nativeSetSize(float w, float h);

    /**
     * Sets the dimensions of the rectangle.
     *
     * @param size the new dimensions of the rectangle.
     */
    public void setSize(Vector2f size) {
        nativeSetSize(size.x, size.y);
        this.size = size;
        pointsNeedUpdate = true;
    }

    /**
     * Gets the dimensions of the rectangle.
     *
     * @return the dimensions of the rectangle.
     */
    public Vector2f getSize() {
        return size;
    }
}
