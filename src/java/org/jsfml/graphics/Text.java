package org.jsfml.graphics;

import org.jsfml.NotNull;
import org.jsfml.system.Vector2f;

/**
 * Graphical text that can be transformed and drawn to a render target.
 */
public class Text extends Transformable implements Drawable {
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

    private Font font = Font.getDefaultFont();
    private String string = "";

    /**
     * Creates a new empty text.
     */
    public Text() {
        super();
    }

    /**
     * Creates a new text.
     *
     * @param string The text string.
     */
    public Text(String string) {
        this();
        setString(string);
    }

    /**
     * Creates a new text.
     *
     * @param string The text string.
     * @param font   The font to use.
     */
    public Text(String string, Font font) {
        this(string);
        setFont(font);
    }

    /**
     * Creates a new text.
     *
     * @param string        The text string.
     * @param font          The font to use.
     * @param characterSize The font size.
     */
    public Text(String string, Font font, int characterSize) {
        this(string, font);
        setCharacterSize(characterSize);
    }

    @Override
    protected native long nativeCreate();

    private native void nativeSetString(String string);

    /**
     * Sets the string to display.
     *
     * @param string The string to display.
     */
    public void setString(@NotNull String string) {
        if (string == null)
            throw new IllegalArgumentException("string must not be null");

        this.string = string;
        nativeSetString(string);
    }

    private native void nativeSetFont(Font font);

    /**
     * Sets the text's font.
     *
     * @param font The text's font.
     */
    public void setFont(@NotNull Font font) {
        if (font == null)
            throw new IllegalArgumentException("font must not be null");

        this.font = font;
        nativeSetFont(font);
    }

    /**
     * Sets the font size for this text.
     *
     * @param characterSize The font size for this text.
     */
    public native void setCharacterSize(int characterSize);

    /**
     * Sets the font drawing style.
     *
     * @param style The font drawing style. This should be an ORed combination of the style flags
     *              {@link Text#BOLD}, {@link Text#ITALIC} and {@link  Text#UNDERLINED},
     *              or {@link Text#REGULAR} for a regular style.
     */
    public native void setStyle(int style);

    private native void nativeSetColor(Color color);

    /**
     * Sets the font color for this text.
     *
     * @param color The font color for this text.
     */
    public void setColor(@NotNull Color color) {
        if (color == null)
            throw new IllegalArgumentException("color must not be null");

        nativeSetColor(color);
    }

    /**
     * Gets the text's string.
     *
     * @return The text strng.
     */
    public String getString() {
        return string;
    }

    /**
     * Gets the text's current font.
     *
     * @return The text's current font.
     */
    public Font getFont() {
        return font;
    }

    /**
     * Gets the text's current font size.
     *
     * @return The text's current font size.
     */
    public native int getCharacterSize();

    /**
     * Gets the text's current font style.
     *
     * @return The text's current font style.
     * @see Text#setStyle(int)
     */
    public native int getStyle();

    /**
     * Gets the text's current font color.
     *
     * @return The text's current font color.
     */
    public native Color getColor();

    private native Vector2f nativeFindCharacterPos(int i);

    /**
     * Returns the position of a character inside the string.
     *
     * @param i The index of the character to return the position for.
     * @return The position of the character at the given index.
     */
    public Vector2f findCharacterPos(int i) {
        if (i < 0 || i >= string.length())
            throw new IndexOutOfBoundsException(Integer.toString(i));

        return nativeFindCharacterPos(i);
    }

    /**
     * Gets the sprite's local bounding rectangle, <i>not</i> taking the sprite's transformation into account.
     *
     * @return The sprite's local bounding rectangle.
     * @see org.jsfml.graphics.Sprite#getGlobalBounds()
     */
    public native FloatRect getLocalBounds();

    /**
     * Gets the sprite's global bounding rectangle, taking the sprite's transformation into account.
     *
     * @return The sprite's global bounding rectangle.
     * @see org.jsfml.graphics.Sprite#getLocalBounds()
     */
    public native FloatRect getGlobalBounds();

    @Override
    public native void setPosition(float x, float y);

    @Override
    public native void setRotation(float angle);

    @Override
    public native void setScale(float x, float y);

    @Override
    public native void setOrigin(float x, float y);

    @Override
    public native Vector2f getPosition();

    @Override
    public native float getRotation();

    @Override
    public native Vector2f getScale();

    @Override
    public native Vector2f getOrigin();

    @Override
    public native void move(float x, float y);

    @Override
    public native void rotate(float angle);

    @Override
    public native void scale(float x, float y);

    @Override
    public native Transform getTransform();

    @Override
    public native Transform getInverseTransform();
}