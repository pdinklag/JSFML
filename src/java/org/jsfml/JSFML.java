package org.jsfml;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.internal.SFMLNative;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * Provides access to JSFML release related properties.
 */
public final class JSFML {
    private static native void nativeGetSFMLVersion(Buffer buffer);

    /**
     * The unique, continuous number of the JSFML release.
     * <p/>
     * Use this value in order to compare JSFML versions.
     */
    public static final int JSFML_RELEASE_NUMBER = 6;

    /**
     * The major version number of the underlying SFML.
     */
    public static final int SFML_VERSION_MAJOR;

    /**
     * The minor version number of the underlying SFML.
     */
    public static final int SFML_VERSION_MINOR;

    /**
     * The patch version number of the underlying SFML.
     */
    public static final int SFML_VERSION_PATCH;

    /**
     * The full JSFML version string.
     * <p/>
     * The version string uses the following format:
     * {@code <SFML_VERSION_MAJOR>.<SFML_VERSION_MINOR>[.SFML_VERSION_PATCH]-J<JSFML_RELEASE_NUMBER>}.
     * <p/>
     * The patch version of SFML is only included if non-zero.
     */
    public static final String VERSION_STRING;

    static {
        SFMLNative.loadNativeLibraries();

        //initialize SFML version
        final IntBuffer buffer = IntercomHelper.getBuffer().asIntBuffer();
        nativeGetSFMLVersion(buffer);

        SFML_VERSION_MAJOR = buffer.get(0);
        SFML_VERSION_MINOR = buffer.get(1);
        SFML_VERSION_PATCH = buffer.get(2);

        final StringBuilder versionStr = new StringBuilder();
        versionStr.append(SFML_VERSION_MAJOR);
        versionStr.append('.').append(SFML_VERSION_MINOR);

        if (SFML_VERSION_PATCH != 0) {
            versionStr.append('.').append(SFML_VERSION_PATCH);
        }

        versionStr.append("-J").append(JSFML_RELEASE_NUMBER);

        VERSION_STRING = versionStr.toString();
    }

    //cannot instantiate
    private JSFML() {
    }
}
