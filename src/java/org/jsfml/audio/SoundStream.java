package org.jsfml.audio;

import org.jsfml.NotNull;
import org.jsfml.system.Time;

/**
 * Abstract base class for streamed audio sources.
 */
public abstract class SoundStream extends SoundSource {
    /**
     * Default constructor.
     */
    public SoundStream() {
        super();
    }

    /**
     * Starts playing the stream or resumes it if it is currently paused.
     */
    public native void play();

    /**
     * Pauses playback of the stream if it is currently playing.
     */
    public native void pause();

    /**
     * Stops playing the stream.
     */
    public native void stop();

    /**
     * Gets the amount of audio channels of this stream.
     *
     * @return The amount of audio channels of this stream.
     */
    public native int getChannelCount();

    /**
     * Gets the sample rate of this stream.
     *
     * @return The sample rate of this stream in samples per second.
     */
    public native int getSampleRate();

    private native void nativeSetPlayingOffset(Time offset);

    /**
     * Sets the playing offset at which to play from the stream.
     *
     * @param offset The playing offset, in milliseconds, at which to play from the stream.
     */
    public final void setPlayingOffset(@NotNull Time offset) {
        if(offset == null)
            throw new NullPointerException("offset must not be null.");

        nativeSetPlayingOffset(offset);
    }

    /**
     * Gets the playing offset at which to play from the stream.
     *
     * @return The playing offset, in milliseconds, at which to play from the stream.
     */
    public native Time getPlayingOffset();

    /**
     * Enables or disables repeated looping of the sound stream playback.
     *
     * @param loop <tt>true</tt> to enable looping, <tt>false</tt> to disable.
     */
    public native void setLoop(boolean loop);

    /**
     * Returns whether or not the sound stream playback is looping.
     *
     * @return <tt>true</tt> if it is looping, <tt>false</tt> if not.
     */
    public native boolean isLoop();

    @Override
    native int nativeGetStatus();
}
