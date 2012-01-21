package org.jsfml.system;

import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Base class for the representation of time values.
 */
@Intercom
public class Time implements Comparable, Serializable {
    private static final long serialVersionUID = 7038088548302750096L;

    /**
     * Gets a new time with the given value.
     *
     * @param seconds The time value in seconds.
     * @return A new time with the given value.
     */
    public static Time getSeconds(float seconds) {
        return new Time((long) (seconds * 1000000.0f));
    }

    /**
     * Gets a new time with the given value.
     *
     * @param milliseconds The time value in milliseconds.
     * @return A new time with the given value.
     */
    public static Time getMilliseconds(long milliseconds) {
        return new Time(milliseconds * 1000);
    }

    /**
     * Gets a new time with the given value.
     *
     * @param microseconds The time value in microseconds.
     * @return A new time with the given value.
     */
    public static Time getMicroseconds(long microseconds) {
        return new Time(microseconds);
    }

    /**
     * Gets a new time with a zero time value;
     *
     * @return A new time with a zero value.
     */
    public static Time getZero() {
        return new Time(0);
    }

    @Intercom
    private long microseconds = 0;

    private Time() {
    }

    @Intercom
    private Time(long microseconds) {
        this.microseconds = microseconds;
    }

    /**
     * Returns the time value in seconds.
     *
     * @return The time value in seconds.
     */
    public float asSeconds() {
        return (float) microseconds / 1000000.0f;
    }

    /**
     * Returns the time value in milliseconds.
     *
     * @return The time value in milliseconds.
     */
    public long asMilliseconds() {
        return microseconds / 1000;
    }

    /**
     * Returns the time value in microseconds.
     *
     * @return The time value in microseconds.
     */
    public long asMicroseconds() {
        return microseconds;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Time) {
            if (microseconds < ((Time) o).microseconds)
                return -1;
            else if (microseconds > ((Time) o).microseconds)
                return 1;
            else
                return 0;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Time time = (Time) o;

        if (microseconds != time.microseconds) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (microseconds ^ (microseconds >>> 32));
    }

    @Override
    public String toString() {
        return "Time{" +
                "microseconds=" + microseconds +
                '}';
    }
}
