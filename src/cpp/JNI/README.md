JNI Sources
===========
This directory contains the implementation of those Java methods declared as `native`. These sources must be kept in synch with the JNI headers, since missing methods will cause an `UnsatisfiedLinkError` in the Java application trying to use them.