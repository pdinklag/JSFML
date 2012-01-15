#include <JSFML/JNI/org_jsfml_SFMLNativeObject.h>
#include <JSFML/Intercom/NativeObject.hpp>

/*
 * Class:     org_jsfml_SFMLNativeObject
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_sfml_SFMLNativeObject_nativeDelete (JNIEnv *env, jobject obj) {
	void* ptr = THIS(void);
	if(ptr) delete ptr;
}
