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

            bool last;
            size_t sampleCount;

            jshortArray dataArray;
            jshort *data;

        public:
            static void Init(JNIEnv* env);

            Chunk(JNIEnv* env, jobject chunk);
            ~Chunk();

            bool GetData(sf::SoundStream::Chunk &chunk);
    };
}

#endif
