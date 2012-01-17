#include <JSFML/JNI/org_jsfml_audio_Sound.h>

#include <JSFML/Intercom/NativeObject.hpp>
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
	THIS(sf::Sound)->Play();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    pause
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_pause (JNIEnv *env, jobject obj) {
	THIS(sf::Sound)->Pause();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_stop (JNIEnv *env, jobject obj) {
	THIS(sf::Sound)->Stop();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    nativeSetBuffer
 * Signature: (Lorg/jsfml/audio/SoundBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_nativeSetBuffer (JNIEnv *env, jobject obj, jobject buffer) {
	THIS(sf::Sound)->SetBuffer(*JSFML::NativeObject::GetPointer<sf::SoundBuffer>(env, buffer));
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setLoop
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setLoop (JNIEnv *env, jobject obj, jboolean b) {
	THIS(sf::Sound)->SetLoop(b);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setPlayingOffset
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setPlayingOffset (JNIEnv *env, jobject obj, jlong offset) {
	THIS(sf::Sound)->SetPlayingOffset(offset & 0xFFFFFFFF);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    isLoop
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_Sound_isLoop (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->GetLoop();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getPlayingOffset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_Sound_getPlayingOffset (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->GetPlayingOffset();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    nativeGetStatus
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_audio_Sound_nativeGetStatus (JNIEnv *env, jobject obj) {
	return (jint)THIS(sf::Sound)->GetStatus();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getPitch
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Sound_getPitch (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->GetPitch();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setPitch
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setPitch (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Sound)->SetPitch(f);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getVolume
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Sound_getVolume (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->GetVolume();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setVolume (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Sound)->SetVolume(f);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector3f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Sound_getPosition (JNIEnv *env, jobject obj) {
	return JSFML::Vector3f::FromSFML(env, THIS(sf::Sound)->GetPosition());
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setPosition
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setPosition
    (JNIEnv *env, jobject obj, jfloat x, jfloat y, jfloat z) {

	THIS(sf::Sound)->SetPosition(x, y, z);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    isRelativeToListener
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_Sound_isRelativeToListener (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->IsRelativeToListener();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setRelativeToListener
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setRelativeToListener
    (JNIEnv *env, jobject obj, jboolean b) {

	THIS(sf::Sound)->SetRelativeToListener(b);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getMinDistance
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Sound_getMinDistance (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->GetMinDistance();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setMinDistance
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setMinDistance (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Sound)->SetMinDistance(f);
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    getAttenuation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Sound_getAttenuation (JNIEnv *env, jobject obj) {
	return THIS(sf::Sound)->GetAttenuation();
}

/*
 * Class:     org_jsfml_audio_Sound
 * Method:    setAttenuation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Sound_setAttenuation (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Sound)->SetAttenuation(f);
}
