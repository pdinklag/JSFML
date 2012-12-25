package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.SFMLNative;
import org.jsfml.SFMLNativeObject;
import org.jsfml.StreamUtil;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector3f;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents a GLSL shader program, consisting of a vertex and a fragment shader.
 */
public class Shader extends SFMLNativeObject implements ConstShader {
    static {
        SFMLNative.loadNativeLibraries();
    }

    /**
     * Special type denoting that the texture of the object being drawn
     * should be used, which cannot be known before it is actually being drawn.
     *
     * @see Shader#setParameter(String, org.jsfml.graphics.Shader.CurrentTextureType)
     */
    public static final class CurrentTextureType {
        private CurrentTextureType() {
            //cannot instantiate from outside.
        }
    }

    /**
     * Special value denoting that the texture of the object being drawn
     * should be used, which cannot be known before it is actually being drawn.
     *
     * @see Shader#setParameter(String, org.jsfml.graphics.Shader.CurrentTextureType)
     */
    public static final CurrentTextureType CURRENT_TEXTURE = new CurrentTextureType();

    /**
     * Checks if shaders are available on this system.
     *
     * @return {@code true} if shaders are available, {@code false} otherwise.
     */
    public static native boolean isAvailable();

    /**
     * Enumeration of shader types.
     */
    public static enum Type {
        /**
         * Vertex shaders.
         */
        VERTEX,

        /**
         * Fragment shaders.
         */
        FRAGMENT
    }

