package org.jsfml;

/**
 * Interface for read-only objects.
 * <p/>
 * This interface is JSFML's way to map C++ const references to Java. Subinterfaces will
 * be specific to a certain JSFML type and provide methods to read from them only.
 * <p/>
 * Every class for which a <tt>Const</tt> interface exists should implement that interface
 * for guaranteed functionality.
 * <p/>
 * The subinterfaces may be used freely outside of the JSFML API itself, however, it is not
 * recommended to implement them, because in certain cases, they are expected to be implemented
 * by a certain JSFML class.
 * <p/>
 * For critical use cases, as a protection from reflection or type casting,
 * JSFML will also provide invisible implementations that will throw a
 * {@link ConstException} in case a method is invoked that would in any way
 * modify the object.
 */
public interface Const {
}
