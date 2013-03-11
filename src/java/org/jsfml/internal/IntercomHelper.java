package org.jsfml.internal;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Provides functionality to encode and decode data structures used
 * for intercom methods.
 */
public final class IntercomHelper {
    private static final int BUFFER_CAPACITY = 16;

    private static final ThreadLocal<IntBuffer> INT_BUFFER =
            new ThreadLocal<IntBuffer>() {
                @Override
                protected IntBuffer initialValue() {
                    return ByteBuffer.allocateDirect(BUFFER_CAPACITY << 2).asIntBuffer();
                }
            };

    private static final ThreadLocal<FloatBuffer> FLOAT_BUFFER =
            new ThreadLocal<FloatBuffer>() {
                @Override
                protected FloatBuffer initialValue() {
                    return ByteBuffer.allocateDirect(BUFFER_CAPACITY << 2).asFloatBuffer();
                }
            };

    /**
     * Gets the current thread-local integer buffer.
     * <p/>
     * The buffer can store up to 16 integer values.
     *
     * @return the current thread-local integer buffer.
     */
    public static IntBuffer getIntBuffer() {
        return INT_BUFFER.get();
    }

    /**
     * Gets the current thread-local float buffer.
     * <p/>
     * The buffer can store up to 16 float values.
     *
     * @return the current thread-local float buffer.
     */
    public static FloatBuffer getFloatBuffer() {
        return FLOAT_BUFFER.get();
    }

    /**
     * Decodes a color from a 32-bit integer.
     *
     * @param code the encoded color.
     * @return the decoded color.
     */
    public static Color decodeColor(int code) {
        final int r = (code >> 24) & 0xFF;
        final int g = (code >> 16) & 0xFF;
        final int b = (code >> 8) & 0xFF;
        final int a = code & 0xFF;
        return new Color(r, g, b, a);
    }

    /**
     * Encodes a color into a 32-bit integer.
     *
     * @param color the color to encode.
     * @return the encoded color.
     */
    public static int encodeColor(Color color) {
        return (color.r << 24) | (color.g << 16) | (color.b << 8) | color.a;
    }

    /**
     * Decodes an integer rectangle from the current integer buffer content.
     *
     * @return the decoded rectangle.
     */
    public static IntRect decodeIntRect() {
        final IntBuffer buf = getIntBuffer();
        return new IntRect(buf.get(0), buf.get(1), buf.get(2), buf.get(3));
    }

    /**
     * Encodes an integer rectangle into the current integer buffer.
     *
     * @param r the rectangle to encode.
     * @return A reference to the integer buffer.
     */
    public static IntBuffer encodeIntRect(IntRect r) {
        final IntBuffer buf = getIntBuffer();
        buf.put(0, r.left);
        buf.put(1, r.top);
        buf.put(2, r.width);
        buf.put(3, r.height);
        return buf;
    }

    /**
     * Decodes an float rectangle from the current float buffer content.
     *
     * @return the decoded rectangle.
     */
    public static FloatRect decodeFloatRect() {
        final FloatBuffer buf = getFloatBuffer();
        return new FloatRect(buf.get(0), buf.get(1), buf.get(2), buf.get(3));
    }

    /**
     * Encodes a float rectangle into the current float buffer.
     *
     * @param r the float to encode.
     * @return A reference to the float buffer.
     */
    public static FloatBuffer encodeFloatRect(FloatRect r) {
        final FloatBuffer buf = getFloatBuffer();
        buf.put(0, r.left);
        buf.put(1, r.top);
        buf.put(2, r.width);
        buf.put(3, r.height);
        return buf;
    }

    private IntercomHelper() {
    }
}
