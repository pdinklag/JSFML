#ifndef JSFML_GLYPH_H_
#define JSFML_GLYPH_H_

#include <jni.h>
#include <SFML/Graphics/Glyph.hpp>

namespace JSFML {
    class Glyph {
        private:
            static jclass cls;
            static jmethodID ctor;

        public:
            static void Init(JNIEnv* env);

            static jobject FromSFML(JNIEnv* env, const sf::Glyph& glyph);
    };
}

#endif
