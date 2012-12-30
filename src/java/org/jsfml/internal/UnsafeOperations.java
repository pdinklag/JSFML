package org.jsfml.internal;

/**
 * Provides inherently unsafe operations on native SFML objects.
 * <p/>
 * These need to be public in order to maintain JSFML's package structure, but should by no means
 * used outside of JSFML.
 */
public final class UnsafeOperations {
    /**
     * Flags an SFML object as Java managed or unmanaged. Java managed objects will be destroyed using the
     * {@link SFMLNativeObject#nativeDelete()} method when this object gets finalized.
     * <p/>
     * This is used for JSFML to differentiate between explicitly self-constructed SFML objects
     * (using {@code new}) and SFML objects that are managed by other SFML objects, but require
     * a Java representation.
     * <p/>
     * Wrong use of this method will make the application prone to crashes and memory leaks,
     * so handle with extreme care.
     *
     * @param object  The SFML object wrapper.
     * @param managed Whether or not this object is managed by JSFML.
     */
    public static void manageSFMLObject(SFMLNativeObject object, boolean managed) {
        object.setJavaManaged(managed);
    }

    private UnsafeOperations() {
    }
}
