#include <JSFML/JNI/org_jsfml_graphics_Image.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector2u.hpp>

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
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Image);
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeCreate
 * Signature: (IILorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeCreate__IILorg_jsfml_graphics_Color_2
    (JNIEnv *env, jobject obj, jint width, jint height, jobject color) {

    THIS(sf::Image)->create(width, height, JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeLoadFromMemory
 * Signature: ([B)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Image_nativeLoadFromMemory (JNIEnv *env, jobject obj, jbyteArray arr) {
    std::size_t n = (std::size_t)env->GetArrayLength(arr);
    jbyte* mem = env->GetByteArrayElements(arr, 0);

    jboolean result = THIS(sf::Image)->loadFromMemory(mem, n);

    env->ReleaseByteArrayElements(arr, mem, JNI_ABORT);
    return result;
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeSaveToFile
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Image_nativeSaveToFile (JNIEnv *env, jobject obj, jstring fileName) {
	const char *utf8 = env->GetStringUTFChars(fileName, NULL);

    jboolean result = THIS(sf::Image)->saveToFile(std::string(utf8));
	env->ReleaseStringUTFChars(fileName, utf8);

    return result;
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    getSize
 * Signature: ()Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Image_getSize (JNIEnv *env, jobject obj) {
   return JSFML::Vector2u::FromSFML(env, THIS(sf::Image)->getSize());
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeCreateMaskFromColor
 * Signature: (Lorg/jsfml/graphics/Color;I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeCreateMaskFromColor
    (JNIEnv *env, jobject obj, jobject color, jint alpha) {

    THIS(sf::Image)->createMaskFromColor(JSFML::Color::ToSFML(env, color), alpha & 0xFF);
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeCopy
 * Signature: (Lorg/jsfml/graphics/Image;IILorg/jsfml/graphics/IntRect;Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeCopy
    (JNIEnv *env, jobject obj, jobject source, jint destX, jint destY, jobject sourceRect, jboolean applyAlpha) {

    THIS(sf::Image)->copy(
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

    THIS(sf::Image)->setPixel(x, y, JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeGetPixel
 * Signature: (II)Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Image_nativeGetPixel
    (JNIEnv *env, jobject obj, jint x, jint y) {

    return JSFML::Color::FromSFML(env, THIS(sf::Image)->getPixel(x, y));
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    flipHorizontally
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_flipHorizontally (JNIEnv *env, jobject obj) {
    THIS(sf::Image)->flipHorizontally();
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    flipVertically
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_flipVertically(JNIEnv *env, jobject obj) {
    THIS(sf::Image)->flipVertically();
}
