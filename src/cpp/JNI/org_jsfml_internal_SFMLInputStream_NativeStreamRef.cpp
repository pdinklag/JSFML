#include <JSFML/JNI/org_jsfml_internal_SFMLInputStream_NativeStreamRef.h>
#include <JSFML/Intercom/InputStream.hpp>

/*
 * Class:     org_jsfml_internal_SFMLInputStream_NativeStreamRef
 * Method:    nativeInitialize
 * Signature: (Lorg/jsfml/internal/SFMLInputStream;)J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_internal_SFMLInputStream_00024NativeStreamRef_nativeInitialize
  (JNIEnv *env, jobject obj, jobject ref) {

	JSFML::InputStream *stream = new JSFML::InputStream(env, ref);
	return (jlong)stream;
}

/*
 * Class:     org_jsfml_internal_SFMLInputStream_NativeStreamRef
 * Method:    nativeRelease
 * Signature: (Lorg/jsfml/internal/SFMLInputStream;J)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_internal_SFMLInputStream_00024NativeStreamRef_nativeRelease
  (JNIEnv *env, jobject, jobject ref, jlong ptr) {

	if(ptr) {
		delete (JSFML::InputStream*)ptr;
	}
}
