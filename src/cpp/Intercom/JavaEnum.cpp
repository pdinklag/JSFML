#include <JSFML/Intercom/JavaEnum.hpp>

jfieldID JavaEnum::f_ordinal = 0;

void JavaEnum::Init(JNIEnv* env) {
    jclass cls = env->FindClass("java/lang/Enum");
    if(cls) {
        JavaEnum::f_ordinal = env->GetFieldID(cls, "ordinal", "I");
		env->DeleteLocalRef(cls);
    }
}

int JavaEnum::ordinal(JNIEnv* env, jobject x) {
    return env->GetIntField(x, JavaEnum::f_ordinal);
}
