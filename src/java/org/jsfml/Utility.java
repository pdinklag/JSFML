package org.jsfml;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation for utility methods introduced by JSFML.
 * <p/>
 * Methods annotated by this have no real representation in SFML, but were introduced
 * by JSFML to add a more Java-esque feel.
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface Utility {
}
