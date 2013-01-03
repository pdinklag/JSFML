package org.jsfml.audio;

import org.jsfml.internal.SFMLNative;
import org.jsfml.internal.SFMLNativeObject;

/**
 * Abstract base class for sound recorders, which provide functionality to capture audio data.
 */
public abstract class SoundRecorder extends SFMLNativeObject {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Checks whether audio capturing is available on this system.
     *
     * @return {@code true} if audio capturing is available, {@code false} otherwise.
     */
    public static native boolean isAvailable();

    /**
     * Constructs a sound recorder.
     */
    protected SoundRecorder() {
        super();
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

    /**
     * Starts capturing audio data.
     *
     * @param sampleRate the sample rate in samples per second.
     */
    public final native void start(int sampleRate);

    /**
     * Starts capturing audio data with a sample rate of 44,100 Hz.
     */
    public final void start() {
        start(44100);
    }

    /**
     * Stops capturing audio data.
     */
    public final native void stop();

    /**
     * Gets the audio sample rate.
     *
     * @return the audio sample rate in samples per second.
     */
    public final native int getSampleRate();

    protected abstract boolean onStart();

    protected abstract boolean onProcessSamples(short[] samples);

    protected abstract void onStop();
}
