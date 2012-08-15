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

    @Override
    native void nativeSetTexture(Texture texture, boolean resetRect);

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

    private native void nativeDraw(RenderTarget target, RenderStates states);

    @Override
    public void draw(@NotNull RenderTarget target, @NotNull RenderStates states) {
        if(target == null)
            throw new NullPointerException("target must not be null");

        if(states == null)
            throw new NullPointerException("states must not be null");

        nativeDraw(target, states);
    }
}
