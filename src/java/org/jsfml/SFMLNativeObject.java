package org.jsfml;

/**
 * Abstract base for classes bound to SFML C++ types.
 * <p/>
 * This class serves as a communication interface between native SFML objects and their Java
 * representations. There should be no reason whatsoever to use this class outside of
 * JSFML itself.
 */
public abstract class SFMLNativeObject {
    private static boolean debug = false;
    private static int numManaged = 0, numWrapped = 0;

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

    static {
        try {
            SFMLNative.loadNativeLibraries();
        } catch (JSFMLException ex) {
            throw new UnsatisfiedLinkError(ex.getMessage());
        }
    }

    private long ptr = 0;
    private boolean wrapped;

    /**
     * Creates an JSFML native object by invoking the <code>nativeCreate</code> method and retrieving
     * a pointer to the SFML object in the JVM heap.
     */
    protected SFMLNativeObject() {
        ptr = nativeCreate();
        wrapped = false;

        numManaged++;
        if (debug)
            System.out.println("SFMLNativeObject [CREATE] " + this + " - ptr=0x" + Long.toHexString(ptr).toUpperCase() + " - numManaged=" + numManaged);
    }

    /**
     * Wraps an JSFML native object around an already existing native SFML object.
     *
     * @param wrap The pointer to the native SFML object in the JNI heap.
     * @deprecated Do <b>NOT</b> play with this!
     */
    @Deprecated
    protected SFMLNativeObject(long wrap) {
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


    final void setPointer(long ptr) {
        this.ptr = ptr;
    }

    /**
     * Creates a new native SFML object of the represented SFML class in the JNI memory heap.
     *
     * @return The pointer to the newly created native SFML object.
     */
    protected abstract long nativeCreate();

    private native void nativeDelete();

    @Override
    protected void finalize() throws Throwable {
        if (!wrapped) {
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

        super.finalize();
    }
}
