package org.jsfml.audio;

import org.jsfml.ConstException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Immutable sound buffer.
 */
final class SFMLConstSoundBuffer extends SoundBuffer {
    SFMLConstSoundBuffer(long wrap) {
        super(wrap);
    }

    @Override
    public void loadFromStream(InputStream in) throws IOException {
        throw new ConstException();
    }

    @Override
    public void loadFromFile(File file) throws IOException {
        throw new ConstException();
    }

    @Override
    public void loadFromSamples(short[] samples, int channelCount, int sampleRate) {
        throw new ConstException();
    }
}
