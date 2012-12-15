package org.jsfml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation for method parameters that must not be {@code null}.
 * <p/>
 * If {@code null} is passed as a parameter that is annotated by this annotation,
 * a {@link NullPointerException} is to be thrown.
 */
@Documented
@Target(ElementType.PARAMETER)
public @interface NotNull {
}
