package org.jsfml.graphics;

import org.jsfml.window.Window;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Immutable texture that cannot be altered.
 */
class ImmutableTexture extends Texture {
    ImmutableTexture(long wrap) {
        super(wrap);
    }

    @Override
    public boolean create(int width, int height) {
        return false;
    }

    @Override
    public boolean loadFromStream(InputStream in, IntRect area) throws IOException {
        return false;
    }

    @Override
    public boolean loadFromFile(File file, IntRect area) throws IOException {
        return false;
    }

    @Override
    public boolean loadFromImage(Image image, IntRect area) {
        return false;
    }

    @Override
    public void update(Image image, int x, int y) {
    }

    @Override
    public void update(Window window, int x, int y) {
    }

    @Override
    public void setSmooth(boolean smooth) {
    }

    @Override
    public void setRepeated(boolean repeated) {
    }
}
