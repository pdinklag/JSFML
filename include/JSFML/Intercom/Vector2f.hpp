#ifndef JSFML_VECTOR2F_H_
#define JSFML_VECTOR2F_H_

#include <jni.h>
#include <SFML/System/Vector2.hpp>

namespace JSFML {
    class Vector2f {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_x;
            static jfieldID f_y;

        public:
            static void Init(JNIEnv* env);

            static sf::Vector2f ToSFML(JNIEnv* env, jobject v);
            static jobject FromSFML(JNIEnv* env, const sf::Vector2f& v);
    };
}

#endif
