#ifndef JSFML_VIDEOMODE_H_
#define JSFML_VIDEOMODE_H_

#include <jni.h>
#include <vector>
#include <SFML/Window/VideoMode.hpp>

namespace JSFML {
    class VideoMode {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_width;
            static jfieldID f_height;
            static jfieldID f_bpp;

            static std::vector<sf::VideoMode> videoModes;

        public:
            static void Init(JNIEnv* env);

            static jobject GetDesktopMode(JNIEnv* env);
            static jobjectArray GetFullscreenModes(JNIEnv* env);

            static sf::VideoMode ToSFML(JNIEnv* env, jobject videoMode);
    };
}

#endif
