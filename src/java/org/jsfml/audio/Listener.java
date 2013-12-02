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

    //cache
    private static float volume = 100;
    private static Vector3f position = Vector3f.ZERO;
    private static Vector3f direction = Vector3f.ZERO;

    private static native void nativeSetGlobalVolume(float volume);

    /**
     * Sets the global sound volume.
     * <p/>
     * The default global volume is 100 (maximum).
     *
     * @param volume the global sound volume, ranging between 0 (silence) and 100 (full volume).
     */
    public static void setGlobalVolume(float volume) {
        nativeSetGlobalVolume(volume);
        Listener.volume = volume;
    }

    /**
     * Gets the global sound volume.
     *
     * @return the global sound volume, ranging between 0 (silence) and 100 (full volume).
     */
    public static float getGlobalVolume() {
        return volume;
    }

    private static native void nativeSetPosition(float x, float y, float z);

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
    public static void setPosition(float x, float y, float z) {
        setPosition(new Vector3f(x, y, z));
    }

    /**
     * Sets the position of the listener in the scene.
     * <p/>
     * Initially, the listener is located at the origin <i>(0, 0, 0)</i>.
     *
     * @param v the listener's new position.
     * @see SoundSource#setPosition(org.jsfml.system.Vector3f)
     */
    public static void setPosition(Vector3f v) {
        nativeSetPosition(v.x, v.y, v.z);
        Listener.position = v;
    }

    /**
     * Gets the listener's current position in the scene.
     *
     * @return the listener's current position in the scene.
     */
    public static Vector3f getPosition() {
        return position;
    }

    private static native void nativeSetDirection(float x, float y, float z);

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
    public static void setDirection(float x, float y, float z) {
        setDirection(new Vector3f(x, y, z));
    }

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
        nativeSetDirection(v.x, v.y, v.z);
        Listener.direction = v;
    }

    /**
     * Gets the listener's current orientation or "view direction" in the scene.
     *
     * @return the listener's current orientation in the scene.
     */
    public static Vector3f getDirection() {
        return direction;
    }

    //cannot instantiate
    private Listener() {
    }
}
