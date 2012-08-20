#include <JSFML/JNI/org_jsfml_audio_Music.h>

#include <JSFML/Intercom/JavaString.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Time.hpp>
#include <JSFML/Intercom/Vector3f.hpp>

#include <JSFML/JNI/org_jsfml_ExPtr.h>

#include <SFML/Audio/Music.hpp>

/*
 * Class:     org_jsfml_audio_Music
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_Music_nativeCreate (JNIEnv *env, jobject obj) {
	sf::Music *music = new sf::Music();

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_SOUND_SOURCE,
        dynamic_cast<sf::Music*>(music));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_SOUND_STREAM,
        dynamic_cast<sf::SoundStream*>(music));

	return (jlong)music;
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

    return THIS(sf::Music)->openFromFile(std::string(JavaString::getUTF8(env, fileName)));
}

/*
 * Class:     org_jsfml_audio_Music
 * Method:    getDuration
 * Signature: ()Lorg/jsfml/system/Time
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_audio_Music_getDuration (JNIEnv *env, jobject obj) {
    return JSFML::Time::FromSFML(env, THIS(sf::Music)->getDuration());
}
