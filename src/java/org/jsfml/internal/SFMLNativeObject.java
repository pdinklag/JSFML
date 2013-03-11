package org.jsfml.internal;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

/**
 * Abstract base class for classes bound to SFML C++ objects.
 * <p/>
 * This class serves as a communication interface between native SFML objects and their Java
 * representations by linking the Java object to the object's native pointer.
 * <p/>
 * There should be no reason whatsoever to use this class outside of
 * JSFML itself.
 */
@Intercom
public abstract class SFMLNativeObject {
    /**
     * Pointer to the underlying SFML object.
     */
    @Intercom
    private long ptr = 0;

    /**
     * "Extra Pointers".
     * <p/>
     * These are pointers dynamically cast to abstract supertypes of the same SFML object.
     * <p/>
     * Holding these is necessary, because all the C++ type information gets lost in the
     * process of storing a pointer in a Java long field. When casting them back
     * to SFML object pointers, the correct virtual table offset has to be used
     * for calls to methods of abstract types.
     */
    @Intercom
    private final LongBuffer exPtr =
            ByteBuffer.allocateDirect(ExPtr.NUM << 3).asLongBuffer();

    /**
     * If this is {@code true}, the underlying object is merely "wrapped" and not
     * "managed" by JSFML.
     * <p/>
     * If an object is managed by JSFML, it will be deleted using the <i>delete</i>
     * operator when the Java object is finalized. Wrapped objects are expected
     * to be cleaned up by SFML and will simply be abandoned upon finalization.
     */
    private boolean wrapped;

    /**
     * Constructs a JSFML native object by invoking the {@link #nativeCreate()}
     * method and retrieving a pointer to the SFML object in the JVM heap.
     */
    @SuppressWarnings("deprecation")
    protected SFMLNativeObject() {
        SFMLNative.loadNativeLibraries();

        ptr = nativeCreate();

        if (ptr == 0)
            throw new JSFMLError("nativeCreate() yielded a NULL pointer: " + this);

        nativeSetExPtr();

        wrapped = false;
    }

    /**
     * Wraps an JSFML native object around an already existing native SFML object.
     *
     * @param wrap the pointer to the native SFML object in the JNI heap.
     * @deprecated Use of this constructor may cause undefined behaviour and is not supported.
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    protected SFMLNativeObject(long wrap) {
        SFMLNative.loadNativeLibraries();

        if (wrap == 0)
            throw new JSFMLError("Tried to wrap around a NULL pointer: " + this);

        ptr = wrap;
        nativeSetExPtr();

        wrapped = true;
    }

    final void setJavaManaged(boolean javaManaged) {
        wrapped = !javaManaged;
    }

    /**
     * Creates a new native SFML object of the represented SFML class in the JVM memory heap.
     *
     * @return the pointer to the newly created native SFML object.
     * @deprecated Use of this method may cause undefined behaviour and is not supported.
     */
    @Deprecated
    protected abstract long nativeCreate();

    /**
     * This method is expected to fill the extra pointers array, if any.
     * <p/>
     * It is called after the object has been created or wrapped around an already
     * existing pointer.
     *
     * @deprecated Use of this method may cause undefined behaviour and is not supported.
     */
    @Deprecated
    protected abstract void nativeSetExPtr();

    /**
     * Deletes the underlying SFML object.
     *
     * @deprecated Use of this method may cause undefined behaviour and is not supported.
     */
    @Deprecated
    protected abstract void nativeDelete();

    @Override
    @SuppressWarnings("deprecation")
    protected void finalize() throws Throwable {
        if (!wrapped && ptr != 0)
            nativeDelete();

        ptr = 0;
        for (int i = 0; i < ExPtr.NUM; i++)
            exPtr.put(i, 0);

        super.finalize();
    }
}
