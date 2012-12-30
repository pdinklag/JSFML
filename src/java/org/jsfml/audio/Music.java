package org.jsfml.audio;

import org.jsfml.internal.NotNull;
import org.jsfml.internal.SFMLInputStream;
import org.jsfml.system.Time;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Provides functionality to play music streams from common audio file formats.
 * <p/>
 * Audio files can be opened using either the {@link #openFromFile(java.io.File)} or
 * {@link #openFromStream(java.io.InputStream)} methods.
 * <p/>
 * The supported audio file formats are:
 * {@code ogg, wav, flac, aiff, au, raw, paf, svx, nist, voc, ircam, w64, mat4,
 * mat5 pvf, htk, sds, avr, sd2, caf, wve, mpc2k, rf64}
 */
public class Music extends SoundStream {
    private final SFMLInputStream.NativeStreamRef streamRef =
            new SFMLInputStream.NativeStreamRef();

    /**
     * Constructs a music.
     */
    public Music() {
        super();
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeSetExPtr();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native boolean nativeOpenFromStream(SFMLInputStream.NativeStreamRef stream);

    /**
     * Attempts to open the music from an {@code InputStream}.
     *
     * @param in the input stream to stream from.
     * @throws java.io.IOException in case an I/O error occurs.
     */
    public void openFromStream(@NotNull InputStream in) throws IOException {
        streamRef.initialize(new SFMLInputStream(Objects.requireNonNull(in)));

        if (!nativeOpenFromStream(streamRef))
            throw new IOException("Failed to open music from input stream.");
    }

    /**
     * Attempts to open the music from a file.
     *
     * @param file the file to stream from.
     * @throws IOException in case an I/O error occurs.
     */
    public void openFromFile(File file) throws IOException {
        if (!file.isFile())
            throw new IOException("file not found: " + file);

        openFromStream(new FileInputStream(file));
    }

    /**
     * Gets the total duration of the music.
     *
     * @return the total duration of the music.
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
