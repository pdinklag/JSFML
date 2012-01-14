#include <JSFML/Intercom/JavaString.hpp>
#include <SFML/Config.hpp>

wchar_t unicodeBuffer[JSTRING_MAX_LEN];
char utf8Buffer[JSTRING_MAX_LEN];

#if defined(SFML_SYSTEM_LINUX) || defined(SFML_SYSTEM_MACOS)
    //For systems with sizeof(wchar_t) = 4, we need an extra buffer
    jchar javaStringBuffer[JSTRING_MAX_LEN];
#endif

const wchar_t* JavaString::getUnicode(JNIEnv *env, jstring str) {
    jsize len = env->GetStringLength(str);

    #if defined(SFML_SYSTEM_WINDOWS)
        //sizeof(wchar_t) = 2
        env->GetStringRegion(str, 0, len, (jchar*)unicodeBuffer);
    #elif defined(SFML_SYSTEM_LINUX) || defined(SFML_SYSTEM_MACOS)
        //sizeof(wchar_t) = 4
        len = env->GetStringLength(str);
        env->GetStringRegion(str, 0, len, javaStringBuffer);

        for(jsize i = 0; i < len; i++)
            unicodeBuffer[i] = javaStringBuffer[i];
    #endif

    unicodeBuffer[len] = 0;
    return unicodeBuffer;
}

const char* JavaString::getUTF8(JNIEnv *env, jstring str) {
    jsize len = env->GetStringUTFLength(str);
    env->GetStringUTFRegion(str, 0, len, utf8Buffer);
    utf8Buffer[len] = 0;
    return utf8Buffer;
}
