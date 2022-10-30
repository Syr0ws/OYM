package com.github.syr0ws.oym.api.adapter;

/**
 * Exception raised when no TypeAdapter can be found for an object type.
 */
public class TypeAdapterNotFoundException extends Exception {

    public TypeAdapterNotFoundException(String message) {
        super(message);
    }

    public TypeAdapterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
