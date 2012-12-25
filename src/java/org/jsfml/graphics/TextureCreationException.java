package org.jsfml.graphics;

/**
 * Thrown if a texture failed to be created.
 */
public class TextureCreationException extends Exception {
    private static final long serialVersionUID = -3423733954575177518L;

    /**
     * Constructs a new texture creation exception with the specified message.
     *
     * @param message the detail message.
     */
    public TextureCreationException(String message) {
        super(message);
    }
}
