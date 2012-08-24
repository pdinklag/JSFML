package org.jsfml.testapp;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Simple helper to execute a local shell command and grab its output.
 */
class Exec {
    private static String readStream(InputStream in) throws IOException {
        byte[] buffer = new byte[in.available()];
        in.read(buffer);

        return new String(buffer, Charset.defaultCharset());
    }

    private int exitCode = -1;
    private String stdout = null, stderr = null;

    /**
     * Executes a local shell command.
     *
     * @param cmd The command to execute.
     * @throws IOException          If something goes wrong reading the output streams.
     * @throws InterruptedException If the process got interrupted.
     */
    public Exec(String cmd) throws InterruptedException, IOException {
        Process proc = Runtime.getRuntime().exec(cmd);
        InputStream out = proc.getInputStream();
        InputStream err = proc.getErrorStream();

        exitCode = proc.waitFor();
        stdout = readStream(out);
        stderr = readStream(err);
    }

    /**
     * Gets the exit code of the command.
     *
     * @return The exit code of the command.
     */
    public int getExitCode() {
        return exitCode;
    }

    /**
     * Gets the command's standard error output.
     *
     * @return The command's standard error output.
     */
    public String getStderr() {
        return stderr;
    }

    /**
     * Gets the command's standard output.
     *
     * @return The command's standard output.
     */
    public String getStdout() {
        return stdout;
    }
}
