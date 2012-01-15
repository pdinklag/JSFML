#include <JSFML/JNI/org_jsfml_graphics_Transformable.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <SFML/Graphics/Transformable.hpp>

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    setPosition
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Transformable_setPosition (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Transformable)->SetPosition(x, y);
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    setRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Transformable_setRotation (JNIEnv *env, jobject obj, jfloat angle) {
    THIS(sf::Transformable)->SetRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    setScale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Transformable_setScale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Transformable)->SetScale(x, y);
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    setOrigin
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Transformable_setOrigin (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Transformable)->SetOrigin(x, y);
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Transformable_getPosition (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::Transformable)->GetPosition());
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    getRotation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_Transformable_getRotation (JNIEnv *env, jobject obj) {
    return THIS(sf::Transformable)->GetRotation();
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    getScale
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Transformable_getScale (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::Transformable)->GetScale());
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    getOrigin
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Transformable_getOrigin (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, THIS(sf::Transformable)->GetOrigin());
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    move
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Transformable_move (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Transformable)->Move(x, y);
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    rotate
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Transformable_rotate (JNIEnv *env, jobject obj, jfloat angle) {
   THIS(sf::Transformable)->Rotate(angle);
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    scale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Transformable_scale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    THIS(sf::Transformable)->Scale(x, y);
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    getTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Transformable_getTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::Transformable)->GetTransform());
}

/*
 * Class:     org_jsfml_graphics_Transformable
 * Method:    getInverseTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Transformable_getInverseTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, THIS(sf::Transformable)->GetInverseTransform());
}
