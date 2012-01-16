package org.jsfml.graphics;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Immutable font that cannot be altered.
 */
public class ImmutableFont extends Font {
    ImmutableFont(long wrap) {
        super(wrap);
    }

    @Override
    public boolean loadFromStream(InputStream in) throws IOException {
        return false;
    }

    @Override
    public boolean loadFromFile(File file) throws IOException {
        return false;
    }
}
