#include <JSFML/JNI/org_jsfml_audio_SoundStream.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/SoundStream.hpp>
#include <JSFML/Intercom/Time.hpp>

#include <JSFML/JNI/org_jsfml_ExPtr.h>

#include <SFML/Audio/SoundStream.hpp>

#define SOUND_STREAM JSFML::NativeObject::GetExPointer<sf::SoundStream>(env, obj, org_jsfml_ExPtr_SOUND_STREAM)


/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_SoundStream_nativeCreate (JNIEnv *env, jobject obj) {
	JSFML::SoundStream *stream = new JSFML::SoundStream(obj);

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_SOUND_SOURCE,
        dynamic_cast<sf::SoundSource*>(stream));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_SOUND_STREAM,
        dynamic_cast<sf::SoundStream*>(stream));

	return (jlong)stream;
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
 * Method:    getSampleRate
 * Signature: ()Ig
 */
JNIEXPORT jint JNICALL Java_org_jsfml_audio_SoundStream_getSampleRate (JNIEnv *env, jobject obj) {
    return SOUND_STREAM->getSampleRate();
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    getChannelCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_audio_SoundStream_getChannelCount (JNIEnv *env, jobject obj) {
   return SOUND_STREAM->getChannelCount();
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    setLoop
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_setLoop (JNIEnv *env, jobject obj, jboolean b) {
	SOUND_STREAM->setLoop(b);
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    nativeSetPlayingOffset
 * Signature: (Lorg/jsfml/system/Time;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_nativeSetPlayingOffset
    (JNIEnv *env, jobject obj, jobject offset) {

	SOUND_STREAM->setPlayingOffset(JSFML::Time::ToSFML(env, offset));
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    isLoop
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundStream_isLoop (JNIEnv *env, jobject obj) {
	return SOUND_STREAM->getLoop();
}

/*
 * Class:     org_jsfml_audio_SoundStream
 * Method:    getPlayingOffset
 * Signature: ()Lorg/jsfml/system/Time;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_SoundStream_getPlayingOffset (JNIEnv *env, jobject obj) {
	return JSFML::Time::FromSFML(env, SOUND_STREAM->getPlayingOffset());
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
 * Method:    initialize
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundStream_initialize
    (JNIEnv *env, jobject obj, jint channelCount, jint sampleRate) {

    THIS(JSFML::SoundStream)->initialize(channelCount, sampleRate);
}
