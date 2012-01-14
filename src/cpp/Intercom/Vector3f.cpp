#include <JSFML/Intercom/Vector3f.hpp>

jclass JSFML::Vector3f::cls = 0;
jmethodID JSFML::Vector3f::ctor = 0;

jfieldID JSFML::Vector3f::f_x = 0;
jfieldID JSFML::Vector3f::f_y = 0;
jfieldID JSFML::Vector3f::f_z = 0;

void JSFML::Vector3f::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/system/Vector3f");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(FFF)V");
        f_x = env->GetFieldID(javaClass, "x", "F");
        f_y = env->GetFieldID(javaClass, "y", "F");
        f_z = env->GetFieldID(javaClass, "z", "F");
		env->DeleteLocalRef(javaClass);
    }
}

sf::Vector3f JSFML::Vector3f::ToSFML(JNIEnv* env, jobject v) {
    return sf::Vector3f(
        env->GetFloatField(v, f_x),
        env->GetFloatField(v, f_y),
        env->GetFloatField(v, f_z));
}

jobject JSFML::Vector3f::FromSFML(JNIEnv* env, const sf::Vector3f& v) {
    return env->NewObject(cls, ctor, v.x, v.y, v.z);
}
