package org.jsfml.audio;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.internal.UnsafeOperations;
import org.jsfml.system.Time;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Provides functionality to instantiate a {@code SoundBuffer} and play a buffered sound.
 */
public class Sound extends SoundSource {
    //cache
    private ConstSoundBuffer soundBuffer = null;
    private boolean loop = false;
    private Time playingOffset = Time.ZERO;

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

        final ByteBuffer buffer = IntercomHelper.getBuffer();
        nativeGetData(buffer);

        this.loop = (buffer.get(0) == 1);
        this.playingOffset = Time.getMicroseconds(buffer.asLongBuffer().get(1));
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

    private native void nativeGetData(Buffer buffer);

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
     * Sets the playing offset from where to play the underlying buffer.
     *
     * @param offset the playing offset in the underlaying buffer.
     */
    public void setPlayingOffset(Time offset) {
        nativeSetPlayingOffset(offset.asMicroseconds());
        this.playingOffset = offset;
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

    /**
     * Gets the playing offset from where to start playing the underlying buffer.
     *
     * @return the playing offset from where to start playing the underlying buffer.
     */
    public Time getPlayingOffset() {
        return playingOffset;
    }

    @Override
    native int nativeGetStatus();

    @Override
    public Status getStatus() {
        return super.getStatus();
    }
}
