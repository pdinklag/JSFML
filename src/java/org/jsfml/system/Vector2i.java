package org.jsfml.system;

import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Represents two-dimensional vectors using integer coordinates and provides
 * arithmetic operations on integral 2D vectors.
 */
@Intercom
public final class Vector2i implements Serializable {
	private static final long serialVersionUID = 4059550337913883695L;

	/**
	 * Adds two vectors.
	 *
	 * @param a the first vector.
	 * @param b the second vector.
	 * @return a new vector, representing the sum of the two vectors.
	 */
	public static Vector2i add(Vector2i a, Vector2i b) {
		return new Vector2i(a.x + b.x, a.y + b.y);
	}

	/**
	 * Subtracts two vectors.
	 *
	 * @param a The first vector.
	 * @param b The second vector.
	 * @return a new vector, representing the difference between the two vectors.
	 */
	public static Vector2i sub(Vector2i a, Vector2i b) {
		return new Vector2i(a.x - b.x, a.y - b.y);
	}


	/**
	 * Performs a component-wise multiplication of two vectors.
	 *
	 * @param a the left vector.
	 * @param b the right vector.
	 * @return a new vector, representing the "product" of the two vectors.
	 */
	public static Vector2i componentwiseMul(Vector2i a, Vector2i b) {
		return new Vector2i(a.x * b.x, a.y * b.y);
	}

	/**
	 * Performs a component-wise division of two vectors.
	 *
	 * @param a the left vector.
	 * @param b the right vector.
	 * @return a new vector, representing the "quotient" of the two vectors.
	 */
	public static Vector2i componentwiseDiv(Vector2i a, Vector2i b) {
		return new Vector2i(a.x / b.x, a.y / b.y);
	}

	/**
	 * Multiplies a vector by a scalar.
	 *
	 * @param a the vector.
	 * @param s the scalar.
	 * @return a new vector, representing the scaled vector.
	 */
	public static Vector2i mul(Vector2i a, int s) {
		return new Vector2i(a.x * s, a.y * s);
	}

	/**
	 * Multiplies a vector by the inverse of a scalar.
	 *
	 * @param a the vector.
	 * @param s the scalar to multiply by.
	 * @return a new vector, representing the scaled vector.
	 */
	public static Vector2i div(Vector2i a, int s) {
		return new Vector2i(a.x / s, a.y / s);
	}

	/**
	 * Computes the negation of a vector.
	 *
	 * @param v the vector.
	 * @return a new vector, representing the negation of the given vector.
	 */
	public static Vector2i neg(Vector2i v) {
		return new Vector2i(-v.x, -v.y);
	}

	/**
	 * The vector's X coordinate.
	 */
	@Intercom
	public final int x;

	/**
	 * The vector's Y coordinate.
	 */
	@Intercom
	public final int y;

	/**
	 * Constructs a new vector and sets its coordinates to zero.
	 */
	public Vector2i() {
		this(0, 0);
	}

	/**
	 * Constructs a new vector by copying another vector.
	 *
	 * @param v the vector to copy.
	 */
	public Vector2i(Vector2i v) {
		this.x = v.x;
		this.y = v.y;
	}

	/**
	 * Constructs a new vector by converting a floating point vector.
	 * <p/>
	 * The fractions of the coordinates will be cut off.
	 *
	 * @param v the vector to convert.
	 */
	public Vector2i(Vector2f v) {
		this((int) v.x, (int) v.y);
	}

	/**
	 * Constructs a new vector with the given coordinates.
	 *
	 * @param x the X coordinate.
	 * @param y the Y coordinate.
	 */
	@Intercom
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof Vector2i && ((Vector2i) o).x == x && ((Vector2i) o).y == y);
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
