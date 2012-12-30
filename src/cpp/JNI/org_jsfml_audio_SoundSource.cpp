#include <JSFML/JNI/org_jsfml_audio_SoundSource.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector3f.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Audio/SoundSource.hpp>

#define SOUND_SOURCE JSFML::NativeObject::GetExPointer<sf::SoundSource>(env, obj, org_jsfml_internal_ExPtr_SOUND_SOURCE)

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getPitch
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_SoundSource_getPitch (JNIEnv *env, jobject obj) {
	return SOUND_SOURCE->getPitch();
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setPitch
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setPitch (JNIEnv *env, jobject obj, jfloat f) {
	SOUND_SOURCE->setPitch(f);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getVolume
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_SoundSource_getVolume (JNIEnv *env, jobject obj) {
	return SOUND_SOURCE->getVolume();
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setVolume (JNIEnv *env, jobject obj, jfloat f) {
	SOUND_SOURCE->setVolume(f);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector3f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_SoundSource_getPosition (JNIEnv *env, jobject obj) {
	return JSFML::Vector3f::FromSFML(env, SOUND_SOURCE->getPosition());
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setPosition
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setPosition
    (JNIEnv *env, jobject obj, jfloat x, jfloat y, jfloat z) {

	SOUND_SOURCE->setPosition(x, y, z);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    isRelativeToListener
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundSource_isRelativeToListener (JNIEnv *env, jobject obj) {
	return SOUND_SOURCE->isRelativeToListener();
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setRelativeToListener
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setRelativeToListener
    (JNIEnv *env, jobject obj, jboolean b) {

	SOUND_SOURCE->setRelativeToListener(b);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getMinDistance
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_SoundSource_getMinDistance (JNIEnv *env, jobject obj) {
	return SOUND_SOURCE->getMinDistance();
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setMinDistance
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setMinDistance (JNIEnv *env, jobject obj, jfloat f) {
	SOUND_SOURCE->setMinDistance(f);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getAttenuation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_SoundSource_getAttenuation (JNIEnv *env, jobject obj) {
	return SOUND_SOURCE->getAttenuation();
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setAttenuation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setAttenuation (JNIEnv *env, jobject obj, jfloat f) {
	SOUND_SOURCE->setAttenuation(f);
}
