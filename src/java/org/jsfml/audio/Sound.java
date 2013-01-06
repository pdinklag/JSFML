package org.jsfml.audio;

import org.jsfml.internal.UnsafeOperations;
import org.jsfml.system.Time;

import java.util.Objects;

/**
 * Provides functionality to instantiate a {@code SoundBuffer} and play a buffered sound.
 */
public class Sound extends SoundSource {
    private ConstSoundBuffer soundBuffer = null;

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

    /**
     * Enables or disables repeated looping of the sound.
     * <p/>
     * If this is set to {@code true} and the sound has finished playing, it will
     * be restarted from the beginning as if {@code setPlayingOffset(Time.ZERO)} was called.
     *
     * @param loop {@code true} to enable looping, {@code false} to disable.
     */
    public native void setLoop(boolean loop);

    private native void nativeSetPlayingOffset(Time offset);

    /**
     * Sets the playing offset from where to play the underlying buffer.
     *
     * @param offset the playing offset in the underlaying buffer.
     */
    public void setPlayingOffset(Time offset) {
        nativeSetPlayingOffset(Objects.requireNonNull(offset));
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
    public native boolean isLoop();

    /**
     * Gets the playing offset from where to start playing the underlying buffer.
     *
     * @return the playing offset from where to start playing the underlying buffer.
     */
    public native Time getPlayingOffset();

    @Override
    native int nativeGetStatus();

    @Override
    public Status getStatus() {
        return super.getStatus();
    }
}
