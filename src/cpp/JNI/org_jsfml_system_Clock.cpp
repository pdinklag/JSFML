#include <JSFML/JNI/org_jsfml_system_Clock.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Time.hpp>

#include <SFML/System/Clock.hpp>

/*
 * Class:     org_jsfml_system_Clock
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_system_Clock_nativeCreate (JNIEnv *env , jobject obj) {
    return (jlong)new sf::Clock();
}

/*
 * Class:     org_jsfml_system_Clock
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_system_Clock_nativeDelete (JNIEnv *env , jobject obj) {
    delete THIS(sf::Clock);
}

/*
 * Class:     org_jsfml_system_Clock
 * Method:    getElapsedTime
 * Signature: ()Lorg/jsfml/system/Time;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_system_Clock_getElapsedTime (JNIEnv *env , jobject obj) {
    return JSFML::Time::FromSFML(env, THIS(sf::Clock)->GetElapsedTime());
}

/*
 * Class:     org_jsfml_system_Clock
 * Method:    restart
 * Signature: ()Lorg/jsfml/system/Time;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_system_Clock_restart (JNIEnv *env , jobject obj) {
    return JSFML::Time::FromSFML(env, THIS(sf::Clock)->Restart());
}
