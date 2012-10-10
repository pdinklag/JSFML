package org.jsfml.graphics;

import org.jsfml.Const;
import org.jsfml.JSFML;
import org.jsfml.system.Vector2f;

/**
 * Interface for read-only views.
 * <p/>
 * It provides methods to can gain information from a view, but none to modify it
 * in any way.
 * <p/>
 * Note that this interface is expected to be implemented by a {@link View}. It is not
 * recommended to be implemented outside of the JSFML API.
 *
 * @see Const
 */
@JSFML
public interface ConstView extends Const {
    /**
     * Gets the current center of the view.
     *
     * @return The current center of the view.
     */
    public Vector2f getCenter();

    /**
     * Gets the current dimensions of the view in pixels.
     *
     * @return The current dimensions of the view in pixels.
     */
    public Vector2f getSize();

    /**
     * Gets the current rotation angle of the view in degrees.
     *
     * @return The current rotation angle of the view in degrees.
     */
    public float getRotation();

    /**
     * Gets the current viewport rectangle of this view.
     *
     * @return The current viewport rectangle of this view.
     */
    public FloatRect getViewport();

    /**
     * Gets the view's current transformation matrix as determined by its center,
     * size and rotation angle.
     *
     * @return The view's current transformation matrix.
     */
    public Transform getTransform();

    /**
     * Gets the inverse of the view's current transformation matrix as determined
     * by its center, size and rotation angle.
     *
     * @return The inverse of the view's current transformation matrix.
     */
    public Transform getInverseTransform();
}
