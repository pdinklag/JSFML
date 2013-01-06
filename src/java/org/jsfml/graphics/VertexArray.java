package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

import java.util.ArrayList;

/**
 * Defines a drawable set of one or multiple 2D primitives.
 */
public class VertexArray extends ArrayList<Vertex> implements Drawable {
    private static final long serialVersionUID = 4656221909265000727L;

    private PrimitiveType primitiveType;

    /**
     * Constructs a new empty vertex array using the {@link PrimitiveType#POINTS} type.
     */
    public VertexArray() {
        this(PrimitiveType.POINTS);
    }

    /**
     * Constructs a new empty vertex array.
     *
     * @param primitiveType The type of primitives drawn by this vertex array.
     */
    public VertexArray(PrimitiveType primitiveType) {
        super(4);

        this.primitiveType = primitiveType;
    }

    /**
     * Gets the type of primitives drawn by this vertex array.
     *
     * @return the type of primitives drawn by this vertex array.
     */
    public PrimitiveType getPrimitiveType() {
        return primitiveType;
    }

    /**
     * Sets the type of primitives drawn by this vertex array.
     *
     * @param primitiveType the type of primitives drawn by this vertex array.
     */
    public void setPrimitiveType(PrimitiveType primitiveType) {
        this.primitiveType = primitiveType;
    }

    /**
     * Computes the axis-aligned bounding box of this vertex array.
     *
     * @return the axis-aligned bounding box of this vertex array.
     */
    public FloatRect getBounds() {
        if (!isEmpty()) {
            Vector2f v = get(0).position;
            float left = v.x;
            float top = v.y;
            float right = v.x;
            float bottom = v.y;

            for (int i = 1; i < size(); i++) {
                v = get(i).position;

                if (v.x < left)
                    left = v.x;
                else if (v.x > right)
                    right = v.x;

                if (v.y < top)
                    top = v.y;
                else if (v.y > bottom)
                    bottom = v.y;
            }

            return new FloatRect(left, top, right - left, bottom - top);
        } else {
            return FloatRect.EMPTY;
        }
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        if (!isEmpty()) {
            target.draw(toArray(new Vertex[size()]), primitiveType, states);
        }
    }
}
