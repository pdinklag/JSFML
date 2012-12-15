package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.JSFML;
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
     * Creates a new empty polygon and allocates a certain amount of points.
     * <p/>
     * This is equal to calling {@link ConvexShape#setPointCount(int)} directly after
     * construction of the polygon.
     *
     * @param points The amount of points of the polygon.
     */
    public ConvexShape(int points) {
        this();
        setPointCount(points);
    }

    /**
     * Creates a new polygon from a given set of points.
     * <p/>
     * This is equal to calling {@link ConvexShape#setPoints(org.jsfml.system.Vector2f...)}
     * directly after construction of the polygon.
     *
     * @param points The points of the polygon.
     */
    @JSFML
    public ConvexShape(@NotNull Vector2f... points) {
        this();
        setPoints(points);
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

    private native void nativeSetPoint(int i, Vector2f v);

    /**
     * Sets a point of the polygon.
     *
     * @param i The index of the point to set. Note that this index must be within the bounds
     *          of the polygon's point count, ie the point count needs to be set properly
     *          using {@link ConvexShape#setPointCount(int)} first.
     * @param v The point to set at the given index.
     */
    public void setPoint(int i, @NotNull Vector2f v) {
        if (v == null)
            throw new NullPointerException("point must not be null.");

        if (i < 0 || i >= getPointCount())
            throw new IndexOutOfBoundsException(Integer.toString(i));

        nativeSetPoint(i, v);
    }

    /**
     * Sets the amount of points that belong to the polygon.
     *
     * @param pointCount The amount of points of the polygon.
     */
    public native void setPointCount(int pointCount);

    /**
     * Sets the points of the polygon.
     * <p/>
     * The use of this method equals consecutive calls of {@link ConvexShape#setPointCount(int)}
     * and {@link ConvexShape#setPoint(int, org.jsfml.system.Vector2f)} for each point
     * in the given array.
     *
     * @param points The points of the polygon.
     */
    @JSFML
    public void setPoints(@NotNull Vector2f... points) {
        if (points == null)
            throw new NullPointerException("points must not be null.");

        setPointCount(points.length);

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                setPointCount(0);
                throw new NullPointerException("point " + i + " is null.");
            }

            nativeSetPoint(i, points[i]);
        }
    }
}
