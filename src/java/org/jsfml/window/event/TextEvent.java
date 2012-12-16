package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Represents text enter events.
 * <p/>
 * Objects of this class are created for events of type
 * {@link Event.Type#TEXT_ENTERED}.
 */
@Intercom
public final class TextEvent extends Event {
	/**
	 * The unicode (UTF-32) of the character that was entered.
	 */
	public final long unicode;

	@Intercom
	TextEvent(int type, long unicode) {
		super(type);
		this.unicode = unicode;
	}

	/**
	 * Gets the UTF-16 character that was entered.
	 *
	 * @return the UTF-16 character that was entered, or {@code ''} if the character that
	 *         was entered cannot be represented by UTF-16.
	 */
	public char getChar() {
		return (char) unicode;
	}

	@Override
	public TextEvent asTextEvent() {
		return this;
	}
}
