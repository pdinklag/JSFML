package org.jsfml.system;

import java.io.Serializable;

public class Vector2i implements Cloneable, Serializable {
    private static final long serialVersionUID = 4059550337913883695L;

    /**
     * The vector's X coordinate.
     */
    public int x = 0;

    /**
     * The vector's Y coordinate.
     */
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
     * Creates a new 2D vector with the given coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public Vector2i(int x, int y) {
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
