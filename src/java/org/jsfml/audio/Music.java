package org.jsfml.audio;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.internal.SFMLErrorCapture;
import org.jsfml.internal.SFMLInputStream;
import org.jsfml.system.Time;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Provides functionality to play music streams from common audio file formats.
 * <p/>
 * Audio files can be opened using either the {@link #openFromFile(java.nio.file.Path)} or
 * {@link #openFromStream(java.io.InputStream)} methods.
 * <p/>
 * The supported audio file formats are:
 * {@code ogg, wav, flac, aiff, au, raw, paf, svx, nist, voc, ircam, w64, mat4,
 * mat5 pvf, htk, sds, avr, sd2, caf, wve, mpc2k, rf64}
 */
public class Music extends SoundStream {
    private final SFMLInputStream.NativeStreamRef streamRef =
            new SFMLInputStream.NativeStreamRef();

    private Time duration = Time.ZERO;

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
    public void openFromStream(InputStream in) throws IOException {
        streamRef.initialize(new SFMLInputStream(Objects.requireNonNull(in)));

        SFMLErrorCapture.start();
        final boolean success = nativeOpenFromStream(streamRef);
        final String msg = SFMLErrorCapture.finish();

        if (!success) {
            throw new IOException(msg);
        }

        sync();
    }

    /**
     * Attempts to open the music from a file.
     *
     * @param path the file to stream from.
     * @throws IOException in case an I/O error occurs.
     */
    public void openFromFile(Path path) throws IOException {
        openFromStream(Files.newInputStream(path));
    }

    private native void nativeGetData(Buffer buffer);

    private void sync() {
        final ByteBuffer buffer = IntercomHelper.getBuffer();
        nativeGetData(buffer);

        this.duration = Time.getMicroseconds(buffer.asLongBuffer().get(0));

        final IntBuffer ints = buffer.asIntBuffer();
        setData(ints.get(2), ints.get(3));
    }

    /**
     * Gets the total duration of the music.
     *
     * @return the total duration of the music.
     */
    public Time getDuration() {
        return duration;
    }

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
