#ifndef JSFML_SOUNDSTREAM_H_
#define JSFML_SOUNDSTREAM_H_

#include <jni.h>
#include <JSFML/Intercom/Chunk.hpp>
#include <SFML/Audio/SoundStream.hpp>

namespace JSFML {
    class SoundStream : public sf::SoundStream {
        private:
            static jclass cls;

            static jmethodID m_onGetData;
            static jmethodID m_onSeek;

            jobject javaRef;
            JSFML::Chunk *buffer;

        protected:
            void clearBuffer();

            //overrides
            virtual bool onGetData(sf::SoundStream::Chunk& data);
            virtual void onSeek(sf::Time timeOffset);

        public:
            static void Init(JNIEnv* env);

            void initialize(unsigned int channelCount, unsigned int sampleRate);

            SoundStream(JNIEnv* env, jobject obj);
            ~SoundStream();
    };
}

#endif
