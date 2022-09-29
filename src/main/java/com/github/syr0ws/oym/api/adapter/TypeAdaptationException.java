package com.github.syr0ws.oym.api.adapter;

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
