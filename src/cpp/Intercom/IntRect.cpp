#include <JSFML/Intercom/IntRect.hpp>

jclass JSFML::IntRect::cls = 0;
jmethodID JSFML::IntRect::ctor = 0;

jfieldID JSFML::IntRect::f_left = 0;
jfieldID JSFML::IntRect::f_top = 0;
jfieldID JSFML::IntRect::f_width = 0;
jfieldID JSFML::IntRect::f_height = 0;

void JSFML::IntRect::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/graphics/IntRect");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(IIII)V");
        f_left = env->GetFieldID(javaClass, "left", "I");
        f_top = env->GetFieldID(javaClass, "top", "I");
        f_width = env->GetFieldID(javaClass, "width", "I");
        f_height = env->GetFieldID(javaClass, "height", "I");
		env->DeleteLocalRef(javaClass);
    }
}

sf::IntRect JSFML::IntRect::ToSFML(JNIEnv* env, jobject rect) {
    return sf::IntRect(
        env->GetIntField(rect, f_left),
        env->GetIntField(rect, f_top),
        env->GetIntField(rect, f_width),
        env->GetIntField(rect, f_height));
}

jobject JSFML::IntRect::FromSFML(JNIEnv* env, const sf::IntRect& rect) {
    return env->NewObject(cls, ctor, rect.Left, rect.Top, rect.Width, rect.Height);
}
