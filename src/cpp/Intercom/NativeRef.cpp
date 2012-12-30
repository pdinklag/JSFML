#include <JSFML/Intercom/NativeRef.hpp>

jfieldID JSFML::NativeRef::f_ptr = 0;

void JSFML::NativeRef::Init(JNIEnv* env) {
    jclass cls = env->FindClass("org/jsfml/internal/NativeRef");
    if(cls) {
        f_ptr = env->GetFieldID(cls, "ptr", "J");
        env->DeleteLocalRef(cls);
    }
}
