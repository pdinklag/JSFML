#include <JSFML/JNI/org_jsfml_graphics_Shader.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/JavaString.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Transform.hpp>

#include <SFML/Graphics/Shader.hpp>

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    isAvailable
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Shader_isAvailable (JNIEnv *env, jclass cls) {
    return sf::Shader::IsAvailable();
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Shader);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Shader_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Shader();
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeLoadFromSource
 * Signature: (Ljava/lang/String;Lorg/jsfml/graphics/Shader$Type;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Shader_nativeLoadFromSource__Ljava_lang_String_2Lorg_jsfml_graphics_Shader_00024Type_2
    (JNIEnv *env, jobject obj, jstring source, jobject type) {

    return THIS(sf::Shader)->LoadFromMemory(
        std::string(JavaString::getUTF8(env, source)),
        (sf::Shader::Type)JavaEnum::ordinal(env, type));
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeLoadFromSource
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Shader_nativeLoadFromSource__Ljava_lang_String_2Ljava_lang_String_2
    (JNIEnv *env, jobject obj, jstring vertSource, jstring fragSource) {

    return THIS(sf::Shader)->LoadFromMemory(
        std::string(JavaString::getUTF8(env, vertSource)),
        std::string(JavaString::getUTF8(env, fragSource)));
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2F
    (JNIEnv *env, jobject obj, jstring name, jfloat x) {

    THIS(sf::Shader)->SetParameter(std::string(JavaString::getUTF8(env, name)), x);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2FF
    (JNIEnv *env, jobject obj, jstring name, jfloat x, jfloat y) {

    THIS(sf::Shader)->SetParameter(std::string(JavaString::getUTF8(env, name)), x, y);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2FFF
    (JNIEnv *env, jobject obj, jstring name, jfloat x, jfloat y, jfloat z) {

    THIS(sf::Shader)->SetParameter(std::string(JavaString::getUTF8(env, name)), x, y, z);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;FFFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2FFFF
    (JNIEnv *env, jobject obj, jstring name, jfloat x, jfloat y, jfloat z, jfloat w) {

    THIS(sf::Shader)->SetParameter(std::string(JavaString::getUTF8(env, name)), x, y, z, w);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;Lorg/jsfml/graphics/Transform;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2Lorg_jsfml_graphics_Transform_2
    (JNIEnv *env, jobject obj, jstring name, jobject xform) {

    THIS(sf::Shader)->SetParameter(
        std::string(JavaString::getUTF8(env, name)),
        JSFML::Transform::ToSFML(env, xform));
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;Lorg/jsfml/graphics/Texture;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2Lorg_jsfml_graphics_Texture_2
    (JNIEnv *env, jobject obj, jstring name, jobject texture) {

    THIS(sf::Shader)->SetParameter(
        std::string(JavaString::getUTF8(env, name)),
        *JSFML::NativeObject::GetPointer<sf::Texture>(env, texture));
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameterCurrentTexture
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameterCurrentTexture
    (JNIEnv *env, jobject obj, jstring name) {

    THIS(sf::Shader)->SetParameter(
        std::string(JavaString::getUTF8(env, name)),
        sf::Shader::CurrentTexture);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    bind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_bind (JNIEnv *env, jobject obj) {
    THIS(sf::Shader)->Bind();
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    unbind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_unbind (JNIEnv *env, jobject obj) {
    THIS(sf::Shader)->Unbind();
}
