package org.jsfml;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;

/**
 * Native library loader.
 * <p/>
 * This class contains the "self-containedness" functionality of JSFML.
 *
 * @see #loadNativeLibraries() for more information.
 */
public final class SFMLNative {
    private static final File JSFML_USER_HOME = new File(System.getProperty("user.home"), ".jsfml");

    private static final String JSFML_BIN_RESOURCE_PATH = "/bin/";

    private static final String MD5_EXT = ".MD5";
    private static final int MD5_LENGTH = 32;

    /**
     * The substring of the {@code os.name} system property
     * to look for to detect Windows systems.
     */
    public static final String OS_NAME_WINDOWS = "Windows";

    /**
     * The substring of the {@code os.name} system property
     * to look for to detect Linux systems.
     */
    public static final String OS_NAME_LINUX = "Linux";

    /**
     * The substring of the {@code os.name} system property
     * to look for to detect Mac OS X systems.
     */
    public static final String OS_NAME_MACOSX = "Mac OS X";

    private static native void nativeInit();

    private static boolean loaded = false;

    private static String readMD5File(InputStream in) throws IOException {
        byte[] buffer = new byte[MD5_LENGTH];
        int n = in.read(buffer);
        in.close();

        if (n != MD5_LENGTH)
            throw new IOException("Error reading MD5 file.");

        return new String(buffer);
    }

    private static String readMD5File(File file) throws IOException {
        return readMD5File(new FileInputStream(file));
    }

    /**
     * Tests whether the current platform is supported by JSFML.
     * <p/>
     * This method can be used before any JSFML call to make sure that JSFML is supported on the current
     * platform. If this is not the case, {@link #loadNativeLibraries()} will raise an error attempting
     * to load the native library.
     *
     * @return {@code true} if this platform is supported by JSFML, {@code false} otherwise.
     */
    public static boolean isPlatformSupported() {
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");

        /**
         * TODO In all probability, a more detailed OS version check should happen in here.
         */

        if (osName.contains(OS_NAME_WINDOWS)) {
            return osArch.equals("x86") || osArch.equals("amd64");
        } else if (osName.contains(OS_NAME_LINUX)) {
            return osArch.equals("x86") || osArch.equals("i386") || osArch.equals("amd64");
        } else if (osName.contains(OS_NAME_MACOSX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Loads the native JSFML libraries if it has not been done yet.
     * <p/>
     * This must be done before any SFML class representations can be used. All affected classes
     * will call this method when they are loaded, so this does not have to be done manually.
     * <p/>
     * This method will scan the {@code os.name} and {@code os.arch} system properties and
     * compile a list of libraries to load if the platform is supported. The libraries will
     * then be looked for in the classpath, extracted to the user directory and loaded.
     * <p/>
     * The libraries will be extracted to {@code ~/.jsfml}. If the files already exist, their MD5
     * hashes (which are provided in separate files) will be tested against the ones in the
     * classpath. If the MD5 hashes differ, the existing file in the user directory will
     * be overridden.
     * <p/>
     * If the current platform is not supported, a {@link JSFMLError} will be raised. To avoid
     * this situation, make sure that {@link #isPlatformSupported} returns {@code true} before
     * using any other JSFML class.
     */
    public static void loadNativeLibraries() {
        if (!loaded) {
            loaded = true;

            //Get operating system information
            final String osName = System.getProperty("os.name");
            final String osArch = System.getProperty("os.arch");

            String arch = null;

            final LinkedList<String> nativeLibs = new LinkedList<String>();

            if (osName.contains(OS_NAME_WINDOWS)) {
                if (osArch.equals("x86")) {
                    arch = "windows_x86";
                } else if (osArch.equals("amd64")) {
                    arch = "windows_x64";
                }

                nativeLibs.add("libsndfile-1.dll");
                nativeLibs.add("openal32.dll");
                nativeLibs.add("sfml-system-2.dll");
                nativeLibs.add("sfml-window-2.dll");
                nativeLibs.add("sfml-audio-2.dll");
                nativeLibs.add("sfml-graphics-2.dll");
                nativeLibs.add("jsfml.dll");
            } else if (osName.contains(OS_NAME_LINUX)) {
                if (osArch.equals("x86") || osArch.equals("i386")) {
                    arch = "linux_x86";
                } else if (osArch.equals("amd64")) {
                    arch = "linux_x64";
                }

                nativeLibs.add("libsfml-system.so");
                nativeLibs.add("libsfml-window.so");
                nativeLibs.add("libsfml-graphics.so");
                nativeLibs.add("libsfml-audio.so");
                nativeLibs.add("libjsfml.so");
            } else if (osName.contains(OS_NAME_MACOSX)) {
                arch = "macosx_universal";

                nativeLibs.add("libfreetype.dylib");
                nativeLibs.add("libsndfile.dylib");
                nativeLibs.add("libsfml-system.dylib");
                nativeLibs.add("libsfml-window.dylib");
                nativeLibs.add("libsfml-graphics.dylib");
                nativeLibs.add("libsfml-audio.dylib");
                nativeLibs.add("libjsfml.jnilib");
            }

            //Check if the current platform is supported
            if (arch == null) {
                throw new JSFMLError("Unsupported platform: " + osName + " " + osArch);
            }

            //Extract native libraries
            final String nativeResourcePath = JSFML_BIN_RESOURCE_PATH + arch + "/";
            final File nativeLibPath = new File(JSFML_USER_HOME, arch);

            for (String lib : nativeLibs) {
                File libFile = new File(nativeLibPath, lib);
                libFile.getParentFile().mkdirs();

                //Check MD5 hash, don't extract if not necessary
                boolean md5Equal = false;

                String md5FileName = lib + MD5_EXT;
                InputStream md5InputStream =
                        SFMLNative.class.getResourceAsStream(nativeResourcePath + md5FileName);

                if (md5InputStream != null) {
                    try {
                        String md5Jar = readMD5File(md5InputStream);

                        File md5File = new File(nativeLibPath, md5FileName);

                        if (md5File.exists()) {
                            md5Equal = readMD5File(md5File).equals(md5Jar);
                        }

                        if (!md5Equal) {
                            try {
                                FileOutputStream out = new FileOutputStream(md5File);
                                out.write(md5Jar.getBytes());
                                out.close();
                            } catch (IOException ex2) {
                                md5File.delete();
                            }
                        }
                    } catch (IOException ex) {
                        //
                    }
                }

                if (!md5Equal) {
                    InputStream inputStream =
                            SFMLNative.class.getResourceAsStream(nativeResourcePath + lib);

                    if (inputStream != null) {
                        try {
                            //System.out.println("Extracting: " + libFile);
                            StreamUtil.streamToFile(inputStream, libFile);
                        } catch (IOException ex) {
                            throw new JSFMLError(
                                    "Failed to extract native library: " + libFile.getAbsolutePath());
                        }
                    } else {
                        throw new JSFMLError(
                                "Could not find native library in the classpath: " + nativeResourcePath + lib);
                    }
                } else {
                    //no need to extract
                }
            }

            //On Linux, add SFML's default install path as a fallback
            for (String lib : nativeLibs)
                System.load(new File(nativeLibPath, lib).getAbsolutePath());

            //Initialize
            nativeInit();
        }
    }

    /**
     * Ensures that a display is available on this system.
     * <p/>
     * If that is not the case, a {@link HeadlessException} is thrown to indicate that the desired
     * JSFML feature is not available.
     */
    public static void ensureDisplay() {
        if (GraphicsEnvironment.isHeadless())
            throw new HeadlessException("This JSFML feature is not available in a headless environment");
    }
}
