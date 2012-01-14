#include <JSFML/JNI/org_jsfml_window_Joystick.h>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <SFML/Window/Joystick.hpp>

JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Joystick_isConnected(JNIEnv* env, jclass cls, jint joy) {
	return sf::Joystick::IsConnected(joy);
}

JNIEXPORT jint JNICALL Java_org_jsfml_window_Joystick_getButtonCount(JNIEnv* env, jclass cls, jint joy) {
	return sf::Joystick::GetButtonCount(joy);
}

JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Joystick_hasAxis(JNIEnv* env, jclass cls, jint joy, jobject axis) {
	return sf::Joystick::HasAxis(joy, (sf::Joystick::Axis)JavaEnum::ordinal(env, axis));
}

JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Joystick_isButtonPressed(JNIEnv* env, jclass cls, jint joy, jint button) {
	return sf::Joystick::IsButtonPressed(joy, (sf::Joystick::Axis)button);
}

JNIEXPORT jfloat JNICALL Java_org_jsfml_window_Joystick_getAxisPosition(JNIEnv* env, jclass cls, jint joy, jobject axis) {
	return sf::Joystick::GetAxisPosition(joy, (sf::Joystick::Axis)JavaEnum::ordinal(env, axis));
}

JNIEXPORT void JNICALL Java_org_jsfml_window_Joystick_update(JNIEnv* env, jclass cls) {
	return sf::Joystick::Update();
}
