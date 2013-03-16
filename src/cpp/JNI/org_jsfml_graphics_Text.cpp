#include <JSFML/JNI/org_jsfml_graphics_Text.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

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
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_DRAWABLE,
        dynamic_cast<sf::Drawable*>(THIS(sf::Text)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_TRANSFORMABLE,
        dynamic_cast<sf::Transformable*>(THIS(sf::Text)));
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

    THIS(sf::Text)->setString(JSFML::Intercom::decodeUtf32(env, str));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetFont
 * Signature: (Lorg/jsfml/graphics/Font;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetFont
    (JNIEnv *env, jobject obj, jobject font) {

    THIS(sf::Text)->setFont(*JSFML::NativeObject::GetPointer<sf::Font>(env, font));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetCharacterSize
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetCharacterSize
    (JNIEnv *env, jobject obj, jint size) {

    THIS(sf::Text)->setCharacterSize(size);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetStyle
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetStyle
    (JNIEnv *env, jobject obj, jint style) {

    THIS(sf::Text)->setStyle(style);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetColor
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetColor (JNIEnv *env, jobject obj, jint color) {
    THIS(sf::Text)->setColor(JSFML::Intercom::decodeColor(color));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeFindCharacterPos
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Text_nativeFindCharacterPos (JNIEnv *env, jobject obj, jint pos) {
    return JSFML::Intercom::encodeVector2f(THIS(sf::Text)->findCharacterPos(pos));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeGetLocalBounds
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeGetLocalBounds (JNIEnv *env, jobject obj, jobject r) {
    JSFML::Intercom::encodeFloatRect(env, THIS(sf::Text)->getLocalBounds(), r);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeGetGlobalBounds
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeGetGlobalBounds (JNIEnv *env, jobject obj, jobject r) {
    JSFML::Intercom::encodeFloatRect(env, THIS(sf::Text)->getGlobalBounds(), r);
}
