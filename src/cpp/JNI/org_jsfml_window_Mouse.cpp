#include <JSFML/JNI/org_jsfml_window_Mouse.h>

#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector2i.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Window/Mouse.hpp>

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    nativeIsButtonPressed
 * Signature: (Lorg/jsfml/window/Mouse$Button;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Mouse_nativeIsButtonPressed (JNIEnv* env, jclass cls, jobject button) {
	return sf::Mouse::isButtonPressed((sf::Mouse::Button)JavaEnum::ordinal(env, button));
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Mouse_getPosition (JNIEnv* env, jclass cls) {
	return JSFML::Vector2i::FromSFML(env, sf::Mouse::getPosition());
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    nativeGetPosition
 * Signature: (Lorg/jsfml/window/Window;)Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Mouse_nativeGetPosition (JNIEnv* env, jclass cls, jobject relativeTo) {
	return JSFML::Vector2i::FromSFML(env,
	    sf::Mouse::getPosition(
	        *JSFML::NativeObject::GetExPointer<sf::Window>(env, relativeTo, org_jsfml_internal_ExPtr_WINDOW)));
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    nativeSetPosition
 * Signature: (Lorg/jsfml/system/Vector2i;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Mouse_nativeSetPosition__Lorg_jsfml_system_Vector2i_2 (JNIEnv* env, jclass cls, jobject position) {
	sf::Mouse::setPosition(JSFML::Vector2i::ToSFML(env, position));
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    nativeSetPosition
 * Signature: (Lorg/jsfml/system/Vector2i;Lorg/jsfml/window/Window;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Mouse_nativeSetPosition__Lorg_jsfml_system_Vector2i_2Lorg_jsfml_window_Window_2 (JNIEnv* env, jclass cls, jobject position, jobject relativeTo) {
	sf::Mouse::setPosition(
	    JSFML::Vector2i::ToSFML(env, position),
	    *JSFML::NativeObject::GetExPointer<sf::Window>(env, relativeTo,org_jsfml_internal_ExPtr_WINDOW));
}
