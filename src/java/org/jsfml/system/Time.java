package org.jsfml.system;

import java.io.Serializable;

/**
 * Represents a time period and provides functionality to convert between
 * various time units, as well as arithmetic operations on time intervals.
 */
public final strictfp class Time implements Comparable<Time>, Serializable {
    private static final long serialVersionUID = 7038088548302750096L;

    /**
     * A time object that represents a zero time period, ie with no duration.
     */
    public static final Time ZERO = new Time(0);

    /**
     * Creates a new time with the given value.
     *
     * @param seconds the time value, in seconds.
     * @return a new time with the given value.
     */
    public static Time getSeconds(float seconds) {
        return new Time((long) (seconds * 1000000.0f));
    }

    /**
     * Creates a new time with the given value.
     *
     * @param milliseconds the time value in milliseconds.
     * @return a new time with the given value.
     */
    public static Time getMilliseconds(long milliseconds) {
        return new Time(milliseconds * 1000);
    }

    /**
     * Creates a new time with the given value.
     *
     * @param microseconds the time value in microseconds.
     * @return a new time with the given value.
     */
    public static Time getMicroseconds(long microseconds) {
        return new Time(microseconds);
    }

    /**
     * Adds two time values.
     *
     * @param a the first operand.
     * @param b The second operand.
     * @return a new time, representing the sum of the two times.
     */
    public static Time add(Time a, Time b) {
        return new Time(a.microseconds + b.microseconds);
    }

    /**
     * Subtracts two time values.
     *
     * @param a the minuend.
     * @param b the subtrahend.
     * @return a new time, representing the difference between the two times.
     */
    public static Time sub(Time a, Time b) {
        return new Time(a.microseconds - b.microseconds);
    }

    /**
     * Scales a time by multiplying its value by a scalar.
     *
     * @param a the time to scale.
     * @param s the scalar.
     * @return a new time, representing the given time scaled by the given factor.
     */
    public static Time mul(Time a, float s) {
        return new Time((long) (s * (float) a.microseconds));
    }

    /**
     * Scales a time by dividing it by a scalar.
     *
     * @param a the time to scale.
     * @param s the scalar.
     * @return a new time, representing the given time scaled by the given factor.
     */
    public static Time div(Time a, float s) {
        return new Time((long) ((float) a.microseconds / s));
    }

    /**
     * Computes a time period's ratio of another time period.
     *
     * @param a The first time period.
     * @param b The second time period.
     * @return The ratio of the first time period to the second time period.
     */
    public static float ratio(Time a, Time b) {
        return (float) a.microseconds / (float) b.microseconds;
    }

    private final long microseconds;

    private Time(long microseconds) {
        this.microseconds = microseconds;
    }

    /**
     * Returns the time value in seconds.
     *
     * @return the time value in seconds.
     */
    public float asSeconds() {
        return (float) microseconds / 1000000.0f;
    }

    /**
     * Returns the time value in milliseconds.
     *
     * @return the time value in milliseconds.
     */
    public long asMilliseconds() {
        return microseconds / 1000;
    }

    /**
     * Returns the time value in microseconds.
     *
     * @return the time value in microseconds.
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
