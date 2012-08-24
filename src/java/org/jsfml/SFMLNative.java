package org.jsfml;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;

/**
 * JSFML native library loader.
 * <p/>
 * This class contains the "self-containedness" functionality of JSFML.
 *
 * @see #loadNativeLibraries() For more information.
 */
public final class SFMLNative {
    private static final File JSFML_USER_HOME = new File(System.getProperty("user.home"), ".jsfml");

    private static final String JSFML_BIN_RESOURCE_PATH = "/bin/";

    private static final String MD5_EXT = ".MD5";
    private static final int MD5_LENGTH = 32;

    /**
     * The substring of the <tt>os.name</tt> system property to look for to detect Windows.
     */
    public static final String OS_NAME_WINDOWS = "Windows";

    /**
     * The substring of the <tt>os.name</tt> system property to look for to detect Linux.
     */
    public static final String OS_NAME_LINUX = "Linux";

    /**
     * The substring of the <tt>os.name</tt> system property to look for to detect Mac OS X.
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
     * @return <tt>true</tt> if this platform is supported by JSFML, <tt>false</tt> otherwise.
     */
    public static boolean isPlatformSupported() {
        if (GraphicsEnvironment.isHeadless())
            return false;

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
     * This method will scan the <tt>os.name</tt> and <tt>os.arch</tt> system properties and
     * compile a list of libraries to load if the platform is supported. The libraries will
     * then be looked for in the classpath, extracted to the user directory and loaded.
     * <p/>
     * The libraries will be extracted to <tt>~/.jsfml</tt>. If the files already exist, their MD5
     * hashes (which are provided in separate files) will be tested against the ones in the
     * classpath. If the MD5 hashes differ, the existing file in the user directory will
     * be overridden.
     * <p/>
     * If the current platform is not supported, a {@link JSFMLError} will be raised. To avoid
     * this situation, make sure that {@link #isPlatformSupported} returns <tt>true</tt> before
     * using any other JSFML class.
     */
    public static void loadNativeLibraries() {
        if (GraphicsEnvironment.isHeadless())
            throw new HeadlessException("JSFML cannot run in a headless environment");

        if (!loaded) {
            loaded = true;

            //Get operating system information
            String osName = System.getProperty("os.name");
            String osArch = System.getProperty("os.arch");

            LinkedList<String> nativeLibs = new LinkedList<String>();

            //Determine which native libraries to load
            if (osName.contains(OS_NAME_WINDOWS)) {
                if (osArch.equals("x86")) {
                    nativeLibs.add("windows_x86/libsndfile-1.dll");
                    nativeLibs.add("windows_x86/openal32.dll");
                    nativeLibs.add("windows_x86/sfml-system-2.dll");
                    nativeLibs.add("windows_x86/sfml-window-2.dll");
                    nativeLibs.add("windows_x86/sfml-audio-2.dll");
                    nativeLibs.add("windows_x86/sfml-graphics-2.dll");
                    nativeLibs.add("windows_x86/jsfml.dll");
                } else if (osArch.equals("amd64")) {
                    nativeLibs.add("windows_x64/libsndfile-1.dll");
                    nativeLibs.add("windows_x64/openal32.dll");
                    nativeLibs.add("windows_x64/sfml-system-2.dll");
                    nativeLibs.add("windows_x64/sfml-window-2.dll");
                    nativeLibs.add("windows_x64/sfml-audio-2.dll");
                    nativeLibs.add("windows_x64/sfml-graphics-2.dll");
                    nativeLibs.add("windows_x64/jsfml.dll");
                }
            } else if (osName.contains(OS_NAME_LINUX)) {
                if (osArch.equals("x86") || osArch.equals("i386")) {
                    nativeLibs.add("linux_x86/libsfml-system.so");
                    nativeLibs.add("linux_x86/libsfml-window.so");
                    nativeLibs.add("linux_x86/libsfml-graphics.so");
                    nativeLibs.add("linux_x86/libsfml-audio.so");
                    nativeLibs.add("linux_x86/libjsfml.so");
                } else if (osArch.equals("amd64")) {
                    nativeLibs.add("linux_x64/libsfml-system.so");
                    nativeLibs.add("linux_x64/libsfml-window.so");
                    nativeLibs.add("linux_x64/libsfml-graphics.so");
                    nativeLibs.add("linux_x64/libsfml-audio.so");
                    nativeLibs.add("linux_x64/libjsfml.so");
                }
            } else if (osName.contains(OS_NAME_MACOSX)) {
                nativeLibs.add("macosx_universal/libfreetype.dylib");
                nativeLibs.add("macosx_universal/libsndfile.dylib");
                nativeLibs.add("macosx_universal/libsfml-system.dylib");
                nativeLibs.add("macosx_universal/libsfml-window.dylib");
                nativeLibs.add("macosx_universal/libsfml-graphics.dylib");
                nativeLibs.add("macosx_universal/libsfml-audio.dylib");
                nativeLibs.add("macosx_universal/libjsfml.jnilib");
            }

            //Check if the current operating system is supported
            if (nativeLibs.size() == 0) {
                throw new UnsupportedOperationException("Unsupported operating system: " + osName + " " + osArch);
            }

            //Locate native libraries
            for (String lib : nativeLibs) {
                File libFile = new File(JSFML_USER_HOME, lib);

                //Check MD5 hash, don't extract if not necessary
                boolean md5Equal = libFile.exists();

                String md5FileName = lib + MD5_EXT;
                InputStream md5InputStream =
                        SFMLNative.class.getResourceAsStream(JSFML_BIN_RESOURCE_PATH + md5FileName);

                if (md5InputStream != null) {
                    try {
                        String md5Jar = readMD5File(md5InputStream);

                        File md5File = new File(JSFML_USER_HOME, md5FileName);
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
                            SFMLNative.class.getResourceAsStream(JSFML_BIN_RESOURCE_PATH + lib);

                    if (inputStream != null) {
                        libFile.getParentFile().mkdirs();

                        try {
                            StreamUtil.streamToFile(inputStream, libFile);
                        } catch (IOException ex) {
                            throw new JSFMLError(
                                    "Failed to extract native library: " + libFile.getAbsolutePath());
                        }
                    } else {
                        throw new JSFMLError(
                                "Could not find native library in the classpath: " + lib);
                    }
                } else {
                    //no need to extract
                }
            }

            //Load JSFML libraries
            for (String lib : nativeLibs) {
                final File libFile = new File(JSFML_USER_HOME, lib);
                if (libFile.isFile()) {
                    System.load(libFile.getAbsolutePath());
                } else {
                    throw new JSFMLError("Native library file does not exist: " + libFile.getAbsolutePath());
                }
            }

            //Initialize native library
            nativeInit();
        }
    }
}
