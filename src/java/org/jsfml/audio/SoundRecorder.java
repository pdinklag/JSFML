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
     * Starts capturing audio data with the specified sample rate.
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
     * Gets the sample rate that audio is being captured with.
     *
     * @return the audio sample rate in samples per second.
     */
    public final native int getSampleRate();

    /**
     * Called when the sound recorder starts recording.
     * <p/>
     * This method can be implemented by deriving classes to perform any actions
     * necessary before audio recording actually starts.
     *
     * @return {@code true} to start recording after this method is done, {@code false} to cancel.
     */
    protected abstract boolean onStart();

    /**
     * Called when a new batch of audio samples comes in.
     * <p/>
     * Implementing classes can then process the captured audio data.
     * <p/>
     * Note that this method will be called in a separate audio capturing thread.
     * <p/>
     * Also note that this method is currently hardcoded to be called every 100ms, which might
     * be changed in the future.
     *
     * @param samples the 16-bit mono samples that were captured.
     * @return {@code true} to continue recording after this method is done, {@code false}
     *         to stop recording.
     */
    protected abstract boolean onProcessSamples(short[] samples);

    /**
     * Called when the audio capture has stopped.
     * <p/>
     * Note that this method will be called in a separate audio capturing thread.
     */
    protected abstract void onStop();
}
