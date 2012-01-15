#ifndef JSFML_FLOATRECT_H_
#define JSFML_FLOATRECT_H_

#include <jni.h>
#include <SFML/Graphics/Rect.hpp>

namespace JSFML {
    class FloatRect {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_left;
            static jfieldID f_top;
            static jfieldID f_width;
            static jfieldID f_height;

        public:
            static void Init(JNIEnv* env);

            static sf::FloatRect ToSFML(JNIEnv* env, jobject rect);
            static jobject FromSFML(JNIEnv* env, const sf::FloatRect& rect);
    };
}

#endif
