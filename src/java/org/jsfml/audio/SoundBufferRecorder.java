package org.jsfml.audio;

/**
 * A {@code SoundRecorder} which stores captured audio data into a {@code SoundBuffer}.
 */
public class SoundBufferRecorder extends SoundRecorder {
    private SoundBuffer soundBuffer;

    /**
     * Constructs a sound buffer recorder.
     */
    public SoundBufferRecorder() {
        super();
        soundBuffer = new SoundBuffer(nativeGetBuffer());
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeSetExPtr();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native long nativeGetBuffer();

    /**
     * Gets the sound buffer containing the captured audio data.
     * <p/>
     * The sound buffer will remain empty until any sound has been successfully captured
     * using the {@link #start(int)} and {@link #stop()} methods.
     *
     * @return the sound buffer containing the captured audio data.
     */
    public ConstSoundBuffer getBuffer() {
        return soundBuffer;
    }

    @Override
    protected final boolean onStart() {
        //SoundBufferRecorder is implemented natively, so this is not used.
        return true;
    }

    @Override
    protected final boolean onProcessSamples(short[] samples) {
        //SoundBufferRecorder is implemented natively, so this is not used.
        return true;
    }

    @Override
    protected final void onStop() {
        //SoundBufferRecorder is implemented natively, so this is not used.
    }
}
