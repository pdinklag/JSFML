#ifndef JSFML_NATIVEOBJECT_H_
#define JSFML_NATIVEOBJECT_H_

#include <jni.h>

namespace JSFML {
    class NativeObject {
        public:
            static jfieldID f_ptr;

            static void Init(JNIEnv* env);

            template <typename T> static T* GetPointer(JNIEnv* env, jobject obj) {
                if(obj)
                    return (T*)env->GetLongField(obj, f_ptr);
                else
                    return NULL;
            }
    };
}

//Helpers
#define PTR(type) JSFML::NativeObject::GetPointer<type>(env, obj)

#endif
