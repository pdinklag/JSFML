package org.jsfml.graphics;

import org.jsfml.ConstException;

/**
 * Mapping for a C++ const sf::View.
 */
final class SFMLConstView extends View{
    SFMLConstView(long wrap) {
        super(wrap);
    }

    @Override
    public void setCenter(float x, float y) {
        throw new ConstException();
    }

    @Override
    public void setSize(float width, float height) {
        throw new ConstException();
    }

    @Override
    public void setRotation(float angle) {
        throw new ConstException();
    }

    @Override
    public void setViewport(FloatRect rect) {
        throw new ConstException();
    }

    @Override
    public void reset(FloatRect rect) {
        throw new ConstException();
    }

    @Override
    public void rotate(float angle) {
        throw new ConstException();
    }

    @Override
    public void zoom(float factor) {
        throw new ConstException();
    }

    @Override
    public void move(float x, float y) {
        throw new ConstException();
    }
}
