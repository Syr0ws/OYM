package com.github.syr0ws.oym.api.adapter;

public class TypeAdapterNotFoundException extends Exception {

    public TypeAdapterNotFoundException(String message) {
        super(message);
    }

    public TypeAdapterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
