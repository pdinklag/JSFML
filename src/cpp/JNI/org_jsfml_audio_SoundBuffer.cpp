#include <string.h>
#include <JSFML/JNI/org_jsfml_audio_SoundBuffer.h>

#include <JSFML/Intercom/NativeObject.hpp>

#include <SFML/Audio/SoundBuffer.hpp>

/*
 * Class:     org_jsfml_audio_SoundBuffer
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_SoundBuffer_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::SoundBuffer();
}

/*
 * Class:     org_jsfml_audio_SoundBuffer
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundBuffer_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::SoundBuffer);
}

/*
 * Class:     org_jsfml_audio_SoundBuffer
 * Method:    nativeCopy
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_SoundBuffer_nativeCopy (JNIEnv *env, jobject obj) {
    return (jlong)new sf::SoundBuffer(*THIS(sf::SoundBuffer));
}

/*
 * Class:     org_jsfml_audio_SoundBuffer
 * Method:    nativeLoadFromMemory
 * Signature: ([B)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundBuffer_nativeLoadFromMemory (JNIEnv *env, jobject obj, jbyteArray arr) {
    std::size_t n = (std::size_t)env->GetArrayLength(arr);
    jbyte* mem = env->GetByteArrayElements(arr, 0);

    jboolean result = THIS(sf::SoundBuffer)->loadFromMemory(mem, n);

    env->ReleaseByteArrayElements(arr, mem, JNI_ABORT);
    return result;
}

/*
 * Class:     org_jsfml_audio_SoundBuffer
 * Method:    nativeLoadFromSamples
 * Signature: (Ljava/nio/Buffer;III)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundBuffer_nativeLoadFromSamples
    (JNIEnv *env, jobject obj, jobject buffer, jint sampleCount, jint channelCount, jint sampleRate) {

    sf::Int16 *samples = (sf::Int16*)env->GetDirectBufferAddress(buffer);
    return THIS(sf::SoundBuffer)->loadFromSamples(samples, sampleCount, channelCount, sampleRate);
}

/*
 * Class:     org_jsfml_audio_SoundBuffer
 * Method:    nativeSaveToFile
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundBuffer_nativeSaveToFile (JNIEnv *env, jobject obj, jstring fileName) {
	const char *utf8 = env->GetStringUTFChars(fileName, NULL);

    jboolean result = THIS(sf::SoundBuffer)->saveToFile(std::string(utf8));
	env->ReleaseStringUTFChars(fileName, utf8);

    return result;
}

/*
 * Class:     org_jsfml_audio_SoundBuffer
 * Method:    nativeGetData
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundBuffer_nativeGetData
    (JNIEnv *env, jobject obj, jobject buffer) {
    
    sf::SoundBuffer *soundBuffer = THIS(sf::SoundBuffer);
    void *data = env->GetDirectBufferAddress(buffer);

    ((jint*)data)[0] = (jint)soundBuffer->getSampleCount();
    ((jint*)data)[1] = (jint)soundBuffer->getSampleRate();
    ((jint*)data)[2] = (jint)soundBuffer->getChannelCount();
    ((jlong*)data)[3] = (jlong)soundBuffer->getDuration().asMicroseconds();
}

/*
 * Class:     org_jsfml_audio_SoundBuffer
 * Method:    nativeGetSamples
 * Signature: (ILjava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundBuffer_nativeGetSamples
    (JNIEnv *env, jobject obj, jint num, jobject buffer) {

    const sf::Int16 *samples = THIS(sf::SoundBuffer)->getSamples();
    jshort *jsamples = (jshort*)env->GetDirectBufferAddress(buffer);
    
    memcpy(jsamples, samples, 2 * num);
}
