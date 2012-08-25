package org.jsfml.testapp;

/**
 * Utility class to find out what Linux distribution is currently used.
 */
class LinuxHelper {
    private static final String detectCmd[] = {
            "lsb_release -a",
            "cat /etc/redhat-release",
            "cat /etc/SuSE-release",
            "cat /etc/gentoo-release",
            "cat /etc/issue",
            "gcc --version"
    };

    /**
     * Tries to find information about the current Linux distribution by probing
     * some shell commands.
     *
     * @return The information that was found, or <tt>null</tt> is no information
     *         could be found.
     */
    public static String geLinuxDistributionInfo() {
        for (String cmd : detectCmd) {
            try {
                Exec exec = new Exec(cmd);
                if (exec.getExitCode() == 0)
                    return exec.getStdout();
            } catch (Throwable t) {
                //ignore
            }
        }
        return null;
    }

    private LinuxHelper() {
    }
}
