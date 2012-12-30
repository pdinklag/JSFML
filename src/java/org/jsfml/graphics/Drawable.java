package org.jsfml.graphics;

import org.jsfml.internal.NotNull;

/**
 * Interface for objects that can be drawn to a render target.
 * <p/>
 * Implementing classes can be conveniently used for the {@link RenderTarget#draw(Drawable)}
 * method, but serve no additional purpose otherwise.
 */
public interface Drawable {
    /**
     * Draws the object to a render target.
     *
     * @param target the target to draw this object on.
     * @param states the current render states.
     */
    void draw(@NotNull RenderTarget target, @NotNull RenderStates states);
}
