#include <JSFML/JNI/org_jsfml_window_Window.h>

#include <JSFML/Intercom/ContextSettings.hpp>
#include <JSFML/Intercom/Event.hpp>
#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector2i.hpp>
#include <JSFML/Intercom/Vector2u.hpp>
#include <JSFML/Intercom/VideoMode.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Window/Window.hpp>
#include <SFML/Graphics/Image.hpp>

//used to find out the operating system
#include <SFML/Config.hpp>

#if defined(SFML_SYSTEM_MACOS)
    #include <pthread.h>
#endif

#define SF_WINDOW JSFML::NativeObject::GetExPointer<sf::Window>(env, obj, org_jsfml_internal_ExPtr_WINDOW)

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_window_Window_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Window();
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_WINDOW, THIS(sf::Window));
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Window);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeCreateWindow
 * Signature: (Ljava/nio/Buffer;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeCreateWindow
    (JNIEnv *env, jobject obj, jobject paramsBuffer, jstring title) {

    jint *params = (jint*)env->GetDirectBufferAddress(paramsBuffer);

    SF_WINDOW->create(
        sf::VideoMode(params[0], params[1], params[2]),
        JSFML::Intercom::decodeUtf32(env, title),
        (sf::Uint32)params[3],
        sf::ContextSettings(params[4], params[5], params[6], params[7], params[8]));
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    isLegalWindowThread
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Window_isLegalWindowThread (JNIEnv *env, jclass cls) {

#if defined(SFML_SYSTEM_MACOS)
    return (pthread_main_np() != 0);
#else
    return true;
#endif

}

/*
 * Class:     org_jsfml_window_Window
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_close (JNIEnv *env, jobject obj) {
    SF_WINDOW->close();
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    isOpen
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Window_isOpen (JNIEnv *env, jobject obj) {
    return SF_WINDOW->isOpen();
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeGetPosition
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_window_Window_nativeGetPosition (JNIEnv *env, jobject obj) {
    return JSFML::Intercom::encodeVector2i(SF_WINDOW->getPosition());
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetPosition
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeSetPosition
    (JNIEnv *env, jobject obj, jint x, jint y) {

    SF_WINDOW->setPosition(sf::Vector2i(x, y));
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeGetSize
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_window_Window_nativeGetSize (JNIEnv *env, jobject obj) {
	return JSFML::Intercom::encodeVector2u(SF_WINDOW->getSize());
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetSize
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeSetSize
    (JNIEnv *env, jobject obj, jint x, jint y) {

    SF_WINDOW->setSize(sf::Vector2u(x, y));
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeGetSettings
 * Signature: (Ljava/nio/Buffer;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeGetSettings (JNIEnv *env, jobject obj, jobject buf) {
    const sf::ContextSettings& settings = SF_WINDOW->getSettings();
    jint *s = (jint*)env->GetDirectBufferAddress(buf);
    
    s[0] = settings.depthBits;
    s[1] = settings.stencilBits;
    s[2] = settings.antialiasingLevel;
    s[3] = settings.majorVersion;
    s[4] = settings.minorVersion;
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    pollEvent
 * Signature: ()Lorg/jsfml/window/event/Event;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Window_pollEvent (JNIEnv *env, jobject obj) {
    sf::Event event;

    if(SF_WINDOW->pollEvent(event))
        return JSFML::Event::FromSFML(env, event);
    else
        return NULL;
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    waitEvent
 * Signature: ()Lorg/jsfml/window/event/Event;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Window_waitEvent (JNIEnv *env, jobject obj) {
    sf::Event event;

    if(SF_WINDOW->waitEvent(event))
        return JSFML::Event::FromSFML(env, event);
    else
        return NULL;
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setVerticalSyncEnabled
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setVerticalSyncEnabled (JNIEnv *env, jobject obj, jboolean b) {
    SF_WINDOW->setVerticalSyncEnabled(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setMouseCursorVisible
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setMouseCursorVisible (JNIEnv *env, jobject obj, jboolean b) {
    SF_WINDOW->setMouseCursorVisible(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetTitle
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeSetTitle (JNIEnv *env, jobject obj, jstring title) {
	SF_WINDOW->setTitle(JSFML::Intercom::decodeUtf32(env, title));
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setVisible
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setVisible (JNIEnv *env, jobject obj, jboolean b) {
    SF_WINDOW->setVisible(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setKeyRepeatEnabled
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setKeyRepeatEnabled (JNIEnv *env, jobject obj, jboolean b) {
    SF_WINDOW->setKeyRepeatEnabled(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetIcon
 * Signature: (Lorg/jsfml/graphics/Image;)J
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeSetIcon
    (JNIEnv * env, jobject obj, jobject jicon) {

    sf::Image* icon = JSFML::NativeObject::GetPointer<sf::Image>(env, jicon);
	sf::Vector2u iconSize = icon->getSize();

    SF_WINDOW->setIcon(
        iconSize.x,
        iconSize.y,
        icon->getPixelsPtr());
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetActive
 * Signature: (Z)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Window_nativeSetActive
    (JNIEnv *env, jobject obj, jboolean b) {

    return SF_WINDOW->setActive(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    display
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_display (JNIEnv *env, jobject obj) {
    SF_WINDOW->display();
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setFramerateLimit
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setFramerateLimit (JNIEnv *env, jobject obj, jint fps) {
    SF_WINDOW->setFramerateLimit(fps);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setJoystickTreshold
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setJoystickTreshold (JNIEnv *env, jobject obj, jfloat t) {
    SF_WINDOW->setJoystickThreshold(t);
}
