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
                if(obj && i >= 0) {
                    jlongArray jarray = (jlongArray)env->GetObjectField(obj, f_exPtr);
                    if(jarray) {
                        int length = env->GetArrayLength(jarray);
                        if(i < length) {
                            jlong *array = env->GetLongArrayElements(jarray, NULL);
                            T *ptr = (T*)array[i];

                            env->ReleaseLongArrayElements(jarray, array, JNI_ABORT);
                            env->DeleteLocalRef(jarray);

                            return ptr;
                        }
                    }
                }

                return NULL;
            }
    };
}

//Helpers
#define THIS(type) JSFML::NativeObject::GetPointer<type>(env, obj)

#endif
