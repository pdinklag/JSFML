package org.jsfml.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation for intercom-critical types, fields, methods and constructors.
 * <p/>
 * This annotation is purely for informational purposes and provides no actual functionality.
 * Elements annotated by this annotation must not be refactored without altering
 * the respective native (C++) sources.
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Intercom {
}
