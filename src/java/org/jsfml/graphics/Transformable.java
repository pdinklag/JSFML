package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

/**
 * Interface for transformable objects that can be positioned in the scene and
 * rotated and scaled around an origin.
 * <p/>
 * An implementation of this interface is provided by the {@link BasicTransformable} class.
 */
public interface Transformable {
    /**
     * Sets the position of this object in the scene so that its origin will be exactly on it.
     *
     * @param x the new X coordinate.
     * @param y the new Y coordinate.
     */
    public void setPosition(float x, float y);

    /**
     * Sets the position of this object in the scene so that its origin will be exactly on it.
     *
     * @param v the new position.
     */
    public void setPosition(Vector2f v);

    /**
     * Sets the rotation of this object around its origin.
     *
     * @param angle the new rotation angle in degrees.
     */
    public void setRotation(float angle);

    /**
     * Sets the scaling of this object, using its origin as the scaling center.
     *
     * @param x the new X scaling factor.
     * @param y the new Y scaling factor.
     */
    public void setScale(float x, float y);

    /**
     * Sets the scaling of this object, using its origin as the scaling center.
     *
     * @param factors the new scaling factors.
     */
    public void setScale(Vector2f factors);

    /**
     * Sets the rotation, scaling and drawing origin of this object.
     *
     * @param x the new X coordinate of the origin.
     * @param y the new Y coordinate of the origin.
     */
    public void setOrigin(float x, float y);

    /**
     * Sets the rotation, scaling and drawing origin of this object.
     *
     * @param v the new origin.
     */
    public void setOrigin(Vector2f v);

    /**
     * Gets the position of this object.
     *
     * @return the current position.
     */
    public Vector2f getPosition();

    /**
     * Gets the rotation angle of this object.
     *
     * @return the current rotation angle in degrees.
     */
    public float getRotation();

    /**
     * Gets the scaling of this object.
     *
     * @return the current scaling factors.
     */
    public Vector2f getScale();

    /**
     * Gets the origin of this object.
     *
     * @return the current origin.
     */
    public Vector2f getOrigin();

    /**
     * Moves this object.
     *
     * @param x the X offset added to the current position.
     * @param y the Y offset added to the current position.
     */
    public void move(float x, float y);

    /**
     * Moves the object.
     *
     * @param v the offset vector added to the current position.
     */
    public void move(Vector2f v);

    /**
     * Rotates this object around its origin.
     *
     * @param angle the rotation angle in degrees.
     */
    public void rotate(float angle);

    /**
     * Scales the object, using its origin as the scaling center.
     *
     * @param x the X scaling factor.
     * @param y the Y scaling factor.
     */
    public void scale(float x, float y);

    /**
     * Scales the object, using its origin as the scaling center.
     *
     * @param factors the scaling factors.
     */
    public void scale(Vector2f factors);

    /**
     * Gets the current transformation matrix.
     *
     * @return the current transformation.
     */
    public Transform getTransform();

    /**
     * Gets the inverse of the current transformation matrix.
     *
     * @return the inverse of the current transform.
     */
    public Transform getInverseTransform();
}
