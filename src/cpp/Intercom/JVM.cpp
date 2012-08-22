#include <JSFML/Intercom/JVM.hpp>

JavaVM *JVM::jvm = NULL;

void JVM::Init(JNIEnv *env) {
    env->GetJavaVM(&jvm);
}

bool JVM::Attach(JNIEnv **penv) {
   if(jvm) {
       return (jvm->AttachCurrentThread((void **)penv, NULL) == 0);
   } else {
       return false;
   }
}

bool JVM::Detach(JNIEnv **penv) {
    if(jvm) {
        *penv = NULL;
        return (jvm->DetachCurrentThread() == 0);
    } else {
        return false;
    }
}
