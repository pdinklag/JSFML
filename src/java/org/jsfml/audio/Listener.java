package org.jsfml.audio;

import org.jsfml.JSFMLException;
import org.jsfml.SFMLNative;
import org.jsfml.system.Vector3f;

/**
 * The audio listener, representing the point in the scene from where all the sounds are heard.
 */
public class Listener {
    static {
        try {
            SFMLNative.loadNativeLibraries();
        } catch (JSFMLException ex) {
            throw new UnsatisfiedLinkError(ex.getMessage());
        }
    }

    /**
     * Sets the global sound volume.
     * <p/>
     * The default global volume is 100 (maximum).
     *
     * @param volume The global sound volume, ranging between 0 and 100.
     */
    public static native void setGlobalVolume(float volume);

    /**
     * Gets the global sound volume.
     *
     * @return The global sound volume, ranging between 0 and 100.
     */
    public static native float getGlobalVolume();

    /**
     * Sets the position of the listener in the scene.
     *
     * @param x The X coordinate of the listener's new position.
     * @param y The Y coordinate of the listener's new position.
     * @param z The Z coordinate of the listener's new position.
     */
    public static native void setPosition(float x, float y, float z);

    /**
     * Sets the position of the listener in the scene.
     *
     * @param v The listener's new position.
     */
    public static void setPosition(Vector3f v) {
        setPosition(v.x, v.y, v.z);
    }

    /**
     * Gets the listener's current position in the scene.
     *
     * @return The listener's current position in the scene.
     */
    public static native Vector3f getPosition();

    /**
     * Sets the orientation of the listener in the scene.
     *
     * @param x The X component of the listener's new orientation.
     * @param y The Y component of the listener's new orientation.
     * @param z The Z component of the listener's new orientation.
     */
    public static native void setDirection(float x, float y, float z);

    /**
     * Sets the orientation of the listener in the scene.
     *
     * @param v The listener's new orientation.
     */
    public static void setDirection(Vector3f v) {
        setDirection(v.x, v.y, v.z);
    }

    /**
     * Gets the listener's current orientation in the scene.
     *
     * @return The listener's current orientation in the scene.
     */
    public static native Vector3f getDirection();
}
