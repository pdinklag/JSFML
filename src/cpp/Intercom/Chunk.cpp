#include <JSFML/Intercom/Chunk.hpp>

#include <JSFML/Intercom/JVM.hpp>

jclass JSFML::Chunk::cls = 0;

jfieldID JSFML::Chunk::f_data = 0;
jfieldID JSFML::Chunk::f_last = 0;

void JSFML::Chunk::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/audio/SoundStream$Chunk");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        f_data = env->GetFieldID(javaClass, "data", "[S");
        f_last = env->GetFieldID(javaClass, "last", "Z");
		env->DeleteLocalRef(javaClass);
    }
}

JSFML::Chunk::Chunk(JNIEnv *env, jobject chunk) {
    this->last = (bool)env->GetBooleanField(chunk, f_last);
    this->dataArray = (jshortArray)env->NewGlobalRef(env->GetObjectField(chunk, f_data));
    this->sampleCount = (size_t)env->GetArrayLength(this->dataArray);
    this->data = env->GetShortArrayElements(dataArray, 0);
}

JSFML::Chunk::~Chunk() {
    JNIEnv *env;

    if(data && JVM::Attach(&env)) {
        env->ReleaseShortArrayElements(dataArray, data, JNI_ABORT);
        env->DeleteGlobalRef(this->dataArray);
        JVM::Detach(&env);

        data = NULL;
        dataArray = NULL;
    }
}

bool JSFML::Chunk::GetData(sf::SoundStream::Chunk &chunk) {
    chunk.sampleCount = sampleCount;
    chunk.samples = (const sf::Int16 *)data;
    return !last;
}
