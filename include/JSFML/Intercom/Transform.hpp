#ifndef JSFML_TRANSFORM_H_
#define JSFML_TRANSFORM_H_

#include <jni.h>
#include <SFML/Graphics/Transform.hpp>

namespace JSFML {
    class Transform {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_data;

        public:
            static void Init(JNIEnv* env);

            static sf::Transform ToSFML(JNIEnv* env, jobject transform);
            static jobject FromSFML(JNIEnv* env, const sf::Transform& transform);
    };
}

#endif
