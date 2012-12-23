#ifndef JSFML_EVENT_H_
#define JSFML_EVENT_H_

#include <jni.h>
#include <SFML/Window/Event.hpp>

namespace JSFML {
    class Event {
        private:
            struct JavaClassInfo {
                jclass cls;
                jmethodID ctor;
            };

            static JavaClassInfo
                GenericEvent,
                KeyEvent,
                JoystickButtonEvent,
                JoystickMoveEvent,
                JoystickEvent,
                MouseMoveEvent,
                MouseButtonEvent,
                MouseWheelEvent,
                SizeEvent,
                TextEvent;

        public:
            static void Init(JNIEnv* env);

            static jobject FromSFML(JNIEnv* env, const sf::Event& event);

    };
}

#endif
