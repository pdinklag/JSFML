package org.jsfml.graphics;

import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Interface for render targets.
 */
public interface RenderTarget {
    /**
     * Clears the render target and fills it with a constant color.
     *
     * @param color The color to fill the target with.
     */
    public void clear(Color color);

    /**
     * Sets the target's view.
     *
     * @param view The target's new view.
     */
    public void setView(ConstView view);

    /**
     * Gets the target's current view.
     *
     * @return The target's current view.
     */
    public ConstView getView();

    /**
     * Gets the target's default view.
     *
     * @return The target's default view.
     */
    public ConstView getDefaultView();

    /**
     * Computes the viewport of the given view applied to this render target.
     *
     * @param view The view.
     * @return The viewport of the given view applied to this render target.
     */
    public IntRect getViewport(View view);

    /**
     * Converts a point from target coordinates to world coordinates using the target's
     * current view.
     *
     * @param point The point on the render target.
     * @return The matching point in the world.
     */
    public Vector2f mapPixelToCoords(Vector2i point);

    /**
     * Converts a point from target coordinates to world coordinates for a certain view.
     *
     * @param point The point on the render target.
     * @param view  The view to use for conversion.
     * @return The matching point in the world.
     */
    public Vector2f mapPixelToCoords(Vector2i point, View view);

    /**
     * Converts a point from world coordinates to target coordinates using the target's
     * current view.
     *
     * @param point The point in the world.
     * @return The matching point on the render target.
     */
    public Vector2i mapCoordsToPixel(Vector2f point);

    /**
     * Converts a point from world coordinates to target coordinates for a certain view.
     *
     * @param point The point in the world.
     * @param view  The view to use for conversion.
     * @return The matching point on the render target.
     */
    public Vector2i mapCoordsToPixel(Vector2f point, View view);

    /**
     * Draws a drawable object to the render target using the default render states.
     *
     * @param drawable The object to draw.
     */
    public void draw(Drawable drawable);

    /**
     * Draws a drawable object to the render target using the given render states.
     *
     * @param drawable     The object to draw.
     * @param renderStates The render states to use for drawing.
     */
    public void draw(Drawable drawable, RenderStates renderStates);

    /**
     * Draws a primitive of the given type using the given vertices and the default render states.
     *
     * @param vertices The vertices to draw.
     * @param type     The type of OpenGL primitive to draw.
     */
    public void draw(Vertex[] vertices, PrimitiveType type);

    /**
     * Draws a primitive of the given type using the given vertices and the given render states.
     *
     * @param vertices The vertices to draw.
     * @param type     The type of OpenGL primitive to draw.
     * @param states   The render states to use for drawing.
     */
    public void draw(Vertex[] vertices, PrimitiveType type, RenderStates states);

    /**
     * Gets the size of the render region.
     *
     * @return The size of the render region.
     */
    public Vector2i getSize();

    /**
     * Pushes the current OpenGL states and matrices to the stack.
     */
    public void pushGLStates();

    /**
     * Pops the last OpenGL states and matrices from the top of the stack.
     */
    public void popGLStates();

    /**
     * Resets the internal OpenGL states so that the target is ready for drawing.
     */
    public void resetGLStates();
}
