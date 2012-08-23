package org.jsfml.audio;

import org.jsfml.SFMLNativeObject;
import org.jsfml.system.Vector3f;

/**
 * Base class for playable sound instances.
 */
public abstract class SoundSource extends SFMLNativeObject {
    /**
     * Enumeration of sound source states.
     */
    public static enum Status {
        /**
         * The sound is currently stopped or has finished playing.
         */
        STOPPED,

        /**
         * The sound is currently paused and can be resumed.
         */
        PAUSED,

        /**
         * The sound is currently playing.
         */
        PLAYING
    }

    /**
     * Default constructor.
     */
    public SoundSource() {
        super();
    }

    @SuppressWarnings("deprecation")
    SoundSource(long wrap) {
        super(wrap);
    }

    /**
     * Sets the pitch factor of the sound.
     *
     * @param pitch The pitch factor of the sound, where 1 is the default pitch.
     */
    public native void setPitch(float pitch);

    /**
     * Sets the volume of the sound.
     * <p/>
     * A sound's default volume is 100 (maximum):
     *
     * @param volume The volume of the sound, ranging between 0 and 100.
     */
    public native void setVolume(float volume);

    /**
     * Sets the position of the sound in the scene.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     */
    public native void setPosition(float x, float y, float z);

    /**
     * Sets the position of the sound in the scene.
     *
     * @param v The new position.
     */
    public final void setPosition(Vector3f v) {
        setPosition(v.x, v.y, v.z);
    }

    /**
     * Determines whether the sound position is always relative to the {@link Listener}
     * or whether it is absolute.
     * <p/>
     * By default, the scene position is absolute.
     *
     * @param relative <tt>true</tt> to make the sound position relative to the listener, <tt>false</tt>
     *                 to make it absolute.
     * @see SoundSource#setPosition(float, float, float)
     */
    public native void setRelativeToListener(boolean relative);

    /**
     * The minimum distance of the sound before attenuation kicks in.
     * <p/>
     * If the distance between the sound and the listener is less or equal to this value, the sound
     * will be heard at its maximum volume. The default minimum distance is 1.
     *
     * @param distance The distance in world units.
     * @see SoundSource#setAttenuation(float)
     */
    public native void setMinDistance(float distance);

    /**
     * Sets the sound's attenuation factor.
     * <p/>
     * As the distance between the sound and the listener becomes higher than the minimum distance,
     * the sound volume will decrease according to this value.
     *
     * @param att The attenuation factor, ranging between 0 (no attenuation) and 100 (instant attenuation).
     * @see SoundSource#setMinDistance(float)
     */
    public native void setAttenuation(float att);

    /**
     * Gets the sound's current pitch factor.
     *
     * @return The sound's current pitch factor.
     */
    public native float getPitch();

    /**
     * Gets the sound's current volume.
     *
     * @return The sound's current volume.
     */
    public native float getVolume();

    /**
     * Gets the sound's current position in the scene.
     *
     * @return The sound's current position in the scene.
     */
    public native Vector3f getPosition();

    /**
     * Returns whether the sound's position is relative to the {@link Listener}.
     *
     * @return <tt>true</tt> if the sound's position is relative to the listener, <tt>false</tt> if
     *         it is absolute.
     * @see SoundSource#setRelativeToListener(boolean)
     */
    public native boolean isRelativeToListener();

    /**
     * Gets the sound's minimum distance before attenuation sets in.
     *
     * @return The sound's minimum distance before attenuation sets in.
     * @see SoundSource#setMinDistance(float)
     */
    public native float getMinDistance();

    /**
     * Gets the sound's attenuation factor.
     *
     * @return The sound's attenuation factor.
     * @see SoundSource#setAttenuation(float)
     */
    public native float getAttenuation();

    abstract int nativeGetStatus();

    /**
     * Gets the current state of the sound stream.
     *
     * @return The current state of the sound stream.
     */
    protected Status getStatus() {
        return Status.values()[nativeGetStatus()];
    }
}
