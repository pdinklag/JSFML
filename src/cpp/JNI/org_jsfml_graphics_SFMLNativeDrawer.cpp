#include <JSFML/JNI/org_jsfml_graphics_SFMLNativeDrawer.h>

#include <JSFML/Intercom/Intercom.hpp>
#include <JSFML/Intercom/NativeObject.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/Drawable.hpp>
#include <SFML/Graphics/RenderTarget.hpp>

/*
 * Class:     org_jsfml_graphics_SFMLNativeDrawer
 * Method:    nativeDrawVertices
 * Signature: (ILjava/nio/Buffer;ILorg/jsfml/graphics/RenderTarget;Ljava/nio/Buffer;Lorg/jsfml/graphics/ConstTexture;Lorg/jsfml/graphics/ConstShader;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeDrawer_nativeDrawVertices
    (JNIEnv *env, jclass cls, jint num, jobject vbuf, jint type, jobject jtarget,
        jobject xformAndBlendMode, jobject texture, jobject shader) {
    
    sf::RenderTarget *target = JSFML::NativeObject::GetExPointer<sf::RenderTarget>(
        env, jtarget, org_jsfml_internal_ExPtr_RENDER_TARGET);
    sf::Vertex *verts = (sf::Vertex*)env->GetDirectBufferAddress(vbuf);
    
    target->draw(verts, num, (sf::PrimitiveType)type,
        sf::RenderStates(JSFML::Intercom::decodeBlendModeOff(env, xformAndBlendMode),
                        JSFML::Intercom::decodeTransform(env, xform),
                        JSFML::NativeObject::GetPointer<sf::Texture>(env, texture),
                        JSFML::NativeObject::GetPointer<sf::Shader>(env, shader)));
}

/*
 * Class:     org_jsfml_graphics_SFMLNativeDrawer
 * Method:    nativeDrawDrawable
 * Signature: (Lorg/jsfml/graphics/Drawable;Lorg/jsfml/graphics/RenderTarget;Ljava/nio/Buffer;Lorg/jsfml/graphics/ConstTexture;Lorg/jsfml/graphics/ConstShader;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_SFMLNativeDrawer_nativeDrawDrawable
    (JNIEnv *env, jclass cls, jobject drawable, jobject target,
        jobject xformAndBlendMode, jobject texture, jobject shader) {

    JSFML::NativeObject::GetExPointer<sf::RenderTarget>(env, target, org_jsfml_internal_ExPtr_RENDER_TARGET)->draw(
        *JSFML::NativeObject::GetExPointer<sf::Drawable>(env, drawable, org_jsfml_internal_ExPtr_DRAWABLE),
        sf::RenderStates(JSFML::Intercom::decodeBlendModeOff(env, xformAndBlendMode),
                JSFML::Intercom::decodeTransform(env, xform),
                JSFML::NativeObject::GetPointer<sf::Texture>(env, texture),
                JSFML::NativeObject::GetPointer<sf::Shader>(env, shader)));
}
