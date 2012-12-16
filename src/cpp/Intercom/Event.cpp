#include <JSFML/Intercom/Event.hpp>

JSFML::Event::JavaClassInfo JSFML::Event::GenericEvent;
JSFML::Event::JavaClassInfo JSFML::Event::KeyEvent;
JSFML::Event::JavaClassInfo JSFML::Event::JoystickButtonEvent;
JSFML::Event::JavaClassInfo JSFML::Event::JoystickMoveEvent;
JSFML::Event::JavaClassInfo JSFML::Event::JoystickEvent;
JSFML::Event::JavaClassInfo JSFML::Event::MouseMoveEvent;
JSFML::Event::JavaClassInfo JSFML::Event::MouseButtonEvent;
JSFML::Event::JavaClassInfo JSFML::Event::MouseWheelEvent;
JSFML::Event::JavaClassInfo JSFML::Event::SizeEvent;
JSFML::Event::JavaClassInfo JSFML::Event::TextEvent;

void JSFML::Event::Init(JNIEnv* env) {
    jclass cls;

    cls = env->FindClass("org/jsfml/window/event/Event");
    if(cls) {
        GenericEvent.cls = (jclass)env->NewGlobalRef(cls);
		GenericEvent.ctor = env->GetMethodID(cls, "<init>", "(I)V");
		env->DeleteLocalRef(cls);
    }

    cls = env->FindClass("org/jsfml/window/event/JoystickButtonEvent");
    if(cls) {
        JoystickButtonEvent.cls = (jclass)env->NewGlobalRef(cls);
		JoystickButtonEvent.ctor = env->GetMethodID(cls, "<init>", "(III)V");
		env->DeleteLocalRef(cls);
    }

    cls = env->FindClass("org/jsfml/window/event/JoystickMoveEvent");
    if(cls) {
        JoystickMoveEvent.cls = (jclass)env->NewGlobalRef(cls);
		JoystickMoveEvent.ctor = env->GetMethodID(cls, "<init>", "(IIIF)V");
		env->DeleteLocalRef(cls);
    }

    cls = env->FindClass("org/jsfml/window/event/JoystickEvent");
    if(cls) {
        JoystickEvent.cls = (jclass)env->NewGlobalRef(cls);
		JoystickEvent.ctor = env->GetMethodID(cls, "<init>", "(II)V");
		env->DeleteLocalRef(cls);
    }

    cls = env->FindClass("org/jsfml/window/event/KeyEvent");
    if(cls) {
        KeyEvent.cls = (jclass)env->NewGlobalRef(cls);
		KeyEvent.ctor = env->GetMethodID(cls, "<init>", "(IIZZZZ)V");
		env->DeleteLocalRef(cls);
    }

    cls = env->FindClass("org/jsfml/window/event/MouseButtonEvent");
    if(cls) {
        MouseButtonEvent.cls = (jclass)env->NewGlobalRef(cls);
		MouseButtonEvent.ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
		env->DeleteLocalRef(cls);
    }

    cls = env->FindClass("org/jsfml/window/event/MouseEvent");
    if(cls) {
        MouseMoveEvent.cls = (jclass)env->NewGlobalRef(cls);
		MouseMoveEvent.ctor = env->GetMethodID(cls, "<init>", "(III)V");
		env->DeleteLocalRef(cls);
    }

    cls = env->FindClass("org/jsfml/window/event/MouseWheelEvent");
    if(cls) {
        MouseWheelEvent.cls = (jclass)env->NewGlobalRef(cls);
		MouseWheelEvent.ctor = env->GetMethodID(cls, "<init>", "(IIII)V");
		env->DeleteLocalRef(cls);
    }

    cls = env->FindClass("org/jsfml/window/event/SizeEvent");
    if(cls) {
        SizeEvent.cls = (jclass)env->NewGlobalRef(cls);
		SizeEvent.ctor = env->GetMethodID(cls, "<init>", "(III)V");
		env->DeleteLocalRef(cls);
    }

    cls = env->FindClass("org/jsfml/window/event/TextEvent");
    if(cls) {
        TextEvent.cls = (jclass)env->NewGlobalRef(cls);
		TextEvent.ctor = env->GetMethodID(cls, "<init>", "(IJ)V");
		env->DeleteLocalRef(cls);
    }
}

#define EVENT_PARAMS(x) x.cls, x.ctor, event.type

jobject JSFML::Event::FromSFML(JNIEnv* env, const sf::Event& event) {
	switch(event.type) {
		case sf::Event::Resized:
			return env->NewObject(EVENT_PARAMS(SizeEvent), event.size.width, event.size.height);

		case sf::Event::TextEntered:
			return env->NewObject(EVENT_PARAMS(TextEvent), (jlong)event.text.unicode);

		case sf::Event::KeyPressed:
		case sf::Event::KeyReleased:
			return env->NewObject(EVENT_PARAMS(KeyEvent), event.key.code, event.key.alt, event.key.shift, event.key.control, event.key.system);

		case sf::Event::MouseMoved:
			return env->NewObject(EVENT_PARAMS(MouseMoveEvent), event.mouseMove.x, event.mouseMove.y);

		case sf::Event::MouseButtonPressed:
		case sf::Event::MouseButtonReleased:
			return env->NewObject(EVENT_PARAMS(MouseButtonEvent), event.mouseButton.x, event.mouseButton.y, event.mouseButton.button);

		case sf::Event::MouseWheelMoved:
			return env->NewObject(EVENT_PARAMS(MouseWheelEvent), event.mouseWheel.x, event.mouseWheel.y, event.mouseWheel.delta);

		case sf::Event::JoystickButtonPressed:
		case sf::Event::JoystickButtonReleased:
			return env->NewObject(EVENT_PARAMS(JoystickButtonEvent), event.joystickButton.joystickId, event.joystickButton.button);

		case sf::Event::JoystickMoved:
			return env->NewObject(EVENT_PARAMS(JoystickMoveEvent), event.joystickMove.joystickId, event.joystickMove.axis, event.joystickMove.position);

		case sf::Event::JoystickConnected:
		case sf::Event::JoystickDisconnected:
			return env->NewObject(EVENT_PARAMS(JoystickEvent), event.joystickConnect.joystickId);

		default:
			return env->NewObject(EVENT_PARAMS(GenericEvent));
	}
}
