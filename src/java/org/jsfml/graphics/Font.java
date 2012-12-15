package org.jsfml.graphics;

import org.jsfml.SFMLNativeObject;
import org.jsfml.StreamUtil;
import org.jsfml.UnsafeOperations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class for loading character fonts.
 */
public class Font extends SFMLNativeObject implements ConstFont {
	private final Map<Integer, Texture> textureMap = new TreeMap<Integer, Texture>();

	/**
	 * Memory reference and heap pointer that keeps alive the data input stream for freetype.
	 */
	private byte[] memRef = null;
	private long memPtr = 0;

	/**
	 * Creates a font.
	 */
	public Font() {
		super();
	}

	/**
	 * Creates a font from another font.
	 *
	 * @param other The font to copy.
	 */
	@SuppressWarnings("deprecation")
	public Font(ConstFont other) {
		super(((Font) other).nativeCopy());
		UnsafeOperations.manageSFMLObject(this, true);
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

	private native long nativeCopy();

	private native long nativeLoadFromMemory(byte[] memory);

	private native void nativeReleaseMemory(byte[] memory, long ptr);

	private void releaseMemory() {
		if (memRef != null) {
			if (memPtr != 0)
				nativeReleaseMemory(memRef, memPtr);

			memRef = null;
			memPtr = 0;
		}
	}

	/**
	 * Fully loads all available bytes from an {@link java.io.InputStream} and attempts to load the texture from it.
	 *
	 * @param in The input stream to read from.
	 * @throws java.io.IOException In case an I/O error occurs.
	 */
	public void loadFromStream(InputStream in) throws IOException {
		releaseMemory();
		memRef = StreamUtil.readStream(in);
		memPtr = nativeLoadFromMemory(memRef);

		if (memPtr == 0) {
			releaseMemory();
			throw new IOException("Failed to load font from input stream.");
		}
	}

	/**
	 * Attempts to load the texture from a file.
	 *
	 * @param file The file to load the texture from.
	 * @throws IOException In case an I/O error occurs.
	 */
	public void loadFromFile(File file) throws IOException {
		releaseMemory();
		memRef = StreamUtil.readFile(file);
		memPtr = nativeLoadFromMemory(memRef);

		if (memPtr == 0) {
			releaseMemory();
			throw new IOException("Failed to load font from file: " + file);
		}
	}

	@Override
	public native Glyph getGlyph(int unicode, int characterSize, boolean bold);

	@Override
	public native int getKerning(int first, int second, int characterSize);

	@Override
	public native int getLineSpacing(int characterSize);

	private native long nativeGetTexture(int characterSize);

	@Override
	public ConstTexture getTexture(int characterSize) {
		Texture texture;
		if (textureMap.containsKey(characterSize)) {
			texture = textureMap.get(characterSize);
		} else {
			long p = nativeGetTexture(characterSize);
			if (p != 0) {
				texture = new Texture(p);
				textureMap.put(characterSize, texture);
			} else {
				texture = null;
			}
		}

		return texture;
	}

	@Override
	protected void finalize() throws Throwable {
		releaseMemory();
		super.finalize();
	}
}
