package org.jsfml.graphics;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.internal.SFMLNativeObject;
import org.jsfml.system.Vector2f;

import java.nio.Buffer;
import java.util.Objects;

/**
 * Decomposed transform defined by a position, a rotation and a scale.
 */
abstract class SFMLNativeTransformable extends SFMLNativeObject implements Transformable {
    //cache
    private Vector2f position = Vector2f.ZERO;
    private float rotation = 0;
    private Vector2f scale = new Vector2f(1, 1);
    private Vector2f origin = Vector2f.ZERO;

    private boolean transformNeedsUpdate = true;
    private Transform transformCache = null;
    private Transform inverseTransformCache = null;

    protected SFMLNativeTransformable() {
        super();
    }

    private native void nativeSetPosition(float x, float y);

    @Override
    public final void setPosition(float x, float y) {
        setPosition(new Vector2f(x, y));
    }

    @Override
    public void setPosition(Vector2f v) {
        position = Objects.requireNonNull(v);
        nativeSetPosition(v.x, v.y);
        transformNeedsUpdate = true;
    }

    private native void nativeSetRotation(float angle);

    @Override
    public void setRotation(float angle) {
        rotation = angle;
        nativeSetRotation(angle);
        transformNeedsUpdate = true;
    }

    private native void nativeSetScale(float x, float y);

    @Override
    public final void setScale(float x, float y) {
        setScale(new Vector2f(x, y));
    }

    @Override
    public void setScale(Vector2f v) {
        scale = Objects.requireNonNull(v);
        nativeSetScale(v.x, v.y);
        transformNeedsUpdate = true;
    }

    private native void nativeSetOrigin(float x, float y);

    @Override
    public final void setOrigin(float x, float y) {
        setOrigin(new Vector2f(x, y));
    }

    @Override
    public void setOrigin(Vector2f v) {
        origin = Objects.requireNonNull(v);
        nativeSetOrigin(v.x, v.y);
        transformNeedsUpdate = true;
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
        setPosition(new Vector2f(position.x + x, position.y + y));
    }

    @Override
    public final void move(Vector2f v) {
        move(v.x, v.y);
    }

    @Override
    public final void rotate(float angle) {
        setRotation(rotation + angle);
    }

    @Override
    public final void scale(float x, float y) {
        setScale(new Vector2f(scale.x * x, scale.y * y));
    }

    @Override
    public final void scale(Vector2f factors) {
        scale(factors.x, factors.y);
    }

    private native void nativeGetTransform(Buffer buf);

    private void updateTransform() {
        if (transformNeedsUpdate) {
            nativeGetTransform(IntercomHelper.getBuffer());
            transformCache = IntercomHelper.decodeTransform();
            inverseTransformCache = transformCache.getInverse();
            transformNeedsUpdate = false;
        }
    }

    @Override
    public Transform getTransform() {
        if (transformNeedsUpdate) {
            updateTransform();
        }

        return transformCache;
    }

    @Override
    public Transform getInverseTransform() {
        if (transformNeedsUpdate) {
            updateTransform();
        }

        return inverseTransformCache;
    }
}
