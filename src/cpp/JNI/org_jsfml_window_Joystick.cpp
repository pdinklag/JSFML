#include <JSFML/JNI/org_jsfml_window_Joystick.h>
#include <SFML/Window/Joystick.hpp>

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    nativeIsConnected
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Joystick_nativeIsConnected (JNIEnv* env, jclass cls, jint joy) {
	return sf::Joystick::isConnected(joy);
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    nativeGetButtonCount
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_window_Joystick_nativeGetButtonCount (JNIEnv* env, jclass cls, jint joy) {
	return sf::Joystick::getButtonCount(joy);
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    nativeHasAxis
 * Signature: (II)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Joystick_nativeHasAxis (JNIEnv* env, jclass cls, jint joy, jint axis) {
	return sf::Joystick::hasAxis(joy, (sf::Joystick::Axis)axis);
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    nativeIsButtonPressed
 * Signature: (II)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Joystick_nativeIsButtonPressed (JNIEnv* env, jclass cls, jint joy, jint button) {
	return sf::Joystick::isButtonPressed(joy, button);
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    nativeGetAxisPosition
 * Signature: (II)F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_window_Joystick_nativeGetAxisPosition (JNIEnv* env, jclass cls, jint joy, jint axis) {
	return sf::Joystick::getAxisPosition(joy, (sf::Joystick::Axis)axis);
}

/*
 * Class:     org_jsfml_window_Joystick
 * Method:    update
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Joystick_update (JNIEnv* env, jclass cls) {
	return sf::Joystick::update();
}
