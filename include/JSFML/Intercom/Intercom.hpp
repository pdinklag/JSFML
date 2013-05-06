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
            //std::wstring
            static std::wstring decodeUtf32(JNIEnv *env, jstring str);
            
            //sf::Color
            static sf::Color decodeColor(jint code);
            
            //sf::Vector2
            static jlong encodeVector2i(const sf::Vector2i& v);
            static jlong encodeVector2u(const sf::Vector2u& v);
            static jlong encodeVector2f(const sf::Vector2f& v);
            
            static sf::Vector2i decodeVector2i(jlong v);
            static sf::Vector2f decodeVector2f(jlong v);
            
            //sf::IntRect
            static void encodeIntRect(JNIEnv *env, const sf::IntRect& rect, jobject out);
            static sf::IntRect decodeIntRect(JNIEnv *env, jobject code);
            
            //sf::FloatRect
            static void encodeFloatRect(JNIEnv *env, const sf::FloatRect& rect, jobject out);
            static sf::FloatRect decodeFloatRect(JNIEnv *env, jobject code);
            
            //sf::Transform
            static void encodeTransform(JNIEnv *env, const sf::Transform& xform, jobject out);
            static sf::Transform decodeTransform(JNIEnv *env, jobject code);
    };
}

#endif
