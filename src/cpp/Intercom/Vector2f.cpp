#include <JSFML/Intercom/Vector2f.hpp>

jclass JSFML::Vector2f::cls = 0;
jmethodID JSFML::Vector2f::ctor = 0;

jfieldID JSFML::Vector2f::f_x = 0;
jfieldID JSFML::Vector2f::f_y = 0;

void JSFML::Vector2f::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/system/Vector2f");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(FF)V");
        f_x = env->GetFieldID(javaClass, "x", "F");
        f_y = env->GetFieldID(javaClass, "y", "F");
		env->DeleteLocalRef(javaClass);
    }
}

sf::Vector2f JSFML::Vector2f::ToSFML(JNIEnv* env, jobject v) {
    return sf::Vector2f(
        env->GetFloatField(v, f_x),
        env->GetFloatField(v, f_y));
}

jobject JSFML::Vector2f::FromSFML(JNIEnv* env, const sf::Vector2f& v) {
    return env->NewObject(cls, ctor, v.x, v.y);
}
