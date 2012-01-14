#include <JSFML/Intercom/NativeObject.hpp>

jfieldID JSFML::NativeObject::f_ptr = 0;

void JSFML::NativeObject::Init(JNIEnv* env) {
    jclass cls = env->FindClass("org/sfml/SFMLNativeObject");
    if(cls) {
        f_ptr = env->GetFieldID(cls, "ptr", "J");
        env->DeleteLocalRef(cls);
    }
}
