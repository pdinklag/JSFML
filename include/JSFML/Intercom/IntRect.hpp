#ifndef JSFML_INTRECT_H_
#define JSFML_INTRECT_H_

#include <jni.h>
#include <SFML/Graphics/Rect.hpp>

namespace JSFML {
    class IntRect {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_left;
            static jfieldID f_top;
            static jfieldID f_width;
            static jfieldID f_height;

        public:
            static void Init(JNIEnv* env);

            static sf::IntRect ToSFML(JNIEnv* env, jobject rect);
            static jobject FromSFML(JNIEnv* env, const sf::IntRect& rect);
    };
}

#endif
