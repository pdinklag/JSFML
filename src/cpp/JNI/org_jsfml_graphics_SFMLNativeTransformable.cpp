#include <JSFML/JNI/org_jsfml_graphics_SFMLNativeTransformable.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/Transformable.hpp>

#define TRANSFORMABLE JSFML::NativeObject::GetExPointer<sf::Transformable>(env, obj, org_jsfml_internal_ExPtr_TRANSFORMABLE)

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    nativeSetPosition
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_nativeSetPosition (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    TRANSFORMABLE->setPosition(x, y);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    nativeSetRotation
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_nativeSetRotation (JNIEnv *env, jobject obj, jfloat angle) {
    TRANSFORMABLE->setRotation(angle);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    nativeSetScale
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_nativeSetScale (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    TRANSFORMABLE->setScale(x, y);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    nativeSetOrigin
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_nativeSetOrigin (JNIEnv *env, jobject obj, jfloat x, jfloat y) {
    TRANSFORMABLE->setOrigin(x, y);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeTransformable
 * Method:    nativeGetTransform
 * Signature: (Ljava/nio/FloatBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeTransformable_nativeGetTransform (JNIEnv *env, jobject obj, jobject buf) {
    return JSFML::Intercom::encodeTransform(env, TRANSFORMABLE->getTransform(), buf);
}
