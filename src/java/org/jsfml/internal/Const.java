package org.jsfml.internal;

/**
 * Interface for read-only objects.
 * <p/>
 * This interface is JSFML's way to map C++ const references to Java. Subinterfaces will
 * be specific to a certain JSFML type and provide methods to read from them only.
 * <p/>
 * The subinterfaces may be used freely outside of the JSFML API itself, however, it is not
 * recommended to implement them, because in certain cases, they are expected to be implemented
 * by a certain JSFML class.
 */
public interface Const {
}
