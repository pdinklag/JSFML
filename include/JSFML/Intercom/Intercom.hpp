#ifndef JSFML_INTERCOM_H_
#define JSFML_INTERCOM_H_

#include <jni.h>
#include <SFML/Graphics/Color.hpp>
#include <SFML/Graphics/Rect.hpp>
#include <SFML/System/String.hpp>

namespace JSFML {
    class Intercom {
        public:
            //sf::String
            static sf::String decodeUtf8(JNIEnv *env, jstring str);
            static sf::String decodeUtf32(JNIEnv *env, jstring str);
            
            //sf::Color
            static sf::Color decodeColor(JNIEnv *env, jint code);
            static jint encodeColor(JNIEnv *env, const sf::Color& color);
            
            //sf::IntRect
            static sf::IntRect decodeIntRect(JNIEnv *env, jobject code);
            static void encodeIntRect(JNIEnv *env, const sf::IntRect& rect, jobject out);
            
            //sf::FloatRect
            static sf::FloatRect decodeFloatRect(JNIEnv *env, jobject code);
            static void encodeFloatRect(JNIEnv *env, const sf::FloatRect& rect, jobject out);
    };
}

#endif
