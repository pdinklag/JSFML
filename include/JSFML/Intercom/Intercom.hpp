#ifndef JSFML_INTERCOM_H_
#define JSFML_INTERCOM_H_

#include <jni.h>
#include <SFML/System/String.hpp>

namespace JSFML {
    class Intercom {
        public:
            static sf::String decodeUtf8(JNIEnv *env, jstring str);
            static sf::String decodeUtf32(JNIEnv *env, jstring str);
    };
}

#endif
