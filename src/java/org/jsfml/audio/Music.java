package org.jsfml.audio;

import org.jsfml.StreamUtil;
import org.jsfml.system.Time;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

	private native boolean nativeOpenFromFile(String fileName);

	/**
	 * Fully writes all available bytes from an {@link java.io.InputStream} into a temporary file
	 * and attempts to open the stream from it.
	 * <p/>
	 * Direct streaming from a Java input stream is not possible at this time, therefore JSFML
	 * resorts to this method.
	 * <p/>
	 * The temporary file is deleted once the JVM terminates.
	 *
	 * @param in the input stream to read from.
	 * @throws java.io.IOException in case an I/O error occurs.
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
	 * @param file the file to load the sound buffer from.
	 * @throws IOException in case an I/O error occurs.
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
