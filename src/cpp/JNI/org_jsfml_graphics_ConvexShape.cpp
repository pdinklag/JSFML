#include <JSFML/JNI/org_jsfml_graphics_ConvexShape.h>

#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/ConvexShape.hpp>
#include <SFML/Graphics/RenderTarget.hpp>

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_ConvexShape_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::ConvexShape();
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_DRAWABLE,
        dynamic_cast<sf::Drawable*>(THIS(sf::ConvexShape)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_TRANSFORMABLE,
        dynamic_cast<sf::Transformable*>(THIS(sf::ConvexShape)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_SHAPE,
        dynamic_cast<sf::Shape*>(THIS(sf::ConvexShape)));
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::ConvexShape);
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeSetPoint
 * Signature: (IFF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeSetPoint
    (JNIEnv *env, jobject obj, jint i, jfloat x, jfloat y) {

    THIS(sf::ConvexShape)->setPoint(i, sf::Vector2f(x, y));
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeSetPointCount
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeSetPointCount
    (JNIEnv *env, jobject obj, jint count) {

    THIS(sf::ConvexShape)->setPointCount(count);
}
