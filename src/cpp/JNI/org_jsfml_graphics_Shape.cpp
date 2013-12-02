#include <JSFML/JNI/org_jsfml_graphics_Shape.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/Shape.hpp>

#define SHAPE JSFML::NativeObject::GetExPointer<sf::Shape>(env, obj, org_jsfml_internal_ExPtr_SHAPE)

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeSetTexture
 * Signature: (Lorg/jsfml/graphics/Texture;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeSetTexture
    (JNIEnv *env, jobject obj, jobject texture, jboolean resetRect) {

     SHAPE->setTexture(JSFML::NativeObject::GetPointer<sf::Texture>(env, texture), resetRect);
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeSetTextureRect
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeSetTextureRect
    (JNIEnv *env, jobject obj, jobject rect) {

    SHAPE->setTextureRect(JSFML::Intercom::decodeIntRect(env, rect));
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeSetFillColor
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeSetFillColor
    (JNIEnv *env, jobject obj, jint color) {

    SHAPE->setFillColor(JSFML::Intercom::decodeColor(color));
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeSetOutlineColor
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeSetOutlineColor
    (JNIEnv *env, jobject obj, jint color) {

    SHAPE->setOutlineColor(JSFML::Intercom::decodeColor(color));
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeSetOutlineThickness
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeSetOutlineThickness
    (JNIEnv *env, jobject obj, jfloat thickness) {

    SHAPE->setOutlineThickness(thickness);
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeGetPointCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Shape_nativeGetPointCount
  (JNIEnv *env, jobject obj) {
  
    return SHAPE->getPointCount();
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeGetPoints
 * Signature: (ILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeGetPoints
  (JNIEnv *env, jobject obj, jint n, jobject buffer) {

    jfloat *buf = (jfloat*)env->GetDirectBufferAddress(buffer);
    for(int i = 0; i < n; i++) {
        sf::Vector2f v = SHAPE->getPoint(i);
        buf[2 * i]     = v.x;
        buf[2 * i + 1] = v.y;
    }
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeGetLocalBounds
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeGetLocalBounds (JNIEnv *env, jobject obj, jobject r) {
    JSFML::Intercom::encodeFloatRect(env, SHAPE->getLocalBounds(), r);
}

/*
 * Class:     org_jsfml_graphics_Shape
 * Method:    nativeGetGlobalBounds
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shape_nativeGetGlobalBounds (JNIEnv *env, jobject obj, jobject r) {
    JSFML::Intercom::encodeFloatRect(env, SHAPE->getGlobalBounds(), r);
}
