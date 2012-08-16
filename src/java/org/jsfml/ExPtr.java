package org.jsfml;

/**
 * Holds index definitions for the {@link SFMLNativeObject#exPtr} array.
 */
final class ExPtr {
    //Total amount of exPtr fields.
    static final int NUM = 4;

    //Pointer to sf::Drawable.
    static final int DRAWABLE = 0;

    //Pointer to sf::Transformable.
    static final int TRANSFORMABLE = 1;

    //Pointer to sf::RenderTarget.
    static final int RENDER_TARGET = 2;

    //Pointer to sf::Shape.
    static final int SHAPE = 3;
}
