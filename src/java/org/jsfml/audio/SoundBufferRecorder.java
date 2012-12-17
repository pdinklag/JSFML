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
    protected void nativeSetExPtr() {
        //TODO Make SoundRecorder tree use the ExPtr system.
    }

    @Override
	@Deprecated
	@SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native long nativeGetBuffer();

    /**
     * Gets the sound buffer containing the captured audio data.
	 * <p />
	 * The sound buffer will remain empty until any sound has been successfully captured
	 * using the {@link #start(int)} and {@link #stop()} methods.
     *
     * @return the sound buffer containing the captured audio data.
     */
    public ConstSoundBuffer getBuffer() {
        return soundBuffer;
    }

    @Override
    public native void start(int sampleRate);

    @Override
    public native void stop();

    @Override
    public native int getSampleRate();
}
