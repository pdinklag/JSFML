#include <JSFML/JNI/org_jsfml_audio_SoundStream.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/SoundStream.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Audio/SoundStream.hpp>

#define SOUND_STREAM JSFML::NativeObject::GetExPointer<sf::SoundStream>(env, obj, org_jsfml_internal_ExPtr_SOUND_STREAM)

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_SoundStream_nativeCreate (JNIEnv *env, jobject obj) {
	return (jlong)new JSFML::SoundStream(env, obj);
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_SOUND_SOURCE,
        dynamic_cast<sf::SoundSource*>(THIS(sf::SoundStream)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_SOUND_STREAM,
        dynamic_cast<sf::SoundStream*>(THIS(JSFML::SoundStream)));
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(JSFML::SoundStream);
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    play
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_play (JNIEnv *env, jobject obj) {
	SOUND_STREAM->play();
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    pause
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_pause (JNIEnv *env, jobject obj) {
	SOUND_STREAM->pause();
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_stop (JNIEnv *env, jobject obj) {
	SOUND_STREAM->stop();
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    nativeSetLoop
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_nativeSetLoop (JNIEnv *env, jobject obj, jboolean b) {
	SOUND_STREAM->setLoop(b);
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    nativeSetPlayingOffset
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_nativeSetPlayingOffset
    (JNIEnv *env, jobject obj, jlong offset) {

	SOUND_STREAM->setPlayingOffset(sf::microseconds(offset));
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    nativeGetStatus
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_audio_SoundStream_nativeGetStatus (JNIEnv *env, jobject obj) {
	return (jint)SOUND_STREAM->getStatus();
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    nativeInitialize
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_nativeInitialize
    (JNIEnv *env, jobject obj, jint channelCount, jint sampleRate) {

    THIS(JSFML::SoundStream)->initialize(channelCount, sampleRate);
}