    /**
     * Constructs a new shader.
     */
    public Shader() {
        super();
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected void nativeSetExPtr() {
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native boolean nativeLoadFromSource(String source, Type shaderType);

    private native boolean nativeLoadFromSource(String vertSource, String fragSource);

    /**
     * Attempts to load a shader from GLSL source code.
     *
     * @param source     the GLSL source code.
     * @param shaderType the shader type.
     */
    public void loadFromSource(@NotNull String source, @NotNull Type shaderType)
            throws IOException {

        if (source == null)
            throw new NullPointerException("source must not be null.");

        if (shaderType == null)
            throw new NullPointerException("shaderType must not be null.");

        if (!nativeLoadFromSource(source, shaderType))
            throw new IOException("Failed to load shader from source.");
    }

    /**
     * Attempts to load a shader from GLSL source code.
     *
     * @param vertexShaderSource   the vertex shader's GLSL source code.
     * @param fragmentShaderSource the fragment shader's GLSL source code.
     * @throws java.io.IOException in case an I/O error occurs.
     */
    public void loadFromSource(@NotNull String vertexShaderSource, @NotNull String fragmentShaderSource)
            throws IOException {

        if (vertexShaderSource == null)
            throw new NullPointerException("vertexShaderSource must not be null.");

        if (fragmentShaderSource == null)
            throw new NullPointerException("fragmentShaderSource must not be null.");

        if (!nativeLoadFromSource(vertexShaderSource, fragmentShaderSource))
            throw new IOException("Failed to load shader from source.");
    }

    /**
     * Fully loads all available bytes from an {@link InputStream}
     * and attempts to load the shader from it.
     *
     * @param in         the input stream to read from.
     * @param shaderType the shader type.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromStream(InputStream in, @NotNull Type shaderType) throws IOException {
        if (shaderType == null)
            throw new NullPointerException("shaderType must not be null.");

        loadFromSource(new String(StreamUtil.readStream(in)), shaderType);
    }

    /**
     * Fully loads all available bytes from an {@link InputStream}
     * and attempts to load the shader from it.
     *
     * @param vertexShaderIn   the input stream to read the vertex shader from.
     * @param fragmentShaderIn the input stream to read the fragment shader from.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromStream(InputStream vertexShaderIn, InputStream fragmentShaderIn) throws IOException {
        loadFromSource(
                new String(StreamUtil.readStream(vertexShaderIn)),
                new String(StreamUtil.readStream(fragmentShaderIn)));
    }

    /**
     * Attempts to load the shader from a file.
     *
     * @param file       the file to load.
     * @param shaderType the shader type.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromFile(File file, @NotNull Type shaderType) throws IOException {
        if (shaderType == null)
            throw new NullPointerException("shaderType must not be null.");

        loadFromSource(new String(StreamUtil.readFile(file)), shaderType);
    }

    /**
     * Attempts to load the shader from files.
     *
     * @param vertexShaderFile   the file to read the vertex shader from.
     * @param fragmentShaderFile the file to read the fragment shader from.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromFile(File vertexShaderFile, File fragmentShaderFile) throws IOException {
        loadFromSource(
                new String(StreamUtil.readFile(vertexShaderFile)),
                new String(StreamUtil.readFile(fragmentShaderFile)));
    }

    private native void nativeSetParameter(String name, float x);

    /**
     * Sets a float parameter ({@code float}) value in the shader.
     *
     * @param name the parameter's name.
     * @param x    the parameter's value.
     */
    public void setParameter(@NotNull String name, float x) {
        if (name == null)
            throw new NullPointerException("name must not be null.");

        nativeSetParameter(name, x);
    }

    private native void nativeSetParameter(String name, float x, float y);

    /**
     * Sets a 2-component-float ({@code vec2}) parameter value in the shader.
     *
     * @param name the parameter's name.
     * @param x    the parameter's value.
     * @param y    the parameter's value.
     */
    public void setParameter(@NotNull String name, float x, float y) {
        if (name == null)
            throw new NullPointerException("name must not be null.");

        nativeSetParameter(name, x, y);
    }

    /**
     * Sets a 2-component-float ({@code vec2}) parameter value in the shader.
     *
     * @param name the parameter's name.
     * @param v    the parameter's value.
     */
    public void setParameter(String name, Vector2f v) {
        setParameter(name, v.x, v.y);
    }

    private native void nativeSetParameter(String name, float x, float y, float z);

    /**
     * Sets a 3-component-float ({@code vec3}) parameter value in the shader.
     *
     * @param name the parameter's name.
     * @param x    the parameter's value.
     * @param y    the parameter's value.
     * @param z    the parameter's value.
     */
    public void setParameter(@NotNull String name, float x, float y, float z) {
        if (name == null)
            throw new NullPointerException("name must not be null.");

        nativeSetParameter(name, x, y, z);
    }

    /**
     * Sets a 3-component-float ({@code vec3}) parameter value in the shader.
     *
     * @param name the parameter's name.
     * @param v    the parameter's value.
     */
    public void setParameter(String name, Vector3f v) {
        setParameter(name, v.x, v.y, v.z);
    }

    private native void nativeSetParameter(String name, float x, float y, float z, float w);

    /**
     * Sets a 4-component-float ({@code vec4}) parameter value in the shader.
     *
     * @param name the parameter's name.
     * @param x    the parameter's value.
     * @param y    the parameter's value.
     * @param z    the parameter's value.
     * @param w    the parameter's value.
     */
    public void setParameter(@NotNull String name, float x, float y, float z, float w) {
        if (name == null)
            throw new NullPointerException("name must not be null.");

        nativeSetParameter(name, x, y, z, w);
    }

    /**
     * Sets a color (vec4) parameter value in the shader.
     *
     * @param name  the parameter's name.
     * @param color the parameter's value.
     */
    public void setParameter(String name, Color color) {
        setParameter(name,
                (float) color.r / 255.0f,
                (float) color.g / 255.0f,
                (float) color.b / 255.0f,
                (float) color.a / 255.0f);
    }

    private native void nativeSetParameter(String name, Transform xform);

    /**
     * Sets a matrix (mat4) parameter value in the shader.
     *
     * @param name  the parameter's name.
     * @param xform the parameter's value.
     */
    public void setParameter(@NotNull String name, @NotNull Transform xform) {
        if (name == null)
            throw new NullPointerException("name must not be null.");

        if (xform == null)
            throw new NullPointerException("xform must not be null.");

        nativeSetParameter(name, xform);
    }

    private native void nativeSetParameter(String name, Texture texture);

    /**
     * Sets a texture (sampler2D) parameter value in the shader.
     *
     * @param name    the parameter's name.
     * @param texture the parameter's value.
     */
    public void setParameter(@NotNull String name, @NotNull ConstTexture texture) {
        if (name == null)
            throw new NullPointerException("name must not be null.");

        if (texture == null)
            throw new NullPointerException("texture must not be null.");

        nativeSetParameter(name, (Texture) texture);
    }

    private native void nativeSetParameterCurrentTexture(String name);

    /**
     * Sets a texture (sampler2D) parameter value in the shader to the texture
     * of the object being drawn in the moment the shader is applied.
     * <p/>
     * Since that texture cannot be known before the object is actually being drawn,
     * this overload can be used to tell the shader to use it when applied.
     *
     * @param name           the parameter's name.
     * @param currentTexture should be {@link Shader#CURRENT_TEXTURE}.
     */
    public void setParameter(@NotNull String name, CurrentTextureType currentTexture) {
        if (name == null)
            throw new NullPointerException("name must not be null.");

        nativeSetParameterCurrentTexture(name);
    }

    @Override
    public native void bind();
}
