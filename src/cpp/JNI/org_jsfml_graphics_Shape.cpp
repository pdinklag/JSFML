#include <JSFML/JNI/org_jsfml_graphics_Shape.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/Shape.hpp>

#define SHAPE JSFML::NativeObject::GetExPointer<sf::Shape>(env, obj, org_jsfml_internal_ExPtr_SHAPE)

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeSetTexture
 * Signature: (Lorg/jsfml/graphics/Texture;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeSetTexture
    (JNIEnv *env, jobject obj, jobject texture, jboolean resetRect) {

     SHAPE->setTexture(JSFML::NativeObject::GetPointer<sf::Texture>(env, texture), resetRect);
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeSetTextureRect
 * Signature: (Lorg/jsfml/graphics/IntRect;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeSetTextureRect
    (JNIEnv *env, jobject obj, jobject rect) {

    SHAPE->setTextureRect(JSFML::IntRect::ToSFML(env, rect));
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeSetFillColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeSetFillColor
    (JNIEnv *env, jobject obj, jobject color) {

    SHAPE->setFillColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeSetOutlineColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeSetOutlineColor
    (JNIEnv *env, jobject obj, jobject color) {

    SHAPE->setOutlineColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    setOutlineThickness
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_setOutlineThickness
    (JNIEnv *env, jobject obj, jfloat thickness) {

    SHAPE->setOutlineThickness(thickness);
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    getTextureRect
 * Signature: ()Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Shape_getTextureRect (JNIEnv *env, jobject obj) {
    return JSFML::IntRect::FromSFML(env, SHAPE->getTextureRect());
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    getFillColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Shape_getFillColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, SHAPE->getFillColor());
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    getOutlineColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Shape_getOutlineColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, SHAPE->getOutlineColor());
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    getOutlineThickness
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_Shape_getOutlineThickness (JNIEnv *env, jobject obj) {
    return SHAPE->getOutlineThickness();
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    getPointCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Shape_getPointCount (JNIEnv *env, jobject obj) {
    return SHAPE->getPointCount();
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeGetPoint
 * Signature: (I)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Shape_nativeGetPoint
    (JNIEnv * env, jobject obj, jint i) {

    return JSFML::Vector2f::FromSFML(env, SHAPE->getPoint(i));
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    getLocalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Shape_getLocalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, SHAPE->getLocalBounds());
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    getGlobalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Shape_getGlobalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, SHAPE->getGlobalBounds());
}
