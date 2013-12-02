package org.jsfml.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Pipe for enabling stream from a Java {@link InputStream} into a {@code sf::InputStream}.
 */
public class SFMLInputStream {
    /**
     * Native pointer manager for an {@code SFMLInputStream} bound
     * natively to an {@code sf::InputStream}.
     */
    public static class NativeStreamRef extends NativeRef<SFMLInputStream> {
        @Override
        protected native long nativeInitialize(SFMLInputStream ref);

        @Override
        protected native void nativeRelease(SFMLInputStream ref, long ptr);
    }

    private final InputStream stream;
    private long pos;

    public SFMLInputStream(InputStream stream) {
        Objects.requireNonNull(stream);

        if (stream.markSupported()) {
            this.stream = stream;
        } else {
            this.stream = new BufferedInputStream(stream);
        }

        //Mark the current position as zero
        this.stream.mark(Integer.MAX_VALUE);
        pos = 0;
    }

    //As defined in sf::InputStream.
    long read(ByteBuffer buffer, long n) {
        try {
            byte[] b = new byte[buffer.capacity()];
            long num = (long) stream.read(b);
            if (num == -1) {
                return 0;
            } else {
                buffer.put(b);
            }

            pos += num;
            return num;
        } catch (IOException ex) {
            pos = -1;
            return 0;
        }
    }

    //As defined in sf::InputStream.
    long seek(long n) {
        try {
            if (n > pos) {
                long skipped = stream.skip(n - pos);
                pos += skipped;
            } else {
                stream.reset();
                pos = 0;

                if (n > 0) {
                    pos = stream.skip(n);
                }
            }

            return (pos == n) ? pos : -1;
        } catch (IOException ex) {
            pos = -1;
            return -1;
        }
    }

    //As defined in sf::InputStream.
    long tell() {
        return pos;
    }

    //As defined in sf::InputStream.
    long getSize() {
        try {
            long n = (long) stream.available();
            return n;
        } catch (IOException ex) {
            return -1;
        }
    }

    //Called by the destructor.
    void close() {
        try {
            stream.close();
        } catch (IOException ex) {
            //
        }
    }
}
