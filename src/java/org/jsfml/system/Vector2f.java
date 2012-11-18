package org.jsfml.system;

import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for 2-dimensional floating point vectors.
 */
@Intercom
public final strictfp class Vector2f implements Serializable {
    private static final long serialVersionUID = -2082611034304583379L;

    /**
     * Adds two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the sum of the two vectors.
     */
    public static Vector2f add(Vector2f a, Vector2f b) {
        return new Vector2f(a.x + b.x, a.y + b.y);
    }

    /**
     * Subtracts two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the difference between the two vectors.
     */
    public static Vector2f sub(Vector2f a, Vector2f b) {
        return new Vector2f(a.x - b.x, a.y - b.y);
    }

    /**
     * Performs a component-wise multiplication of two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the "product" of the two vectors.
     */
    public static Vector2f componentwiseMul(Vector2f a, Vector2f b) {
        return new Vector2f(a.x * b.x, a.y * b.y);
    }

    /**
     * Performs a component-wise division of two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the "quotient" of the two vectors.
     */
    public static Vector2f componentwiseDiv(Vector2f a, Vector2f b) {
        return new Vector2f(a.x / b.x, a.y / b.y);
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param a The vector.
     * @param s The scalar.
     * @return A new vector, representing the scaled vector.
     */
    public static Vector2f mul(Vector2f a, float s) {
        return new Vector2f(a.x * s, a.y * s);
    }

    /**
     * Multiplies a vector by the inverse of a scalar.
     *
     * @param a The vector.
     * @param s The scalar.
     * @return A new vector, representing the scaled vector.
     */
    public static Vector2f div(Vector2f a, float s) {
        return new Vector2f(a.x / s, a.y / s);
    }

    /**
     * Computes the negative of a vector.
     *
     * @param v The vector.
     * @return A new vector, representing the negative of the given vector.
     */
    public static Vector2f neg(Vector2f v) {
        return new Vector2f(-v.x, -v.y);
    }

    /**
     * The vector's X coordinate.
     */
    @Intercom
    public final float x;

    /**
     * The vector's Y coordinate.
     */
    @Intercom
    public final float y;

    /**
     * Creates a new 2D vector.
     * <p/>
     * The x and y components of this vector will be zero.
     */
    public Vector2f() {
        this(0, 0);
    }

    /**
     * Creates a new 2D vector as a copy of another vector.
     *
     * @param v The vector to copy.
     */
    public Vector2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Creates a new 2D vector from an integer vector.
     * <p/>
     * The fractions of the x and y components will be zero.
     *
     * @param v The vector to convert.
     */
    public Vector2f(Vector2i v) {
        this((float) v.x, (float) v.y);
    }

    /**
     * Creates a new 2D vector with the given coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    @Intercom
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Vector2f && ((Vector2f) o).x == x && ((Vector2f) o).y == y);
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
