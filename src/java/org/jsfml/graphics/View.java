package org.jsfml.graphics;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.internal.SFMLNativeObject;
import org.jsfml.system.Vector2f;

import java.nio.Buffer;

/**
 * Represents a 2D camera which defines the region of the scene that is visible.
 */
public class View extends SFMLNativeObject implements ConstView {
    //Cache
    private Vector2f size = new Vector2f(1000, 1000);
    private Vector2f center = new Vector2f(500, 500);
    private float rotation = 0;
    private FloatRect viewport = new FloatRect(0, 0, 1, 1);

    private boolean transformNeedsUpdate = true;
    private Transform transformCache = null;
    private Transform inverseTransformCache = null;

    /**
     * Constructs a new view for the area of (0, 0, 1000, 1000).
     */
    public View() {
        super();
    }

    @SuppressWarnings("deprecation")
    View(long wrap) {
        super(wrap);
        sync();
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

    private native void nativeSetCenter(float x, float y);

    /**
     * Sets the center of the view.
     *
     * @param x the new X coordinate of the view's center.
     * @param y the new Y coordinate of the view's center.
     */
    public final void setCenter(float x, float y) {
        setCenter(new Vector2f(x, y));
    }

    /**
     * Sets the center of the view.
     *
     * @param v the new center of the view.
     */
    public void setCenter(Vector2f v) {
        nativeSetCenter(v.x, v.y);
        this.center = v;
        transformNeedsUpdate = true;
    }

    private native void nativeSetSize(float width, float height);

    /**
     * Sets the dimensions of the view.
     *
     * @param width  the new width of the view in pixels.
     * @param height the new height of the view in pixels.
     */
    public void setSize(float width, float height) {
        setSize(new Vector2f(width, height));
    }

    /**
     * Sets the dimensions of the view.
     *
     * @param v the new size of the view in pixels.
     */
    public void setSize(Vector2f v) {
        nativeSetSize(v.x, v.y);
        this.size = v;
        transformNeedsUpdate = true;
    }

    private native void nativeSetRotation(float angle);

    /**
     * Sets the rotation of the view around its center.
     *
     * @param angle the new rotation angle in degrees.
     */
    public void setRotation(float angle) {
        nativeSetRotation(angle);
        this.rotation = angle;
        transformNeedsUpdate = true;
    }

    private native void nativeSetViewport(Buffer buffer);

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
        nativeSetViewport(IntercomHelper.encodeFloatRect(rect));
        this.viewport = rect;
    }

    private native void nativeReset(Buffer buffer);

    /**
     * Resets the view to a certain viewport rectangle, resetting the rotation angle in the process.
     *
     * @param rect the viewport rectangle.
     */
    public void reset(FloatRect rect) {
        nativeReset(IntercomHelper.encodeFloatRect(rect));
        sync();
    }

    private native void nativeGetViewport(Buffer buffer);

    private native long nativeGetSize();

    private native long nativeGetCenter();

    private native float nativeGetRotation();

    private void sync() {
        nativeGetViewport(IntercomHelper.getBuffer());
        this.viewport = IntercomHelper.decodeFloatRect();
        this.size = IntercomHelper.decodeVector2f(nativeGetSize());
        this.center = IntercomHelper.decodeVector2f(nativeGetCenter());
        this.rotation = nativeGetRotation();
        transformNeedsUpdate = true;
    }

    @Override
    public Vector2f getCenter() {
        return center;
    }

    @Override
    public Vector2f getSize() {
        return size;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public FloatRect getViewport() {
        return viewport;
    }

    /**
     * Rotates the view around its center.
     *
     * @param angle the angle to rotate by, in degrees.
     */
    public final void rotate(float angle) {
        setRotation(rotation + angle);
    }

    /**
     * Moves the center of the view.
     *
     * @param x the X offset to move the view's center by.
     * @param y the Y offset to move the view's center by.
     */
    public final void move(float x, float y) {
        setCenter(new Vector2f(center.x + x, center.y + y));
    }

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
    public final void zoom(float factor) {
        setSize(size.x * factor, size.y * factor);
    }

    private native void nativeGetTransform(Buffer buf);

    private void updateTransform() {
        if (transformNeedsUpdate) {
            nativeGetTransform(IntercomHelper.getBuffer());
            transformCache = IntercomHelper.decodeTransform();
            inverseTransformCache = transformCache.getInverse();
            transformNeedsUpdate = false;
        }
    }

    @Override
    public Transform getTransform() {
        if (transformNeedsUpdate) {
            updateTransform();
        }

        return transformCache;
    }

    @Override
    public Transform getInverseTransform() {
        if (transformNeedsUpdate) {
            updateTransform();
        }

        return inverseTransformCache;
    }
}
