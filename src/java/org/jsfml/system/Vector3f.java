package org.jsfml.system;

import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for 3-dimensional floating point vectors.
 */
@Intercom
public final class Vector3f implements Serializable {
    private static final long serialVersionUID = -2176250005619169432L;

    /**
     * Adds two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the sum of the two vectors.
     */
    public static Vector3f add(Vector3f a, Vector3f b) {
        return new Vector3f(
                a.x + b.x,
                a.y + b.y,
                a.z + b.z);
    }

    /**
     * Subtracts two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the difference between the two vectors.
     */
    public static Vector3f sub(Vector3f a, Vector3f b) {
        return new Vector3f(
                a.x - b.x,
                a.y - b.y,
                a.z - b.z);
    }

    /**
     * Performs a component-wise multiplication of two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the "product" of the two vectors.
     */
    public static Vector3f componentwiseMul(Vector3f a, Vector3f b) {
        return new Vector3f(
                a.x * b.x,
                a.y * b.y,
                a.z * b.z);
    }

    /**
     * Performs a component-wise division of two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the "quotient" of the two vectors.
     */
    public static Vector3f componentwiseDiv(Vector3f a, Vector3f b) {
        if (b.x == 0 || b.y == 0 || b.z == 0)
            throw new IllegalArgumentException("Division by zero: " + a + " / " + b);

        return new Vector3f(
                a.x / b.x,
                a.y / b.y,
                a.z / b.z);
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param a The vector.
     * @param s The scalar.
     * @return A new vector, representing the scaled vector.
     */
    public static Vector3f mul(Vector3f a, float s) {
        return new Vector3f(
                a.x * s,
                a.y * s,
                a.z * s);
    }

    /**
     * Multiplies a vector by the inverse of a scalar.
     *
     * @param a The vector.
     * @param s The scalar.
     * @return A new vector, representing the scaled vector.
     */
    public static Vector3f div(Vector3f a, float s) {
        if (s == 0)
            throw new IllegalArgumentException("Division by zero: " + a + " / " + s);

        return new Vector3f(
                a.x / s,
                a.y / s,
                a.z / s);
    }

    /**
     * Computes the negative of a vector.
     *
     * @param v The vector.
     * @return A new vector, representing the negative of the given vector.
     */
    public static Vector3f neg(Vector3f v) {
        return new Vector3f(-v.x, -v.y, -v.z);
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
     * The vector's Z coordinate.
     */
    @Intercom
    public final float z;

    /**
     * Creates a new 3D vector.
     */
    public Vector3f() {
        this(0, 0, 0);
    }

    /**
     * Creates a new 3D vector as a copy of another vector.
     *
     * @param v The vector to copy.
     */
    public Vector3f(Vector3f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    /**
     * Creates a new 3D vector with the given coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     */
    @Intercom
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Vector3f &&
                ((Vector3f) o).x == x && ((Vector3f) o).y == y && ((Vector3f) o).z == z);
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vector3f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
