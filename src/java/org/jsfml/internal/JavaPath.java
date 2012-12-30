package org.jsfml.internal;

import java.io.File;
import java.lang.reflect.Field;
 
/**
 * Utility class for java library path operations.
 */
final class JavaPath {
    /**
     * Retrieves the current JRE's lib path.
     * <p/>
     * More precisely, this method gets the first entry in the "sys_paths" array,
     * which, conventionally, points to the JRE's architecture-specific lib directory.
     *
     * @return The lib path of the current JRE.
     * @throws Exception In case an error occurs.
     */
    public static File getJreLibPath() throws Exception {
        final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        sysPathsField.setAccessible(true);

		return new File(((String[]) sysPathsField.get(null))[0]);
    }
 
    /**
     * Adds a path to the java.library.path variable.
     * <p/>
     * More precisely, the specified path is appended to the "usr_paths" array, which holds all paths given
     * using the -Djava.library.path VM option.
     *
     * @param path The path to add to the java.library.path variable.
     * @throws Exception In case an error occurs.
     */
    public static void addLibraryPath(String path) throws Exception {
        final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
        usrPathsField.setAccessible(true);
 
        final String[] usrPaths = (String[]) usrPathsField.get(null);
        final String[] newUsrPaths = new String[usrPaths.length + 1];
 
        System.arraycopy(usrPaths, 0, newUsrPaths, 0, usrPaths.length);
        newUsrPaths[newUsrPaths.length - 1] = path;
 
        usrPathsField.set(null, newUsrPaths);
    }
 
    private JavaPath() {
        //can't instantiate
    }
}
