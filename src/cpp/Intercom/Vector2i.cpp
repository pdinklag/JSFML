#include <JSFML/Intercom/Vector2i.hpp>

jclass JSFML::Vector2i::cls = 0;
jmethodID JSFML::Vector2i::ctor = 0;

jfieldID JSFML::Vector2i::f_x = 0;
jfieldID JSFML::Vector2i::f_y = 0;

void JSFML::Vector2i::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/system/Vector2i");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(II)V");
        f_x = env->GetFieldID(javaClass, "x", "I");
        f_y = env->GetFieldID(javaClass, "y", "I");
		env->DeleteLocalRef(javaClass);
    }
}

sf::Vector2i JSFML::Vector2i::ToSFML(JNIEnv* env, jobject v) {
    return sf::Vector2i(
        env->GetIntField(v, f_x),
        env->GetIntField(v, f_y));
}

jobject JSFML::Vector2i::FromSFML(JNIEnv* env, const sf::Vector2i& v) {
    return env->NewObject(cls, ctor, v.x, v.y);
}
