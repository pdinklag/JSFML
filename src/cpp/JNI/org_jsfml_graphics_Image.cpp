#include <JSFML/JNI/org_jsfml_graphics_Image.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/JavaString.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <SFML/Graphics/Image.hpp>

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Image_nativeCreate__ (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Image();
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeCreate
 * Signature: (IILorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeCreate__IILorg_jsfml_graphics_Color_2
    (JNIEnv *env, jobject obj, jint width, jint height, jobject color) {

    THIS(sf::Image)->Create(width, height, JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeLoadFromMemory
 * Signature: ([B)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Image_nativeLoadFromMemory (JNIEnv *env, jobject obj, jbyteArray arr) {
    std::size_t n = (std::size_t)env->GetArrayLength(arr);
    jbyte* mem = env->GetByteArrayElements(arr, 0);

    jboolean result = THIS(sf::Image)->LoadFromMemory(mem, n);

    env->ReleaseByteArrayElements(arr, mem, 0);
    return result;
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeSaveToFile
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Image_nativeSaveToFile (JNIEnv *env, jobject obj, jstring fileName) {
    return THIS(sf::Image)->SaveToFile(std::string(JavaString::getUTF8(env, fileName)));
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    getWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Image_getWidth (JNIEnv *env, jobject obj) {
    return THIS(sf::Image)->GetWidth();
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    getHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Image_getHeight (JNIEnv *env, jobject obj) {
  return THIS(sf::Image)->GetHeight();
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeCreateMaskFromColor
 * Signature: (Lorg/jsfml/graphics/Color;I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeCreateMaskFromColor
    (JNIEnv *env, jobject obj, jobject color, jint alpha) {

    THIS(sf::Image)->CreateMaskFromColor(JSFML::Color::ToSFML(env, color), alpha & 0xFF);
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeCopy
 * Signature: (Lorg/jsfml/graphics/Image;IILorg/jsfml/graphics/IntRect;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeCopy
    (JNIEnv *env, jobject obj, jobject source, jint destX, jint destY, jobject sourceRect, jboolean applyAlpha) {

    THIS(sf::Image)->Copy(
        *JSFML::NativeObject::GetPointer<sf::Image>(env, source),
        destX, destY, JSFML::IntRect::ToSFML(env, sourceRect), applyAlpha);
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeSetPixel
 * Signature: (IILorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeSetPixel
    (JNIEnv *env, jobject obj, jint x, jint y, jobject color) {

    THIS(sf::Image)->SetPixel(x, y, JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    getPixel
 * Signature: (II)Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Image_getPixel
    (JNIEnv *env, jobject obj, jint x, jint y) {

    return JSFML::Color::FromSFML(env, THIS(sf::Image)->GetPixel(x, y));
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    flipHorizontally
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_flipHorizontally (JNIEnv *env, jobject obj) {
    THIS(sf::Image)->FlipHorizontally();
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    flipVertically
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_flipVertically(JNIEnv *env, jobject obj) {
    THIS(sf::Image)->FlipVertically();
}
