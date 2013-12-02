package org.jsfml.audio;

import org.jsfml.internal.Const;
import org.jsfml.system.Time;

import java.io.IOException;
import java.nio.file.Path;


/**
 * Read-only interface for sound buffers.
 * <p/>
 * It provides methods to can gain information from a sound buffer (such as the duration)
 * and save it to a file.
 * <p/>
 * Note that this interface is expected to be implemented by a {@link SoundBuffer}.
 * It is not recommended to be implemented outside of the JSFML API.
 *
 * @see Const
 */
public interface ConstSoundBuffer extends Const {
    /**
     * Attempts to save the sound buffer to a file.
     *
     * @param path the path to the file to write.
     * @throws IOException in case saving failed.
     */
    public void saveToFile(Path path) throws IOException;

    /**
     * Retrieves the raw 16-bit audio samples stored in the buffer.
     *
     * @return the raw audio 16-bit samples stored in the buffer.
     */
    public short[] getSamples();

    /**
     * Retrieves the amount of samples stored in the buffer.
     *
     * @return the amount of samples stored in the buffer.
     */
    public int getSampleCount();

    /**
     * Gets the sound buffer's sample rate in samples per second.
     *
     * @return the sound buffer's sample rate in samples per second.
     */
    public int getSampleRate();

    /**
     * Gets the amount of audio channels in the buffer
     * (e.g. 1 for mono, 2 for stereo etc).
     *
     * @return the amount of audio channels in the buffer.
     */
    public int getChannelCount();

    /**
     * Gets the duration of the sound.
     *
     * @return the duration of the sound.
     */
    public Time getDuration();
}
