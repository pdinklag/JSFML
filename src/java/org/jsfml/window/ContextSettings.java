package org.jsfml.window;

import org.jsfml.Intercom;
import org.jsfml.SFMLNative;

import java.io.Serializable;

/**
 * Holds the settings of an OpenGL context attached to a window.
 * <p/>
 * Unless you need anti-aliasing for primitives or require your window to hold an OpenGL context
 * for a specific OpenGL version (e.g. for custom OpenGL rendering), you will most likely not
 * need this.
 * <p/>
 * All settings provided by this class are handled as <i>hints</i> to the created OpenGL context. If a
 * feature is not supported by the hardware, the respective setting will be ignored and a supported
 * default is used instead.
 */
@Intercom
public class ContextSettings implements Serializable {
    private static final long serialVersionUID = -3658200233541780345L;

    static {
        SFMLNative.loadNativeLibraries();
    }

    @Intercom
    private int depthBits = 24;

    @Intercom
    private int stencilBits = 8;

    @Intercom
    private int antialiasingLevel = 0;

    @Intercom
    private int majorVersion = 2;

    @Intercom
    private int minorVersion = 0;

    /**
     * Creates new context settings with default values.
     */
    public ContextSettings() {
    }

    /**
     * Creates new context settings.
     *
     * @param depthBits         The amount of depth buffer bits.
     * @param stencilBits       The amount of stencil buffer bits.
     * @param antialiasingLevel The level of anti-aliasing.
     * @param majorVersion      The desired major OpenGL version number.
     * @param minorVersion      The desired minor OpenGL version number.
     */
    @Intercom
    public ContextSettings(int depthBits, int stencilBits, int antialiasingLevel, int majorVersion, int minorVersion) {
        this.depthBits = depthBits;
        this.stencilBits = stencilBits;
        this.antialiasingLevel = antialiasingLevel;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }

    /**
     * Gets the desired anti-aliasing level of the OpenGL context.
     *
     * @return The desired anti-aliasing level of the OpenGL context.
     */
    public int getAntialiasingLevel() {
        return antialiasingLevel;
    }

    /**
     * Sets the desired anti-aliasing level of the OpenGL context.
     *
     * @param antialiasingLevel The desired anti-aliasing level of the OpenGL context.
     */
    public void setAntialiasingLevel(int antialiasingLevel) {
        if (antialiasingLevel < 0)
            throw new IllegalArgumentException("antialiasingLevel must not be negative.");

        this.antialiasingLevel = antialiasingLevel;
    }

    /**
     * Gets the desired amount of depth bits of the OpenGL context.
     *
     * @return The desired amount of depth bits of the OpenGL context.
     */
    public int getDepthBits() {
        return depthBits;
    }

    /**
     * Sets the desired amount of depth bits of the OpenGL context.
     *
     * @param depthBits The desired amount of depth bits of the OpenGL context.
     */
    public void setDepthBits(int depthBits) {
        if (depthBits < 0)
            throw new IllegalArgumentException("depthBits must not be negative.");

        this.depthBits = depthBits;
    }

    /**
     * Gets the desired major OpenGL version.
     *
     * @return The desired major OpenGL version.
     */
    public int getMajorVersion() {
        return majorVersion;
    }

    /**
     * Sets the desired major OpenGL version.
     * <p/>
     * Note that only OpenGL versions of 3.0 or higher are relevant if a specific OpenGL version
     * is to be used. Requested versions older than 3.0 will all be treated the same way.
     *
     * @param majorVersion The desired major OpenGL version.
     */
    public void setMajorVersion(int majorVersion) {
        if (majorVersion < 0)
            throw new IllegalArgumentException("majorVersion must not be negative.");

        this.majorVersion = majorVersion;
    }

    /**
     * Gets the desired minor OpenGL version.
     *
     * @return The desired minor OpenGL version.
     */
    public int getMinorVersion() {
        return minorVersion;
    }

    /**
     * Sets the desired major OpenGL version.
     * <p/>
     * Note that only OpenGL versions of 3.0 or higher are relevant if a specific OpenGL version
     * is to be used. Requested versions older than 3.0 will all be treated the same way.
     *
     * @param minorVersion The desired major OpenGL version.
     */
    public void setMinorVersion(int minorVersion) {
        if (minorVersion < 0)
            throw new IllegalArgumentException("minorVersion must not be negative.");

        this.minorVersion = minorVersion;
    }

    /**
     * Gets the desired amount of stencil bits of the OpenGL context.
     *
     * @return The desired amount of stencil bits of the OpenGL context.
     */
    public int getStencilBits() {
        return stencilBits;
    }

    /**
     * Sets the desired amount of stencil bits of the OpenGL context.
     *
     * @param stencilBits The desired amount of stencil bits of the OpenGL context.
     */
    public void setStencilBits(int stencilBits) {
        if (stencilBits < 0)
            throw new IllegalArgumentException("stencilBits must not be negative.");

        this.stencilBits = stencilBits;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ContextSettings) {
            ContextSettings c = (ContextSettings) o;
            return (c.antialiasingLevel == antialiasingLevel &&
                    c.depthBits == depthBits &&
                    c.majorVersion == majorVersion &&
                    c.minorVersion == minorVersion &&
                    c.stencilBits == stencilBits);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = depthBits;
        result = 31 * result + stencilBits;
        result = 31 * result + antialiasingLevel;
        result = 31 * result + majorVersion;
        result = 31 * result + minorVersion;
        return result;
    }

    @Override
    public String toString() {
        return "ContextSettings{" +
                "depthBits=" + depthBits +
                ", stencilBits=" + stencilBits +
                ", antialiasingLevel=" + antialiasingLevel +
                ", majorVersion=" + majorVersion +
                ", minorVersion=" + minorVersion +
                '}';
    }
}
