
> ⛔️ __Development on JSFML has ceased in December 2015. This project is outdated and no longer maintained or supported in any way and is hosted here solely for reference.__

# JSFML - Simple Fast Multimedia Library for Java

"SFML is a free multimedia C++ API that provides you low and high level access to graphics, input, audio, etc." (Source: http://www.sfml-dev.org/)

JSFML _was_ the official Java library that provides Java applications with access to a wide range of the functionalities of SFML.

## System requirements

To run a JSFML application, Java JRE 7 or higher is required.

Graphics features require OpenGL, audio features require OpenAL.

Furthermore, there are some platform-specific requirements that are listed below.

### Windows
Windows XP SP2 or higher is required. Furthermore, the _Visual C++ Redistributable for Visual Studio 2013_ needs to be installed.

### Linux
Generally, it is recommended to install the latest updates from your distribution's vendor. For windows, an X window environment is required.

Fedora users will need to install the package `openal-soft` manually.

## Building

Please refer to the wiki page about building JSFML: https://github.com/pdinklag/JSFML/wiki/Building-JSFML

# Philosophy

## What JSFML is and is not
JSFML is a Java binding to SFML. It is _not_ Java re-implementation of SFML. This makes JSFML relatively easy to maintain and ensures that it can be updated to the latest SFML release, including all new features and bugfixes, in a minimal amount of time.

However, for performance reasons, some data is held and managed in Java objects and some methods are implemented in Java. This is true for the so-called `Intercom` types such as vectors, colors, and other simple structures that do not have a lot of logic behind them.

To avoid having to deal with the problem of mapping SFML pointers back to Java objects, object members, such as a sprite's texture, are held in the Java object for quick access.

## Necessity of SFML features
Not all features / classes of SFML have a JSFML representation. This is because certain features are simply not necessary to be ported, because Java already has them out of the box. These include:

 * All multi-threading related features.
 * All networking related features.
 * The `sf::String` and `sf::Utf` classes for unicode support.

## Implementation
The core code consists of native (C++) delegates to SFML methods, for which the Java Native Interface (JNI) is used. The Java code part of JSFML has the following tasks:

 * Provide a lightweight, yet effective, interface between Java objects and the underlying SFML objects (`SFMLNativeObject`).
 * Allow "self-containedness". This means that the platform-specific SFML and JSFML binaries (`dll`, `so`, etc.) should be hidden from JSFML users and end users and be extracted and loaded as needed. This avoids problems with supported SFML versions among other things, at the cost of a larger file.
 * Ensure stability by making sure _null_ is never passed to native methods that expect C++ references, which cannot be _NULL_.
 * Adapt SFML to Java coding conventions. This includes exceptions being thrown instead of success values being returned from loading methods, among other things.
 * Provide additional features for Java integration, such as the ability to have an SFML window within an AWT/Swing application or a browser applet.

## Workaround conventions
Some things done in C++ are not possible in Java. This includes const references and operator overloading. JSFML follows the following principles:

 * Overloaded operators for SFML objects are represented by appropriately named static methods within the class in question.
 * Simple data model classes (such as `Vector2f`) are designed to be immutable.
 * Classes used in _const_ fields in the C++ implementation get an interface prefixed _Const_, only providing read operations. The interface is used throughout the implementation where possible, rather than the mutable class.
 * Unsigned value types (e.g. `unsigned int`) will be mapped to the corresponding signed type in Java, since there are no unsigned types in Java. In cases where this conversion can cause trouble, a longer Java type might be used instead (e.g. `int` for `unsigned char`).

#Credits

## Authors
* [Patrick Dinklage](https://github.com/pdinklag) - Main developer
* [Marco Antognini](https://github.com/mantognini) - Mac OS X developer

## Additional Credits
* [Laurent Gomila](https://github.com/LaurentGomila) - Author of SFML (http://www.sfml-dev.org/)
