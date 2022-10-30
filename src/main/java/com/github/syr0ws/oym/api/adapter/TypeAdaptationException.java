package com.github.syr0ws.oym.api.adapter;

/**
 * Exception raised when an error occurs while adapting objects from and to nodes.
 */
public class TypeAdaptationException extends Exception {

    public TypeAdaptationException(String message) {
        super(message);
    }

    public TypeAdaptationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeAdaptationException(Throwable cause) {
        super(cause);
    }
}
