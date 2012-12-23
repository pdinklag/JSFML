#include <JSFML/JNI/org_jsfml_window_Keyboard.h>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <SFML/Window/Keyboard.hpp>

/*
 * Class:     org_jsfml_window_Keyboard
 * Method:    nativeIsKeyPressed
 * Signature: (Lorg/jsfml/window/Keyboard$Key;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Keyboard_nativeIsKeyPressed (JNIEnv* env, jclass cls, jobject key) {
    //need to subtract one from the ordinal because UNKNOWN has index 0
    sf::Keyboard::Key sfKey = (sf::Keyboard::Key)(JavaEnum::ordinal(env, key) - 1);
	return sf::Keyboard::isKeyPressed(sfKey);
}
