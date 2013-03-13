#include <JSFML/JNI/org_jsfml_graphics_RenderWindow.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/ContextSettings.hpp>
#include <JSFML/Intercom/Event.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Time.hpp>
#include <JSFML/Intercom/Vector2f.hpp>
#include <JSFML/Intercom/Vector2i.hpp>
#include <JSFML/Intercom/Vector2u.hpp>
#include <JSFML/Intercom/Vertex.hpp>
#include <JSFML/Intercom/VideoMode.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/RenderWindow.hpp>
#include <SFML/Graphics/Image.hpp>

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderWindow_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::RenderWindow();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_RENDER_TARGET,
        dynamic_cast<sf::RenderTarget*>(THIS(sf::RenderWindow)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_WINDOW,
        dynamic_cast<sf::Window*>(THIS(sf::RenderWindow)));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::RenderWindow);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeCapture
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderWindow_nativeCapture (JNIEnv *env, jobject obj) {
    sf::Image *image = new sf::Image();
    *image = THIS(sf::RenderWindow)->capture();
    return (jlong)image;
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeClear
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeClear (JNIEnv *env, jobject obj, jobject color) {
    THIS(sf::RenderWindow)->clear(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeSetView
 * Signature: (Lorg/jsfml/graphics/View;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeSetView (JNIEnv *env, jobject obj, jobject view) {
    THIS(sf::RenderWindow)->setView(*JSFML::NativeObject::GetPointer<sf::View>(env, view));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeGetDefaultView
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderWindow_nativeGetDefaultView (JNIEnv *env, jobject obj) {
    return (jlong)&THIS(sf::RenderWindow)->getDefaultView();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeGetViewport
 * Signature: (Lorg/jsfml/graphics/View;)Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_nativeGetViewport (JNIEnv *env, jobject obj, jobject view) {
    return JSFML::IntRect::FromSFML(
        env,
        THIS(sf::RenderWindow)->getViewport(*JSFML::NativeObject::GetPointer<sf::View>(env, view)));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeMapPixelToCoords
 * Signature: (Lorg/jsfml/system/Vector2i;Lorg/jsfml/graphics/View;)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_nativeMapPixelToCoords
    (JNIEnv *env, jobject obj, jobject point, jobject view) {

    if(view == NULL) {
        return JSFML::Vector2f::FromSFML(env,
            THIS(sf::RenderWindow)->mapPixelToCoords(
                JSFML::Vector2i::ToSFML(env, point)));
    } else {
        return JSFML::Vector2f::FromSFML(env,
            THIS(sf::RenderWindow)->mapPixelToCoords(
                JSFML::Vector2i::ToSFML(env, point),
                *JSFML::NativeObject::GetPointer<sf::View>(env, view)));
    }
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeMapCoordsToPixel
 * Signature: (Lorg/jsfml/system/Vector2f;Lorg/jsfml/graphics/View;)Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_nativeMapCoordsToPixel
    (JNIEnv *env, jobject obj, jobject point, jobject view) {

    if(view == NULL) {
        return JSFML::Vector2i::FromSFML(env,
            THIS(sf::RenderWindow)->mapCoordsToPixel(
                JSFML::Vector2f::ToSFML(env, point)));
    } else {
        return JSFML::Vector2i::FromSFML(env,
            THIS(sf::RenderWindow)->mapCoordsToPixel(
                JSFML::Vector2f::ToSFML(env, point),
                *JSFML::NativeObject::GetPointer<sf::View>(env, view)));
    }
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    pushGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_pushGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderWindow)->pushGLStates();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    popGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_popGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderWindow)->popGLStates();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    resetGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_resetGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderWindow)->resetGLStates();
}
