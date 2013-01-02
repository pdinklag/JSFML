package org.jsfml.graphics;

/**
 * Thrown if a vertex or fragment shader source could not be compiled or linked.
 */
public class ShaderSourceException extends Exception {
    private static final long serialVersionUID = -6514888818053624276L;

    /**
     * Constructs a shader compilation exception with the specified message.
     *
     * @param msg the error message.
     */
    public ShaderSourceException(String msg) {
        super(msg);
    }
}
