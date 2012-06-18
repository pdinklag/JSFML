package org.jsfml.graphics;

class ImmutableColor extends Color {
    ImmutableColor(int r, int g, int b) {
        super(r, g, b);
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
