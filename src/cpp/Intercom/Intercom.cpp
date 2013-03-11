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
