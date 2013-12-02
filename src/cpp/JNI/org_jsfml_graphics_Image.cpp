#include <JSFML/JNI/org_jsfml_graphics_Image.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <SFML/Graphics/Image.hpp>

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Image_nativeCreate (JNIEnv *env, jobject obj) {
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
 * Method:    nativeGetSize
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Image_nativeGetSize
    (JNIEnv *env, jobject obj) {

    return JSFML::Intercom::encodeVector2u(THIS(sf::Image)->getSize());
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeSync
 * Signature: (IILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeSync
    (JNIEnv *env, jobject obj, jint width, jint height, jobject buffer) {

    sf::Uint8 *dst = (sf::Uint8*)env->GetDirectBufferAddress(buffer);
    const sf::Uint8 *src = THIS(sf::Image)->getPixelsPtr();
    int x = width * height * 4;
    
    while(x) {
        *dst++ = *src++;
        x--;
    }
}

/*
 * Class:     org_jsfml_graphics_Image
 * Method:    nativeCommit
 * Signature: (IILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Image_nativeCommit
    (JNIEnv *env, jobject obj, jint width, jint height, jobject buffer) {
    
    if(width == 0 || height == 0) {
        THIS(sf::Image)->create(0, 0);
    } else {
        const sf::Uint8 *pixels = (const sf::Uint8*)env->GetDirectBufferAddress(buffer);
        THIS(sf::Image)->create(width, height, pixels);
    }
}
