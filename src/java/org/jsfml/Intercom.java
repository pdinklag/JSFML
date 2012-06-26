package org.jsfml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation for intercom-critical types, fields and constructors.
 * <p/>
 * This annotation is purely for information purposes and provides no actual functionality. Elements
 * annotated by this annotation must not be refactored without altering the respective C++ sources.
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR})
public @interface Intercom {
}
