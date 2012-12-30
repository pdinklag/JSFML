package org.jsfml;

import java.io.*;

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
     * @param inputStream The input stream to read.
     * @return The bytes read from the stream.
     * @throws IOException If an error occurs in the process.
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
     * @param file The file to read.
     * @return The bytes read from the file.
     * @throws IOException If an error occurs in the process.
     */
    public static byte[] readFile(File file) throws IOException {
        try (final InputStream in = new FileInputStream(file)) {
            return readStream(in);
        }
    }

    /**
     * Fully streams an input stream into a file.
     * <p/>
     * This method does not close the stream when done.
     *
     * @param inputStream The input stream to read.
     * @param file        The file to write to.
     * @throws IOException If an error occurs in the process.
     */
    public static void streamToFile(InputStream inputStream, File file) throws IOException {
        if (inputStream == null)
            throw new IOException("The input stream is null");

        final byte[] buffer = new byte[BUFFER_SIZE];
        try (final FileOutputStream outputStream = new FileOutputStream(file, false)) {
            for (int n = inputStream.read(buffer); n > 0; n = inputStream.read(buffer)) {
                outputStream.write(buffer, 0, n);
            }
        }
    }
}
