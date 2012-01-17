#include <JSFML/JNI/org_jsfml_graphics_RectangleShape.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <SFML/Graphics/RectangleShape.hpp>

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RectangleShape_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::RectangleShape();
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::RectangleShape);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetSize
 * Signature: (Lorg/jsfml/system/Vector2f;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetSize
    (JNIEnv *env, jobject obj, jobject size) {

    THIS(sf::RectangleShape)->SetSize(JSFML::Vector2f::ToSFML(env, size));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getSize
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getSize (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::RectangleShape)->GetSize());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetTexture
 * Signature: (Lorg/jsfml/graphics/Texture;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetTexture
    (JNIEnv *env, jobject obj, jobject texture, jboolean resetRect) {

     THIS(sf::RectangleShape)->SetTexture(JSFML::NativeObject::GetPointer<sf::Texture>(env, texture), resetRect);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetTextureRect
 * Signature: (Lorg/jsfml/graphics/IntRect;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetTextureRect
    (JNIEnv *env, jobject obj, jobject rect) {

    THIS(sf::RectangleShape)->SetTextureRect(JSFML::IntRect::ToSFML(env, rect));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetFillColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetFillColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::RectangleShape)->SetFillColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetOutlineColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetOutlineColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::RectangleShape)->SetOutlineColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    setOutlineThickness
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_setOutlineThickness
    (JNIEnv *env, jobject obj, jfloat thickness) {

    THIS(sf::RectangleShape)->SetOutlineThickness(thickness);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getTextureRect
 * Signature: ()Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getTextureRect (JNIEnv *env, jobject obj) {
    return JSFML::IntRect::FromSFML(env, THIS(sf::RectangleShape)->GetTextureRect());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getFillColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getFillColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::RectangleShape)->GetFillColor());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getOutlineColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getOutlineColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::RectangleShape)->GetOutlineColor());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getOutlineThickness
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_RectangleShape_getOutlineThickness (JNIEnv *env, jobject obj) {
    return THIS(sf::RectangleShape)->GetOutlineThickness();
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getPointCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_RectangleShape_getPointCount (JNIEnv *env, jobject obj) {
    return THIS(sf::RectangleShape)->GetPointCount();
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeGetPoint
 * Signature: (I)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_nativeGetPoint
    (JNIEnv * env, jobject obj, jint i) {

    return JSFML::Vector2f::FromSFML(env, THIS(sf::RectangleShape)->GetPoint(i));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getLocalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getLocalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::RectangleShape)->GetLocalBounds());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getGlobalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getGlobalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::RectangleShape)->GetGlobalBounds());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    setPosition
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_setPosition (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::RectangleShape)->SetPosition(x, y);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    setRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_setRotation (JNIEnv *env, jobject obj, jfloat angle) {
    THIS(sf::RectangleShape)->SetRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    setScale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_setScale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::RectangleShape)->SetScale(x, y);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    setOrigin
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_setOrigin (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::RectangleShape)->SetOrigin(x, y);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getPosition (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::RectangleShape)->GetPosition());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getRotation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_RectangleShape_getRotation (JNIEnv *env, jobject obj) {
    return THIS(sf::RectangleShape)->GetRotation();
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getScale
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getScale (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::RectangleShape)->GetScale());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getOrigin
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getOrigin (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::RectangleShape)->GetOrigin());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    move
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_move (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::RectangleShape)->Move(x, y);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    rotate
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_rotate (JNIEnv *env, jobject obj, jfloat angle) {
   THIS(sf::RectangleShape)->Rotate(angle);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    scale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_scale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::RectangleShape)->Scale(x, y);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::RectangleShape)->GetTransform());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getInverseTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getInverseTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::RectangleShape)->GetInverseTransform());
}
