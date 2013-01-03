package org.jsfml.internal;

import java.awt.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

/**
 * Native library loader.
 * <p/>
 * This class contains the "self-containedness" functionality of JSFML.
 *
 * @see #loadNativeLibraries() for more information.
 */
public final class SFMLNative {
    private static final Path JSFML_USER_HOME = Paths.get(System.getProperty("user.home"), ".jsfml");

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

        if (n != MD5_LENGTH)
            throw new IOException("Error reading MD5 file.");

        return new String(buffer);
    }

    private static String readMD5File(Path path) throws IOException {
        try (final InputStream fis = Files.newInputStream(path)) {
            return readMD5File(fis);
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
     * If loading the native libraries fails, a {@link JSFMLError} will be raised with
     * a brief description of what went wrong.
     */
    public static void loadNativeLibraries() {
        if (!loaded) {
            loaded = true;
            
            //Force load class "java.awt.Component"
            //This way, the AWT libraries will be loaded by the correct class loader
            try {
                Class.forName("java.awt.Component");
            } catch (ClassNotFoundException ex) {
                throw new JSFMLError("", ex);
            }

            //Get operating system information
            final String osName = System.getProperty("os.name");
            final String osArch = System.getProperty("os.arch");

            String arch = null;

            final LinkedList<String> nativeLibs = new LinkedList<>();

            if (osName.contains(OS_NAME_WINDOWS)) {
                switch (osArch) {
                    case "x86":
                        arch = "windows_x86";
                        break;

                    case "amd64":
                        arch = "windows_x64";
                        break;
                }

                nativeLibs.add("libsndfile-1.dll");
                nativeLibs.add("openal32.dll");
                nativeLibs.add("sfml-system-2.dll");
                nativeLibs.add("sfml-window-2.dll");
                nativeLibs.add("sfml-audio-2.dll");
                nativeLibs.add("sfml-graphics-2.dll");
                nativeLibs.add("jsfml.dll");

                //try and load awt.dll
                try {
                    System.loadLibrary("awt");
                } catch (UnsatisfiedLinkError err) {
                    //it might have already been loaded
                    if (!err.getMessage().contains("already loaded"))
                        throw err;
                }

                //try and load jawt.dll
                try {
                    System.loadLibrary("jawt");
                } catch (UnsatisfiedLinkError err) {
                    //it might have already been loaded
                    if (!err.getMessage().contains("already loaded"))
                        throw err;
                }
            } else if (osName.contains(OS_NAME_LINUX)) {
                switch (osArch) {
                    case "x86":
                    case "i386":
                        arch = "linux_x86";
                        break;

                    case "amd64":
                        arch = "linux_x64";
                        break;
                }

                nativeLibs.add("libsfml-system.so");
                nativeLibs.add("libsfml-window.so");
                nativeLibs.add("libsfml-graphics.so");
                nativeLibs.add("libsfml-audio.so");
                nativeLibs.add("libjsfml.so");
				
				//Add "xawt" lib dir to java.library.path
                try {
                    final File jreLibPath = JavaPath.getJreLibPath();
                    JavaPath.addLibraryPath(new File(jreLibPath, "xawt").getAbsolutePath());
                } catch(Exception ex) {
                    throw new JSFMLError("Failed to add xawt lib path to the native library path", ex);
                }
                
                //try and load libmawt
                try {
                    System.loadLibrary("mawt");
                 } catch (UnsatisfiedLinkError err) {
                    //it might have already been loaded
                    if (!err.getMessage().contains("already loaded"))
                        throw err;
                }
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
            final Path nativeLibPath = JSFML_USER_HOME.resolve(arch);

            try {
                Files.createDirectories(nativeLibPath);
            } catch (FileAlreadyExistsException ex) {
                //nevermind
            } catch (IOException ex) {
                throw new JSFMLError("Failed to create native library directory: " +
                        nativeLibPath.toString(), ex);
            }

            for (String lib : nativeLibs) {
                final Path libFile = nativeLibPath.resolve(lib);

                //Check MD5 hash, don't extract if not necessary
                boolean md5Equal = false;

                final String md5FileName = lib + MD5_EXT;

                try (final InputStream md5InputStream =
                             SFMLNative.class.getResourceAsStream(nativeResourcePath + md5FileName)) {

                    final String md5Jar = readMD5File(md5InputStream);
                    final Path md5File = nativeLibPath.resolve(md5FileName);

                    if (Files.isRegularFile(libFile) && Files.isRegularFile(md5File)) {
                        md5Equal = readMD5File(md5File).equals(md5Jar);
                    }

                    if (!md5Equal) {
                        try (final OutputStream out = Files.newOutputStream(md5File)) {
                            out.write(md5Jar.getBytes());
                        } catch (IOException ex) {
                            ex.printStackTrace(); //write to stderr
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(); //write to stderr
                }

                if (!md5Equal) {
                    try (final InputStream in = SFMLNative.class.getResourceAsStream(
                            nativeResourcePath + lib)) {

                        if (in != null) {
                            StreamUtil.streamToFile(in, libFile);
                        } else {
                            throw new JSFMLError(
                                    "Could not find native library in the classpath: " + nativeResourcePath + lib);
                        }
                    } catch (IOException ex) {
                        throw new JSFMLError(
                                "Failed to extract native library: " + libFile.toString(), ex);
                    }
                } else {
                    //MD5 hashes are equal, no need to extract
                }
            }

            //On Linux, add SFML's default install path as a fallback
            for (String lib : nativeLibs) {
                System.load(nativeLibPath.resolve(lib).toAbsolutePath().toString());
            }

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
