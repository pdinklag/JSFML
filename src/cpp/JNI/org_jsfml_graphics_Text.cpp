#include <JSFML/JNI/org_jsfml_graphics_Text.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/JavaString.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <SFML/Graphics/Text.hpp>

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Text_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Text();
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Text);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetString
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetString
    (JNIEnv *env, jobject obj, jstring str) {

    THIS(sf::Text)->SetString(sf::String(JavaString::getUnicode(env, str)));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetFont
 * Signature: (Lorg/jsfml/graphics/Font;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetFont
    (JNIEnv *env, jobject obj, jobject font) {

    THIS(sf::Text)->SetFont(*JSFML::NativeObject::GetPointer<sf::Font>(env, font));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    setCharacterSize
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_setCharacterSize
    (JNIEnv *env, jobject obj, jint size) {

    THIS(sf::Text)->SetCharacterSize(size);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    setStyle
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_setStyle
    (JNIEnv *env, jobject obj, jint style) {

    THIS(sf::Text)->SetStyle(style);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetColor (JNIEnv *env, jobject obj, jobject color) {
    THIS(sf::Text)->SetColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getCharacterSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Text_getCharacterSize (JNIEnv *env, jobject obj) {
    return THIS(sf::Text)->GetCharacterSize();
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getStyle
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Text_getStyle (JNIEnv *env, jobject obj) {
    return THIS(sf::Text)->GetStyle();
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::Text)->GetColor());
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getLocalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getLocalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::Text)->GetLocalBounds());
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getGlobalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getGlobalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::Text)->GetGlobalBounds());
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    setPosition
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_setPosition (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Text)->SetPosition(x, y);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    setRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_setRotation (JNIEnv *env, jobject obj, jfloat angle) {
    THIS(sf::Text)->SetRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    setScale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_setScale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Text)->SetScale(x, y);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    setOrigin
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_setOrigin (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Text)->SetOrigin(x, y);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getPosition (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::Text)->GetPosition());
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getRotation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_Text_getRotation (JNIEnv *env, jobject obj) {
    return THIS(sf::Text)->GetRotation();
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getScale
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getScale (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::Text)->GetScale());
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getOrigin
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getOrigin (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::Text)->GetOrigin());
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    move
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_move (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Text)->Move(x, y);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    rotate
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_rotate (JNIEnv *env, jobject obj, jfloat angle) {
   THIS(sf::Text)->Rotate(angle);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    scale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_scale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Text)->Scale(x, y);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::Text)->GetTransform());
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getInverseTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getInverseTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::Text)->GetInverseTransform());
}
