package org.jsfml.internal;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Transform;

import java.nio.*;

/**
 * Provides functionality to encode and decode data structures used
 * for intercom methods.
 */
public final class IntercomHelper {
    private static final ThreadLocal<ByteBuffer> BUFFER =
            new ThreadLocal<ByteBuffer>() {
                @Override
                protected ByteBuffer initialValue() {
                    return ByteBuffer.allocateDirect(256).order(ByteOrder.nativeOrder());
                }
            };

    /**
     * Gets the current thread-local buffer.
     * <p/>
     * The buffer can store up to 256 bytes.
     *
     * @return the current thread-local float buffer.
     */
    public static Buffer getBuffer() {
        return BUFFER.get();
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
     * Encodes an integer rectangle into the current integer buffer.
     *
     * @param r the rectangle to encode.
     * @return A reference to the integer buffer.
     */
    public static Buffer encodeIntRect(IntRect r) {
        final IntBuffer buf = BUFFER.get().asIntBuffer();
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
        final FloatBuffer buf = BUFFER.get().asFloatBuffer();
        return new FloatRect(
                buf.get(0),
                buf.get(1),
                buf.get(2),
                buf.get(3));
    }

    /**
     * Encodes a float rectangle into the current float buffer.
     *
     * @param r the float to encode.
     * @return A reference to the float buffer.
     */
    public static Buffer encodeFloatRect(FloatRect r) {
        final FloatBuffer buf = BUFFER.get().asFloatBuffer();
        buf.put(0, r.left);
        buf.put(1, r.top);
        buf.put(2, r.width);
        buf.put(3, r.height);
        return buf;
    }

    /**
     * Decodes a transformation matrix from the current float buffer content.
     *
     * @return the decoded transformation matrix.
     */
    public static Transform decodeTransform() {
        final FloatBuffer buf = BUFFER.get().asFloatBuffer();
        return new Transform(
                buf.get(0),
                buf.get(1),
                buf.get(2),
                buf.get(3),
                buf.get(4),
                buf.get(5),
                buf.get(6),
                buf.get(7),
                buf.get(8)
        );
    }

    private IntercomHelper() {
    }
}
