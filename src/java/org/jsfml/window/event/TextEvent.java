package org.jsfml.window.event;

import org.jsfml.Intercom;

/**
 * Event class for event type {@link Event.Type#TEXT_ENTERED}.
 */
@Intercom
public class TextEvent extends Event {
    private long unicode;

    @Intercom
    public TextEvent(int type, long unicode) {
        super(type);
        this.unicode = unicode;
    }

    /**
     * Gets the unicode (UTF-32) of the character that was entered.
     *
     * @return The unicode (UTF-32) of the character that was entered.
     */
    public long getUnicode() {
        return unicode;
    }

    /**
     * Gets the UTF-16 character that was entered.
     *
     * @return The UTF-16 character that was entered.
     */
    public char getChar() {
        return (char) (unicode & 0xFFFF);
    }

    @Override
    public TextEvent asTextEvent() {
        return this;
    }
}
