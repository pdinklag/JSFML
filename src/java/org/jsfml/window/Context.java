package org.jsfml.window;

import org.jsfml.internal.SFMLNative;
import org.jsfml.internal.SFMLNativeObject;

/**
 * Holds a valid OpenGL drawing context.
 * <p/>
 * For every OpenGL call, a valid context is required. Using this class, by creating
 * an instance, you can obtain a valid context.
 * <p/>
 * This is only required if you do not have an active window that provides an OpenGL context.
 */
public final class Context extends SFMLNativeObject {
    private static final ThreadLocal<Context> threadContext = new ThreadLocal<>();

    /**
     * Creates and activates a valid OpenGL context on the current thread.
     * <p/>
     * If there already is a context in the current thread,
     * it will simply be activated.
     *
     * @return the activated OpenGL context.
     */
    public static Context getContext() {
        Context context = threadContext.get();
        if (context != null) {
            try {
                context.setActive(true);
            } catch (ContextActivationException ex) {
                //
            }
        } else {
            context = new Context();
            threadContext.set(context);
        }

        return context;
    }

    private Context() {
        SFMLNative.ensureDisplay();
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected void nativeSetExPtr() {
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native boolean nativeSetActive(boolean active);

    /**
     * Explictly activates or deactivates the OpenGL context.
     *
     * @param active {@code true} to activate, {@code false} to deactivate.
     * @throws ContextActivationException if activating or deactivating the context failed.
     */
    public void setActive(boolean active) throws ContextActivationException {
        if (!nativeSetActive(active)) {
            throw new ContextActivationException("Failed to " +
                    (active ? "activate" : "deactivate") +
                    " the context.");
        }
    }
}
