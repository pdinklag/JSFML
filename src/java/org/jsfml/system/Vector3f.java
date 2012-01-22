package org.jsfml.system;

import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for manipulating 3-dimensional vectors.
 */
@Intercom
public class Vector3f implements Cloneable, Serializable {
    private static final long serialVersionUID = -2176250005619169432L;

    /**
     * Adds two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the sum of the two vectors.
     */
    public static Vector3f add(Vector3f a, Vector3f b) {
        return new Vector3f(a).add(b);
    }

    /**
     * Subtracts two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the difference between the two vectors.
     */
    public static Vector3f sub(Vector3f a, Vector3f b) {
        return new Vector3f(a).sub(b);
    }

    /**
     * Performs a component-wise multiplication of two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the "product" of the two vectors.
     */
    public static Vector3f componentwiseMul(Vector3f a, Vector3f b) {
        return new Vector3f(a).componentwiseMul(b);
    }

    /**
     * Performs a component-wise division of two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the "quotient" of the two vectors.
     */
    public static Vector3f componentwiseDiv(Vector3f a, Vector3f b) {
        return new Vector3f(a).componentwiseDiv(b);
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param a The vector.
     * @param s The scalar.
     * @return A new vector, representing the scaled vector.
     */
    public static Vector3f mul(Vector3f a, float s) {
        return new Vector3f(a).mul(s);
    }

    /**
     * Multiplies a vector by the inverse of a scalar.
     *
     * @param a The vector.
     * @param s The scalar.
     * @return A new vector, representing the scaled vector.
     */
    public static Vector3f div(Vector3f a, float s) {
        return new Vector3f(a).div(s);
    }

    /**
     * Performs a vector multiplication of two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return The vector product, or dot product, of the two vectors.
     */
    public static float dot(Vector3f a, Vector3f b) {
        return a.x * b.x + a.y + b.y;
    }

    /**
     * Computes the cross product between two vectors.
     *
     * @param a The left vector.
     * @param b The right vector.
     * @return A new vector, representing the cross product of the two vectors.
     */
    public static Vector3f cross(Vector3f a, Vector3f b) {
        return new Vector3f(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x
        );
    }

    /**
     * Computes the normal of a vector.
     *
     * @param v The vector.
     * @return A new vector, representing the normal of the given vector.
     */
    public static Vector3f normal(Vector3f v) {
        return new Vector3f(v).normalize();
    }

    /**
     * Computes the negative of a vector.
     *
     * @param v The vector.
     * @return A new vector, representing the negative of the given vector.
     */
    public static Vector3f neg(Vector3f v) {
        return new Vector3f(v).negate();
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
     * The vector's Z coordinate.
     */
    @Intercom
    public float z = 0;

    /**
     * Creates a new 3D vector.
     */
    public Vector3f() {
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

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add.
     * @return This vector after the addition.
     */
    public Vector3f add(Vector3f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        return this;
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract.
     * @return This vector after the subtraction.
     */
    public Vector3f sub(Vector3f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        return this;
    }

    /**
     * Multiplies this vector by another vector component-wise.
     *
     * @param v The vector to multiply.
     * @return This vector after the multiplication.
     */
    public Vector3f componentwiseMul(Vector3f v) {
        this.x *= v.x;
        this.y *= v.y;
        this.z *= v.z;
        return this;
    }

    /**
     * Divides this vector by another vector component-wise.
     *
     * @param v The vector to multiply.
     * @return This vector after the multiplication.
     */
    public Vector3f componentwiseDiv(Vector3f v) {
        if (v.x == 0 || v.y == 0 || v.z == 0)
            throw new IllegalArgumentException("Division by zero.");

        this.x /= v.x;
        this.y /= v.y;
        this.z /= v.z;
        return this;
    }

    /**
     * Multiplies each component of this vector by a scalar.
     *
     * @param s The scalar to multiply.
     * @return This vector after the multiplication.
     */
    public Vector3f mul(float s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
        return this;
    }

    /**
     * Multiplies each component of this vector by the inverse of a scalar.
     *
     * @param s The scalar to divide by.
     * @return This vector after the division.
     */
    public Vector3f div(float s) {
        if (s == 0)
            throw new IllegalArgumentException("Division by zero.");

        this.x /= s;
        this.y /= s;
        this.z /= s;
        return this;
    }

    /**
     * Normalizes this vector.
     *
     * @return This vector after the normalization.
     */
    public Vector3f normalize() {
        float l = length();
        if (l != 0.0f) {
            this.x /= l;
            this.y /= l;
            this.z /= l;
        }
        return this;
    }

    /**
     * Negates this vector.
     *
     * @return This vector after the negation.
     */
    public Vector3f negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    /**
     * Computes the squared length of this vector.
     *
     * @return The squared length of this vector.
     */
    public float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
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
    protected Vector3f clone() throws CloneNotSupportedException {
        return (Vector3f) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector3f)) return false;

        Vector3f vector3f = (Vector3f) o;

        if (Float.compare(vector3f.x, x) != 0) return false;
        if (Float.compare(vector3f.y, y) != 0) return false;
        if (Float.compare(vector3f.z, z) != 0) return false;

        return true;
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
