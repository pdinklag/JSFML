
JSFML - Simple Fast Multimedia Library for Java
===============================================

"SFML is a free multimedia C++ API that provides you low and high level access to graphics, input, audio, etc." (Source: http://www.sfml-dev.org/)

JSFML is the official Java library that provides Java applications with access to a wide range of the functionalities of SFML.

#Building

Please check out this forum thread about building JSFML: http://en.sfml-dev.org/forums/index.php?topic=6906.msg45568#msg45568

Note : to build JSFML on Mac OS X you need to install the "java for developer" tools. Moreover, you can use `java_home` utility to set JAVA_HOME variable like this : `export JAVA_HOME=`/usr/libexec/java_home` `

#Mac OS X

## Known Issue

JSFML currently needs a little trick to work on Mac OS X. You have to add `-XstartOnFirstThread` to the JVM launch option. For example, to run the example you would use the command `java -XstartOnFirstThread -jar jsfml-examples.jar`.

## How to build SFML for JSFML

If you want to use your custom version of SFML with JSFML you need to build SFML the usual way. However, when everything is built, you have to run some shell commands to make everything work as expected.

For each custom SFML module rename the `libsfml-MODULE.2.0.dylib` into `libsfml-MODULE.dylib` and do :

    install_name_tool -id libsfml-MODULE.dylib libsfml-MODULE.dylib \
                      -change @executable_path/../Frameworks/libsfml-MODULE_REFERENCE_X.2.dylib @loader_path/libsfml-MODULE_REFERENCE_X.dylib \
                     [-change @executable_path/../Frameworks/libsfml-MODULE_REFERENCE_Y.2.dylib @loader_path/libsfml-MODULE_REFERENCE_Y.dylib \ ]
                      ...
                      libsfml-MODULE.dylib


e.g. if you want to use your custom graphics module you need to run :

    install_name_tool -id libsfml-graphics.dylib \
                      -change @executable_path/../Frameworks/libsfml-window.2.dylib @loader_path/libsfml-window.dylib \
                      -change @executable_path/../Frameworks/libsfml-system.2.dylib @loader_path/libsfml-system.dylib \
                      libsfml-graphics.dylib


For the audio module you have to run this command in addition to the previous ones :

    install_name_tool -change @executable_path/../Frameworks/sndfile.framework/Versions/A/sndfile @loader_path/libsndfile.dylib \
                      libsfml-audio.dylib


If you want to use your custom sndfile library you must have it as a dylib and run :

    install_name_tool -id libsndfile.dylib libsndfile.dylib


You can use sfml/bin/macosx_universal/updateBinaires.sh script to do most of this job automatically. See comment at the beginning of the script.


#Philosophy

What JSFML is and is not
------------------------
JSFML is a Java binding to SFML. It is _not_ Java re-implementation of SFML. This makes JSFML relatively easy to maintain and ensures that it can be updated to the latest SFML release, including all new features and bugfixes, in a minimal amount of time.

However, for performance reasons, some data is held and managed in Java objects and some methods are implemented in Java. This is true for the so-called `Intercom` types such as vectors, colors, and other simple structures that do not have a lot of logics behind them.

To avoid having to deal with the problem of mapping SFML pointers back to Java objects, object members, such as a sprite's texture, are held in the Java object for quick access.

Necessity of SFML features
--------------------------
Not all features / classes of SFML have a JSFML representation. This is because certain features are simply not necessary to be ported, because Java already has them out of the box. These include:

 * All multi-threading related features.
 * All networking related features.
 * The sf::String and sf::Utf classes for unicode support.

Implementation
--------------

The core code consists of native (C++) delegates to SFML methods, for which the Java Native Interface (JNI) is used. The Java code part of JSFML has the following tasks:

 * Provide a lightweight, yet effective, interface between Java objects and the underlying SFML objects (`SFMLNativeObject`).
 * Allow "self-contained"ness. This means that the platform-specific SFML and JSFML binaries (`dll`, `so`, etc.) should be hidden from JSFML users and end users and be extracted and loaded as needed. This avoids problems with supported SFML versions among other things, at the cost of a larger file.
 * Ensure stability by making sure _null_ is never passed to native methods that expect C++ references, which cannot be _NULL_.
 * Adapt SFML to Java coding conventions. This includes _camelCase_ method names as well as exceptions being thrown instead of success values being returned from loading methods, among other things.
 * Provide additional features for Java integration, such as the ability to have an SFML window within an AWT/Swing application or a browser applet.

Workaround conventions
----------------------
Some things done in C++ are not possible in Java. This includes const references and operator overloading. JSFML follows the following principles:

 * Overloaded operators for SFML objects are represented by appropriately named static methods within the class in question.
 * Objects passed to JSFML methods are never modified by those.
 * Objects retrieved from SFML that should not be altered, such as the default font or a render texture's texture object, are of an invisible immutable subtype and reject any change attempts silently.
 * Unsigned value types (e.g. `unsigned int`) will be mapped to the corresponding signed type in Java, since there are no unsigned types in Java. In cases where this conversion can cause trouble, a longer Java type might be used instead (e.g. `int` for `unsigned char`).

#Credits

Authors
-------
* [Patrick Dinklage][1] - Main developer
* [Marco Antognini][2] - Mac OS X developer

Additional Credits
------------------
* [Laurent Gomila][3] - Author of SFML (http://www.sfml-dev.org/)

[1]: https://github.com/pdinklag
[2]: https://github.com/mantognini
[3]: https://github.com/LaurentGomila
