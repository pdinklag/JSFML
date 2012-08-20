package org.jsfml;

/**
 * Abstract base for classes bound to SFML C++ types.
 * <p/>
 * This class serves as a communication interface between native SFML objects and their Java
 * representations. There should be no reason whatsoever to use this class outside of
 * JSFML itself.
 */
@Intercom
public abstract class SFMLNativeObject {
    private static boolean debug = false;
    private static int numManaged = 0, numWrapped = 0;

    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Enables or disables SFMLNativeObject debug output to the standard output stream.
     * When enabled, the creation, wrapping and deletion of all SFML native objects will be logged to the
     * standard output stream for debugging purposes.
     *
     * @param b Whether or not to enable debug information logging.
     */
    @SuppressWarnings("deprecation")
    public static void setDebug(boolean b) {
        debug = b;
        Runtime.runFinalizersOnExit(b);
    }

    @Intercom
    private long ptr = 0;

    @Intercom
    private final long[] exPtr = new long[ExPtr.NUM];

    private boolean wrapped;

    /**
     * Creates an JSFML native object by invoking the <code>nativeCreate</code> method and retrieving
     * a pointer to the SFML object in the JVM heap.
     */
    @SuppressWarnings("deprecation")
    protected SFMLNativeObject() {
        ptr = nativeCreate();

        if (ptr == 0)
            throw new JSFMLError("nativeCreate() yielded a NULL pointer: " + this);

        wrapped = false;

        numManaged++;
        if (debug)
            System.out.println("SFMLNativeObject [CREATE] " + this + " - ptr=0x" + Long.toHexString(ptr).toUpperCase() + " - numManaged=" + numManaged);
    }

    /**
     * Wraps an JSFML native object around an already existing native SFML object.
     *
     * @param wrap The pointer to the native SFML object in the JNI heap.
     * @deprecated This is intended for internal use only. Using this from outside may cause
     *             undefined behaviour.
     */
    @Deprecated
    protected SFMLNativeObject(long wrap) {
        if (wrap == 0)
            throw new JSFMLError("Tried to wrap around a NULL pointer: " + this);

        ptr = wrap;
        wrapped = true;

        numWrapped++;
        if (debug)
            System.out.println("SFMLNativeObject [WRAP]   " + this + " - ptr=0x" + Long.toHexString(ptr).toUpperCase() + " - numWrapped=" + numWrapped);
    }

    final void setJavaManaged(boolean javaManaged) {
        boolean wasWrapped = wrapped;
        wrapped = !javaManaged;

        if (wasWrapped && !wrapped) {
            numWrapped--;
            numManaged++;
            System.out.println("SFMLNativeObject [MANAGE]   " + this + " - ptr=0x" + Long.toHexString(ptr).toUpperCase() + " - numManaged=" + numManaged + " - numWrapped=" + numWrapped);
        } else if (!wasWrapped && wrapped) {
            numWrapped++;
            numManaged--;
            System.out.println("SFMLNativeObject [UNMANAGE]   " + this + " - ptr=0x" + Long.toHexString(ptr).toUpperCase() + " - numManaged=" + numManaged + " - numWrapped=" + numWrapped);
        }
    }

    /**
     * Creates a new native SFML object of the represented SFML class in the JNI memory heap.
     * <p/>
     * This method is also expected to fill the extra pointers array, if any.
     * <p/>
     * NOTE: This method is intended for internal use <i>only</i>.
     * Using this from outside may cause memory leaks.
     *
     * @return The pointer to the newly created native SFML object.
     */
    protected abstract long nativeCreate();

    /**
     * Deletes the underlying SFML object.
     * <p/>
     * NOTE: This method is inteded for internal use <i>only</i>.
     * Using this from outside may cause undefined behaviour and crashes.
     */
    protected abstract void nativeDelete();

    @Override
    @SuppressWarnings("deprecation")
    protected void finalize() throws Throwable {
        if (!wrapped) {
            if (ptr != 0)
                nativeDelete();

            numManaged--;
            if (debug)
                System.out.println("SFMLNativeObject [DELETE] " + this + " - ptr=0x" + Long.toHexString(ptr).toUpperCase() + " - numManaged=" + numManaged);
        } else {
            numWrapped--;
            if (debug)
                System.out.println("SFMLNativeObject [UNWRAP] " + this + " - ptr=0x" + Long.toHexString(ptr).toUpperCase() + " - numWrapped=" + numWrapped);
        }

        ptr = 0;
        for (int i = 0; i < exPtr.length; i++)
            exPtr[i] = 0;

        super.finalize();
    }
}
