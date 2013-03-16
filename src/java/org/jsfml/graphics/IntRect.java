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

import org.jsfml.system.Vector2i;

import java.io.Serializable;

/**
 * Represents an axis-aligned rectangle using integer coordinates.
 */
public final class IntRect implements Serializable {
    private static final long serialVersionUID = -4430448425788537785L;

    /**
     * An empty rectangle with no dimensions.
     */
    public static final IntRect EMPTY = new IntRect(0, 0, 0, 0);

    /**
     * The X coordinate of the rectangle's left edge.
     */
    public final int left;

    /**
     * The Y coordinate of the rectangle's top edge.
     */
    public final int top;

    /**
     * The width of the rectangle.
     */
    public final int width;

    /**
     * The height of the rectangle.
     */
    public final int height;

    /**
     * Constructs a new rectangle with the specified parameters.
     *
     * @param left   the X coordinate of the rectangle's left edge.
     * @param top    the Y coordinate of the rectangle's top edge.
     * @param width  the rectangle's width.
     * @param height the rectangle's height.
     */
    public IntRect(int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs a new rectangle with the specified parameters.
     *
     * @param position the position of the rectangle's top left corner.
     * @param size     the rectangle's dimension.
     */
    public IntRect(Vector2i position, Vector2i size) {
        this.left = position.x;
        this.top = position.y;
        this.width = size.x;
        this.height = size.y;
    }

    /**
     * Constructs a new rectangle by converting a floating point rectangle.
     * <p/>
     * The fractions of the components will be removed.
     *
     * @param rect the rectangle to convert.
     */
    public IntRect(FloatRect rect) {
        this((int) rect.left, (int) rect.top, (int) rect.width, (int) rect.height);
    }

    /**
     * Tests whether a point is inside the rectangle's boundaries, including its edges.
     *
     * @param x the X coordinate of the tested point.
     * @param y the Y coordinate of the tested point.
     * @return {@code true} if the point lies within the rectangle's boundaries,
     *         {@code false} otherwise.
     */
    public boolean contains(int x, int y) {
        return (x >= left) && (x < left + width) && (y >= top) && (y < top + height);
    }

    /**
     * Tests whether a point is inside the rectangle's boundaries, including its edges.
     *
     * @param point the point to be tested.
     * @return {@code true} if the point lies within the rectangle's boundaries,
     *         {@code false} otherwise.
     */
    public boolean contains(Vector2i point) {
        return contains(point.x, point.y);
    }

    /**
     * Tests whether this rectangle intersects with another rectangle and
     * calculates the rectangle of intersection.
     *
     * @param rect the rectangle to test against.
     * @return the intersection rectangle, or {@code null} if the rectangles do not intersect.
     */
    public IntRect intersection(IntRect rect) {
        int left = Math.max(this.left, rect.left);
        int top = Math.max(this.top, rect.top);
        int right = Math.min(this.left + this.width, rect.left + rect.width);
        int bottom = Math.min(this.top + this.height, rect.top + rect.height);

        if (left < right && top < bottom) {
            return new IntRect(left, top, right - left, bottom - top);
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IntRect) {
            IntRect r = (IntRect) o;
            return (left == r.left &&
                    top == r.top &&
                    width == r.width &&
                    height == r.height);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = left;
        result = 31 * result + top;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "IntRect{" +
                "left=" + left +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
