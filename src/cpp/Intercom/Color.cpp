#include <JSFML/Intercom/Color.hpp>

jclass JSFML::Color::cls = 0;
jmethodID JSFML::Color::ctor = 0;

jfieldID JSFML::Color::f_r = 0;
jfieldID JSFML::Color::f_g = 0;
jfieldID JSFML::Color::f_b = 0;
jfieldID JSFML::Color::f_a = 0;

void JSFML::Color::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/graphics/Color");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(IIII)V");
        f_r = env->GetFieldID(javaClass, "r", "I");
        f_g = env->GetFieldID(javaClass, "g", "I");
        f_b = env->GetFieldID(javaClass, "b", "I");
        f_a = env->GetFieldID(javaClass, "a", "I");
		env->DeleteLocalRef(javaClass);
    }
}

sf::Color JSFML::Color::ToSFML(JNIEnv* env, jobject color) {
    return sf::Color(
        env->GetIntField(color, f_r) & 0xFF,
        env->GetIntField(color, f_g) & 0xFF,
        env->GetIntField(color, f_b) & 0xFF,
        env->GetIntField(color, f_a) & 0xFF);
}

jobject JSFML::Color::FromSFML(JNIEnv* env, const sf::Color& color) {
    return env->NewObject(cls, ctor, color.r, color.g, color.b, color.a);
}
