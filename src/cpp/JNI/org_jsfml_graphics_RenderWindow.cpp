#include <JSFML/JNI/org_jsfml_graphics_RenderWindow.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/ContextSettings.hpp>
#include <JSFML/Intercom/Event.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/JavaString.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Time.hpp>
#include <JSFML/Intercom/Vector2f.hpp>
#include <JSFML/Intercom/Vector2i.hpp>
#include <JSFML/Intercom/Vector2u.hpp>
#include <JSFML/Intercom/Vertex.hpp>
#include <JSFML/Intercom/VideoMode.hpp>

#include <SFML/Graphics/RenderWindow.hpp>
#include <SFML/Graphics/Image.hpp>

#define VERTEX_BUFSIZE_RWINDOW 0x400
sf::Vertex sfVertexBuffer_RenderWindow[VERTEX_BUFSIZE_RWINDOW];

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderWindow_nativeCreate__ (JNIEnv *env, jobject obj) {
    return (jlong)new sf::RenderWindow();
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
 * Method:    nativeGetRenderTargetPtr
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderWindow_nativeGetRenderTargetPtr (JNIEnv *env, jobject obj) {
    return (jlong)dynamic_cast<sf::RenderTarget*>(THIS(sf::RenderWindow));
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
 * Method:    nativeConvertCoords
 * Signature: (Lorg/jsfml/system/Vector2i;Lorg/jsfml/graphics/View;)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_nativeConvertCoords
    (JNIEnv *env, jobject obj, jobject point, jobject view) {

    if(view == NULL) {
        return JSFML::Vector2f::FromSFML(env,
            THIS(sf::RenderWindow)->convertCoords(
                JSFML::Vector2i::ToSFML(env, point)));
    } else {
        return JSFML::Vector2f::FromSFML(env,
            THIS(sf::RenderWindow)->convertCoords(
                JSFML::Vector2i::ToSFML(env, point),
                *JSFML::NativeObject::GetPointer<sf::View>(env, view)));
    }
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeDraw
 * Signature: ([Lorg/jsfml/graphics/Vertex;Lorg/jsfml/graphics/PrimitiveType;Lorg/jsfml/graphics/RenderStates;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeDraw___3Lorg_jsfml_graphics_Vertex_2Lorg_jsfml_graphics_PrimitiveType_2Lorg_jsfml_graphics_RenderStates_2
    (JNIEnv *env, jobject obj, jobjectArray vertices, jobject type, jobject renderStates) {

    jint num = env->GetArrayLength(vertices);
    if(num > 0) {
        for(jint i = 0; i < num; i++)
            sfVertexBuffer_RenderWindow[i] = JSFML::Vertex::ToSFML(env, env->GetObjectArrayElement(vertices, i));

        THIS(sf::RenderWindow)->draw(
            sfVertexBuffer_RenderWindow,
            num,
            (sf::PrimitiveType)JavaEnum::ordinal(env, type),
            JSFML::RenderStates::ToSFML(env, renderStates));
    }
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeCreate
 * Signature: (Lorg/jsfml/window/VideoMode;Ljava/lang/String;ILorg/jsfml/window/ContextSettings;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeCreate__Lorg_jsfml_window_VideoMode_2Ljava_lang_String_2ILorg_jsfml_window_ContextSettings_2
    (JNIEnv *env, jobject obj, jobject videoMode, jstring title, jint style, jobject settings) {

    THIS(sf::RenderWindow)->create(
        JSFML::VideoMode::ToSFML(env, videoMode),
        std::string(JavaString::getUTF8(env, title)),
        (sf::Uint32)style,
        JSFML::ContextSettings::ToSFML(env, settings));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_close (JNIEnv *env, jobject obj) {
    THIS(sf::RenderWindow)->close();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    isOpen
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_RenderWindow_isOpen (JNIEnv *env, jobject obj) {
    return THIS(sf::RenderWindow)->isOpen();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    getPosition
 * Signature: ()Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_getPosition (JNIEnv *env, jobject obj) {
    return JSFML::Vector2i::FromSFML(env, THIS(sf::RenderWindow)->getPosition());
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeSetPosition
 * Signature: (Lorg/jsfml/system/Vector2i;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeSetPosition
    (JNIEnv *env, jobject obj, jobject position) {

    THIS(sf::RenderWindow)->setPosition(JSFML::Vector2i::ToSFML(env, position));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    getSize
 * Signature: ()Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_getSize (JNIEnv *env, jobject obj) {
	//don't be confused, JSFML::Vector2u maps to Vector2i, because there are no unsigned types in Java
	return JSFML::Vector2u::FromSFML(env, THIS(sf::RenderWindow)->getSize());
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeSetSize
 * Signature: (Lorg/jsfml/system/Vector2i;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeSetSize
    (JNIEnv *env, jobject obj, jobject size) {

	//don't be confused, JSFML::Vector2u maps to Vector2i, because there are no unsigned types in Java
    THIS(sf::RenderWindow)->setSize(JSFML::Vector2u::ToSFML(env, size));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    getSettings
 * Signature: ()Lorg/jsfml/window/ContextSettings;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_getSettings (JNIEnv *env, jobject obj) {
    return JSFML::ContextSettings::FromSFML(env, THIS(sf::RenderWindow)->getSettings());
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    pollEvent
 * Signature: ()Lorg/jsfml/window/event/Event;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_pollEvent (JNIEnv *env, jobject obj) {
    sf::Event event;

    if(THIS(sf::RenderWindow)->pollEvent(event))
        return JSFML::Event::FromSFML(env, event);
    else
        return NULL;
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    waitEvent
 * Signature: ()Lorg/jsfml/window/event/Event;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_waitEvent (JNIEnv *env, jobject obj) {
    sf::Event event;

    if(THIS(sf::RenderWindow)->waitEvent(event))
        return JSFML::Event::FromSFML(env, event);
    else
        return NULL;
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setVerticalSyncEnabled
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setVerticalSyncEnabled (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->setVerticalSyncEnabled(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setMouseCursorVisible
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setMouseCursorVisible (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->setMouseCursorVisible(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeSetTitle
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeSetTitle (JNIEnv *env, jobject obj, jstring title) {
    THIS(sf::RenderWindow)->setTitle(std::string(JavaString::getUTF8(env, title)));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setVisible
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setVisible (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->setVisible(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setKeyRepeatEnabled
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setKeyRepeatEnabled (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->setKeyRepeatEnabled(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeSetIcon
 * Signature: (Lorg/jsfml/graphics/Image;)J
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeSetIcon
    (JNIEnv * env, jobject obj, jobject jicon) {

    sf::Image* icon = JSFML::NativeObject::GetPointer<sf::Image>(env, jicon);
	sf::Vector2u iconSize = icon->getSize();
	
    THIS(sf::RenderWindow)->setIcon(
        iconSize.x,
        iconSize.y,
        icon->getPixelsPtr());
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setActive
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setActive (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->setActive(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    display
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_display (JNIEnv *env, jobject obj) {
    THIS(sf::RenderWindow)->display();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setFramerateLimit
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setFramerateLimit (JNIEnv *env, jobject obj, jint fps) {
    THIS(sf::RenderWindow)->setFramerateLimit(fps);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setJoystickTreshold
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setJoystickTreshold (JNIEnv *env, jobject obj, jfloat t) {
    THIS(sf::RenderWindow)->setJoystickThreshold(t);
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
