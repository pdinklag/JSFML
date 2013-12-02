package org.jsfml.graphics;

/**
 * A specialized shape representing a circle.
 */
public class CircleShape extends Shape {
    //cache
    private float radius = 0;

    /**
     * Constructs a new circle shape with a zero radius, approximated using 30 points.
     */
    public CircleShape() {
        super();
    }

    /**
     * Constructs a new circle shape with the specified radius, approximated using 30 points.
     *
     * @param radius the circle's radius.
     */
    public CircleShape(float radius) {
        this();
        setRadius(radius);
    }

    /**
     * Constructs a new circle shape with the specified parameters.
     *
     * @param radius the circle's radius.
     * @param points the amount of points to approximate the circle with.
     * @see CircleShape#setPointCount(int)
     */
    public CircleShape(float radius, int points) {
        this(radius);
        setPointCount(points);
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

    private native void nativeSetRadius(float radius);

    /**
     * Sets the radius of this circle.
     *
     * @param radius the new radius of the circle shape.
     */
    public void setRadius(float radius) {
        nativeSetRadius(radius);
        this.radius = radius;
        pointsNeedUpdate = true;
    }

    /**
     * Gets the radius of this circle.
     *
     * @return the radius of this circle shape.
     */
    public float getRadius() {
        return radius;
    }

    private native void nativeSetPointCount(int count);

    /**
     * Sets the amount of points the circle should be approximated with.
     * <p/>
     * A higher amount of points will yield a smoother result at the cost of performance.
     *
     * @param count the amount of points used to approximate the circle.
     */
    public void setPointCount(int count) {
        nativeSetPointCount(count);
        pointsNeedUpdate = true;
    }
}
