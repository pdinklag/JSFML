package org.jsfml.graphics;

/**
 * Enumeration of supported primitive types.
 *
 * @see VertexArray
 */
public enum PrimitiveType {
    /**
     * A list of individual points.
     */
    POINTS,

    /**
     * A list of individual lines.
     */
    LINES,

    /**
     * A list of connected lines, where each point gets connected to the previous point.
     */
    LINE_STRIP,

    /**
     * A list of individual triangles.
     */
    TRIANGLES,

    /**
     * A list of connected triangles, where ech point uses the two previous points
     * to form a triangle.
     */
    TRIANGLE_STRIP,

    /**
     * A list of connected triangles, where each point uses the common center and the
     * previous point to form a triangle.
     */
    TRIANGLE_FAN,

    /**
     * A list of individual quads.
     */
    QUADS
}
