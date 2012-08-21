#include <JSFML/Intercom/JVM.hpp>

JavaVM *JVM::jvm = NULL;

void JVM::Init(JNIEnv *env) {
    env->GetJavaVM(&jvm);
}

JNIEnv* JVM::GetJNIEnv() {
   if(jvm) {
       JNIEnv *env = NULL;
       jvm->AttachCurrentThread((void **)&env, NULL);

       return env;
   } else {
       return NULL;
   }
}
