#include <JSFML/JNI/org_jsfml_graphics_CircleShape.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <SFML/Graphics/CircleShape.hpp>

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_CircleShape_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::CircleShape();
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setRadius
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setRadius
    (JNIEnv *env, jobject obj, jfloat radius) {

    THIS(sf::CircleShape)->SetRadius(radius);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getRadius
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_CircleShape_getRadius (JNIEnv *env, jobject obj) {
    return THIS(sf::CircleShape)->GetRadius();
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setPointCount
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setPointCount
    (JNIEnv *env, jobject obj, jint count) {

    THIS(sf::CircleShape)->SetPointCount(count);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeSetTexture
 * Signature: (Lorg/jsfml/graphics/Texture;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_nativeSetTexture
    (JNIEnv *env, jobject obj, jobject texture, jboolean resetRect) {

     THIS(sf::CircleShape)->SetTexture(JSFML::NativeObject::GetPointer<sf::Texture>(env, texture), resetRect);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeSetTextureRect
 * Signature: (Lorg/jsfml/graphics/IntRect;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_nativeSetTextureRect
    (JNIEnv *env, jobject obj, jobject rect) {

    THIS(sf::CircleShape)->SetTextureRect(JSFML::IntRect::ToSFML(env, rect));
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeSetFillColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_nativeSetFillColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::CircleShape)->SetFillColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeSetOutlineColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_nativeSetOutlineColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::CircleShape)->SetOutlineColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setOutlineThickness
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setOutlineThickness
    (JNIEnv *env, jobject obj, jfloat thickness) {

    THIS(sf::CircleShape)->SetOutlineThickness(thickness);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getTextureRect
 * Signature: ()Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getTextureRect (JNIEnv *env, jobject obj) {
    return JSFML::IntRect::FromSFML(env, THIS(sf::CircleShape)->GetTextureRect());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getFillColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getFillColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::CircleShape)->GetFillColor());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getOutlineColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getOutlineColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::CircleShape)->GetOutlineColor());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getOutlineThickness
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_CircleShape_getOutlineThickness (JNIEnv *env, jobject obj) {
    return THIS(sf::CircleShape)->GetOutlineThickness();
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getPointCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_CircleShape_getPointCount (JNIEnv *env, jobject obj) {
    return THIS(sf::CircleShape)->GetPointCount();
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeGetPoint
 * Signature: (I)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_nativeGetPoint
    (JNIEnv * env, jobject obj, jint i) {

    return JSFML::Vector2f::FromSFML(env, THIS(sf::CircleShape)->GetPoint(i));
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getLocalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getLocalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::CircleShape)->GetLocalBounds());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getGlobalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getGlobalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::CircleShape)->GetGlobalBounds());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setPosition
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setPosition (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::CircleShape)->SetPosition(x, y);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setRotation (JNIEnv *env, jobject obj, jfloat angle) {
    THIS(sf::CircleShape)->SetRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setScale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setScale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::CircleShape)->SetScale(x, y);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setOrigin
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setOrigin (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::CircleShape)->SetOrigin(x, y);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getPosition (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::CircleShape)->GetPosition());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getRotation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_CircleShape_getRotation (JNIEnv *env, jobject obj) {
    return THIS(sf::CircleShape)->GetRotation();
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getScale
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getScale (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::CircleShape)->GetScale());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getOrigin
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getOrigin (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::CircleShape)->GetOrigin());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    move
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_move (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::CircleShape)->Move(x, y);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    rotate
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_rotate (JNIEnv *env, jobject obj, jfloat angle) {
   THIS(sf::CircleShape)->Rotate(angle);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    scale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_scale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::CircleShape)->Scale(x, y);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::CircleShape)->GetTransform());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getInverseTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getInverseTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::CircleShape)->GetInverseTransform());
}
