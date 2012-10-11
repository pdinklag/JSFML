package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Event class for event type {@link Event.Type#TEXT_ENTERED}.
 */
@Intercom
public final class TextEvent extends Event {
    /**
     * The unicode (UTF-32) of the character that was entered.
     */
    public final long unicode;

    @Intercom
    public TextEvent(int type, long unicode) {
        super(type);
        this.unicode = unicode;
    }

    /**
     * Gets the UTF-16 character that was entered.
     *
     * @return The UTF-16 character that was entered.
     */
    public char getChar() {
        return (char) unicode;
    }

    @Override
    public TextEvent asTextEvent() {
        return this;
    }
}
