#include <JSFML/JNI/org_jsfml_graphics_VertexArray.h>

#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vertex.hpp>

#include <SFML/Graphics/VertexArray.hpp>

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_VertexArray_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::VertexArray();
}

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    getVertexCount
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_VertexArray_getVertexCount (JNIEnv *env, jobject obj) {
    return THIS(sf::VertexArray)->GetVertexCount();
}

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    nativeGetVertex
 * Signature: (I)Lorg/jsfml/graphics/Vertex;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_VertexArray_nativeGetVertex
    (JNIEnv *env, jobject obj, jint i) {

    return JSFML::Vertex::FromSFML(env, (*THIS(sf::VertexArray))[i]);
}

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    nativeSetVertex
 * Signature: (ILorg/jsfml/graphics/Vertex;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_VertexArray_nativeSetVertex
    (JNIEnv *env, jobject obj, jint i, jobject vertex) {

    (*THIS(sf::VertexArray))[i] = JSFML::Vertex::ToSFML(env, vertex);
}

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    nativeClear
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_VertexArray_nativeClear (JNIEnv *env, jobject obj) {
    THIS(sf::VertexArray)->Clear();
}

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    nativeResize
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_VertexArray_nativeResize
    (JNIEnv *env, jobject obj, jint num) {

    THIS(sf::VertexArray)->Resize(num);
}

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    nativeAppend
 * Signature: (Lorg/jsfml/graphics/Vertex;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_VertexArray_nativeAppend
    (JNIEnv *env, jobject obj, jobject v) {

    THIS(sf::VertexArray)->Append(JSFML::Vertex::ToSFML(env, v));
}

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    nativeSetPrimitiveType
 * Signature: (Lorg/jsfml/graphics/PrimitiveType;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_VertexArray_nativeSetPrimitiveType
    (JNIEnv *env, jobject obj, jobject type) {

    THIS(sf::VertexArray)->SetPrimitiveType((sf::PrimitiveType)JavaEnum::ordinal(env, type));
}

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    nativeGetPrimitiveType
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_VertexArray_nativeGetPrimitiveType (JNIEnv *env, jobject obj) {
    return (jint)THIS(sf::VertexArray)->GetPrimitiveType();
}

/*
 * Class:     org_jsfml_graphics_VertexArray
 * Method:    getBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_VertexArray_getBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::VertexArray)->GetBounds());
}
