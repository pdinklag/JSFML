#include <JSFML/JNI/org_jsfml_graphics_SFMLNativeTransformable.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <JSFML/JNI/org_jsfml_ExPtr.h>

#include <SFML/Graphics/Transformable.hpp>

#define TRANSFORMABLE JSFML::NativeObject::GetExPointer<sf::Transformable>(env, obj, org_jsfml_ExPtr_TRANSFORMABLE)

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    setPosition
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_setPosition (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    TRANSFORMABLE->setPosition(x, y);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    setRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_setRotation (JNIEnv *env, jobject obj, jfloat angle) {
    TRANSFORMABLE->setRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    setScale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_setScale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    TRANSFORMABLE->setScale(x, y);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    setOrigin
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_setOrigin (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    TRANSFORMABLE->setOrigin(x, y);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_getPosition (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, TRANSFORMABLE->getPosition());
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    getRotation
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_getRotation (JNIEnv *env, jobject obj) {
    return TRANSFORMABLE->getRotation();
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    getScale
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_getScale (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, TRANSFORMABLE->getScale());
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    getOrigin
 * Signature: ()Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_getOrigin (JNIEnv *env, jobject obj) {
    return JSFML::Vector2f::FromSFML(env, TRANSFORMABLE->getOrigin());
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    move
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_move (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    TRANSFORMABLE->move(x, y);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    rotate
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_rotate (JNIEnv *env, jobject obj, jfloat angle) {
   TRANSFORMABLE->rotate(angle);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    scale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_scale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    TRANSFORMABLE->scale(x, y);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    getTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_getTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, TRANSFORMABLE->getTransform());
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    getInverseTransform
 * Signature: ()Lorg/jsfml/graphics/Transform;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_getInverseTransform (JNIEnv *env, jobject obj) {
    return JSFML::Transform::FromSFML(env, TRANSFORMABLE->getInverseTransform());
}
