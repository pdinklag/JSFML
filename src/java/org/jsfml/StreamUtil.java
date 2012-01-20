package org.jsfml;

import java.io.*;

/**
 * Provides stream utility functions.
 */
public class StreamUtil {
    private final static String TEMPFILE_PREFIX = "jsfml";
    private final static String TEMPFILE_SUFFIX = "temp";

    private final static int BUFFER_SIZE = 16384;

    /**
     * Fully reads an input stream into a byte array.
     *
     * @param inputStream The input stream to read.
     * @return The bytes read from the stream.
     * @throws IOException If an error occurs in the process.
     */
    public static byte[] readStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
        
        for(int n = inputStream.read(buffer); n > 0; n = inputStream.read(buffer))
            out.write(buffer, 0, n);
        
        return out.toByteArray();
    }

    /**
     * Fully reads an input stream into a byte array.
     *
     * @param file The file to read.
     * @return The bytes read from the file.
     * @throws IOException If an error occurs in the process.
     */
    public static byte[] readFile(File file) throws IOException {
        InputStream fis = new FileInputStream(file);

        byte[] b = null;
        IOException ioException = null;

        try {
            b = readStream(fis);
        } catch (IOException ex) {
            ioException = ex;
        } finally {
            fis.close();
        }

        if (ioException != null)
            throw ioException;

        return b;
    }

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
