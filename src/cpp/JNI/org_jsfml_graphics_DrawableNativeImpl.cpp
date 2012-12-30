#include <JSFML/JNI/org_jsfml_graphics_DrawableNativeImpl.h>

#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/RenderStates.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/Drawable.hpp>
#include <SFML/Graphics/RenderTarget.hpp>

/*
 * Class:     org_jsfml_graphics_DrawableNativeImpl
 * Method:    draw
 * Signature: (Lorg/jsfml/graphics/Drawable;Lorg/jsfml/graphics/RenderTarget;Lorg/jsfml/graphics/RenderStates;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_DrawableNativeImpl_draw
    (JNIEnv *env, jclass cls, jobject drawable, jobject target, jobject states) {

    JSFML::NativeObject::GetExPointer<sf::RenderTarget>(env, target, org_jsfml_internal_ExPtr_RENDER_TARGET)->draw(
        *JSFML::NativeObject::GetExPointer<sf::Drawable>(env, drawable, org_jsfml_internal_ExPtr_DRAWABLE),
        JSFML::RenderStates::ToSFML(env, states));
}
