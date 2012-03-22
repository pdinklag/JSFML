#include <JSFML/Intercom/ContextSettings.hpp>

jclass JSFML::ContextSettings::cls = 0;
jmethodID JSFML::ContextSettings::ctor = 0;

jfieldID JSFML::ContextSettings::f_depth = 0;
jfieldID JSFML::ContextSettings::f_stencil = 0;
jfieldID JSFML::ContextSettings::f_aa = 0;
jfieldID JSFML::ContextSettings::f_major = 0;
jfieldID JSFML::ContextSettings::f_minor = 0;

void JSFML::ContextSettings::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/window/ContextSettings");
    if(javaClass) {
		cls = (jclass)env->NewGlobalRef(javaClass);
		ctor = env->GetMethodID(javaClass, "<init>", "(IIIII)V");
        f_depth = env->GetFieldID(javaClass, "depthBits", "I");
        f_stencil = env->GetFieldID(javaClass, "stencilBits", "I");
        f_aa = env->GetFieldID(javaClass, "antialiasingLevel", "I");
        f_major = env->GetFieldID(javaClass, "majorVersion", "I");
        f_minor = env->GetFieldID(javaClass, "minorVersion", "I");
		env->DeleteLocalRef(javaClass);
    }
}

sf::ContextSettings JSFML::ContextSettings::ToSFML(JNIEnv* env, jobject contextSettings) {
	return sf::ContextSettings(
		env->GetIntField(contextSettings, f_depth),
		env->GetIntField(contextSettings, f_stencil),
		env->GetIntField(contextSettings, f_aa),
		env->GetIntField(contextSettings, f_major),
		env->GetIntField(contextSettings, f_minor));
}

jobject JSFML::ContextSettings::FromSFML(JNIEnv* env, const sf::ContextSettings& context) {
	return env->NewObject(cls, ctor,
        context.depthBits,
        context.stencilBits,
        context.antialiasingLevel,
        context.majorVersion,
        context.minorVersion);
}
