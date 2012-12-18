#ifndef JSFML_INPUTSTREAM_H_
#define JSFML_INPUTSTREAM_H_

#include <jni.h>
#include <SFML/System/InputStream.hpp>

namespace JSFML {
    class InputStream : public sf::InputStream {
        private:
            static jclass cls;

            static jmethodID m_read;
            static jmethodID m_seek;
            static jmethodID m_tell;
            static jmethodID m_getSize;
            static jmethodID m_close;

            jobject javaRef;

        public:
            static void Init(JNIEnv* env);

            InputStream(JNIEnv*, jobject);

            //overrides
            virtual ~InputStream();
            
            virtual sf::Int64 read(void*, sf::Int64);
            virtual sf::Int64 seek(sf::Int64);
            virtual sf::Int64 tell();
            virtual sf::Int64 getSize();
    };
}

#endif
