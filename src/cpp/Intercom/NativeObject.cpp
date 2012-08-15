#include <JSFML/Intercom/NativeObject.hpp>

jfieldID JSFML::NativeObject::f_ptr = 0;
jfieldID JSFML::NativeObject::f_exPtr = 0;

void JSFML::NativeObject::Init(JNIEnv* env) {
    jclass cls = env->FindClass("org/jsfml/SFMLNativeObject");
    if(cls) {
        f_ptr = env->GetFieldID(cls, "ptr", "J");
        f_exPtr = env->GetFieldID(cls, "exPtr", "[J");
        env->DeleteLocalRef(cls);
    }
}
