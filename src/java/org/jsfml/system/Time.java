package org.jsfml.system;

import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for the representation of time values.
 */
@Intercom
public final class Time implements Comparable<Time>, Serializable {
    private static final long serialVersionUID = 7038088548302750096L;

    /**
     * A time object that represents a zero time period.
     */
    public static final Time ZERO = new Time(0);

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
     * Adds two times.
     *
     * @param a The left time.
     * @param b The right time.
     * @return A new time, representing the sum of the two times.
     */
    public static Time add(Time a, Time b) {
        return new Time(a.microseconds + b.microseconds);
    }

    /**
     * Subtracts two times.
     *
     * @param a The left time.
     * @param b The right time.
     * @return A new time, representing the difference between the two times.
     */
    public static Time sub(Time a, Time b) {
        return new Time(a.microseconds - b.microseconds);
    }

    /**
     * Computes a time's ratio of another time.
     *
     * @param a The first time.
     * @param b The right time.
     * @return The ratio of the first time to the second time.
     */
    public static float ratio(Time a, Time b) {
        return (float) a.microseconds / (float) b.microseconds;
    }

    @Intercom
    private final long microseconds;

    private Time() {
        this(0);
    }

    @Intercom
    private Time(long microseconds) {
        this.microseconds = microseconds;
    }

    /**
     * Creates a new time from another time.
     *
     * @param time The time to copy.
     */
    public Time(Time time) {
        this.microseconds = time.microseconds;
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
    public int compareTo(Time t) {
        return ((Long) microseconds).compareTo(t.microseconds);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Time && ((Time) o).microseconds == microseconds);
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
