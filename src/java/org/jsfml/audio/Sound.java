package org.jsfml.audio;

import org.jsfml.internal.UnsafeOperations;
import org.jsfml.system.Time;

import java.util.Objects;

/**
 * Provides functionality to instantiate a {@code SoundBuffer} and play a buffered sound.
 */
public class Sound extends SoundSource {
    //cache
    private ConstSoundBuffer soundBuffer = null;
    private boolean loop = false;

    /**
     * Constructs an empty sound.
     */
    public Sound() {
        super();
    }

    /**
     * Constructs a sound with the specified {@link SoundBuffer}
     *
     * @param soundBuffer the sound buffer to use.
     */
    public Sound(ConstSoundBuffer soundBuffer) {
        this();
        setBuffer(soundBuffer);
    }

    /**
     * Constructs a sound by copying another sound.
     *
     * @param other the sound to copy.
     */
    @SuppressWarnings("deprecation")
    public Sound(Sound other) {
        super(other.nativeCopy());
        UnsafeOperations.manageSFMLObject(this, true);

        this.loop = other.loop;
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
     * @param soundBuffer the new sound buffer.
     */
    public void setBuffer(ConstSoundBuffer soundBuffer) {
        this.soundBuffer = Objects.requireNonNull(soundBuffer);
        nativeSetBuffer((SoundBuffer) soundBuffer);
    }

    private native void nativeSetLoop(boolean loop);

    /**
     * Enables or disables repeated looping of the sound.
     * <p/>
     * If this is set to {@code true} and the sound has finished playing, it will
     * be restarted from the beginning as if {@code setPlayingOffset(Time.ZERO)} was called.
     *
     * @param loop {@code true} to enable looping, {@code false} to disable.
     */
    public void setLoop(boolean loop) {
        nativeSetLoop(loop);
        this.loop = loop;
    }

    private native void nativeSetPlayingOffset(long offset);

    /**
     * Sets the playing position within the underlying buffer.
     *
     * @param offset the new playing position within the underlying buffer.
     */
    public void setPlayingOffset(Time offset) {
        nativeSetPlayingOffset(offset.asMicroseconds());
    }

    /**
     * Gets the underlying sound buffer that this sound plays from.
     *
     * @return the underlying sound buffer that this sound plays from.
     */
    public ConstSoundBuffer getBuffer() {
        return soundBuffer;
    }

    /**
     * Returns whether or not the sound is looping.
     *
     * @return {@code true} if this sound is looping, {@code false} if not.
     */
    public boolean isLoop() {
        return loop;
    }

    private native long nativeGetPlayingOffset();

    /**
     * Gets the currently playing position within the underlying buffer.
     *
     * @return the currently playing position within the underlying buffer.
     */
    public Time getPlayingOffset() {
        return Time.getMicroseconds(nativeGetPlayingOffset());
    }

    @Override
    native int nativeGetStatus();

    @Override
    public Status getStatus() {
        return super.getStatus();
    }
}
