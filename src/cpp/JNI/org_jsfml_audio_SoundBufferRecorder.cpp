#include <JSFML/JNI/org_jsfml_audio_SoundBufferRecorder.h>

#include <JSFML/Intercom/NativeObject.hpp>

#include <SFML/Audio/SoundBufferRecorder.hpp>

/*
 * Class:     org_jsfml_audio_SoundBufferRecorder
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_SoundBufferRecorder_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::SoundBufferRecorder();
}

/*
 * Class:     org_jsfml_audio_SoundBufferRecorder
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundBufferRecorder_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::SoundBufferRecorder);
}

/*
 * Class:     org_jsfml_audio_SoundBufferRecorder
 * Method:    nativeGetBuffer
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_SoundBufferRecorder_nativeGetBuffer (JNIEnv *env, jobject obj) {
    return (jlong)&THIS(sf::SoundBufferRecorder)->GetBuffer();
}

/*
 * Class:     org_jsfml_audio_SoundBufferRecorder
 * Method:    start
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundBufferRecorder_start
    (JNIEnv *env, jobject obj, jint sampleRate) {

    THIS(sf::SoundBufferRecorder)->Start(sampleRate);
}

/*
 * Class:     org_jsfml_audio_SoundBufferRecorder
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundBufferRecorder_stop (JNIEnv *env, jobject obj) {
    THIS(sf::SoundBufferRecorder)->Stop();
}

/*
 * Class:     org_jsfml_audio_SoundBufferRecorder
 * Method:    getSampleRate
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_audio_SoundBufferRecorder_getSampleRate (JNIEnv *env, jobject obj) {
    return THIS(sf::SoundBufferRecorder)->GetSampleRate();
}
