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
