#include <JSFML/JNI/org_jsfml_audio_SoundSource.h>

#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Audio/SoundSource.hpp>

#define SOUND_SOURCE JSFML::NativeObject::GetExPointer<sf::SoundSource>(env, obj, org_jsfml_internal_ExPtr_SOUND_SOURCE)

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    nativeGetData
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_nativeGetData
    (JNIEnv *env, jobject obj, jobject buffer) {

    sf::SoundSource *src = SOUND_SOURCE;
    void *data = env->GetDirectBufferAddress(buffer);
    
    ((jbyte*)data)[0] = src->isRelativeToListener() ? 1 : 0;
    ((jfloat*)data)[1] = src->getVolume();
    ((jfloat*)data)[2] = src->getPitch();
    
    sf::Vector3f pos = src->getPosition();
    ((jfloat*)data)[3] = pos.x;
    ((jfloat*)data)[4] = pos.y;
    ((jfloat*)data)[5] = pos.z;
    ((jfloat*)data)[6] = src->getMinDistance();
    ((jfloat*)data)[7] = src->getAttenuation();
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    nativeSetPitch
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_nativeSetPitch (JNIEnv *env, jobject obj, jfloat f) {
	SOUND_SOURCE->setPitch(f);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    nativeSetVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_nativeSetVolume (JNIEnv *env, jobject obj, jfloat f) {
	SOUND_SOURCE->setVolume(f);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    nativeSetPosition
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_nativeSetPosition
    (JNIEnv *env, jobject obj, jfloat x, jfloat y, jfloat z) {

	SOUND_SOURCE->setPosition(x, y, z);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    nativeSetRelativeToListener
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_nativeSetRelativeToListener
    (JNIEnv *env, jobject obj, jboolean b) {

	SOUND_SOURCE->setRelativeToListener(b);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    nativeSetMinDistance
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_nativeSetMinDistance (JNIEnv *env, jobject obj, jfloat f) {
	SOUND_SOURCE->setMinDistance(f);
}

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    nativeSetAttenuation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_nativeSetAttenuation (JNIEnv *env, jobject obj, jfloat f) {
	SOUND_SOURCE->setAttenuation(f);
}
