package org.jsfml.system;

import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for manipulating 2-dimensional vectors.
 */
@Intercom
public class Vector2f implements Cloneable, Serializable {
    private static final long serialVersionUID = -2082611034304583379L;

    /**
     * Adds two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the sum of the two vectors.
     */
    public static Vector2f add(Vector2f a, Vector2f b) {
        return new Vector2f(a).add(b);
    }

    /**
     * Subtracts two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the difference between the two vectors.
     */
    public static Vector2f sub(Vector2f a, Vector2f b) {
        return new Vector2f(a).sub(b);
    }

    /**
     * Performs a component-wise multiplication of two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the "product" of the two vectors.
     */
    public static Vector2f mul(Vector2f a, Vector2f b) {
        return new Vector2f(a).mul(b);
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param a The vector.
     * @param s The scalar.
     * @return A new vector, representing the scaled vector.
     */
    public static Vector2f mul(Vector2f a, float s) {
        return new Vector2f(a).mul(s);
    }

    /**
     * Performs a vector multiplication of two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return The vector product, or dot product, of the two vectors.
     */
    public static float dot(Vector2f a, Vector2f b) {
        return a.x * b.x + a.y + b.y;
    }

    /**
     * Computes the normal of a vector.
     * @param v The vector.
     * @return A new vector, representing the normal of the given vector.
     */
    public static Vector2f normal(Vector2f v) {
        return new Vector2f(v).normalize();
    }

    /**
     * Computes the negative of a vector.
     * @param v The vector.
     * @return A new vector, representing the negative of the given vector.
     */
    public static Vector2f neg(Vector2f v) {
        return new Vector2f(v).negate();
    }

    /**
     * The vector's X coordinate.
     */
    @Intercom
    public float x = 0;

    /**
     * The vector's Y coordinate.
     */
    @Intercom
    public float y = 0;

    /**
     * Creates a new 2D vector.
     */
    public Vector2f() {
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

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add.
     * @return This vector after the addition.
     */
    public Vector2f add(Vector2f v) {
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
    public Vector2f sub(Vector2f v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * Multiplies this vector by another vector component-wise.
     *
     * @param v The vector to multiply.
     * @return This vector after the multiplication.
     */
    public Vector2f mul(Vector2f v) {
        this.x *= v.x;
        this.y *= v.y;
        return this;
    }

    /**
     * Multiplies each component of this vector by a scalar.
     *
     * @param s The scalar to multiply.
     * @return This vector after the multiplication.
     */
    public Vector2f mul(float s) {
        this.x *= s;
        this.y *= s;
        return this;
    }

    /**
     * Normalizes this vector.
     *
     * @return This vector after the normalization.
     */
    public Vector2f normalize() {
        float l = length();
        if (l != 0.0f) {
            this.x /= l;
            this.y /= l;
        }
        return this;
    }

    /**
     * Negates this vector.
     *
     * @return This vector after the negation.
     */
    public Vector2f negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    /**
     * Computes the squared length of this vector.
     *
     * @return The squared length of this vector.
     */
    public float lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }

    /**
     * Returns the length of this vector.
     *
     * @return The length of this vector.
     */
    public float length() {
        return (float) Math.sqrt(this.lengthSquared());
    }

    @Override
    protected Vector2f clone() throws CloneNotSupportedException {
        return (Vector2f) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector2f)) return false;

        Vector2f vector2f = (Vector2f) o;

        if (Float.compare(vector2f.x, x) != 0) return false;
        if (Float.compare(vector2f.y, y) != 0) return false;

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
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
