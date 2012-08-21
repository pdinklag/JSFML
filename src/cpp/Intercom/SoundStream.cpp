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

JSFML::SoundStream::SoundStream(jobject obj) {
    this->buffer = NULL;
    this->javaRef = JVM::GetJNIEnv()->NewGlobalRef(obj);
}

JSFML::SoundStream::~SoundStream() {
    JVM::GetJNIEnv()->DeleteGlobalRef(javaRef);
    clearBuffer();
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

    JNIEnv *env = JVM::GetJNIEnv();
    jobject chunk = JVM::GetJNIEnv()->CallObjectMethod(javaRef, m_onGetData);

    if(chunk) {
        buffer = new JSFML::Chunk(chunk);
        env->DeleteLocalRef(chunk);

        return buffer->GetData(data);
    } else {
        return false;
    }
}

void JSFML::SoundStream::onSeek(sf::Time timeOffset) {
    JNIEnv *env = JVM::GetJNIEnv();
    env->CallVoidMethod(javaRef, m_onSeek, JSFML::Time::FromSFML(env, timeOffset));
}
