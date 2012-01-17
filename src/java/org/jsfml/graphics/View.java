package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.SFMLNativeObject;
import org.jsfml.system.Vector2f;

/**
 * A 2D camera that defines which region of the world space is rendered.
 */
public class View extends SFMLNativeObject {
    /**
     * Creates a new View.
     */
    public View() {
        super();
    }

    @SuppressWarnings("deprecation")
    View(long wrap) {
        super(wrap);
    }

    @Override
    protected final native long nativeCreate();

    @Override
    protected native void nativeDelete();

    /**
     * Sets the center of the view.
     *
     * @param x The X coordinate of the view's center.
     * @param y The Y coordinate of the view's center.
     */
    public native void setCenter(float x, float y);

    /**
     * Sets the center of the view.
     *
     * @param v The center of the view.
     */
    public void setCenter(Vector2f v) {
        setCenter(v.x, v.y);
    }

    /**
     * Sets the dimensions of the view.
     *
     * @param width  The new width of the view in pixels.
     * @param height The new height of the view in pixels.
     */
    public native void setSize(float width, float height);

    /**
     * Sets the dimensions of the view.
     *
     * @param v The new size of the view in pixels.
     */
    public void setSize(Vector2f v) {
        setSize(v.x, v.y);
    }

    /**
     * Sets the rotation of the view.
     *
     * @param angle The rotation angle in degrees.
     */
    public native void setRotation(float angle);

    private native void nativeSetViewport(FloatRect rect);

    /**
     * Sets the viewport rectangle of this view.
     *
     * @param rect The new viewport rectangle.
     */
    public void setViewport(@NotNull FloatRect rect) {
        if (rect == null)
            throw new IllegalArgumentException("rect must not be null.");

        nativeSetViewport(rect);
    }

    private native void nativeReset(FloatRect rect);

    /**
     * Resets the view to a certain viewport rectangle, resetting the rotation angle in the process.
     *
     * @param rect The viewport rectangle.
     */
    public void reset(@NotNull FloatRect rect) {
        if (rect == null)
            throw new IllegalArgumentException("rect must not be null.");

        nativeReset(rect);
    }

    /**
     * Gets the current center of the view.
     *
     * @return The current center of the view.
     */
    public native Vector2f getCenter();

    /**
     * Gets the current dimensions of the view in pixels.
     *
     * @return The current dimensions of the view in pixels.
     */
    public native Vector2f getSize();

    /**
     * Gets the current rotation angle of the view in degrees.
     *
     * @return The current rotation angle of the view in degrees.
     */
    public native float getRotation();

    /**
     * Gets the current viewport rectangle of this view.
     *
     * @return The current viewport rectangle of this view.
     */
    public native FloatRect getViewport();

    /**
     * Rotates the view around its center.
     *
     * @param angle The angle to rotate by in degrees.
     */
    public native void rotate(float angle);

    /**
     * Moves the center of the view.
     *
     * @param x The X offset to move the view's center by.
     * @param y The Y offset to move the view's center by.
     */
    public native void move(float x, float y);

    /**
     * Moves the center of the view.
     *
     * @param v The offset vector to move the view's center by.
     */
    public void move(Vector2f v) {
        move(v.x, v.y);
    }

    /**
     * Resizes the view.
     *
     * @param factor The zoom factor.
     */
    public native void zoom(float factor);
}
