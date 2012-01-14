#ifndef JAVASTRING_H_
#define JAVASTRING_H_

#include <jni.h>

#define JSTRING_MAX_LEN 1024

class JavaString {
    public:
        static jfieldID f_ordinal;

        static const wchar_t* getUnicode(JNIEnv* env, jstring str);
        static const char* getUTF8(JNIEnv* env, jstring str);
};

#endif
