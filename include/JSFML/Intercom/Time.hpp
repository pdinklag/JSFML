#ifndef JSFML_TIME_H_
#define JSFML_TIME_H_

#include <jni.h>
#include <SFML/System/Time.hpp>

namespace JSFML {
    class Time {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_microseconds;

        public:
            static void Init(JNIEnv* env);

            static sf::Time ToSFML(JNIEnv* env, jobject time);
            static jobject FromSFML(JNIEnv* env, const sf::Time& time);
    };
}

#endif
