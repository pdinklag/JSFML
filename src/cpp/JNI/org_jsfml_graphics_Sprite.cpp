#include <JSFML/JNI/org_jsfml_graphics_Sprite.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <SFML/Graphics/Sprite.hpp>

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Sprite_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Sprite();
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Sprite);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    nativeSetTexture
 * Signature: (Lorg/jsfml/graphics/Texture;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeSetTexture (JNIEnv *env, jobject obj, jobject texture) {
    THIS(sf::Sprite)->SetTexture(*JSFML::NativeObject::GetPointer<sf::Texture>(env, texture));
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    nativeSetTextureRect
 * Signature: (Lorg/jsfml/graphics/IntRect;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeSetTextureRect (JNIEnv *env, jobject obj, jobject rect) {
    THIS(sf::Sprite)->SetTextureRect(JSFML::IntRect::ToSFML(env, rect));
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    nativeSetColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeSetColor (JNIEnv *env, jobject obj, jobject color) {
    THIS(sf::Sprite)->SetColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getTextureRect
 * Signature: ()Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Sprite_getTextureRect (JNIEnv *env, jobject obj) {
    return JSFML::IntRect::FromSFML(env, THIS(sf::Sprite)->GetTextureRect());
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Sprite_getColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::Sprite)->GetColor());
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getLocalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Sprite_getLocalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::Sprite)->GetLocalBounds());
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getGlobalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Sprite_getGlobalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::Sprite)->GetGlobalBounds());
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    setPosition
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_setPosition (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Sprite)->SetPosition(x, y);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    setRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_setRotation (JNIEnv *env, jobject obj, jfloat angle) {
    THIS(sf::Sprite)->SetRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    setScale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_setScale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Sprite)->SetScale(x, y);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    setOrigin
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_setOrigin (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Sprite)->SetOrigin(x, y);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Sprite_getPosition (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::Sprite)->GetPosition());
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getRotation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_Sprite_getRotation (JNIEnv *env, jobject obj) {
    return THIS(sf::Sprite)->GetRotation();
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getScale
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Sprite_getScale (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::Sprite)->GetScale());
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getOrigin
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Sprite_getOrigin (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::Sprite)->GetOrigin());
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    move
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_move (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Sprite)->Move(x, y);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    rotate
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_rotate (JNIEnv *env, jobject obj, jfloat angle) {
   THIS(sf::Sprite)->Rotate(angle);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    scale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_scale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Sprite)->Scale(x, y);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Sprite_getTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::Sprite)->GetTransform());
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    getInverseTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Sprite_getInverseTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::Sprite)->GetInverseTransform());
}


