#include <JSFML/JNI/org_jsfml_graphics_CircleShape.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <JSFML/JNI/org_jsfml_ExPtr.h>

#include <SFML/Graphics/CircleShape.hpp>
#include <SFML/Graphics/RenderTarget.hpp>

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_CircleShape_nativeCreate (JNIEnv *env, jobject obj) {
    sf::CircleShape *circleShape = new sf::CircleShape();

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_DRAWABLE,
        dynamic_cast<sf::Drawable*>(circleShape));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_TRANSFORMABLE,
        dynamic_cast<sf::Transformable*>(circleShape));

    return (jlong)circleShape;
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::CircleShape);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setRadius
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setRadius
    (JNIEnv *env, jobject obj, jfloat radius) {

    THIS(sf::CircleShape)->setRadius(radius);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getRadius
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_CircleShape_getRadius (JNIEnv *env, jobject obj) {
    return THIS(sf::CircleShape)->getRadius();
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setPointCount
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setPointCount
    (JNIEnv *env, jobject obj, jint count) {

    THIS(sf::CircleShape)->setPointCount(count);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeSetTexture
 * Signature: (Lorg/jsfml/graphics/Texture;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_nativeSetTexture
    (JNIEnv *env, jobject obj, jobject texture, jboolean resetRect) {

     THIS(sf::CircleShape)->setTexture(JSFML::NativeObject::GetPointer<sf::Texture>(env, texture), resetRect);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeSetTextureRect
 * Signature: (Lorg/jsfml/graphics/IntRect;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_nativeSetTextureRect
    (JNIEnv *env, jobject obj, jobject rect) {

    THIS(sf::CircleShape)->setTextureRect(JSFML::IntRect::ToSFML(env, rect));
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeSetFillColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_nativeSetFillColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::CircleShape)->setFillColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeSetOutlineColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_nativeSetOutlineColor
    (JNIEnv *env, jobject obj, jobject color) {

    THIS(sf::CircleShape)->setOutlineColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    setOutlineThickness
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_CircleShape_setOutlineThickness
    (JNIEnv *env, jobject obj, jfloat thickness) {

    THIS(sf::CircleShape)->setOutlineThickness(thickness);
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getTextureRect
 * Signature: ()Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getTextureRect (JNIEnv *env, jobject obj) {
    return JSFML::IntRect::FromSFML(env, THIS(sf::CircleShape)->getTextureRect());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getFillColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getFillColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::CircleShape)->getFillColor());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getOutlineColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getOutlineColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::CircleShape)->getOutlineColor());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getOutlineThickness
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_CircleShape_getOutlineThickness (JNIEnv *env, jobject obj) {
    return THIS(sf::CircleShape)->getOutlineThickness();
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getPointCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_CircleShape_getPointCount (JNIEnv *env, jobject obj) {
    return THIS(sf::CircleShape)->getPointCount();
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    nativeGetPoint
 * Signature: (I)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_nativeGetPoint
    (JNIEnv * env, jobject obj, jint i) {

    return JSFML::Vector2f::FromSFML(env, THIS(sf::CircleShape)->getPoint(i));
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getLocalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getLocalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::CircleShape)->getLocalBounds());
}

/*
 * Class:     org_jsfml_graphics_CircleShape
 * Method:    getGlobalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_CircleShape_getGlobalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::CircleShape)->getGlobalBounds());
}
