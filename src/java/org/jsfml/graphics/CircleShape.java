package org.jsfml.graphics;

/**
 * A specialized shape representing a circle.
 */
public class CircleShape extends Shape {
    /**
     * Creates a new circle shape with a zero radius approximated using 30 points.
     */
    public CircleShape() {
        super();
    }

    /**
     * Creates a new circle shape approximated using 30 points.
     *
     * @param radius The circle's radius in pixels.
     */
    public CircleShape(float radius) {
        this();
        setRadius(radius);
    }

    /**
     * Creates a new circle shape approximated using 30 points.
     *
     * @param radius The circle's radius in pixels.
     * @param points The amount of points to approximate the circle with.
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

    /**
     * Sets the radius of this circle.
     *
     * @param radius The new radius of the circle in pixels.
     */
    public native void setRadius(float radius);

    /**
     * Gets the radius of this circle.
     *
     * @return The radius of this circle in pixels.
     */
    public native float getRadius();

    /**
     * Sets the amount of points the circle should be approximated with.
     * <p/>
     * A higher amount of points will yield a smoother result at the cost of performance.
     *
     * @param count The amount of points used to approximate the circle.
     */
    public native void setPointCount(int count);
}
