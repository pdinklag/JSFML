#include <JSFML/Intercom/InputStream.hpp>

#include <JSFML/Intercom/JVM.hpp>

jclass JSFML::InputStream::cls = 0;

jmethodID JSFML::InputStream::m_read = 0;
jmethodID JSFML::InputStream::m_seek = 0;
jmethodID JSFML::InputStream::m_tell = 0;
jmethodID JSFML::InputStream::m_getSize = 0;
jmethodID JSFML::InputStream::m_close = 0;

void JSFML::InputStream::Init(JNIEnv *env) {
    jclass javaClass = env->FindClass("org/jsfml/internal/SFMLInputStream");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        m_read = env->GetMethodID(javaClass, "read", "(Ljava/nio/ByteBuffer;J)J");
        m_seek = env->GetMethodID(javaClass, "seek", "(J)J");
        m_tell = env->GetMethodID(javaClass, "tell", "()J");
        m_getSize = env->GetMethodID(javaClass, "getSize", "()J");
        m_close = env->GetMethodID(javaClass, "close", "()V");
		env->DeleteLocalRef(javaClass);
    }
}

JSFML::InputStream::InputStream(JNIEnv *env, jobject obj) {
    this->javaRef = env->NewGlobalRef(obj);
}

JSFML::InputStream::~InputStream() {
    JNIEnv *env;
    if(JVM::Attach(&env)) {
        env->CallVoidMethod(javaRef, m_close);
        env->DeleteGlobalRef(javaRef);
        JVM::Detach(&env);
    }
}

sf::Int64 JSFML::InputStream::read(void *buffer, sf::Int64 size) {
    sf::Int64 read;

    JNIEnv *env;
    if(JVM::Attach(&env)) {
        jobject byteBuffer = env->NewDirectByteBuffer(buffer, (jlong)size);

        read = (sf::Int64)env->CallLongMethod(javaRef, m_read, byteBuffer, (jlong)size);

        env->DeleteLocalRef(byteBuffer);
        JVM::Detach(&env);
    }
    
    return read;
}

sf::Int64 JSFML::InputStream::seek(sf::Int64 pos) {
    sf::Int64 actualPos;

    JNIEnv *env;
    if(JVM::Attach(&env)) {
        actualPos = (sf::Int64)env->CallLongMethod(javaRef, m_seek, (jlong)pos);
        JVM::Detach(&env);
    }
    
    return pos;
}

sf::Int64 JSFML::InputStream::tell() {
    sf::Int64 pos;

    JNIEnv *env;
    if(JVM::Attach(&env)) {
        pos = (sf::Int64)env->CallLongMethod(javaRef, m_tell);
        JVM::Detach(&env);
    }
    
    return pos;
}

sf::Int64 JSFML::InputStream::getSize() {
    sf::Int64 size;

    JNIEnv *env;
    if(JVM::Attach(&env)) {
        size = (sf::Int64)env->CallLongMethod(javaRef, m_getSize);
        JVM::Detach(&env);
    }
    
    return size;
}
