#include <JSFML/JNI/org_jsfml_window_Mouse.h>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector2i.hpp>
#include <SFML/Window/Mouse.hpp>

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    isButtonPressed
 * Signature: (Lorg/jsfml/window/Mouse$Button;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Mouse_isButtonPressed (JNIEnv* env, jclass cls, jobject button) {
	return sf::Mouse::isButtonPressed((sf::Mouse::Button)JavaEnum::ordinal(env, button));
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Mouse_getPosition__ (JNIEnv* env, jclass cls) {
	return JSFML::Vector2i::FromSFML(env, sf::Mouse::getPosition());
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    getPosition
 * Signature: (Lorg/jsfml/window/Window;)Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Mouse_getPosition__Lorg_jsfml_window_Window_2 (JNIEnv* env, jclass cls, jobject relativeTo) {
	return JSFML::Vector2i::FromSFML(env,
	    sf::Mouse::getPosition(*JSFML::NativeObject::GetPointer<sf::Window>(env, relativeTo)));
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    setPosition
 * Signature: (Lorg/jsfml/system/Vector2i;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Mouse_setPosition__Lorg_jsfml_system_Vector2i_2 (JNIEnv* env, jclass cls, jobject position) {
	sf::Mouse::setPosition(JSFML::Vector2i::ToSFML(env, position));
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    setPosition
 * Signature: (Lorg/jsfml/system/Vector2i;Lorg/jsfml/window/Window;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Mouse_setPosition__Lorg_jsfml_system_Vector2i_2Lorg_jsfml_window_Window_2 (JNIEnv* env, jclass cls, jobject position, jobject relativeTo) {
	sf::Mouse::setPosition(
	    JSFML::Vector2i::ToSFML(env, position),
	    *JSFML::NativeObject::GetPointer<sf::Window>(env, relativeTo));
}
