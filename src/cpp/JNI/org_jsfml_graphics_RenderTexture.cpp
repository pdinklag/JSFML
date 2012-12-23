#include <JSFML/JNI/org_jsfml_graphics_RenderTexture.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Vector2f.hpp>
#include <JSFML/Intercom/Vector2i.hpp>
#include <JSFML/Intercom/Vector2u.hpp>
#include <JSFML/Intercom/Vertex.hpp>

#include <JSFML/JNI/org_jsfml_ExPtr.h>

#include <SFML/Graphics/RenderTexture.hpp>

#define VERTEX_BUFSIZE_RTEXTURE 0x400
sf::Vertex sfVertexBuffer_RenderTexture[VERTEX_BUFSIZE_RTEXTURE];

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderTexture_nativeCreate__ (JNIEnv *env, jobject obj) {
    return (jlong)new sf::RenderTexture();
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_RENDER_TARGET,
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
 * Method:    nativeCreate
 * Signature: (IIZ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_RenderTexture_nativeCreate__IIZ
    (JNIEnv *env, jobject obj, jint width, jint height, jboolean depthBuffer) {

    return THIS(sf::RenderTexture)->create(width, height, depthBuffer);
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    setSmooth
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_setSmooth (JNIEnv *env, jobject obj, jboolean b) {
    THIS(sf::RenderTexture)->setSmooth(b);
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    isSmooth
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_jsfml_graphics_RenderTexture_isSmooth (JNIEnv *env, jobject obj) {
    return THIS(sf::RenderTexture)->isSmooth();
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
 * Method:    getSize
 * Signature: ()Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderTexture_getSize (JNIEnv *env, jobject obj) {
	//don't be confused, JSFML::Vector2u maps to Vector2i, because there are no unsigned types in Java
	return JSFML::Vector2u::FromSFML(env, THIS(sf::RenderTexture)->getSize());
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeClear
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeClear (JNIEnv *env, jobject obj, jobject color) {
    THIS(sf::RenderTexture)->clear(JSFML::Color::ToSFML(env, color));
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
 * Method:    nativeGetViewport
 * Signature: (Lorg/jsfml/graphics/View;)Lorg/jsfml/graphics/IntRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderTexture_nativeGetViewport (JNIEnv *env, jobject obj, jobject view) {
    return JSFML::IntRect::FromSFML(
        env,
        THIS(sf::RenderTexture)->getViewport(*JSFML::NativeObject::GetPointer<sf::View>(env, view)));
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeMapPixelToCoords
 * Signature: (Lorg/jsfml/system/Vector2i;Lorg/jsfml/graphics/View;)Lorg/jsfml/system/Vector2f;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderTexture_nativeMapPixelToCoords
    (JNIEnv *env, jobject obj, jobject point, jobject view) {

    if(view == NULL) {
        return JSFML::Vector2f::FromSFML(env,
            THIS(sf::RenderTexture)->mapPixelToCoords(
                JSFML::Vector2i::ToSFML(env, point)));
    } else {
        return JSFML::Vector2f::FromSFML(env,
            THIS(sf::RenderTexture)->mapPixelToCoords(
                JSFML::Vector2i::ToSFML(env, point),
                *JSFML::NativeObject::GetPointer<sf::View>(env, view)));
    }
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeMapCoordsToPixel
 * Signature: (Lorg/jsfml/system/Vector2f;Lorg/jsfml/graphics/View;)Lorg/jsfml/system/Vector2i;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_RenderTexture_nativeMapCoordsToPixel
    (JNIEnv *env, jobject obj, jobject point, jobject view) {

    if(view == NULL) {
        return JSFML::Vector2i::FromSFML(env,
            THIS(sf::RenderTexture)->mapCoordsToPixel(
                JSFML::Vector2f::ToSFML(env, point)));
    } else {
        return JSFML::Vector2i::FromSFML(env,
            THIS(sf::RenderTexture)->mapCoordsToPixel(
                JSFML::Vector2f::ToSFML(env, point),
                *JSFML::NativeObject::GetPointer<sf::View>(env, view)));
    }
}

/*
 * Class:     org_jsfml_graphics_RenderTexture
 * Method:    nativeDraw
 * Signature: ([Lorg/jsfml/graphics/Vertex;Lorg/jsfml/graphics/PrimitiveType;Lorg/jsfml/graphics/RenderStates;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RenderTexture_nativeDraw
    (JNIEnv *env, jobject obj, jobjectArray vertices, jobject type, jobject renderStates) {

    jint num = env->GetArrayLength(vertices);
    if(num > 0) {
        for(jint i = 0; i < num; i++)
            sfVertexBuffer_RenderTexture[i] = JSFML::Vertex::ToSFML(env, env->GetObjectArrayElement(vertices, i));

        THIS(sf::RenderTexture)->draw(
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
