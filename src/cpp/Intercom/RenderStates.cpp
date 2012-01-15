#include <JSFML/Intercom/RenderStates.hpp>

#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Transform.hpp>

jclass JSFML::RenderStates::cls = 0;

jfieldID JSFML::RenderStates::f_blendMode = 0;
jfieldID JSFML::RenderStates::f_transform = 0;
jfieldID JSFML::RenderStates::f_texture = 0;
jfieldID JSFML::RenderStates::f_shader = 0;

void JSFML::RenderStates::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/graphics/RenderStates");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);

        f_blendMode = env->GetFieldID(javaClass, "blendMode", "Lorg/jsfml/graphics/BlendMode;");
        f_transform = env->GetFieldID(javaClass, "transform", "Lorg/jsfml/graphics/Transform;");
        f_texture = env->GetFieldID(javaClass, "texture", "Lorg/jsfml/graphics/Texture;");
        f_shader = env->GetFieldID(javaClass, "shader", "Lorg/jsfml/graphics/Shader;");
		env->DeleteLocalRef(javaClass);
    }
}

sf::RenderStates JSFML::RenderStates::ToSFML(JNIEnv* env, jobject states) {
    return sf::RenderStates(
        (sf::BlendMode)JavaEnum::ordinal(env, env->GetObjectField(states, f_blendMode)),
        JSFML::Transform::ToSFML(env, env->GetObjectField(states, f_transform)),
        JSFML::NativeObject::GetPointer<sf::Texture>(env, env->GetObjectField(states, f_texture)),
        JSFML::NativeObject::GetPointer<sf::Shader>(env, env->GetObjectField(states, f_texture)));
}
