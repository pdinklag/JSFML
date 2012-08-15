package org.jsfml.audio;

import org.jsfml.NotNull;
import org.jsfml.UnsafeOperations;
import org.jsfml.system.Time;
import org.jsfml.system.Vector3f;

/**
 * A playable sound, an "instance" of a {@link SoundBuffer}.
 */
public class Sound extends SoundSource {
    private SoundBuffer soundBuffer = null;

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
    public Sound(SoundBuffer soundBuffer) {
        this();
        setBuffer(soundBuffer);
    }

    /**
     * Creates a new sound from another sound.
     *
     * @param other The sound to copy.
     */
    public Sound(Sound other) {
        super(other.nativeCopy());
        UnsafeOperations.manageSFMLObject(this, true);
    }

    @Override
    protected native long nativeCreate();

    @Override
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
    public void setBuffer(@NotNull SoundBuffer soundBuffer) {
        if (soundBuffer == null)
            throw new NullPointerException("soundBuffer must not be null.");

        this.soundBuffer = soundBuffer;
        nativeSetBuffer(soundBuffer);
    }

    /**
     * Enables or disables repeated looping of the sound.
     *
     * @param loop <tt>true</tt> to enable looping, <tt>false</tt> to disable.
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
    public SoundBuffer getBuffer() {
        return soundBuffer;
    }

    /**
     * Returns whether or not the sound is looping.
     *
     * @return <tt>true</tt> if it is looping, <tt>false</tt> if not.
     */
    public native boolean isLoop();

    /**
     * Gets the playing offset from where to start playing in the underlying buffer.
     *
     * @return The playing offset from where to start playing in the underlying buffer.
     */
    public native Time getPlayingOffset();

    private native int nativeGetStatus();

    /**
     * Gets the current state of the sound.
     *
     * @return The current state of the sound.
     */
    public Status getStatus() {
        return Status.values()[nativeGetStatus()];
    }

    @Override
    public native void setPitch(float pitch);

    @Override
    public native void setVolume(float volume);

    @Override
    public native void setPosition(float x, float y, float z);

    @Override
    public native void setRelativeToListener(boolean relative);

    @Override
    public native void setMinDistance(float distance);

    @Override
    public native void setAttenuation(float att);

    @Override
    public native float getPitch();

    @Override
    public native float getVolume();

    @Override
    public native Vector3f getPosition();

    @Override
    public native boolean isRelativeToListener();

    @Override
    public native float getMinDistance();

    @Override
    public native float getAttenuation();
}
