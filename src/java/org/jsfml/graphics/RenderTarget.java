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
     * @param color the color to fill the target with.
     */
    public void clear(Color color);

    /**
     * Sets the target's view.
     *
     * @param view the target's new view.
     */
    public void setView(ConstView view);

    /**
     * Gets the target's current view.
     *
     * @return the target's current view.
     */
    public ConstView getView();

    /**
     * Gets the target's default view.
     *
     * @return the target's default view.
     */
    public ConstView getDefaultView();

    /**
     * Computes the viewport of the given view applied to this render target.
     *
     * @param view the view.
     * @return the viewport of the given view applied to this render target.
     */
    public IntRect getViewport(ConstView view);

    /**
     * Converts a point from target coordinates to world coordinates using the target's
     * current view.
     *
     * @param point the point on the render target.
     * @return the matching point in the world.
     */
    public Vector2f mapPixelToCoords(Vector2i point);

    /**
     * Converts a point from target coordinates to world coordinates for a certain view.
     *
     * @param point the point on the render target.
     * @param view  the view to use for conversion.
     * @return the matching point in the world.
     */
    public Vector2f mapPixelToCoords(Vector2i point, ConstView view);

    /**
     * Converts a point from world coordinates to target coordinates using the target's
     * current view.
     *
     * @param point the point in the world.
     * @return the matching point on the render target.
     */
    public Vector2i mapCoordsToPixel(Vector2f point);

    /**
     * Converts a point from world coordinates to target coordinates for a certain view.
     *
     * @param point the point in the world.
     * @param view  the view to use for conversion.
     * @return the matching point on the render target.
     */
    public Vector2i mapCoordsToPixel(Vector2f point, ConstView view);

    /**
     * Draws a drawable object to the render target using the default render states.
     *
     * @param drawable the object to draw.
     */
    public void draw(Drawable drawable);

    /**
     * Draws a drawable object to the render target using the given render states.
     *
     * @param drawable     the object to draw.
     * @param renderStates the render states to use for drawing.
     */
    public void draw(Drawable drawable, RenderStates renderStates);

    /**
     * Draws a primitive of the given type using the given vertices and the default render states.
     *
     * @param vertices the vertices to draw.
     * @param type     the type of OpenGL primitive to draw.
     */
    public void draw(Vertex[] vertices, PrimitiveType type);

    /**
     * Draws a primitive of the given type using the given vertices and the given render states.
     *
     * @param vertices the vertices to draw.
     * @param type     the type of OpenGL primitive to draw.
     * @param states   the render states to use for drawing.
     */
    public void draw(Vertex[] vertices, PrimitiveType type, RenderStates states);

    /**
     * Gets the size of the render region.
     *
     * @return the size of the render region.
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
