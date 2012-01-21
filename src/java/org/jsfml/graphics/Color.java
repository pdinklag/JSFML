package org.jsfml.graphics;


import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for manipulating RGBA colors.
 */
@Intercom
public class Color implements Cloneable, Serializable {
    private static final long serialVersionUID = -161207563051572152L;

    /**
     * Black <tt>(RGB:0,0,0)</tt>
     */
    public final static Color BLACK = new Color(0, 0, 0);

    /**
     * White <tt>(RGB:255,255,255)</tt>
     */
    public final static Color WHITE = new Color(255, 255, 255);
    /**
     * Red <tt>(RGB:255,0,0)</tt>
     */
    public final static Color RED = new Color(255, 0, 0);
    /**
     * Green <tt>(RGB:0,255,0)</tt>
     */
    public final static Color GREEN = new Color(0, 255, 0);
    /**
     * Blue <tt>(RGB:0,0,255)</tt>
     */
    public final static Color BLUE = new Color(0, 0, 255);
    /**
     * Yellow <tt>(RGB:255,255,0)</tt>
     */
    public final static Color YELLOW = new Color(255, 255, 0);
    /**
     * Magenta <tt>(RGB:255,255,0)</tt>
     */
    public final static Color MAGENTA = new Color(255, 0, 255);
    /**
     * Cyan <tt>(RGB:0,255,255)</tt>
     */
    public final static Color CYAN = new Color(0, 255, 255);

    /**
     * Modulates two colors by adding them to one another.
     *
     * @param a The left color.
     * @param b The right color.
     * @return A new color, the modulation of the two given colors.
     */
    public static Color add(Color a, Color b) {
        return new Color(a).add(b);
    }

    /**
     * Modulates two colors by subtracting one color from another.
     *
     * @param a The left color.
     * @param b The right color.
     * @return A new color, the modulation of the two given colors.
     */
    public static Color sub(Color a, Color b) {
        return new Color(a).sub(b);
    }

    /**
     * Modulates two colors by multiplying their components.
     *
     * @param a The left color.
     * @param b The right color.
     * @return A new color, the modulation of the two given colors.
     */
    public static Color mul(Color a, Color b) {
        return new Color(a).mul(b);
    }

    /**
     * Modulates a color by multiplying its components with a factor.
     *
     * @param color The color.
     * @param f     The factor.
     * @return A new color, the modulation of the given color.
     */
    public static Color mul(Color color, float f) {
        return new Color(color).mul(f);
    }

    private static int clamp(int x) {
        return Math.max(0, Math.min(x, 255));
    }

    @Intercom
    private int r = 0;

    @Intercom
    private int g = 0;

    @Intercom
    private int b = 0;

    @Intercom
    private int a = 255;

    /**
     * Creates a new default color <tt>(RGBA:0,0,0,255)</tt>.
     */
    public Color() {
    }

    /**
     * Creates a new color.
     * <p/>
     * The alpha value will be set to <tt>255</tt>.
     *
     * @param r The color's red component.
     * @param g The color's green component.
     * @param b The color's blue component.
     */
    public Color(int r, int g, int b) {
        this.r = clamp(r);
        this.g = clamp(g);
        this.b = clamp(b);
        this.a = 255;
    }

    /**
     * Creates a new color.
     *
     * @param r The color's red component.
     * @param g The color's green component.
     * @param b The color's blue component.
     * @param a The color's alpha component.
     */
    @Intercom
    public Color(int r, int g, int b, int a) {
        this.r = clamp(r);
        this.g = clamp(g);
        this.b = clamp(b);
        this.a = clamp(a);
    }

    /**
     * Creates a color from another color.
     *
     * @param color The color to copy.
     */
    public Color(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = color.a;
    }

    /**
     * Creates a color from another color with a new alpha value.
     *
     * @param color The color to copy.
     * @param alpha The alpha value of the new color.
     */
    public Color(Color color, int alpha) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = clamp(alpha);
    }

    /**
     * Gets the red component of the color.
     *
     * @return The color's red component.
     */
    public int getRed() {
        return r;
    }

    /**
     * Sets the red component of the color.
     *
     * @param r The color's new red component.
     */
    public void setRed(int r) {
        this.r = clamp(r);
    }

    /**
     * Gets the green component of the color.
     *
     * @return The color's green component.
     */
    public int getGreen() {
        return g;
    }

    /**
     * Sets the green component of the color.
     *
     * @param g The color's new green component.
     */
    public void setGreen(int g) {
        this.g = clamp(g);
    }

    /**
     * Gets the blue component of the color.
     *
     * @return The color's blue component.
     */
    public int getBlue() {
        return b;
    }

    /**
     * Sets the blue component of the color.
     *
     * @param b The color's new blue component.
     */
    public void setBlue(int b) {
        this.b = clamp(b);
    }

    /**
     * Gets the alpha component of the color.
     *
     * @return The color's alpha component.
     */
    public int getAlpha() {
        return a;
    }

    /**
     * Sets the alpha component of the color.
     *
     * @param a The color's new alpha component.
     */
    public void setAlpha(int a) {
        this.a = clamp(a);
    }

    /**
     * Modulates the color by adding another color.
     * <p/>
     * The addition is performed per component. Components exceeding 255 will be clamped to that value.
     *
     * @param color The color to add.
     * @return This color after the modulation.
     */
    public Color add(Color color) {
        this.r = clamp(this.r + color.r);
        this.g = clamp(this.g + color.g);
        this.b = clamp(this.b + color.b);
        this.a = clamp(this.a + color.a);
        return this;
    }

    /**
     * Modulates the color by subtracting another color from it.
     * <p/>
     * The addition is performed per component. Components exceeding 255 will be clamped to that value.
     *
     * @param color The color to subtract.
     * @return This color after the modulation.
     */
    public Color sub(Color color) {
        this.r = clamp(this.r - color.r);
        this.g = clamp(this.g - color.g);
        this.b = clamp(this.b - color.b);
        this.a = clamp(this.a - color.a);
        return this;
    }

    /**
     * Modulates the color by multiplying it with another color.
     * <p/>
     * The multiplication is performed per component. The components are each divided by 255 after the operation.
     *
     * @param color The color to multiply.
     * @return This color after the modulation.
     */
    public Color mul(Color color) {
        this.r = clamp(this.r * color.r / 255);
        this.g = clamp(this.g * color.g / 255);
        this.b = clamp(this.b * color.b / 255);
        this.a = clamp(this.a * color.a / 255);
        return this;
    }

    /**
     * Modulates the color by multiplying its components with a factor.
     *
     * @param f The factor.
     * @return This color after the modulation.
     */
    public Color mul(float f) {
        this.r = clamp((int) (this.r * f));
        this.g = clamp((int) (this.g * f));
        this.b = clamp((int) (this.b * f));
        this.a = clamp((int) (this.a * f));
        return this;
    }

    @Override
    protected Color clone() throws CloneNotSupportedException {
        return (Color) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Color)) return false;

        Color color = (Color) o;

        if (a != color.a) return false;
        if (b != color.b) return false;
        if (g != color.g) return false;
        if (r != color.r) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = r;
        result = 31 * result + g;
        result = 31 * result + b;
        result = 31 * result + a;
        return result;
    }

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
