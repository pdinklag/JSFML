package org.jsfml.graphics;

import org.jsfml.internal.SFMLNativeObject;
import org.jsfml.system.Vector2f;

import java.util.Objects;

/**
 * Represents a 2D camera which defines the region of the scene that is visible.
 */
public class View extends SFMLNativeObject implements ConstView {
    /**
     * Constructs a new view for the area of (0, 0, 1000, 1000).
     */
    public View() {
        super();
    }

    @SuppressWarnings("deprecation")
    View(long wrap) {
        super(wrap);
    }

    /**
     * Constructs a new view for the specified area.
     *
     * @param rect the area visible by this view.
     */
    public View(FloatRect rect) {
        this();
        reset(rect);
    }

    /**
     * Constructs a view for the specified area.
     *
     * @param center the center of the view.
     * @param size   the size of the view.
     */
    public View(Vector2f center, Vector2f size) {
        this();
        setCenter(center);
        setSize(size);
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected final native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected void nativeSetExPtr() {
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    /**
     * Sets the center of the view.
     *
     * @param x the new X coordinate of the view's center.
     * @param y the new Y coordinate of the view's center.
     */
    public native void setCenter(float x, float y);

    /**
     * Sets the center of the view.
     *
     * @param v the new center of the view.
     */
    public final void setCenter(Vector2f v) {
        setCenter(v.x, v.y);
    }

    /**
     * Sets the dimensions of the view.
     *
     * @param width  the new width of the view in pixels.
     * @param height the new height of the view in pixels.
     */
    public native void setSize(float width, float height);

    /**
     * Sets the dimensions of the view.
     *
     * @param v the new size of the view in pixels.
     */
    public final void setSize(Vector2f v) {
        setSize(v.x, v.y);
    }

    /**
     * Sets the rotation of the view around its center.
     *
     * @param angle the new rotation angle in degrees.
     */
    public native void setRotation(float angle);

    private native void nativeSetViewport(FloatRect rect);

    /**
     * Sets the viewport rectangle of this view.
     * <p/>
     * The viewport defines which portion of the render target is used by this view and is
     * expressed using factors between 0 and 1 (percentage of the target's width and height).
     * <p/>
     * The default viewport rectangle is (0, 0, 1, 1).
     *
     * @param rect the new viewport rectangle.
     */
    public void setViewport(FloatRect rect) {
        nativeSetViewport(Objects.requireNonNull(rect));
    }

    private native void nativeReset(FloatRect rect);

    /**
     * Resets the view to a certain viewport rectangle, resetting the rotation angle in the process.
     *
     * @param rect the viewport rectangle.
     */
    public void reset(FloatRect rect) {
        nativeReset(Objects.requireNonNull(rect));
    }

    @Override
    public native Vector2f getCenter();

    @Override
    public native Vector2f getSize();

    @Override
    public native float getRotation();

    @Override
    public native FloatRect getViewport();

    /**
     * Rotates the view around its center.
     *
     * @param angle the angle to rotate by, in degrees.
     */
    public native void rotate(float angle);

    /**
     * Moves the center of the view.
     *
     * @param x the X offset to move the view's center by.
     * @param y the Y offset to move the view's center by.
     */
    public native void move(float x, float y);

    /**
     * Moves the center of the view.
     *
     * @param v the offset vector to move the view's center by.
     */
    public final void move(Vector2f v) {
        move(v.x, v.y);
    }

    /**
     * Resizes the view.
     *
     * @param factor the zoom factor.
     */
    public native void zoom(float factor);

    @Override
    public native Transform getTransform();

    @Override
    public native Transform getInverseTransform();
}
