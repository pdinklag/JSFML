package org.jsfml.audio;

/**
 * Specialized {@link SoundRecorder} which stores captured audio data into a {@link SoundBuffer}.
 */
public class SoundBufferRecorder extends SoundRecorder {
    private ImmutableSoundBuffer soundBuffer;

    /**
     * Creates a new sound buffer recorder.
     */
    public SoundBufferRecorder() {
        super();
        soundBuffer = new ImmutableSoundBuffer(nativeGetBuffer());
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    private native long nativeGetBuffer();

    /**
     * Gets the sound buffer containing the captured audio data.
     *
     * @return The sound buffer containing the captured audio data.
     */
    public SoundBuffer getBuffer() {
        return soundBuffer;
    }

    @Override
    public native void start(int sampleRate);

    @Override
    public native void stop();

    @Override
    public native int getSampleRate();
}
