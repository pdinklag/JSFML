#include <JSFML/JNI/org_jsfml_window_VideoMode.h>
#include <JSFML/Intercom/VideoMode.hpp>

/*
 * Class:     org_jsfml_window_VideoMode
 * Method:    getDesktopMode
 * Signature: ()Lorg/jsfml/window/VideoMode;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_VideoMode_getDesktopMode (JNIEnv *env, jclass cls) {
    return JSFML::VideoMode::GetDesktopMode(env);
}

/*
 * Class:     org_jsfml_window_VideoMode
 * Method:    getFullscreenModes
 * Signature: ()[Lorg/jsfml/window/VideoMode;
 */
JNIEXPORT jobjectArray JNICALL Java_org_jsfml_window_VideoMode_getFullscreenModes (JNIEnv *env, jclass cls) {
    return JSFML::VideoMode::GetFullscreenModes(env);
}
