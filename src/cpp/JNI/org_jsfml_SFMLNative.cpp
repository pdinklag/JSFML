#include <JSFML/JNI/org_jsfml_SFMLNative.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector2i.hpp>
#include <JSFML/Intercom/Vector2f.hpp>
#include <JSFML/Intercom/Vector3f.hpp>

JNIEXPORT void JNICALL Java_org_jsfml_SFMLNative_nativeInit (JNIEnv *env, jclass cls) {
    //initialize intercom types
    JSFML::NativeObject::Init(env);

    JSFML::Vector2i::Init(env);
    JSFML::Vector2f::Init(env);
    JSFML::Vector3f::Init(env);
}
