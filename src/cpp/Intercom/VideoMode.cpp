#include <JSFML/Intercom/VideoMode.hpp>

jclass JSFML::VideoMode::cls = 0;
jmethodID JSFML::VideoMode::ctor = 0;

jfieldID JSFML::VideoMode::f_width = 0;
jfieldID JSFML::VideoMode::f_height = 0;
jfieldID JSFML::VideoMode::f_bpp = 0;

void JSFML::VideoMode::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/window/VideoMode");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(III)V");
        f_width = env->GetFieldID(javaClass, "width", "I");
        f_height = env->GetFieldID(javaClass, "height", "I");
        f_bpp = env->GetFieldID(javaClass, "bpp", "I");
        env->DeleteLocalRef(javaClass);
    }
}

jobject JSFML::VideoMode::GetDesktopMode(JNIEnv* env) {
	sf::VideoMode mode = sf::VideoMode::getDesktopMode();
	return env->NewObject(JSFML::VideoMode::cls, JSFML::VideoMode::ctor, mode.width, mode.height, mode.bitsPerPixel);
}

jobjectArray JSFML::VideoMode::GetFullscreenModes(JNIEnv* env) {
	std::vector<sf::VideoMode> modes = sf::VideoMode::getFullscreenModes();
	jobjectArray array = env->NewObjectArray(modes.size(), JSFML::VideoMode::cls, NULL);

	for(int i = 0; i < modes.size(); i++) {
		sf::VideoMode mode = modes[i];
		env->SetObjectArrayElement(array, i,
		    env->NewObject(JSFML::VideoMode::cls, JSFML::VideoMode::ctor, mode.width, mode.height, mode.bitsPerPixel));
	}
	return array;
}

sf::VideoMode JSFML::VideoMode::ToSFML(JNIEnv* env, jobject videoMode) {
	return sf::VideoMode(
		env->GetIntField(videoMode, f_width),
		env->GetIntField(videoMode, f_height),
		env->GetIntField(videoMode, f_bpp));
}
