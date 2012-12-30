package org.jsfml.audio;

import org.jsfml.internal.SFMLNative;
import org.jsfml.system.Vector3f;

/**
 * Represents the point in the scene from where all the sounds are heard and
 * provides funcionality to modify it.
 * <p/>
 * Modifying the position and orientation ("view direction") of the listener
 * changes the way sounds are heard to accomplish 3D sound.
 * Sounds will be panned and attenuated according to their position relative to the listener
 * and the listener's orientation..
 */
public final class Listener {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Sets the global sound volume.
     * <p/>
     * The default global volume is 100 (maximum).
     *
     * @param volume the global sound volume, ranging between 0 (silence) and 100 (full volume).
     */
    public static native void setGlobalVolume(float volume);

    /**
     * Gets the global sound volume.
     *
     * @return the global sound volume, ranging between 0 (silence) and 100 (full volume).
     */
    public static native float getGlobalVolume();

    /**
     * Sets the position of the listener in the scene.
     * <p/>
     * Initially, the listener is located at the origin <i>(0, 0, 0)</i>.
     *
     * @param x the X coordinate of the listener's new position.
     * @param y the Y coordinate of the listener's new position.
     * @param z the Z coordinate of the listener's new position.
     * @see SoundSource#setPosition(org.jsfml.system.Vector3f)
     */
    public static native void setPosition(float x, float y, float z);

    /**
     * Sets the position of the listener in the scene.
     * <p/>
     * Initially, the listener is located at the origin <i>(0, 0, 0)</i>.
     *
     * @param v the listener's new position.
     * @see SoundSource#setPosition(org.jsfml.system.Vector3f)
     */
    public static void setPosition(Vector3f v) {
        setPosition(v.x, v.y, v.z);
    }

    /**
     * Gets the listener's current position in the scene.
     *
     * @return the listener's current position in the scene.
     */
    public static native Vector3f getPosition();

    /**
     * Sets the orientation or "view direction" of the listener in the scene.
     * <p/>
     * The vector passed does not need to be normalized. Initially, the listener's orientation
     * is along the Z axis, looking "into" the screen <i>(0, 0, -1)</i>.
     *
     * @param x the X component of the listener's new orientation.
     * @param y the Y component of the listener's new orientation.
     * @param z the Z component of the listener's new orientation.
     * @see SoundSource#setPosition(org.jsfml.system.Vector3f)
     */
    public static native void setDirection(float x, float y, float z);

    /**
     * Sets the orientation or "view direction" of the listener in the scene.
     * <p/>
     * The vector passed does not need to be normalized. Initially, the listener's orientation
     * is along the Z axis, looking "into" the screen <i>(0, 0, -1)</i>.
     *
     * @param v the listener's new orientation.
     * @see SoundSource#setPosition(org.jsfml.system.Vector3f)
     */
    public static void setDirection(Vector3f v) {
        setDirection(v.x, v.y, v.z);
    }

    /**
     * Gets the listener's current orientation or "view direction" in the scene.
     *
     * @return the listener's current orientation in the scene.
     */
    public static native Vector3f getDirection();

    //cannot instantiate
    private Listener() {
    }
}
