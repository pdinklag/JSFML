package org.jsfml.graphics;

import org.jsfml.SFMLNativeObject;
import org.jsfml.system.Vector2f;

/**
 * Decomposed transform defined by a position, a rotation and a scale.
 */
public abstract class Transformable extends SFMLNativeObject {
    /**
     * Default constructor.
     */
    protected Transformable() {
        super();
    }

    /**
     * Sets the position of this object.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public abstract void setPosition(float x, float y);

    /**
     * Sets the position of this object.
     *
     * @param v The new position.
     */
    public void setPosition(Vector2f v) {
        setPosition(v.x, v.y);
    }

    /**
     * Sets the rotation of this object.
     *
     * @param angle The new rotation in degrees.
     */
    public abstract void setRotation(float angle);

    /**
     * Sets the scaling of this object.
     *
     * @param x The X scaling factor.
     * @param y The Y scaling factor.
     */
    public abstract void setScale(float x, float y);

    /**
     * Sets the scaling of this object.
     *
     * @param factors The new scaling factors.
     */
    public void setScale(Vector2f factors) {
        setScale(factors.x, factors.y);
    }

    /**
     * Sets the rotation, scaling and drawing origin of this object.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public abstract void setOrigin(float x, float y);

    /**
     * Sets the rotation, scaling and drawing origin of this object.
     *
     * @param v The new origin.
     */
    public void setOrigin(Vector2f v) {
        setOrigin(v.x, v.y);
    }

    /**
     * Gets the position of this object.
     *
     * @return The current position.
     */
    public abstract Vector2f getPosition();

    /**
     * Gets the rotation angle of this object.
     *
     * @return The current rotation angle in degrees.
     */
    public abstract float getRotation();

    /**
     * Gets the scaling of this object.
     *
     * @return The current scaling factors.
     */
    public abstract Vector2f getScale();

    /**
     * Gets the origin of this object.
     *
     * @return The current origin.
     */
    public abstract Vector2f getOrigin();

    /**
     * Moves this object.
     *
     * @param x The X offset.
     * @param y The Y offset.
     */
    public abstract void move(float x, float y);

    /**
     * Moves this object.
     *
     * @param v The offset vector.
     */
    public void move(Vector2f v) {
        move(v.x, v.y);
    }

    /**
     * Rotates this object.
     *
     * @param angle The rotation angle in degrees.
     */
    public abstract void rotate(float angle);

    /**
     * Scales this object.
     *
     * @param x The X scaling factor.
     * @param y The Y scaling factor.
     */
    public abstract void scale(float x, float y);

    /**
     * Scales this object.
     *
     * @param factors The scaling factors.
     */
    public void scale(Vector2f factors) {
        scale(factors.x, factors.y);
    }

    /**
     * Gets the current transform matrix.
     *
     * @return The current transform.
     */
    public abstract Transform getTransform();

    /**
     * Gets the inverse of the current transform matrix.
     *
     * @return The inverse of the current transform.
     */
    public abstract Transform getInverseTransform();
}
