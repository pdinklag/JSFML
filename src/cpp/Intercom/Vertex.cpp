#include <JSFML/Intercom/Vertex.hpp>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

jclass JSFML::Vertex::cls = 0;
jmethodID JSFML::Vertex::ctor = 0;

jfieldID JSFML::Vertex::f_position = 0;
jfieldID JSFML::Vertex::f_color = 0;
jfieldID JSFML::Vertex::f_texCoords = 0;

void JSFML::Vertex::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/graphics/Vertex");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(Lorg.jsfml.graphics.Vector2f;Lorg.jsfml.graphics.Color;Lorg.jsfml.graphics.Vector2f;)V");
        f_position = env->GetFieldID(javaClass, "position", "Lorg.jsfml.graphics.Vector2f;");
        f_color = env->GetFieldID(javaClass, "color", "Lorg.jsfml.graphics.Color;");
        f_texCoords = env->GetFieldID(javaClass, "texCoords", "Lorg.jsfml.graphics.Vector2f;");
		env->DeleteLocalRef(javaClass);
    }
}

sf::Vertex JSFML::Vertex::ToSFML(JNIEnv* env, jobject vertex) {
	return sf::Vertex(
		JSFML::Vector2f::ToSFML(env, env->GetObjectField(vertex, f_position)),
		JSFML::Color::ToSFML(env, env->GetObjectField(vertex, f_color)),
		JSFML::Vector2f::ToSFML(env, env->GetObjectField(vertex, f_texCoords)));
}

jobject JSFML::Vertex::FromSFML(JNIEnv* env, const sf::Vertex& vertex) {
    return env->NewObject(cls, ctor,
		JSFML::Vector2f::FromSFML(env, vertex.Position),
		JSFML::Color::FromSFML(env, vertex.Color),
		JSFML::Vector2f::FromSFML(env, vertex.TexCoords));
}
