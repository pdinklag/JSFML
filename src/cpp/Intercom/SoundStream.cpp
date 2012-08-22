#include <JSFML/Intercom/SoundStream.hpp>

#include <JSFML/Intercom/JVM.hpp>
#include <JSFML/Intercom/Time.hpp>

jclass JSFML::SoundStream::cls = 0;

jmethodID JSFML::SoundStream::m_onGetData = 0;
jmethodID JSFML::SoundStream::m_onSeek = 0;

void JSFML::SoundStream::Init(JNIEnv *env) {
    jclass javaClass = env->FindClass("org/jsfml/audio/SoundStream");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        m_onGetData = env->GetMethodID(javaClass, "onGetData", "()Lorg/jsfml/audio/SoundStream$Chunk;");
        m_onSeek = env->GetMethodID(javaClass, "onSeek", "(Lorg/jsfml/system/Time;)V");
		env->DeleteLocalRef(javaClass);
    }
}

JSFML::SoundStream::SoundStream(JNIEnv *env, jobject obj) {
    this->buffer = NULL;
    this->javaRef = env->NewGlobalRef(obj);
}

JSFML::SoundStream::~SoundStream() {
    clearBuffer();

    JNIEnv *env;
    if(JVM::Attach(&env)) {
        env->DeleteGlobalRef(javaRef);
        JVM::Detach(&env);
    }
}

void JSFML::SoundStream::clearBuffer() {
    if(buffer) {
        delete buffer;
        buffer = NULL;
    }
}

void JSFML::SoundStream::initialize(unsigned int channelCount, unsigned int sampleRate) {
    sf::SoundStream::initialize(channelCount, sampleRate);
}

bool JSFML::SoundStream::onGetData(sf::SoundStream::Chunk& data) {
    clearBuffer();

    bool continuePlaying = false;

    JNIEnv *env;
    if(JVM::Attach(&env)) {
        jobject chunk = env->CallObjectMethod(javaRef, m_onGetData);
        if(chunk) {
            buffer = new JSFML::Chunk(env, chunk);
            env->DeleteLocalRef(chunk);

            continuePlaying = buffer->GetData(data);
        }

        JVM::Detach(&env);
    }
    return continuePlaying;
}

void JSFML::SoundStream::onSeek(sf::Time timeOffset) {
    JNIEnv *env;
    if(JVM::Attach(&env)) {
        env->CallVoidMethod(javaRef, m_onSeek, JSFML::Time::FromSFML(env, timeOffset));
        JVM::Detach(&env);
    }
}
