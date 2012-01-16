package org.jsfml.graphics;

import org.jsfml.SFMLNativeObject;

/**
 * Defines a drawable set of one or multiple 2D primitives.
 */
public class VertexArray extends SFMLNativeObject implements Drawable {
    private int numVertices = 0;

    /**
     * Creates a new empty vertex array.
     */
    public VertexArray() {
        super();
    }

    /**
     * Creates a new empty vertex array.
     *
     * @param type The primitive type to draw.
     */
    public VertexArray(PrimitiveType type) {
        this();
        setPrimitiveType(type);
    }

    /**
     * Creates a new vertex array.
     *
     * @param type        The primitive type to draw.
     * @param vertexCount The initial vertex count.
     */
    public VertexArray(PrimitiveType type, int vertexCount) {
        this(type);
        resize(vertexCount);
    }

    @Override
    protected native long nativeCreate();

    /**
     * Gets the amount of vertices stored in this vertex array.
     *
     * @return The amount of vertices stored in this vertex array.
     */
    public native int getVertexCount();

    private native Vertex nativeGetVertex(int i);

    /**
     * Gets a vertex from the array.
     *
     * @param i The index of the vertex to get.
     * @return The vertex at the given index.
     */
    public Vertex getVertex(int i) {
        if (i < 0 || i >= numVertices)
            throw new ArrayIndexOutOfBoundsException(i);

        return nativeGetVertex(i);
    }

    private native void nativeSetVertex(int i, Vertex v);

    /**
     * Sets a vertex in the array.
     *
     * @param i      The index at which to set.
     * @param vertex The vertex to set at the given index.
     */
    public void setVertex(int i, Vertex vertex) {
        if (vertex == null)
            throw new IllegalArgumentException("vertex must not be null.");

        if (i < 0 || i >= numVertices)
            throw new ArrayIndexOutOfBoundsException(i);

        nativeSetVertex(i, vertex);
    }

    private native void nativeClear();

    /**
     * Clears the vertex array.
     */
    public void clear() {
        nativeClear();
        numVertices = 0;
    }

    private native void nativeResize(int n);

    /**
     * Resizes the vertex array.
     *
     * @param size The new size of the vertex array.
     */
    public void resize(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("size must be greater than zero.");

        nativeResize(size);
        numVertices = size;
    }

    private native void nativeAppend(Vertex v);

    /**
     * Appends a vertex to the end of the vertex array.
     *
     * @param v The vertex to append.
     */
    public void append(Vertex v) {
        if (v == null)
            throw new IllegalArgumentException("vertex must not be null.");

        nativeAppend(v);
    }

    private native void nativeSetPrimitiveType(PrimitiveType type);

    /**
     * Sets the primitive type to draw.
     *
     * @param type The primitive type.
     */
    public void setPrimitiveType(PrimitiveType type) {
        if (type == null)
            throw new IllegalArgumentException("type must not be null.");

        nativeSetPrimitiveType(type);
    }

    private native int nativeGetPrimitiveType();

    /**
     * Gets the current primitive type to draw.
     *
     * @return The current primitive type.
     */
    public PrimitiveType getPrimitiveType() {
        return PrimitiveType.values()[nativeGetPrimitiveType()];
    }

    /**
     * Gets the axis-aligned bounding box of the shape represented by this vertex array.
     *
     * @return The axis-aligned bounding box of the shape represented by this vertex array.
     */
    public native FloatRect getBounds();
}
