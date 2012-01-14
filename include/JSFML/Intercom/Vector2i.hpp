#ifndef JSFML_VECTOR2I_H_
#define JSFML_VECTOR2I_H_

#include <jni.h>
#include <vector>
#include <SFML/System/Vector2.hpp>

namespace JSFML {
    class Vector2i {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_x;
            static jfieldID f_y;

        public:
            static void Init(JNIEnv* env);

            static sf::Vector2i ToSFML(JNIEnv* env, jobject v);
            static jobject FromSFML(JNIEnv* env, const sf::Vector2i& v);
    };
}

#endif
