#include <JSFML/JNI/org_jsfml_window_Window.h>

#include <JSFML/Intercom/ContextSettings.hpp>
#include <JSFML/Intercom/Event.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/JavaString.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Vector2i.hpp>
#include <JSFML/Intercom/Vector2u.hpp>
#include <JSFML/Intercom/VideoMode.hpp>

#include <JSFML/JNI/org_jsfml_ExPtr.h>

#include <SFML/Window/Window.hpp>
#include <SFML/Graphics/Image.hpp>

//used to find out the operating system
#include <SFML/Config.hpp>

#if defined(SFML_SYSTEM_MACOS)
    #include <pthread.h>
#endif

#define SF_WINDOW JSFML::NativeObject::GetExPointer<sf::Window>(env, obj, org_jsfml_ExPtr_WINDOW)

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_window_Window_nativeCreate__ (JNIEnv *env, jobject obj) {
    sf::Window* window = new sf::Window();
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_WINDOW, window);
    return (jlong)window;
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

    SF_WINDOW->create(
        JSFML::VideoMode::ToSFML(env, videoMode),
        std::string(JavaString::getUTF8(env, title)),
        (sf::Uint32)style,
        JSFML::ContextSettings::ToSFML(env, settings));
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
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Window_getPosition (JNIEnv *env, jobject obj) {
    return JSFML::Vector2i::FromSFML(env, SF_WINDOW->getPosition());
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetPosition
 * Signature: (Lorg/jsfml/system/Vector2i;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeSetPosition
    (JNIEnv *env, jobject obj, jobject position) {

    SF_WINDOW->setPosition(JSFML::Vector2i::ToSFML(env, position));
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    getSize
 * Signature: ()Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Window_getSize (JNIEnv *env, jobject obj) {
	//don't be confused, JSFML::Vector2u maps to Vector2i, because there are no unsigned types in Java
	return JSFML::Vector2u::FromSFML(env, SF_WINDOW->getSize());
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    nativeSetSize
 * Signature: (Lorg/jsfml/system/Vector2i;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_window_Window_nativeSetSize
    (JNIEnv *env, jobject obj, jobject size) {

	//don't be confused, JSFML::Vector2u maps to Vector2i, because there are no unsigned types in Java
    SF_WINDOW->setSize(JSFML::Vector2u::ToSFML(env, size));
}

/*
 * Class:     org_jsfml_window_Window
 * Method:    getSettings
 * Signature: ()Lorg/jsfml/window/ContextSettings;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_window_Window_getSettings (JNIEnv *env, jobject obj) {
    return JSFML::ContextSettings::FromSFML(env, SF_WINDOW->getSettings());
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
    SF_WINDOW->setTitle(std::string(JavaString::getUTF8(env, title)));
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
