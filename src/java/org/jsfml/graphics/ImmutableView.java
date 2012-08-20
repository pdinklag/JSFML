package org.jsfml.graphics;

/**
 * Immutable view.
 */
class ImmutableView extends View {
    ImmutableView(long wrap) {
        super(wrap);
    }

    @Override
    public void setCenter(float x, float y) {
    }

    @Override
    public void setSize(float width, float height) {
    }

    @Override
    public void setRotation(float angle) {
    }

    @Override
    public void setViewport(FloatRect rect) {
    }

    @Override
    public void reset(FloatRect rect) {
    }

    @Override
    public void rotate(float angle) {
    }

    @Override
    public void zoom(float factor) {
    }

    @Override
    public void move(float x, float y) {
    }
}
