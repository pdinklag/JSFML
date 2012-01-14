#include <JSFML/JNI/org_jsfml_window_Keyboard.h>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <SFML/Window/Keyboard.hpp>

JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Keyboard_isKeyPressed(JNIEnv* env, jclass cls, jobject key) {
	return sf::Keyboard::IsKeyPressed((sf::Keyboard::Key)JavaEnum::ordinal(env, key));
}
