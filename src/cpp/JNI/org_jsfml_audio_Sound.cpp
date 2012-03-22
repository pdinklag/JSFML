#include <JSFML/JNI/org_jsfml_audio_Sound.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Time.hpp>
#include <JSFML/Intercom/Vector3f.hpp>

#include <SFML/Audio/Sound.hpp>

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_Sound_nativeCreate (JNIEnv *env, jobject obj) {
	return (jlong)(new sf::Sound());
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Sound);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    nativeCopy
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_Sound_nativeCopy (JNIEnv *env, jobject obj) {
	return (jlong)(new sf::Sound(*THIS(sf::Sound)));
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    play
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_play (JNIEnv *env, jobject obj) {
	THIS(sf::Sound)->play();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    pause
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_pause (JNIEnv *env, jobject obj) {
	THIS(sf::Sound)->pause();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_stop (JNIEnv *env, jobject obj) {
	THIS(sf::Sound)->stop();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    nativeSetBuffer
 * Signature: (Lorg/jsfml/audio/SoundBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_nativeSetBuffer
    (JNIEnv *env, jobject obj, jobject buffer) {

	THIS(sf::Sound)->setBuffer(*JSFML::NativeObject::GetPointer<sf::SoundBuffer>(env, buffer));
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setLoop
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setLoop (JNIEnv *env, jobject obj, jboolean b) {
	THIS(sf::Sound)->setLoop(b);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    nativeSetPlayingOffset
 * Signature: (Lorg/jsfml/system/Time;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_c
    (JNIEnv *env, jobject obj, jobject time) {

	THIS(sf::Sound)->setPlayingOffset(JSFML::Time::ToSFML(env, time));
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    isLoop
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_Sound_isLoop (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->getLoop();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getPlayingOffset
 * Signature: ()Lorg/jsfml/system/Time;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Sound_getPlayingOffset (JNIEnv *env, jobject obj) {
	return JSFML::Time::FromSFML(env, THIS(sf::Sound)->getPlayingOffset());
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    nativeGetStatus
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_audio_Sound_nativeGetStatus (JNIEnv *env, jobject obj) {
	return (jint)THIS(sf::Sound)->getStatus();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getPitch
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Sound_getPitch (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->getPitch();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setPitch
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setPitch (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Sound)->setPitch(f);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getVolume
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Sound_getVolume (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->getVolume();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setVolume (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Sound)->setVolume(f);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector3f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Sound_getPosition (JNIEnv *env, jobject obj) {
	return JSFML::Vector3f::FromSFML(env, THIS(sf::Sound)->getPosition());
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setPosition
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setPosition
    (JNIEnv *env, jobject obj, jfloat x, jfloat y, jfloat z) {

	THIS(sf::Sound)->setPosition(x, y, z);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    isRelativeToListener
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_Sound_isRelativeToListener (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->isRelativeToListener();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setRelativeToListener
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setRelativeToListener
    (JNIEnv *env, jobject obj, jboolean b) {

	THIS(sf::Sound)->setRelativeToListener(b);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getMinDistance
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Sound_getMinDistance (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->getMinDistance();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setMinDistance
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setMinDistance (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Sound)->setMinDistance(f);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getAttenuation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Sound_getAttenuation (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->getAttenuation();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setAttenuation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setAttenuation (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Sound)->setAttenuation(f);
}
