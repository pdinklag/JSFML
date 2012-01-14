#ifndef JAVAENUM_H_
#define JAVAENUM_H_

#include <jni.h>

class JavaEnum {
    private:
        static jfieldID f_ordinal;

    public:
        static void Init(JNIEnv* env);

        static int ordinal(JNIEnv* env, jobject x);
};

#endif
