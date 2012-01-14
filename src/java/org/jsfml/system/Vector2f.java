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
