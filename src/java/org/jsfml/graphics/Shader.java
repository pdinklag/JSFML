package org.jsfml.graphics;

import org.jsfml.SFMLNativeObject;
import org.jsfml.StreamUtil;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector3f;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Shader program consisting of a vertex and a fragment shader.
 */
public class Shader extends SFMLNativeObject {
    /**
     * Checks if shaders are available on this system.
     *
     * @return <tt>true</tt> if shaders are available, <tt>false</tt> otherwise.
     */
    public static native boolean isAvailable();

    /**
     * Shader type enumeration.
     */
    public static enum Type {
        /**
         * Type for vertex shaders.
         */
        VERTEX,

        /**
         * Type for fragment a.k.a. pixel shaders.
         */
        FRAGMENT
    }

    /**
     * Creates a new shader.
     */
    public Shader() {
        super();
    }

    @Override
    protected native long nativeCreate();

    private native boolean nativeLoadFromSource(String source, Type shaderType);

    private native boolean nativeLoadFromSource(String vertSource, String fragSource);

    /**
     * Attempts to load a shader from a source code.
     *
     * @param source     The source code.
     * @param shaderType The shader type.
     * @return <tt>true</tt> if the shader was successfully loaded, <tt>false</tt> otherwise.
     */
    public boolean loadFromSource(String source, Type shaderType) {
        if (source == null)
            throw new IllegalArgumentException("source must not be null.");

        if (shaderType == null)
            throw new IllegalArgumentException("shaderType must not be null.");

        return nativeLoadFromSource(source, shaderType);
    }

    /**
     * Attempts to load a shader from a source code.
     *
     * @param vertexShaderSource   The vertex shader's source code.
     * @param fragmentShaderSource The fragment shader's source code.
     * @return <tt>true</tt> if the shader was successfully loaded, <tt>false</tt> otherwise.
     */
    public boolean loadFromSource(String vertexShaderSource, String fragmentShaderSource) {
        if (vertexShaderSource == null)
            throw new IllegalArgumentException("vertexShaderSource must not be null.");

        if (fragmentShaderSource == null)
            throw new IllegalArgumentException("fragmentShaderSource must not be null.");

        return nativeLoadFromSource(vertexShaderSource, fragmentShaderSource);
    }

    /**
     * Fully loads all available bytes from an {@link InputStream} and attempts to load the shader from it.
     *
     * @param in         The input stream to read from.
     * @param shaderType The shader type.
     * @return <tt>true</tt> if the shader was successfully loaded, <tt>false</tt> otherwise.
     * @throws IOException In case an I/O error occurs.
     */
    public boolean loadFromStream(InputStream in, Type shaderType) throws IOException {
        if (shaderType == null)
            throw new IllegalArgumentException("shaderType must not be null.");

        return loadFromSource(new String(StreamUtil.readStream(in)), shaderType);
    }

    /**
     * Fully loads all available bytes from an {@link InputStream} and attempts to load the shader from it.
     *
     * @param vertexShaderIn   The input stream to read the vertex shader from.
     * @param fragmentShaderIn The input stream to read the fragment shader from.
     * @return <tt>true</tt> if the shader was successfully loaded, <tt>false</tt> otherwise.
     * @throws IOException In case an I/O error occurs.
     */
    public boolean loadFromStream(InputStream vertexShaderIn, InputStream fragmentShaderIn) throws IOException {
        return loadFromSource(
                new String(StreamUtil.readStream(vertexShaderIn)),
                new String(StreamUtil.readStream(fragmentShaderIn)));
    }

    /**
     * Attempts to load the shader from a file.
     *
     * @param file       The file to load.
     * @param shaderType The shader type.
     * @return <tt>true</tt> if the shader was successfully loaded, <tt>false</tt> otherwise.
     * @throws IOException In case an I/O error occurs.
     */
    public boolean loadFromFile(File file, Type shaderType) throws IOException {
        if (shaderType == null)
            throw new IllegalArgumentException("shaderType must not be null.");

        return loadFromSource(new String(StreamUtil.readFile(file)), shaderType);
    }

