package org.jsfml.audio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Immutable sound buffer.
 */
final class ImmutableSoundBuffer extends SoundBuffer {
    ImmutableSoundBuffer(long wrap) {
        super(wrap);
    }

    @Override
    public void loadFromStream(InputStream in) throws IOException {
        throw new UnsupportedOperationException("This sound buffer is immutable!");
    }

    @Override
    public void loadFromFile(File file) throws IOException {
        throw new UnsupportedOperationException("This sound buffer is immutable!");
    }

    @Override
    public void loadFromSamples(short[] samples, int channelCount, int sampleRate) {
        throw new UnsupportedOperationException("This sound buffer is immutable!");
    }
}
