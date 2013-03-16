package org.jsfml.graphics;

import org.jsfml.internal.IntercomHelper;
import org.jsfml.system.Vector2f;

import java.nio.Buffer;
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
    private Color color = Color.WHITE;
    private int style = TextStyle.REGULAR;
    private int characterSize = 30;

    private boolean boundsNeedUpdate = true;
    private FloatRect localBounds = null;
    private FloatRect globalBounds = null;

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
        boundsNeedUpdate = true;
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
        boundsNeedUpdate = true;
    }

    private native void nativeSetCharacterSize(int characterSize);

    /**
     * Sets the font size for this text.
     *
     * @param characterSize The font size for this text.
     */
    public void setCharacterSize(int characterSize) {
        nativeSetCharacterSize(characterSize);
        this.characterSize = characterSize;
        boundsNeedUpdate = true;
    }

    private native void nativeSetStyle(int style);

    /**
     * Sets the font drawing style.
     *
     * @param style The font drawing style. This should be an combination ({@code OR})
     *              of the style flags {@link TextStyle#BOLD}, {@link TextStyle#ITALIC} and
     *              {@link TextStyle#UNDERLINED}, or {@link TextStyle#REGULAR} for the
     *              regular style.
     */
    public void setStyle(int style) {
        nativeSetStyle(style);
        this.style = style;
        boundsNeedUpdate = true;
    }

    private native void nativeSetColor(int color);

    /**
     * Sets the font color for this text.
     *
     * @param color The font color for this text.
     */
    public void setColor(Color color) {
        nativeSetColor(IntercomHelper.encodeColor(color));
        this.color = color;
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
    public int getCharacterSize() {
        return characterSize;
    }

    /**
     * Gets the text's current font style.
     *
     * @return The text's current font style.
     * @see Text#setStyle(int)
     */
    public int getStyle() {
        return style;
    }

    /**
     * Gets the text's current font color.
     *
     * @return The text's current font color.
     */
    public Color getColor() {
        return color;
    }

    private native long nativeFindCharacterPos(int i);

    /**
     * Returns the position of a character inside the string.
     *
     * @param i The index of the character to return the position for.
     * @return The position of the character at the given index.
     */
    public Vector2f findCharacterPos(int i) {
        if (i < 0 || i >= string.length())
            throw new StringIndexOutOfBoundsException(Integer.toString(i));

        return IntercomHelper.decodeVector2f(nativeFindCharacterPos(i));
    }

    private native void nativeGetLocalBounds(Buffer buf);

    private native void nativeGetGlobalBounds(Buffer buf);

    private void updateBounds() {
        if(boundsNeedUpdate) {
            nativeGetLocalBounds(IntercomHelper.getBuffer());
            localBounds = IntercomHelper.decodeFloatRect();

            nativeGetGlobalBounds(IntercomHelper.getBuffer());
            globalBounds = IntercomHelper.decodeFloatRect();

            boundsNeedUpdate = false;
        }
    }

    /**
     * Gets the text's local bounding rectangle,
     * <i>not</i> taking the text's transformation into account.
     *
     * @return the text's local bounding rectangle.
     * @see org.jsfml.graphics.Sprite#getGlobalBounds()
     */
    public FloatRect getLocalBounds() {
        if(boundsNeedUpdate) {
            updateBounds();
        }

        return localBounds;
    }

    /**
     * Gets the text's global bounding rectangle in the scene,
     * taking the text's transformation into account.
     *
     * @return the text's global bounding rectangle.
     * @see org.jsfml.graphics.Text#getLocalBounds()
     */
    public FloatRect getGlobalBounds() {
        if(boundsNeedUpdate) {
            updateBounds();
        }

        return globalBounds;
    }

    @Override
    public void setPosition(Vector2f v) {
        super.setPosition(v);
        boundsNeedUpdate = true;
    }

    @Override
    public void setRotation(float angle) {
        super.setRotation(angle);
        boundsNeedUpdate = true;
    }

    @Override
    public void setScale(Vector2f v) {
        super.setScale(v);
        boundsNeedUpdate = true;
    }

    @Override
    public void setOrigin(Vector2f v) {
        super.setOrigin(v);
        boundsNeedUpdate = true;
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        SFMLNativeDrawer.draw(this,
                Objects.requireNonNull(target),
                Objects.requireNonNull(states));
    }
}
