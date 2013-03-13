#include <JSFML/JNI/org_jsfml_graphics_SFMLNativeDrawer.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/RenderStates.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/Drawable.hpp>
#include <SFML/Graphics/RenderTarget.hpp>

/*
 * Class:     org_jsfml_graphics_SFMLNativeDrawer
 * Method:    nativeDrawVertices
 * Signature: (ILjava/nio/Buffer;ILorg/jsfml/graphics/RenderTarget;Lorg/jsfml/graphics/RenderStates;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeDrawer_nativeDrawVertices
    (JNIEnv *env, jclass cls, jint num, jobject vbuf, jint type, jobject jtarget, jobject jstates) {
    
    sf::RenderStates states = JSFML::RenderStates::ToSFML(env, jstates);
    sf::RenderTarget *target = JSFML::NativeObject::GetExPointer<sf::RenderTarget>(
        env, jtarget, org_jsfml_internal_ExPtr_RENDER_TARGET);
    sf::Vertex *verts = (sf::Vertex*)env->GetDirectBufferAddress(vbuf);
    
    target->draw(verts, num, (sf::PrimitiveType)type, states);
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeDrawer
 * Method:    draw
 * Signature: (Lorg/jsfml/graphics/Drawable;Lorg/jsfml/graphics/RenderTarget;Lorg/jsfml/graphics/RenderStates;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeDrawer_draw
    (JNIEnv *env, jclass cls, jobject drawable, jobject target, jobject states) {

    JSFML::NativeObject::GetExPointer<sf::RenderTarget>(env, target, org_jsfml_internal_ExPtr_RENDER_TARGET)->draw(
        *JSFML::NativeObject::GetExPointer<sf::Drawable>(env, drawable, org_jsfml_internal_ExPtr_DRAWABLE),
        JSFML::RenderStates::ToSFML(env, states));
}
