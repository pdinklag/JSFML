package org.jsfml.internal;

/**
 * Holds index definitions for the {@link SFMLNativeObject#exPtr} array.
 */
@Intercom
final class ExPtr {
    //Total amount of exPtr fields.
    @Intercom
    static final int NUM = 3;

    //Pointer to sf::Drawable.
    @Intercom
    static final int DRAWABLE = 0;

    //Pointer to sf::Transformable.
    @Intercom
    static final int TRANSFORMABLE = 1;

    //Pointer to sf::Shape.
    @Intercom
    static final int SHAPE = 2;

    //Pointer to sf::RenderTarget.
    @Intercom
    static final int RENDER_TARGET = 0;

    //Pointer to sf::Window.
    @Intercom
    static final int WINDOW = 1;

    //Pointer to sf::SoundSource.
    @Intercom
    static final int SOUND_SOURCE = 0;

    //Pointer to sf::SoundStream.
    @Intercom
    static final int SOUND_STREAM = 1;

    //Pointer to sf::SoundRecorder.
    @Intercom
    static final int SOUND_RECORDER = 0;
}
