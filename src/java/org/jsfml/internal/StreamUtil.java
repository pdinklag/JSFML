package org.jsfml.internal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Provides stream utility functions used by JSFML-internal file reading and writing methods.
 */
public class StreamUtil {
    private final static int BUFFER_SIZE = 16384;

    /**
     * Fully reads an input stream into a byte array.
     * <p/>
     * This method does not close the stream when done.
     *
     * @param inputStream the input stream to read.
     * @return the bytes read from the stream.
     * @throws IOException if an error occurs in the process.
     */
    public static byte[] readStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);

        for (int n = inputStream.read(buffer); n > 0; n = inputStream.read(buffer))
            out.write(buffer, 0, n);

        return out.toByteArray();
    }

    /**
     * Fully reads a file into a byte array.
     *
     * @param path the path to the file to read.
     * @return the bytes read from the file.
     * @throws IOException if an error occurs in the process.
     */
    public static byte[] readFile(Path path) throws IOException {
        try (final InputStream in = Files.newInputStream(path)) {
            return readStream(in);
        }
    }

    /**
     * Fully streams an input stream into a file.
     * <p/>
     * This method does not close the stream when done.
     *
     * @param inputStream the input stream to read.
     * @param path        the file to write to.
     * @throws IOException if an error occurs in the process.
     */
    public static void streamToFile(InputStream inputStream, Path path) throws IOException {
        if (inputStream == null)
            throw new IOException("The input stream is null");

        final byte[] buffer = new byte[BUFFER_SIZE];
        try (final OutputStream out = Files.newOutputStream(path)) {
            for (int n = inputStream.read(buffer); n > 0; n = inputStream.read(buffer)) {
                out.write(buffer, 0, n);
            }
        }
    }
}
