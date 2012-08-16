#include <JSFML/JNI/org_jsfml_graphics_RectangleShape.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <JSFML/JNI/org_jsfml_ExPtr.h>

#include <SFML/Graphics/RectangleShape.hpp>
#include <SFML/Graphics/RenderTarget.hpp>

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RectangleShape_nativeCreate (JNIEnv *env, jobject obj) {
    sf::RectangleShape *rectangleShape = new sf::RectangleShape();

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_DRAWABLE,
        dynamic_cast<sf::Drawable*>(rectangleShape));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_TRANSFORMABLE,
        dynamic_cast<sf::Transformable*>(rectangleShape));

    return (jlong)rectangleShape;
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

    THIS(sf::RectangleShape)->setSize(JSFML::Vector2f::ToSFML(env, size));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getSize
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getSize (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::RectangleShape)->getSize());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetTexture
 * Signature: (Lorg/jsfml/graphics/Texture;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetTexture
    (JNIEnv *env, jobject obj, jobject texture, jboolean resetRect) {

     THIS(sf::RectangleShape)->setTexture(
        JSFML::NativeObject::GetPointer<sf::Texture>(env, texture), resetRect);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetTextureRect
 * Signature: (Lorg/jsfml/graphics/IntRect;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetTextureRect
    (JNIEnv *env, jobject obj, jobject rect) {

    THIS(sf::RectangleShape)->setTextureRect(JSFML::IntRect::ToSFML(env, rect));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetFillColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetFillColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::RectangleShape)->setFillColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetOutlineColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetOutlineColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::RectangleShape)->setOutlineColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    setOutlineThickness
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_setOutlineThickness
    (JNIEnv *env, jobject obj, jfloat thickness) {

    THIS(sf::RectangleShape)->setOutlineThickness(thickness);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getTextureRect
 * Signature: ()Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getTextureRect (JNIEnv *env, jobject obj) {
    return JSFML::IntRect::FromSFML(env, THIS(sf::RectangleShape)->getTextureRect());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getFillColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getFillColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::RectangleShape)->getFillColor());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getOutlineColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getOutlineColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::RectangleShape)->getOutlineColor());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getOutlineThickness
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_RectangleShape_getOutlineThickness (JNIEnv *env, jobject obj) {
    return THIS(sf::RectangleShape)->getOutlineThickness();
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getPointCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_RectangleShape_getPointCount (JNIEnv *env, jobject obj) {
    return THIS(sf::RectangleShape)->getPointCount();
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeGetPoint
 * Signature: (I)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_nativeGetPoint
    (JNIEnv * env, jobject obj, jint i) {

    return JSFML::Vector2f::FromSFML(env, THIS(sf::RectangleShape)->getPoint(i));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getLocalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getLocalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::RectangleShape)->getLocalBounds());
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    getGlobalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RectangleShape_getGlobalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::RectangleShape)->getGlobalBounds());
}
