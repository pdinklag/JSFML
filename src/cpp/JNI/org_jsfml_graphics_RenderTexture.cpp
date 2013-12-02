#include <JSFML/JNI/org_jsfml_graphics_RenderTexture.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/RenderTexture.hpp>

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderTexture_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::RenderTexture();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_RENDER_TARGET,
        dynamic_cast<sf::RenderTarget*>(THIS(sf::RenderTexture)));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::RenderTexture);
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeCreateTexture
 * Signature: (IIZ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_RenderTexture_nativeCreateTexture
    (JNIEnv *env, jobject obj, jint width, jint height, jboolean depthBuffer) {

    return THIS(sf::RenderTexture)->create(width, height, depthBuffer);
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    setActive
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_setActive (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderTexture)->setActive(b);
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    display
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_display (JNIEnv *env, jobject obj) {
    THIS(sf::RenderTexture)->display();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeGetTexture
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderTexture_nativeGetTexture (JNIEnv *env, jobject obj) {
    return (jlong)&THIS(sf::RenderTexture)->getTexture();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeClear
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeClear (JNIEnv *env, jobject obj, jint color) {
    THIS(sf::RenderTexture)->clear(JSFML::Intercom::decodeColor(color));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeSetView
 * Signature: (Lorg/jsfml/graphics/View;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeSetView (JNIEnv *env, jobject obj, jobject view) {
    THIS(sf::RenderTexture)->setView(*JSFML::NativeObject::GetPointer<sf::View>(env, view));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeGetDefaultView
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderTexture_nativeGetDefaultView (JNIEnv *env, jobject obj) {
    return (jlong)&THIS(sf::RenderTexture)->getDefaultView();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeMapPixelToCoords
 * Signature: (JLorg/jsfml/graphics/ConstView;)J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderTexture_nativeMapPixelToCoords
    (JNIEnv *env, jobject obj, jlong point, jobject view) {

    return JSFML::Intercom::encodeVector2f(THIS(sf::RenderTexture)->mapPixelToCoords(
            JSFML::Intercom::decodeVector2i(point),
            *JSFML::NativeObject::GetPointer<sf::View>(env, view)));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeMapCoordsToPixel
 * Signature: (JLorg/jsfml/graphics/ConstView;)J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderTexture_nativeMapCoordsToPixel
    (JNIEnv *env, jobject obj, jlong point, jobject view) {

    return JSFML::Intercom::encodeVector2i(THIS(sf::RenderTexture)->mapCoordsToPixel(
            JSFML::Intercom::decodeVector2f(point),
            *JSFML::NativeObject::GetPointer<sf::View>(env, view)));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    pushGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_pushGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderTexture)->pushGLStates();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    popGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_popGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderTexture)->popGLStates();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    resetGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_resetGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderTexture)->resetGLStates();
}
