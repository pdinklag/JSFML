#include <JSFML/JNI/org_jsfml_graphics_Sprite.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

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
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_DRAWABLE,
        dynamic_cast<sf::Drawable*>(THIS(sf::Sprite)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_TRANSFORMABLE,
        dynamic_cast<sf::Transformable*>(THIS(sf::Sprite)));
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
 * Signature: (Lorg/jsfml/graphics/Texture;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeSetTexture
    (JNIEnv *env, jobject obj, jobject texture, jboolean resetRect) {

    THIS(sf::Sprite)->setTexture(
        *JSFML::NativeObject::GetPointer<sf::Texture>(env, texture), (bool)resetRect);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    nativeSetTextureRect
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeSetTextureRect (JNIEnv *env, jobject obj, jobject rect) {
    THIS(sf::Sprite)->setTextureRect(JSFML::Intercom::decodeIntRect(env, rect));
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    nativeSetColor
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeSetColor (JNIEnv *env, jobject obj, jint color) {
    THIS(sf::Sprite)->setColor(JSFML::Intercom::decodeColor(color));
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    nativeGetLocalBounds
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeGetLocalBounds (JNIEnv *env, jobject obj, jobject r) {
    JSFML::Intercom::encodeFloatRect(env, THIS(sf::Sprite)->getLocalBounds(), r);
}

/*
 * Class:     org_jsfml_graphics_Sprite
 * Method:    nativeGetGlobalBounds
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Sprite_nativeGetGlobalBounds (JNIEnv *env, jobject obj, jobject r) {
    JSFML::Intercom::encodeFloatRect(env, THIS(sf::Sprite)->getGlobalBounds(), r);
}
