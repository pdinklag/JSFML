package org.jsfml.system;

import java.io.Serializable;

/**
 * Represents three-dimensional vectors using integer coordinates and provides
 * arithmetic operations on integral 3D vectors.
 */
public final class Vector3i implements Serializable {
    private static final long serialVersionUID = -2260992069088589367L;

    /**
     * The zero vector.
     */
    public static final Vector3i ZERO = new Vector3i(0, 0, 0);

    /**
     * Adds two vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return a new vector, representing the sum of the two vectors.
     */
    public static Vector3i add(Vector3i a, Vector3i b) {
        return new Vector3i(
                a.x + b.x,
                a.y + b.y,
                a.z + b.z);
    }

    /**
     * Subtracts two vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return a new vector, representing the difference between the two vectors.
     */
    public static Vector3i sub(Vector3i a, Vector3i b) {
        return new Vector3i(
                a.x - b.x,
                a.y - b.y,
                a.z - b.z);
    }

    /**
     * Performs a component-wise multiplication of two vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return a new vector, representing the "product" of the two vectors.
     */
    public static Vector3i componentwiseMul(Vector3i a, Vector3i b) {
        return new Vector3i(
                a.x * b.x,
                a.y * b.y,
                a.z * b.z);
    }

    /**
     * Performs a component-wise division of two vectors.
     *
     * @param a the first vector.
     * @param b the second vector.
     * @return a new vector, representing the "quotient" of the two vectors.
     */
    public static Vector3i componentwiseDiv(Vector3i a, Vector3i b) {
        return new Vector3i(
                a.x / b.x,
                a.y / b.y,
                a.z / b.z);
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param a the vector.
     * @param s the scalar to multiply by.
     * @return a new vector, representing the scaled vector.
     */
    public static Vector3i mul(Vector3i a, int s) {
        return new Vector3i(
                a.x * s,
                a.y * s,
                a.z * s);
    }

    /**
     * Performs an integer division on each component of the vector by a scalar.
     *
     * @param a the vector.
     * @param s the scalar.
     * @return a new vector, representing the scaled vector.
     */
    public static Vector3i div(Vector3i a, int s) {
        return new Vector3i(
                a.x / s,
                a.y / s,
                a.z / s);
    }

    /**
     * Computes the negation of a vector.
     *
     * @param v the vector.
     * @return a new vector, representing the negation of the given vector.
     */
    public static Vector3i neg(Vector3i v) {
        return new Vector3i(-v.x, -v.y, -v.z);
    }

    /**
     * The vector's X coordinate.
     */
    public final int x;

    /**
     * The vector's Y coordinate.
     */
    public final int y;

    /**
     * The vector's Z coordinate.
     */
    public final int z;

    /**
     * Constructs a new vector with the given coordinates.
     *
     * @param x the X coordinate.
     * @param y the Y coordinate.
     * @param z the Z coordinate.
     */
    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a new vector by converting a floating point vector.
     * <p/>
     * The fractions of the coordinates will be cut off.
     *
     * @param v the vector to convert.
     */
    public Vector3i(Vector3f v) {
        this((int) v.x, (int) v.y, (int) v.z);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Vector3f &&
                ((Vector3f) o).x == x && ((Vector3f) o).y == y && ((Vector3f) o).z == z);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
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
