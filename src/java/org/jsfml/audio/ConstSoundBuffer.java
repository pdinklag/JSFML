package org.jsfml.audio;

import org.jsfml.Const;
import org.jsfml.NotNull;
import org.jsfml.system.Time;

import java.io.File;


/**
 * Interface for read-only textures.
 * <p/>
 * It provides methods to can gain information from a texture, but none to modify it
 * in any way.
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
     * @param file The file to write.
     * @return <tt>true</tt> if the sound buffer was saved successfully, <tt>false</tt> otherwise.
     */
    public boolean saveToFile(@NotNull File file);

    /**
     * Retrieves the raw audio samples stored in the buffer.
     *
     * @return The raw audio samples stored in the buffer.
     */
    public short[] getSamples();

    /**
     * Retrieves the amount of samples stored in the buffer.
     *
     * @return The amount of samples stored in the buffer.
     */
    public int getSampleCount();

    /**
     * Gets the sound buffer's sample rate.
     *
     * @return The sound buffer's sample rate in samples per second.
     */
    public int getSampleRate();

    /**
     * Gets the amount of audio channels in the buffer.
     *
     * @return The amount of audio channels in the buffer.
     */
    public int getChannelCount();

    /**
     * Gets the duration of the sound.
     *
     * @return The duration of the sound in milliseconds.
     */
    public Time getDuration();
}
