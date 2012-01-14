package org.jsfml.graphics;


import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for manipulating RGBA colors.
 * <p/>
 * This class is a simple data holder for the components <i>Red</i>, <i>Green</i>, <i>Blue</i> and <i>Alpha</i>
 * (opacity), all ranging between 0 and 255.
 */
@Intercom
public class Color implements Serializable {
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
     * Creates a new color from an AWT color.
     *
     * @param awtColor The AWT color to create this color from.
     */
    public Color(java.awt.Color awtColor) {
        this(
                awtColor.getRed(),
                awtColor.getGreen(),
                awtColor.getBlue(),
                awtColor.getAlpha());
    }

    /**
     * Gets the red component of the color.
     *
     * @return The color's red component.
     */
    public int getR() {
        return r;
    }

    /**
     * Sets the red component of the color.
     *
     * @param r The color's new red component.
     */
    public void setR(int r) {
        this.r = clamp(r);
    }

    /**
     * Gets the green component of the color.
     *
     * @return The color's green component.
     */
    public int getG() {
        return g;
    }

    /**
     * Sets the green component of the color.
     *
     * @param g The color's new green component.
     */
    public void setG(int g) {
        this.g = clamp(g);
    }

    /**
     * Gets the blue component of the color.
     *
     * @return The color's blue component.
     */
    public int getB() {
        return b;
    }

    /**
     * Sets the blue component of the color.
     *
     * @param b The color's new blue component.
     */
    public void setB(int b) {
        this.b = clamp(b);
    }

    /**
     * Gets the alpha component of the color.
     *
     * @return The color's alpha component.
     */
    public int getA() {
        return a;
    }

    /**
     * Sets the alpha component of the color.
     *
     * @param a The color's new alpha component.
     */
    public void setA(int a) {
        this.a = clamp(a);
    }

    /**
     * Modulates the color by adding another color.
     * <p/>
     * The addition is performed per component. Components exceeding 255 will be clamped to that value.
     *
     * @param color The color to add.
     * @return A new color object, representing the modulated color.
     */
    public Color add(Color color) {
        return new Color(
                r + color.r,
                g + color.g,
                b + color.b,
                a + color.a);
    }

    /**
     * Modulates the color by multiplying it with another color.
     * <p/>
     * The multiplication is performed per component. The components are each divided by 255 after the operation.
     *
     * @param color The color to add.
     * @return A new color object, representing the modulated color.
     */
    public Color mul(Color color) {
        return new Color(
                r * color.r / 255,
                g * color.g / 255,
                b * color.b / 255,
                a * color.a / 255);
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

    /**
     * Converts this color to an AWT color.
     *
     * @return The AWT color converted from this color.
     */
    public java.awt.Color toAWT() {
        return new java.awt.Color(r, g, b, a);
    }
}
