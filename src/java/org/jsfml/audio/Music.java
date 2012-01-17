package org.jsfml.audio;

import org.jsfml.StreamUtil;
import org.jsfml.system.Vector3f;

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

    private native boolean nativeOpenFromFile(String fileName);

    /**
     * Fully writes all available bytes from an {@link java.io.InputStream} into a temporary file
     * and attempts to open the stream from it.
     * <p/>
     * Direct streaming from a Java input stream is not possible, therefore JSFML resorts to this method.
     * The temporary file is deleted once the JVM terminates.
     *
     * @param in The input stream to read from.
     * @return <tt>true</tt> if the sound buffer was successfully loaded, <tt>false</tt> otherwise.
     * @throws java.io.IOException In case an I/O error occurs.
     */
    public boolean openFromStream(InputStream in) throws IOException {
        File tempFile = StreamUtil.streamToTempFile(in);
        tempFile.deleteOnExit();

        return nativeOpenFromFile(tempFile.getAbsolutePath());
    }

    /**
     * Attempts to open the stream from a file.
     *
     * @param file The file to load the sound buffer from.
     * @return <tt>true</tt> if the sound buffer was successfully loaded, <tt>false</tt> otherwise.
     * @throws IOException In case an I/O error occurs.
     */
    public boolean openFromFile(File file) throws IOException {
        if (!file.isFile())
            throw new IOException("file not found: " + file);

        return nativeOpenFromFile(file.getAbsolutePath());
    }

    /**
     * Gets the total duration of the music.
     *
     * @return The total duration of the music in milliseconds.
     */
    public native long getDuration();

    @Override
    public native void play();

    @Override
    public native void pause();

    @Override
    public native void stop();

    @Override
    public native int getChannelCount();

    @Override
    public native int getSampleRate();

    private native int nativeGetStatus();

    @Override
    public Status getStatus() {
        return Status.values()[nativeGetStatus()];
    }

    @Override
    public native void setPlayingOffset(long offset);

    @Override
    public native long getPlayingOffset();

    @Override
    public native void setLoop(boolean loop);

    @Override
    public native boolean isLoop();

    @Override
    public native void setPitch(float pitch);

    @Override
    public native void setVolume(float volume);

    @Override
    public native void setPosition(float x, float y, float z);

    @Override
    public native void setRelativeToListener(boolean relative);

    @Override
    public native void setMinDistance(float distance);

    @Override
    public native void setAttenuation(float att);

    @Override
    public native float getPitch();

    @Override
    public native float getVolume();

    @Override
    public native Vector3f getPosition();

    @Override
    public native boolean isRelativeToListener();

    @Override
    public native float getMinDistance();

    @Override
    public native float getAttenuation();
}