    /**
     * Attempts to load the shader from files.
     *
     * @param vertexShaderFile   The file to read the vertex shader from.
     * @param fragmentShaderFile The file to read the fragment shader from.
     * @return <tt>true</tt> if the shader was successfully loaded, <tt>false</tt> otherwise.
     * @throws IOException In case an I/O error occurs.
     */
    public boolean loadFromFile(File vertexShaderFile, File fragmentShaderFile) throws IOException {
        return loadFromSource(
                new String(StreamUtil.readFile(vertexShaderFile)),
                new String(StreamUtil.readFile(fragmentShaderFile)));
    }

    private native void nativeSetParameter(String name, float x);

    /**
     * Sets a float parameter (<tt>float</tt>) value in the shader.
     *
     * @param name The parameter's name.
     * @param x    The parameter's value.
     */
    public void setParameter(String name, float x) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");

        nativeSetParameter(name, x);
    }

    private native void nativeSetParameter(String name, float x, float y);

    /**
     * Sets a 2-component-float (<tt>vec2</tt>) parameter value in the shader.
     *
     * @param name The parameter's name.
     * @param x    The parameter's value.
     * @param y    The parameter's value.
     */
    public void setParameter(String name, float x, float y) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");

        nativeSetParameter(name, x, y);
    }

    /**
     * Sets a 2-component-float (<tt>vec2</tt>) parameter value in the shader.
     *
     * @param name The parameter's name.
     * @param v    The parameter's value.
     */
    public void setParameter(String name, Vector2f v) {
        setParameter(name, v.x, v.y);
    }

    private native void nativeSetParameter(String name, float x, float y, float z);

    /**
     * Sets a 3-component-float (<tt>vec3</tt>) parameter value in the shader.
     *
     * @param name The parameter's name.
     * @param x    The parameter's value.
     * @param y    The parameter's value.
     * @param z    The parameter's value.
     */
    public void setParameter(String name, float x, float y, float z) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");

        nativeSetParameter(name, x, y, z);
    }

    /**
     * Sets a 3-component-float (<tt>vec3</tt>) parameter value in the shader.
     *
     * @param name The parameter's name.
     * @param v    The parameter's value.
     */
    public void setParameter(String name, Vector3f v) {
        setParameter(name, v.x, v.y, v.z);
    }

    private native void nativeSetParameter(String name, float x, float y, float z, float w);

    /**
     * Sets a 4-component-float (<tt>vec4</tt>) parameter value in the shader.
     *
     * @param name The parameter's name.
     * @param x    The parameter's value.
     * @param y    The parameter's value.
     * @param z    The parameter's value.
     * @param w    The parameter's value.
     */
    public void setParameter(String name, float x, float y, float z, float w) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");

        nativeSetParameter(name, x, y, z, w);
    }

    /**
     * Sets a color (vec4) parameter value in the shader.
     *
     * @param name  The parameter's name.
     * @param color The parameter's value.
     */
    public void setParameter(String name, Color color) {
        setParameter(name,
                (float) color.getR() / 255.0f,
                (float) color.getG() / 255.0f,
                (float) color.getB() / 255.0f,
                (float) color.getA() / 255.0f);
    }

    private native void nativeSetParameter(String name, Transform xform);

    /**
     * Sets a matrix (mat4) parameter value in the shader.
     *
     * @param name  The parameter's name.
     * @param xform The parameter's value.
     */
    public void setParameter(String name, Transform xform) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");

        if (xform == null)
            throw new IllegalArgumentException("xform must not be null.");

        nativeSetParameter(name, xform);
    }

    private native void nativeSetParameter(String name, Texture texture);

    /**
     * Sets a texture (sampler2D) parameter value in the shader.
     *
     * @param name    The parameter's name.
     * @param texture The parameter's value.
     */
    public void setParameter(String name, Texture texture) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");

        if (texture == null)
            throw new IllegalArgumentException("texture must not be null.");

        nativeSetParameter(name, texture);
    }

    /**
     * Activates the shader for rendering.
     */
    public native void bind();

    /**
     * Deactivates the shader for rendering.
     */
    public native void unbind();
}
