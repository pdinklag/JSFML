#ifndef JSFML_NATIVEOBJECT_H_
#define JSFML_NATIVEOBJECT_H_

#include <jni.h>

namespace JSFML {
    class NativeObject {
        public:
            static jfieldID f_ptr;
            static jfieldID f_exPtr;

            static void Init(JNIEnv* env);

            template <typename T> static T* GetPointer(JNIEnv* env, jobject obj) {
                if(obj)
                    return (T*)env->GetLongField(obj, f_ptr);
                else
                    return NULL;
            }

            template <typename T> static T* GetExPointer(JNIEnv* env, jobject obj, int i) {
                T *ptr = NULL;

                if(obj && i >= 0) {
                    jobject buffer = (jlongArray)env->GetObjectField(obj, f_exPtr);
                    if(buffer) {
                        jlong *exPtr = (jlong*)env->GetDirectBufferAddress(buffer);
                        ptr = (T*)exPtr[i];
                        
                        env->DeleteLocalRef(buffer);
                    }
                }

                return ptr;
            }

            static void SetExPointer(JNIEnv* env, jobject obj, int i, void *ptr) {
                if(obj && i >= 0) {
                    jobject buffer = (jlongArray)env->GetObjectField(obj, f_exPtr);
                    if(buffer) {
                        jlong *exPtr = (jlong*)env->GetDirectBufferAddress(buffer);
                        exPtr[i] = (jlong)ptr;
                        
                        env->DeleteLocalRef(buffer);
                    }
                }
            }
    };
}

//Helpers
#define THIS(type) JSFML::NativeObject::GetPointer<type>(env, obj)

#endif
