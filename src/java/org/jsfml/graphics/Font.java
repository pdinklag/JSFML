package org.jsfml.graphics;

import org.jsfml.internal.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

/**
 * Holds a character font for use in text displays.
 */
public class Font extends SFMLNativeObject implements ConstFont {
    private class SizeInfo {
        final int characterSize;
        final float lineSpacing;
        final float underlinePosition, underlineThickness;
        final ConstTexture texture;
        final TreeMap<Long, Float> kerning = new TreeMap<>();
        final TreeMap<Integer, Glyph> glyphs = new TreeMap<>();
        final TreeMap<Integer, Glyph> boldGlyphs = new TreeMap<>();

        SizeInfo(
                int characterSize,
                float lineSpacing,
                float underlinePosition,
                float underlineThickness,
                ConstTexture texture) {

            this.characterSize = characterSize;
            this.lineSpacing = lineSpacing;
            this.texture = texture;
            this.underlinePosition = underlinePosition;
            this.underlineThickness = underlineThickness;
        }
    }

    private final Map<Integer, SizeInfo> sizeInfos = new TreeMap<>();

    /**
     * Memory reference and heap pointer that keeps alive the data input stream for freetype.
     */
    private final NativeRef<byte[]> memoryRef = new NativeRef<byte[]>() {
        @Override
        protected long nativeInitialize(byte[] ref) {
            return nativeLoadFromMemory(ref);
        }

        @Override
        protected void nativeRelease(byte[] ref, long ptr) {
            nativeReleaseMemory(ref, ptr);
        }
    };

    private Info info = null;

    /**
     * Constructs a new font.
     */
    public Font() {
        super();
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected void nativeSetExPtr() {
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native long nativeLoadFromMemory(byte[] memory);

    private native void nativeReleaseMemory(byte[] memory, long ptr);

    /**
     * Fully loads all available bytes from an {@link java.io.InputStream}
     * and attempts to load the texture from it.
     *
     * @param in the input stream to read from.
     * @throws java.io.IOException in case an I/O error occurs.
     */
    public void loadFromStream(InputStream in) throws IOException {
        SFMLErrorCapture.start();
        memoryRef.initialize(StreamUtil.readStream(in));
        final String msg = SFMLErrorCapture.finish();

        if (!memoryRef.hasNonZeroPointer()) {
            throw new IOException(msg);
        }

        info = null; //invalidate cache
    }

    /**
     * Attempts to load the texture from a file.
     *
     * @param path the path to the file to load the texture from.
     * @throws IOException in case an I/O error occurs.
     */
    public void loadFromFile(Path path) throws IOException {
        SFMLErrorCapture.start();
        memoryRef.initialize(StreamUtil.readFile(path));
        final String msg = SFMLErrorCapture.finish();

        if (!memoryRef.hasNonZeroPointer()) {
            throw new IOException(msg);
        }

        info = null; //invalidate cache
    }

    private native String nativeGetInfo();

    @Override
    public Info getInfo() {
        if (info == null) {
            info = new Info(nativeGetInfo());
        }
        return info;
    }

    private native long nativeGetTexture(int characterSize);

    private native float nativeGetLineSpacing(int characterSize);

    private native float nativeGetUnderlinePosition(int characterSize);

    private native float nativeGetUnderlineThickness(int characterSize);

    private native void nativeGetGlyph(int unicode, int characterSize, boolean bold, Buffer buf);

    private native float nativeGetKerning(int first, int second, int characterSize);

    private SizeInfo getSizeInfo(int characterSize) {
        SizeInfo info = sizeInfos.get(characterSize);
        if (info == null) {
            final float lineSpacing = nativeGetLineSpacing(characterSize);
            final float underlinePosition = nativeGetUnderlinePosition(characterSize);
            final float underlineThickness = nativeGetUnderlineThickness(characterSize);
            final long p = nativeGetTexture(characterSize);
            final ConstTexture tex = (p != 0) ? new Texture(p) : null;

            info = new SizeInfo(characterSize, lineSpacing, underlinePosition, underlineThickness, tex);
            sizeInfos.put(characterSize, info);
        }
        return info;
    }

    @Override
    public Glyph getGlyph(int unicode, int characterSize, boolean bold) {
        final SizeInfo info = getSizeInfo(characterSize);
        final Map<Integer, Glyph> glyphMap = bold ? info.boldGlyphs : info.glyphs;

        Glyph glyph = glyphMap.get(unicode);
        if (glyph == null) {
            final IntBuffer buf = IntercomHelper.getBuffer().asIntBuffer();

            nativeGetGlyph(unicode, characterSize, bold, buf);
            glyph = new Glyph(
                    buf.get(0),
                    new IntRect(buf.get(1), buf.get(2), buf.get(3), buf.get(4)),
                    new IntRect(buf.get(5), buf.get(6), buf.get(7), buf.get(8)));

            glyphMap.put(unicode, glyph);
        }

        return glyph;
    }

    @Override
    public float getKerning(int first, int second, int characterSize) {
        final SizeInfo info = getSizeInfo(characterSize);
        final long x = ((long) first << 32) | (long) second;
        Float kerning = info.kerning.get(x);
        if (kerning == null) {
            kerning = nativeGetKerning(first, second, characterSize);
            info.kerning.put(x, kerning);
        }

        return kerning;
    }

    @Override
    public float getLineSpacing(int characterSize) {
        return getSizeInfo(characterSize).lineSpacing;
    }

    @Override
    public float getUnderlinePosition(int characterSize) {
        return getSizeInfo(characterSize).underlinePosition;
    }

    @Override
    public float getUnderlineThickness(int characterSize) {
        return getSizeInfo(characterSize).underlineThickness;
    }

    @Override
    public ConstTexture getTexture(int characterSize) {
        return getSizeInfo(characterSize).texture;
    }
}
