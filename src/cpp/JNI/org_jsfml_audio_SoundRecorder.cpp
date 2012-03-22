#include <JSFML/JNI/org_jsfml_audio_SoundRecorder.h>

#include <SFML/Audio/SoundRecorder.hpp>

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    isAvailable
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundRecorder_isAvailable (JNIEnv *env, jclass cls) {
    return sf::SoundRecorder::isAvailable();
}
