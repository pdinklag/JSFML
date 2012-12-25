package org.jsfml.graphics;

/**
 * Provides text style constants.
 * <p/>
 * These constants can be combined using an arithmetic {@code OR} operation to define
 * a text style.
 */
public interface TextStyle {
    /**
     * Regular drawing style.
     */
    public static final int REGULAR = 0;

    /**
     * Bold drawing style.
     */
    public static final int BOLD = 0x01;

    /**
     * Italic drawing style.
     */
    public static final int ITALIC = 0x02;

    /**
     * Underlined drawing style.
     */
    public static final int UNDERLINED = 0x04;
}
