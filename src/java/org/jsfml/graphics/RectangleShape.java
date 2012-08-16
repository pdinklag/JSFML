package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.system.Vector2f;

/**
 * A specialized shape representing a rectangle.
 */
public class RectangleShape extends Shape {
    /**
     * Creates a new rectangle with no dimensions.
     */
    public RectangleShape() {
        super();
    }

    /**
     * Creates a new rectangle shape.
     *
     * @param size The rectangle's dimensions.
     */
    public RectangleShape(Vector2f size) {
        this();
        setSize(size);
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    private native void nativeSetSize(Vector2f size);

    /**
     * Sets the dimensions of the rectangle.
     *
     * @param size The dimensions of the rectangle.
     */
    public void setSize(@NotNull Vector2f size) {
        if (size == null)
            throw new NullPointerException("size must not be null.");

        nativeSetSize(size);
    }

    /**
     * Gets the dimensions of the rectangle.
     *
     * @return The dimensions of the rectangle.
     */
    public native Vector2f getSize();
}
