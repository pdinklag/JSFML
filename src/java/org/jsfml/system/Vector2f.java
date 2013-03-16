package org.jsfml.system;

import java.io.Serializable;

/**
 * Represents two-dimensional vectors using floating point coordinates and provides
 * arithmetic operations on floating point 2D vectors.
 */
public final strictfp class Vector2f implements Serializable {
    private static final long serialVersionUID = -2082611034304583379L;

    /**
     * The zero vector.
     */
    public static final Vector2f ZERO = new Vector2f(0, 0);

    /**
     * Adds two vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return a new vector, representing the sum of the two vectors.
     */
    public static Vector2f add(Vector2f a, Vector2f b) {
        return new Vector2f(a.x + b.x, a.y + b.y);
    }

    /**
     * Subtracts two vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return a new vector, representing the difference between the two vectors.
     */
    public static Vector2f sub(Vector2f a, Vector2f b) {
        return new Vector2f(a.x - b.x, a.y - b.y);
    }

    /**
     * Performs a component-wise multiplication of two vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return a new vector, representing the "product" of the two vectors.
     */
    public static Vector2f componentwiseMul(Vector2f a, Vector2f b) {
        return new Vector2f(a.x * b.x, a.y * b.y);
    }

    /**
     * Performs a component-wise division of two vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return a new vector, representing the "quotient" of the two vectors.
     */
    public static Vector2f componentwiseDiv(Vector2f a, Vector2f b) {
        return new Vector2f(a.x / b.x, a.y / b.y);
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param a the vector.
     * @param s the scalar to multiply by.
     * @return a new vector, representing the scaled vector.
     */
    public static Vector2f mul(Vector2f a, float s) {
        return new Vector2f(a.x * s, a.y * s);
    }

    /**
     * Multiplies a vector by the inverse of a scalar.
     *
     * @param a the vector.
     * @param s the scalar to divide by.
     * @return a new vector, representing the scaled vector.
     */
    public static Vector2f div(Vector2f a, float s) {
        return new Vector2f(a.x / s, a.y / s);
    }

    /**
     * Computes the negation of a vector.
     *
     * @param v the vector.
     * @return a new vector, representing the negation of the given vector.
     */
    public static Vector2f neg(Vector2f v) {
        return new Vector2f(-v.x, -v.y);
    }

    /**
     * The vector's X coordinate.
     */
    public final float x;

    /**
     * The vector's Y coordinate.
     */
   public final float y;

    /**
     * Constructs a new vector with the given coordinates.
     *
     * @param x the X coordinate.
     * @param y the Y coordinate.
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new vector by converting an integral vector.
     * <p/>
     * The fractions of the coordinates will be zero.
     *
     * @param v the vector to convert.
     */
    public Vector2f(Vector2i v) {
        this((float) v.x, (float) v.y);
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
