package org.jsfml.graphics;

import org.jsfml.internal.Const;
import org.jsfml.internal.JSFML;
import org.jsfml.internal.NotNull;
import org.jsfml.system.Vector2i;

/**
 * Interface for read-only textures.
 * <p/>
 * It provides methods to can gain information from a texture, but none to modify it
 * in any way.
 * <p/>
 * Note that this interface is expected to be implemented by a {@link Texture}.
 * It is not recommended to be implemented outside of the JSFML API.
 *
 * @see Const
 */
@JSFML
public interface ConstTexture extends Const {
    /**
     * Gets the dimensions of the texture.
     *
     * @return the dimensions of the texture.
     */
    public Vector2i getSize();

    /**
     * Copies this texture to an editable {@link Image}.
     *
     * @return the image that contains a coyp of the texure's contents.
     */
    public Image copyToImage();

    /**
     * Activates the texture for rendering.
     *
     * @param coordinateType the coordinate type to use.
     */
    public void bind(@NotNull Texture.CoordinateType coordinateType);

    /**
     * Activates the texture for rendering, using the
     * {@link Texture.CoordinateType#NORMALIZED} coordinate type..
     */
    public void bind();

    /**
     * Checks whether the smooth filter is enabled.
     *
     * @return {@code true} if enabled, {@code false} if disabled.
     */
    public boolean isSmooth();

    /**
     * Checks whether texture repeating is enabled.
     *
     * @return {@code true} if enabled, {@code false} if disabled.
     */
    public boolean isRepeated();
}
