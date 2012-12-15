package org.jsfml.audio;

import org.jsfml.NotNull;
import org.jsfml.UnsafeOperations;
import org.jsfml.system.Time;

/**
 * A playable sound, an "instance" of a {@link SoundBuffer}.
 */
public class Sound extends SoundSource {
    private ConstSoundBuffer soundBuffer = null;

    /**
     * Creates a new sound.
     */
    public Sound() {
        super();
    }

    /**
     * Creates a new sound.
     *
     * @param soundBuffer The sound buffer to use.
     */
    public Sound(ConstSoundBuffer soundBuffer) {
        this();
        setBuffer(soundBuffer);
    }

    /**
     * Creates a new sound from another sound.
     *
     * @param other The sound to copy.
     */
	@SuppressWarnings("deprecation")
    public Sound(Sound other) {
        super(other.nativeCopy());
        UnsafeOperations.manageSFMLObject(this, true);
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

    private native long nativeCopy();

    /**
     * Starts playing the sound or resumes it if it is currently paused.
     */
    public native void play();

    /**
     * Pauses the sound if it is currently playing.
     */
    public native void pause();

    /**
     * Stops the sound if it is currently playing or paused.
     */
    public native void stop();

    private native void nativeSetBuffer(SoundBuffer soundBuffer);

    /**
     * Sets the sound buffer used by this sound.
     *
     * @param soundBuffer The sound buffer.
     */
    public void setBuffer(@NotNull ConstSoundBuffer soundBuffer) {
        if (soundBuffer == null)
            throw new NullPointerException("soundBuffer must not be null.");

        this.soundBuffer = soundBuffer;
        nativeSetBuffer((SoundBuffer) soundBuffer);
    }

    /**
     * Enables or disables repeated looping of the sound.
     *
     * @param loop {@code true} to enable looping, {@code false} to disable.
     */
    public native void setLoop(boolean loop);

    private native void nativeSetPlayingOffset(Time offset);

    /**
     * Sets the playing offset from where to start playing in the underlying buffer.
     *
     * @param offset The playing offset in milliseconds.
     */
    public void setPlayingOffset(@NotNull Time offset) {
        if (offset == null)
            throw new NullPointerException("offset must not be null.");

        nativeSetPlayingOffset(offset);
    }

    /**
     * Gets the underlying sound buffer that is being played from.
     *
     * @return The underlying sound buffer that is being played from.
     */
    public ConstSoundBuffer getBuffer() {
        return soundBuffer;
    }

    /**
     * Returns whether or not the sound is looping.
     *
     * @return {@code true} if it is looping, {@code false} if not.
     */
    public native boolean isLoop();

    /**
     * Gets the playing offset from where to start playing in the underlying buffer.
     *
     * @return The playing offset from where to start playing in the underlying buffer.
     */
    public native Time getPlayingOffset();

    @Override
    native int nativeGetStatus();

    @Override
    public Status getStatus() {
        return super.getStatus();
    }
}
