#ifndef JSFML_COLOR_H_
#define JSFML_COLOR_H_

#include <jni.h>
#include <vector>
#include <SFML/Graphics/Color.hpp>

namespace JSFML {
    class Color {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_r;
            static jfieldID f_g;
            static jfieldID f_b;
            static jfieldID f_a;

        public:
            static void Init(JNIEnv* env);

            static sf::Color ToSFML(JNIEnv* env, jobject color);
            static jobject FromSFML(JNIEnv* env, const sf::Color& color);
    };
}

#endif
