#include <JSFML/JNI/org_jsfml_internal_SFMLNative.h>

#include <JSFML/Intercom/JVM.hpp>
#include <JSFML/Intercom/JavaEnum.hpp>

#include <JSFML/Intercom/InputStream.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/NativeRef.hpp>

#include <JSFML/Intercom/Time.hpp>
#include <JSFML/Intercom/Vector2i.hpp>
#include <JSFML/Intercom/Vector2f.hpp>
#include <JSFML/Intercom/Vector2u.hpp>
#include <JSFML/Intercom/Vector3f.hpp>

#include <JSFML/Intercom/ContextSettings.hpp>
#include <JSFML/Intercom/Event.hpp>
#include <JSFML/Intercom/VideoMode.hpp>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/Glyph.hpp>
#include <JSFML/Intercom/IntRect.hpp>
#include <JSFML/Intercom/RenderStates.hpp>
#include <JSFML/Intercom/Transform.hpp>
#include <JSFML/Intercom/Vertex.hpp>

#include <JSFML/Intercom/Chunk.hpp>
#include <JSFML/Intercom/SoundStream.hpp>

/*
 * Class:     org_jsfml_internal_SFMLNative
 * Method:    nativeInit
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_internal_SFMLNative_nativeInit (JNIEnv *env, jclass cls) {
    JVM::Init(env);
    JavaEnum::Init(env);
    
    JSFML::InputStream::Init(env);
    JSFML::NativeObject::Init(env);
    JSFML::NativeRef::Init(env);

    JSFML::Time::Init(env);
    JSFML::Vector2i::Init(env);
    JSFML::Vector2f::Init(env);
    JSFML::Vector2u::Init(env);
    JSFML::Vector3f::Init(env);

    JSFML::ContextSettings::Init(env);
    JSFML::Event::Init(env);
    JSFML::VideoMode::Init(env);

    JSFML::Color::Init(env);
    JSFML::FloatRect::Init(env);
    JSFML::Glyph::Init(env);
    JSFML::IntRect::Init(env);
    JSFML::RenderStates::Init(env);
    JSFML::Transform::Init(env);
    JSFML::Vertex::Init(env);

    JSFML::Chunk::Init(env);
    JSFML::SoundStream::Init(env);
}
