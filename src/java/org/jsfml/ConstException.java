package org.jsfml;

/**
 * Thrown if a user attempts to invoke a method of a {@link Const} object
 * that would modify the respective object.
 * <p/>
 * This can only happen if one tries to work around the {@link Const} feature
 * using either reflection or type casting.
 */
public final class ConstException extends RuntimeException {
    private static final long serialVersionUID = 2668244761448108605L;

    public ConstException() {
        super("A method was invoked that would modify a Const object!");
    }
}
