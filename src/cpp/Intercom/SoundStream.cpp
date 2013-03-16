#include <JSFML/Intercom/SoundStream.hpp>

#include <JSFML/Intercom/JVM.hpp>

jclass JSFML::SoundStream::cls = 0;

jmethodID JSFML::SoundStream::m_onGetData = 0;
jmethodID JSFML::SoundStream::m_onSeek = 0;

void JSFML::SoundStream::Init(JNIEnv *env) {
    jclass javaClass = env->FindClass("org/jsfml/audio/SoundStream");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        m_onGetData = env->GetMethodID(javaClass, "onGetDataInternal", "()Ljava/nio/Buffer;");
        m_onSeek = env->GetMethodID(javaClass, "onSeekInternal", "(J)V");
		env->DeleteLocalRef(javaClass);
    }
}

JSFML::SoundStream::SoundStream(JNIEnv *env, jobject obj) {
    this->javaRef = env->NewGlobalRef(obj);
    this->currentSamples = NULL;
}

JSFML::SoundStream::~SoundStream() {
    JNIEnv *env;
    if(JVM::Attach(&env)) {
        env->DeleteGlobalRef(javaRef);
        
        if(this->currentSamples) {
            env->DeleteGlobalRef(this->currentSamples);
            this->currentSamples = NULL;
        }
        
        JVM::Detach(&env);
    }
}

void JSFML::SoundStream::initialize(unsigned int channelCount, unsigned int sampleRate) {
    sf::SoundStream::initialize(channelCount, sampleRate);
}

bool JSFML::SoundStream::onGetData(sf::SoundStream::Chunk& data) {
    bool continuePlaying = false;

    JNIEnv *env;
    if(JVM::Attach(&env)) {
        if(this->currentSamples) {
            env->DeleteGlobalRef(this->currentSamples);
            this->currentSamples = NULL;
        }
    
        jobject samplesBuffer = env->CallObjectMethod(javaRef, m_onGetData);
        if(samplesBuffer) {
            this->currentSamples = env->NewGlobalRef(samplesBuffer);
            void *buffer = env->GetDirectBufferAddress(samplesBuffer);
            env->DeleteLocalRef(samplesBuffer);
            
            jint header = ((jint*)buffer)[0];
            
            continuePlaying = ((header & 0x80000000) == 0);
            
            data.sampleCount = header & 0x7FFFFFFF;
            data.samples = ((sf::Int16*)buffer) + 2;
        }

        JVM::Detach(&env);
    }
    return continuePlaying;
}

void JSFML::SoundStream::onSeek(sf::Time timeOffset) {
    JNIEnv *env;
    if(JVM::Attach(&env)) {
        env->CallVoidMethod(javaRef, m_onSeek, timeOffset.asMicroseconds());
        JVM::Detach(&env);
    }
}
