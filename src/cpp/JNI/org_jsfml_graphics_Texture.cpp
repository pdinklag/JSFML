#include <JSFML/JNI/org_jsfml_graphics_Texture.h>

#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <SFML/Graphics/Texture.hpp>

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Texture_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Texture();
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
 * Method:    create
 * Signature: (II)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Texture_create (JNIEnv *env, jobject obj, jint width, jint height) {
    return THIS(sf::Texture)->Create(width, height);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeLoadFromMemory
 * Signature: ([BLorg/jsfml/graphics/IntRect;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Texture_nativeLoadFromMemory
    (JNIEnv *env, jobject obj, jbyteArray arr, jobject area) {

    std::size_t n = (std::size_t)env->GetArrayLength(arr);
    jbyte* mem = env->GetByteArrayElements(arr, 0);

    jboolean result = THIS(sf::Texture)->LoadFromMemory(mem, n, JSFML::IntRect::ToSFML(env, area));

    env->ReleaseByteArrayElements(arr, mem, 0);
    return result;
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeLoadFromImage
 * Signature: (Lorg/jsfml/graphics/Image;Lorg/jsfml/graphics/IntRect;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Texture_nativeLoadFromImage
    (JNIEnv *env, jobject obj, jobject image, jobject area) {

    return THIS(sf::Texture)->LoadFromImage(
        *JSFML::NativeObject::GetPointer<sf::Image>(env, image),
        JSFML::IntRect::ToSFML(env, area));
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    getWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Texture_getWidth (JNIEnv *env, jobject obj) {
    return THIS(sf::Texture)->GetWidth();
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    getHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Texture_getHeight (JNIEnv *env, jobject obj) {
     return THIS(sf::Texture)->GetHeight();
 }

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeCopyToImage
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Texture_nativeCopyToImage (JNIEnv *env, jobject obj) {
    sf::Image* image = new sf::Image();
    sf::Image copy = THIS(sf::Texture)->CopyToImage();

    image->Create(copy.GetWidth(), copy.GetHeight(), copy.GetPixelsPtr());
    return (jlong)image;
}

 /*
  * Class:     org_jsfml_graphics_Texture
  * Method:    nativeUpdate
  * Signature: (Lorg/jsfml/graphics/Image;II)V
  */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_nativeUpdate__Lorg_jsfml_graphics_Image_2II
    (JNIEnv *env, jobject obj, jobject image, jint x, jint y) {

    THIS(sf::Texture)->Update(*JSFML::NativeObject::GetPointer<sf::Image>(env, image), x, y);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeUpdate
 * Signature: (Lorg/jsfml/window/Window;II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_nativeUpdate__Lorg_jsfml_window_Window_2II
    (JNIEnv *env, jobject obj, jobject window, jint x, jint y) {

    THIS(sf::Texture)->Update(*JSFML::NativeObject::GetPointer<sf::Window>(env, window), x, y);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    nativeBind
 * Signature: (Lorg/jsfml/graphics/Texture$CoordinateType;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_nativeBind (JNIEnv *env, jobject obj, jobject coordType) {
    THIS(sf::Texture)->Bind((sf::Texture::CoordinateType)JavaEnum::ordinal(env, coordType));
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    setSmooth
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_setSmooth (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::Texture)->SetSmooth(b);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    isSmooth
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Texture_isSmooth (JNIEnv *env, jobject obj) {
    return THIS(sf::Texture)->IsSmooth();
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    setRepeated
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Texture_setRepeated (JNIEnv *env, jobject obj, jboolean b) {
  THIS(sf::Texture)->SetRepeated(b);
}

/*
 * Class:     org_jsfml_graphics_Texture
 * Method:    isRepeated
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_Texture_isRepeated (JNIEnv *env, jobject obj) {
    return THIS(sf::Texture)->IsRepeated();
}
