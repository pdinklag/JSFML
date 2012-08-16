package org.jsfml.graphics;

/**
 * Provides a generic implementation of the {@link Drawable#draw(RenderTarget, RenderStates)}
 * method for native SFML drawables.
 */
final class DrawableNativeImpl {
    static native void draw(Drawable drawable, RenderTarget target, RenderStates states);
}
