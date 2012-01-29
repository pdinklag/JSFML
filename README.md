JSFML - A Java binding to the Simple and Fast Multimedia Library (SFML)
=======================================================================

"SFML is a free multimedia C++ API that provides you low and high level access to graphics, input, audio, etc." (Source: http://www.sfml-dev.org/)

JSFML is a Java library that provides Java applications with access to a wide range of the functionalities of SFML.

#Building

Please check out this forum thread about building JSFML: http://www.sfml-dev.org/forum/viewtopic.php?p=45568

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

#Credits

Authors
-------
Patrick Dinklage - Head developer of JSFML (pdinklag@googlemail.com)

Additional Credits
------------------
Laurent Gomila - Author of SFML (http://www.sfml-dev.org/)
