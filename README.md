JSFML - A Java binding to the Simple and Fast Multimedia Library (SFML)
=======================================================================

"SFML is a free multimedia C++ API that provides you low and high level access to graphics, input, audio, etc." (Source: http://www.sfml-dev.org/)

JSFML is a Java library that provides Java applications with access to a wide range of the functionalities of SFML.

#Building

Java
----
Building the Java classes can be done simply by invoking the "java.compile" target in the Apache Ant buildfile.

C++
---
In order to build the binaries on any supported system (at the time of writing: Windows x86, Linux x86),
the following preparations must be met:

 * The GNU C++ compiler executable `g++` must be in your system's `PATH` environment variable. The official Windows binaries were built with MinGW, therefore that is recommend.
 * The `JAVA_HOME` environment variable must point to a working JDK (1.6 or later).
 * The `SFML_HOME` environment variable must point to the SFML root directory, which contains `include` and `extlibs`.
 * It is assumed that `SFML_HOME` also has a sub-directory called `build/lib` in which the SFML libraries (lib*.a) must be located for linking. It should also contain the respective binaries (*.dll or *.so*).

The Apache Ant buildfile contains the following set of targets to compile and link JSFML:

 * `cpp.headers` -- Generates the JNI headers from the Java sources using `javah`.
 * `cpp.win32` -- Compiles and links the C++ sources for Windows 32 bit.
 * `cpp.linux32`-- Compiles and links the C++ sources for Linux 32 bit.

Build targets for other operating systems will follow.

JAR
---
The final self-contained JSFML package (containing the binaries for each system) can be built using the `jar` target in the Apache Ant buildfile.

Note that the jar file is expected to be signed off in that build target. In order to use JSFML in browser applets properly, this is a necessity to do.

Javadoc
-------
The javadoc for JSFML can be built using the `javadoc` target.

#Philosophy

What JSFML is and is not
------------------------
JSFML is a Java binding to SFML. It is _not_ Java re-implementation of SFML. This makes JSFML relatively easy to maintain and ensures that it can be updated to the latest SFML release, including all new features and bugfixes, in a minimal amount of time.

Implementation
--------------

The core code consists of native (C++) delegates to SFML methods, for which the Java Native Interface (JNI) is used. The Java code part of JSFML has the following tasks:

 * Provide a lightweight, yet effective, interface between Java objects and the underlying SFML objects (`SFMLNativeObject`).
 * Allow "self-contained"ness. This means that the platform-specific SFML and JSFML binaries (`dll`, `so`, etc.) should be hidden from JSFML users and end users and be extracted and loaded as needed. This avoids problems with supported SFML versions among other things, at the cost of a larger file.
 * Ensure stability by making sure invalid parameters (such as _null_) are never passed to native methods that expect C++ references, which cannot be _NULL_.
 * Adapt SFML to Java coding conventions. This includes _camelCase_ method names as well as exceptions being thrown instead of success values being returned from loading methods, among other things.
 * Provide additional features for Java integration, such as the ability to have an SFML window within an AWT/Swing application or a browser applet.

#Credits

Authors
-------
Patrick Dinklage - Head developer of JSFML (pdinklag@googlemail.com)

Additional Credits
------------------
Laurent Gomila - Author of SFML (http://www.sfml-dev.org/)
