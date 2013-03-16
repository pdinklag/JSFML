package org.jsfml.graphics;


import java.io.Serializable;

/**
 * Represents RGBA colors.
 */
public final class Color implements Serializable {
    private static final long serialVersionUID = -161207563051572152L;

    /**
     * Black {@code (0, 0, 0)}
     */
    public final static Color BLACK = new Color(0, 0, 0);

    /**
     * White {@code (255, 255, 255)}
     */
    public final static Color WHITE = new Color(255, 255, 255);

    /**
     * Red {@code (255, 0, 0)}
     */
    public final static Color RED = new Color(255, 0, 0);

    /**
     * Green {@code (0, 255, 0)}
     */
    public final static Color GREEN = new Color(0, 255, 0);

    /**
     * Blue {@code (0, 0, 255)}
     */
    public final static Color BLUE = new Color(0, 0, 255);

    /**
     * Yellow {@code (255, 255, 0)}
     */
    public final static Color YELLOW = new Color(255, 255, 0);

    /**
     * Magenta {@code (255, 255, 0)}
     */
    public final static Color MAGENTA = new Color(255, 0, 255);

    /**
     * Cyan {@code (0, 255, 255)}
     */
    public final static Color CYAN = new Color(0, 255, 255);

    /**
     * Transparent {@code (0, 0, 0, 0)}
     */
    public final static Color TRANSPARENT = new Color(0, 0, 0, 0);

    /**
     * Modulates two colors by performing a component-wise addition.
     *
     * @param a the first color.
     * @param b the second color.
     * @return the modulated color.
     */
    public static Color add(Color a, Color b) {
        return new Color(
                a.r + b.r,
                a.g + b.g,
                a.b + b.b,
                a.a + b.a);
    }

    /**
     * Modulates two colors by performing a component-wise multiplication.
     *
     * @param a the first color.
     * @param b the second color.
     * @return the modulated color.
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
     * @param color the color.
     * @param f     the factor.
     * @return the modulated color.
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
    public final int r;

    /**
     * The green component of the color.
     */
    public final int g;

    /**
     * The blue component of the color.
     */
    public final int b;

    /**
     * The alpha component of the color, ranging between 0 (transparent) and 255 (fully opaque).
     */
    public final int a;

    /**
     * Constructs a new color with the specified color components and an alpha value of 255
     * (fully opaque).
     *
     * @param r the color's red component.
     * @param g the color's green component.
     * @param b the color's blue component.
     */
    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    /**
     * Constructs a new color with the specified color and alpha components.
     *
     * @param r the color's red component.
     * @param g the color's green component.
     * @param b the color's blue component.
     * @param a the color's alpha component, ranging between 0 (transparent) and 255 (fully opaque).
     */
    public Color(int r, int g, int b, int a) {
        this.r = clamp(r);
        this.g = clamp(g);
        this.b = clamp(b);
        this.a = clamp(a);
    }

    /**
     * Constructs a new color by copying another color and resetting the alpha value.
     *
     * @param color the color to copy.
     * @param alpha the alpha value of the new color,
     *              ranging between 0 (transparent) and 255 (fully opaque).
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
