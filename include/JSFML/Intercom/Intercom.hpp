#ifndef JSFML_INTERCOM_H_
#define JSFML_INTERCOM_H_

#include <jni.h>
#include <SFML/Graphics/Color.hpp>
#include <SFML/Graphics/Rect.hpp>
#include <SFML/Graphics/Transform.hpp>
#include <SFML/System/String.hpp>
#include <SFML/System/Vector2.hpp>

namespace JSFML {
    class Intercom {
        public:
            //sf::String
            static sf::String decodeUtf8(JNIEnv *env, jstring str);
            static sf::String decodeUtf32(JNIEnv *env, jstring str);
            
            //sf::Color
            static sf::Color decodeColor(jint code);
            
            //sf::IntRect
            static sf::IntRect decodeIntRect(JNIEnv *env, jobject code);
            
            //sf::FloatRect
            static void encodeFloatRect(JNIEnv *env, const sf::FloatRect& rect, jobject out);
            
            //sf::Transform
            static void encodeTransform(JNIEnv *env, const sf::Transform& xform, jobject out);
    };
}

#endif
