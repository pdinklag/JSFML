#include <JSFML/JNI/org_jsfml_SFMLNative.h>

#include <JSFML/Intercom/NativeObject.hpp>

JNIEXPORT void JNICALL Java_org_jsfml_SFMLNative_nativeInit (JNIEnv *env, jclass cls) {
    //initialize intercom types
    JSFML::NativeObject::Init(env);
}
