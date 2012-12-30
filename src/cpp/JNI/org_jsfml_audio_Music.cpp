#include <JSFML/JNI/org_jsfml_audio_Music.h>

#include <JSFML/Intercom/InputStream.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/NativeRef.hpp>
#include <JSFML/Intercom/Time.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

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
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_Music_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_SOUND_SOURCE,
        dynamic_cast<sf::SoundSource*>(THIS(sf::Music)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_SOUND_STREAM,
        dynamic_cast<sf::SoundStream*>(THIS(sf::Music)));
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
 * Method:    nativeOpenFromStream
 * Signature: (Lorg/jsfml/SFMLInputStream$NativeStreamRef;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_Music_nativeOpenFromStream
  (JNIEnv *env, jobject obj, jobject stream) {

	return THIS(sf::Music)->openFromStream(
		*JSFML::NativeRef::GetPointer<JSFML::InputStream>(env, stream));
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getDuration
 * Signature: ()Lorg/jsfml/system/Time
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Music_getDuration (JNIEnv *env, jobject obj) {
    return JSFML::Time::FromSFML(env, THIS(sf::Music)->getDuration());
}
