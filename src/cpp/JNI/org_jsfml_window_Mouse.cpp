#include <JSFML/JNI/org_jsfml_window_Mouse.h>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector2i.hpp>
#include <SFML/Window/Mouse.hpp>

JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Mouse_isButtonPressed(JNIEnv* env, jclass cls, jobject button) {
	return sf::Mouse::IsButtonPressed((sf::Mouse::Button)JavaEnum::ordinal(env, button));
}

JNIEXPORT jobject JNICALL Java_org_jsfml_window_Mouse_getPosition__(JNIEnv* env, jclass cls) {
	return JSFML::Vector2i::FromSFML(env, sf::Mouse::GetPosition());
}

JNIEXPORT jobject JNICALL Java_org_jsfml_window_Mouse_getPosition__Lorg_jsfml_window_Window_2(JNIEnv* env, jclass cls, jobject relativeTo) {
	return JSFML::Vector2i::FromSFML(env, sf::Mouse::GetPosition(*JSFML::NativeObject::GetPointer<sf::Window>(env, relativeTo)));
}

JNIEXPORT void JNICALL Java_org_jsfml_window_Mouse_setPosition__Lorg_jsfml_system_Vector2i_2(JNIEnv* env, jclass cls, jobject position) {
	sf::Mouse::SetPosition(JSFML::Vector2i::ToSFML(env, position));
}

JNIEXPORT void JNICALL Java_org_jsfml_window_Mouse_setPosition__Lorg_jsfml_system_Vector2i_2Lorg_jsfml_window_Window_2(JNIEnv* env, jclass cls, jobject position, jobject relativeTo) {
	sf::Mouse::SetPosition(JSFML::Vector2i::ToSFML(env, position), *JSFML::NativeObject::GetPointer<sf::Window>(env, relativeTo));
}
