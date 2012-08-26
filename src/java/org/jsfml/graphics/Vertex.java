package org.jsfml.graphics;

import org.jsfml.Intercom;
import org.jsfml.system.Vector2f;

import java.io.Serializable;

/**
 * Defines a shape point with position, color and texture coordinates information.
 */
@Intercom
public final class Vertex implements Serializable {
    private static final long serialVersionUID = -5749297453247575018L;

    @Intercom
    private Vector2f position;

    @Intercom
    private Color color;

    @Intercom
    private Vector2f texCoords;

    /**
     * Creates a new vertex.
     */
    public Vertex() {
        this(new Vector2f(), new Color(), new Vector2f());
    }

    /**
     * Creates a new vertex.
     *
     * @param position The vertex' position.
     */
    public Vertex(Vector2f position) {
        this(position, new Color(), new Vector2f());
    }

    /**
     * Creates a new vertex.
     *
     * @param position The vertex' position.
     * @param color    The vertex' color.
     */
    public Vertex(Vector2f position, Color color) {
        this(position, color, new Vector2f());
    }

    /**
     * Creates a new vertex.
     *
     * @param position  The vertex' position.
     * @param texCoords The vertex' texture coordinates.
     */
    public Vertex(Vector2f position, Vector2f texCoords) {
        this(position, new Color(), texCoords);
    }

    /**
     * Creates a new vertex.
     *
     * @param position  The vertex' position.
     * @param color     The vertex' color.
     * @param texCoords The vertex' texture coordinates.
     */
    public Vertex(Vector2f position, Color color, Vector2f texCoords) {
        this.position = position;
        this.color = color;
        this.texCoords = texCoords;
    }

    /**
     * Gets the vertex position.
     *
     * @return The vertex position.
     */
    public Vector2f getPosition() {
        return position;
    }

    /**
     * Gets the vertex color.
     *
     * @return The vertex color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the vertex texture coordinates.
     *
     * @return The vertex texture coordinates.
     */
    public Vector2f getTexCoords() {
        return texCoords;
    }

    /**
     * Sets the vertex color.
     *
     * @param color The new vertex color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the vertex position.
     *
     * @param position The new vertex position.
     */
    public void setPosition(Vector2f position) {
        this.position = position;
    }

    /**
     * Sets the vertex texture coordinates.
     *
     * @param texCoords The new vertex texture coordinates.
     */
    public void setTexCoords(Vector2f texCoords) {
        this.texCoords = texCoords;
    }
}
