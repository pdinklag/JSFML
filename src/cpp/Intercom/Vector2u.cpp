#include <JSFML/Intercom/Vector2u.hpp>

jclass JSFML::Vector2u::cls = 0;
jmethodID JSFML::Vector2u::ctor = 0;

jfieldID JSFML::Vector2u::f_x = 0;
jfieldID JSFML::Vector2u::f_y = 0;

void JSFML::Vector2u::Init(JNIEnv* env) {
	/*
		NOTE: There are no unsigned integers in Java, therefore this class simply maps from and to Vector2i.
	*/
    jclass javaClass = env->FindClass("org/jsfml/system/Vector2i");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(II)V");
        f_x = env->GetFieldID(javaClass, "x", "I");
        f_y = env->GetFieldID(javaClass, "y", "I");
		env->DeleteLocalRef(javaClass);
    }
}

sf::Vector2u JSFML::Vector2u::ToSFML(JNIEnv* env, jobject v) {
    return sf::Vector2u(
        env->GetIntField(v, f_x),
        env->GetIntField(v, f_y));
}

jobject JSFML::Vector2u::FromSFML(JNIEnv* env, const sf::Vector2u& v) {
    return env->NewObject(cls, ctor, v.x, v.y);
}
