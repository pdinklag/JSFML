#include <JSFML/JNI/org_jsfml_window_VideoMode.h>

#include <SFML/Window/VideoMode.hpp>

/*
 * Class:     org_jsfml_window_VideoMode
 * Method:    nativeGetDesktopMode
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_VideoMode_nativeGetDesktopMode
    (JNIEnv *env, jclass cls, jobject buf) {

    sf::VideoMode desktop = sf::VideoMode::getDesktopMode();
    
    jint *vmode = (jint*)env->GetDirectBufferAddress(buf);
    vmode[0] = desktop.width;
    vmode[1] = desktop.height;
    vmode[2] = desktop.bitsPerPixel;
}

/*
 * Class:     org_jsfml_window_VideoMode
 * Method:    nativeGetFullscreenModeCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_window_VideoMode_nativeGetFullscreenModeCount
    (JNIEnv *env, jclass cls) {

    return (jint)sf::VideoMode::getFullscreenModes().size();
}

/*
 * Class:     org_jsfml_window_VideoMode
 * Method:    nativeGetFullscreenModes
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_VideoMode_nativeGetFullscreenModes
    (JNIEnv *env, jclass cls, jobject buf) {

    const std::vector<sf::VideoMode>& modes = sf::VideoMode::getFullscreenModes();
    jint *data = (jint*)env->GetDirectBufferAddress(buf);
    
    for(int i = 0; i < modes.size(); i++) {
        const sf::VideoMode& mode = modes[i];
        data[3 * i]     = mode.width;
        data[3 * i + 1] = mode.height;
        data[3 * i + 2] = mode.bitsPerPixel;
    }
}
