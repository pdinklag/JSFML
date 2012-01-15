#include <JSFML/JNI/org_jsfml_window_Joystick.h>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <SFML/Window/Joystick.hpp>

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    isConnected
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Joystick_isConnected (JNIEnv* env, jclass cls, jint joy) {
	return sf::Joystick::IsConnected(joy);
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    getButtonCount
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_window_Joystick_getButtonCount (JNIEnv* env, jclass cls, jint joy) {
	return sf::Joystick::GetButtonCount(joy);
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    hasAxis
 * Signature: (ILorg/jsfml/window/Joystick$Axis;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Joystick_hasAxis (JNIEnv* env, jclass cls, jint joy, jobject axis) {
	return sf::Joystick::HasAxis(joy, (sf::Joystick::Axis)JavaEnum::ordinal(env, axis));
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    isButtonPressed
 * Signature: (II)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Joystick_isButtonPressed (JNIEnv* env, jclass cls, jint joy, jint button) {
	return sf::Joystick::IsButtonPressed(joy, (sf::Joystick::Axis)button);
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    getAxisPosition
 * Signature: (ILorg/jsfml/window/Joystick$Axis;)F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_window_Joystick_getAxisPosition (JNIEnv* env, jclass cls, jint joy, jobject axis) {
	return sf::Joystick::GetAxisPosition(joy, (sf::Joystick::Axis)JavaEnum::ordinal(env, axis));
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    update
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Joystick_update (JNIEnv* env, jclass cls) {
	return sf::Joystick::Update();
}
