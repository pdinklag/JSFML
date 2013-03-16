#include <JSFML/JNI/org_jsfml_window_Mouse.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Window/Mouse.hpp>

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    nativeIsButtonPressed
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Mouse_nativeIsButtonPressed (JNIEnv* env, jclass cls, jint button) {
	return sf::Mouse::isButtonPressed((sf::Mouse::Button)button);
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    nativeGetPosition
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_window_Mouse_nativeGetPosition__ (JNIEnv* env, jclass cls) {
	return JSFML::Intercom::encodeVector2i(sf::Mouse::getPosition());
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    nativeGetPosition
 * Signature: (Lorg/jsfml/window/Window;)J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_window_Mouse_nativeGetPosition__Lorg_jsfml_window_Window_2
    (JNIEnv* env, jclass cls, jobject relativeTo) {
    
	return JSFML::Intercom::encodeVector2i(sf::Mouse::getPosition(
        *JSFML::NativeObject::GetExPointer<sf::Window>(env, relativeTo, org_jsfml_internal_ExPtr_WINDOW)));
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    nativeSetPosition
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Mouse_nativeSetPosition__J
    (JNIEnv* env, jclass cls, jlong position) {
    
	sf::Mouse::setPosition(JSFML::Intercom::decodeVector2i(position));
}

/*
 * Class:     org_jsfml_window_Mouse
 * Method:    nativeSetPosition
 * Signature: (JLorg/jsfml/window/Window;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Mouse_nativeSetPosition__JLorg_jsfml_window_Window_2
    (JNIEnv* env, jclass cls, jlong position, jobject relativeTo) {
    
	sf::Mouse::setPosition(
	    JSFML::Intercom::decodeVector2i(position),
	    *JSFML::NativeObject::GetExPointer<sf::Window>(env, relativeTo,org_jsfml_internal_ExPtr_WINDOW));
}
