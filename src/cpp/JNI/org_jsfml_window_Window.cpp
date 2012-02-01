#include <JSFML/JNI/org_jsfml_window_Window.h>

#include <JSFML/Intercom/ContextSettings.hpp>
#include <JSFML/Intercom/Event.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/JavaString.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/VideoMode.hpp>

#include <SFML/Window/Window.hpp>
#include <SFML/Graphics/Image.hpp>

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_window_Window_nativeCreate__ (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Window();
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
 * Method:    nativeCreate
 * Signature: (Lorg/jsfml/window/VideoMode;Ljava/lang/String;ILorg/jsfml/window/ContextSettings;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeCreate__Lorg_jsfml_window_VideoMode_2Ljava_lang_String_2ILorg_jsfml_window_ContextSettings_2
    (JNIEnv *env, jobject obj, jobject videoMode, jstring title, jint style, jobject settings) {

    THIS(sf::Window)->Create(
        JSFML::VideoMode::ToSFML(env, videoMode),
        std::string(JavaString::getUTF8(env, title)),
        (sf::Uint32)style,
        JSFML::ContextSettings::ToSFML(env, settings));
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_close (JNIEnv *env, jobject obj) {
    THIS(sf::Window)->Close();
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    isOpen
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_window_Window_isOpen (JNIEnv *env, jobject obj) {
    return THIS(sf::Window)->IsOpen();
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    getWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_window_Window_getWidth (JNIEnv *env, jobject obj) {
    return THIS(sf::Window)->GetWidth();
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    getHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_window_Window_getHeight (JNIEnv *env, jobject obj) {
    return THIS(sf::Window)->GetHeight();
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    getSettings
 * Signature: ()Lorg/jsfml/window/ContextSettings;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Window_getSettings (JNIEnv *env, jobject obj) {
    return JSFML::ContextSettings::FromSFML(env, THIS(sf::Window)->GetSettings());
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    pollEvent
 * Signature: ()Lorg/jsfml/window/event/Event;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Window_pollEvent (JNIEnv *env, jobject obj) {
    sf::Event event;

    if(THIS(sf::Window)->PollEvent(event))
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

    if(THIS(sf::Window)->WaitEvent(event))
        return JSFML::Event::FromSFML(env, event);
    else
        return NULL;
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    enableVerticalSync
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_enableVerticalSync (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::Window)->EnableVerticalSync(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    showMouseCursor
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_showMouseCursor (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::Window)->ShowMouseCursor(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setPosition
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setPosition (JNIEnv *env, jobject obj, jint x, jint y) {
    THIS(sf::Window)->SetPosition(x, y);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setSize
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setSize (JNIEnv *env, jobject obj, jint width, jint height) {
   THIS(sf::Window)->SetSize(width, height);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetTitle
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeSetTitle (JNIEnv *env, jobject obj, jstring title) {
    THIS(sf::Window)->SetTitle(std::string(JavaString::getUTF8(env, title)));
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    show
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_show (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::Window)->Show(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    enableKeyRepeat
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_enableKeyRepeat (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::Window)->EnableKeyRepeat(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetIcon
 * Signature: (Lorg/jsfml/graphics/Image;)J
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeSetIcon
    (JNIEnv * env, jobject obj, jobject jicon) {

    sf::Image* icon = JSFML::NativeObject::GetPointer<sf::Image>(env, jicon);
    THIS(sf::Window)->SetIcon(
        icon->GetWidth(),
        icon->GetHeight(),
        icon->GetPixelsPtr());
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setActive
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setActive (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::Window)->SetActive(b);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    display
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_display (JNIEnv *env, jobject obj) {
    THIS(sf::Window)->Display();
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setFramerateLimit
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setFramerateLimit (JNIEnv *env, jobject obj, jint fps) {
    THIS(sf::Window)->SetFramerateLimit(fps);
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    setJoystickTreshold
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_setJoystickTreshold (JNIEnv *env, jobject obj, jfloat t) {
    THIS(sf::Window)->SetJoystickThreshold(t);
}
