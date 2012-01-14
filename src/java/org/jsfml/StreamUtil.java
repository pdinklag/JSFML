package org.jsfml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Provides stream utility functions.
 */
class StreamUtil {
    private final static String TEMPFILE_PREFIX = "jsfml";
    private final static String TEMPFILE_SUFFIX = "temp";

    private final static int BUFFER_SIZE = 16384;

    /**
     * Fully reads an input stream and writes it into a file.
     * <p/>
     * When the operation is finished, the input stream will be closed.
     *
     * @param inputStream The input stream to read.
     * @param file        The file to write to.
     * @throws IOException If an error occurs in the process.
     */
    public static void streamToFile(InputStream inputStream, File file) throws IOException {
        if (inputStream == null)
            throw new IOException("The input stream is null");

        byte[] buffer = new byte[BUFFER_SIZE];
        FileOutputStream outputStream = new FileOutputStream(file, false);

        for (int n = inputStream.read(buffer); n > 0; n = inputStream.read(buffer)) {
            outputStream.write(buffer, 0, n);
        }

        outputStream.close();
        inputStream.close();
    }

    /**
     * Fully reads an input stream and writes it into a temporary file.
     * <p/>
     * When the operation is finished, the input stream will be closed.
     *
     * @param inputStream The input stream to read.
     * @return The file the stream has been written into.
     * @throws IOException If an error occurs in the process.
     */
    public static File streamToTempFile(InputStream inputStream) throws IOException {
        File tempFile = File.createTempFile(TEMPFILE_PREFIX, TEMPFILE_SUFFIX);
        streamToFile(inputStream, tempFile);

        return tempFile;
    }
}
