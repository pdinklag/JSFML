package org.jsfml.window.event;

import org.jsfml.Intercom;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Represents text enter events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#TEXT_ENTERED}.
 */
@Intercom
public final class TextEvent extends Event {
	private final static Charset utf32 = Charset.forName("UTF-32");

	/**
	 * The UTF-32 code of the character that was entered.
	 */
	public final int unicode;

	/**
	 * The Java representation of the character that was entered.
	 */
	public final char character;

	@Intercom
	TextEvent(int type, int unicode) {
		super(type);

		this.unicode = unicode;

		final ByteBuffer unicodeBuffer = ByteBuffer.allocate(4);
		unicodeBuffer.putInt(unicode);
		unicodeBuffer.flip();

		final CharBuffer chars = utf32.decode(unicodeBuffer);
		character = chars.get();
	}

	@Override
	public TextEvent asTextEvent() {
		return this;
	}
}
