package org.jsfml.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation for utility classes or methods introduced by JSFML.
 * <p/>
 * Classes and methods annotated by this have no real representation in SFML,
 * but were introduced for seamless Java integration.
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
public @interface JSFML {
}
