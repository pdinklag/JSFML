package org.jsfml.window;

import org.jsfml.Intercom;
import org.jsfml.JSFMLException;
import org.jsfml.SFMLNative;

import java.io.Serializable;

/**
 * Class defining the settings of the OpenGL context attached to a window.
 */
@Intercom
public class ContextSettings implements Serializable {
    private static final long serialVersionUID = -3658200233541780345L;

    static {
        try {
            SFMLNative.loadNativeLibraries();
        } catch (JSFMLException ex) {
            throw new UnsatisfiedLinkError(ex.getMessage());
        }
    }

    /**
     * Amount of depth buffer bits.
     */
    @Intercom
    public int depthBits = 24;

    /**
     * Amount of stencil buffer bits.
     */
    @Intercom
    public int stencilBits = 8;

    /**
     * Level of antialiasing.
     */
    @Intercom
    public int antialiasingLevel = 0;

    /**
     * Major OpenGL version number of the context.
     */
    @Intercom
    public int majorVersion = 2;

    /**
     * Minor OpenGL version number of the context.
     */
    @Intercom
    public int minorVersion = 0;

    /**
     * Creates new context settings with default settings.
     */
    public ContextSettings() {
    }

    /**
     * Creates new context settings.
     *
     * @param depthBits         The amount of depth buffer bits.
     * @param stencilBits       The amount of stencil buffer bits.
     * @param antialiasingLevel The level of anti-aliasing.
     * @param majorVersion      Major OpenGL version number.
     * @param minorVersion      Minor OpenGL version number.
     */
    @Intercom
    public ContextSettings(int depthBits, int stencilBits, int antialiasingLevel, int majorVersion, int minorVersion) {
        this.depthBits = depthBits;
        this.stencilBits = stencilBits;
        this.antialiasingLevel = antialiasingLevel;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
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
