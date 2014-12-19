#include <JSFML/JNI/org_jsfml_graphics_Font.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <SFML/Graphics/Font.hpp>

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Font_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Font);
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Font_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Font();
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeLoadFromMemory
 * Signature: ([B)J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Font_nativeLoadFromMemory
    (JNIEnv *env, jobject obj, jbyteArray arr) {

    std::size_t n = (std::size_t)env->GetArrayLength(arr);
    jbyte* mem = env->GetByteArrayElements(arr, 0);

    if(THIS(sf::Font)->loadFromMemory(mem, n)) {
        return (jlong)mem;
    } else {
        env->ReleaseByteArrayElements(arr, mem, JNI_ABORT);
        return 0;
    }
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeReleaseMemory
 * Signature: ([BJ)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Font_nativeReleaseMemory
    (JNIEnv *env, jobject obj, jbyteArray arr, jlong memPtr) {

    env->ReleaseByteArrayElements(arr, (jbyte*)memPtr, JNI_ABORT);
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeGetGlyph
 * Signature: (IIZLjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Font_nativeGetGlyph
    (JNIEnv *env, jobject obj, jint unicode, jint characterSize, jboolean bold, jobject buffer) {
    
    const sf::Glyph& glyph = THIS(sf::Font)->getGlyph((sf::Uint32)unicode, characterSize, bold);
    
    jint *buf = (jint*)env->GetDirectBufferAddress(buffer);
    buf[0] = glyph.advance;
    buf[1] = glyph.bounds.left;
    buf[2] = glyph.bounds.top;
    buf[3] = glyph.bounds.width;
    buf[4] = glyph.bounds.height;
    buf[5] = glyph.textureRect.left;
    buf[6] = glyph.textureRect.top;
    buf[7] = glyph.textureRect.width;
    buf[8] = glyph.textureRect.height;
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeGetInfo
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_jsfml_graphics_Font_nativeGetInfo
    (JNIEnv *env, jobject obj) {

    return env->NewStringUTF(THIS(sf::Font)->getInfo().family.c_str());
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeGetKerning
 * Signature: (III)F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_Font_nativeGetKerning
    (JNIEnv *env, jobject obj, jint first, jint second, jint size) {

    return THIS(sf::Font)->getKerning((sf::Uint32)first, (sf::Uint32)second, size);
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeGetLineSpacing
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_Font_nativeGetLineSpacing
    (JNIEnv *env, jobject obj, jint size) {

    return THIS(sf::Font)->getLineSpacing(size);
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeGetUnderlinePosition
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_Font_nativeGetUnderlinePosition
    (JNIEnv *env, jobject obj, jint size) {

    return THIS(sf::Font)->getUnderlinePosition(size);
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeGetUnderlineThickness
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_org_jsfml_graphics_Font_nativeGetUnderlineThickness
    (JNIEnv *env, jobject obj, jint size) {

    return THIS(sf::Font)->getUnderlineThickness(size);
}

/*
 * Class:     org_jsfml_graphics_Font
 * Method:    nativeGetTexture
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Font_nativeGetTexture
    (JNIEnv *env, jobject obj, jint size) {

    return (jlong)&THIS(sf::Font)->getTexture(size);
}
