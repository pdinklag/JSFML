package org.jsfml.audio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Immutable sound buffer that cannot be altered.
 */
class ImmutableSoundBuffer extends SoundBuffer {
    ImmutableSoundBuffer(long wrap) {
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

    @Override
    public boolean loadFromSamples(short[] samples, int channelCount, int sampleRate) {
        return false;
    }
}
