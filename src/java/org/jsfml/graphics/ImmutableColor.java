package org.jsfml.graphics;

/**
 * Immutable color.
 */
class ImmutableColor extends Color {
    private static final long serialVersionUID = 8700468200193026947L;

    ImmutableColor(int r, int g, int b) {
        super(r, g, b);
    }

    ImmutableColor(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    @Override
    public void setAlpha(int a) {
    }

    @Override
    public void setBlue(int b) {
    }

    @Override
    public void setGreen(int g) {
    }

    @Override
    public void setRed(int r) {
    }

    @Override
    public Color sub(Color color) {
        return this;
    }

    @Override
    public Color mul(float f) {
        return this;
    }

    @Override
    public Color mul(Color color) {
        return this;
    }

    @Override
    public Color add(Color color) {
        return this;
    }
}
