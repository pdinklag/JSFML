#include <JSFML/JNI/org_jsfml_window_VideoMode.h>
#include <JSFML/Intercom/VideoMode.hpp>

JNIEXPORT jobject JNICALL Java_org_jsfml_window_VideoMode_getDesktopMode (JNIEnv *env, jclass cls) {
    return JSFML::VideoMode::GetDesktopMode(env);
}

JNIEXPORT jobjectArray JNICALL Java_org_jsfml_window_VideoMode_getFullscreenModes (JNIEnv *env, jclass cls) {
    return JSFML::VideoMode::GetFullscreenModes(env);
}
