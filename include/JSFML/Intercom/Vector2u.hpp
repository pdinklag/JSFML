#ifndef JSFML_VECTOR2U_H_
#define JSFML_VECTOR2U_H_

#include <jni.h>
#include <SFML/System/Vector2.hpp>

namespace JSFML {
    class Vector2u {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_x;
            static jfieldID f_y;

        public:
            static void Init(JNIEnv* env);

            static sf::Vector2u ToSFML(JNIEnv* env, jobject v);
            static jobject FromSFML(JNIEnv* env, const sf::Vector2u& v);
    };
}

#endif
