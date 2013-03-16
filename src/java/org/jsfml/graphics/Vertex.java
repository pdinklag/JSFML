package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

import java.io.Serializable;

/**
 * Defines a shape point with position, color and texture coordinates information.
 */
public final class Vertex implements Serializable {
    private static final long serialVersionUID = -5749297453247575018L;

    /**
     * The vertex position.
     */
    public final Vector2f position;

    /**
     * The vertex color.
     */
    public final Color color;

    /**
     * The vertex texture coordinates.
     * <p/>
     * The unit space this information is measured in depends on the way the respective
     * texture will be bound. By the default, the {@link Texture.CoordinateType#NORMALIZED}
     * coordinate type is used.
     */
    public final Vector2f texCoords;

    /**
     * Constructs a new vertex at the specified position with white color.
     *
     * @param position the vertex' position.
     */
    public Vertex(Vector2f position) {
        this(position, Color.WHITE, Vector2f.ZERO);
    }

    /**
     * Constructs a new vertex with the specified position and color.
     *
     * @param position the vertex' position.
     * @param color    the vertex' color.
     */
    public Vertex(Vector2f position, Color color) {
        this(position, color, Vector2f.ZERO);
    }

    /**
     * Constructs a new vertex with the specified position and texture coordinates, with white color.
     *
     * @param position  the vertex' position.
     * @param texCoords the vertex' texture coordinates.
     */
    public Vertex(Vector2f position, Vector2f texCoords) {
        this(position, Color.WHITE, texCoords);
    }

    /**
     * Constructs a new vertex with the specified parameters.
     *
     * @param position  the vertex' position.
     * @param color     the vertex' color.
     * @param texCoords the vertex' texture coordinates.
     */
    public Vertex(Vector2f position, Color color, Vector2f texCoords) {
        this.position = position;
        this.color = color;
        this.texCoords = texCoords;
    }
}
