package org.jsfml.audio;


import org.jsfml.internal.*;
import org.jsfml.system.Time;

import java.io.IOException;
import java.io.InputStream;
import java.nio.*;
import java.nio.file.Path;

/**
 * Buffer of audio samples, providing an audio data source for a {@code Sound}.
 */
public class SoundBuffer extends SFMLNativeObject implements ConstSoundBuffer {
    //cache
    private int sampleCount = 0;
    private int sampleRate = 0;
    private int channelCount = 0;
    private Time duration = Time.ZERO;

    private boolean needsSync = true;
    private boolean samplesNeedSync = true;
    private ShortBuffer samplesBuffer = null;

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

        needsSync = true;
        samplesNeedSync = true;
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

        needsSync = true;
        samplesNeedSync = true;
    }

    private native boolean nativeLoadFromSamples(Buffer samples, int n, int channelCount, int sampleRate);

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

        final ShortBuffer buffer = ByteBuffer.allocateDirect(2 * samples.length).order(
                ByteOrder.nativeOrder()).asShortBuffer();

        buffer.put(samples);

        SFMLErrorCapture.start();
        final boolean success = nativeLoadFromSamples(
                buffer, samples.length, channelCount, sampleRate);

        final String err = SFMLErrorCapture.finish();

        if (!success) {
            throw new IOException(err);
        }

        this.samplesBuffer = buffer;
        this.sampleCount = samples.length;
        this.channelCount = channelCount;
        this.sampleRate = sampleRate;

        needsSync = true;
        samplesNeedSync = false;
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

    private native void nativeGetData(Buffer buffer);

    private void sync() {
        if (needsSync) {
            final IntBuffer ints = IntercomHelper.getBuffer().asIntBuffer();
            final LongBuffer longs = IntercomHelper.getBuffer().asLongBuffer();

            nativeGetData(IntercomHelper.getBuffer());
            sampleCount = ints.get(0);
            sampleRate = ints.get(1);
            channelCount = ints.get(2);
            duration = Time.getMicroseconds(longs.get(2));

            needsSync = false;
        }
    }

    private native void nativeGetSamples(int n, Buffer buffer);

    private void syncSamples() {
        if (samplesNeedSync) {
            final int samples = getSampleCount();
            samplesBuffer = ByteBuffer.allocateDirect(2 * getSampleCount()).order(
                    ByteOrder.nativeOrder()).asShortBuffer();

            nativeGetSamples(samples, samplesBuffer);
            samplesNeedSync = false;
        }
    }

    @Override
    public short[] getSamples() {
        if (samplesNeedSync) {
            syncSamples();
        }

        final short[] copy = new short[sampleCount];
        samplesBuffer.get(copy);
        return copy;
    }

    @Override
    public int getSampleCount() {
        if (needsSync) {
            sync();
        }

        return sampleCount;
    }

    @Override
    public int getSampleRate() {
        if (needsSync) {
            sync();
        }

        return sampleRate;
    }

    @Override
    public int getChannelCount() {
        if (needsSync) {
            sync();
        }

        return channelCount;
    }

    @Override
    public Time getDuration() {
        if (needsSync) {
            sync();
        }

        return duration;
    }
}
