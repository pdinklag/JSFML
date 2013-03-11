#include <JSFML/Intercom/Intercom.hpp>
#include <SFML/Config.hpp>

sf::String JSFML::Intercom::decodeUtf8(JNIEnv *env, jstring str) {
    const char *chars = env->GetStringUTFChars(str, NULL);
    sf::String sf = sf::String((const char*)chars);
    env->ReleaseStringUTFChars(str, chars);
    
    return sf;
}

sf::String JSFML::Intercom::decodeUtf32(JNIEnv *env, jstring str) {
    const jchar *chars = env->GetStringChars(str, NULL);
    sf::String sf;

    #if defined(SFML_SYSTEM_WINDOWS)
        //sizeof(wchar_t) == 2
        sf = sf::String((const wchar_t*)chars);
    #else
        //sizeof(wchar_t) != 2
        jsize len = env->GetStringLength(str);
        wchar_t *buffer = new wchar_t[len + 1];
        
        buffer[len] = 0;
        for(jsize i = 0; i < len; i++) {
            buffer[i] = (wchar_t)chars[i];
        }
        
        sf = sf::String(buffer);
        delete[] buffer;
    #endif
    
    env->ReleaseStringChars(str, chars);
    return sf;
}

sf::Color JSFML::Intercom::decodeColor(JNIEnv *env, jint code) {
    sf::Uint8 r = (code >> 24) & 0xFF;
    sf::Uint8 g = (code >> 16) & 0xFF;
    sf::Uint8 b = (code >> 8) & 0xFF;
    sf::Uint8 a = code & 0xFF;
    return sf::Color(r, g, b, a);
}

jint JSFML::Intercom::encodeColor(JNIEnv *env, const sf::Color& color) {
    return (color.r << 24) | (color.g << 16) | (color.b << 8) | color.a;
}

sf::IntRect JSFML::Intercom::decodeIntRect(JNIEnv *env, jobject code) {
    jint *buf = (jint*)env->GetDirectBufferAddress(code);
    return sf::IntRect(buf[0], buf[1], buf[2], buf[3]);
}

void JSFML::Intercom::encodeIntRect(JNIEnv *env, const sf::IntRect& rect, jobject out) {
    jint *buf = (jint*)env->GetDirectBufferAddress(out);
    buf[0] = rect.left;
    buf[1] = rect.top;
    buf[2] = rect.width;
    buf[3] = rect.height;
}

sf::FloatRect JSFML::Intercom::decodeFloatRect(JNIEnv *env, jobject code) {
    jfloat *buf = (jfloat*)env->GetDirectBufferAddress(code);
    return sf::FloatRect(buf[0], buf[1], buf[2], buf[3]);
}

void JSFML::Intercom::encodeFloatRect(JNIEnv *env, const sf::FloatRect& rect, jobject out) {
    jfloat *buf = (jfloat*)env->GetDirectBufferAddress(out);
    buf[0] = rect.left;
    buf[1] = rect.top;
    buf[2] = rect.width;
    buf[3] = rect.height;
}
