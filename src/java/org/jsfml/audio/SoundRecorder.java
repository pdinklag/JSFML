package org.jsfml.audio;

import org.jsfml.SFMLNative;
import org.jsfml.SFMLNativeObject;

/**
 * Abstract base class for capturing audio data.
 */
public abstract class SoundRecorder extends SFMLNativeObject {
	static {
		SFMLNative.loadNativeLibraries();
	}

    /**
     * Checks whether audio capturing is available on this system.
     *
     * @return <tt>true</tt> if audio capturing is available, <tt>false</tt> otherwise.
     */
    public static native boolean isAvailable();

    /**
     * Default constructor.
     */
    public SoundRecorder() {
        super();
    }

    /**
     * Starts capturing audio data.
     *
     * @param sampleRate The sample rate in samples per second.
     */
    public abstract void start(int sampleRate);

    /**
     * Starts capturing audio data with a sample rate of 44,100 Hz.
     */
    public final void start() {
        start(44100);
    }

    /**
     * Stops the audio capturing.
     */
    public abstract void stop();

    /**
     * Gets the audio sample rate.
     *
     * @return The audio sample rate in samples per second.
     */
    public abstract int getSampleRate();
}
