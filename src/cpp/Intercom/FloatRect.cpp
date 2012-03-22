#include <JSFML/Intercom/FloatRect.hpp>

jclass JSFML::FloatRect::cls = 0;
jmethodID JSFML::FloatRect::ctor = 0;

jfieldID JSFML::FloatRect::f_left = 0;
jfieldID JSFML::FloatRect::f_top = 0;
jfieldID JSFML::FloatRect::f_width = 0;
jfieldID JSFML::FloatRect::f_height = 0;

void JSFML::FloatRect::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/graphics/FloatRect");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(FFFF)V");
        f_left = env->GetFieldID(javaClass, "left", "F");
        f_top = env->GetFieldID(javaClass, "top", "F");
        f_width = env->GetFieldID(javaClass, "width", "F");
        f_height = env->GetFieldID(javaClass, "height", "F");
		env->DeleteLocalRef(javaClass);
    }
}

sf::FloatRect JSFML::FloatRect::ToSFML(JNIEnv* env, jobject rect) {
    return sf::FloatRect(
        env->GetFloatField(rect, f_left),
        env->GetFloatField(rect, f_top),
        env->GetFloatField(rect, f_width),
        env->GetFloatField(rect, f_height));
}

jobject JSFML::FloatRect::FromSFML(JNIEnv* env, const sf::FloatRect& rect) {
    return env->NewObject(cls, ctor, rect.left, rect.top, rect.width, rect.height);
}
