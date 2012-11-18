package org.jsfml.system;

import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for 3-dimensional integer vectors.
 */
public final class Vector3i implements Serializable {
	private static final long serialVersionUID = -2260992069088589367L;

	/**
	 * Adds two vectors.
	 *
	 * @param a The left vector.
	 * @param b The right vector.
	 * @return A new vector, representing the sum of the two vectors.
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
	 * @param a The left vector.
	 * @param b The right vector.
	 * @return A new vector, representing the difference between the two vectors.
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
	 * @param a The left vector.
	 * @param b The right vector.
	 * @return A new vector, representing the "product" of the two vectors.
	 */
	public static Vector3i componentwiseMul(Vector3i a, Vector3i b) {
		return new Vector3i(
				a.x * b.x,
				a.y * b.y,
				a.z * b.z);
	}

	/**
	 * Performs a component-wise integer division of two vectors.
	 *
	 * @param a The left vector.
	 * @param b The right vector.
	 * @return A new vector, representing the "quotient" of the two vectors.
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
	 * @param a The vector.
	 * @param s The scalar.
	 * @return A new vector, representing the scaled vector.
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
	 * @param a The vector.
	 * @param s The scalar.
	 * @return A new vector, representing the scaled vector.
	 */
	public static Vector3i div(Vector3i a, int s) {
		return new Vector3i(
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
	public static Vector3i neg(Vector3i v) {
		return new Vector3i(-v.x, -v.y, -v.z);
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
	 * The vector's Z coordinate.
	 */
	@Intercom
	public final int z;

	/**
	 * Creates a new 3D vector.
	 */
	public Vector3i() {
		this(0, 0, 0);
	}

	/**
	 * Creates a new 3D vector as a copy of another vector.
	 *
	 * @param v The vector to copy.
	 */
	public Vector3i(Vector3i v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}

	/**
	 * Creates a new 3D vector from a floating point vector.
	 * The fractions of the x, y and z components will be removed in the process.
	 *
	 * @param v The vector to convert.
	 */
	public Vector3i(Vector3f v) {
		this((int) v.x, (int) v.y, (int) v.z);
	}

	/**
	 * Creates a new 3D vector with the given coordinates.
	 *
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 * @param z The Z coordinate.
	 */
	@Intercom
	public Vector3i(int x, int y, int z) {
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
