#include <JSFML/JNI/org_jsfml_graphics_Shader.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Transform.hpp>

#include <SFML/Graphics/Shader.hpp>

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    bind
 * Signature: (Lorg/jsfml/graphics/ConstShader;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_bind (JNIEnv *env, jclass cls, jobject jshader) {
    sf::Shader::bind(JSFML::NativeObject::GetPointer<sf::Shader>(env, jshader));
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    isAvailable
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Shader_isAvailable (JNIEnv *env, jclass cls) {
    return sf::Shader::isAvailable();
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
 * Method:    nativeLoadFromSource1
 * Signature: (Ljava/lang/String;Lorg/jsfml/graphics/Shader/Type;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Shader_nativeLoadFromSource1
    (JNIEnv *env, jobject obj, jstring source, jobject type) {

    const char *utf8 = env->GetStringUTFChars(source, NULL);

    jboolean result = THIS(sf::Shader)->loadFromMemory(
        std::string(utf8),
        (sf::Shader::Type)JavaEnum::ordinal(env, type));

    env->ReleaseStringUTFChars(source, utf8);
    return result;
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeLoadFromSource2
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Shader_nativeLoadFromSource2
    (JNIEnv *env, jobject obj, jstring vertSource, jstring fragSource) {

    const char *utf8Vert = env->GetStringUTFChars(vertSource, NULL);
    const char *utf8Frag = env->GetStringUTFChars(fragSource, NULL);

    jboolean result = THIS(sf::Shader)->loadFromMemory(
        std::string(utf8Vert),
        std::string(utf8Frag));

    env->ReleaseStringUTFChars(vertSource, utf8Vert);
    env->ReleaseStringUTFChars(fragSource, utf8Frag);
    return result;
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2F
    (JNIEnv *env, jobject obj, jstring name, jfloat x) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(std::string(utf8), x);
    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2FF
    (JNIEnv *env, jobject obj, jstring name, jfloat x, jfloat y) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(std::string(utf8), x, y);
    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2FFF
    (JNIEnv *env, jobject obj, jstring name, jfloat x, jfloat y, jfloat z) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(std::string(utf8), x, y, z);
    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;FFFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2FFFF
    (JNIEnv *env, jobject obj, jstring name, jfloat x, jfloat y, jfloat z, jfloat w) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(std::string(utf8), x, y, z, w);
    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;Lorg/jsfml/graphics/Transform;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2Lorg_jsfml_graphics_Transform_2
    (JNIEnv *env, jobject obj, jstring name, jobject xform) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(
        std::string(utf8),
        JSFML::Transform::ToSFML(env, xform));

    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameter
 * Signature: (Ljava/lang/String;Lorg/jsfml/graphics/Texture;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameter__Ljava_lang_String_2Lorg_jsfml_graphics_Texture_2
    (JNIEnv *env, jobject obj, jstring name, jobject texture) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(
        std::string(utf8),
        *JSFML::NativeObject::GetPointer<sf::Texture>(env, texture));

    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameterCurrentTexture
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameterCurrentTexture
    (JNIEnv *env, jobject obj, jstring name) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(
        std::string(utf8),
        sf::Shader::CurrentTexture);

    env->ReleaseStringUTFChars(name, utf8);
}
