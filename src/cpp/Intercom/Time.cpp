#include <JSFML/Intercom/Time.hpp>

jclass JSFML::Time::cls = 0;
jmethodID JSFML::Time::ctor = 0;

jfieldID JSFML::Time::f_microseconds = 0;

void JSFML::Time::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/system/Time");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(J)V");
        f_microseconds = env->GetFieldID(javaClass, "microseconds", "J");
		env->DeleteLocalRef(javaClass);
    }
}

sf::Time JSFML::Time::ToSFML(JNIEnv* env, jobject time) {
    return sf::Microseconds(env->GetLongField(time, f_microseconds));
}

jobject JSFML::Time::FromSFML(JNIEnv* env, const sf::Time& time) {
    return env->NewObject(cls, ctor, (jlong)time.AsMicroseconds());
}
