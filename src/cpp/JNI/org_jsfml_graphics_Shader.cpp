#include <JSFML/JNI/org_jsfml_graphics_Shader.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

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
 * Signature: (Ljava/lang/String;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Shader_nativeLoadFromSource1
    (JNIEnv *env, jobject obj, jstring source, jint type) {

    const char *utf8 = env->GetStringUTFChars(source, NULL);

    jboolean result = THIS(sf::Shader)->loadFromMemory(
        std::string(utf8),
        (sf::Shader::Type)type);

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
 * Method:    nativeSetParameterFloat
 * Signature: (Ljava/lang/String;F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameterFloat
    (JNIEnv *env, jobject obj, jstring name, jfloat x) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(std::string(utf8), x);
    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameterVec2
 * Signature: (Ljava/lang/String;FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameterVec2
    (JNIEnv *env, jobject obj, jstring name, jfloat x, jfloat y) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(std::string(utf8), x, y);
    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameterVec3
 * Signature: (Ljava/lang/String;FFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameterVec3
    (JNIEnv *env, jobject obj, jstring name, jfloat x, jfloat y, jfloat z) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(std::string(utf8), x, y, z);
    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameterVec4
 * Signature: (Ljava/lang/String;FFFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameterVec4
    (JNIEnv *env, jobject obj, jstring name, jfloat x, jfloat y, jfloat z, jfloat w) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(std::string(utf8), x, y, z, w);
    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameterMat4
 * Signature: (Ljava/lang/String;Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameterMat4
    (JNIEnv *env, jobject obj, jstring name, jobject xform) {

    const char *utf8 = env->GetStringUTFChars(name, NULL);
    THIS(sf::Shader)->setParameter(
        std::string(utf8),
        JSFML::Intercom::decodeTransform(env, xform));

    env->ReleaseStringUTFChars(name, utf8);
}

/*
 * Class:     org_jsfml_graphics_Shader
 * Method:    nativeSetParameterSampler2d
 * Signature: (Ljava/lang/String;Lorg/jsfml/graphics/Texture;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Shader_nativeSetParameterSampler2d
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
