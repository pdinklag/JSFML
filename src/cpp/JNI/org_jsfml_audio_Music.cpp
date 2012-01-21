#include <JSFML/JNI/org_jsfml_audio_Music.h>

#include <JSFML/Intercom/JavaString.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Time.hpp>
#include <JSFML/Intercom/Vector3f.hpp>

#include <SFML/Audio/Music.hpp>

/*
 * Class:     org_jsfml_audio_Music
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_Music_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Music();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Music);
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    nativeOpenFromFile
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_Music_nativeOpenFromFile
    (JNIEnv *env, jobject obj, jstring fileName) {

    return THIS(sf::Music)->OpenFromFile(std::string(JavaString::getUTF8(env, fileName)));
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getDuration
 * Signature: ()Lorg/jsfml/system/Time
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Music_getDuration (JNIEnv *env, jobject obj) {
    return JSFML::Time::FromSFML(env, THIS(sf::Music)->GetDuration());
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    play
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_play (JNIEnv *env, jobject obj) {
	THIS(sf::Music)->Play();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    pause
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_pause (JNIEnv *env, jobject obj) {
	THIS(sf::Music)->Pause();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_stop (JNIEnv *env, jobject obj) {
	THIS(sf::Music)->Stop();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getSampleRate
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_audio_Music_getSampleRate (JNIEnv *env, jobject obj) {
    return THIS(sf::Music)->GetSampleRate();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getChannelCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_audio_Music_getChannelCount (JNIEnv *env, jobject obj) {
   return THIS(sf::Music)->GetChannelCount();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    setLoop
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_setLoop (JNIEnv *env, jobject obj, jboolean b) {
	THIS(sf::Music)->SetLoop(b);
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    nativeSetPlayingOffset
 * Signature: (Lorg/jsfml/system/Time;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_nativeSetPlayingOffset
    (JNIEnv *env, jobject obj, jobject offset) {

	THIS(sf::Music)->SetPlayingOffset(JSFML::Time::ToSFML(env, offset));
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    isLoop
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_Music_isLoop (JNIEnv *env, jobject obj) {
	return THIS(sf::Music)->GetLoop();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getPlayingOffset
 * Signature: ()Lorg/jsfml/system/Time;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Music_getPlayingOffset (JNIEnv *env, jobject obj) {
	return JSFML::Time::FromSFML(env, THIS(sf::Music)->GetPlayingOffset());
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    nativeGetStatus
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_audio_Music_nativeGetStatus (JNIEnv *env, jobject obj) {
	return (jint)THIS(sf::Music)->GetStatus();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getPitch
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Music_getPitch (JNIEnv *env, jobject obj) {
	return THIS(sf::Music)->GetPitch();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    setPitch
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_setPitch (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Music)->SetPitch(f);
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getVolume
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Music_getVolume (JNIEnv *env, jobject obj) {
	return THIS(sf::Music)->GetVolume();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    setVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_setVolume (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Music)->SetVolume(f);
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector3f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Music_getPosition (JNIEnv *env, jobject obj) {
	return JSFML::Vector3f::FromSFML(env, THIS(sf::Music)->GetPosition());
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    setPosition
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_setPosition
    (JNIEnv *env, jobject obj, jfloat x, jfloat y, jfloat z) {

	THIS(sf::Music)->SetPosition(x, y, z);
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    isRelativeToListener
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_Music_isRelativeToListener (JNIEnv *env, jobject obj) {
	return THIS(sf::Music)->IsRelativeToListener();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    setRelativeToListener
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_setRelativeToListener
    (JNIEnv *env, jobject obj, jboolean b) {

	THIS(sf::Music)->SetRelativeToListener(b);
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getMinDistance
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Music_getMinDistance (JNIEnv *env, jobject obj) {
	return THIS(sf::Music)->GetMinDistance();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    setMinDistance
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_setMinDistance (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Music)->SetMinDistance(f);
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getAttenuation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_Music_getAttenuation (JNIEnv *env, jobject obj) {
	return THIS(sf::Music)->GetAttenuation();
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    setAttenuation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_setAttenuation (JNIEnv *env, jobject obj, jfloat f) {
	THIS(sf::Music)->SetAttenuation(f);
}
