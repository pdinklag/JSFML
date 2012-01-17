#include <JSFML/JNI/org_jsfml_audio_Listener.h>

#include <JSFML/Intercom/Vector3f.hpp>

#include <SFML/Audio/Listener.hpp>

/*
 * Class:     org_jsfml_audio_Listener
 * Method:    setGlobalVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Listener_setGlobalVolume
    (JNIEnv *env, jclass cls, jfloat vol) {

    sf::Listener::SetGlobalVolume(vol);
}

/*
 * Class:     org_jsfml_audio_Listener
 * Method:    getGlobalVolume
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Listener_getGlobalVolume (JNIEnv *env, jclass cls) {
    return sf::Listener::GetGlobalVolume();
}

/*
 * Class:     org_jsfml_audio_Listener
 * Method:    setPosition
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Listener_setPosition
    (JNIEnv *env , jclass cls, jfloat x, jfloat y, jfloat z) {

    sf::Listener::SetPosition(x, y, z);
}

/*
 * Class:     org_jsfml_audio_Listener
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector3f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Listener_getPosition (JNIEnv *env, jclass cls) {
    return JSFML::Vector3f::FromSFML(env, sf::Listener::GetPosition());
}

/*
 * Class:     org_jsfml_audio_Listener
 * Method:    setDirection
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Listener_setDirection
    (JNIEnv *env , jclass cls, jfloat x, jfloat y, jfloat z) {

    sf::Listener::SetDirection(x, y, z);
}

/*
 * Class:     org_jsfml_audio_Listener
 * Method:    getDirection
 * Signature: ()Lorg/jsfml/system/Vector3f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Listener_getDirection (JNIEnv *env, jclass cls) {
    return JSFML::Vector3f::FromSFML(env, sf::Listener::GetDirection());
}
