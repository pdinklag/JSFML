package org.jsfml.graphics;

import org.jsfml.NotNull;

/**
 * Interface for objects that can be drawn to a render target.
 */
public interface Drawable {
    /**
     * Draws the object to a render target.
     *
     * @param target The target to draw this object on.
     * @param states The current render states.
     */
    void draw(@NotNull RenderTarget target, @NotNull RenderStates states);
}
