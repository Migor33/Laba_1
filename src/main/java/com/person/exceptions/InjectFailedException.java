package com.person.exceptions;

/**
 * Inject Exception.
 */
public class InjectFailedException extends Exception {
    /**
     * Exception constructor.
     * @param exception real exception.
     */
    public InjectFailedException(final Exception exception) {
        super("InjectException: " + exception.getMessage());
    }
}
