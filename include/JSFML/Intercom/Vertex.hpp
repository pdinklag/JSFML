#ifndef JSFML_VERTEX_H_
#define JSFML_VERTEX_H_

#include <jni.h>
#include <SFML/Graphics/Vertex.hpp>

namespace JSFML {
    class Vertex {
        private:
            static jclass cls;
            static jmethodID ctor;

            static jfieldID f_position;
            static jfieldID f_color;
            static jfieldID f_texCoords;

        public:
            static void Init(JNIEnv* env);

            static sf::Vertex ToSFML(JNIEnv* env, jobject vertex);
            static jobject FromSFML(JNIEnv* env, const sf::Vertex& vertex);
    };
}

#endif
