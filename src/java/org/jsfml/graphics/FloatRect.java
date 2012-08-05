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

import org.jsfml.Intercom;
import org.jsfml.system.Vector2f;

import java.io.Serializable;

/**
 * Utility class for manipulating 2D axis aligned rectangles.
 */
@Intercom
public class FloatRect implements Serializable {
    private static final long serialVersionUID = -8603980852893951558L;

    /**
     * Left (X) coordinate of the rectangle.
     */
    @Intercom
    public float left;

    /**
     * Top (Y) coordinate of the rectangle.
     */
    @Intercom
    public float top;

    /**
     * The width of the rectangle.
     */
    @Intercom
    public float width;

    /**
     * The height of the rectangle.
     */
    @Intercom
    public float height;

    /**
     * Creates a new rectangle with no dimensions.
     */
    public FloatRect() {
    }

    /**
     * Creates a new rectangle.
     *
     * @param left   The rectangle's left coordinate.
     * @param top    The rectangle's top coordinate.
     * @param width  The rectangle's width.
     * @param height The rectangle's height.
     */
    @Intercom
    public FloatRect(float left, float top, float width, float height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new rectangle.
     *
     * @param position The rectangle's position.
     * @param size     The rectangle's size.
     */
    public FloatRect(Vector2f position, Vector2f size) {
        this.left = position.x;
        this.top = position.y;
        this.width = size.x;
        this.height = size.y;
    }

    /**
     * Creates a new rectangle as a copy of another rectangle.
     *
     * @param rect The rectangle to copy.
     */
    public FloatRect(FloatRect rect) {
        this.left = rect.left;
        this.top = rect.top;
        this.width = rect.width;
        this.height = rect.height;
    }

    /**
     * Creates a new rectangle from an integer rectangle.
     * The fractions of the components will be set to zero.
     *
     * @param rect The rectangle to convert.
     */
    public FloatRect(IntRect rect) {
        this((float) rect.left, (float) rect.top, (float) rect.width, (float) rect.height);
    }

    /**
     * Tests whether a point is inside the rectangle's area, including its edges.
     *
     * @param x The X coordinate of the tested point.
     * @param y The Y coordinate of the tested point.
     * @return <tt>true</tt> if the point lies within the rectangle's area, <tt>false</tt> otherwise.
     */
    public boolean contains(float x, float y) {
        return (x >= left) && (x < left + width) && (y >= top) && (y < top + height);
    }

    /**
     * Tests whether a point is inside the rectangle's area, including its edges.
     *
     * @param point The point to be tested.
     * @return <tt>true</tt> if the point lies within the rectangle's area, <tt>false</tt> otherwise.
     */
    public boolean contains(Vector2f point) {
        return contains(point.x, point.y);
    }

    /**
     * Tests whether two rectangles intersect.
     *
     * @param rect         The rectangle to test intersection against.
     * @param intersection If not <tt>null</tt>, the overlapping area will be written into this object.
     * @return <tt>true</tt> if the two rectangles overlap, <tt>false</tt> otherwise.
     */
    public boolean intersects(FloatRect rect, FloatRect intersection) {
        float left = Math.max(this.left, rect.left);
        float top = Math.max(this.top, rect.top);
        float right = Math.min(this.left + this.width, rect.left + rect.width);
        float bottom = Math.min(this.top + this.height, rect.top + rect.height);

        if (left < right && top < bottom) {
            if (intersection != null) {
                intersection.left = left;
                intersection.top = top;
                intersection.width = right - left;
                intersection.height = bottom - top;
            }
            return true;
        } else {
            if (intersection != null) {
                intersection.left = 0;
                intersection.top = 0;
                intersection.width = 0;
                intersection.height = 0;
            }
            return false;
        }
    }

    /**
     * Tests whether two rectangles intersect.
     *
     * @param rect The rectangle to test intersection against.
     * @return <tt>true</tt> if the two rectangles overlap, <tt>false</tt> otherwise.
     */
    public boolean intersects(FloatRect rect) {
        return intersects(rect, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FloatRect)) return false;

        FloatRect floatRect = (FloatRect) o;

        if (Float.compare(floatRect.height, height) != 0) return false;
        if (Float.compare(floatRect.left, left) != 0) return false;
        if (Float.compare(floatRect.top, top) != 0) return false;
        if (Float.compare(floatRect.width, width) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (left != +0.0f ? Float.floatToIntBits(left) : 0);
        result = 31 * result + (top != +0.0f ? Float.floatToIntBits(top) : 0);
        result = 31 * result + (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FloatRect{" +
                "left=" + left +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
