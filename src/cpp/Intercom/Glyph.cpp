#include <JSFML/Intercom/Glyph.hpp>

#include <JSFML/Intercom/IntRect.hpp>

jclass JSFML::Glyph::cls = 0;
jmethodID JSFML::Glyph::ctor = 0;

void JSFML::Glyph::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/graphics/Glyph");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(ILorg/jsfml/graphics/IntRect;Lorg/jsfml/graphics/IntRect;)V");
		env->DeleteLocalRef(javaClass);
    }
}

jobject JSFML::Glyph::FromSFML(JNIEnv* env, const sf::Glyph& glyph) {
    return env->NewObject(cls, ctor,
        glyph.Advance,
        JSFML::IntRect::FromSFML(env, glyph.Bounds),
        JSFML::IntRect::FromSFML(env, glyph.TextureRect));
}
