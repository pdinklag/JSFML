package org.jsfml.graphics;

import org.jsfml.Intercom;
import org.jsfml.system.Vector2f;

import java.io.Serializable;

/**
 * Defines a shape point with position, color and texture coordinates information.
 */
@Intercom
public class Vertex implements Serializable {
    private static final long serialVersionUID = -5749297453247575018L;

    @Intercom
    private Vector2f position = new Vector2f();

    @Intercom
    private Color color = new Color();

    @Intercom
    private Vector2f texCoords = new Vector2f();

    /**
     * Creates a new vertex.
     */
    public Vertex() {
    }

    /**
     * Creates a new vertex.
     *
     * @param position The vertex' position.
     */
    public Vertex(Vector2f position) {
        setPosition(position);
    }

    /**
     * Creates a new vertex.
     *
     * @param position The vertex' position.
     * @param color    The vertex' color.
     */
    public Vertex(Vector2f position, Color color) {
        setPosition(position);
        setColor(color);
    }

    /**
     * Creates a new vertex.
     *
     * @param position  The vertex' position.
     * @param texCoords The vertex' texture coordinates.
     */
    public Vertex(Vector2f position, Vector2f texCoords) {
        setPosition(position);
        setTexCoords(texCoords);
    }

    /**
     * Creates a new vertex.
     *
     * @param position  The vertex' position.
     * @param color     The vertex' color.
     * @param texCoords The vertex' texture coordinates.
     */
    public Vertex(Vector2f position, Color color, Vector2f texCoords) {
        setPosition(position);
        setColor(color);
        setTexCoords(texCoords);
    }

    /**
     * Gets the vertex' position.
     *
     * @return The vertex' position.
     */
    public Vector2f getPosition() {
        return position;
    }

    /**
     * Sets the vertex' position.
     *
     * @param position The vertex' new position.
     */
    public void setPosition(Vector2f position) {
        if (color == null)
            throw new IllegalArgumentException("A vertex position cannot be null.");

        this.position = position;
    }

    /**
     * Gets the vertex' color.
     *
     * @return The vertex' color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the vertex' color.
     *
     * @param color The vertex' new color.
     */
    public void setColor(Color color) {
        if (color == null)
            throw new IllegalArgumentException("A vertex color cannot be null.");

        this.color = color;
    }

    /**
     * Gets the vertex' texture coordinates.
     *
     * @return The vertex' texture coordinates.
     */
    public Vector2f getTexCoords() {
        return texCoords;
    }

    /**
     * Sets the vertex' texture coordinates.
     *
     * @param texCoords The vertex' new texture coordinates.
     */
    public void setTexCoords(Vector2f texCoords) {
        if (color == null)
            throw new IllegalArgumentException("A vertex tex coords cannot be null.");

        this.texCoords = texCoords;
    }
}
