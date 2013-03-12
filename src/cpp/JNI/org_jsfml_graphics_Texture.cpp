#include <JSFML/JNI/org_jsfml_graphics_Texture.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <SFML/Graphics/Texture.hpp>

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeGetMaximumSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Texture_nativeGetMaximumSize (JNIEnv *env, jclass cls) {
    return (jint)sf::Texture::getMaximumSize();
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeBind
 * Signature: (Lorg/jsfml/graphics/Texture;Lorg/jsfml/graphics/Texture/CoordinateType;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_nativeBind (JNIEnv *env, jclass cls, jobject jtex, jobject coordType) {
    sf::Texture::bind(
        JSFML::NativeObject::GetPointer<sf::Texture>(env, jtex),
        (sf::Texture::CoordinateType)JavaEnum::ordinal(env, coordType));
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Texture_nativeCreate__ (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Texture();
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Texture);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeCopy
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Texture_nativeCopy (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Texture(*THIS(sf::Texture));
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeCreate
 * Signature: (II)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Texture_nativeCreate__II
  (JNIEnv *env, jobject obj, jint width, jint height) {

    return THIS(sf::Texture)->create(width, height);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeLoadFromMemory
 * Signature: ([BLjava/nio/Buffer;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Texture_nativeLoadFromMemory
    (JNIEnv *env, jobject obj, jbyteArray arr, jobject area) {

    std::size_t n = (std::size_t)env->GetArrayLength(arr);
    jbyte* mem = env->GetByteArrayElements(arr, 0);

    jboolean result = THIS(sf::Texture)->loadFromMemory(mem, n, JSFML::Intercom::decodeIntRect(env, area));

    env->ReleaseByteArrayElements(arr, mem, JNI_ABORT);
    return result;
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeLoadFromImage
 * Signature: (Lorg/jsfml/graphics/Image;Ljava/nio/Buffer;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Texture_nativeLoadFromImage
    (JNIEnv *env, jobject obj, jobject image, jobject area) {

    return THIS(sf::Texture)->loadFromImage(
        *JSFML::NativeObject::GetPointer<sf::Image>(env, image),
        JSFML::Intercom::decodeIntRect(env, area));
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeGetSize
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Texture_nativeGetSize (JNIEnv *env, jobject obj) {
    return JSFML::Intercom::encodeVector2u(THIS(sf::Texture)->getSize());
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeCopyToImage
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Texture_nativeCopyToImage (JNIEnv *env, jobject obj) {
    sf::Image* image = new sf::Image();
    *image = THIS(sf::Texture)->copyToImage();
    return (jlong)image;
}

 /*
  * Class:     org_jsfml_graphics_Texture
  * Method:    nativeUpdate
  * Signature: (Lorg/jsfml/graphics/Image;II)V
  */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_nativeUpdate__Lorg_jsfml_graphics_Image_2II
    (JNIEnv *env, jobject obj, jobject image, jint x, jint y) {

    THIS(sf::Texture)->update(*JSFML::NativeObject::GetPointer<sf::Image>(env, image), x, y);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeUpdate
 * Signature: (Lorg/jsfml/window/Window;II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_nativeUpdate__Lorg_jsfml_window_Window_2II
    (JNIEnv *env, jobject obj, jobject window, jint x, jint y) {

    THIS(sf::Texture)->update(*JSFML::NativeObject::GetPointer<sf::Window>(env, window), x, y);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeSetSmooth
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_nativeSetSmooth (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::Texture)->setSmooth(b);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeSetRepeated
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_nativeSetRepeated (JNIEnv *env, jobject obj, jboolean b) {
  THIS(sf::Texture)->setRepeated(b);
}
