
#include <JSFML/JNI/org_jsfml_JSFML.h>

#include <JSFML/Intercom/Intercom.hpp>

#include <SFML/Config.hpp>

JNIEXPORT void JNICALL Java_org_jsfml_JSFML_nativeGetSFMLVersion
    (JNIEnv *env, jclass cls, jobject buffer) {

    jint *version = (jint*)env->GetDirectBufferAddress(buffer);
    version[0] = SFML_VERSION_MAJOR;
    version[1] = SFML_VERSION_MINOR;
    version[2] = SFML_VERSION_PATCH;
}
