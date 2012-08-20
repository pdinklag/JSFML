#include <JSFML/Intercom/Chunk.hpp>

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

bool JSFML::Chunk::GetData(JNIEnv* env, jobject chunk, sf::SoundStream::Chunk &data) {
    jshortArray arr = (jshortArray)env->GetObjectField(chunk, f_data);
    data.sampleCount = (size_t)env->GetArrayLength(arr);

    if(data.sampleCount > 0) {
        jshort *samples = env->GetShortArrayElements(arr, 0);
        if(samples) {
            //make a copy of the data - MUST BE FREED BY WHATEVER USES IT!
            sf::Int16 *copy = new sf::Int16[data.sampleCount];
            for(size_t i = 0; i < data.sampleCount; i++)
                copy[i] = (sf::Int16)samples[i];

            data.samples = copy;
            env->ReleaseShortArrayElements(arr, samples, JNI_ABORT);
        } else {
            data.sampleCount = 0;
        }
    }

    return (bool)env->GetBooleanField(chunk, f_last);
}
