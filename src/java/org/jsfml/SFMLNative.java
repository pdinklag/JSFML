package org.jsfml;

import java.io.*;
import java.util.LinkedList;

/**
 * JSFML native library handler.
 */
public final class SFMLNative {
    /**
     * System property name for the JSFML binary path.
     * <p/>
     * Use this property to determine the path in which JSFML will look for the binary files. You have to set
     * this property  <i>before</i> creating any JSFML objects.
     * <p/>
     * Note that the OS and architecture directory structure will still be expected.
     * <p/>
     * If the property is not set (ie if it is set to <tt>null</tt>), JSFML will attempt to extract the binaries from
     * the class loader's resource path to the current user's home directory.
     */
    public static final String PROPERTY_JSFML_BIN = "jsfml.bin";

    private static final File JSFML_USER_HOME = new File(System.getProperty("user.home"), ".jsfml");

    private static final String JSFML_BIN_RESOURCE_PATH = "/bin/";

    private static final String MD5_EXT = ".MD5";
    private static final int MD5_LENGTH = 32;

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
     * Forced loading of the JSFML native libraries.
     * <p/>
     * This must be done before any SFML class representations can be used. All affected classes will call this
     * method when they are loaded, so usually this does not have to be done automatically.
     *
     * @throws JSFMLException If an error occured.
     */
    public static void loadNativeLibraries() throws JSFMLException {
        if (!loaded) {
            loaded = true;

            //Get operating system information
            String osName = System.getProperty("os.name");
            String osArch = System.getProperty("os.arch");

            LinkedList<String> jreLibs = new LinkedList<String>();
            LinkedList<String> nativeLibs = new LinkedList<String>();

            //Determine which native libraries to load
            if (osName.contains("Windows")) {
                jreLibs.add("client/jvm");
                jreLibs.add("awt");
                jreLibs.add("jawt");

                if (osArch.equals("x86")) {
                    nativeLibs.add("windows_x86/libsndfile-1.dll");
                    nativeLibs.add("windows_x86/openal32.dll");
                    nativeLibs.add("windows_x86/jsfml.dll");
                }
            } else if (osName.contains("Linux")) {
                if (osArch.equals("x86") || osArch.equals("i386")) {
                    nativeLibs.add("linux_x86/libsfml-system.so");
                    nativeLibs.add("linux_x86/libsfml-window.so");
                    nativeLibs.add("linux_x86/libsfml-graphics.so");
                    nativeLibs.add("linux_x86/libsfml-audio.so");
                    nativeLibs.add("linux_x86/libjsfml.so");
                }
            } else if (osName.contains("Mac OS X")) {
                //TODO
            }

            //Check if operating system is supported
            if (nativeLibs.size() == 0) {
                throw new JSFMLException("Unsupported operating system: " + osName + " " + osArch);
            }

            //Load JRE libraries
            for (String lib : jreLibs) {
                try {
                    System.loadLibrary(lib);
                } catch (UnsatisfiedLinkError error) {
                    if (!error.getMessage().contains("already loaded"))
                        throw new JSFMLException(error.getMessage(), error);
                }
            }

            //Locate native libraries
            String nativeLibPath = System.getProperty(PROPERTY_JSFML_BIN);

            if (nativeLibPath == null) {
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
                                throw new JSFMLException(
                                        "Failed to extract native library: " + libFile.getAbsolutePath());
                            }
                        } else {
                            throw new JSFMLException(
                                    "Could not find native library in the classpath: " + lib);
                        }
                    } else {
                        //no need to extract
                    }
                }
                nativeLibPath = JSFML_USER_HOME.getAbsolutePath();
            }

            //Load JSFML libraries
            for (String lib : nativeLibs) {
                File libFile = new File(nativeLibPath, lib);

                try {
                    System.load(libFile.getAbsolutePath());
                } catch (UnsatisfiedLinkError error) {
                    throw new JSFMLException(error.getMessage(), error);
                }
            }

            //Initialize native library
            nativeInit();
        }
    }
}
