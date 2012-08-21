#ifndef JVM_H_
#define JVM_H_

#include <jni.h>

class JVM {
    private:
        static JavaVM *jvm;

    public:
        static void Init(JNIEnv *env);
        static JNIEnv *GetJNIEnv();
};

#endif
