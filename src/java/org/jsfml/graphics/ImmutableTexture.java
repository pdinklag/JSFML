package org.jsfml.graphics;

import org.jsfml.window.Window;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Immutable texture.
 */
final class ImmutableTexture extends Texture {
    ImmutableTexture(long wrap) {
        super(wrap);
    }

    @Override
    public boolean create(int width, int height) {
        throw new UnsupportedOperationException("This texture is immutable!");
    }

    @Override
    public void loadFromStream(InputStream in, IntRect area) throws IOException {
        throw new UnsupportedOperationException("This texture is immutable!");
    }

    @Override
    public void loadFromFile(File file, IntRect area) throws IOException {
        throw new UnsupportedOperationException("This texture is immutable!");
    }

    @Override
    public boolean loadFromImage(Image image, IntRect area) {
        throw new UnsupportedOperationException("This texture is immutable!");
    }

    @Override
    public void update(Image image, int x, int y) {
        throw new UnsupportedOperationException("This texture is immutable!");
    }

    @Override
    public void update(Window window, int x, int y) {
        throw new UnsupportedOperationException("This texture is immutable!");
    }

    @Override
    public void setSmooth(boolean smooth) {
        throw new UnsupportedOperationException("This texture is immutable!");
    }

    @Override
    public void setRepeated(boolean repeated) {
        throw new UnsupportedOperationException("This texture is immutable!");
    }
}
