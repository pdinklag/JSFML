package org.jsfml.audio;

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
    public abstract void play();

    /**
     * Pauses playback of the stream if it is currently playing.
     */
    public abstract void pause();

    /**
     * Stops playing the stream.
     */
    public abstract void stop();

    /**
     * Gets the amount of audio channels of this stream.
     *
     * @return The amount of audio channels of this stream.
     */
    public abstract int getChannelCount();

    /**
     * Gets the sample rate of this stream.
     *
     * @return The sample rate of this stream in samples per second.
     */
    public abstract int getSampleRate();

    /**
     * Gets the current state of the sound stream.
     *
     * @return The current state of the sound stream.
     */
    public abstract Status getStatus();

    /**
     * Sets the playing offset at which to play from the stream.
     *
     * @param offset The playing offset, in milliseconds, at which to play from the stream.
     */
    public abstract void setPlayingOffset(long offset);


    /**
     * Gets the playing offset at which to play from the stream.
     *
     * @return The playing offset, in milliseconds, at which to play from the stream.
     */
    public abstract long getPlayingOffset();

    /**
     * Enables or disables repeated looping of the sound stream playback.
     *
     * @param loop <tt>true</tt> to enable looping, <tt>false</tt> to disable.
     */
    public abstract void setLoop(boolean loop);

    /**
     * Returns whether or not the sound stream playback is looping.
     *
     * @return <tt>true</tt> if it is looping, <tt>false</tt> if not.
     */
    public abstract boolean isLoop();
}
