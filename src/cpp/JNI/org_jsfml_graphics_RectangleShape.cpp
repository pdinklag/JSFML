#include <JSFML/JNI/org_jsfml_graphics_RectangleShape.h>

#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/RectangleShape.hpp>

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RectangleShape_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::RectangleShape();
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_DRAWABLE,
        dynamic_cast<sf::Drawable*>(THIS(sf::RectangleShape)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_TRANSFORMABLE,
        dynamic_cast<sf::Transformable*>(THIS(sf::RectangleShape)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_SHAPE,
        dynamic_cast<sf::Shape*>(THIS(sf::RectangleShape)));
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::RectangleShape);
}

/*
 * Class:     org_jsfml_graphics_RectangleShape
 * Method:    nativeSetSize
 * Signature: (FF)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_RectangleShape_nativeSetSize
    (JNIEnv *env, jobject obj, jfloat w, jfloat h) {

    THIS(sf::RectangleShape)->setSize(sf::Vector2f(w, h));
}
