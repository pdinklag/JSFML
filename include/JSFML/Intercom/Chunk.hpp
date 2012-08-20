#ifndef JSFML_CHUNK_H_
#define JSFML_CHUNK_H_

#include <jni.h>
#include <SFML/Audio/SoundStream.hpp>

namespace JSFML {
    class Chunk {
        private:
            static jclass cls;

            static jfieldID f_data;
            static jfieldID f_last;

        public:
            static void Init(JNIEnv* env);

            static bool GetData(JNIEnv* env, jobject chunk, sf::SoundStream::Chunk &data);
    };
}

#endif
