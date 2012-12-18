#ifndef JSFML_NATIVEREF_H_
#define JSFML_NATIVEREF_H_

#include <jni.h>

namespace JSFML {
    class NativeRef {
        private:
            static jclass cls;
            static jfieldID f_ptr;
    
        public:
            static void Init(JNIEnv* env);

            template <typename T> static T* GetPointer(JNIEnv* env, jobject obj) {
                if(obj)
                    return (T*)env->GetLongField(obj, f_ptr);
                else
                    return NULL;
            }
    };
}

#endif
