/*
 This class uses direct C++ to Java translations from SFML source code, hence the following notice:

 ////////////////////////////////////////////////////////////
 //
 // SFML - Simple and Fast Multimedia Library
 // Copyright (C) 2007-2009 Laurent Gomila (laurent.gom@gmail.com)
 //
 // This software is provided 'as-is', without any express or implied warranty.
 // In no event will the authors be held liable for any damages arising from the use of this software.
 //
 // Permission is granted to anyone to use this software for any purpose,
 // including commercial applications, and to alter it and redistribute it freely,
 // subject to the following restrictions:
 //
 // 1. The origin of this software must not be misrepresented;
 //    you must not claim that you wrote the original software.
 //    If you use this software in a product, an acknowledgment
 //    in the product documentation would be appreciated but is not required.
 //
 // 2. Altered source versions must be plainly marked as such,
 //    and must not be misrepresented as being the original software.
 //
 // 3. This notice may not be removed or altered from any source distribution.
 //
 ////////////////////////////////////////////////////////////
*/

package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Defines a 3x3 transformation matrix for 2D transformations.
 */
public final strictfp class Transform implements Serializable {
    private static final long serialVersionUID = 3796964163848107663L;

    /**
     * The identity transformation, maps any vector to itself and therefore
     * "does nothing".
     */
    public final static Transform IDENTITY = new Transform();

    /**
     * Combines two transformation matrices by multiplying them.
     *
     * @param t1 the first transformation matrix.
     * @param t2 the second transformation matrix.
     * @return the product of the two given matrices.
     */
    public static Transform combine(Transform t1, Transform t2) {
        float[] a = t1.data;
        float[] b = t2.data;

        return new Transform(
                a[0] * b[0] + a[4] * b[1] + a[12] * b[3],
                a[0] * b[4] + a[4] * b[5] + a[12] * b[7],
                a[0] * b[12] + a[4] * b[13] + a[12] * b[15],
                a[1] * b[0] + a[5] * b[1] + a[13] * b[3],
                a[1] * b[4] + a[5] * b[5] + a[13] * b[7],
                a[1] * b[12] + a[5] * b[13] + a[13] * b[15],
                a[3] * b[0] + a[7] * b[1] + a[15] * b[3],
                a[3] * b[4] + a[7] * b[5] + a[15] * b[7],
                a[3] * b[12] + a[7] * b[13] + a[15] * b[15]);
    }

    /**
     * Adds a translation by a 2D vector to a transformation.
     *
     * @param t the transformation to apply the translation on.
     * @param x the X coordinate of the translation vector.
     * @param y the Y coordinate of the translation vector.
     * @return a new transformation matrix with the translation applied.
     */
    public static Transform translate(Transform t, float x, float y) {
        return combine(t, new Transform(
                1, 0, x,
                0, 1, y,
                0, 0, 1));
    }

    /**
     * Adds a translation by a 2D vector to a transformation.
     *
     * @param t the transformation to apply the translation to.
     * @param v the  translation vector.
     * @return a new transformation matrix with the translation applied.
     */
    public static Transform translate(Transform t, Vector2f v) {
        return translate(t, v.x, v.y);
    }

    /**
     * Adds a rotation around the origin to a transformation.
     *
     * @param t     the transformation to apply the rotation on.
     * @param angle the rotation angle in degrees.
     * @return a new transformation with the rotation applied
     */
    public static Transform rotate(Transform t, float angle) {
        double rad = Math.toRadians(angle);
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        return combine(t, new Transform(
                cos, -sin, 0,
                sin, cos, 0,
                0, 0, 1));
    }

    /**
     * Adds a rotation around an arbitrary center to this transformation.
     *
     * @param t       the transformation to apply the rotation on.
     * @param angle   the rotation angle in degrees.
     * @param centerX the X coordinate of the rotation center.
     * @param centerY the Y coordinate of the rotation center.
     * @return a new transformation with the rotation applied.
     */
    public static Transform rotate(Transform t, float angle, float centerX, float centerY) {
        double rad = Math.toRadians(angle);
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        return combine(t, new Transform(
                cos, -sin, centerX * (1 - cos) + centerY * sin,
                sin, cos, centerY * (1 - cos) - centerX * sin,
                0, 0, 1));
    }

    /**
     * Adds a rotation around an arbitrary center to this transformation.
     *
     * @param t      the transformation to apply the rotation on.
     * @param angle  the rotation angle in degrees.
     * @param center the rotation center.
     * @return a new transformation with the rotation applied.
     */
    public static Transform rotate(Transform t, float angle, Vector2f center) {
        return rotate(t, angle, center.x, center.y);
    }

    /**
     * Adds a scaling operation from the origin to a transformation.
     *
     * @param t      the transform to apply the scaling on.
     * @param scaleX the X factor of the scaling operation.
     * @param scaleY the Y factor of the scaling operation.
     * @return a new transformation with the scaling applied.
     */
    public static Transform scale(Transform t, float scaleX, float scaleY) {
        return combine(t, new Transform(
                scaleX, 0, 0,
                0, scaleY, 0,
                0, 0, 1));
    }

    /**
     * Adds a scaling operation from the origin to a transformation.
     *
     * @param t       the transform to apply the scaling on.
     * @param factors the factors of the scaling operation.
     * @return a new transformation with the scaling applied.
     */
    public static Transform scale(Transform t, Vector2f factors) {
        return scale(t, factors.x, factors.y);
    }

    /**
     * Adds a scaling operation from an arbitrary center to a transformation.
     *
     * @param t       the transform to apply the scaling on.
     * @param scaleX  the X factor of the scaling operation.
     * @param scaleY  the Y factor of the scaling operation.
     * @param centerX the X coordinate of the scaling center.
     * @param centerY the Y coordinate of the scaling center.
     * @return a new transformation with the scaling applied.
     */
    public static Transform scale(Transform t, float scaleX, float scaleY, float centerX, float centerY) {
        return combine(t, new Transform(
                scaleX, 0, centerX * (1 - scaleX),
                0, scaleY, centerY * (1 - scaleY),
                0, 0, 1));
    }

    /**
     * Adds a scaling operation from an arbitrary center to a transformation.
     *
     * @param t       the transform to apply the scaling on.
     * @param factors the factors of the scaling operation.
     * @param center  the scaling center.
     * @return a new transformation with the scaling applied.
     */
    public static Transform scale(Transform t, Vector2f factors, Vector2f center) {
        return scale(t, factors.x, factors.y, center.x, center.y);
    }

    private final float[] data = new float[16];

    /**
     * Constructs an identity transformation.
     */
    public Transform() {
        data[0] = 1.0f;
        data[5] = 1.0f;
        data[10] = 1.0f;
        data[15] = 1.0f;
    }

    /**
     * Constructs a new transformation from a 3x3 matrix of the following format:
     * <pre>a00, a01, a02,
     * a10, a11, a12,
     * a20, a21, a22</pre>
     *
     * @param a00 Matrix component.
     * @param a01 Matrix component.
     * @param a02 Matrix component.
     * @param a10 Matrix component.
     * @param a11 Matrix component.
     * @param a12 Matrix component.
     * @param a20 Matrix component.
     * @param a21 Matrix component.
     * @param a22 Matrix component.
     */
    public Transform(
            float a00, float a01, float a02,
            float a10, float a11, float a12,
            float a20, float a21, float a22) {
        data[0] = a00;
        data[1] = a10;
        data[3] = a20;
        data[4] = a01;
        data[5] = a11;
        data[7] = a21;
        data[10] = 1.f;
        data[12] = a02;
        data[11] = 0.f;
        data[13] = a12;
        data[15] = a22;
    }

    /**
     * Constructs a new transformation by copying another transformation.
     *
     * @param t the transformation to copy.
     */
    public Transform(Transform t) {
        System.arraycopy(t.data, 0, data, 0, 16);
    }

    /**
     * Gets a copy of the underlying 4x4 3D transformation matrix.
     *
     * @return a copy of the underlying 4x4 3D transformation matrix.
     */
    public float[] getMatrix() {
        float[] m = new float[16];
        System.arraycopy(data, 0, m, 0, 16);
        return m;
    }

    /**
     * Returns the inverse transformation.
     *
     * @return the inverse transformation,
     *         or the identity transformation if the matrix cannot be inverted.
     */
    public Transform getInverse() {
        float det = data[0] * (data[15] * data[5] - data[7] * data[13]) -
                data[1] * (data[15] * data[4] - data[7] * data[12]) +
                data[3] * (data[13] * data[4] - data[5] * data[12]);

        if (det != 0.f) {
            return new Transform(
                    (data[15] * data[5] - data[7] * data[13]) / det,
                    -(data[15] * data[4] - data[7] * data[12]) / det,
                    (data[13] * data[4] - data[5] * data[12]) / det,
                    -(data[15] * data[1] - data[3] * data[13]) / det,
                    (data[15] * data[0] - data[3] * data[12]) / det,
                    -(data[13] * data[0] - data[1] * data[12]) / det,
                    (data[7] * data[1] - data[3] * data[5]) / det,
                    -(data[7] * data[0] - data[3] * data[4]) / det,
                    (data[5] * data[0] - data[1] * data[4]) / det);
        } else {
            return new Transform();
        }
    }

    /**
     * Transforms a 2D point using the transformation matrix.
     *
     * @param x the X coordinate of the point.
     * @param y the Y coordinate of the point.
     * @return a new 2D vector, representing the transformed point.
     */
    public Vector2f transformPoint(float x, float y) {
        return new Vector2f(
                data[0] * x + data[4] * y + data[12],
                data[1] * x + data[5] * y + data[13]);
    }

    /**
     * Transforms a 2D point using the transformation matrix.
     *
     * @param v the point to transform.
     * @return a new 2D vector, representing the transformed point.
     */
    public final Vector2f transformPoint(Vector2f v) {
        return transformPoint(v.x, v.y);
    }

    /**
     * Transforms a rectangle and returns the axis-aligned bounding rectangle.
     *
     * @param rectangle the rectangle to transform.
     * @return the axis-aligned bounding rectangle of the rotated rectangle.
     */
    public FloatRect transformRect(FloatRect rectangle) {
        // Transform the 4 corners of the rectangle
        Vector2f points[] = new Vector2f[]{
                transformPoint(rectangle.left, rectangle.top),
                transformPoint(rectangle.left, rectangle.top + rectangle.height),
                transformPoint(rectangle.left + rectangle.width, rectangle.top),
                transformPoint(rectangle.left + rectangle.width, rectangle.top + rectangle.height)};

        // Compute the bounding rectangle of the transformed points
        float left = points[0].x;
        float top = points[0].y;
        float right = points[0].x;
        float bottom = points[0].y;

        for (int i = 1; i < 4; ++i) {
            if (points[i].x < left) left = points[i].x;
            else if (points[i].x > right) right = points[i].x;
            if (points[i].y < top) top = points[i].y;
            else if (points[i].y > bottom) bottom = points[i].y;
        }

        return new FloatRect(left, top, right - left, bottom - top);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Transform && Arrays.equals(data, ((Transform) o).data));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Transform{data=\n");
        int i = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                str.append(Float.toString(data[i++])).append(" ");
            }
            str.append("\n");
        }
        str.append("}");
        return str.toString();
    }
}
