package org.jsfml.graphics;

import org.jsfml.ConstException;
import org.jsfml.window.Window;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mapping for a C++ const sf::Texture.
 */
final class SFMLConstTexture extends Texture {
    SFMLConstTexture(long wrap) {
        super(wrap);
    }

    @Override
    public boolean create(int width, int height) {
        throw new ConstException();
    }

    @Override
    public void loadFromStream(InputStream in, IntRect area) throws IOException {
        throw new ConstException();
    }

    @Override
    public void loadFromFile(File file, IntRect area) throws IOException {
        throw new ConstException();
    }

    @Override
    public boolean loadFromImage(Image image, IntRect area) {
        throw new ConstException();
    }

    @Override
    public void update(Image image, int x, int y) {
        throw new ConstException();
    }

    @Override
    public void update(Window window, int x, int y) {
        throw new ConstException();
    }

    @Override
    public void setSmooth(boolean smooth) {
        throw new ConstException();
    }

    @Override
    public void setRepeated(boolean repeated) {
        throw new ConstException();
    }
}
