#include <JSFML/JNI/org_jsfml_audio_Listener.h>

#include <SFML/Audio/Listener.hpp>

/*
 * Class:     org_jsfml_audio_Listener
 * Method:    nativeSetGlobalVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Listener_nativeSetGlobalVolume
    (JNIEnv *env, jclass cls, jfloat vol) {

    sf::Listener::setGlobalVolume(vol);
}

/*
 * Class:     org_jsfml_audio_Listener
 * Method:    nativeSetPosition
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Listener_nativeSetPosition
    (JNIEnv *env , jclass cls, jfloat x, jfloat y, jfloat z) {

    sf::Listener::setPosition(x, y, z);
}

/*
 * Class:     org_jsfml_audio_Listener
 * Method:    nativeSetDirection
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Listener_nativeSetDirection
    (JNIEnv *env , jclass cls, jfloat x, jfloat y, jfloat z) {

    sf::Listener::setDirection(x, y, z);
}
