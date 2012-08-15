package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.SFMLNativeObject;

/**
 * Defines a drawable set of one or multiple 2D primitives.
 */
public class VertexArray extends SFMLNativeObject implements Drawable {
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

    @Override
    protected native void nativeDelete();

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
        if (i < 0 || i >= getVertexCount())
            throw new IndexOutOfBoundsException(Integer.toString(i));

        return nativeGetVertex(i);
    }

    /**
     * Gets all vertices from the vertex array.
     *
     * @return An array containing all the vertices in the vertex array.
     */
    public Vertex[] getVertices() {
        int n = getVertexCount();
        Vertex[] vertices = new Vertex[n];

        for (int i = 0; i < n; i++)
            vertices[i] = nativeGetVertex(i);

        return vertices;
    }

    private native void nativeSetVertex(int i, Vertex v);

    /**
     * Sets a vertex in the array.
     *
     * @param i      The index at which to set.
     * @param vertex The vertex to set at the given index.
     */
    public void setVertex(int i, @NotNull Vertex vertex) {
        if (vertex == null)
            throw new NullPointerException("vertex must not be null.");

        if (i < 0 || i >= getVertexCount())
            throw new IndexOutOfBoundsException(Integer.toString(i));

        nativeSetVertex(i, vertex);
    }

    /**
     * Clears the vertex array, removing all vertices from it.
     */
    public native void clear();

    private native void nativeResize(int n);

    /**
     * Resizes the vertex array.
     *
     * @param size The new size of the vertex array.
     */
    public void resize(int size) {
        if (size < 0)
            throw new NullPointerException("size must be non-negative.");

        nativeResize(size);
    }

    private native void nativeAppend(Vertex v);

    /**
     * Appends a set of vertices to the end of the vertex array.
     *
     * @param vertices The vertices to add to the array.
     */
    public void append(@NotNull Vertex... vertices) {
        if (vertices == null)
            throw new NullPointerException("vertices must not be null.");

        for (Vertex v : vertices) {
            if (v == null)
                throw new NullPointerException("None of the vertices must be null.");

            nativeAppend(v);
        }
    }

    private native void nativeSetPrimitiveType(PrimitiveType type);

    /**
     * Sets the primitive type to draw.
     *
     * @param type The primitive type.
     */
    public void setPrimitiveType(@NotNull PrimitiveType type) {
        if (type == null)
            throw new NullPointerException("type must not be null.");

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

    private native void nativeDraw(RenderTarget target, RenderStates states);

    @Override
    public void draw(@NotNull RenderTarget target, @NotNull RenderStates states) {
        if(target == null)
            throw new NullPointerException("target must not be null");

        if(states == null)
            throw new NullPointerException("states must not be null");

        nativeDraw(target, states);
    }
}
