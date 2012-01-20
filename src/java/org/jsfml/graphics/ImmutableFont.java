package org.jsfml.graphics;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Immutable font that cannot be altered.
 */
class ImmutableFont extends Font {
    ImmutableFont(long wrap) {
        super(wrap);
    }

    @Override
    public void loadFromStream(InputStream in) throws IOException {
    }

    @Override
    public void loadFromFile(File file) throws IOException {
    }
}
