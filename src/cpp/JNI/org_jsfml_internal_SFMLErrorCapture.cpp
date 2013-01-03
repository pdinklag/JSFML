#include <JSFML/JNI/org_jsfml_internal_SFMLErrorCapture.h>

#include <SFML/System/Err.hpp>
#include <sstream>

std::stringstream *buffer = NULL;
std::streambuf *old = NULL;

/*
 * Class:     org_jsfml_internal_SFMLErrorCapture
 * Method:    nativeStart
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_jsfml_internal_SFMLErrorCapture_nativeStart(JNIEnv *env, jclass cls) {
    buffer = new std::stringstream();
    old = sf::err().rdbuf(buffer->rdbuf());
}

/*
 * Class:     org_jsfml_internal_StdErrCapture
 * Method:    nativeFinish
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_jsfml_internal_SFMLErrorCapture_nativeFinish(JNIEnv *env, jclass cls) {
    //Flush error stream
    sf::err().flush();
    
    //Get ouput
    jstring output = env->NewStringUTF(buffer->str().c_str());
    
    //Reset old stream
    sf::err().rdbuf(old);
    old = NULL;
    
    //Delete buffer
    delete buffer;
    buffer = NULL;
    
    //Return output
    return output;
}
