#include <JSFML/JNI/org_jsfml_graphics_RenderTexture.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Vector2f.hpp>
#include <JSFML/Intercom/Vertex.hpp>

#include <SFML/Graphics/RenderTexture.hpp>

#define VERTEX_BUFSIZE_RTEXTURE 0x400
sf::Vertex sfVertexBuffer_RenderTexture[VERTEX_BUFSIZE_RTEXTURE];

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
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::RenderTexture);
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    create
 * Signature: (IIZ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_RenderTexture_create
    (JNIEnv *env, jobject obj, jint width, jint height, jboolean depthBuffer) {

    return THIS(sf::RenderTexture)->Create(width, height, depthBuffer);
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    setSmooth
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_setSmooth (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderTexture)->SetSmooth(b);
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    isSmooth
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_RenderTexture_isSmooth (JNIEnv *env, jobject obj) {
    return THIS(sf::RenderTexture)->IsSmooth();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    setActive
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_setActive (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderTexture)->SetActive(b);
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    display
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_display (JNIEnv *env, jobject obj) {
    THIS(sf::RenderTexture)->Display();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeGetTexture
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderTexture_nativeGetTexture (JNIEnv *env, jobject obj) {
    return (jlong)&THIS(sf::RenderTexture)->GetTexture();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    getWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_RenderTexture_getWidth (JNIEnv *env, jobject obj) {
    return THIS(sf::RenderTexture)->GetWidth();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    getHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_RenderTexture_getHeight (JNIEnv *env, jobject obj) {
    return THIS(sf::RenderTexture)->GetHeight();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeClear
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeClear (JNIEnv *env, jobject obj, jobject color) {
    THIS(sf::RenderTexture)->Clear(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeSetView
 * Signature: (Lorg/jsfml/graphics/View;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeSetView (JNIEnv *env, jobject obj, jobject view) {
    THIS(sf::RenderTexture)->SetView(*JSFML::NativeObject::GetPointer<sf::View>(env, view));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeGetDefaultView
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderTexture_nativeGetDefaultView (JNIEnv *env, jobject obj) {
    return (jlong)&THIS(sf::RenderTexture)->GetDefaultView();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeGetViewport
 * Signature: (Lorg/jsfml/graphics/View;)Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderTexture_nativeGetViewport (JNIEnv *env, jobject obj, jobject view) {
    return JSFML::IntRect::FromSFML(
        env,
        THIS(sf::RenderTexture)->GetViewport(*JSFML::NativeObject::GetPointer<sf::View>(env, view)));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    convertCoords
 * Signature: (FF)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderTexture_convertCoords
    (JNIEnv * env, jobject obj, jfloat x, jfloat y) {

    return JSFML::Vector2f::FromSFML(env, THIS(sf::RenderTexture)->ConvertCoords(x, y));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeConvertCoords
 * Signature: (FFLorg/jsfml/graphics/View;)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderTexture_nativeConvertCoords
    (JNIEnv *env, jobject obj, jfloat x, jfloat y, jobject view) {

    return JSFML::Vector2f::FromSFML(env,
        THIS(sf::RenderTexture)->ConvertCoords(x, y, *JSFML::NativeObject::GetPointer<sf::View>(env, view)));
 }

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeDraw
 * Signature: (Lorg/jsfml/graphics/Drawable;Lorg/jsfml/graphics/RenderStates;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeDraw__Lorg_jsfml_graphics_Drawable_2Lorg_jsfml_graphics_RenderStates_2
    (JNIEnv *env, jobject obj, jobject drawable, jobject renderStates) {

    THIS(sf::RenderTexture)->Draw(
        *JSFML::NativeObject::GetPointer<sf::Drawable>(env, drawable),
        JSFML::RenderStates::ToSFML(env, renderStates));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeDraw
 * Signature: ([Lorg/jsfml/graphics/Vertex;Lorg/jsfml/graphics/PrimitiveType;Lorg/jsfml/graphics/RenderStates;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeDraw___3Lorg_jsfml_graphics_Vertex_2Lorg_jsfml_graphics_PrimitiveType_2Lorg_jsfml_graphics_RenderStates_2
    (JNIEnv *env, jobject obj, jobjectArray vertices, jobject type, jobject renderStates) {

    jint num = env->GetArrayLength(vertices);
    if(num > 0) {
        for(jint i = 0; i < num; i++)
            sfVertexBuffer_RenderTexture[i] = JSFML::Vertex::ToSFML(env, env->GetObjectArrayElement(vertices, i));

        THIS(sf::RenderTexture)->Draw(
            sfVertexBuffer_RenderTexture,
            num,
            (sf::PrimitiveType)JavaEnum::ordinal(env, type),
            JSFML::RenderStates::ToSFML(env, renderStates));
    }
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    pushGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_pushGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderTexture)->PushGLStates();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    popGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_popGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderTexture)->PopGLStates();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    resetGLStates
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_resetGLStates (JNIEnv *env, jobject obj) {
    THIS(sf::RenderTexture)->ResetGLStates();
}
