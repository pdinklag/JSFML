#include <JSFML/JNI/org_jsfml_SFMLNative.h>

#include <JSFML/Intercom/JavaEnum.hpp>

#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/Intercom/Vector2i.hpp>
#include <JSFML/Intercom/Vector2f.hpp>
#include <JSFML/Intercom/Vector3f.hpp>

#include <JSFML/Intercom/ContextSettings.hpp>
#include <JSFML/Intercom/Event.hpp>
#include <JSFML/Intercom/VideoMode.hpp>

#include <JSFML/Intercom/Color.hpp>

JNIEXPORT void JNICALL Java_org_jsfml_SFMLNative_nativeInit (JNIEnv *env, jclass cls) {
    //initialize intercom types
    JavaEnum::Init(env);

    JSFML::NativeObject::Init(env);

    JSFML::Vector2i::Init(env);
    JSFML::Vector2f::Init(env);
    JSFML::Vector3f::Init(env);

    JSFML::ContextSettings::Init(env);
    JSFML::Event::Init(env);
    JSFML::VideoMode::Init(env);

    JSFML::Color::Init(env);
}
