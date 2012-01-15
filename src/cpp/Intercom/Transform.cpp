#include <JSFML/Intercom/Transform.hpp>

jclass JSFML::Transform::cls = 0;
jmethodID JSFML::Transform::ctor = 0;

jfieldID JSFML::Transform::f_data = 0;

void JSFML::Transform::Init(JNIEnv* env) {
    jclass javaClass = env->FindClass("org/jsfml/graphics/Transform");
    if(javaClass) {
        cls = (jclass)env->NewGlobalRef(javaClass);
        ctor = env->GetMethodID(javaClass, "<init>", "(FFFFFFFFF)V");
        f_data = env->GetFieldID(javaClass, "data", "[F");
		env->DeleteLocalRef(javaClass);
    }
}

sf::Transform JSFML::Transform::ToSFML(JNIEnv* env, jobject transform) {
    jfloatArray floatArray = (jfloatArray)env->GetObjectField(transform, f_data);
    jfloat* data = env->GetFloatArrayElements(floatArray, 0);

    sf::Transform sfmlTransform(
        data[0], data[4], data[12],
        data[1], data[5], data[13],
        data[3], data[7], data[15]);

    env->ReleaseFloatArrayElements(floatArray, data, 0);

    return sfmlTransform;
}

jobject JSFML::Transform::FromSFML(JNIEnv* env, const sf::Transform& transform) {
    const float* data = transform.GetMatrix();
    return env->NewObject(cls, ctor,
            data[0], data[4], data[12],
            data[1], data[5], data[13],
            data[3], data[7], data[15]);
}
