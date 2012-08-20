/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_jsfml_audio_SoundSource */

#ifndef _Included_org_jsfml_audio_SoundSource
#define _Included_org_jsfml_audio_SoundSource
#ifdef __cplusplus
extern "C" {
#endif
/* Inaccessible static: debug */
/* Inaccessible static: numManaged */
/* Inaccessible static: numWrapped */
/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setPitch
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setPitch
  (JNIEnv *, jobject, jfloat);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setVolume
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setVolume
  (JNIEnv *, jobject, jfloat);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setPosition
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setPosition
  (JNIEnv *, jobject, jfloat, jfloat, jfloat);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setRelativeToListener
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setRelativeToListener
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setMinDistance
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setMinDistance
  (JNIEnv *, jobject, jfloat);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    setAttenuation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundSource_setAttenuation
  (JNIEnv *, jobject, jfloat);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getPitch
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_SoundSource_getPitch
  (JNIEnv *, jobject);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getVolume
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_SoundSource_getVolume
  (JNIEnv *, jobject);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector3f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_SoundSource_getPosition
  (JNIEnv *, jobject);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    isRelativeToListener
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundSource_isRelativeToListener
  (JNIEnv *, jobject);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getMinDistance
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_SoundSource_getMinDistance
  (JNIEnv *, jobject);

/*
 * Class:     org_jsfml_audio_SoundSource
 * Method:    getAttenuation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_audio_SoundSource_getAttenuation
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
