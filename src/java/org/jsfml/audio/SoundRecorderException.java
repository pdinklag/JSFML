package org.jsfml.audio;

/**
 * Thrown when sound recorder related functions fail.
 */
public class SoundRecorderException extends Exception {
    private static final long serialVersionUID = -6416178048026460236L;

    /**
     * Constructs a sound recorder exception with the specified message.
     *
     * @param message the error message.
     */
    public SoundRecorderException(String message) {
        super(message);
    }
}
