#ifndef JSFML_RENDERSTATES_H_
#define JSFML_RENDERSTATES_H_

#include <jni.h>
#include <SFML/Graphics/RenderStates.hpp>

namespace JSFML {
    class RenderStates {
        private:
            static jclass cls;

            static jfieldID f_blendMode;
            static jfieldID f_transform;
            static jfieldID f_texture;
            static jfieldID f_shader;

        public:
            static void Init(JNIEnv* env);

            static sf::RenderStates ToSFML(JNIEnv* env, jobject states);
    };
}

#endif
