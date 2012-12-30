package org.jsfml.audio;

import org.jsfml.internal.SFMLNative;
import org.jsfml.internal.SFMLNativeObject;

/**
 * Abstract base class for sound recorders, which provide functionality to capture audio data.
 * <p/>
 * This class can currently not be implemented to function properly in pure Java.
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
    public SoundRecorder() {
        super();
    }

    /**
     * Starts capturing audio data.
     *
     * @param sampleRate the sample rate in samples per second.
     */
    public abstract void start(int sampleRate);

    /**
     * Starts capturing audio data with a sample rate of 44,100 Hz.
     */
    public final void start() {
        start(44100);
    }

    /**
     * Stops capturing audio data.
     */
    public abstract void stop();

    /**
     * Gets the audio sample rate.
     *
     * @return the audio sample rate in samples per second.
     */
    public abstract int getSampleRate();
}
