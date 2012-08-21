package org.jsfml.audio;

import org.jsfml.StreamUtil;
import org.jsfml.system.Time;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Streamed music played from an audio file.
 */
public class Music extends SoundStream {
    /**
     * Creates a new music.
     */
    public Music() {
        super();
    }

    @Override
    protected native long nativeCreate();

    @Override
    protected native void nativeDelete();

    private native boolean nativeOpenFromFile(String fileName);

    /**
     * Fully writes all available bytes from an {@link java.io.InputStream} into a temporary file
     * and attempts to open the stream from it.
     * <p/>
     * Direct streaming from a Java input stream is not possible, therefore JSFML resorts to this method.
     * The temporary file is deleted once the JVM terminates.
     *
     * @param in The input stream to read from.
     * @throws java.io.IOException In case an I/O error occurs.
     */
    public void openFromStream(InputStream in) throws IOException {
        File tempFile = StreamUtil.streamToTempFile(in);
        tempFile.deleteOnExit();

        if (!nativeOpenFromFile(tempFile.getAbsolutePath()))
            throw new IOException("Failed to open music from input stream.");
    }

    /**
     * Attempts to open the stream from a file.
     *
     * @param file The file to load the sound buffer from.
     * @throws IOException In case an I/O error occurs.
     */
    public void openFromFile(File file) throws IOException {
        if (!file.isFile())
            throw new IOException("file not found: " + file);

        if (!nativeOpenFromFile(file.getAbsolutePath()))
            throw new IOException("Failed to open music from file: " + file);
    }

    /**
     * Gets the total duration of the music.
     *
     * @return The total duration of the music in milliseconds.
     */
    public native Time getDuration();

    @Override
    protected final void initialize(int channelCount, int sampleRate) {
        //Music is implemented natively, so this is not used.
    }

    @Override
    protected final Chunk onGetData() {
        //Music is implemented natively, so this is not used.
        return null;
    }

    @Override
    protected final void onSeek(Time time) {
        //Music is implemented natively, so this is not used.
    }
}
