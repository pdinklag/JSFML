#include <JSFML/JNI/org_jsfml_graphics_ConvexShape.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <JSFML/JNI/org_jsfml_ExPtr.h>

#include <SFML/Graphics/ConvexShape.hpp>
#include <SFML/Graphics/RenderTarget.hpp>

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_ConvexShape_nativeCreate (JNIEnv *env, jobject obj) {
    sf::ConvexShape *convexShape = new sf::ConvexShape();

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_DRAWABLE,
        dynamic_cast<sf::Drawable*>(convexShape));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_TRANSFORMABLE,
        dynamic_cast<sf::Transformable*>(convexShape));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_ExPtr_SHAPE,
        dynamic_cast<sf::Shape*>(convexShape));

    return (jlong)convexShape;
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
 * Signature: (ILorg/jsfml/system/Vector2f;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_nativeSetPoint
    (JNIEnv *env, jobject obj, jint i, jobject v) {

    THIS(sf::ConvexShape)->setPoint(i, JSFML::Vector2f::ToSFML(env, v));
}

/*
 * Class:     org_jsfml_graphics_ConvexShape
 * Method:    setPointCount
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_ConvexShape_setPointCount
    (JNIEnv *env, jobject obj, jint count) {

    THIS(sf::ConvexShape)->setPointCount(count);
}
