package org.jsfml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation for method parameters that must not be <tt>null</tt>, because they
 * are going to be passed to a native method.
 * <p/>
 * If <tt>null</tt> is passed as a parameter that is annotated by this annotation,
 * a {@link IllegalArgumentException} is to be thrown.
 */
@Documented
@Target(ElementType.PARAMETER)
public @interface NotNull {
}
