#include <JSFML/JNI/org_jsfml_graphics_RenderWindow.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/ContextSettings.hpp>
#include <JSFML/Intercom/Event.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/JavaString.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Vector2f.hpp>
#include <JSFML/Intercom/Vertex.hpp>
#include <JSFML/Intercom/VideoMode.hpp>

#include <SFML/Graphics/RenderWindow.hpp>

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
 * Method:    nativeClear
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeClear (JNIEnv *env, jobject obj, jobject color) {
    THIS(sf::RenderWindow)->Clear(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeSetView
 * Signature: (Lorg/jsfml/graphics/View;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeSetView (JNIEnv *env, jobject obj, jobject view) {
    THIS(sf::RenderWindow)->SetView(*JSFML::NativeObject::GetPointer<sf::View>(env, view));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeGetDefaultView
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderWindow_nativeGetDefaultView (JNIEnv *env, jobject obj) {
    return (jlong)&THIS(sf::RenderWindow)->GetDefaultView();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeGetViewport
 * Signature: (Lorg/jsfml/graphics/View;)Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_nativeGetViewport (JNIEnv *env, jobject obj, jobject view) {
    return JSFML::IntRect::FromSFML(
        env,
        THIS(sf::RenderWindow)->GetViewport(*JSFML::NativeObject::GetPointer<sf::View>(env, view)));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    convertCoords
 * Signature: (FF)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_convertCoords
    (JNIEnv * env, jobject obj, jfloat x, jfloat y) {

    return JSFML::Vector2f::FromSFML(env, THIS(sf::RenderWindow)->ConvertCoords(x, y));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeConvertCoords
 * Signature: (FFLorg/jsfml/graphics/View;)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_nativeConvertCoords
    (JNIEnv *env, jobject obj, jfloat x, jfloat y, jobject view) {

    return JSFML::Vector2f::FromSFML(env,
        THIS(sf::RenderWindow)->ConvertCoords(x, y, *JSFML::NativeObject::GetPointer<sf::View>(env, view)));
 }

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeDraw
 * Signature: (Lorg/jsfml/graphics/Drawable;Lorg/jsfml/graphics/RenderStates;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeDraw__Lorg_jsfml_graphics_Drawable_2Lorg_jsfml_graphics_RenderStates_2
    (JNIEnv *env, jobject obj, jobject drawable, jobject renderStates) {

    THIS(sf::RenderWindow)->Draw(
        *JSFML::NativeObject::GetPointer<sf::Drawable>(env, drawable),
        JSFML::RenderStates::ToSFML(env, renderStates));
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
        sf::Vertex sfmlVertices[env->GetArrayLength(vertices)];

        for(jint i = 0; i < num; i++)
            sfmlVertices[i] = JSFML::Vertex::ToSFML(env, env->GetObjectArrayElement(vertices, i));

        THIS(sf::RenderWindow)->Draw(
            sfmlVertices,
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

    THIS(sf::RenderWindow)->Create(
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
    THIS(sf::RenderWindow)->Close();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    isOpen
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_RenderWindow_isOpen (JNIEnv *env, jobject obj) {
    return THIS(sf::RenderWindow)->IsOpen();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    getWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_RenderWindow_getWidth (JNIEnv *env, jobject obj) {
    return THIS(sf::RenderWindow)->GetWidth();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    getHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_RenderWindow_getHeight (JNIEnv *env, jobject obj) {
    return THIS(sf::RenderWindow)->GetHeight();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    getSettings
 * Signature: ()Lorg/jsfml/window/ContextSettings;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_getSettings (JNIEnv *env, jobject obj) {
    return JSFML::ContextSettings::FromSFML(env, THIS(sf::RenderWindow)->GetSettings());
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    pollEvent
 * Signature: ()Lorg/jsfml/window/event/Event;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderWindow_pollEvent (JNIEnv *env, jobject obj) {
    sf::Event event;

    if(THIS(sf::RenderWindow)->PollEvent(event))
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

    if(THIS(sf::RenderWindow)->WaitEvent(event))
        return JSFML::Event::FromSFML(env, event);
    else
        return NULL;
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    enableVerticalSync
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_enableVerticalSync (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->EnableVerticalSync(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    showMouseCursor
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_showMouseCursor (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->ShowMouseCursor(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setPosition
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setPosition (JNIEnv *env, jobject obj, jint x, jint y) {
    THIS(sf::RenderWindow)->SetPosition(x, y);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setSize
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setSize (JNIEnv *env, jobject obj, jint width, jint height) {
   THIS(sf::RenderWindow)->SetSize(width, height);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeSetTitle
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeSetTitle (JNIEnv *env, jobject obj, jstring title) {
    THIS(sf::RenderWindow)->SetTitle(std::string(JavaString::getUTF8(env, title)));
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    show
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_show (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->Show(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    enableKeyRepeat
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_enableKeyRepeat (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->EnableKeyRepeat(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeSetIcon
 * Signature: (III[I)J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderWindow_nativeSetIcon
    (JNIEnv *env, jobject obj, jint width, jint height, jint bytesPerPixel, jintArray pixels) {

	sf::Uint8* data = new sf::Uint8[width * height * 4]; //32bit RGBA
	jint* source = env->GetIntArrayElements(pixels, 0);

	int s = 0;
	int d = 0;

	for(int y = 0; y < height; y++) {
		for(int x = 0; x < width; x++) {
			for(int i = 0; i < 4; i++) {
				data[d + i] = 0xFF;
				if(bytesPerPixel < 4 && i == 3)
					data[d + i] = 0xFF;
				else
					data[d + i] = source[s + i] & 0xFF;
			}

			d += 4;
			s += bytesPerPixel;
		}
	}

	env->ReleaseIntArrayElements(pixels, source, 0);

	THIS(sf::RenderWindow)->SetIcon(width, height, data);
	return (jlong)data;
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    nativeDeleteIcon
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_nativeDeleteIcon (JNIEnv *env, jobject obj, jlong ptr) {
    if(ptr) delete[] (sf::Uint8*)ptr;
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setActive
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setActive (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderWindow)->SetActive(b);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    display
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_display (JNIEnv *env, jobject obj) {
    THIS(sf::RenderWindow)->Display();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setFramerateLimit
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setFramerateLimit (JNIEnv *env, jobject obj, jint fps) {
    THIS(sf::RenderWindow)->SetFramerateLimit(fps);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    getFrameTime
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderWindow_getFrameTime (JNIEnv *env, jobject obj) {
    return (jlong)THIS(sf::RenderWindow)->GetFrameTime();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    setJoystickTreshold
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_setJoystickTreshold (JNIEnv *env, jobject obj, jfloat t) {
    THIS(sf::RenderWindow)->SetJoystickThreshold(t);
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    pushGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_pushGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderWindow)->PushGLStates();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    popGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_popGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderWindow)->PopGLStates();
}

/*
 * Class:     org_jsfml_graphics_RenderWindow
 * Method:    resetGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderWindow_resetGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderWindow)->ResetGLStates();
}
