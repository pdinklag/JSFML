#include <JSFML/JNI/org_jsfml_SFMLNativeObject.h>
#include <JSFML/Intercom/NativeObject.hpp>

JNIEXPORT void JNICALL Java_org_sfml_SFMLNativeObject_nativeDelete (JNIEnv *env, jobject obj) {
	void* ptr = PTR(void);
	if(ptr) delete ptr;
}
