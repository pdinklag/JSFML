package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

/**
 * Interface for transformable objects that can be positioned, rotated and scaled around an
 * origin.
 */
public interface Transformable {
    /**
     * Sets the position of this object so that its origin will be exactly on it.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public void setPosition(float x, float y);

    /**
     * Sets the position of this object so that its origin will be exactly on it.
     *
     * @param v The new position.
     */
    public void setPosition(Vector2f v);

    /**
     * Sets the rotation of this object around its origin.
     *
     * @param angle The new rotation angle in degrees.
     */
    public void setRotation(float angle);

    /**
     * Sets the scaling of this object, using its origin as the scaling center.
     *
     * @param x The X scaling factor.
     * @param y The Y scaling factor.
     */
    public void setScale(float x, float y);

    /**
     * Sets the scaling of this object, using its origin as the scaling center.
     *
     * @param factors The new scaling factors.
     */
    public void setScale(Vector2f factors);

    /**
     * Sets the rotation, scaling and drawing origin of this object.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public void setOrigin(float x, float y);

    /**
     * Sets the rotation, scaling and drawing origin of this object.
     *
     * @param v The new origin.
     */
    public void setOrigin(Vector2f v);

    /**
     * Gets the position of this object.
     *
     * @return The current position.
     */
    public Vector2f getPosition();

    /**
     * Gets the rotation angle of this object.
     *
     * @return The current rotation angle in degrees.
     */
    public float getRotation();

    /**
     * Gets the scaling of this object.
     *
     * @return The current scaling factors.
     */
    public Vector2f getScale();

    /**
     * Gets the origin of this object.
     *
     * @return The current origin.
     */
    public Vector2f getOrigin();

    /**
     * Moves this object.
     *
     * @param x The X offset added to the current position.
     * @param y The Y offset added to the current position.
     */
    public void move(float x, float y);

    /**
     * Moves the object.
     *
     * @param v The offset vector added to the current position.
     */
    public void move(Vector2f v);

    /**
     * Rotates this object around its origin.
     *
     * @param angle The rotation angle in degrees.
     */
    public void rotate(float angle);

    /**
     * Scales the object, using its origin as the scaling center.
     *
     * @param x The X scaling factor.
     * @param y The Y scaling factor.
     */
    public void scale(float x, float y);

    /**
     * Scales the object, using its origin as the scaling center.
     *
     * @param factors The scaling factors.
     */
    public void scale(Vector2f factors);

    /**
     * Gets the current transform matrix.
     *
     * @return The current transform.
     */
    public Transform getTransform();

    /**
     * Gets the inverse of the current transform matrix.
     *
     * @return The inverse of the current transform.
     */
    public Transform getInverseTransform();
}
