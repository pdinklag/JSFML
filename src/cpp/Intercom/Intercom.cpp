#include <JSFML/Intercom/Intercom.hpp>
#include <SFML/Config.hpp>

std::wstring JSFML::Intercom::decodeUtf32(JNIEnv *env, jstring jstr) {
    const jchar *jchars = env->GetStringChars(jstr, NULL);
    std::wstring s;

    #if defined(SFML_SYSTEM_WINDOWS)
        //sizeof(wchar_t) == 2
        s = std::wstring((const wchar_t*)jchars);
    #else
        //sizeof(wchar_t) != 2
        jsize len = env->GetStringLength(jstr);
        wchar_t *buffer = new wchar_t[len + 1];
        
        buffer[len] = 0;
        for(jsize i = 0; i < len; i++) {
            buffer[i] = (wchar_t)jchars[i];
        }
        
        s = std::wstring(buffer);
        delete[] buffer;
    #endif
    
    env->ReleaseStringChars(jstr, jchars);
    return s;
}

sf::Color JSFML::Intercom::decodeColor(jint code) {
    sf::Uint8 a = (code >> 24) & 0xFF;
    sf::Uint8 b = (code >> 16) & 0xFF;
    sf::Uint8 g = (code >> 8) & 0xFF;
    sf::Uint8 r = code & 0xFF;
    return sf::Color(r, g, b, a);
}

void JSFML::Intercom::encodeIntRect(JNIEnv *env, const sf::IntRect& rect, jobject out) {
    jint *buf = (jint*)env->GetDirectBufferAddress(out);
    buf[0] = rect.left;
    buf[1] = rect.top;
    buf[2] = rect.width;
    buf[3] = rect.height;
}

sf::IntRect JSFML::Intercom::decodeIntRect(JNIEnv *env, jobject code) {
    jint *buf = (jint*)env->GetDirectBufferAddress(code);
    return sf::IntRect(buf[0], buf[1], buf[2], buf[3]);
}

void JSFML::Intercom::encodeFloatRect(JNIEnv *env, const sf::FloatRect& rect, jobject out) {
    jfloat *buf = (jfloat*)env->GetDirectBufferAddress(out);
    buf[0] = rect.left;
    buf[1] = rect.top;
    buf[2] = rect.width;
    buf[3] = rect.height;
}

sf::FloatRect JSFML::Intercom::decodeFloatRect(JNIEnv *env, jobject code) {
    jint *buf = (jint*)env->GetDirectBufferAddress(code);
    return sf::FloatRect(buf[0], buf[1], buf[2], buf[3]);
}

void JSFML::Intercom::encodeTransform(JNIEnv *env, const sf::Transform& xform, jobject out) {
    jfloat *buf = (jfloat*)env->GetDirectBufferAddress(out);
    const float *data = xform.getMatrix();
    
    buf[0] = data[0];
    buf[1] = data[4];
    buf[2] = data[12];
    buf[3] = data[1];
    buf[4] = data[5];
    buf[5] = data[13];
    buf[6] = data[3];
    buf[7] = data[7];
    buf[8] = data[15];
}

sf::Transform JSFML::Intercom::decodeTransform(JNIEnv *env, jobject code) {
    jfloat *buf = (jfloat*)env->GetDirectBufferAddress(code);
    return sf::Transform(
        buf[0], buf[1], buf[2],
        buf[3], buf[4], buf[5],
        buf[6], buf[7], buf[8]);
}

jlong JSFML::Intercom::encodeVector2i(const sf::Vector2i& v) {
    jlong vec = (jlong)v.y << 32;
    vec |= v.x;
    return vec;
}

jlong JSFML::Intercom::encodeVector2u(const sf::Vector2u& v) {
    jlong vec = (jlong)v.y << 32;
    vec |= v.x;
    return vec;
}

jlong JSFML::Intercom::encodeVector2f(const sf::Vector2f& v) {
    int x, y;
    *((jfloat*)&x) = v.x;
    *((jfloat*)&y) = v.y;
    
    jlong vec = (jlong)y << 32;
    vec |= x;
    return vec;
}

sf::Vector2i JSFML::Intercom::decodeVector2i(jlong v) {
    return sf::Vector2i((int)(v & 0xFFFFFFFF), (int)(v >> 32));
}

sf::Vector2f JSFML::Intercom::decodeVector2f(jlong v) {
    int x = v & 0xFFFFFFFF;
    int y = v >> 32;
    return sf::Vector2f(*((jfloat*)&x), *((jfloat*)&y));
}
