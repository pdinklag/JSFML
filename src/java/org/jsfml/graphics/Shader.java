package org.jsfml.graphics;

import org.jsfml.internal.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector3f;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.Objects;

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
     * Activates a shader for rendering.
     * <p/>
     * This is required only if you wish to use JSFML shaders in custom OpenGL code.
     *
     * @param shader the shader to activate, or {@code null} to indicate that no shader
     *               is to be used.
     */
    public static native void bind(ConstShader shader);

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

    private native boolean nativeLoadFromSource1(String source, int shaderType);

    private native boolean nativeLoadFromSource2(String vertSource, String fragSource);

    private void throwException(String msg) throws IOException, ShaderSourceException {
        if (msg.startsWith("Failed to compile") || msg.startsWith("Failed to link")) {
            throw new ShaderSourceException(msg);
        } else {
            throw new IOException(msg);
        }
    }

    /**
     * Attempts to load a shader from GLSL source code.
     *
     * @param source     the GLSL source code.
     * @param shaderType the shader type.
     * @throws IOException           in case an I/O error occurs.
     * @throws ShaderSourceException in case the shader could not be compiled or linked.
     */
    public void loadFromSource(String source, Type shaderType)
            throws IOException, ShaderSourceException {

        SFMLErrorCapture.start();
        final boolean result = nativeLoadFromSource1(
                Objects.requireNonNull(source), shaderType.ordinal());

        final String msg = SFMLErrorCapture.finish();

        if (!result) {
            throwException(msg);
        }
    }

    /**
     * Attempts to load a shader from GLSL source code.
     *
     * @param vertexShaderSource   the vertex shader's GLSL source code.
     * @param fragmentShaderSource the fragment shader's GLSL source code.
     * @throws IOException           in case an I/O error occurs.
     * @throws ShaderSourceException in case the shader could not be compiled or linked.
     */
    public void loadFromSource(String vertexShaderSource, String fragmentShaderSource)
            throws IOException, ShaderSourceException {

        SFMLErrorCapture.start();
        final boolean result = nativeLoadFromSource2(
                Objects.requireNonNull(vertexShaderSource),
                Objects.requireNonNull(fragmentShaderSource));

        final String msg = SFMLErrorCapture.finish();

        if (!result) {
            throwException(msg);
        }
    }

    /**
     * Fully loads all available bytes from an {@link InputStream}
     * and attempts to load the shader from it.
     *
     * @param in         the input stream to read from.
     * @param shaderType the shader type.
     * @throws IOException           in case an I/O error occurs.
     * @throws ShaderSourceException in case the shader could not be compiled or linked.
     */
    public void loadFromStream(InputStream in, Type shaderType)
            throws IOException, ShaderSourceException {

        loadFromSource(new String(StreamUtil.readStream(in)), Objects.requireNonNull(shaderType));
    }

    /**
     * Fully loads all available bytes from an {@link InputStream}
     * and attempts to load the shader from it.
     *
     * @param vertexShaderIn   the input stream to read the vertex shader from.
     * @param fragmentShaderIn the input stream to read the fragment shader from.
     * @throws IOException           in case an I/O error occurs.
     * @throws ShaderSourceException in case the shader could not be compiled or linked.
     */
    public void loadFromStream(InputStream vertexShaderIn, InputStream fragmentShaderIn)
            throws IOException, ShaderSourceException {

        loadFromSource(
                new String(StreamUtil.readStream(vertexShaderIn)),
                new String(StreamUtil.readStream(fragmentShaderIn)));
    }

    /**
     * Attempts to load the shader from a file.
     *
     * @param path       the path to the file to load.
     * @param shaderType the shader type.
     * @throws IOException           in case an I/O error occurs.
     * @throws ShaderSourceException in case the shader could not be compiled or linked.
     */
    public void loadFromFile(Path path, Type shaderType)
            throws IOException, ShaderSourceException {

        loadFromSource(new String(StreamUtil.readFile(path)), Objects.requireNonNull(shaderType));
    }

    /**
     * Attempts to load the shader from files.
     *
     * @param vertexShaderFile   the path to the file to read the vertex shader from.
     * @param fragmentShaderFile the path to the file to read the fragment shader from.
     * @throws IOException           in case an I/O error occurs.
     * @throws ShaderSourceException in case the shader could not be compiled or linked.
     */
    public void loadFromFile(Path vertexShaderFile, Path fragmentShaderFile)
            throws IOException, ShaderSourceException {

        loadFromSource(
                new String(StreamUtil.readFile(vertexShaderFile)),
                new String(StreamUtil.readFile(fragmentShaderFile)));
    }

    private native void nativeSetParameterFloat(String name, float x);

    /**
     * Sets a float parameter ({@code float}) value in the shader.
     *
     * @param name  the parameter's name.
     * @param value the parameter's value.
     */
    public void setParameter(String name, float value) {
        nativeSetParameterFloat(Objects.requireNonNull(name), value);
    }

    private native void nativeSetParameterVec2(String name, float x, float y);

    /**
     * Sets a 2-component-float ({@code vec2}) parameter value in the shader.
     *
     * @param name the parameter's name.
     * @param x    the parameter's value.
     * @param y    the parameter's value.
     */
    public void setParameter(String name, float x, float y) {
        nativeSetParameterVec2(Objects.requireNonNull(name), x, y);
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

    private native void nativeSetParameterVec3(String name, float x, float y, float z);

    /**
     * Sets a 3-component-float ({@code vec3}) parameter value in the shader.
     *
     * @param name the parameter's name.
     * @param x    the parameter's value.
     * @param y    the parameter's value.
     * @param z    the parameter's value.
     */
    public void setParameter(String name, float x, float y, float z) {
        nativeSetParameterVec3(Objects.requireNonNull(name), x, y, z);
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

    private native void nativeSetParameterVec4(String name, float x, float y, float z, float w);

    /**
     * Sets a 4-component-float ({@code vec4}) parameter value in the shader.
     *
     * @param name the parameter's name.
     * @param x    the parameter's value.
     * @param y    the parameter's value.
     * @param z    the parameter's value.
     * @param w    the parameter's value.
     */
    public void setParameter(String name, float x, float y, float z, float w) {
        nativeSetParameterVec4(Objects.requireNonNull(name), x, y, z, w);
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

    private native void nativeSetParameterMat4(String name, Buffer xform);

    /**
     * Sets a matrix (mat4) parameter value in the shader.
     *
     * @param name  the parameter's name.
     * @param xform the parameter's value.
     */
    public void setParameter(String name, Transform xform) {
        nativeSetParameterMat4(
                Objects.requireNonNull(name),
                IntercomHelper.encodeTransform(xform));
    }

    private native void nativeSetParameterSampler2d(String name, Texture texture);

    /**
     * Sets a texture (sampler2D) parameter value in the shader.
     *
     * @param name    the parameter's name.
     * @param texture the parameter's value.
     */
    public void setParameter(String name, ConstTexture texture) {
        nativeSetParameterSampler2d(Objects.requireNonNull(name), (Texture) Objects.requireNonNull(texture));
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
    public void setParameter(String name, CurrentTextureType currentTexture) {
        nativeSetParameterCurrentTexture(Objects.requireNonNull(name));
    }
}
