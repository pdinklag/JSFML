JSFML - A Java binding to the Simple and Fast Multimedia Library (SFML)
=======================================================================

"SFML is a free multimedia C++ API that provides you low and high level access to graphics, input, audio, etc." (Source: [http://www.sfml-dev.org/] )

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
 * It is assumed that `SFML_HOME` also has a sub-directory called `build/lib` in which the SFML libraries (lib*.a) must be located for linking. It should also contain the respective binaries (*.dll or *.so).

The Apache Ant buildfile contains the following set of targets to compile and link JSFML:

 * `cpp.headers` -- Generates the JNI headers from the Java sources using `javah`.
 * `cpp.win32` -- Compiles and links the C++ sources for Windows 32 bit (x86).

Build targets for other operating systems will follow.

JAR
---
The final self-contained JSFML package (containing the binaries for each system) can be built using the `jar` target in the Apache Ant buildfile.

#Credits

Authors
-------
Patrick Dinklage - Head developer of JSFML ( [pdinklag@googlemail.com] )

Additional Credits
------------------
Laurent Gomila - Author of SFML ( [http://www.sfml-dev.org/] )
