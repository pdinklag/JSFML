package org.jsfml.graphics;

/**
 * Types of OpenGL primitives that can be rendered.
 */
public enum PrimitiveType {
    /**
     * List of individual points.
     */
    POINTS,

    /**
     * List of individual lines.
     */
    LINES,

    /**
     * List of connected lines. Each point gets connected to the previous point.
     */
    LINE_STRIP,

    /**
     * List of individual triangles.
     */
    TRIANGLES,

    /**
     * List of connected triangles. Each point uses the two previous points to form a triangle.
     */
    TRIANGLE_STRIP,

    /**
     * List of connected triangles. point uses the common center and the previous point to form a triangle.
     */
    TRIANGLE_FAN,

    /**
     * List of individual quads.
     */
    QUADS
}
