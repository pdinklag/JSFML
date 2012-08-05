package org.jsfml.system;

import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for manipulating 2-dimensional vectors.
 */
@Intercom
public class Vector2i implements Cloneable, Serializable {
    private static final long serialVersionUID = 4059550337913883695L;

    /**
     * Adds two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the sum of the two vectors.
     */
    public static Vector2i add(Vector2i a, Vector2i b) {
        return new Vector2i(a).add(b);
    }

    /**
     * Subtracts two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the difference between the two vectors.
     */
    public static Vector2i sub(Vector2i a, Vector2i b) {
        return new Vector2i(a).sub(b);
    }

    /**
     * Computes the negative of a vector.
     *
     * @param v The vector.
     * @return A new vector, representing the negative of the given vector.
     */
    public static Vector2i neg(Vector2i v) {
        return new Vector2i(v).negate();
    }

    /**
     * The vector's X coordinate.
     */
    @Intercom
    public int x = 0;

    /**
     * The vector's Y coordinate.
     */
    @Intercom
    public int y = 0;

    /**
     * Creates a new 2D vector.
     */
    public Vector2i() {
    }

    /**
     * Creates a new 2D vector as a copy of another vector.
     *
     * @param v The vector to copy.
     */
    public Vector2i(Vector2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Creates a new 2D vector from a floating point vector.
     * The fractions of the x and y components will be removed in the process.
     *
     * @param v The vector to convert.
     */
    public Vector2i(Vector2f v) {
        this((int) v.x, (int) v.y);
    }

    /**
     * Creates a new 2D vector with the given coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    @Intercom
    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add.
     * @return This vector after the addition.
     */
    public Vector2i add(Vector2i v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract.
     * @return This vector after the subtraction.
     */
    public Vector2i sub(Vector2i v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * Negates this vector.
     *
     * @return This vector after the negation.
     */
    public Vector2i negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    @Override
    protected Vector2f clone() throws CloneNotSupportedException {
        return (Vector2f) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector2i)) return false;

        Vector2i vector2i = (Vector2i) o;

        if (vector2i.x != x) return false;
        if (vector2i.y != y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vector2i{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
