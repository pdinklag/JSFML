package org.jsfml.graphics;

import org.jsfml.system.Vector2f;

import java.util.Objects;

/**
 * Represents a graphical text that can be transformed and drawn to a render target.
 * <p/>
 * This class implements the {@code TextStyle} interface for quick access to the constants
 * provided by it.
 */
public class Text extends SFMLNativeTransformable implements Drawable, TextStyle {
    private ConstFont font = null;
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
     * @param font   The font to use.
     */
    public Text(String string, ConstFont font) {
        this();
        setFont(font);
        setString(string);
    }

    /**
     * Creates a new text.
     *
     * @param string        The text string.
     * @param font          The font to use.
     * @param characterSize The font size.
     */
    public Text(String string, ConstFont font, int characterSize) {
        this();
        setCharacterSize(characterSize);
        setFont(font);
        setString(string);
    }

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native long nativeCreate();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeSetExPtr();

    @Override
    @Deprecated
    @SuppressWarnings("deprecation")
    protected native void nativeDelete();

    private native void nativeSetString(String string);

    /**
     * Sets the string to display.
     *
     * @param string The string to display.
     */
    public void setString(String string) {
        this.string = Objects.requireNonNull(string);
        nativeSetString(string);
    }

    private native void nativeSetFont(Font font);

    /**
     * Sets the text's font.
     *
     * @param font The text's font.
     */
    public void setFont(ConstFont font) {
        this.font = Objects.requireNonNull(font);
        nativeSetFont((Font) font);
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
     * @param style The font drawing style. This should be an combination ({@code OR})
     *              of the style flags {@link TextStyle#BOLD}, {@link TextStyle#ITALIC} and
     *              {@link TextStyle#UNDERLINED}, or {@link TextStyle#REGULAR} for the
     *              regular style.
     */
    public native void setStyle(int style);

    private native void nativeSetColor(Color color);

    /**
     * Sets the font color for this text.
     *
     * @param color The font color for this text.
     */
    public void setColor(Color color) {
        nativeSetColor(Objects.requireNonNull(color));
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
     * @return The text's current font. This may be {@code null} if no font has been set yet.
     */
    public ConstFont getFont() {
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
    public void draw(RenderTarget target, RenderStates states) {
        DrawableNativeImpl.draw(this,
                Objects.requireNonNull(target),
                Objects.requireNonNull(states));
    }
}
