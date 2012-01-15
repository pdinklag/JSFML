#include <JSFML/JNI/org_jsfml_graphics_View.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

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
 * Method:    setCenter
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_setCenter (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::View)->SetCenter(x, y);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    setSize
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_setSize (JNIEnv *env, jobject obj, jfloat width, jfloat height) {
    THIS(sf::View)->SetSize(width, height);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    setRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_setRotation (JNIEnv *env, jobject obj, jfloat angle) {
    THIS(sf::View)->SetRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    setViewport
 * Signature: (Lorg/jsfml/graphics/FloatRect;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_setViewport (JNIEnv *env, jobject obj, jobject rect) {
    THIS(sf::View)->SetViewport(JSFML::FloatRect::ToSFML(env, rect));
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    reset
 * Signature: (Lorg/jsfml/graphics/FloatRect;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_reset (JNIEnv *env, jobject obj, jobject rect) {
    THIS(sf::View)->Reset(JSFML::FloatRect::ToSFML(env, rect));
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    getCenter
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_View_getCenter (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::View)->GetCenter());
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    getSize
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_View_getSize (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::View)->GetSize());
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    getRotation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_View_getRotation (JNIEnv *env, jobject obj) {
    return THIS(sf::View)->GetRotation();
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    getViewport
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_View_getViewport (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::View)->GetViewport());
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    rotate
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_rotate (JNIEnv *env, jobject obj, jfloat angle) {
    THIS(sf::View)->Rotate(angle);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    move
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_move (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::View)->Move(x, y);
}

/*
 * Class:     org_jsfml_graphics_View
 * Method:    zoom
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_View_zoom (JNIEnv *env, jobject obj, jfloat factor) {
    THIS(sf::View)->Zoom(factor);
}
