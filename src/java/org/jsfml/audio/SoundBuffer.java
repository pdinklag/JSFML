package org.jsfml.audio;


import org.jsfml.internal.*;
import org.jsfml.system.Time;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Buffer of audio samples, providing an audio data source for a {@code Sound}.
 */
public class SoundBuffer extends SFMLNativeObject implements ConstSoundBuffer {
    /**
     * Constructs a sound buffer.
     */
    public SoundBuffer() {
        super();
    }

    @SuppressWarnings("deprecation")
    SoundBuffer(long wrap) {
        super(wrap);
    }

    /**
     * Constructs a sound buffer by copying another sound buffer.
     *
     * @param other the sound buffer to copy.
     */
    @SuppressWarnings("deprecation")
    public SoundBuffer(ConstSoundBuffer other) {
        super(((SoundBuffer) other).nativeCopy());
        UnsafeOperations.manageSFMLObject(this, true);
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected void nativeSetExPtr() {
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native long nativeCopy();

    private native boolean nativeLoadFromMemory(byte[] memory);

    /**
     * Fully loads all available bytes from the specified {@link java.io.InputStream}
     * and attempts to load the sound buffer from it.
     *
     * @param in the input stream to read from.
     * @throws java.io.IOException in case an I/O error occurs.
     */
    public void loadFromStream(InputStream in) throws IOException {
        SFMLErrorCapture.start();
        final boolean success = nativeLoadFromMemory(StreamUtil.readStream(in));
        final String err = SFMLErrorCapture.finish();

        if (!success) {
            throw new IOException(err);
        }
    }

    /**
     * Attempts to load the sound buffer from a file.
     *
     * @param path the path to the file to load the sound buffer from.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromFile(Path path) throws IOException {
        SFMLErrorCapture.start();
        final boolean success = nativeLoadFromMemory(StreamUtil.readFile(path));
        final String err = SFMLErrorCapture.finish();

        if (!success) {
            throw new IOException(err);
        }
    }

    private native boolean nativeLoadFromSamples(short[] samples, int channelCount, int sampleRate);

    /**
     * Attempts to load the sound buffer from an array of raw 16-bit audio samples.
     *
     * @param samples      the samples data.
     * @param channelCount the amount of audio channels.
     * @param sampleRate   the sample rate in samples per second.
     * @throws java.io.IOException in case an I/O error occurs.
     */
    public void loadFromSamples(short[] samples, int channelCount, int sampleRate)
            throws IOException {

        SFMLErrorCapture.start();
        final boolean success = nativeLoadFromSamples(Objects.requireNonNull(samples), channelCount, sampleRate);
        final String err = SFMLErrorCapture.finish();

        if (!success) {
            throw new IOException(err);
        }
    }

    private native boolean nativeSaveToFile(String fileName);

    @Override
    public void saveToFile(Path path) throws IOException {
        SFMLErrorCapture.start();
        final boolean success = nativeSaveToFile(path.toAbsolutePath().toString());
        final String err = SFMLErrorCapture.finish();

        if (!success) {
            throw new IOException(err);
        }
    }

    @Override
    public native short[] getSamples();

    @Override
    public native int getSampleCount();

    @Override
    public native int getSampleRate();

    @Override
    public native int getChannelCount();

    @Override
    public native Time getDuration();
}
