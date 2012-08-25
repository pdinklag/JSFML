#include <JSFML/JNI/org_jsfml_window_Context.h>
#include <JSFML/Intercom/NativeObject.hpp>
#include <SFML/Window/Context.hpp>

/*
 * Class:     org_jsfml_window_Context
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_window_Context_nativeCreate
  (JNIEnv *env, jobject obj) {

    return (jlong)new sf::Context();
}

/*
 * Class:     org_jsfml_window_Context
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Context_nativeDelete
  (JNIEnv *env, jobject obj) {

    delete THIS(sf::Context);
}

/*
 * Class:     org_jsfml_window_Context
 * Method:    nativeSetActive
 * Signature: (Z)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Context_nativeSetActive
  (JNIEnv *env, jobject obj, jboolean active) {

    return THIS(sf::Context)->setActive(active);
}
