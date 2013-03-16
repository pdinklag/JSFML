#ifndef JSFML_SOUNDSTREAM_H_
#define JSFML_SOUNDSTREAM_H_

#include <jni.h>
#include <SFML/Audio/SoundStream.hpp>

namespace JSFML {
    class SoundStream : public sf::SoundStream {
        private:
            static jclass cls;

            static jmethodID m_onGetData;
            static jmethodID m_onSeek;

            jobject javaRef;        //global ref to the org.jsfml.audio.SoundStream
            jobject currentSamples; //global ref to a java.nio.Buffer holding the currently playing chunk data

        protected:
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
