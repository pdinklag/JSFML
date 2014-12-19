#include <JSFML/JNI/org_jsfml_audio_SoundRecorder.h>

#include <SFML/Audio/SoundRecorder.hpp>
#include <SFML/System/Time.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/SoundRecorder.hpp>

#define SOUND_RECORDER JSFML::NativeObject::GetExPointer<sf::SoundRecorder>(env, obj, org_jsfml_internal_ExPtr_SOUND_RECORDER)

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    isAvailable
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundRecorder_isAvailable (JNIEnv *env, jclass cls) {
    return sf::SoundRecorder::isAvailable();
}

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    getAvailableDevices
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_org_jsfml_audio_SoundRecorder_getAvailableDevices
    (JNIEnv *env, jclass cls) {

    std::vector<std::string> devices = sf::SoundRecorder::getAvailableDevices();

    jobjectArray arr = JSFML::Intercom::newStringArray(env, devices.size());
    for(int i = 0; i < devices.size(); i++) {
        env->SetObjectArrayElement(arr, i, env->NewStringUTF(devices[i].c_str()));
    }
    
    return arr;
}

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    getDefaultDevice
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_jsfml_audio_SoundRecorder_getDefaultDevice
    (JNIEnv *env, jclass cls) {

    return env->NewStringUTF(sf::SoundRecorder::getDefaultDevice().c_str());
}

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_audio_SoundRecorder_nativeCreate (JNIEnv *env, jobject obj) {
	return (jlong)new JSFML::SoundRecorder(env, obj);
}

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundRecorder_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_SOUND_RECORDER,
        dynamic_cast<sf::SoundRecorder*>(THIS(JSFML::SoundRecorder)));
}

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundRecorder_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(JSFML::SoundRecorder);
}

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    nativeStart
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundRecorder_nativeStart
    (JNIEnv *env, jobject obj, jint sampleRate) {

    return SOUND_RECORDER->start((unsigned int)sampleRate);
}

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundRecorder_stop (JNIEnv *env, jobject obj) {
    SOUND_RECORDER->stop();
}

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    nativeSetProcessingInterval
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_audio_SoundRecorder_nativeSetProcessingInterval
    (JNIEnv *env, jobject obj, jlong interval) {

    /*
        This must NEVER be called by the SoundBufferRecorder class!
        The protected access on the Java level should prevent this from happening, unless reflection is used.
    */
    THIS(JSFML::SoundRecorder)->setProcessingInterval(sf::microseconds(interval));
}

/*
 * Class:     org_jsfml_audio_SoundRecorder
 * Method:    nativeSetDevice
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_audio_SoundRecorder_nativeSetDevice
    (JNIEnv *env, jobject obj, jstring device) {

    const char *utf8 = env->GetStringUTFChars(device, NULL);

    bool result = SOUND_RECORDER->setDevice(std::string(utf8));
    env->ReleaseStringUTFChars(device, utf8);
    
    return result;
}
