#ifndef JSFML_CONTEXTSETTINGS_H_
#define JSFML_CONTEXTSETTINGS_H_

#include <jni.h>
#include <SFML/Window/ContextSettings.hpp>

namespace JSFML {
    class ContextSettings {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_depth;
            static jfieldID f_stencil;
            static jfieldID f_aa;
            static jfieldID f_major;
            static jfieldID f_minor;

        public:
            static void Init(JNIEnv* env);
            
            static sf::ContextSettings ToSFML(JNIEnv* env, jobject contextSettings);
            static jobject FromSFML(JNIEnv* env, const sf::ContextSettings& contextSettings);
    };
}

#endif
