#ifndef JSFML_VECTOR3F_H_
#define JSFML_VECTOR3F_H_

#include <jni.h>
#include <SFML/System/Vector3.hpp>

namespace JSFML {
    class Vector3f {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_x;
            static jfieldID f_y;
            static jfieldID f_z;

        public:
            static void Init(JNIEnv* env);

            static sf::Vector3f ToSFML(JNIEnv* env, jobject v);
            static jobject FromSFML(JNIEnv* env, const sf::Vector3f& v);
    };
}

#endif
