#ifndef JSFML_SOUNDRECORDER_H_
#define JSFML_SOUNDRECORDER_H_

#include <jni.h>
#include <SFML/Audio/SoundRecorder.hpp>

namespace JSFML {
    class SoundRecorder : public sf::SoundRecorder {
        private:
            static jclass cls;

            static jmethodID m_onStart;
            static jmethodID m_onProcessSamples;
            static jmethodID m_onStop;

            jobject javaRef;

        protected:
            //overrides
            virtual bool onStart();
            virtual bool onProcessSamples(const sf::Int16 *samples, std::size_t count);
            virtual void onStop();

        public:
            static void Init(JNIEnv* env);

            SoundRecorder(JNIEnv* env, jobject obj);
            ~SoundRecorder();
    };
}

#endif
