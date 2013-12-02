package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

/**
 * Implementation of the {@link Transformable} interface.
 * <p/>
 * Classes can inherit from this in order to provide the {@link Transformable} interface.
 * The implementation equals that of the original SFML implementation.
 */
public class BasicTransformable implements Transformable {
    private Vector2f origin = Vector2f.ZERO;
    private Vector2f position = Vector2f.ZERO;
    private float rotation = 0;
    private Vector2f scale = new Vector2f(1, 1);

    private Transform transform = Transform.IDENTITY;
    private boolean transformNeedsUpdate = false;
    private Transform inverseTransform = Transform.IDENTITY;
    private boolean inverseTransformNeedsUpdate = false;

    /**
     * Default constructor, initializes this object with an identity transformation.
     */
    public BasicTransformable() {
    }

    @Override
    public final void setPosition(float x, float y) {
        setPosition(new Vector2f(x, y));
    }

    @Override
    public void setPosition(Vector2f v) {
        this.position = v;

        transformNeedsUpdate = true;
        inverseTransformNeedsUpdate = true;
    }

    @Override
    public void setRotation(float angle) {
        this.rotation = angle % 360.0f;
        if (this.rotation < 0)
            this.rotation += 360.0f;

        transformNeedsUpdate = true;
        inverseTransformNeedsUpdate = true;
    }

    @Override
    public final void setScale(float x, float y) {
        setScale(new Vector2f(x, y));
    }

    @Override
    public void setScale(Vector2f factors) {
        this.scale = factors;

        transformNeedsUpdate = true;
        inverseTransformNeedsUpdate = true;
    }

    @Override
    public final void setOrigin(float x, float y) {
        setOrigin(new Vector2f(x, y));
    }

    @Override
    public void setOrigin(Vector2f v) {
        this.origin = v;

        transformNeedsUpdate = true;
        inverseTransformNeedsUpdate = true;
    }

    @Override
    public Vector2f getPosition() {
        return position;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public Vector2f getScale() {
        return scale;
    }

    @Override
    public Vector2f getOrigin() {
        return origin;
    }

    @Override
    public final void move(float x, float y) {
        move(new Vector2f(x, y));
    }

    @Override
    public void move(Vector2f v) {
        setPosition(Vector2f.add(position, v));
    }

    @Override
    public void rotate(float angle) {
        setRotation(rotation + angle);
    }

    @Override
    public final void scale(float x, float y) {
        scale(new Vector2f(x, y));
    }

    @Override
    public void scale(Vector2f factors) {
        setScale(Vector2f.componentwiseMul(scale, factors));
    }

    @Override
    public Transform getTransform() {
        if (transformNeedsUpdate) {
            double angle = -Math.toRadians(rotation);
            float cos = (float) Math.cos(angle);
            float sin = (float) Math.sin(angle);

            float sxc = scale.x * cos;
            float syc = scale.y * cos;
            float sxs = scale.x * sin;
            float sys = scale.y * sin;
            float tx = -origin.x * sxc - origin.y * sys + position.x;
            float ty = origin.x * sxs - origin.y * syc + position.y;

            transform = new Transform(
                    sxc, sys, tx,
                    -sxs, syc, ty,
                    0, 0, 1);

            transformNeedsUpdate = false;
        }

        return transform;
    }

    @Override
    public Transform getInverseTransform() {
        if (inverseTransformNeedsUpdate) {
            inverseTransform = getTransform().getInverse();
            inverseTransformNeedsUpdate = false;
        }

        return inverseTransform;
    }
}
