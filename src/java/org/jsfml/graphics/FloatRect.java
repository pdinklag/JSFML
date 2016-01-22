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

import java.awt.Rectangle;
import java.io.Serializable;

/**
 * Represents an axis-aligned rectangle using floating point coordinates.
 */
public final strictfp class FloatRect implements Serializable {
    private static final long serialVersionUID = -8603980852893951558L;

    /**
     * An empty rectangle with no dimensions.
     */
    public static final FloatRect EMPTY = new FloatRect(0, 0, 0, 0);

    /**
     * Constructs a FloatRect from an AWT rectangle.
     *
     * @param r The {@link java.awt.Rectangle} to convert.
     * @return A FloatRect with the coordinates of the argument copied.
     */
    public static FloatRect fromAwtRectangle(Rectangle r) {
        return new FloatRect(r.x, r.y, r.width, r.height);
    }

    /**
     * The X coordinate of the rectangle's left edge.
     */
    public final float left;

    /**
     * The Y coordinate of the rectangle's top edge.
     */
    public final float top;

    /**
     * The width of the rectangle.
     */
    public final float width;

    /**
     * The height of the rectangle.
     */
    public final float height;

    /**
     * Constructs a new rectangle with the specified parameters.
     *
     * @param left   the X coordinate of the rectangle's left edge.
     * @param top    the Y coordinate of the rectangle's top edge.
     * @param width  the rectangle's width.
     * @param height the rectangle's height.
     */
    public FloatRect(float left, float top, float width, float height) {
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
    public FloatRect(Vector2f position, Vector2f size) {
        this.left = position.x;
        this.top = position.y;
        this.width = size.x;
        this.height = size.y;
    }

    /**
     * Constructs a new rectangle by converting an intergral rectangle.
     * <p/>
     * The fractions of the components will be set to zero.
     *
     * @param rect the rectangle to convert.
     */
    public FloatRect(IntRect rect) {
        this((float) rect.left, (float) rect.top, (float) rect.width, (float) rect.height);
    }

    /**
     * Tests whether a point is inside the rectangle's boundaries, including its edges.
     *
     * @param x the X coordinate of the tested point.
     * @param y the Y coordinate of the tested point.
     * @return {@code true} if the point lies within the rectangle's boundaries,
     *         {@code false} otherwise.
     */
    public boolean contains(float x, float y) {
        //direct port of SFML code
        final float minX = Math.min(left, left + width);
        final float maxX = Math.max(left, left + width);
        final float minY = Math.min(top, top + height);
        final float maxY = Math.max(top, top + height);

        return (x >= minX) && (x < maxX) && (y >= minY) && (y < maxY);
    }

    /**
     * Tests whether a point is inside the rectangle's boundaries, including its edges.
     *
     * @param point the point to be tested.
     * @return {@code true} if the point lies within the rectangle's boundaries,
     *         {@code false} otherwise.
     */
    public boolean contains(Vector2f point) {
        return contains(point.x, point.y);
    }

    /**
     * Tests whether this rectangle intersects with another rectangle and
     * calculates the rectangle of intersection.
     *
     * @param rect the rectangle to test against.
     * @return the intersection rectangle, or {@code null} if the rectangles do not intersect.
     */
    public FloatRect intersection(FloatRect rect) {
        //direct port of SFML code

        // Compute the min and max of the first rectangle on both axes
        final float r1MinX = Math.min(left, left + width);
        final float r1MaxX = Math.max(left, left + width);
        final float r1MinY = Math.min(top, top + height);
        final float r1MaxY = Math.max(top, top + height);

        // Compute the min and max of the second rectangle on both axes
        final float r2MinX = Math.min(rect.left, rect.left + rect.width);
        final float r2MaxX = Math.max(rect.left, rect.left + rect.width);
        final float r2MinY = Math.min(rect.top, rect.top + rect.height);
        final float r2MaxY = Math.max(rect.top, rect.top + rect.height);

        // Compute the intersection boundaries
        final float interLeft = Math.max(r1MinX, r2MinX);
        final float interTop = Math.max(r1MinY, r2MinY);
        final float interRight = Math.min(r1MaxX, r2MaxX);
        final float interBottom = Math.min(r1MaxY, r2MaxY);

        // If the intersection is valid (positive non zero area), then there is an intersection
        if ((interLeft < interRight) && (interTop < interBottom)) {
            return new FloatRect(
                    interLeft,
                    interTop,
                    interRight - interLeft,
                    interBottom - interTop);
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FloatRect) {
            FloatRect r = (FloatRect) o;
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
        final int ileft = left != 0.0f ? Float.floatToIntBits(left) : 0;
        final int itop = top != 0.0f ? Float.floatToIntBits(top) : 0;
        final int iwidth = width != 0.0f ? Float.floatToIntBits(width) : 0;
        final int iheight = height != 0.0f ? Float.floatToIntBits(height) : 0;
        return (ileft * 0x2F2F2F2F) ^ (itop * 0x1F1F1F1F) ^ (iwidth * 0x0F0F0F0F) ^ iheight;
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

    /**
     * Converts this FloatRect to an AWT rectangle. Coordinate values
     * are rounded by truncation (cast to int).
     *
     * @return A {@link java.awt.Rectangle} with the coordinates of this FloatRect.
     */
    public Rectangle toAwtRectangle() {
        return new Rectangle((int) left, (int) top, (int) width, (int) height);
    }
}
