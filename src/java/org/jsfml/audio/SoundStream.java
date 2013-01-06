package org.jsfml.audio;

import org.jsfml.internal.Intercom;
import org.jsfml.system.Time;

import java.util.Objects;

/**
 * Abstract base class for streamed sound sources.
 * <p/>
 * Unlike buffered sounds, streamed sounds are not held in memory in their entirety, but only
 * a certain amount of sound chunks. When the currently buffered chunk is done being played,
 * the next chunk is requested from the stream's source.
 * <p/>
 * This approach should be preferred for long sounds
 * that do not need to be sought around in often, such as music or procedurally generated
 * sounds.
 *
 * @see Chunk
 */
@Intercom
public abstract class SoundStream extends SoundSource {
    /**
     * Represents a chunk of audio data provided by a {@code SoundStream} when
     * new data is requested.
     */
    @Intercom
    public static class Chunk {
        @Intercom
        private final short[] data;

        @Intercom
        private final boolean last;

        /**
         * Constructs a new chunk containing the specified data.
         *
         * @param data An array of 16-bit samples representing the chunk's audio data.
         * @param last Determines whether this audio chunk is the last in the stream. If set to
         *             {@code true}, the stream will stop playing once this chunk has finished
         *             playing.
         */
        public Chunk(short[] data, boolean last) {
            this.data = Objects.requireNonNull(data);
            this.last = last;
        }

        /**
         * Gets the 16-bit audio sample data in this chunk.
         *
         * @return the audio sample data in this chunk.
         */
        public short[] getData() {
            return data;
        }

        /**
         * Tests whether this chunk is the last chunk of data in the stream.
         * <p/>
         * If this is {@code true}, the audio stream will stop playing after this chunk.
         *
         * @return {@code true} if the chunk is marked as the last one, {@code false}
         *         otherwise.
         */
        public boolean isLast() {
            return last;
        }
    }

    /**
     * Constructs a sound stream.
     */
    public SoundStream() {
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
     * @return the amount of audio channels of this stream.
     */
    public native int getChannelCount();

    /**
     * Gets the sample rate of this stream.
     *
     * @return the sample rate of this stream in samples per second.
     */
    public native int getSampleRate();

    private native void nativeSetPlayingOffset(Time offset);

    /**
     * Sets the current playing offset at which to play from the stream.
     *
     * @param offset the playing offset at which to play from the stream.
     */
    public final void setPlayingOffset(Time offset) {
        nativeSetPlayingOffset(Objects.requireNonNull(offset));
    }

    /**
     * Gets the playing offset at which to play from the stream.
     *
     * @return the playing offset at which to play from the stream.
     */
    public native Time getPlayingOffset();

    /**
     * Enables or disables repeated looping of the sound stream playback.
     * <p/>
     * If a looping sound stream has finished playing its last audio chunk, it will
     * restart playing from the first chunk as if {@code setPlayingOffset(Time.ZERO)} was invoked.
     *
     * @param loop {@code true} to enable looping, {@code false} to disable.
     */
    public native void setLoop(boolean loop);

    /**
     * Returns whether or not the sound stream playback is looping.
     * <p/>
     * If a looping sound stream has finished playing its last audio chunk, it will
     * restart playing from the first chunk as if {@code setPlayingOffset(Time.ZERO)} was invoked.
     *
     * @return {@code true} if it is looping, {@code false} if not.
     */
    public native boolean isLoop();

    @Override
    native int nativeGetStatus();

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    /**
     * Defines the audio stream parameters.
     * <p/>
     * Before the stream can be played, the implementing class must call this method.
     *
     * @param channelCount the amount of audio channels (e.g. 1 for mono, 2 for stereo).
     * @param sampleRate   the sample rate in samples per second.
     */
    protected native void initialize(int channelCount, int sampleRate);

    /**
     * Requests a new chunk of audio data.
     * <p/>
     * This method is called when the audio stream has played all buffered samples and needs
     * new samples to continue playing.
     * <p/>
     * Note that this method will be called within a separate playback thread.
     *
     * @return the next chunk of audio data. If the chunk is marked as the last chunk,
     *         the stream will either stop playing after playing this chunk, or restart
     *         from the beginning if {@link #isLoop()} returns {@code true}.
     *         To stop playback immediately, {@code null} may be returned.
     * @see Chunk
     */
    @Intercom
    protected abstract Chunk onGetData();

    /**
     * Re-positions the stream's current playing offset.
     * <p/>
     * This method is called when the stream is reset or a re-positioning has been requested
     * via {@link #setPlayingOffset(org.jsfml.system.Time)}.
     * <p/>
     * Note that this method will be called within a separate playback thread.
     *
     * @param time the time offset to jump to.
     */
    @Intercom
    protected abstract void onSeek(Time time);
}
