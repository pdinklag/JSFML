package org.jsfml.graphics;

/**
 * Immutable view.
 */
final class ImmutableView extends View {
    ImmutableView(long wrap) {
        super(wrap);
    }

    @Override
    public void setCenter(float x, float y) {
        throw new UnsupportedOperationException("This view is immutable!");
    }

    @Override
    public void setSize(float width, float height) {
        throw new UnsupportedOperationException("This view is immutable!");
    }

    @Override
    public void setRotation(float angle) {
        throw new UnsupportedOperationException("This view is immutable!");
    }

    @Override
    public void setViewport(FloatRect rect) {
        throw new UnsupportedOperationException("This view is immutable!");
    }

    @Override
    public void reset(FloatRect rect) {
        throw new UnsupportedOperationException("This view is immutable!");
    }

    @Override
    public void rotate(float angle) {
        throw new UnsupportedOperationException("This view is immutable!");
    }

    @Override
    public void zoom(float factor) {
        throw new UnsupportedOperationException("This view is immutable!");
    }

    @Override
    public void move(float x, float y) {
        throw new UnsupportedOperationException("This view is immutable!");
    }
}
