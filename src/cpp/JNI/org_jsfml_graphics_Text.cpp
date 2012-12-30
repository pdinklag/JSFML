#include <JSFML/JNI/org_jsfml_graphics_Text.h>

#include <JSFML/Intercom/Color.hpp>
#include <JSFML/Intercom/FloatRect.hpp>
#include <JSFML/Intercom/NativeObject.hpp>
#include <JSFML/Intercom/Vector2f.hpp>

#include <JSFML/JNI/org_jsfml_internal_ExPtr.h>

#include <SFML/Graphics/RenderTarget.hpp>
#include <SFML/Graphics/Text.hpp>

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeCreate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_Text_nativeCreate (JNIEnv *env, jobject obj) {
    return (jlong)new sf::Text();
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetExPtr
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetExPtr (JNIEnv *env, jobject obj) {
    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_DRAWABLE,
        dynamic_cast<sf::Drawable*>(THIS(sf::Text)));

    JSFML::NativeObject::SetExPointer(env, obj, org_jsfml_internal_ExPtr_TRANSFORMABLE,
        dynamic_cast<sf::Transformable*>(THIS(sf::Text)));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeDelete
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeDelete (JNIEnv *env, jobject obj) {
    delete THIS(sf::Text);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetString
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetString
    (JNIEnv *env, jobject obj, jstring str) {

	jsize len = env->GetStringLength(str);
	if(len > 0) {
		wchar_t *buffer = new wchar_t[len + 1];

		#if defined(SFML_SYSTEM_WINDOWS)
			// sizeof(wchar_t) == 2
			env->GetStringRegion(str, 0, len, (jchar *)buffer);
			THIS(sf::Text)->setString(sf::String(buffer));
		#else
			// sizeof(whcar_t) == 4
			const jchar *chars = env->GetStringChars(str, NULL);

			for(size_t i = 0; i < len; i++)
				buffer[i] = (wchar_t)chars[i];

			env->ReleaseStringChars(str, chars);
		#endif

		buffer[len] = 0;
		THIS(sf::Text)->setString(sf::String(buffer));

		delete[] buffer;
	} else {
                THIS(sf::Text)->setString(std::string(""));
	}
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetFont
 * Signature: (Lorg/jsfml/graphics/Font;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetFont
    (JNIEnv *env, jobject obj, jobject font) {

    THIS(sf::Text)->setFont(*JSFML::NativeObject::GetPointer<sf::Font>(env, font));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    setCharacterSize
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_setCharacterSize
    (JNIEnv *env, jobject obj, jint size) {

    THIS(sf::Text)->setCharacterSize(size);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    setStyle
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_setStyle
    (JNIEnv *env, jobject obj, jint style) {

    THIS(sf::Text)->setStyle(style);
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    nativeSetColor
 * Signature: (Lorg/jsfml/graphics/Color;)V
 */
JNIEXPORT void JNICALL Java_org_jsfml_graphics_Text_nativeSetColor (JNIEnv *env, jobject obj, jobject color) {
    THIS(sf::Text)->setColor(JSFML::Color::ToSFML(env, color));
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getCharacterSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Text_getCharacterSize (JNIEnv *env, jobject obj) {
    return THIS(sf::Text)->getCharacterSize();
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getStyle
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_jsfml_graphics_Text_getStyle (JNIEnv *env, jobject obj) {
    return THIS(sf::Text)->getStyle();
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getColor
 * Signature: ()Lorg/jsfml/graphics/Color;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getColor (JNIEnv *env, jobject obj) {
    return JSFML::Color::FromSFML(env, THIS(sf::Text)->getColor());
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getLocalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getLocalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::Text)->getLocalBounds());
}

/*
 * Class:     org_jsfml_graphics_Text
 * Method:    getGlobalBounds
 * Signature: ()Lorg/jsfml/graphics/FloatRect;
 */
JNIEXPORT jobject JNICALL Java_org_jsfml_graphics_Text_getGlobalBounds (JNIEnv *env, jobject obj) {
    return JSFML::FloatRect::FromSFML(env, THIS(sf::Text)->getGlobalBounds());
}
