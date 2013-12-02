#include <JSFML/JNI/org_jsfml_graphics_View.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Intercom.hpp>

#include <SFML/Graphics/View.hpp>

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_View_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::View();
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::View);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeSetCenter
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_nativeSetCenter
    (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    
    THIS(sf::View)->setCenter(x, y);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeSetSize
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_nativeSetSize
    (JNIEnv *env, jobject obj, jfloat width, jfloat height) {
    
    THIS(sf::View)->setSize(width, height);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeSetRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_nativeSetRotation
    (JNIEnv *env, jobject obj, jfloat angle) {
    
    THIS(sf::View)->setRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeSetViewport
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_nativeSetViewport
    (JNIEnv *env, jobject obj, jobject rect) {
    
    THIS(sf::View)->setViewport(JSFML::Intercom::decodeFloatRect(env, rect));
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeReset
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_nativeReset
    (JNIEnv *env, jobject obj, jobject rect) {
    
    THIS(sf::View)->reset(JSFML::Intercom::decodeFloatRect(env, rect));
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeGetCenter
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_View_nativeGetCenter (JNIEnv *env, jobject obj) {
    return JSFML::Intercom::encodeVector2f(THIS(sf::View)->getCenter());
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeGetSize
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_View_nativeGetSize (JNIEnv *env, jobject obj) {
    return JSFML::Intercom::encodeVector2f(THIS(sf::View)->getSize());
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeGetRotation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_View_nativeGetRotation (JNIEnv *env, jobject obj) {
    return THIS(sf::View)->getRotation();
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeGetViewport
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_nativeGetViewport (JNIEnv *env, jobject obj, jobject buffer) {
    return JSFML::Intercom::encodeFloatRect(env, THIS(sf::View)->getViewport(), buffer);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    nativeGetTransform
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_nativeGetTransform
    (JNIEnv *env, jobject obj, jobject buffer) {

    return JSFML::Intercom::encodeTransform(env, THIS(sf::View)->getTransform(), buffer);
}
