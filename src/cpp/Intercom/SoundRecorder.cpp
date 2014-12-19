#include <JSFML/Intercom/SoundRecorder.hpp>

#include <JSFML/Intercom/JVM.hpp>

jclass JSFML::SoundRecorder::cls = 0;

jmethodID JSFML::SoundRecorder::m_onStart = 0;
jmethodID JSFML::SoundRecorder::m_onProcessSamples = 0;
jmethodID JSFML::SoundRecorder::m_onStop = 0;

void JSFML::SoundRecorder::Init(JNIEnv *env) {
    jclass javaClass = env->FindClass("org/jsfml/audio/SoundRecorder");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        m_onStart = env->GetMethodID(javaClass, "onStart", "()Z");
        m_onProcessSamples = env->GetMethodID(javaClass, "onProcessSamples", "([S)Z");
        m_onStop = env->GetMethodID(javaClass, "onStop", "()V");
		env->DeleteLocalRef(javaClass);
    }
}

JSFML::SoundRecorder::SoundRecorder(JNIEnv *env, jobject obj) {
    this->javaRef = env->NewGlobalRef(obj);
}

JSFML::SoundRecorder::~SoundRecorder() {
    JNIEnv *env;
    if(JVM::Attach(&env)) {
        env->DeleteGlobalRef(javaRef);
        JVM::Detach(&env);
    }
}

bool JSFML::SoundRecorder::onStart() {
    bool start = false;

    JNIEnv *env;
    if(JVM::Attach(&env)) {
        start = (bool)env->CallBooleanMethod(javaRef, m_onStart);
        JVM::Detach(&env);
    }
    return start;
}

bool JSFML::SoundRecorder::onProcessSamples(const sf::Int16 *samples, std::size_t count) {
    bool continueRecording = false;

    JNIEnv *env;
    if(JVM::Attach(&env)) {
        jshortArray jsamplesArray = env->NewShortArray((jsize)count);
        
        jshort *jsamples = env->GetShortArrayElements(jsamplesArray, NULL);
        for(int i = 0; i < count; i++) {
            jsamples[i] = (jshort)samples[i];
        }
        env->ReleaseShortArrayElements(jsamplesArray, jsamples, 0);
    
        continueRecording = (bool)env->CallBooleanMethod(javaRef, m_onProcessSamples, jsamplesArray);
        JVM::Detach(&env);
    }
    return continueRecording;
}

void JSFML::SoundRecorder::onStop() {
    JNIEnv *env;
    if(JVM::Attach(&env)) {
        env->CallVoidMethod(javaRef, m_onStop);
        JVM::Detach(&env);
    }
}

void JSFML::SoundRecorder::setProcessingInterval(sf::Time interval) {
    sf::SoundRecorder::setProcessingInterval(interval);
}
