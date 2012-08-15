#include <JSFML/JNI/org_jsfml_graphics_ConvexShape.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <JSFML/JNI/org_jsfml_graphics_RenderTarget.h>

#include <SFML/Graphics/ConvexShape.hpp>
#include <SFML/Graphics/RenderTarget.hpp>

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_ConvexShape_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::ConvexShape();
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::ConvexShape);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeSetPoint
 * Signature: (ILorg/jsfml/system/Vector2f;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeSetPoint
    (JNIEnv *env, jobject obj, jint i, jobject v) {

    THIS(sf::ConvexShape)->setPoint(i, JSFML::Vector2f::ToSFML(env, v));
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    setPointCount
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_setPointCount
    (JNIEnv *env, jobject obj, jint count) {

    THIS(sf::ConvexShape)->setPointCount(count);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeSetTexture
 * Signature: (Lorg/jsfml/graphics/Texture;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeSetTexture
    (JNIEnv *env, jobject obj, jobject texture, jboolean resetRect) {

     THIS(sf::ConvexShape)->setTexture(JSFML::NativeObject::GetPointer<sf::Texture>(env, texture), resetRect);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeSetTextureRect
 * Signature: (Lorg/jsfml/graphics/IntRect;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeSetTextureRect
    (JNIEnv *env, jobject obj, jobject rect) {

    THIS(sf::ConvexShape)->setTextureRect(JSFML::IntRect::ToSFML(env, rect));
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeSetFillColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeSetFillColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::ConvexShape)->setFillColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeSetOutlineColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeSetOutlineColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::ConvexShape)->setOutlineColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    setOutlineThickness
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_setOutlineThickness
    (JNIEnv *env, jobject obj, jfloat thickness) {

    THIS(sf::ConvexShape)->setOutlineThickness(thickness);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getTextureRect
 * Signature: ()Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getTextureRect (JNIEnv *env, jobject obj) {
    return JSFML::IntRect::FromSFML(env, THIS(sf::ConvexShape)->getTextureRect());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getFillColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getFillColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::ConvexShape)->getFillColor());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getOutlineColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getOutlineColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::ConvexShape)->getOutlineColor());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getOutlineThickness
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_ConvexShape_getOutlineThickness (JNIEnv *env, jobject obj) {
    return THIS(sf::ConvexShape)->getOutlineThickness();
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getPointCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_ConvexShape_getPointCount (JNIEnv *env, jobject obj) {
    return THIS(sf::ConvexShape)->getPointCount();
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeGetPoint
 * Signature: (I)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_nativeGetPoint
    (JNIEnv * env, jobject obj, jint i) {

    return JSFML::Vector2f::FromSFML(env, THIS(sf::ConvexShape)->getPoint(i));
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getLocalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getLocalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::ConvexShape)->getLocalBounds());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getGlobalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getGlobalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::ConvexShape)->getGlobalBounds());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    setPosition
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_setPosition (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::ConvexShape)->setPosition(x, y);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    setRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_setRotation (JNIEnv *env, jobject obj, jfloat angle) {
    THIS(sf::ConvexShape)->setRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    setScale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_setScale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::ConvexShape)->setScale(x, y);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    setOrigin
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_setOrigin (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::ConvexShape)->setOrigin(x, y);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getPosition (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::ConvexShape)->getPosition());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getRotation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_ConvexShape_getRotation (JNIEnv *env, jobject obj) {
    return THIS(sf::ConvexShape)->getRotation();
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getScale
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getScale (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::ConvexShape)->getScale());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getOrigin
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getOrigin (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::ConvexShape)->getOrigin());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    move
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_move (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::ConvexShape)->move(x, y);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    rotate
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_rotate (JNIEnv *env, jobject obj, jfloat angle) {
   THIS(sf::ConvexShape)->rotate(angle);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    scale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_scale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::ConvexShape)->scale(x, y);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::ConvexShape)->getTransform());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    getInverseTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_ConvexShape_getInverseTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::ConvexShape)->getInverseTransform());
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeDraw
 * Signature: (Lorg/jsfml/graphics/RenderTarget;Lorg/jsfml/graphics/RenderStates;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeDraw
    (JNIEnv *env, jobject obj, jobject target, jobject states) {

    sf::RenderTarget *sfTarget = JSFML::NativeObject::GetExPointer<sf::RenderTarget>(
        env, target, org_jsfml_graphics_RenderTarget_EXPTR_RENDER_TARGET);

    sfTarget->draw(*THIS(sf::ConvexShape), JSFML::RenderStates::ToSFML(env, states));
}
