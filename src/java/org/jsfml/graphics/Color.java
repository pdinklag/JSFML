package org.jsfml.graphics;


import org.jsfml.Intercom;

import java.io.Serializable;

/**
 * Utility class for manipulating RGBA colors.
 */
@Intercom
public final class Color implements Serializable {
    private static final long serialVersionUID = -161207563051572152L;

    /**
     * Black {@code (RGB:0, 0, 0)}
     */
    public final static Color BLACK = new Color(0, 0, 0);

    /**
     * White {@code (RGB:255, 255, 255)}
     */
    public final static Color WHITE = new Color(255, 255, 255);

    /**
     * Red {@code (RGB:255, 0, 0)}
     */
    public final static Color RED = new Color(255, 0, 0);

    /**
     * Green {@code (RGB:0, 255, 0)}
     */
    public final static Color GREEN = new Color(0, 255, 0);

    /**
     * Blue {@code (RGB:0, 0, 255)}
     */
    public final static Color BLUE = new Color(0, 0, 255);

    /**
     * Yellow {@code (RGB:255, 255, 0)}
     */
    public final static Color YELLOW = new Color(255, 255, 0);

    /**
     * Magenta {@code (RGB:255, 255, 0)}
     */
    public final static Color MAGENTA = new Color(255, 0, 255);

    /**
     * Cyan {@code (RGB:0, 255, 255)}
     */
    public final static Color CYAN = new Color(0, 255, 255);

    /**
     * Transparent {@code (RGBA:0, 0, 0, 0)}
     */
    public final static Color TRANSPARENT = new Color(0, 0, 0, 0);

    /**
     * Modulates two colors by adding them to one another.
     *
     * @param a The left color.
     * @param b The right color.
     * @return A new color, the modulation of the two given colors.
     */
    public static Color add(Color a, Color b) {
        return new Color(
                a.r + b.r,
                a.g + b.g,
                a.b + b.b,
                a.a + b.a);
    }

    /**
     * Modulates two colors by multiplying their components.
     *
     * @param a The left color.
     * @param b The right color.
     * @return A new color, the modulation of the two given colors.
     */
    public static Color mul(Color a, Color b) {
        return new Color(
                (a.r * b.r) / 255,
                (a.g * b.g) / 255,
                (a.b * b.b) / 255,
                (a.a * b.a) / 255);
    }

    /**
     * Modulates a color by multiplying its components with a factor.
     *
     * @param color The color.
     * @param f     The factor.
     * @return A new color, the modulation of the given color.
     */
    public static Color mul(Color color, float f) {
        return new Color(
                (int) (color.r * f),
                (int) (color.g * f),
                (int) (color.b * f),
                (int) (color.a * f));
    }

    private static int clamp(int x) {
        return Math.max(0, Math.min(x, 255));
    }

    /**
     * The red component of the color.
     */
    @Intercom
    public final int r;

    /**
     * The green component of the color.
     */
    @Intercom
    public final int g;

    /**
     * The blue component of the color.
     */
    @Intercom
    public final int b;

    /**
     * The alpha component of the color.
     */
    @Intercom
    public final int a;

    /**
     * Creates a new default color {@code (RGBA:0, 0, 0, 255)}.
     */
    public Color() {
        this(0, 0, 0, 255);
    }

    /**
     * Creates a new color.
     * <p/>
     * The alpha value will be set to {@code 255}.
     *
     * @param r The color's red component.
     * @param g The color's green component.
     * @param b The color's blue component.
     */
    public Color(int r, int g, int b) {
        this(r, g, b, 255);
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

    @Override
    public boolean equals(Object o) {
        return (o instanceof Color &&
                ((Color) o).r == r &&
                ((Color) o).g == g &&
                ((Color) o).b == b &&
                ((Color) o).a == a);
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
