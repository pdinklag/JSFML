package org.jsfml.testapp;

import java.io.IOException;

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
     * @throws IOException          If something goes wrong reading the output streams.
     * @throws InterruptedException If the process got interrupted.
     */
    public static String geLinuxDistributionInfo() throws InterruptedException, IOException {
        for (String cmd : detectCmd) {
            Exec exec = new Exec(cmd);
            if (exec.getExitCode() == 0)
                return exec.getStdout();
        }
        return null;
    }

    private LinuxHelper() {
    }
}
