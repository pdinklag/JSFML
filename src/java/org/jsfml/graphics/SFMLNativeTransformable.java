package org.jsfml.graphics;

import org.jsfml.SFMLNativeObject;
import org.jsfml.system.Vector2f;

/**
 * Decomposed transform defined by a position, a rotation and a scale.
 */
abstract class SFMLNativeTransformable extends SFMLNativeObject implements Transformable {
    /**
     * Default constructor.
     */
    protected SFMLNativeTransformable() {
        super();
    }

    @Override
    public native void setPosition(float x, float y);

    @Override
    public final void setPosition(Vector2f v) {
        setPosition(v.x, v.y);
    }

    @Override
    public native void setRotation(float angle);

    @Override
    public native void setScale(float x, float y);

    @Override
    public final void setScale(Vector2f factors) {
        setScale(factors.x, factors.y);
    }

    @Override
    public native void setOrigin(float x, float y);

    @Override
    public final void setOrigin(Vector2f v) {
        setOrigin(v.x, v.y);
    }

    @Override
    public native Vector2f getPosition();

    @Override
    public native float getRotation();

    @Override
    public native Vector2f getScale();

    @Override
    public native Vector2f getOrigin();

    @Override
    public native void move(float x, float y);

    @Override
    public final void move(Vector2f v) {
        move(v.x, v.y);
    }

    @Override
    public native void rotate(float angle);

    @Override
    public native void scale(float x, float y);

    @Override
    public final void scale(Vector2f factors) {
        scale(factors.x, factors.y);
    }

    @Override
    public native Transform getTransform();

    @Override
    public native Transform getInverseTransform();
}
